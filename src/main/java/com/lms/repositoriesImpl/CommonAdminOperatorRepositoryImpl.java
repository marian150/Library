package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.CommonAdminOperatorRepository;
import com.lms.security.Password;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Dependent
public class CommonAdminOperatorRepositoryImpl implements CommonAdminOperatorRepository {
    Logger logger = Logger.getLogger(CommonAdminOperatorRepositoryImpl.class);

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        User user = new User();

        try {
            user.setFirstName(signUpDTO.getFirstname());
            user.setLastName(signUpDTO.getLastname());
            user.setEmail(signUpDTO.getEmail());
            user.setPassword(Password.hashPassword(signUpDTO.getPassword()));
            user.setPhone(signUpDTO.getPhone());
            user.setRegDate(LocalDate.now());
            user.setRating(50);
        } catch (Exception e) {}

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            UserType userType = (UserType) session.load(UserType.class, 2L);
            user.setUserType(userType);
            Serializable userId = session.save(user);
            tx.commit();
            logger.info("Reader " + userId + " is created");
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback();
            logger.error("Unable to create reader", e);
            return false;
        } finally {
            session.close();
        }
    }
    @Override
    public List<Book> searchBook(Map<String, String> values) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Transaction tx =null;
        try {
            tx = session.beginTransaction();
            List<Predicate> predicates = new ArrayList<>();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            // Main query - the thing (Book) that needs to get retrieved
            CriteriaQuery<Book> cqBook = cb.createQuery(Book.class);
            Root<Book> bookRoot = cqBook.from(Book.class);

            //Subquery - to find which books have return_id != null, so that only books which are available can be displayed for lending
            Subquery<RentBook> cqSubquery = cqBook.subquery(RentBook.class);
            Root<RentBook> rentBookRoot = cqSubquery.from(RentBook.class);

            // fetches to get additional data about books
            bookRoot.fetch("publisher", JoinType.LEFT);
            bookRoot.fetch("authors", JoinType.LEFT);
            bookRoot.fetch("genre", JoinType.LEFT);
            bookRoot.fetch("bookState", JoinType.LEFT);

            // (SELECT DISTINCT book FROM rent_book JOIN book ON [...] WHERE book.publisher LIKE 'X' AND .... )
            cqSubquery.select(rentBookRoot.get("book")).distinct(true);

            if (values.containsKey("publisher"))
                predicates.add(cb.like(cb.lower(bookRoot.get("publisher").get("publisherName")), values.get("publisher").toLowerCase()));
            if (values.containsKey("authors"))
                predicates.add(cb.like(bookRoot.join("authors").get("name"), values.get("authors")));
            if (values.containsKey("genre"))
                predicates.add(cb.like(bookRoot.get("genre").get("name"), values.get("genre")));
            if (values.containsKey("bookState"))
                predicates.add(cb.like(bookRoot.get("bookState").get("stateName"), values.get("bookState")));
            if (values.containsKey("title"))
                predicates.add(cb.like(bookRoot.get("title"), values.get("title")));
            if (values.containsKey("isbn"))
                predicates.add(cb.like(bookRoot.get("isbn"), values.get("isbn")));
            if (values.containsKey("bookId"))
                predicates.add(cb.equal(bookRoot.get("bookId"), Long.parseLong(values.get("bookId"))));
            if (values.containsKey("issueDate"))
                predicates.add(cb.like(bookRoot.get("issueDate"), values.get("issueDate")));


            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            cqSubquery.where(cb.isNull(rentBookRoot.get("returnBook")));

            // SELECT DISTINCT * FROM book WHERE id NOT IN (cqSubquery) -> only books which have a return_id respectively return_date are allowed to be lent
            cqBook.select(bookRoot).where(cb.not(bookRoot.in(cqSubquery)), finalPredicate).distinct(true);
            TypedQuery<Book> typedQuery = session.createQuery(cqBook);

            List<Book> availableBooks = typedQuery.getResultList();

            return availableBooks;
        } catch (NoResultException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Book book = new Book();

        List<String> authorListString = Arrays.asList(addBookDTO.getAuthor().split(","));
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> a = cq.from(Author.class);
        cq.select(a);
        List<Predicate> predicates = new ArrayList<>();

        for(String temp : authorListString) {
            predicates.add(cb.like(a.get("name"), temp));
        }
        Predicate finalPredicate = cb.or(predicates.toArray((new Predicate[predicates.size()])));


        cq.where(finalPredicate);
        TypedQuery<Author> typedQuery = session.createQuery(cq);
        List<Author> authorsList = typedQuery.getResultList();
        Set<Author> authors = new HashSet<>(authorsList);
        for(Author au : authors) {
            System.out.println("Authors:\n" + au.getAuthorId() + " " + au.getName());
        }

        Query queryBs = session.createQuery("Select bs from BookState bs where bs.stateName like 'New'");
        BookState bookState = (BookState)queryBs.getSingleResult();

        Query queryBc = session.createQuery("Select bc from BookCovers bc where bc.coverName like :bookCover");
        queryBc.setParameter("bookCover", addBookDTO.getBookCovers());
        BookCovers bookCovers = (BookCovers)queryBc.getSingleResult();

        Query queryG = session.createQuery("Select g from Genre g where g.name like :genre");
        queryG.setParameter("genre", addBookDTO.getGenre());
        Genre genre = (Genre) queryG.getSingleResult();

        Query queryP = session.createQuery("Select p from Publisher p where p.publisherName like :pName");
        queryP.setParameter("pName", addBookDTO.getPublisher());
        Publisher publisher = (Publisher) queryP.getSingleResult();

        book.setPublisher(publisher);
        book.setBookCovers(bookCovers);
        book.setGenre(genre);
        book.setAuthors(authors);
        book.setBookState(bookState);
        book.setBookId(addBookDTO.getBookId());
        book.setIsbn(addBookDTO.getIsbn());
        book.setTitle(addBookDTO.getTitle());
        book.setIssueDate(addBookDTO.getIssueDate());

        Transaction tx = null;
        try {

            tx = session.beginTransaction();

            Serializable newBookId = session.save(book);
            tx.commit();
            logger.info("Book " + newBookId + " is added");
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback();
            logger.error("Unable to add book", e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<BookCovers> retrieveBookCovers() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        String hql = "Select bc from BookCovers bc";
        Query query = session.createQuery(hql);

        try {
            List<BookCovers> bookCovers = query.getResultList();
            session.close();
            return  bookCovers;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public List<Genre> retrieveBookGenre() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        String hql = "Select g from Genre g";
        Query query = session.createQuery(hql);

        try {
            List<Genre> genres = query.getResultList();
            session.close();
            return genres;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public List<BookState> retrieveBookState() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        String hql = "Select bs from BookState bs";
        Query query = session.createQuery(hql);

        try {
            List<BookState> bookStates = query.getResultList();
            session.close();
            return bookStates;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public boolean addPublisher(String publisherName) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherName);

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Serializable newPubId = session.save(publisher);
            tx.commit();
            logger.info("Publisher" + newPubId + " is created");
            return true;
        } catch (Exception e) {
            if(tx != null )tx.rollback();
            logger.error("Unable to create publisher", e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean searchPublisher(String publisherName) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        String hql = "select p from Publisher p where p.publisherName like :pName";
        Query query = session.createQuery(hql);
        query.setParameter("pName", publisherName);

        try {
            Object publisher = query.getSingleResult();
            return true;
        } catch (NoResultException e) {return false;}
        finally {
            session.close();
        }
    }

    @Override
    public boolean searchAuthor(String author) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        String hql = "select a from Author a where a.name like :aName";
        Query query = session.createQuery(hql);
        query.setParameter("aName", author);

        try {
            Object authorObject = query.getSingleResult();
            return true;
        } catch (NoResultException e) {return false;}
        finally {
            session.close();
        }
    }

    @Override
    public boolean addAuthor(String author) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Author authorObject = new Author();
        authorObject.setName(author);

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Serializable newAuthorId = session.save(authorObject);
            tx.commit();
            logger.info("Publisher" + newAuthorId + " is created");
            return true;
        } catch (Exception e) {
            if(tx != null)tx.rollback();
            logger.error("Unable to create publisher", e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean scrapBook(Long id) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select bs from BookState bs where bs.stateName like 'Obsolete'");
            BookState bs = (BookState)query.getSingleResult();
            Book book = (Book)session.load(Book.class, id);
            book.setBookState(bs);
            session.update(book);
            tx.commit();
            logger.info("Book " + book.getBookId().toString() + " status is changed to " + bs.getStateName());
            return true;
        } catch (Exception e) {
            if(tx != null)tx.rollback();
            logger.error("Unable to scrap book", e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<RentBook> findLentBooks(Map<String, String> values) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            List<Predicate> predicates = new ArrayList<>();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RentBook> cq = cb.createQuery(RentBook.class);
            Root<RentBook> rb = cq.from(RentBook.class);
            rb.fetch("client", JoinType.LEFT);
            rb.fetch("librarian", JoinType.LEFT);
            rb.fetch("book", JoinType.LEFT).fetch("authors", JoinType.LEFT);
            rb.fetch("rentType", JoinType.LEFT);
            cq.select(rb).distinct(true);


            if(values.containsKey("userId"))
                predicates.add(cb.equal(rb.get("client").get("userId"), Long.parseLong(values.get("userId"))));
            if(values.containsKey("firstName"))
                predicates.add(cb.like(rb.get("client").get("firstName"), values.get("firstName")));
            if(values.containsKey("lastName"))
                predicates.add(cb.like(rb.get("client").get("lastName"), values.get("lastName")));
            if(values.containsKey("bookId"))
                predicates.add(cb.equal(rb.get("book").get("bookId"), Long.parseLong(values.get("bookId"))));
            if(values.containsKey("title"))
                predicates.add(cb.like(rb.get("book").get("title"), values.get("title")));
            predicates.add(cb.isNull(rb.get("returnBook")));
            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            cq.where(finalPredicate);
            TypedQuery<RentBook> typedQuery = session.createQuery(cq);

            List<RentBook> result = typedQuery.getResultList();
            return result;
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean returnBooks(ReturnBookDTO books) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Serializable> returnedBooksIds = new ArrayList<>();
            RentBook rentBook = new RentBook();

            for(Long id : books.getBookIds()){
                // get the lent book into object/entity
                rentBook = session.get(RentBook.class, id);
                // create new entity ReturnBook which will be filled with details about the returning
                ReturnBook returnBook = new ReturnBook();
                returnBook.setReturnId(rentBook.getRentId()); // set the id of the row from rent_book
                returnBook.setLibId(books.getLibId()); // set id of librarian who accepted the book
                returnBook.setReturnDate(LocalDate.now()); // set current date
                // save entity to DB
                session.save(returnBook);
                // find the row from table return_book by the retrieved id
                ReturnBook lastReturnedBook = session.get(ReturnBook.class, id);
                // insert the id of the newly created row in return_book into the FK in rent_book
                rentBook.setReturnBook(lastReturnedBook);
                // save updated entity to DB
                Serializable returnBookId = session.save(rentBook);
                returnedBooksIds.add(returnBookId);
            }
            User user = rentBook.getClient();
            user.setRating(user.getRating() + 1);
            session.update(user);
            tx.commit();
            for (Serializable id : returnedBooksIds) {
                logger.info("Book " + id + " is returned");
            }
            return true;
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            logger.error("Unable to return books", e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public LocalDate extendDueDate(Long id) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            RentBook rentBookToBeUpdated = session.get(RentBook.class, id);
            rentBookToBeUpdated.setDueDate(rentBookToBeUpdated.getDueDate().plusMonths(1));
            session.update(rentBookToBeUpdated);
            tx.commit();
            RentBook rentBook = session.get(RentBook.class, id);
            return rentBook.getDueDate();
        } catch (Exception e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<LoadFormsModel> loadForms() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Predicate> predicates = new ArrayList<>();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<LoadFormsModel> cq = cb.createQuery(LoadFormsModel.class);
            Root<Notifications> notificationsRoot = cq.from(Notifications.class);

            notificationsRoot.join("form");
            notificationsRoot.join("status");

            //predicates.add(cb.like(notificationsRoot.get("status").get("statusName"), "New"));
            predicates.add(cb.isNotNull(notificationsRoot.get("form")));

            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));

            cq.multiselect(notificationsRoot.get("form").get("firstName"), notificationsRoot.get("form").get("lastName"),
                    notificationsRoot.get("form").get("email"), notificationsRoot.get("form").get("phone"),
                    notificationsRoot.get("form").get("submitDate"), notificationsRoot.get("status").get("statusName"));

            cq.where(finalPredicate);

            List<LoadFormsModel> notifications = session.createQuery(cq).getResultList();
            return notifications;
        } catch (NoResultException e) {
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }
    @Override
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Serializable> lentBooksIds = new ArrayList<>();

            for (Long bookId : lendBookDTO.getBookIDs()) {
                RentBook rentBook = new RentBook();

                Query queryB = session.createQuery("Select b from Book b where b.bookId = :bookId");
                queryB.setParameter("bookId", bookId);
                Book book = (Book) queryB.getSingleResult();
                rentBook.setBook(book);

                Query queryC = session.createQuery("Select u from User u where u.userId = :userId");
                queryC.setParameter("userId", lendBookDTO.getUserID());
                User client = (User) queryC.getSingleResult();
                rentBook.setClient(client);

                Query queryL = session.createQuery("Select u from User u where u.userId = :userId");
                queryL.setParameter("userId", userId);
                User lib = (User) queryL.getSingleResult();

                Query queryRT = session.createQuery("Select rt from RentType rt where rt.typeId = :typeId");
                queryRT.setParameter("typeId", lendBookDTO.getLendType());
                RentType rt = (RentType) queryRT.getSingleResult();
                rentBook.setRentType(rt);

                rentBook.setLibrarian(lib);
                rentBook.setRentDate(LocalDate.now());
                rentBook.setDueDate(LocalDate.now().plusMonths(1));
                Serializable lentBookId = session.save(rentBook);
                User user = rentBook.getClient();
                if (user.getRating() < 100) {
                    user.setRating(user.getRating() + 1);
                    session.update(user);
                }
                lentBooksIds.add(lentBookId);
            }
            tx.commit();
            for (Serializable id : lentBooksIds) {
                logger.info("Book " + id + " is lent");
            }
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Unable to lend books", e);
            return false;
        } finally {
            session.close();
        }
    }
}

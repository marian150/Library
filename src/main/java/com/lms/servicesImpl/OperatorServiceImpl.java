package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadBooksToBeArchivedModel;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.OperatorRepository;
import com.lms.services.CommonAdminOperatorService;
import com.lms.services.OperatorService;
import com.lms.services.PrivilegedUserService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Dependent
public class OperatorServiceImpl implements OperatorService {

    @Inject
    private OperatorRepository operatorRepository;
    @Inject
    private CommonAdminOperatorService commonAdminOperatorService;

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return commonAdminOperatorService.createReader(operatorRepository, signUpDTO);
    }

    @Override
    public List<User> searchUsers(Map<String, String> values) {

        return commonAdminOperatorService.searchUsers(operatorRepository, values);
    }

    @Override
    public List<Book> searchBook(Map<String, String> values) {
        return commonAdminOperatorService.searchBook(operatorRepository, values);
    }

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return commonAdminOperatorService.addBook(operatorRepository, addBookDTO);
    }

    @Override
    public boolean scrapBook(Long bookId) { return commonAdminOperatorService.scrapBook(operatorRepository, bookId); }

    @Override
    public List<BookCovers> retrieveBookCovers() {
        return commonAdminOperatorService.retrieveBookCovers(operatorRepository);
    }

    @Override
    public List<Genre> retrieveBookGenre() {
        return commonAdminOperatorService.retrieveBookGenre(operatorRepository);
    }

    @Override
    public List<BookState> retrieveBookState() {
        return commonAdminOperatorService.retrieveBookState(operatorRepository);
    }

    @Override
    public boolean addPublisher(String publisherName) {
        return commonAdminOperatorService.addPublisher(operatorRepository, publisherName);
    }

    @Override
    public boolean searchPublisher(String publisherName) {
        return commonAdminOperatorService.searchPublisher(operatorRepository, publisherName);
    }

    @Override
    public boolean searchAuthor(String author) {
        return commonAdminOperatorService.searchAuthor(operatorRepository, author);
    }

    @Override
    public boolean addAuthor(String author) {
        return commonAdminOperatorService.addAuthor(operatorRepository, author);
    }

    @Override
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) { return commonAdminOperatorService.lendBook(operatorRepository, lendBookDTO, userId); }

    @Override
    public boolean returnBooks(ReturnBookDTO returnBookDTO) { return commonAdminOperatorService.returnBooks(operatorRepository, returnBookDTO); }

    @Override
    public LocalDate extendDueDate(Long id) { return commonAdminOperatorService.extendDueDate(operatorRepository, id); }

    @Override
    public List<LoadFormsModel> loadForms() {
        return commonAdminOperatorService.loadForms(operatorRepository);
    }

    @Override
    public List<RentBook> findLentBooks(Map<String, String> values) { return commonAdminOperatorService.findLentBooks(operatorRepository, values); }

    @Override
    public List<LoadFormsModel> loadNewForms() {
        return operatorRepository.loadNewForms();
    }

    @Override
    public List<RentBook> loadOverdue() {
        return operatorRepository.loadOverdue();
    }

    @Override
    public List<LoadBooksToBeArchivedModel> loadBooksToBeArchived() {
        List<Book> books = operatorRepository.loadBooksToBeArchived();
        List<LoadBooksToBeArchivedModel> booksToBeArchivedModels = new ArrayList<>();
        for(int i = 0; i < books.size(); i++) {
            String authors = "";
            for (Author a : books.get(i).getAuthors())
                authors += a.getName() + ", ";
            booksToBeArchivedModels.add(new LoadBooksToBeArchivedModel(
                    books.get(i).getBookId().toString(),
                    books.get(i).getTitle(),
                    authors,
                    books.get(i).getIssueDate(),
                    books.get(i).getIsbn()));
        }
        return booksToBeArchivedModels;
    }


}

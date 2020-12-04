package com.lms.controllers.commonComponentsLogicImpl;

import com.lms.controllers.commonComponentsLogic.CommonAdminOperatorFunctionalities;
import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.*;
import com.lms.services.PrivilegedUserService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.enterprise.context.Dependent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class CommonAdminOperatorFunctionalitiesImpl implements CommonAdminOperatorFunctionalities {

    public List<Book> searchBook(TextField inv, TextField title, TextField isbn, TextField author, ComboBox genre, TextField publ, TextField year, ComboBox state, PrivilegedUserService pu) {
        Map<String, String> values = new HashMap<>();
        if(inv.getText() != "")
            values.put("bookId", inv.getText());
        if(title.getText() != "")
            values.put("title", title.getText());
        if(isbn.getText() != "")
            values.put("isbn", isbn.getText());
        if(author.getText() != "")
            values.put("authors", author.getText());
        if(genre.getValue() != null && genre.getValue() != "All")
            values.put("genre", String.valueOf(genre.getValue()));
        if(publ.getText() != "")
            values.put("publisher", publ.getText());
        if(year.getText() != "")
            values.put("issueDate", year.getText());
        if(state.getValue() != null && state.getValue() != "All")
            values.put("bookState", String.valueOf(state.getValue()));
        inv.clear();
        title.clear();
        isbn.clear();
        author.clear();
        publ.clear();
        year.clear();
        return pu.searchBook(values);
    }
    public void displayBooks(List<Book> books, TableView tableView, ObservableList<SearchBookTableView> observableList,
                             TableColumn<SearchBookTableView, String> inv, TableColumn<SearchBookTableView, String> title,
                             TableColumn<SearchBookTableView, String> author, TableColumn<SearchBookTableView, String> isbn,
                             TableColumn<SearchBookTableView, String> genre, TableColumn<SearchBookTableView, String> year,
                             TableColumn<SearchBookTableView, String> publ, TableColumn<SearchBookTableView, String> state){
        tableView.getItems().clear();
        for(int i = 0; i < books.size(); i ++){
            String authors = "";
            for (Author a : books.get(i).getAuthors()) {
                authors += a.getName() + ", ";
            }

            observableList.add(new SearchBookTableView(
                    new SimpleStringProperty(Long.toString(books.get(i).getBookId())),
                    new SimpleStringProperty(books.get(i).getTitle()),
                    new SimpleStringProperty(authors),
                    new SimpleStringProperty(books.get(i).getIsbn()),
                    new SimpleStringProperty(books.get(i).getPublisher().getPublisherName()),
                    new SimpleStringProperty(books.get(i).getIssueDate()),
                    new SimpleStringProperty(books.get(i).getGenre().getName()),
                    new SimpleStringProperty(books.get(i).getBookState().getStateName())));
        }
        inv.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        publ.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        tableView.setItems(observableList);
    }

    @Override
    public List<User> searchUsers(TextField id, TextField fname, TextField lname, TextField email, TextField phone,
                                    DatePicker from, DatePicker to, PrivilegedUserService pu) {
        Map<String, String> values = new HashMap<>();
        if(id.getText() != "")
            values.put("id", id.getText());
        if(fname.getText() != "")
            values.put("firstName", fname.getText());
        if(lname.getText() != "")
            values.put("lastName", lname.getText());
        if(email.getText() != "")
            values.put("email", email.getText());
        if(phone.getText() != "")
            values.put("phone", phone.getText());
        if(from.getValue() != null)
            values.put("fromDate", from.getValue().toString());
        if(to.getValue() != null)
            values.put("toDate", to.getValue().toString());
        id.clear();
        fname.clear();
        lname.clear();
        email.clear();
        phone.clear();
        from.setValue(null);
        to.setValue(null);
        return pu.searchUsers(values);
    }

    @Override
    public boolean scrapBook(PrivilegedUserService pu, TextField bookId) {
        return pu.scrapBook(Long.parseLong(bookId.getText()));
    }

    @Override
    public List<RentBook> findLentBooks(PrivilegedUserService pu, TextField rid, TextField fname, TextField lname, TextField inv, TextField title) {
        Map<String, String> values = new HashMap<>();
        if(rid.getText() != "")
            values.put("userId", rid.getText());
        if(fname.getText() != "")
            values.put("firstName", fname.getText());
        if(lname.getText() != "")
            values.put("lastName", lname.getText());
        if(inv.getText() != "")
            values.put("bookId", inv.getText());
        if(title.getText() != "")
            values.put("title", title.getText());
        return pu.findLentBooks(values);
    }

    @Override
    public void displayLentBooks(List<RentBook> books, TableView<ReturnBookTableView> tableView, ObservableList<ReturnBookTableView> observableList, TableColumn<ReturnBookTableView, String> lid,TableColumn<ReturnBookTableView, String> rid,
                                 TableColumn<ReturnBookTableView, String> inv, TableColumn<ReturnBookTableView, String> title, TableColumn<ReturnBookTableView, String> author,
                                 TableColumn<ReturnBookTableView, String> lend, TableColumn<ReturnBookTableView, String> due, TableColumn<ReturnBookTableView, String> operator,
                                 TableColumn<ReturnBookTableView, String> type) {
        tableView.getItems().clear();
        for(int i = 0; i < books.size(); i++){
            String authors = "";
            for (Author a : books.get(i).getBook().getAuthors()) {
                authors += a.getName() + ", ";
            }

            observableList.add(new ReturnBookTableView(
                    new SimpleStringProperty(books.get(i).getRentId().toString()),
                    new SimpleStringProperty(books.get(i).getClient().getUserId().toString()),
                    new SimpleStringProperty(books.get(i).getBook().getBookId().toString()),
                    new SimpleStringProperty(books.get(i).getBook().getTitle()),
                    new SimpleStringProperty(authors),
                    new SimpleStringProperty(books.get(i).getRentDate().toString()),
                    new SimpleStringProperty(books.get(i).getDueDate().toString()),
                    new SimpleStringProperty(books.get(i).getLibrarian().getFirstName() + " " + books.get(i).getLibrarian().getLastName()),
                    new SimpleStringProperty(books.get(i).getRentType().getTypeName())
            ));
        }
        lid.setCellValueFactory(new PropertyValueFactory<>("lendId"));
        rid.setCellValueFactory(new PropertyValueFactory<>("readerId"));
        inv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        lend.setCellValueFactory(new PropertyValueFactory<>("lendDate"));
        due.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableView.setItems(observableList);

    }
    @Override
    public void displayForms(List<FormTableView> forms, TableView tableView, ObservableList<FormTableView> observableList,
                             TableColumn<FormTableView, String> fname, TableColumn<FormTableView, String> lname,
                             TableColumn<FormTableView, String> email, TableColumn<FormTableView, String> phone,
                             TableColumn<FormTableView, String> date, TableColumn<FormTableView, String> status){
        tableView.getItems().clear();
        for (int i = 0; i < forms.size(); i++) {
            observableList.add(new FormTableView(
                    new SimpleStringProperty(forms.get(i).getFname()),
                    new SimpleStringProperty(forms.get(i).getLname()),
                    new SimpleStringProperty(forms.get(i).getEmail()),
                    new SimpleStringProperty(forms.get(i).getPhone()),
                    new SimpleStringProperty(forms.get(i).getSubmitDate()),
                    new SimpleStringProperty(forms.get(i).getStatus()),
                    new SimpleStringProperty("")
            ));
        }
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        date.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(observableList);
    }

    public void nullifyAddBookFields(TextField inv, ComboBox genre, ComboBox cover, TextField isbn, TextField author,
                                     TextField issueDate, TextField publ, TextField title) {

        inv.clear();
        genre.setValue(null);
        cover.setValue(null);
        isbn.clear();
        author.clear();
        issueDate.clear();
        publ.clear();
        title.clear();
    }

    public void nullifyCreateReaderFields(TextField fname, TextField lname, TextField email, TextField pass, TextField phone) {
        fname.clear(); lname.clear(); email.clear(); pass.clear(); phone.clear();
    }
    public void nullifyLendBookUserDetails(TextField phone, TextField email, TextField name, TextField id) {
        phone.clear();
        email.clear();
        name.clear();
        id.clear();
    }

    public void nullifyCrapBookFields(TextField author, TextField genre, TextField inv, TextField isbn, TextField publ, TextField title, TextField year) {
        author.clear();
        genre.clear();
        inv.clear();
        isbn.clear();
        publ.clear();
        title.clear();
        year.clear();
    }

    public List<BookCovers> retrieveBookCovers(PrivilegedUserService pu) {
        return pu.retrieveBookCovers();
    }

    public List<Genre> retrieveBookGenre(PrivilegedUserService pu) {
        return pu.retrieveBookGenre();
    }

    public List<BookState> retrieveBookState(PrivilegedUserService pu) {return pu.retrieveBookState();}

    public boolean searchPublisher(PrivilegedUserService pu, String publisherName) {return pu.searchPublisher(publisherName);}

    public boolean addPublisher(PrivilegedUserService pu, String publisherName) {
        return pu.addPublisher(publisherName);
    }

    public boolean searchAuthor(PrivilegedUserService pu, String author) {
        return pu.searchAuthor(author);
    }

    public boolean addAuthor(PrivilegedUserService pu, String author) {
        return pu.addAuthor(author);
    }
    public boolean createReader(PrivilegedUserService pu, TextField fname, TextField lname, TextField email, TextField pass, TextField phone){
        SignUpDTO signUpDTO = new SignUpDTO(fname.getText(), lname.getText(), email.getText(), pass.getText(), phone.getText());
        return pu.createReader(signUpDTO);
    }

    public boolean createReader(PrivilegedUserService pu, SignUpDTO signUpDTO){
        return pu.createReader(signUpDTO);
    }
    public boolean lendBook(PrivilegedUserService pu, Long lendType, User currentUser, ListView chosen_books_for_rent, TextField lend_rd_id) {
        LendBookDTO lendBookDTO = new LendBookDTO();
        lendBookDTO.setUserID(Long.parseLong(lend_rd_id.getText()));
        List<Long> books = new ArrayList<>();
        for(Object item : chosen_books_for_rent.getItems()) {
            String[] values = item.toString().split(" ");
            books.add(Long.parseLong(values[0]));
        }
        lendBookDTO.setBookIDs(books);
        lendBookDTO.setLendType(lendType);
        return pu.lendBook(lendBookDTO, currentUser.getUserId());
    };

    public boolean returnBooks(PrivilegedUserService pu, User currentUser, TableView<ReturnBookTableView> return_table_view){
        ReturnBookDTO returnBookDTO = new ReturnBookDTO();
        returnBookDTO.setLibId(currentUser.getUserId());
        List<Long> returnBookIds = new ArrayList<>();
        TableView.TableViewSelectionModel<ReturnBookTableView> selectionModel = return_table_view.getSelectionModel();
        ObservableList<ReturnBookTableView> selectedItems = selectionModel.getSelectedItems();

        for(ReturnBookTableView i : selectedItems){
            returnBookIds.add(Long.parseLong(i.getLendId()));
        }
        returnBookDTO.setBookIds(returnBookIds);
        return pu.returnBooks(returnBookDTO);
    }
    public void updateAfterReturn(Boolean isSuccessful, TableView<ReturnBookTableView> return_table_view){
        if(isSuccessful){
            TableView.TableViewSelectionModel<ReturnBookTableView> selectionModel = return_table_view.getSelectionModel();
            ObservableList<ReturnBookTableView> selectedItems = selectionModel.getSelectedItems();
            return_table_view.getItems().removeAll(selectedItems);
            return_table_view.getSelectionModel().clearSelection();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong");
            alert.show();
        }
    }

    public LocalDate extendDueDate(PrivilegedUserService pu, TableView<ReturnBookTableView> return_table_view){
        ReturnBookTableView selectedItem = return_table_view.getSelectionModel().getSelectedItem();
        return pu.extendDueDate(Long.parseLong(selectedItem.getLendId()));
    }

    public void updateAfterExtendDueDate(LocalDate newDueDate, TableView<ReturnBookTableView> return_table_view){

        if(newDueDate == null || newDueDate.equals(LocalDate.parse(return_table_view.getSelectionModel().getSelectedItem().getDueDate()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong");
            alert.show();
        } else {
            return_table_view.getSelectionModel().getSelectedItem().setDueDate(newDueDate.toString());
        }
    }

    public boolean addBook(PrivilegedUserService pu, TextField bookId, TextField isbn, TextField year, ComboBox genre, ComboBox cover, TextField publisher, TextField title, TextField author) {
        AddBookDTO addBookDTO = new AddBookDTO();
        addBookDTO.setBookId(Long.parseLong(bookId.getText()));
        addBookDTO.setIsbn(isbn.getText());
        addBookDTO.setIssueDate(year.getText());
        addBookDTO.setGenre(genre.getValue().toString());
        addBookDTO.setBookCovers(cover.getValue().toString());
        addBookDTO.setPublisher(publisher.getText());
        addBookDTO.setTitle(title.getText());
        addBookDTO.setAuthor(author.getText());
        return pu.addBook(addBookDTO);
    }
    public boolean checkNullValuesAddBook(TextField bookId, TextField isbn, TextField year, ComboBox genre, ComboBox cover, TextField publisher, TextField title, TextField author) {
        List<TextField> addBookValues = new ArrayList<>();
        addBookValues.add(author);
        addBookValues.add(title);
        addBookValues.add(year);
        addBookValues.add(isbn);
        addBookValues.add(publisher);
        addBookValues.add(bookId);
        for (TextField item : addBookValues) {
            if (item.getText().equals("")) {
                return true;
            }
        }
        if(genre.getSelectionModel().getSelectedItem() == null)
            return true;
        return cover.getSelectionModel().getSelectedItem() == null;
    }

}

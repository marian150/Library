package com.lms.controllers.commonComponentsLogicImpl;

import com.lms.controllers.commonComponentsLogic.CommonAdminOperatorFunctionalities;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.*;
import com.lms.services.PrivilegedUserService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.enterprise.context.Dependent;
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
    public void displayUsers(List<User> users, TableView<SearchReaderTableView> tableView, ObservableList<SearchReaderTableView> observableList,
                             TableColumn<SearchReaderTableView, String> rid, TableColumn<SearchReaderTableView, String> fname,
                             TableColumn<SearchReaderTableView, String> lname, TableColumn<SearchReaderTableView, String> email,
                             TableColumn<SearchReaderTableView, String> phone,  TableColumn<SearchReaderTableView, String> date,
                             TableColumn<SearchReaderTableView, String> rating
                             ){
        tableView.getItems().clear();
        for(int i = 0; i < users.size(); i ++){
            String ratingString;
            if(users.get(i).getRating() == -1){
                ratingString = "N/A";
            }else{
                ratingString = users.get(i).getRating().toString();
            }
            observableList.add(new SearchReaderTableView(
                    new SimpleLongProperty(users.get(i).getUserId()),
                    new SimpleStringProperty(users.get(i).getFirstName()),
                    new SimpleStringProperty(users.get(i).getLastName()),
                    new SimpleStringProperty(users.get(i).getEmail()),
                    new SimpleStringProperty(users.get(i).getPhone()),
                    new SimpleStringProperty(users.get(i).getRegDate().toString()),
                    new SimpleStringProperty(ratingString)));
        }
        rid.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        date.setCellValueFactory(new PropertyValueFactory<>("regdate"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableView.setItems(observableList);
    }
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
        return pu.scrapBook(pu, Long.parseLong(bookId.getText()));
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
                    new SimpleStringProperty(forms.get(i).getStatus()))
            );
        }
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        date.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(observableList);
    }

    public void nullifyAddBookFields(ComboBox genre, ComboBox cover, TextField isbn, TextField id, TextField author,
                                     TextField issueDate, TextField publ, TextField title) {

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


}

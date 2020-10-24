package com.lms.controllers.commonComponentsLogicImpl;

import com.lms.controllers.BrowseReaderController;
import com.lms.controllers.LoginController;
import com.lms.controllers.commonComponentsLogic.CommonAdminOperatorFunctionalities;
import com.lms.models.entities.Author;
import com.lms.models.entities.Book;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.SearchBookTableView;
import com.lms.models.nonpersistentclasses.SearchReaderTableView;
import com.lms.services.PrivilegedUserService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class CommonAdminOperatorFunctionalitiesImpl implements CommonAdminOperatorFunctionalities {
    public List<Book> searchBook(TextField inv, TextField title, TextField isbn, TextField author, TextField genre, TextField publ, TextField year, ComboBox state, PrivilegedUserService pu) {
        Map<String, String> values = new HashMap<>();
        if(inv.getText() != "")
            values.put("bookId", inv.getText());
        if(title.getText() != "")
            values.put("title", title.getText());
        if(isbn.getText() != "")
            values.put("isbn", isbn.getText());
        if(author.getText() != "")
            values.put("authors", author.getText());
        if(genre.getText() != "")
            values.put("genre", genre.getText());
        if(publ.getText() != "")
            values.put("publisher", publ.getText());
        if(year.getText() != "")
            values.put("issueDate", year.getText());
        if(state.getValue() != null)
            values.put("bookState", String.valueOf(state.getValue()));
        inv.clear();
        title.clear();
        isbn.clear();
        author.clear();
        genre.clear();
        publ.clear();
        year.clear();
        state.setValue(null);
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
                             TableColumn<SearchReaderTableView, String> phone,  TableColumn<SearchReaderTableView, String> date
                             ){
        tableView.getItems().clear();
        for(int i = 0; i < users.size(); i ++){
            observableList.add(new SearchReaderTableView(
                    new SimpleLongProperty(users.get(i).getUserId()),
                    new SimpleStringProperty(users.get(i).getFirstName()),
                    new SimpleStringProperty(users.get(i).getLastName()),
                    new SimpleStringProperty(users.get(i).getEmail()),
                    new SimpleStringProperty(users.get(i).getPhone()),
                    new SimpleStringProperty(users.get(i).getRegDate().toString())));
        }
        rid.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        date.setCellValueFactory(new PropertyValueFactory<>("regdate"));
        tableView.setItems(observableList);
    }
    public List<User> searchReader(TextField fname, TextField lname, TextField email, TextField phone,
                                   DatePicker from, DatePicker to, PrivilegedUserService pu) {
        Map<String, String> values = new HashMap<>();
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
        fname.clear();
        lname.clear();
        email.clear();
        phone.clear();
        from.setValue(null);
        to.setValue(null);
        return pu.searchReader(values);
    }

    @Override
    public void showLendBrowseReaderWindow(ActionEvent event, FXMLLoader fxmlLoader) {
        try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/lendBookBrowseReader.fxml")){
            Parent root = (Parent) fxmlLoader.load(fxml);
            BrowseReaderController browseReaderController = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}

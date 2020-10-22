package com.lms.controllers;


import com.lms.controllers.commonComponentsLogic.commonUserFunctionalities;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Author;
import com.lms.models.entities.Book;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.BrowseReaderTableView;
import com.lms.models.nonpersistentclasses.SearchBookTableView;
import com.lms.models.nonpersistentclasses.SearchReaderTableView;
import com.lms.services.OperatorService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorController {

    @Inject
    private OperatorService operatorService;
    @Inject
    private commonUserFunctionalities commonUserFunctionalities;

    private User currentUser;

    @Inject
    FXMLLoader fxmlLoader;
    @FXML
    private Label greeting_label;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private TextField phone;
    @FXML
    private Button create_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private AnchorPane create_reader_anchor;
    @FXML
    private TextField search_reader_fname;
    @FXML
    private TextField search_reader_lname;
    @FXML
    private TextField search_reader_email;
    @FXML
    private TextField search_reader_phone;
    @FXML
    private DatePicker search_reader_from;
    @FXML
    private DatePicker search_reader_to;
    @FXML
    private Button search_reader_btn;
    @FXML
    private Button lend_browse_reader_btn;
    @FXML
    private Button lend_browse_book_btn;
    @FXML
    private Button search_book_btn;
    @FXML
    private TextField search_book_inv;
    @FXML
    private TextField search_book_isbn;
    @FXML
    private TextField search_book_title;
    @FXML
    private TextField search_book_author;
    @FXML
    private TextField search_book_publisher;
    @FXML
    private TextField search_book_genre;
    @FXML
    private TextField search_book_date;
    @FXML
    private ComboBox search_book_state;
    @FXML
    private TextField lend_rd_id;
    @FXML
    private TextField lend_rd_name;
    @FXML
    private TextField lend_rd_email;
    @FXML
    private TextField lend_rd_phone;
    @FXML
    private TableView<SearchReaderTableView> reader_table_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> readerid_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> fname_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> lname_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> email_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> phone_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> regdate_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> rating_column_id;
    @FXML
    private TableView<SearchBookTableView> search_book_table_view;
    @FXML private TableColumn<SearchBookTableView, String> invnum_column_id;
    @FXML private TableColumn<SearchBookTableView, String> title_column_id;
    @FXML private TableColumn<SearchBookTableView, String> author_column_id;
    @FXML private TableColumn<SearchBookTableView, String> isbn_column_id;
    @FXML private TableColumn<SearchBookTableView, String> genre_column_id;
    @FXML private TableColumn<SearchBookTableView, String> publisher_column_id;
    @FXML private TableColumn<SearchBookTableView, String> year_column_id;
    @FXML private TableColumn<SearchBookTableView, String> state_column_id;

    ObservableList<SearchReaderTableView> readersObservableList = FXCollections.observableArrayList();
    ObservableList<SearchBookTableView> searchBooksObservableList = FXCollections.observableArrayList();

    public OperatorController() {}

    public boolean createReader(){
        SignUpDTO signUpDTO = new SignUpDTO(fname.getText(), lname.getText(), email.getText(), pass.getText(), phone.getText());
        return operatorService.createReader(signUpDTO);
    }

    public List<User> searchReader() {
        Map<String, String> values = new HashMap<>();
        if(search_reader_fname.getText() != "")
            values.put("firstName", search_reader_fname.getText());
        if(search_reader_lname.getText() != "")
            values.put("lastName", search_reader_lname.getText());
        if(search_reader_email.getText() != "")
            values.put("email", search_reader_email.getText());
        if(search_reader_phone.getText() != "")
            values.put("phone", search_reader_phone.getText());
        if(search_reader_from.getValue() != null)
            values.put("fromDate", search_reader_from.getValue().toString());
        if(search_reader_to.getValue() != null)
            values.put("toDate", search_reader_to.getValue().toString());
        search_reader_fname.clear();
        search_reader_lname.clear();
        search_reader_email.clear();
        search_reader_phone.clear();
        search_reader_from.setValue(null);
        search_reader_to.setValue(null);
        return operatorService.searchReader(values);
    }
    public void displayUsers(List<User> users){
        reader_table_id.getItems().clear();
        for(int i = 0; i < users.size(); i ++){
            readersObservableList.add(new SearchReaderTableView(
                    new SimpleLongProperty(users.get(i).getUserId()),
                    new SimpleStringProperty(users.get(i).getFirstName()),
                    new SimpleStringProperty(users.get(i).getLastName()),
                    new SimpleStringProperty(users.get(i).getEmail()),
                    new SimpleStringProperty(users.get(i).getPhone()),
                    new SimpleStringProperty(users.get(i).getRegDate().toString())));
        }
        readerid_column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname_column_id.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname_column_id.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email_column_id.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_column_id.setCellValueFactory(new PropertyValueFactory<>("phone"));
        regdate_column_id.setCellValueFactory(new PropertyValueFactory<>("regdate"));
        reader_table_id.setItems(readersObservableList);
    }

    public List<Book> searchBook() {
        Map<String, String> values = new HashMap<>();
        if(search_book_inv.getText() != "")
            values.put("bookId", search_book_inv.getText());
        if(search_book_title.getText() != "")
            values.put("title", search_book_title.getText());
        if(search_book_isbn.getText() != "")
            values.put("isbn", search_book_isbn.getText());
        if(search_book_author.getText() != "")
            values.put("authors", search_book_author.getText());
        if(search_book_genre.getText() != "")
            values.put("genre", search_book_genre.getText());
        if(search_book_publisher.getText() != "")
            values.put("publisher", search_book_publisher.getText());
        if(search_book_date.getText() != "")
            values.put("issueDate", search_book_date.getText());
        if(search_book_state.getValue() != null)
            values.put("bookState", String.valueOf(search_book_state.getValue()));
        search_book_inv.clear();
        search_book_title.clear();
        search_book_isbn.clear();
        search_book_author.clear();
        search_book_genre.clear();
        search_book_publisher.clear();
        search_book_date.clear();
        search_book_state.setValue(null);
        return operatorService.searchBook(values);
    }

    public void displayBooks(List<Book> books){
        search_book_table_view.getItems().clear();
        for(int i = 0; i < books.size(); i ++){
            String authors = "";
            for(Author a : books.get(i).getAuthors()){
                authors += a.getName() + ", ";
            }
            searchBooksObservableList.add(new SearchBookTableView(
                    new SimpleStringProperty(Long.toString(books.get(i).getBookId())),
                    new SimpleStringProperty(books.get(i).getTitle()),
                    new SimpleStringProperty(authors),
                    new SimpleStringProperty(books.get(i).getIsbn()),
                    new SimpleStringProperty(books.get(i).getPublisher().getPublisherName()),
                    new SimpleStringProperty(books.get(i).getIssueDate()),
                    new SimpleStringProperty(books.get(i).getGenre().getName()),
                    new SimpleStringProperty(books.get(i).getBookState().getStateName())));
        }
        invnum_column_id.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        title_column_id.setCellValueFactory(new PropertyValueFactory<>("title"));
        author_column_id.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbn_column_id.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genre_column_id.setCellValueFactory(new PropertyValueFactory<>("genre"));
        year_column_id.setCellValueFactory(new PropertyValueFactory<>("year"));
        publisher_column_id.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        state_column_id.setCellValueFactory(new PropertyValueFactory<>("state"));
        search_book_table_view.setItems(searchBooksObservableList);
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setGreeting_label(String names) {
        greeting_label.setText(names);
    }

    public void setBrowsedUserDetails(BrowseReaderTableView user) {
        lend_rd_id.setText(String.valueOf(user.getId()));
        lend_rd_name.setText(user.getFname() + " " + user.getLname());
        lend_rd_email.setText(user.getEmail());
        lend_rd_phone.setText(user.getPhone());
    }

    public void initialize() {
        create_btn.setOnAction(event -> {
            if(createReader()) {
                fname.clear(); lname.clear(); email.clear(); pass.clear(); phone.clear();
                Label createdUser = new Label("Successfully created user.");
                createdUser.setTextFill(Color.GREEN);
                create_reader_anchor.getChildren().add(createdUser);
            } else {
                fname.clear(); lname.clear(); email.clear(); pass.clear(); phone.clear();
                Label wentWrong = new Label("Something went wrong");
                wentWrong.setTextFill(Color.RED);
                create_reader_anchor.getChildren().add(wentWrong);
            }
        });

        search_reader_btn.setOnAction(event -> {
            List<User> result = searchReader();
            displayUsers(result);
        });

        search_book_btn.setOnAction(event -> {
            List<Book> result = searchBook();
            displayBooks(result);
        });

        lend_browse_reader_btn.setOnAction(event -> {
            try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/lendBookBrowseReader.fxml")){
                Parent root = (Parent) fxmlLoader.load(fxml);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                stage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        });

        logout_btn.setOnAction(event -> {
            commonUserFunctionalities.logout(greeting_label, fxmlLoader);
        });
    }

}

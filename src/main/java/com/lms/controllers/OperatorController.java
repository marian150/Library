package com.lms.controllers;


import com.lms.models.dtos.AddBookDTO;
import com.lms.controllers.commonComponentsLogic.CommonAdminOperatorFunctionalities;
import com.lms.controllers.commonComponentsLogic.CommonUserFunctionalities;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.*;
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
import net.bytebuddy.asm.Advice;
import org.dom4j.Text;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

public class OperatorController {

    @Inject
    private OperatorService operatorService;
    @Inject
    private CommonUserFunctionalities commonUserFunctionalities;
    @Inject
    private CommonAdminOperatorFunctionalities commonAdminOperatorFunctionalities;
    private User currentUser;
    @Inject
    FXMLLoader fxmlLoader;
    @FXML private Label greeting_label;
    @FXML private ListView add_book_for_rent_listview;
    @FXML private Button add_book_to_listview_btn;
    @FXML private Button add_listview_to_rent_book_btn;
    @FXML private ListView chosen_books_for_rent;
    @FXML private Button clear_all_lend_book_listview_btn;
    @FXML private Button clear_archived_lend_book_listview_btn;
    @FXML private TabPane tabPane;
    @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private TextField email;
    @FXML private TextField pass;
    @FXML private TextField phone;
    @FXML private Button create_btn;
    @FXML private Button logout_btn;
    @FXML private Button lend_for_home_btn;
    @FXML private Button lend_for_reading_room_btn;
    @FXML private AnchorPane create_reader_anchor;
    @FXML private TextField search_reader_ID;
    @FXML private TextField search_reader_fname;
    @FXML private TextField search_reader_lname;
    @FXML private TextField search_reader_email;
    @FXML private TextField search_reader_phone;
    @FXML private DatePicker search_reader_from;
    @FXML private DatePicker search_reader_to;
    @FXML private Button search_reader_btn;
    @FXML private Button reader_to_rent_book_btn;
    @FXML private Button search_book_btn;
    @FXML private TextField search_book_inv;
    @FXML private TextField search_book_isbn;
    @FXML private TextField search_book_title;
    @FXML private TextField search_book_author;
    @FXML private TextField search_book_publisher;
    @FXML private ComboBox search_book_genre;
    @FXML private TextField search_book_date;
    @FXML private ComboBox search_book_state;
    @FXML private TextField lend_rd_id;
    @FXML private TextField lend_rd_name;
    @FXML private TextField lend_rd_email;
    @FXML private TextField lend_rd_phone;
    @FXML private TextField add_book_ID;
    @FXML private TextField add_book_isbn;
    @FXML private TextField add_book_title;
    @FXML private TextField add_book_author;
    @FXML private TextField add_book_publisher;
    @FXML private ComboBox add_book_genre;
    @FXML private ComboBox add_book_cover;
    @FXML private TextField add_book_issue_date;
    @FXML private Button add_book_btn;
    @FXML private AnchorPane add_book_anchor;
    @FXML private TableView<SearchReaderTableView> reader_table_id;
    @FXML private TableColumn<SearchReaderTableView, String> readerid_column_id;
    @FXML private TableColumn<SearchReaderTableView, String> fname_column_id;
    @FXML private TableColumn<SearchReaderTableView, String> lname_column_id;
    @FXML private TableColumn<SearchReaderTableView, String> email_column_id;
    @FXML private TableColumn<SearchReaderTableView, String> phone_column_id;
    @FXML private TableColumn<SearchReaderTableView, String> regdate_column_id;
    @FXML private TableColumn<SearchReaderTableView, String> rating_column_id;
    @FXML private TableView<SearchBookTableView> search_book_table_view;
    @FXML private TableColumn<SearchBookTableView, String> invnum_column_id;
    @FXML private TableColumn<SearchBookTableView, String> title_column_id;
    @FXML private TableColumn<SearchBookTableView, String> author_column_id;
    @FXML private TableColumn<SearchBookTableView, String> isbn_column_id;
    @FXML private TableColumn<SearchBookTableView, String> genre_column_id;
    @FXML private TableColumn<SearchBookTableView, String> publisher_column_id;
    @FXML private TableColumn<SearchBookTableView, String> year_column_id;
    @FXML private TableColumn<SearchBookTableView, String> state_column_id;
    @FXML private Button scrap_btn;
    @FXML private TextField scrap_inv_id;
    @FXML private TextField scrap_isbn_id;
    @FXML private TextField scrap_title_id;
    @FXML private TextField scrap_author_id;
    @FXML private TextField scrap_publ_id;
    @FXML private TextField scrap_genre_id;
    @FXML private TextField scrap_year_id;
    @FXML private Button add_scrap_btn;
    @FXML private Button return_search_btn;
    @FXML private TextField return_reader_id;
    @FXML private TextField return_fname_id;
    @FXML private TextField return_lname_id;
    @FXML private TextField return_inv_id;
    @FXML private TextField return_title_id;
    @FXML private Button return_btn;
    @FXML private Button return_extend_btn;
    @FXML private TableView<ReturnBookTableView> return_table_view;
    @FXML private TableColumn<ReturnBookTableView, String> return_reader_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_inv_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_title_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_author_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_lend_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_due_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_operator_col_id;

    ObservableList<SearchReaderTableView> readersObservableList = FXCollections.observableArrayList();
    ObservableList<SearchBookTableView> searchBooksObservableList = FXCollections.observableArrayList();
    ObservableList<ReturnBookTableView> returnObservableList = FXCollections.observableArrayList();
    public OperatorController() {}

    public boolean createReader(){
        SignUpDTO signUpDTO = new SignUpDTO(fname.getText(), lname.getText(), email.getText(), pass.getText(), phone.getText());
        return operatorService.createReader(signUpDTO);
    }

    public boolean addBook() {
        AddBookDTO addBookDTO = new AddBookDTO();
        addBookDTO.setBookId(Long.parseLong(add_book_ID.getText()));
        addBookDTO.setIsbn(add_book_isbn.getText());
        addBookDTO.setIssueDate(add_book_issue_date.getText());
        addBookDTO.setGenre(add_book_genre.getValue().toString());
        addBookDTO.setBookCovers(add_book_cover.getValue().toString());
        addBookDTO.setPublisher(add_book_publisher.getText());
        addBookDTO.setTitle(add_book_title.getText());
        addBookDTO.setAuthor(add_book_author.getText());
        return operatorService.addBook(addBookDTO);
    }

    public List<BookCovers> retrieveBookCovers() {
        return operatorService.retrieveBookCovers();
    }

    public List<Genre> retrieveBookGenre() {
        return operatorService.retrieveBookGenre();
    }

    public List<BookState> retrieveBookState() {return operatorService.retrieveBookState();}

    public boolean searchPublisher(String publisherName) {return operatorService.searchPublisher(publisherName);}

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setGreeting_label(String names) {
        greeting_label.setText(names);
    }

    public void setBrowsedUserDetails(BrowseReaderTableView user) {
        System.out.println(user.getId() + " " + user.getFname());
        lend_rd_id.setText(String.valueOf(user.getId()));
        System.out.println(lend_rd_id.getText());
        lend_rd_name.setText(user.getFname() + " " + user.getLname());
        lend_rd_email.setText(user.getEmail());
        lend_rd_phone.setText(user.getPhone());
    }
    public void nullifyAddBookFields() {
        add_book_genre.setValue(null);
        add_book_cover.setValue(null);
        add_book_isbn.clear();
        add_book_ID.clear();
        add_book_author.clear();
        add_book_issue_date.clear();
        add_book_publisher.clear();
        add_book_title.clear();
    }

    public void nullifyCreateReaderFields() {
        fname.clear(); lname.clear(); email.clear(); pass.clear(); phone.clear();
    }
    public void nullifyLendBookUserDetails() {
        lend_rd_phone.clear();
        lend_rd_email.clear();
        lend_rd_name.clear();
        lend_rd_id.clear();
    }

    public boolean addPublisher(String publisherName) {
        return operatorService.addPublisher(publisherName);
    }

    public boolean searchAuthor(String author) {
        return operatorService.searchAuthor(author);
    }

    public boolean addAuthor(String author) {
        return operatorService.addAuthor(author);
    }

    public boolean lendBook(Long lendType) {
        LendBookDTO lendBookDTO = new LendBookDTO();
        lendBookDTO.setUserID(Long.parseLong(lend_rd_id.getText()));
        List<Long> books = new ArrayList<>();
        for(Object item : chosen_books_for_rent.getItems()) {
            books.add(Long.parseLong(item.toString().substring(0, 1)));
        }
        lendBookDTO.setBookIDs(books);
        lendBookDTO.setLendType(lendType);
        return operatorService.lendBook(lendBookDTO, currentUser.getUserId());
    };

    public void initialize() {
        for (BookCovers bc : retrieveBookCovers()) {
            add_book_cover.getItems().add(bc.getCoverName());
        }

        search_book_state.getItems().add("All");
        for (BookState bs : retrieveBookState()) {
            search_book_state.getItems().add(bs.getStateName());
        }

        search_book_genre.getItems().add("All");
        for (Genre g : retrieveBookGenre()) {
            add_book_genre.getItems().add(g.getName());
            search_book_genre.getItems().add(g.getName());
        }
        search_book_genre.getSelectionModel().selectFirst();
        search_book_state.getSelectionModel().selectFirst();

        create_btn.setOnAction(event -> {
            if(createReader()) {
                Label createdUser = new Label("Successfully created user.");
                createdUser.setTextFill(Color.GREEN);
                create_reader_anchor.getChildren().add(createdUser);
            } else {
                Label wentWrong = new Label("Something went wrong");
                wentWrong.setTextFill(Color.RED);
                create_reader_anchor.getChildren().add(wentWrong);
            }
            nullifyCreateReaderFields();
        });

        search_reader_btn.setOnAction(event -> {
            List<User> result = commonAdminOperatorFunctionalities.searchReader(search_reader_ID,
                    search_reader_fname,search_reader_lname, search_reader_email, search_reader_phone,
                    search_reader_from, search_reader_to, operatorService
            );
            // rating_column_id is still missing, to be added later
            commonAdminOperatorFunctionalities.displayUsers(
                    result, reader_table_id, readersObservableList, readerid_column_id,
                    fname_column_id, lname_column_id, email_column_id, phone_column_id,
                    regdate_column_id
            );
        });

        search_book_btn.setOnAction(event -> {
            List<Book> result = commonAdminOperatorFunctionalities.searchBook(
                    search_book_inv, search_book_title,search_book_isbn,
                    search_book_author,search_book_genre,search_book_publisher,
                    search_book_date,search_book_state,operatorService);
            commonAdminOperatorFunctionalities.displayBooks(
                    result, search_book_table_view,searchBooksObservableList,
                    invnum_column_id, title_column_id, author_column_id,
                    isbn_column_id, genre_column_id, year_column_id, publisher_column_id, state_column_id);
        });

        reader_to_rent_book_btn.setOnAction(event -> {
            SearchReaderTableView selectedReader = reader_table_id.getSelectionModel().getSelectedItem();
            tabPane.getSelectionModel().select(2);
            lend_rd_id.setText(selectedReader.getId().toString());
            lend_rd_name.setText(selectedReader.getFname() + " " + selectedReader.getLname());
            lend_rd_email.setText(selectedReader.getEmail());
            lend_rd_phone.setText(selectedReader.getPhone());
        });

        add_book_btn.setOnAction(event -> {
            boolean addBookSuccessfull = false;
            List<String> authorListString = Arrays.asList(add_book_author.getText().split(","));
            if(searchPublisher(add_book_publisher.getText())) {
                addBookSuccessfull = addBook();
            } else {
                addPublisher(add_book_publisher.getText());
                addBookSuccessfull = addBook();
            }
            if (addBookSuccessfull) {
                Label addedBook = new Label("Successfully added Book.");
                addedBook.setTextFill(Color.GREEN);
                add_book_anchor.getChildren().add(addedBook);
            }else {
                Label addedBook = new Label("Something went wrong.");
                addedBook.setTextFill(Color.RED);
                add_book_anchor.getChildren().add(addedBook);
            }
            nullifyAddBookFields();
        });

        add_book_to_listview_btn.setOnAction(event -> {
            SearchBookTableView selectedBook = search_book_table_view.getSelectionModel().getSelectedItem();
            if(selectedBook != null) {
                add_book_for_rent_listview.getItems().add(selectedBook.getInventoryNumber() + "  " +
                        selectedBook.getTitle() + "  " + selectedBook.getAuthor() + "  " + selectedBook.getState());
            }
        });

        add_listview_to_rent_book_btn.setOnAction(event -> {
            chosen_books_for_rent.getItems().addAll(add_book_for_rent_listview.getItems());
            tabPane.getSelectionModel().select(2);
            add_book_for_rent_listview.getItems().clear();
        });

        lend_for_reading_room_btn.setOnAction(event -> {
            lendBook(2L);
            chosen_books_for_rent.getItems().clear();
            nullifyLendBookUserDetails();
        });

        lend_for_home_btn.setOnAction(event -> {
            boolean containsArchivedBook = false;
            for (Object item : chosen_books_for_rent.getItems()) {
                if(item.toString().contains("Archived")) {
                    containsArchivedBook = true;
                    break;
                }
            }
            if(containsArchivedBook) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot lend Archived books for home!");
                alert.show();
            } else {
                lendBook(1L);
                chosen_books_for_rent.getItems().clear();
                nullifyLendBookUserDetails();
            }
        });

        clear_all_lend_book_listview_btn.setOnAction(event -> {
            chosen_books_for_rent.getItems().clear();
        });

        clear_archived_lend_book_listview_btn.setOnAction(event -> {
            for(Object item : chosen_books_for_rent.getItems()) {
                if(item.toString().contains("Archived")) {
                    chosen_books_for_rent.getItems().remove(item);
                }
            }
        });

        logout_btn.setOnAction(event -> {
            commonUserFunctionalities.logout(greeting_label, fxmlLoader);
        });

        scrap_btn.setOnAction(event -> {
            commonAdminOperatorFunctionalities.scrapBook(operatorService, scrap_inv_id);
        });
        add_scrap_btn.setOnAction(event -> {
            SearchBookTableView selectedBook = search_book_table_view.getSelectionModel().getSelectedItem();
            if(selectedBook != null) {
                scrap_inv_id.setText(selectedBook.getInventoryNumber());
                scrap_isbn_id.setText(selectedBook.getIsbn());
                scrap_author_id.setText(selectedBook.getAuthor());
                scrap_title_id.setText(selectedBook.getTitle());
                scrap_publ_id.setText(selectedBook.getPublisher());
                scrap_genre_id.setText(selectedBook.getGenre());
                scrap_year_id.setText(selectedBook.getYear());
            }
        });
        return_search_btn.setOnAction(event -> {
            List<RentBook> result = commonAdminOperatorFunctionalities.findLentBooks(operatorService, return_reader_id,
                    return_fname_id, return_lname_id, return_inv_id, return_title_id
                    );
            commonAdminOperatorFunctionalities.displayLentBooks(result, return_table_view, returnObservableList, return_reader_col_id,
                    return_inv_col_id,return_title_col_id, return_author_col_id,return_lend_col_id,
                    return_due_col_id, return_operator_col_id);
        });
    }
}

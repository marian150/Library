package com.lms.controllers;


import com.lms.models.dtos.AddBookDTO;
import com.lms.controllers.commonComponentsLogic.CommonAdminOperatorFunctionalities;
import com.lms.controllers.commonComponentsLogic.CommonUserFunctionalities;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.*;
import com.lms.services.OperatorService;
import com.lms.validation.base.Error;
import com.lms.validation.err_decorators.*;
import com.lms.validation.err_types.EmailError;
import com.lms.validation.err_types.NameError;
import com.lms.validation.err_types.PasswordError;
import com.lms.validation.err_types.PhoneError;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import javax.inject.Inject;
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
    Logger logger = Logger.getLogger(OperatorController.class);

    @Inject
    FXMLLoader fxmlLoader;
    @FXML
    private Label greeting_label;
    @FXML
    private ListView add_book_for_rent_listview;
    @FXML
    private Button add_book_to_listview_btn;
    @FXML
    private Button add_listview_to_rent_book_btn;
    @FXML
    private ListView chosen_books_for_rent;
    @FXML
    private Button clear_all_lend_book_listview_btn;
    @FXML
    private Button clear_archived_lend_book_listview_btn;
    @FXML
    private TabPane tabPane;
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
    private Button lend_for_home_btn;
    @FXML
    private Button lend_for_reading_room_btn;
    @FXML
    private AnchorPane create_reader_anchor;
    @FXML
    private TextField search_reader_ID;
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
    private Button reader_to_rent_book_btn;
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
    private ComboBox search_book_genre;
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
    private TextField add_book_ID;
    @FXML
    private TextField add_book_isbn;
    @FXML
    private TextField add_book_title;
    @FXML
    private TextField add_book_author;
    @FXML
    private TextField add_book_publisher;
    @FXML
    private ComboBox add_book_genre;
    @FXML
    private ComboBox add_book_cover;
    @FXML
    private TextField add_book_issue_date;
    @FXML
    private Button add_book_btn;
    @FXML
    private AnchorPane add_book_anchor;
    @FXML
    private Button load_forms_btn;
    @FXML
    private TableView<FormTableView> forms_load_tableview;
    @FXML
    private TableColumn<FormTableView, String> form_tableview_fname;
    @FXML
    private TableColumn<FormTableView, String> form_tableview_lname;
    @FXML
    private TableColumn<FormTableView, String> form_tableview_email;
    @FXML
    private TableColumn<FormTableView, String> form_tableview_phone;
    @FXML
    private TableColumn<FormTableView, String> form_tableview_date;
    @FXML
    private TableColumn<FormTableView, String> form_tableview_status;
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
    @FXML private CheckBox loyal_check_box;
    @FXML private CheckBox not_loyal_check_box;
    @FXML private TableView<ReturnBookTableView> return_table_view;
    @FXML private TableColumn<ReturnBookTableView, String> return_lendid_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_reader_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_inv_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_title_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_author_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_lend_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_due_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_operator_col_id;
    @FXML private TableColumn<ReturnBookTableView, String> return_type_col_id;
    ObservableList<SearchReaderTableView> readersObservableList = FXCollections.observableArrayList();
    ObservableList<SearchBookTableView> searchBooksObservableList = FXCollections.observableArrayList();
    ObservableList<ReturnBookTableView> returnObservableList = FXCollections.observableArrayList();
    ObservableList<FormTableView> formsObservableList = FXCollections.observableArrayList();

    public OperatorController() {}

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setGreeting_label(String names) {
        greeting_label.setText(names);
    }
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
            String[] values = item.toString().split(" ");
            books.add(Long.parseLong(values[0]));
        }
        lendBookDTO.setBookIDs(books);
        lendBookDTO.setLendType(lendType);
        return operatorService.lendBook(lendBookDTO, currentUser.getUserId());
    };

    public boolean returnBooks(){
        ReturnBookDTO returnBookDTO = new ReturnBookDTO();
        returnBookDTO.setLibId(currentUser.getUserId());
        List<Long> returnBookIds = new ArrayList<>();
        TableView.TableViewSelectionModel<ReturnBookTableView> selectionModel = return_table_view.getSelectionModel();
        ObservableList<ReturnBookTableView> selectedItems = selectionModel.getSelectedItems();

        for(ReturnBookTableView i : selectedItems){
            returnBookIds.add(Long.parseLong(i.getLendId()));
        }
        returnBookDTO.setBookIds(returnBookIds);
        return operatorService.returnBooks(returnBookDTO);
    }
    public void updateAfterReturn(Boolean isSuccessful){
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

    public LocalDate extendDueDate(){
        ReturnBookTableView selectedItem = return_table_view.getSelectionModel().getSelectedItem();
        return operatorService.extendDueDate(Long.parseLong(selectedItem.getLendId()));
    }

    public void updateAfterExtendDueDate(LocalDate newDueDate){

        if(newDueDate == null || newDueDate.equals(LocalDate.parse(return_table_view.getSelectionModel().getSelectedItem().getDueDate()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong");
            alert.show();
        } else {
            return_table_view.getSelectionModel().getSelectedItem().setDueDate(newDueDate.toString());
        }
    }

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
            Error emailError = new NotNullErrorDecorator(new EmailError());
            Error passError = new NotNullErrorDecorator(new UpperErrorDecorator(new LongLengthErrorDecorator(new ShortLengthErrorDecorator(new PasswordError()))));
            Error nameError = new NotNullErrorDecorator(new OnlyCharErrorDecorator(new LongLengthErrorDecorator(new NameError())));
            Error phoneError = new NotNullErrorDecorator(new OnlyNumbersErrorDecorator(new PhoneError()));
            if (!nameError.errors(fname.getText()).equals("Name errors:")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(nameError.errors(fname.getText()));
                alert.show();
            } else if (!nameError.errors(lname.getText()).equals("Name errors:")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(nameError.errors(lname.getText()));
                alert.show();
            } else if(emailError.errors(email.getText()) != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(emailError.errors(email.getText()));
                alert.show();
            } else if(!passError.errors(pass.getText()).equals("Password errors:")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(passError.errors(pass.getText()));
                alert.show();
            }else if (!phoneError.errors(phone.getText()).equals("Phone errors:")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(phoneError.errors(phone.getText()));
                alert.show();
            } else {
                logger.info(currentUser.getUserId().toString() + " attempting to create a new Reader");
                boolean isCreated = createReader();
                commonAdminOperatorFunctionalities.nullifyCreateReaderFields(fname, lname, email, pass, phone);
                Alert alert;
                if (isCreated) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Successfully created reader");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Something went wrong");
                }
                alert.show();
            }
        });

        search_reader_btn.setOnAction(event -> {
            List<User> result = commonAdminOperatorFunctionalities.searchUsers(search_reader_ID,
                    search_reader_fname,search_reader_lname, search_reader_email, search_reader_phone,
                    search_reader_from, search_reader_to, operatorService
            );
            commonAdminOperatorFunctionalities.displayUsers(
                    result, reader_table_id, readersObservableList, readerid_column_id,
                    fname_column_id, lname_column_id, email_column_id, phone_column_id,
                    regdate_column_id, rating_column_id
            );
        });

        search_book_btn.setOnAction(event -> {
            operatorService.checkForPostponed();
            List<Book> result = commonAdminOperatorFunctionalities.searchBook(
                    search_book_inv, search_book_title,search_book_isbn,
                    search_book_author,search_book_genre,search_book_publisher,
                    search_book_date,search_book_state,operatorService);
            commonAdminOperatorFunctionalities.displayBooks(
                    result, search_book_table_view,searchBooksObservableList,
                    invnum_column_id, title_column_id, author_column_id,
                    isbn_column_id, genre_column_id, year_column_id, publisher_column_id, state_column_id);
        });

        loyal_check_box.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(loyal_check_box.isSelected()) {
                reader_table_id.getItems().removeIf(item -> Integer.parseInt(item.getRating()) < 85);
            }
        });

        not_loyal_check_box.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(not_loyal_check_box.isSelected()) {
                reader_table_id.getItems().removeIf(item -> Integer.parseInt(item.getRating()) > 85);
            }
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
            boolean addBookSuccessful = false;
            List<String> authorListString = Arrays.asList(add_book_author.getText().split(","));
            for(String aut : authorListString) {
                if(!searchAuthor(aut))
                    addAuthor(aut);
            }
            if(searchPublisher(add_book_publisher.getText())) {
                logger.info(currentUser.getUserId().toString() + " attempting to add book");
                addBookSuccessful = addBook();
            } else {
                logger.info(currentUser.getUserId().toString() + " attempting to add publisher");
                addPublisher(add_book_publisher.getText());
                logger.info(currentUser.getUserId().toString() + " attempting to add book");
                addBookSuccessful = addBook();
            }
            if (addBookSuccessful) {
                Label addedBook = new Label("Successfully added Book.");
                addedBook.setTextFill(Color.GREEN);
                add_book_anchor.getChildren().add(addedBook);
            }else {
                Label addedBook = new Label("Something went wrong.");
                addedBook.setTextFill(Color.RED);
                add_book_anchor.getChildren().add(addedBook);
            }
            commonAdminOperatorFunctionalities.nullifyAddBookFields(add_book_genre, add_book_cover,add_book_isbn,add_book_ID,add_book_author,add_book_issue_date, add_book_publisher,add_book_title);
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
            logger.info(currentUser.getUserId().toString() + " is attempting to lend for Reading Room");
            lendBook(2L);
            chosen_books_for_rent.getItems().clear();
            commonAdminOperatorFunctionalities.nullifyLendBookUserDetails(lend_rd_phone, lend_rd_email, lend_rd_name, lend_rd_id);
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
                logger.info(currentUser.getUserId().toString() + " is attempting to lend for Home");
                boolean successfulLend = lendBook(1L);
                if (successfulLend) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Lend successful");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error when lending books");
                    alert.show();
                }
                chosen_books_for_rent.getItems().clear();
                commonAdminOperatorFunctionalities.nullifyLendBookUserDetails(lend_rd_phone, lend_rd_email, lend_rd_name, lend_rd_id);
            }
        });

        clear_all_lend_book_listview_btn.setOnAction(event -> {
            chosen_books_for_rent.getItems().clear();
        });

        clear_archived_lend_book_listview_btn.setOnAction(event -> {
            for(int i = 0; i < chosen_books_for_rent.getItems().size(); i++) {
                Object item = chosen_books_for_rent.getItems().get(i);
                if(item.toString().contains("Archived")) {
                    chosen_books_for_rent.getItems().remove(item);
                    i--;
                }
            }
        });

        logout_btn.setOnAction(event -> {
            logger.info(currentUser.getUserId().toString() + " is logging out");
            commonUserFunctionalities.logout(greeting_label, fxmlLoader);
        });

        scrap_btn.setOnAction(event -> {
            commonAdminOperatorFunctionalities.scrapBook(operatorService, scrap_inv_id);
            commonAdminOperatorFunctionalities.nullifyCrapBookFields(scrap_author_id,scrap_genre_id, scrap_inv_id, scrap_isbn_id,scrap_publ_id, scrap_title_id, scrap_year_id );
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
            tabPane.getSelectionModel().select(4);
        });
        return_search_btn.setOnAction(event -> {
            List<RentBook> result = commonAdminOperatorFunctionalities.findLentBooks(operatorService, return_reader_id,
                    return_fname_id, return_lname_id, return_inv_id, return_title_id
                    );
            commonAdminOperatorFunctionalities.displayLentBooks(result, return_table_view, returnObservableList, return_lendid_col_id,return_reader_col_id,
                    return_inv_col_id,return_title_col_id, return_author_col_id,return_lend_col_id,
                    return_due_col_id, return_operator_col_id, return_type_col_id);
            TableView.TableViewSelectionModel<ReturnBookTableView> selectionModel = return_table_view.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        });
        return_btn.setOnAction(event -> {
            if (!return_table_view.getSelectionModel().isEmpty())
                updateAfterReturn(returnBooks());
        });

        return_extend_btn.setOnAction(event -> {
            updateAfterExtendDueDate(extendDueDate());
        });

        load_forms_btn.setOnAction(event -> {
            List<LoadFormsModel> forms = operatorService.loadForms();
            List<FormTableView> formsForDisplay = new ArrayList<>();
            for (LoadFormsModel lf : forms) {
                System.out.println(lf.getFirst_name());
                formsForDisplay.add(new FormTableView(new SimpleStringProperty(lf.getFirst_name()),
                        new SimpleStringProperty(lf.getLast_name()),
                        new SimpleStringProperty(lf.getEmail()), new SimpleStringProperty(lf.getPhone()),
                        new SimpleStringProperty(lf.getDate().toString()), new SimpleStringProperty(lf.getStatus())));
            }
            if (forms != null) {
                commonAdminOperatorFunctionalities.displayForms(formsForDisplay, forms_load_tableview,
                        formsObservableList, form_tableview_fname, form_tableview_lname, form_tableview_email,
                        form_tableview_phone, form_tableview_date, form_tableview_status);
            }
        });
    }

}

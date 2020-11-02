package com.lms.controllers.commonComponentsLogic;

import com.lms.models.entities.Book;
import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.ReturnBookTableView;
import com.lms.models.nonpersistentclasses.SearchBookTableView;
import com.lms.models.nonpersistentclasses.SearchReaderTableView;
import com.lms.services.PrivilegedUserService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.util.List;

public interface CommonAdminOperatorFunctionalities {
    List<Book> searchBook(TextField inv, TextField title, TextField isbn, TextField author, ComboBox genre,
                          TextField publ, TextField year, ComboBox state, PrivilegedUserService os);
    void displayBooks(List<Book> books, TableView tableView, ObservableList<SearchBookTableView> observableList,
                      TableColumn<SearchBookTableView, String> inv, TableColumn<SearchBookTableView, String> title,
                      TableColumn<SearchBookTableView, String> author, TableColumn<SearchBookTableView, String> isbn,
                      TableColumn<SearchBookTableView, String> genre, TableColumn<SearchBookTableView, String> year,
                      TableColumn<SearchBookTableView, String> publ, TableColumn<SearchBookTableView, String> state);
    void displayUsers(List<User> users, TableView<SearchReaderTableView> tableView, ObservableList<SearchReaderTableView> observableList,
                      TableColumn<SearchReaderTableView, String> rid, TableColumn<SearchReaderTableView, String> fname,
                      TableColumn<SearchReaderTableView, String> lname, TableColumn<SearchReaderTableView, String> email,
                      TableColumn<SearchReaderTableView, String> phone, TableColumn<SearchReaderTableView, String> date);
    List<User> searchReader(TextField id, TextField fname, TextField lname, TextField email, TextField phone,
                            DatePicker from, DatePicker to, PrivilegedUserService pu);

    boolean scrapBook(PrivilegedUserService pu, TextField bookId);
    List<RentBook> findLentBooks(PrivilegedUserService pu, TextField rid, TextField fname, TextField lname, TextField inv, TextField title);
    void displayLentBooks(List<RentBook> books, TableView<ReturnBookTableView> tableView, ObservableList<ReturnBookTableView> observableList,
                          TableColumn<ReturnBookTableView, String> lid, TableColumn<ReturnBookTableView, String> rid,
                          TableColumn<ReturnBookTableView, String> inv, TableColumn<ReturnBookTableView, String> title,
                          TableColumn<ReturnBookTableView, String> author, TableColumn<ReturnBookTableView, String> lend,
                          TableColumn<ReturnBookTableView, String> due, TableColumn<ReturnBookTableView, String> operator);
}

package com.lms.controllers.commonComponentsLogic;

import com.lms.models.entities.Book;
import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.*;
import com.lms.services.PrivilegedUserService;
import javafx.collections.ObservableList;
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

    boolean scrapBook(PrivilegedUserService pu, TextField bookId);
    public List<User> searchUsers(TextField id, TextField fname, TextField lname, TextField email, TextField phone,
                                    DatePicker from, DatePicker to, PrivilegedUserService pu);
    List<RentBook> findLentBooks(PrivilegedUserService pu, TextField rid, TextField fname, TextField lname, TextField inv, TextField title);
    void displayLentBooks(List<RentBook> books, TableView<ReturnBookTableView> tableView, ObservableList<ReturnBookTableView> observableList,
                          TableColumn<ReturnBookTableView, String> lid, TableColumn<ReturnBookTableView, String> rid,
                          TableColumn<ReturnBookTableView, String> inv, TableColumn<ReturnBookTableView, String> title,
                          TableColumn<ReturnBookTableView, String> author, TableColumn<ReturnBookTableView, String> lend,
                          TableColumn<ReturnBookTableView, String> due, TableColumn<ReturnBookTableView, String> operator,
                          TableColumn<ReturnBookTableView, String> type);

    public void displayForms(List<FormTableView> forms, TableView tableView, ObservableList<FormTableView> observableList,
                             TableColumn<FormTableView, String> fname, TableColumn<FormTableView, String> lname,
                             TableColumn<FormTableView, String> email, TableColumn<FormTableView, String> phone,
                             TableColumn<FormTableView, String> date, TableColumn<FormTableView, String> status);

    void nullifyCreateReaderFields(TextField fname, TextField lname, TextField email, TextField pass, TextField phone);
    void nullifyLendBookUserDetails(TextField phone, TextField email, TextField name, TextField id);
    void nullifyCrapBookFields(TextField author, TextField genre, TextField inv, TextField isbn, TextField publ, TextField title, TextField year);
    void nullifyAddBookFields(ComboBox genre, ComboBox cover, TextField isbn, TextField id, TextField author, TextField issueDate, TextField publ, TextField title);

}

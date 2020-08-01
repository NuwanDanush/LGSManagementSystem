package lgsapp.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lgsapp.helpers.DbConnect;
import lgsapp.helpers.Secratary;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class HomeController implements Initializable{
    private File selectedFile =null;
    private FileInputStream fis;

    Connection con = DbConnect.getConnection();

    private FileChooser fileChooser;
    private Desktop desktop = Desktop.getDesktop();
    private String imageFile;

    //define row clicked variables
    String clickfname;
    String clicklname;
    String clickoffice;
    String clicksrchyr;

    Secratary secratary;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtWOP;

    @FXML
    private ComboBox cmbGender;

    @FXML
    private ComboBox cmbCuryr;

    @FXML
    private ComboBox cmbOffice;

    @FXML
    private DatePicker txtBirthday;

    @FXML
    private DatePicker txtFirstAppdate;

    @FXML
    private DatePicker txtUpgrading;

    @FXML
    private DatePicker txtRetirement;

    @FXML
    private DatePicker txtIncrement;

    @FXML
    private CheckBox chkPb;

    @FXML
    private CheckBox chkPm;

    @FXML
    private CheckBox chkPe;

    @FXML
    private RadioButton radioyes;

    @FXML
    private RadioButton radiono;

    @FXML
    private TableView<Secratary> tblData;

    @FXML
    private TableColumn<Secratary, String> colfname;

    @FXML
    private TableColumn<Secratary, String> collname;

    @FXML
    private TableColumn<Secratary, String> colwop;

    @FXML
    private TableColumn<Secratary, String> coloffice;

    @FXML
    private TableColumn<Secratary, String> colcontact;

    @FXML
    private TableColumn<Secratary, String> colbday;

    @FXML
    private TableColumn<Secratary, String> colfappdate;

    @FXML
    private TableColumn<Secratary, String> colupgdate;

    @FXML
    private TableColumn<Secratary, String> colretdate;

    @FXML
    private TableColumn<Secratary, String> colincdate;

    @FXML
    private TableColumn<Secratary, String> colinc;

    @FXML
    private TableColumn<Secratary, String> colbeg;

    @FXML
    private TableColumn<Secratary, String> colmid;

    @FXML
    private TableColumn<Secratary, String> colend;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtsearchyr;



    ObservableList<Secratary> oblist = FXCollections.observableArrayList(); //get data from model




    @FXML
    private ImageView img_frame;



}
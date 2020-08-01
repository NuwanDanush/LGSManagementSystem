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

public class HomeController implements Initializable {
    private File selectedFile = null;
    private FileInputStream fis;

    Connection con = DbConnect.getConnection();

    private FileChooser fileChooser;
    private Desktop desktop = Desktop.getDesktop();
    private String imageFile;

    // define row clicked variables
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

    ObservableList<Secratary> oblist = FXCollections.observableArrayList(); // get data from model

    @FXML
    private ImageView img_frame;

    @FXML
    void uploadPic(MouseEvent event) throws IOException { // using file chooser

        handle_load();

    }

    @FXML
    public void handle_load() throws MalformedURLException { // to select image on hdd and set it to the image view

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif")); // limit
                                                                                                             // fileChooser
                                                                                                             // options
                                                                                                             // to image
                                                                                                             // files
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();
            System.out.println("the path is>>>>>>>" + imageFile + ">>>>>>>");

            Image image = new Image(imageFile);
            img_frame.setImage(image);
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select valid image File");
            alert.showAndWait();
        }

    }

    @FXML
    void addInfo(MouseEvent event) throws IOException { // add secretary infos
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String wop = txtWOP.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem().toString();
        String office = cmbOffice.getSelectionModel().getSelectedItem().toString();
        String curyr = cmbCuryr.getSelectionModel().getSelectedItem().toString();
        String bday = txtBirthday.getValue().toString();
        String fappdate = txtFirstAppdate.getValue().toString();
        String upgrade = txtUpgrading.getValue().toString();
        String retdate = txtRetirement.getValue().toString();
        String incdate = txtIncrement.getValue().toString();
        String chpb = getchkboxData(chkPb);
        String chpm = getchkboxData(chkPm);
        String chpe = getchkboxData(chkPe);
        String incremantal = getIncremantal();

        if (selectedFile == null) {
            fis = null;
        } else {
            fis = new FileInputStream(selectedFile);// get the image file

        }

        // secratary = new Secratary();

        // PreparedStatement ps =
        // secratary.addsecretary(firstname,lastname,wop,office,contact,email,gender,bday,fappdate,upgrade,retdate,incdate,incremantal,chpb,chpm,chpe,fis,curyr);
        Connection con = DbConnect.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO `secrataries`( `fname`, `lname`, `wop`, `office`, `contact`, `email`, `gender`, `bday`, `fappdate`, `upgdate`, `retdate`, `incdate`, `salinc`, `yrbeg`, `yrmid`, `yrend`, `imageid`, `curyr`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, wop);
            ps.setString(4, office);
            ps.setString(5, contact);
            ps.setString(6, email);
            ps.setString(7, gender);
            ps.setString(8, bday);
            ps.setString(9, fappdate);
            ps.setString(10, upgrade);
            ps.setString(11, retdate);
            ps.setString(12, incdate);
            ps.setString(13, incremantal);
            ps.setString(14, chpb);
            ps.setString(15, chpm);
            ps.setString(16, chpe);
            ps.setBinaryStream(17, (InputStream) fis, (int) selectedFile.length());
            ps.setString(18, curyr);

            if (ps.executeUpdate() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successfully Saved");
                String s = "Successfully Registered.";
                alert.setContentText(s);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getchkboxData(CheckBox checkBox){  ///to get the data from check box
        String value = "";
        if (checkBox.isSelected()){
            value = "yes";
        }
        else{
            value = "no";
        }
        return value;
    }

    public String getIncremantal(){  //to get the data from radio btn
        String value = "";
        if (radioyes.isSelected()){
            value = "yes";
        }
        else{
            value = "no";
        }
        return value;
    }



    public boolean isValidEmailAddress(String email) {  //for email validation
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }








    //to fill the table view
    public void refreshtable(String year){
        String query = "SELECT  `fname`, `lname`, `wop`, `office`, `contact`, `bday`, `fappdate`, `upgdate`, `retdate`, `incdate`, `salinc`, `yrbeg`, `yrmid`, `yrend` FROM `secratary` WHERE `curyr` =?";
        try {
            PreparedStatement  ps = con.prepareStatement(query);
            ps.setString(1,year);
            ResultSet rs = ps.executeQuery();


            while (rs.next()){
                oblist.add(new Secratary(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),rs.getString(13),rs.getString(14)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colfname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        collname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colwop.setCellValueFactory(new PropertyValueFactory<>("wop"));
        coloffice.setCellValueFactory(new PropertyValueFactory<>("office"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colbday.setCellValueFactory(new PropertyValueFactory<>("bday"));
        colfappdate.setCellValueFactory(new PropertyValueFactory<>("fappdate"));
        colupgdate.setCellValueFactory(new PropertyValueFactory<>("upgradedate"));
        colretdate.setCellValueFactory(new PropertyValueFactory<>("retdate"));
        colincdate.setCellValueFactory(new PropertyValueFactory<>("incdate"));
        colinc.setCellValueFactory(new PropertyValueFactory<>("inc"));
        colbeg.setCellValueFactory(new PropertyValueFactory<>("beg"));
        colmid.setCellValueFactory(new PropertyValueFactory<>("mid"));
        colend.setCellValueFactory(new PropertyValueFactory<>("end"));

        tblData.setItems(oblist); //set the oblist to table view




    }




}
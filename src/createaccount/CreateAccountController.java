package createaccount;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.FileChooser;
import login.BankMain;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    private FileChooser fileChooser = new FileChooser();
    private File file;
    private FileInputStream fis;

    public ImageView pic;
    @FXML
    public TextField name;
    @FXML
    public TextField idcardno;
    @FXML
    public TextField mobileno;
    @FXML
    public TextField city;
    @FXML
    public TextField address;
    @FXML
    public TextField accountno;
    @FXML
    public TextField pin;
    @FXML
    public TextField balance;
    @FXML
    public TextField answer;
    @FXML
    public DatePicker dob;
    @FXML
    public ComboBox<String> gender;
    @FXML
    public ComboBox<String> maritalstatus;
    @FXML
    public ComboBox<String> religion;
    @FXML
    public ComboBox<String> accounttype;
    @FXML
    public ComboBox<String> question;

    ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Other");
    ObservableList<String> list1 = FXCollections.observableArrayList("Single","Married");
    ObservableList<String> list2 = FXCollections.observableArrayList("Islam","Christianity","Other");
    ObservableList<String> list3 = FXCollections.observableArrayList("Savings","Current");
    ObservableList<String> list4 = FXCollections.observableArrayList("What is your pet name?","What is your childhood town?","What is your nickname?");


    public void backToLogin(MouseEvent mouseEvent) throws IOException {
        BankMain.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/login/LoginScreen.fxml")));
    }

    public void closeApp(MouseEvent mouseEvent){
        Platform.exit();
        System.exit(0);
    }

    public void setUpPic(MouseEvent mouseEvent){
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files",".png",".jpg"));
        file = fileChooser.showOpenDialog(null);
        if(file!=null){
            Image img = new Image(file.toURI().toString(),150,150,true,true);
            pic.setImage(img);
            pic.setPreserveRatio(true);
        }
    }

    public void newAccount(MouseEvent mouseEvent){
        Connection con;
        PreparedStatement ps;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
            String sql = "INSERT INTO userdata (Name, ICN, MobileNo, Gender, Religion, MaritalStatus, DOB, City, Address, " +
                    "AccountNo, PIN, AccountType, Balance, SecurityQuestion, Answer, ProfilePic) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, name.getText());
            ps.setString(2, idcardno.getText());
            ps.setString(3, mobileno.getText());
            ps.setString(4, gender.getValue());
            ps.setString(5, religion.getValue());
            ps.setString(6, maritalstatus.getValue());
            ps.setString(7, dob.getEditor().getText());
            ps.setString(8, city.getText());
            ps.setString(9, address.getText());
            ps.setString(10, accountno.getText());
            ps.setString(11, pin.getText());
            ps.setString(12, accounttype.getValue());
            ps.setString(13, balance.getText());
            ps.setString(14, question.getValue());
            ps.setString(15, answer.getText());
            fis = new FileInputStream(file);
            ps.setBinaryStream(16, fis, (int)file.length());

            int i = ps.executeUpdate();
            Alert a;
            if(i>0){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Account Created");
                a.setHeaderText("Account Created Successfully");
                a.setContentText("Your account has been created successfully. You can now login with your account number and pin.");
            }else{
                a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Account Not Created");
                a.setContentText("Your account is not created. There is error. TRY AGAIN!!");
            }
            a.showAndWait();

        }catch(Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error in creating account");
            a.setContentText("Your account is not created. There is some technical issues. " + e.getMessage());
            a.showAndWait();
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.setItems(list);
        maritalstatus.setItems(list1);
        religion.setItems(list2);
        accounttype.setItems(list3);
        question.setItems(list4);
    }
}

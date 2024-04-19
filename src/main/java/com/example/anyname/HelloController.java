package com.example.anyname;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    public TextField tfUserName;
    public TextField tfUser;
    public TextField tfPass;
    public Label lblPassword;
    public PasswordField pfPassword;
    public Button btnLogin;
    public Button btnRegister;
    public Button btnSubmit;
    public Button btnLogout;
    public VBox pnLogin;
    public ColorPicker cpPicker;
    public TextField tfPassword;
    private static final String[] validUsernames = {"user1", "user2", "user3"};
    private static final String[] validPasswords = {"pass1", "pass2", "pass3"};
    @FXML
    private Label welcomeText;
    @FXML
    protected void onSubmitClick() throws IOException{

        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement("INSERT INTO users (name,email) VALUES (?,?)")){
            String user = tfUser.getText();
            String pass = tfPass.getText();
            statement.setString(1,user);
            statement.setString(2,pass);
            int rows = statement.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onRegisterClickVisit() throws IOException{
        VBox parent = pnLogin;
        Parent scene = FXMLLoader.load(getClass().getResource("register.fxml"));
        parent.getChildren().clear();
        parent.getChildren().add(scene);
    }
    @FXML
    protected void onLoginClick() throws IOException {
        String username = tfUserName.getText();
        String password = tfPassword.getText();

        boolean isValidCredentials = false;
        for (int i = 0; i < validUsernames.length; i++) {
            if (username.equals(validUsernames[i]) && password.equals(validPasswords[i])) {
                isValidCredentials = true;
                break;
            }
        }

        if (isValidCredentials) {
            AnchorPane p = (AnchorPane) pnLogin.getParent();
            p.getScene().getStylesheets().clear();
            p.getScene().getStylesheets().add(getClass().getResource("user1.css").toExternalForm());

            Parent scene = FXMLLoader.load(getClass().getResource("home-view.fxml"));
            scene.prefHeight(p.getScene().getHeight());
            scene.prefWidth(p.getScene().getWidth());
            p.getChildren().clear();
            p.getChildren().add(scene);
        }
    }

    @FXML
    protected void onLogoutClick() throws IOException{
        Color color = cpPicker.getValue();
//        System.out.println(cpPicker.getValue());
        String cssColor = "rgb(" + (int)(color.getRed()*255)+","+
                                     (int)(color.getGreen()*255)+","+
                (int)(color.getBlue()*255)+
                ")";

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(getClass().getResource("user1.css").getPath(),true));

            bw.write(".button{ -fx-background-color: " + cssColor + "; }");
            bw.newLine();
            bw.close();
        }catch(IOException e){
        }
        AnchorPane p = (AnchorPane) btnLogout.getParent();
        Parent scene = new FXMLLoader().load(getClass().getResource("login-view.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }
}
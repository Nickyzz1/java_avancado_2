package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountContoller {

    public CreateAccountContoller(){

    }

    @FXML
    protected PasswordField passUser;
    @FXML
    protected TextField emailUser;
    @FXML
    protected Button criandoConta; 

    @FXML
    protected void methodCreateAccount(ActionEvent e) throws Exception 
    {
        User user = new User();
        user.setName(emailUser.getText());
        user.setPassword(passUser.getText());
    
        Context ctx = new Context();
        ctx.begin();
        ctx.save(user);
        ctx.commit();
        
        URL newSceneUrl = getClass().getResource("login-scene.fxml");
        Parent newRoot = FXMLLoader.load(newSceneUrl);
        Scene newScene = new Scene(newRoot);
        Stage currentStage = (Stage) criandoConta.getScene().getWindow();
        currentStage.setScene(newScene);
        currentStage.show();

    }
    
}

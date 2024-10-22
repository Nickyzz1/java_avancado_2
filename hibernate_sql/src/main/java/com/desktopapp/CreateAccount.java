package com.desktopapp;

import java.awt.TextField;
import java.net.URL;

import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;

public class CreateAccount {

    public CreateAccount(){

    }

    @FXML
    protected PasswordField userPass;
    @FXML
    protected TextField userName;

    public CreateAccount(TextField userName, PasswordField userPass){
        this.userName = userName;
        this.userPass = userPass;
    }

    @FXML
    protected void methodCreateAccount()
    {
        User user = new User();
        user.setName(userName.toString());
        user.setPassword(userPass.toString());
    
        Context ctx = new Context();
        ctx.begin();
        ctx.save(user);
        ctx.commit();
        
        URL newURL = getClass().getResource("login-scene.fxml");
        Scene newScene = new Scene(newRoot);

    }
    
}

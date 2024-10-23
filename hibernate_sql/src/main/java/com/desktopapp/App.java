package com.desktopapp;

import com.desktopapp.model.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) {

        // cria um user
        User user = new User();
        user.setName("");
        user.setPassword("");

        Context ctx = new Context();
        ctx.begin();
        ctx.save(user);
        ctx.commit();

        // Cart cart = new Cart();
        // cart.setName("ferrari");
        // cart.setValue(10.0);

        // ctx.begin();
        // ctx.save();
        // ctx.commit();

        launch(args);
    }
    @Override
    public void start(Stage arg0) throws Exception
    {
        Scene scene = LoginSceneController.CreateScene();
        arg0.setScene(scene);
        arg0.show();

    }
}
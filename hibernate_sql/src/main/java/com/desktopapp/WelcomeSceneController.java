package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class WelcomeSceneController {

  User loggedUser;
  public User getLoggedUser() {
      return loggedUser;
  }
  public void setLoggedUser(User loggedUser) {
      this.loggedUser = loggedUser;
  }
  
  public static Scene CreateScene(User user) throws Exception {
      URL sceneUrl = WelcomeSceneController.class
              .getResource("bemVindo.fxml");
      FXMLLoader loader = new FXMLLoader(sceneUrl);
      loader.load();
      
      var controller = (WelcomeSceneController)loader.getController();
      controller.setLoggedUser(user);
      
      Parent root = loader.getRoot();
      Scene scene = new Scene(root);
      return scene;
  }
}

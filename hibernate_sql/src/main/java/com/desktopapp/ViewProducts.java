package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewProducts {

    User loggedUser;
    MyCart cart = new MyCart();

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public ViewProducts() { }

    @FXML
    protected Button inquisicao;
    @FXML
    protected Button subsolo;
    @FXML
    protected Button inquisicao2;
    @FXML
    protected Button myCart;

    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = ViewProducts.class.getResource("bemVindo.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
    
        ViewProducts controller = loader.getController(); 
        controller.setLoggedUser(user);
    
        return new Scene(root);
    }

    @FXML
    public void addProduct(ActionEvent e) throws Exception {

        Button sourceButton = (Button) e.getSource();
        String name = sourceButton.getId();
        Double price = 10.0; 
        Products product = new Products(name, price);
        cart.addProduct(product);

        // Cart cart = new Cart();
        // cart.setNameProd(name);
        // cart.setValueProd(value);

        // Context ctx = new Context();
        // ctx.begin();
        // ctx.save(cart);
        // ctx.commit();

    }

    @FXML
    protected void changeToCart(ActionEvent e) throws Exception {
        
        ViewCart view = new ViewCart(cart);
        var scene = ViewCart.CreateScene(loggedUser, cart);
        Stage currentStage = (Stage) myCart.getScene().getWindow();
        currentStage.setScene(scene);
    }

    // botao sair 
}

package com.desktopapp;

import java.net.URL;
import java.util.List;

import com.desktopapp.model.Cart;
import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ViewCart {

    @FXML
    private VBox productsVBox; 

    public static Scene CreateScene(User user, List<Cart> cartItems) throws Exception {
        URL sceneUrl = ViewCart.class.getResource("cart.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();

        ViewCart controller = loader.getController();
        controller.displayCartItems(cartItems); 

        return new Scene(root);
    }


    public void displayCartItems(List<Cart> cartItems) {
        productsVBox.getChildren().clear(); 

        if (cartItems != null && !cartItems.isEmpty()) {
            for (Cart item : cartItems) {
                // Cria um Label para cada produto e adiciona à VBox
                Label label = new Label(item.getName() + " - " + item.getValue() + " R$");
                productsVBox.getChildren().add(label);
            }
        } else {
           
            Label label = new Label("Seu carrinho está vazio.");
            productsVBox.getChildren().add(label);
        }
    }
}

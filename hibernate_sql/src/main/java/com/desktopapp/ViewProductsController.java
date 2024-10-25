package com.desktopapp;

import java.io.IOException;
import java.net.URL;

import com.desktopapp.model.Cart;
import com.desktopapp.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewProductsController {

    User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public ViewProductsController() { }

    @FXML
    protected Button inquisicao;
    @FXML
    protected Button subsolo;
    @FXML
    protected Button inquisicao2;
    @FXML
    protected Button myCart;
    @FXML
    protected Button seeAll;

    public static Scene CreateScene(User user) throws Exception {

        try{

            URL sceneUrl = ViewProductsController.class.getResource("bemVindo.fxml");
            FXMLLoader loader = new FXMLLoader(sceneUrl);
            Parent root = loader.load();
        
            ViewProductsController controller = loader.getController(); 
            controller.setLoggedUser(user);
        
            return new Scene(root);

        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    @FXML
    public void addProduct(ActionEvent e) throws Exception {

        Button sourceButton = (Button) e.getSource();
        String name = sourceButton.getId();
        Double price = 10.0; 

        // Cart product = new Cart();
        // product.setNameProd(name);
        // product.setValueProd(price);
        // cart.addProduct(product);

        Cart cart = new Cart();
        cart.setNameProd(name);
        cart.setValueProd(price);

        Context ctx = new Context();
        ctx.begin();
        ctx.save(cart);
        ctx.commit();

    }

    @FXML
    protected void changeToCart(ActionEvent e) throws Exception {
        
        var scene = ViewCartController.CreateScene(loggedUser);
        Stage currentStage = (Stage) myCart.getScene().getWindow();
        currentStage.setScene(scene);
      
    }

    @FXML
    protected void changeToEdit(ActionEvent e) throws Exception {
        
        var scene = OperProductController.CreateScene(loggedUser);
        Stage currentStage = (Stage) seeAll.getScene().getWindow();
        currentStage.setScene(scene);
      
    }

    // botao sair 
}

package com.desktopapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.desktopapp.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewCart {

    MyCart cart;

    public ViewCart(MyCart cart) {
        this.cart = cart;
    }

    @FXML 
    private TableView<Products> tableView; 

    @FXML
    private TableColumn<Products, Long> idColumn; 

    @FXML
    private TableColumn<Products, String> nameColumn; 

    @FXML
    private TableColumn<Products, Double> priceColumn; 

    public static Scene CreateScene(User user, MyCart cart) throws Exception {
        URL sceneUrl = ViewCart.class.getResource("cart.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        loader.setController(new ViewCart(cart)); 
        Parent root = loader.load();

        return new Scene(root);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idCart"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameProd"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("valueProd")); 

        tableView.setItems(cartList());
    }

    private ObservableList<Products> cartList() {
        ObservableList<Products> observableList = FXCollections.observableArrayList();

        
        ArrayList<Products> products = cart.getProducts(); 
        observableList.addAll(products); 

        return observableList;
    }

    public void displayCartItems(List<Products> cartItems) { 
        tableView.getItems().clear();
    
        if (cartItems != null && !cartItems.isEmpty()) {
            ObservableList<Products> observableList = FXCollections.observableArrayList(cartItems);
            tableView.setItems(observableList);
        } else {
            System.out.println("Seu carrinho está vazio.");
        }
    }

    public void setCart(MyCart cart) {
        this.cart = cart;
        tableView.setItems(cartList()); 
    }
    
}

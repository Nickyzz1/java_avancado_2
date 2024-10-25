package com.desktopapp;

import java.net.URL;
import java.util.ResourceBundle;

import com.desktopapp.model.User;
import com.desktopapp.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OperProduct implements Initializable {

    private User loggedUser;

    @FXML
    protected Button voltarHome;

    @FXML 
    private TableView<Product> tableView; 

    @FXML
    private TableColumn<Product, ?> idColumn; 

    @FXML
    private TableColumn<Product, ?> nameColumn; 

    @FXML
    private TableColumn<Product, ?> priceColumn; 

    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = ViewCart.class.getResource("adicionarEditarExcluirProdutos.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        
        ViewCart controller = loader.getController(); 
        controller.setLoggedUser(user); 
    
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceProduto"));
    
        Context ctx = new Context();
        ObservableList<Product> lista = produtos();
        if (lista != null && !lista.isEmpty()) {
            this.tableView.setItems(lista);
        } else {
            System.out.println("Seu carrinho está vazio.");
        }
    }

    public ObservableList<Product> produtos() {
        Context ctx = new Context();
        int cont = 1;
        ObservableList<Product> Lista2 = FXCollections.observableArrayList();
        while (true) {
            Product produto = ctx.find(Product.class, (Object)cont);
            if (produto == null) {
                break;
            }
            Lista2.add(produto);
            cont ++;
        }
        return Lista2;
    }

    
    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

   
    @FXML
    protected void goBackHome()
    {
        try {
            var scene = ViewProducts.CreateScene(getLoggedUser());
            Stage currentStage = (Stage) voltarHome.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    protected void addProduct()
    {

    }
    @FXML
    protected Product getProductFromCell()
    {
        idColumn.getCellValueFactory();
        return null;
    }

    
    
}

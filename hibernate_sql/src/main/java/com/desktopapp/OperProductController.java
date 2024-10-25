package com.desktopapp;

import java.net.URL;
import java.util.ResourceBundle;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

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

public class OperProductController implements Initializable {

    private User loggedUser;

    @FXML
    protected Button voltar;

    @FXML 
    private TableView<Product> tableViewAllProducts; 

    @FXML
    private TableColumn<Product, ?> idColumnProducts; 

    @FXML
    private TableColumn<Product, ?> nameColumnProducts; 

    @FXML
    private TableColumn<Product, ?> priceColumnProducts; 

    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = OperProductController.class.getResource("adicionarEditarExcluirProdutos.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        
        OperProductController controller = loader.getController(); 
        controller.setLoggedUser(user); 
    
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumnProducts.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        nameColumnProducts.setCellValueFactory(new PropertyValueFactory<>("nameProd"));
        priceColumnProducts.setCellValueFactory(new PropertyValueFactory<>("priceProd"));
    
        Context ctx = new Context();
        ObservableList<Product> lista = produtos();
        if (lista != null && !lista.isEmpty()) {
            this.tableViewAllProducts.setItems(lista);
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
            var scene = ViewProductsController.CreateScene(getLoggedUser());
            Stage currentStage = (Stage) voltar.getScene().getWindow();
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
        idColumnProducts.getCellValueFactory();
        return null;
    }

    
    
}

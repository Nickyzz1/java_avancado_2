package com.desktopapp;

import java.io.IOException;
import java.net.URL;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.desktopapp.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditProductController {

    private Product product;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    // Método para configurar o produto a ser editado
    public void setProduct(Product product) {
        this.product = product;
        // Preenche os campos de texto com os dados do produto
        if (product != null) {
            nameField.setText(product.getNameProd());
            priceField.setText(String.valueOf(product.getPriceProd()));
        }
    }

    // Método para salvar as alterações (adicione este método se ainda não tiver)
    @FXML
    private void saveProduct() {
        if (product != null) {
            product.setNameProd(nameField.getText());
            product.setPriceProd(Double.parseDouble(priceField.getText()));
            // Aqui você deve adicionar o código para atualizar o produto no banco de dados
            // Por exemplo: ctx.update(product);
        }
        // Fechar a janela após salvar
        ((Stage) nameField.getScene().getWindow()).close();
    }
    

    @FXML
    private void initialize() {
        // Ação para o botão "Editar"
        editButton.setOnAction(event -> saveProduct());

        // Ação para o botão "Cancelar"
        cancelButton.setOnAction(event -> closeWindow());

    }


    @FXML
    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}

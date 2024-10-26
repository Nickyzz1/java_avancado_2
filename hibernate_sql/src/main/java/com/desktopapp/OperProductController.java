package com.desktopapp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.DoubleFunction;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

import jakarta.persistence.TypedQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class OperProductController implements Initializable {

    private User loggedUser;

    @FXML
    protected Button voltar;

    @FXML 
    private TableView<Product> tableViewAllProducts; 

    @FXML
    private TableColumn<Product, Long> idColumnProducts; 

    @FXML
    private TableColumn<Product, String> nameColumnProducts; 

    @FXML
    private TableColumn<Product, Double> priceColumnProducts; 
    
    @FXML
    private TableColumn<Product, Void> editar; 

    @FXML
    private TableColumn<Product, Void> excluir; 

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField priceProduto;

    
    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = OperProductController.class.getResource("adicionarEditarExcluirProdutos.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        
        OperProductController controller = loader.getController(); 
        controller.setLoggedUser(user); 
    
        return new Scene(root);
    }

    @FXML
    protected void createProduct()
    {
        Product prod1 = new Product();
        prod1.setNameProd( nomeProduto.getText());
        prod1.setPriceProd(Double.parseDouble(priceProduto.getText()));

        Context ctx = new Context();

        ctx.begin();
        ctx.save(prod1);
        ctx.commit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumnProducts.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        nameColumnProducts.setCellValueFactory(new PropertyValueFactory<>("nameProd"));
        priceColumnProducts.setCellValueFactory(new PropertyValueFactory<>("priceProd"));

        // Configuração da coluna de editar
        editar.setCellFactory(new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @FXML
            public TableCell<Product, Void> call(TableColumn<Product, Void> param) {
                return new TableCell<Product, Void>() {
                    private final Button editButton = new Button("Editar");
    
                    {
                        // Configurar o evento de clique para abrir a nova tela de edição
                        editButton.setOnAction(event -> {
                            Product selectedProduct = getTableView().getItems().get(getIndex());
                            openEditProductScreen(selectedProduct);
                        });
                    }
    
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : editButton);
                    }
                };
            }
        });
    
        // Adicionar a coluna editar à tabela, se ainda não estiver adicionada
        if (!tableViewAllProducts.getColumns().contains(editar)) {
            tableViewAllProducts.getColumns().add(editar);
        }

        // Configuração da coluna de excluir
        excluir.setCellFactory(new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(TableColumn<Product, Void> param) {
                return new TableCell<Product, Void>() {
                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction(event -> {
                            Product product = getTableView().getItems().get(getIndex());
                            deleteProduct(product);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : btn);
                    }

                };
            }
        });

        // Adicionando as colunas de editar e excluir à tabela
        tableViewAllProducts.getColumns().addAll(editar, excluir);

        // Carregar produtos na tabela
        ObservableList<Product> lista = produtos();
        if (lista != null && !lista.isEmpty()) {
            this.tableViewAllProducts.setItems(lista);
        } else {
            System.out.println("Seu carrinho está vazio.");
        }
    }

    public ObservableList<Product> produtos() {
        Context ctx = new Context();
        ObservableList<Product> lista = FXCollections.observableArrayList();
        try {
            String jpql = "SELECT p FROM Product p";
            TypedQuery<Product> query = ctx.createQuery(Product.class, jpql);
            List<Product> produtosList = query.getResultList();
            lista.addAll(produtosList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @FXML
    protected void goBackHome() {
        try {
            var scene = ViewProductsController.CreateScene(getLoggedUser());
            Stage currentStage = (Stage) voltar.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // @FXML
    private void openEditProductScreen(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarProduto.fxml"));
            Parent editRoot = loader.load();
    
            EditProductController editController = loader.getController();
            editController.setProduct(product);
    
            Stage editStage = new Stage();
            editStage.setScene(new Scene(editRoot));
            editStage.setTitle("Editar Produto");
            editStage.show();
            
            // Atualizar a tabela após fechar a janela de edição
            editStage.setOnHiding(event -> tableViewAllProducts.refresh());
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(Product product) {
        Context ctx = new Context();
        ctx.begin();
        try {
            ctx.remove(product); // Chama o método para remover o produto
            ctx.commit();        // Confirma a remoção no banco de dados
            tableViewAllProducts.getItems().remove(product); // Atualiza a tabela
        } catch (Exception e) {
            e.printStackTrace();
            if (ctx != null) {
                ctx.commit(); // Rollback em caso de erro
            }
        }
    }
    
}

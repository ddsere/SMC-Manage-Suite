package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PlaceOrderFormController {

    @FXML
    private JFXComboBox<?> cmbItemId;

    @FXML
    private JFXComboBox<?> cmbSId;

    @FXML
    private TableColumn<?, ?> colItemCartAction;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSCartAction;

    @FXML
    private TableColumn<?, ?> colSId;

    @FXML
    private TableColumn<?, ?> colSName;

    @FXML
    private TableColumn<?, ?> colSPrice;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCusName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemQtyOnHand;

    @FXML
    private Label lblItemUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblSName;

    @FXML
    private Label lblSPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblCartItem;

    @FXML
    private TableView<?> tblCartService;

    @FXML
    private TextField txtCusPhone;

    @FXML
    private TextField txtQtyBuy;

    @FXML
    void btnAddItemToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddServiceToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}

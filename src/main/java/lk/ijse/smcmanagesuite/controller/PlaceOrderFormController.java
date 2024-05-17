package lk.ijse.smcmanagesuite.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.smcmanagesuite.model.tm.ItemCartTm;
import lk.ijse.smcmanagesuite.model.tm.ServiceCartTm;
import lk.ijse.smcmanagesuite.repository.OrderRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class PlaceOrderFormController {

    @FXML
    private JFXComboBox<String> cmbItemId;

    @FXML
    private JFXComboBox<String> cmbSId;

    @FXML
    private TableColumn<?, ?> colItemCartAction;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colItemTotal;

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
    private TableView<ItemCartTm> tblCartItem;

    @FXML
    private TableView<?> tblCartService;

    @FXML
    private TextField txtCusPhone;

    @FXML
    private TextField txtQtyBuy;

    private ObservableList<ItemCartTm> itemCartList = FXCollections.observableArrayList();
    private ObservableList<ServiceCartTm> serviceCartList = FXCollections.observableArrayList();
    private double netTotal = 0;

    public void initialize() {
        setCellValueFactory();
        loadNextOrderId();
        setDate();
        getServiceId();
    }

    private void getServiceId() {

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    private void loadNextOrderId() {
        try {
            String currentId = OrderRepo.currentId();
            String nextId = nextId(currentId);

            lblOrderID.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("D");
            int id = Integer.parseInt(split[1]);
            id++;

            // Format the ID with leading zeros using String.format
            return "D" + String.format("%03d", id);
        } else {
            return "D001";
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemCartAction.setCellValueFactory(new PropertyValueFactory<>("btnItemCartRemove"));

        colSId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("sDesc"));
        colSPrice.setCellValueFactory(new PropertyValueFactory<>("sPrice"));
        colSCartAction.setCellValueFactory(new PropertyValueFactory<>("btnServiceCartRemove"));
    }

    @FXML
    void btnAddItemToCartOnAction(ActionEvent event) {
        String itemId = cmbItemId.getValue();
        String itemDesc = lblItemName.getText();
        Double itemPrice = Double.valueOf(lblItemUnitPrice.getText());
        int itemQty = Integer.parseInt(txtQtyBuy.getText());
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        double total = itemQty * itemPrice;

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblCartItem.getSelectionModel().getSelectedIndex();
                itemCartList.remove(selectedIndex);

                tblCartItem.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblCartItem.getItems().size(); i++) {
            if (itemId.equals(colItemId.getCellData(i))) {
                itemQty += itemCartList.get(i).getItemQty();
                total = itemPrice * itemQty;

                itemCartList.get(i).setItemQty(itemQty);
                itemCartList.get(i).setItemTotal(total);

                tblCartItem.refresh();
                calculateNetTotal();
                txtQtyBuy.setText("");
                return;
            }
        }

        ItemCartTm itemCartTm = new ItemCartTm(itemId, itemDesc, itemPrice, itemQty, total, btnRemove);

        itemCartList.add(itemCartTm);

        tblCartItem.setItems(itemCartList);
        txtQtyBuy.setText("");
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        netTotal = 0;
        for (int i = 0; i < tblCartItem.getItems().size(); i++) {
            netTotal += (double) colItemTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
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

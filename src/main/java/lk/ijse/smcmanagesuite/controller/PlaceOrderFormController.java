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
import lk.ijse.smcmanagesuite.model.*;
import lk.ijse.smcmanagesuite.model.tm.ItemCartTm;
import lk.ijse.smcmanagesuite.model.tm.ServiceCartTm;
import lk.ijse.smcmanagesuite.repository.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private TableView<ServiceCartTm> tblCartService;

    @FXML
    private TextField txtCusPhone;

    @FXML
    private TextField txtQtyBuy;

    private ObservableList<ItemCartTm> itemCartList = FXCollections.observableArrayList();
    private ObservableList<ServiceCartTm> serviceCartList = FXCollections.observableArrayList();
    private double netTotal = 0;
    private String cusPhone ;

    public void initialize() {
        setCellValueFactory();
        loadNextOrderId();
        setDate();
        getServiceId();
        getItemId();
    }

    private void getItemId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ItemRepo.getCodes();
            for (String id : idList) {
                obList.add(id);
            }

            cmbItemId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getServiceId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ServiceRepo.getIds();
            for (String id : idList) {
                obList.add(id);
            }

            cmbSId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemDesc"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("itemTotal"));
        colItemCartAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        colSId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("sDesc"));
        colSPrice.setCellValueFactory(new PropertyValueFactory<>("sPrice"));
        colSCartAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
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

        double netItemTotal = 0;
        double netServiceTotal = 0;

        for (int i = 0; i < tblCartItem.getItems().size(); i++) {
            netItemTotal += (double) colItemTotal.getCellData(i);

        }
        for (int i = 0;i<tblCartService.getItems().size();i++){
            netServiceTotal +=(double) colSPrice.getCellData(i);
        }
        netTotal = netItemTotal + netServiceTotal;
        lblNetTotal.setText(String.valueOf(netTotal));
    }


    @FXML
    void btnAddServiceToCartOnAction(ActionEvent event) {
        String code = cmbSId.getValue();
        String description = lblSName.getText();
        double servicePrice = Double.parseDouble(lblSPrice.getText());
        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblCartService.getSelectionModel().getSelectedIndex();
                serviceCartList.remove(selectedIndex);

                tblCartService.refresh();
                calculateNetTotal();
            }
        });

        ServiceCartTm sCartList = new ServiceCartTm(code, description, servicePrice,btnRemove);

        serviceCartList.add(sCartList);

        tblCartService.setItems(serviceCartList);
        lblSPrice.setText("");
        lblSName.setText("");
        txtCusPhone.setText("");
        calculateNetTotal();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderID.getText();
        String cusName = lblCusName.getText();
        Date date = Date.valueOf(LocalDate.now());
        double total = Double.parseDouble(lblNetTotal.getText());


        var order = new Order(orderId,date,total, cusPhone, cusName);

        System.out.println(order.toString());

        List<ItemQty> itemQties = new ArrayList<>();
        for (int i = 0; i < tblCartItem.getItems().size(); i++) {
            ItemCartTm tm = itemCartList.get(i);

            ItemQty od = new ItemQty(
                    tm.getItemQty(),
                    tm.getItemId()
            );
            itemQties.add(od);
        }

        List<ServiceIds> serviceIds = new ArrayList<>();
        for (int i = 0; i < tblCartService.getItems().size(); i++) {
            ServiceCartTm tm = serviceCartList.get(i);

            ServiceIds si = new ServiceIds(
                    tm.getSId()
            );
            serviceIds.add(si);
        }

        PlaceOrder po = new PlaceOrder(order, itemQties ,serviceIds);
        try {
            boolean isPlaced = PlaceOrderRepo.placeOrder(po);
            System.out.println(isPlaced);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "order placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "order not placed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
        String empId = cmbItemId.getValue();

        try {
            Item item = ItemRepo.searchById(empId);
            if (item != null) {
                lblItemName.setText(item.getDescription());
                lblItemQtyOnHand.setText(item.getQty());
                lblItemUnitPrice.setText(String.valueOf(item.getPrice()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbSIdOnAction(ActionEvent event) {
        String serviceId = cmbSId.getValue();

        try {
            Service service = ServiceRepo.searchById(serviceId);
            if (service != null) {
                lblSName.setText(service.getDescription());
                lblSPrice.setText(service.getPrice());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        cusPhone = txtCusPhone.getText();

        try {
            Customer customer = CustomerRepo.searchById(cusPhone);
            if (customer != null) {
                lblCusName.setText(customer.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

package lk.ijse.smcmanagesuite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.smcmanagesuite.model.Order;
import lk.ijse.smcmanagesuite.model.tm.OrderTm;
import lk.ijse.smcmanagesuite.repository.OrderRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCusName;

    @FXML
    private TableColumn<?, ?> colCusPhone;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<OrderTm> tblOrder;

    private List<Order> orderList = new ArrayList<>();

    public void initialize() {
        this.orderList = getAllOrders();
        setCellValueFactory();
        loadOrderTable();
    }

    private List<Order> getAllOrders() {
        List<Order> orderList = null;
        try {
            orderList = OrderRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colCusPhone.setCellValueFactory(new PropertyValueFactory<>("cusPhone"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void loadOrderTable() {
        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();

        for (Order order : orderList) {
            OrderTm orderTm = new OrderTm(
                    order.getOrderId(),
                    order.getDate(),
                    order.getCusName(),
                    order.getCusPhone(),
                    order.getAmount()
            );

            tmList.add(orderTm);
        }
        tblOrder.setItems(tmList);
    }

}

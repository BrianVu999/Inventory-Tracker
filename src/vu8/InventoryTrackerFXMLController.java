/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vu8;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nam Vu
 */
public class InventoryTrackerFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private InventoryList list;
    private ObservableList<Inventory> obsInventoryList;
    @FXML
    private TextField txtItemId, txtItemName, txtQOH, txtROP, txtSellPrice;
    @FXML
    private ArrayList<TextField> textField;
    @FXML
    private Button btnOrders, btnSave, btnAdd, btnExit;
    @FXML
    private Label lblMessage;
    @FXML
    private ListView lstOrderList;

    private Alert alert;

    public void addButton() {
        this.clearField();
        this.lockField(true);
        btnOrders.setDisable(true);
        btnAdd.setDisable(true);
        btnSave.setDisable(false);
        txtItemId.requestFocus();
        lblMessage.setText("");
    }

    public void saveButton() {
        lblMessage.setText("");
        try {
            this.addInventory();

            btnOrders.setDisable(false);
            btnAdd.setDisable(false);
            btnSave.setDisable(true);
            this.clearField();
            this.reset(false);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Data Entry Error");
            alert.setHeaderText("Invalid Value Entered");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void ordersButton() {
        lblMessage.setText("");
        if (list.length() == 0) {
            lblMessage.setText("No items to list. Add Some!");
        } else {
            obsInventoryList.clear();
            for (int i = 0; i < list.length(); i++) {
                if (list.get(i).getRop() >= list.get(i).getQoh()) {
                    obsInventoryList.add(list.get(i));
                }
            }
            if (obsInventoryList.isEmpty()) {
                lblMessage.setText("No items to re-order.");
            }
        }
    }

    public void exitButton() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look the Confirmation Dialog");
        alert.setContentText("Are you ok to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public void addInventory() {
        list.add(new Inventory(txtItemId.getText(), txtItemName.getText(),
                Integer.parseInt(txtQOH.getText()),
                Integer.parseInt(txtROP.getText()),
                Double.parseDouble(txtSellPrice.getText())));
    }

    public void clearField() {
        for (TextField t : textField) {
            t.setText("");
        }
    }

    public void lockField(boolean islock) {
        for (TextField t : textField) {
            t.setDisable(!islock);
        }
    }

    public void reset(boolean isReset) {
        btnAdd.setDisable(isReset);
        btnOrders.setDisable(isReset);
        btnSave.setDisable(!isReset);
        lockField(isReset);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //create observable Array List for the list view
        list = new InventoryList();
        obsInventoryList = FXCollections.observableArrayList();
        lstOrderList.setItems(obsInventoryList);

        //create array list for text field
        textField = new ArrayList<TextField>();
        textField.add(txtItemId);
        textField.add(txtItemName);
        textField.add(txtQOH);
        textField.add(txtROP);
        textField.add(txtSellPrice);

        this.lockField(false);

        //display the message for re-order point
        lstOrderList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                try {
                    Inventory i = (Inventory) lstOrderList.getSelectionModel().getSelectedItem();
                    lblMessage.setText("Re-order Point: " + i.getRop());
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException thrown!");
                }
            }
        });
    }

}

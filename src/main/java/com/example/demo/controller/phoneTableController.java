package com.example.demo.controller;

import com.example.demo.dao.PhoneDao;
import com.example.demo.dao.impl.PhoneDaoImpl;
import com.example.demo.model.Ember;
import com.example.demo.model.Phone;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class phoneTableController implements Initializable {
    PhoneDao dao = new PhoneDaoImpl();
    @FXML
    private TableView<Phone> table;
    @FXML
    private TableColumn<Phone, Integer> id;
    @FXML
    private TableColumn<Phone, String> number;
    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        button.setOnAction(event -> {
            try {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                changeViews();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeTable(){
        List<Phone> emberlist = dao.findall();
        table.getItems().setAll(emberlist);
    }

    private void changeViews() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainWindow.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("embers");
        stage.setScene(scene);
        stage.show();
    }


}

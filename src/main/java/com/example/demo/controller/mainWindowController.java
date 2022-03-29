package com.example.demo.controller;

import com.example.demo.App;
import com.example.demo.dao.EmberDao;
import com.example.demo.dao.impl.EmberDaoImpl;
import com.example.demo.model.Ember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class mainWindowController implements Initializable {
    EmberDao dao = new EmberDaoImpl();

    @FXML
    private TableView<Ember> tableView;
    @FXML
    private TableColumn<Ember, Integer> id;
    @FXML
    private TableColumn<Ember, String> nev;
    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        button.setOnAction(event -> {
            try {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                changeViews();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeTable() {
        List<Ember> emberlist = dao.findAll();
        tableView.getItems().setAll(emberlist);
    }

    private void changeViews() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/phoneTable.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("phones");
        stage.setScene(scene);
        stage.show();
    }
}
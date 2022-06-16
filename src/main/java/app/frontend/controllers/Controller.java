package app.frontend.controllers;

import app.backend.Parameter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import app.backend.models.Parameters;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    private final ObservableList<Parameter> tableData = FXCollections.observableArrayList(Parameters::getParameterList());

    @FXML
    private TableView<Parameter> table;

    @FXML
    private TableColumn<Parameter, Integer> idColumn;

    @FXML
    private TableColumn<Parameter, String> typeColumn;

    @FXML
    private TableColumn<Parameter, String> labelColumn;

    @FXML
    private TableColumn<Parameter, String> descriptionColumn;

    @FXML
    private TableColumn<Parameter, Integer> addressColumn;

    @FXML
    private TableColumn<Parameter, Integer> sizeColumn;

    @FXML
    private TableColumn<Parameter, Integer> valueColumn;

    @FXML
    private void initialize() {
        initData();
        idColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("ID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("Тип"));
        labelColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("Имя"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("Описание"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("Адрес"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("Размер"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("Значение"));
        table.setItems(tableData);
    }

    private void initData() {

    }
}

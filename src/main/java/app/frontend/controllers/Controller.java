package app.frontend.controllers;

import app.backend.models.Parameter;
import app.backend.models.ParametersWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.control.Alert.AlertType.NONE;
import static javafx.scene.control.Alert.AlertType.WARNING;

public class Controller implements Initializable {

    private List<Parameter> tableData = ParametersWrapper.getInstance().getParameterList();
    @FXML
    public Button searchButton;
    @FXML
    public TextField searchTextField;
    @FXML
    public ComboBox<String> fieldComboBox;
    @FXML
    public ComboBox<String> operationComboBox;
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
    private TableColumn<Parameter, String> addressColumn;

    @FXML
    private TableColumn<Parameter, String> sizeColumn;

    @FXML
    private TableColumn<Parameter, String> valueColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        System.out.println(addressColumn.isEditable());

        table.setItems(FXCollections.observableList(tableData));
        table.setEditable(true);
        addressColumn.setOnEditCommit(parameterStringCellEditEvent -> {
            parameterStringCellEditEvent.getRowValue().setAddress(parameterStringCellEditEvent.getNewValue());
            System.out.println(addressColumn.getCellData(parameterStringCellEditEvent.getRowValue()));
        });
        sizeColumn.setOnEditCommit(parameterStringCellEditEvent -> {
            parameterStringCellEditEvent.getRowValue().setSize(parameterStringCellEditEvent.getNewValue());
            System.out.println(sizeColumn.getCellData(parameterStringCellEditEvent.getRowValue()));
        });
        valueColumn.setOnEditCommit(parameterStringCellEditEvent -> {
            parameterStringCellEditEvent.getRowValue().setValue(parameterStringCellEditEvent.getNewValue());
            System.out.println(valueColumn.getCellData(parameterStringCellEditEvent.getRowValue()));
        });
        List<String> fields = new ArrayList<>();
        fields.add("ID");
        fields.add("Тип");
        fields.add("Имя");
        fields.add("Описание");
        fields.add("Адрес");
        fields.add("Размер");
        fields.add("Значение");
        fieldComboBox.setItems(FXCollections.observableList(fields));
        List<String> operations = new ArrayList<>();
        operations.add("==");
        operations.add(">=");
        operations.add("<=");
        operations.add("!=");
        operations.add("Содержит");
        operations.add("Не содержит");
        operationComboBox.setItems(FXCollections.observableList(operations));
    }

    public void search(ActionEvent actionEvent) {

        List<Parameter> filteredParameters = new ArrayList<>();
        Alert alert = new Alert(WARNING);

        if (searchTextField.getText().isEmpty()){
            alert.setTitle("Ошибка");
            alert.setContentText("Не указано значение поиска");
            alert.show();
            return;
        }
        switch (fieldComboBox.getValue()) {
            case "ID":
                switch (operationComboBox.getValue()) {
                    case ">=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> parameter.getId() >= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "<=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> parameter.getId() <= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Содержит":
                    case "==":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> parameter.getId() == Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Не содержит":
                    case "!=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> parameter.getId() != Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    default:
                        alert.setContentText("Не выбрана операция!");
                        alert.show();
                }
                break;
            case "Тип":
                switch (operationComboBox.getValue()) {
                    case ">=":
                    case "<=":
                    case "Содержит":
                    case "==":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> parameter.getType().toString().contains(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Не содержит":
                    case "!=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> !parameter.getType().toString().contains(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    default:
                        alert.setContentText("Не выбрана операция!");
                        alert.show();
                }
                break;
            case "Описание":
                switch (operationComboBox.getValue()) {
                    case ">=":
                    case "<=":
                    case "Содержит":
                    case "==":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> parameter.getDescription().contains(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Не содержит":
                    case "!=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> !parameter.getDescription().contains(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    default:
                        alert.setContentText("Не выбрана операция!");
                        alert.show();
                }
                break;
            case "Имя":
                switch (operationComboBox.getValue()) {
                    case ">=":
                    case "<=":
                    case "Содержит":
                    case "==":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> parameter.getLabel().contains(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Не содержит":
                    case "!=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> !parameter.getLabel().contains(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    default:
                        alert.setContentText("Не выбрана операция!");
                        alert.show();
                }
                break;
            case "Адрес":
                switch (operationComboBox.getValue()) {
                    case ">=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getAddress()) >= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "<=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getAddress()) <= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Содержит":
                    case "==":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getAddress()) == Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Не содержит":
                    case "!=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getAddress()) != Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    default:
                        alert.setContentText("Не выбрана операция!");
                        alert.show();
                }
                break;
            case "Размер":
                switch (operationComboBox.getValue()) {
                    case ">=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getSize()) >= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "<=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getSize()) <= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Содержит":
                    case "==":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getSize()) == Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Не содержит":
                    case "!=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getSize()) != Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    default:
                        alert.setContentText("Не выбрана операция!");
                        alert.show();
                }
                break;
            case "Значение":
                switch (operationComboBox.getValue()) {
                    case ">=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getValue()) >= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "<=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getValue()) <= Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Содержит":
                    case "==":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getValue()) == Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    case "Не содержит":
                    case "!=":
                        filteredParameters.addAll(tableData.stream().filter(parameter -> Integer.parseInt(parameter.getValue()) != Integer.parseInt(searchTextField.getText())).collect(Collectors.toList()));
                        break;
                    default:
                        alert.setContentText("Не выбрана операция!");
                        alert.show();
                }
                break;
        }
        table.setItems(FXCollections.observableList(filteredParameters));
    }

    public void start(ActionEvent actionEvent) {
    }

    public void stop(ActionEvent actionEvent) {
    }
}

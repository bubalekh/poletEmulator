package app.frontend.controllers;

import app.backend.models.Parameter;
import app.backend.models.ParametersWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final List<Parameter> tableData = ParametersWrapper.getInstance().getParameterList();

    public Group root;

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
    }
}

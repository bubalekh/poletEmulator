package app.frontend.controllers;

import app.backend.models.Parameter;
import app.backend.models.ParametersWrapper;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
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
        idColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("type"));
        labelColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("label"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("description"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("address"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("size"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("value"));

        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        System.out.println(addressColumn.isEditable());

        table.setItems(FXCollections.observableList(tableData));
        table.setEditable(true);
        addressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Parameter, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Parameter, String> parameterStringCellEditEvent) {
                parameterStringCellEditEvent.getRowValue().setAddress(parameterStringCellEditEvent.getNewValue());
                System.out.println(addressColumn.getCellData(parameterStringCellEditEvent.getRowValue()));
            }
        });
        sizeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Parameter, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Parameter, String> parameterStringCellEditEvent) {
                parameterStringCellEditEvent.getRowValue().setSize(parameterStringCellEditEvent.getNewValue());
                System.out.println(sizeColumn.getCellData(parameterStringCellEditEvent.getRowValue()));
            }
        });
        valueColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Parameter, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Parameter, String> parameterStringCellEditEvent) {
                parameterStringCellEditEvent.getRowValue().setValue(parameterStringCellEditEvent.getNewValue());
                System.out.println(valueColumn.getCellData(parameterStringCellEditEvent.getRowValue()));
            }
        });
    }
}

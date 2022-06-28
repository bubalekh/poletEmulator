package app.frontend.controllers;

import app.backend.Parameter;
import app.backend.models.Parameters;
import app.backend.models.ParametersWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
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
    private TableColumn<Parameter, Integer> addressColumn;

    @FXML
    private TableColumn<Parameter, Integer> sizeColumn;

    @FXML
    private TableColumn<Parameter, Integer> valueColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("type"));
        labelColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("label"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Parameter, String>("description"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("address"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("size"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Parameter, Integer>("value"));

        //labelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellValueFactory(TextFieldTableCell.forTableColumn());

        System.out.println(addressColumn.isEditable());

        table.setItems(FXCollections.observableList(tableData));
        table.setEditable(true);
        addressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Parameter, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Parameter, Integer> parameterIntegerCellEditEvent) {
                parameterIntegerCellEditEvent.getRowValue().setAddress(parameterIntegerCellEditEvent.getNewValue());
                System.out.println(addressColumn.getCellData(parameterIntegerCellEditEvent.getRowValue()));
            }
        });
        sizeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Parameter, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Parameter, Integer> parameterIntegerCellEditEvent) {
                parameterIntegerCellEditEvent.getRowValue().setSize(parameterIntegerCellEditEvent.getNewValue());
                System.out.println(sizeColumn.getCellData(parameterIntegerCellEditEvent.getRowValue()));
            }
        });
        valueColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Parameter, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Parameter, Integer> parameterIntegerCellEditEvent) {
                parameterIntegerCellEditEvent.getRowValue().setValue(parameterIntegerCellEditEvent.getNewValue());
                System.out.println(valueColumn.getCellData(parameterIntegerCellEditEvent.getRowValue()));
            }
        });
    }
}

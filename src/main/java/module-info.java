module poletEmulator {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jackson.annotations;
    requires jackson.dataformat.xml;
    requires jackson.databind;
    requires org.apache.commons.lang3;

    opens app.frontend.controllers;
    opens app.backend.models;
    opens app.backend;

    exports app.frontend;
    exports app.backend;
    exports app.backend.models;
}
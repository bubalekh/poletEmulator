module poletEmulator {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jackson.annotations;
    requires jackson.dataformat.xml;

    opens app.frontend.controllers;

    exports app.frontend;
    exports app.backend;
}
package app;

import app.backend.Backend;
import app.frontend.Frontend;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Backend backend = new Backend();
        Thread backendThread = new Thread(backend);
        backendThread.start();
        Application.launch(Frontend.class, args);
    }
}

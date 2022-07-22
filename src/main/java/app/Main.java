package app;

import app.backend.Backend;
import app.backend.BackendHandler;
import app.frontend.Frontend;
import javafx.application.Application;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Backend backend = new Backend();
        backend.start();
        backend.pauseBackend();
        Thread backendHandlerThread = new BackendHandler(backend);
        backendHandlerThread.start();
        Application.launch(Frontend.class, args);
    }
}

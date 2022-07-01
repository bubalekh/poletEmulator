package app;

import app.backend.Backend;
import app.frontend.Frontend;
import javafx.application.Application;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Thread backendThread = new Thread(new Backend());
        backendThread.start();
        Application.launch(Frontend.class, args);
    }
}

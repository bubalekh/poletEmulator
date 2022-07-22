package app.backend;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BackendHandler extends Thread {
    private static final Queue<FrontendAction> frontendActions = new LinkedBlockingQueue<>();
    private final Backend backend;

    public BackendHandler(Backend backend) {
        this.backend = backend;
    }

    public static Queue<FrontendAction> getFrontendActions() {
        return frontendActions;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            if (!frontendActions.isEmpty()) {
                if (frontendActions.poll().isActive()) {
                    backend.resumeBackend();
                    System.out.println("Запущено");
                } else {
                    backend.pauseBackend();
                    System.out.println("Не запущено");
                }
            }
            else {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Я живой!!!");
        }
    }
}

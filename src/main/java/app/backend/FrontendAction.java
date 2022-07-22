package app.backend;

public class FrontendAction {
    private final boolean active;

    public FrontendAction(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}

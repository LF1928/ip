package duke;

import javafx.application.Application;

/**
 * The Launcher class serves as an entry point to start the application.
 * This class is required to work around classpath issues when launching JavaFX applications.
 */
public class Launcher {
    /**
     * Launches the app
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

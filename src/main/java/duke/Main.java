package duke;

import java.io.IOException;
import java.util.Objects;

import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main entry point for the Cinnamoroll Task Manager application.
 * This class initializes the UI and handles user interactions.
 */
public class Main extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInputField;
    private Cinnamoroll cinnamoroll = new Cinnamoroll();
    private final Image chatbotImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/cinnamonroll.jpg")));

    /**
     * The main entry point for the Cinnamoroll Task Manager application.
     * This class initializes the UI and handles user interactions.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getCinnamoDialog(Ui.start(), chatbotImage)
        );
        dialogContainer.getChildren().add(
                DialogBox.getCinnamoDialog(Ui.help(), chatbotImage)
        );
    }

    /**
     * Sets the chatbot instance used for processing user input.
     *
     * @param c The chatbot instance.
     */
    public void setChatbot(Cinnamoroll c) {
        cinnamoroll = c;
    }

    /**
     * Starts the JavaFX application by setting up the main window.
     *
     * @param stage The primary stage for the application.
     */
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Cinnamoroll Task Manager");
            stage.setScene(scene);
            fxmlLoader.<duke.Main>getController().setChatbot(cinnamoroll); // inject the cinnamoroll instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles user input when the user enters a message.
     * It processes the input through the chatbot and displays the response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInputField.getText().trim();
        String reply = cinnamoroll.processUserInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getCinnamoDialog(reply, chatbotImage)
        );
        userInputField.clear();
    }
}

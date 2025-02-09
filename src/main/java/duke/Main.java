package duke;

import duke.command.Command;
import duke.command.Storage;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDescriptionException;
import duke.parsers.Parser;
import duke.tasks.Task;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInputField;
    @FXML
    private Button enterButton;
    private Scene scene;
    private Cinnamonroll cinnamonroll = new Cinnamonroll();;
    private Image chatbotImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/cinnamonroll.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    public void setChatbot(Cinnamonroll c) {
        cinnamonroll = c;
    }

    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<duke.Main>getController().setChatbot(cinnamonroll);  // inject the cinnamonroll instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleUserInput() {
        String input = userInputField.getText().trim();
        String reply = cinnamonroll.processUserInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getCinnamonDialog(reply, chatbotImage)
        );
        userInputField.clear();
    }
}

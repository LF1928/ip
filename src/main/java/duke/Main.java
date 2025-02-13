package duke;


import duke.ui.Ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInputField;
    private Cinnamoroll cinnamoroll = new Cinnamoroll();;
    private final Image chatbotImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/cinnamonroll.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getCinnamonDialog(Ui.start(), chatbotImage)
        );
    }
    public void setChatbot(Cinnamoroll c) {
        cinnamoroll = c;
    }

    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<duke.Main>getController().setChatbot(cinnamoroll);  // inject the cinnamoroll instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleUserInput() {
        String input = userInputField.getText().trim();
        String reply = cinnamoroll.processUserInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getCinnamonDialog(reply, chatbotImage)
        );
        userInputField.clear();
    }
}

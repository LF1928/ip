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
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private Scene scene;
    private Image chatbotImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/cinnamonroll.jpg")));

    public void start(Stage primaryStage) {

        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);


        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.setContent(dialogContainer);

        //Input field
        userInputField = new TextField();
        userInputField.setPromptText("User Input");
        userInputField.setPrefWidth(325);

        //Enter button
        enterButton = new Button("Enter");
        enterButton.setStyle("-fx-background-color: lightgreen;");
        enterButton.setPrefWidth(55.0);
        enterButton.setOnAction(event -> handleUserInput());

        userInputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Simulate a button click when Enter is pressed
                enterButton.fire();
            }
        });

        //Input box layout
        HBox inputBox = new HBox(10, userInputField, enterButton);
        inputBox.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
        inputBox.setPadding(new Insets(5));
        inputBox.setMinHeight(30);


        DialogBox dialogBox = DialogBox.getCinnamonDialog(Ui.start(), chatbotImage);
        dialogContainer.getChildren().addAll(dialogBox);


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, inputBox, enterButton);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(enterButton, 5.0);
        AnchorPane.setRightAnchor(enterButton, 5.0);

        AnchorPane.setLeftAnchor(inputBox, 1.0);
        AnchorPane.setBottomAnchor(inputBox, 1.0);
        mainLayout.setStyle("-fx-background-color: #ADD8E6;");

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        scene = new Scene(mainLayout, 400, 600);
        primaryStage.setTitle("Cinnamonroll");
        primaryStage.setScene(scene);
        primaryStage.show();

        Storage.ensureDirectoryExists();
        Storage.loadTasksFromFile(listOfTasks);
    }
    private String processUserInput(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(input, listOfTasks);

            Storage.saveTasksToFile(listOfTasks);

            if (command == Command.BYE) {
                Platform.exit();
            }

            return response;
        } catch (MissingDescriptionException | IllegalArgumentException | InvalidTaskNumberException e) {
            return e.getMessage();
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInputField.getText().trim();
        String reply = processUserInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getCinnamonDialog(reply, chatbotImage)
        );
        userInputField.clear();
    }
}

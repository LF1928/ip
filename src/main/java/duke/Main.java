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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Main extends Application {
    private TextArea chatHistory;
    private TextField userInputField;
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public void start(Stage primaryStage) {
        //Input field
        userInputField = new TextField();
        userInputField.setPromptText("User Input");
        userInputField.setPrefWidth(300);

        //ChatHistory
        chatHistory = new TextArea();
        chatHistory.setEditable(false);
        chatHistory.setWrapText(true);
        chatHistory.setStyle("-fx-font-family: monospace; -fx-font-size: 14;");
        chatHistory.appendText(Ui.start());

        //Enter button
        Button enterButton = new Button("Enter");
        enterButton.setStyle("-fx-background-color: lightgreen;");
        enterButton.setOnAction(event -> handleUserInput());

        //Input box layout
        HBox inputBox = new HBox(10, userInputField, enterButton);
        inputBox.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
        inputBox.setPadding(new Insets(5));
        inputBox.setMinHeight(50);

        // Create a BorderPane layout
        BorderPane root = new BorderPane();
        root.setCenter(chatHistory); // Chat history in the center
        root.setBottom(inputBox); // Input box at the bottom

        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("ChatBot UI");
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
            return "ChatBot: " + e.getMessage();
        }
    }

    private void handleUserInput() {
        String input = userInputField.getText().trim();
        if (!input.isEmpty()) {
            // Display user input in the chat history
            chatHistory.appendText("You: " + input + "\n");
            userInputField.clear();

            // Process user input and get reply
            String reply = processUserInput(input);
            chatHistory.appendText("ChatBot: " + reply + "\n");
        }
    }
}

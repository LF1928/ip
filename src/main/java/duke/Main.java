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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.util.ArrayList;

public class Main extends Application {
    private ListView<Message> chatHistory;
    private TextField userInputField;
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public void start(Stage primaryStage) {
        //Input field
        userInputField = new TextField();
        userInputField.setPromptText("User Input");
        userInputField.setPrefWidth(300);

        //ChatHistory
        chatHistory = new ListView<>();
        chatHistory.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {
                return new ListCell<Message>() {
                    @Override
                    protected void updateItem(Message item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            TextFlow textFlow = new TextFlow();
                            Text text = new Text(item.getContent());
                            text.setStyle("-fx-font-family: monospace; -fx-font-size: 14;");

                            textFlow.getChildren().add(text);
                            textFlow.setMaxWidth(200); // Set max width for messages
                            textFlow.setPadding(new Insets(5));

                            if (item.isUserMessage()) {
                                textFlow.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");
                                setAlignment(Pos.CENTER_RIGHT);
                            } else {
                                textFlow.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 10;");
                                setAlignment(Pos.CENTER_LEFT);
                            }

                            setGraphic(textFlow);
                        }
                    }
                };
            }
        });

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

        chatHistory.getItems().add(new Message(Ui.start(), false));

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

    private void handleUserInput() {
        String input = userInputField.getText().trim();
        if (!input.isEmpty()) {
            // Display user input in the chat history
            chatHistory.getItems().add(new Message("You: " + input, true));
            userInputField.clear();

            // Process user input and get reply
            String reply = processUserInput(input);
            chatHistory.getItems().add(new Message("LFChat: " + reply, false));
        }
    }
}

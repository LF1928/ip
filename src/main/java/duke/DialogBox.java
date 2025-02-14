package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box used for displaying messages in a chat interface.
 * Each dialog box consists of a text message and an optional display picture.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox object containing text and an optional image.
     *
     * @param text The message to be displayed in the dialog box.
     * @param img  The image to be displayed alongside the message (can be null).
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (img != null) {
            displayPicture.setImage(img);
        } else {
            getChildren().remove(displayPicture);
        }
    }

    /**
     * Flips the dialog box so that the text appears on the left,
     * simulating a reply from bot.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a user dialog box without an image.
     * The text is styled with the "user-label" CSS class.
     *
     * @param s The text message to be displayed.
     * @return A DialogBox instance representing the user's message.
     */
    public static DialogBox getUserDialog(String s) {
        var db = new DialogBox(s, null);
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    /**
     * Creates a dialog box for Cinnamoroll (bot) with an image.
     * The message is flipped to the left to indicate a bot response.
     *
     * @param s The text message to be displayed.
     * @param i The image to be displayed alongside the message.
     * @return A DialogBox instance representing Cinnamon's response.
     */
    public static DialogBox getCinnamoDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}

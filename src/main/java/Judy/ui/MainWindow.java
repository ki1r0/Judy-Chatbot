package Judy.ui;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Judy judy;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Putin.png")));
    private Image JudyImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Trump.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = Ui.showWelcome();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, JudyImage));
    }

    /** Injects the Duke instance */
    public void setDuke(Judy j) {
        judy = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = judy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, JudyImage)
        );
        userInput.clear();
    }
}

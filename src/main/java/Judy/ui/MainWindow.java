package Judy.ui;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private HBox inputContainer;
    @FXML
    private Button sendButton;
    private Judy judy;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Putin.png")));
    private Image JudyImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Trump.png")));

    /**
     * Initializes the UI by setting up scrolling behavior and displaying a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
        userInput.prefWidthProperty().bind(inputContainer.widthProperty()
                .subtract(sendButton.widthProperty()).subtract(10));
        String welcomeMessage = Ui.showWelcome();
        dialogContainer.getChildren().add(DialogBox.getJudyDialog(welcomeMessage, JudyImage));
    }

    public void setJudy(Judy j) {
        judy = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = judy.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJudyDialog(response, JudyImage)
            );
            userInput.clear();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ Root Cause: " + e.getCause());
        }
    }
}

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Created by mateusz on 17.08.17.
 */
public class CustomizeScore extends BasicScene {
    private GridPane grid;

    private Score score;
    private Label scoreLabel;
    private TextField usersScoreLimitInput;
    private Button changeScoreLimitButton;
    private Button resetScoreButton;

    public CustomizeScore(RocksmithChallenger rocksmithChallenger, Score score) {
        super(rocksmithChallenger);
        this.score = score;
    }

    private GridPane structGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20.0);
        grid.setVgap(20.0);
        grid.setPadding(new Insets(25,25,25,25));

        createComponents();
        applyPropertiesToComponents();
        addObjectsToGridPane();
        addEventHandlers();
        applyCSS();

        return grid;
    }

    private void createComponents() {
        scoreLabel = new Label();
        usersScoreLimitInput = new TextField("Write new score limit here.");
        changeScoreLimitButton = new Button("Change score limit");
        resetScoreButton = new Button("Reset score");
    }

    private void applyPropertiesToComponents() {
        scoreLabel.setDisable(true);
        scoreLabel.setMouseTransparent(true);

        usersScoreLimitInput.setEditable(true);

        readjustIndicators();

        double PREF_WIDTH = 600;
        scoreLabel.setPrefWidth(PREF_WIDTH);
        usersScoreLimitInput.setPrefWidth(PREF_WIDTH);
        changeScoreLimitButton.setPrefWidth(PREF_WIDTH);
        resetScoreButton.setPrefWidth(PREF_WIDTH);
    }

    private void addObjectsToGridPane() {
        grid.add(scoreLabel, 0, 0);
        grid.add(usersScoreLimitInput, 0, 1);
        grid.add(changeScoreLimitButton, 0 , 2);
        grid.add(resetScoreButton, 0, 3);
    }

    private void applyCSS() {
        grid.getStyleClass().add("grid");

        scoreLabel.getStyleClass().add("CustomizeScore_scoreLabel");
        usersScoreLimitInput.getStyleClass().add("CustomizeScore_usersScoreLimitInput");
    }

    private void addEventHandlers() {
        usersScoreLimitInput.setOnMouseClicked(event -> {
            usersScoreLimitInput.setText("");
            usersScoreLimitInput.setStyle("-fx-font-fill: black;");
        });

        changeScoreLimitButton.setOnAction(event -> {
            try {
                String usersInput = usersScoreLimitInput.getText();
                Double usersInputDouble = score.stringWithSpacesToDoubleParser(usersInput);
                score.setScoreLimit(usersInputDouble);
                readjustIndicators();
            } catch (NumberFormatException e) {
                usersScoreLimitInput.setText("You should enter a number.");
                usersScoreLimitInput.setStyle("-fx-text-fill: red;");
            } catch (ScoreLesserThanOneException e) {
                usersScoreLimitInput.setText("You should enter a positive number.");
                usersScoreLimitInput.setStyle("-fx-text-fill: red;");
            }
        });

        resetScoreButton.setOnAction(event -> {
            score.setCurrentScore(0);
            readjustIndicators();
        });

    }

    private void readjustIndicators() {
        scoreLabel.setText(String.format(" %n Current score: %,.0f / %,.0f %n ",
                                                score.getCurrentScore(), score.getScoreLimit()));
    }

    @Override
    protected void setGridPaneInsideBorderPane(GridPane grid) {
        super.setGridPaneInsideBorderPane(structGridPane());
    }

    @Override
    public Scene getScene() {
        Scene scene = super.getScene();
        super.setGridPaneInsideBorderPane(grid);
        return scene;
    }

}

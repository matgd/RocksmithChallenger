import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by mateusz on 17.08.17.
 */
public class ScoreChaser extends BasicScene {
    private GridPane grid;

    private Score score;
    private ProgressBar scoreBar;
    private TextField scoreField;
    private TextField scorePercentField;
    private TextField scoreInputField;
    private Button challengeButton;

    public ScoreChaser(RocksmithChallenger rocksmithChallenger, Score score) {
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
        scoreBar = score.getScoreBar();
        scoreField = score.getScoreField();
        scorePercentField = score.getScorePercentField();
        scoreInputField = new TextField();
        challengeButton = new Button("Add score!");
    }

    private void applyPropertiesToComponents() {
        scoreField.setMouseTransparent(true);
        scorePercentField.setMouseTransparent(true);

        double PREF_WIDTH = 600;
        scoreBar.setPrefWidth(PREF_WIDTH);
        scoreField.setPrefWidth(PREF_WIDTH);
        scorePercentField.setPrefWidth(PREF_WIDTH);
        scoreInputField.setPrefWidth(PREF_WIDTH);
        challengeButton.setPrefWidth(PREF_WIDTH);
    }

    private void addObjectsToGridPane() {
        grid.add(scoreBar, 0 ,0);
        grid.add(scoreField, 0,  1);
        grid.add(scorePercentField, 0 , 2);
        grid.add(scoreInputField, 0, 3);
        grid.add(challengeButton, 0 ,4);
    }

    private void applyCSS() {
        grid.getStyleClass().add("grid");

        scoreBar.getStyleClass().add("ScoreChaser_scoreBar");
        scoreField.getStyleClass().add("ScoreChaser_scoreField");
        scorePercentField.getStyleClass().add("ScoreChaser_scorePercentField");
        scoreInputField.getStyleClass().add("ScoreChaser_scoreInputField");
    }


    private void addEventHandlers() {
        scoreInputField.setOnMouseClicked(event -> {
            scoreInputField.setText("");
            scoreInputField.setStyle("-fx-font-fill: black;");
        });

        challengeButton.setOnAction(event -> {
            try {
                String scoreInputString = scoreInputField.getText();
                Double scoreInputDouble = score.stringWithSpacesToDoubleParser(scoreInputString);
                if(scoreInputDouble + score.getCurrentScore() < 0) {
                    throw new ScoreLesserThanZeroException();
                }
                score.addToCurrentScore(scoreInputDouble);
            } catch (NumberFormatException e) {
                scoreInputField.setText("You should enter a number.");
                scoreInputField.setStyle("-fx-font-fill: red;");
            } catch (ScoreLesserThanZeroException e) {
                score.setCurrentScore(0);
            }
        });
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

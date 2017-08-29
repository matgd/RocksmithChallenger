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
public class ScoreChaser extends BasicScene implements SceneProvider {
    private GridPane grid;

    private Score score;
    private ProgressBar scoreBar;
    private TextField scoreField;
    private TextField scorePercentField;
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
        challengeButton = new Button("Add score!");
    }

    private void applyPropertiesToComponents() {
        scoreField.setMouseTransparent(true);
        scorePercentField.setMouseTransparent(true);

        double PREF_WIDTH = 600;
        scoreBar.setPrefWidth(PREF_WIDTH);
        scoreField.setPrefWidth(PREF_WIDTH);
        scorePercentField.setPrefWidth(PREF_WIDTH);
        challengeButton.setPrefWidth(PREF_WIDTH);
    }

    private void addObjectsToGridPane() {
        grid.add(scoreBar, 0 ,0);
        grid.add(scoreField, 0,  1);
        grid.add(scorePercentField, 0 , 2);
        grid.add(challengeButton, 0 ,3);
    }

    private void applyCSS() {
        grid.getStyleClass().add("grid");

        scoreBar.getStyleClass().add("scoreBar");
        scoreField.getStyleClass().add("scoreField");
        scorePercentField.getStyleClass().add("scorePercentField");
    }


    private void addEventHandlers() {
        challengeButton.setOnAction(event -> score.addToCurrentScore(45_000*(Math.random())));
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

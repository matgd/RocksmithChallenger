import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
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

    private FlowPane radioButtonFlowPane;
    private int inputMultiplier = 1;
    private ToggleGroup inputMultipliersGroup;
    private RadioButton multiplyInputByOneRadioButton;
    private RadioButton multiplyInputByThousandRadioButton;
    private RadioButton multiplyInputByHundredThousandRadioButton;

    private Button addScoreButton;

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

        radioButtonFlowPane = new FlowPane();
        inputMultipliersGroup = new ToggleGroup();
        multiplyInputByOneRadioButton = new RadioButton("x 1        ");
        multiplyInputByThousandRadioButton = new RadioButton("x 1 000       ");
        multiplyInputByHundredThousandRadioButton = new RadioButton("x 100 000");

        addScoreButton = new Button("Add score!");
    }

    private void applyPropertiesToComponents() {
        scoreField.setMouseTransparent(true);
        scorePercentField.setMouseTransparent(true);

        multiplyInputByOneRadioButton.setToggleGroup(inputMultipliersGroup);
        multiplyInputByThousandRadioButton.setToggleGroup(inputMultipliersGroup);
        multiplyInputByHundredThousandRadioButton.setToggleGroup(inputMultipliersGroup);
        multiplyInputByOneRadioButton.setSelected(true);
        radioButtonFlowPane.setAlignment(Pos.CENTER);
        radioButtonFlowPane.getChildren().add(multiplyInputByOneRadioButton);
        radioButtonFlowPane.getChildren().add(multiplyInputByThousandRadioButton);
        radioButtonFlowPane.getChildren().add(multiplyInputByHundredThousandRadioButton);

        double PREF_WIDTH = 600;
        scoreBar.setPrefWidth(PREF_WIDTH);
        scoreField.setPrefWidth(PREF_WIDTH);
        scorePercentField.setPrefWidth(PREF_WIDTH);
        scoreInputField.setPrefWidth(PREF_WIDTH);
        addScoreButton.setPrefWidth(PREF_WIDTH);
    }

    private void addObjectsToGridPane() {
        grid.add(scoreBar, 0 ,0);
        grid.add(scoreField, 0,  1);
        grid.add(scorePercentField, 0 , 2);
        grid.add(scoreInputField, 0, 3);
        grid.add(radioButtonFlowPane, 0, 4);
        grid.add(addScoreButton, 0 ,5);
    }

    private void applyCSS() {
        grid.getStyleClass().add("grid");

        scoreBar.getStyleClass().add("ScoreChaser_scoreBar");
        scoreField.getStyleClass().add("ScoreChaser_scoreField");
        scorePercentField.getStyleClass().add("ScoreChaser_scorePercentField");
        scoreInputField.getStyleClass().add("ScoreChaser_scoreInputField");
        multiplyInputByOneRadioButton.getStyleClass().add("ScoreChaser_RadioButton");
        multiplyInputByThousandRadioButton.getStyleClass().add("ScoreChaser_RadioButton");
        multiplyInputByHundredThousandRadioButton.getStyleClass().add("ScoreChaser_RadioButton");
    }


    private void addEventHandlers() {
        multiplyInputByOneRadioButton.setOnAction(event -> {
            inputMultiplier = 1;
        });

        multiplyInputByThousandRadioButton.setOnAction(event -> {
            inputMultiplier = 1_000;
        });

        multiplyInputByHundredThousandRadioButton.setOnAction(event -> {
            inputMultiplier = 100_000;
        });

        scoreInputField.setOnMouseClicked(event -> {
            scoreInputField.setText("");
            scoreInputField.setStyle("-fx-font-fill: black;");
        });

        addScoreButton.setOnAction(event -> {
            try {
                String scoreInputString = scoreInputField.getText();
                Double scoreInputDouble = score.stringWithSpacesToDoubleParser(scoreInputString);
                if(scoreInputDouble + score.getCurrentScore() < 0) {
                    throw new ScoreLesserThanZeroException();
                }
                score.addToCurrentScore(scoreInputDouble * inputMultiplier);
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

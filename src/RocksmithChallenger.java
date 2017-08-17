import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by mateusz on 16.08.17.
 */
public class RocksmithChallenger extends Application {
    private GridPane grid;
    private Scene scene;

    private Score score;
    private ProgressBar scoreBar;
    private TextField scoreField;
    private TextField scorePercentField;
    private Button challengeButton;

    private void buildApplication(Stage primaryStage) {
        structGridPane();
        setUpStage(primaryStage);
        applyCSS();
    }

    private GridPane structGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20.0);
        grid.setVgap(20.0);
        grid.setPadding(new Insets(25,25,25,25));

        createComponents();
        applyPropertiesToComponents();

        grid.add(scoreBar, 0 ,0);
        grid.add(scoreField, 0,  1);
        grid.add(scorePercentField, 0 , 2);
        grid.add(challengeButton, 0 ,3);

        return grid;
    }

    private void createComponents() {
        score = new Score(250_000);
        scoreBar = score.getScoreBar();
        scoreField = score.getScoreField();
        scorePercentField = score.getScorePercentField();
        challengeButton = new Button("Add score!");

        challengeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                score.addToCurrentScore(45_000*(Math.random()));
            }
        });
    }

    private void applyPropertiesToComponents() {
        double PREF_WIDTH = 600;
        scoreBar.setPrefWidth(PREF_WIDTH);
        scoreField.setPrefWidth(PREF_WIDTH);
        scorePercentField.setPrefWidth(PREF_WIDTH);
        challengeButton.setPrefWidth(PREF_WIDTH);
    }

    private Stage setUpStage(Stage primaryStage) {
        double ASPECT_RATIO = (double)4/3;
        double WINDOW_MAX_HEIGHT = 720;
        double WINDOW_MAX_WIDTH = WINDOW_MAX_HEIGHT * ASPECT_RATIO;

        scene = new Scene(grid, 800, 600);

        primaryStage.setTitle("Rocksmith Challenger");
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(WINDOW_MAX_WIDTH);
        primaryStage.setMaxHeight(WINDOW_MAX_HEIGHT);
        primaryStage.show();

        return primaryStage;
    }

    private void applyCSS() {
        scene.getStylesheets().add("style.css");
        grid.getStyleClass().add("grid");

        scoreBar.getStyleClass().add("scoreBar");
        scoreField.getStyleClass().add("scoreField");
        scorePercentField.getStyleClass().add("scorePercentField");

    }

    @Override
    public void start(Stage primaryStage) {
        buildApplication(primaryStage);
    }
}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by mateusz on 16.08.17.
 */
public class RocksmithChallenger extends Application {
    private Score score;
    private ScoreChaser scoreChaser;
    private Options options;

    private void buildApplication(Stage primaryStage) {
        createAppComponents();
        setUpStage(primaryStage, scoreChaser.getScene()); //temp instead of Menu's scene
    }

    private void createAppComponents() {
        score = new Score(250_000);
        scoreChaser = new ScoreChaser(score);

        //TODO
        //options = new Options(score, ...???);
        //menu = new Menu(???);
    }

    private Stage setUpStage(Stage primaryStage, Scene scene) {
        double ASPECT_RATIO = (double)4/3;
        double WINDOW_MAX_HEIGHT = 720;
        double WINDOW_MAX_WIDTH = WINDOW_MAX_HEIGHT * ASPECT_RATIO;

        primaryStage.setTitle("Rocksmith Challenger");
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(WINDOW_MAX_WIDTH);
        primaryStage.setMaxHeight(WINDOW_MAX_HEIGHT);
        primaryStage.show();

        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        buildApplication(primaryStage);
    }

    public Score getScore() {
        return score;
    }
}

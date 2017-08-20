import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by mateusz on 16.08.17.
 */
public class RocksmithChallenger extends Application {
    private Stage stage;
    private Score score;

    private ScoreChaser scoreChaser;
    private Menu menu;

    private Scene currentScene;

    private void buildApplication(Stage stage) {
        createAppComponents();
        currentScene = scoreChaser.getScene();
        currentScene = menu.getScene();
        setUpStage(currentScene);
    }

    private void createAppComponents() {
        score = new Score(250_000);
        scoreChaser = new ScoreChaser(this, score);
        menu = new Menu(this);
    }

    protected Stage setUpStage(Scene scene) {
        double ASPECT_RATIO = (double)4/3;
        double WINDOW_MAX_HEIGHT = 720;
        double WINDOW_MAX_WIDTH = WINDOW_MAX_HEIGHT * ASPECT_RATIO;

        stage.setTitle("Rocksmith Challenger");
        stage.setScene(currentScene);
        stage.setMaxWidth(WINDOW_MAX_WIDTH);
        stage.setMaxHeight(WINDOW_MAX_HEIGHT);
        stage.show();

        return stage;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        buildApplication(stage);
    }

    public void setCurrentSceneToScoreChasers() {
        // TODO (improve)
       currentScene = scoreChaser.getScene();
       stage.setScene(currentScene);
    }

    //TODO improve

    public void setCurrentSceneToMenus() {
        currentScene = menu.getScene();
        stage.setScene(currentScene);
    }
}

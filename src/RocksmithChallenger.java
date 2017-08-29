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
    private Options options;
    private BasicScene basicScene;
    private AboutMe aboutMeScene;
    //TODO
    // challenges
    // stats

    private Scene currentScene;
    private SceneProvider currentSceneProvider;

    private void buildApplication(Stage stage) {
        createAppComponents();
        currentScene = scoreChaser.getScene();
        setUpStage(currentScene);
    }

    private void createAppComponents() {
        score = new Score(250_000);
        scoreChaser = new ScoreChaser(this, score);
        options = new Options(this, score);
        basicScene = new BasicScene(this);
        aboutMeScene = new AboutMe(this);
    }

    private Stage setUpStage(Scene scene) {
        double ASPECT_RATIO = (double)4/3;
        double WINDOW_MAX_HEIGHT = 720;
        double WINDOW_MAX_WIDTH = WINDOW_MAX_HEIGHT * ASPECT_RATIO;

        // TODO
        // Switching between scenes changes window resolution back to 800x600

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

    public void changeCurrentScene(SceneProvider sceneProvider) {
        currentScene = sceneProvider.getScene();
        stage.setScene(currentScene);
    }

    public ScoreChaser getScoreChaser() {
        return scoreChaser;
    }

    public Options getOptions() {
        return options;
    }

    public BasicScene getBasicScene() {
        return basicScene;
    }

    public AboutMe getAboutMeScene() {
        return aboutMeScene;
    }
}

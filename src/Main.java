import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by mateusz on 07.08.17.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        RocksmithChallenger app = new RocksmithChallenger();
        app.start(primaryStage);
    }
}

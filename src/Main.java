import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
        primaryStage.setTitle("Rocksmith Challenger");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20.0);
        grid.setVgap(20.0);
        grid.setPadding(new Insets(25,25,25,25));


        ProgressBar scoreBar = new ProgressBar(0.0);
        scoreBar.setPrefWidth(300);

        Button challengeButton = new Button("Give me a challenge!");
        challengeButton.setPrefWidth(scoreBar.getPrefWidth());

        challengeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double CURRENT_PROGRESS = scoreBar.getProgress();
                scoreBar.setProgress(CURRENT_PROGRESS + (Math.random()/3));
            }
        });

        Scene scene = new Scene(grid, 350, 300);
        primaryStage.setScene(scene);
        grid.add(scoreBar, 0 ,0);
        grid.add(challengeButton, 0 ,1);


        primaryStage.show();
    }


}

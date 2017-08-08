import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

        Score score = new Score(250_000);

        ProgressBar scoreBar = score.getScoreBar();
        scoreBar.setPrefWidth(300);

        TextField scoreField = score.getScoreField();

        TextField scorePercentField = score.getScorePercentField();

        Button challengeButton = new Button("Add score!");
        challengeButton.setPrefWidth(scoreBar.getPrefWidth());

        challengeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                score.addToCurrentScore(45_000*(Math.random()));
            }
        });

        Scene scene = new Scene(grid, 350, 300);
        primaryStage.setScene(scene);
        grid.add(scoreBar, 0 ,0);
        grid.add(scoreField, 0,  1);
        grid.add(scorePercentField, 0 , 2);
        grid.add(challengeButton, 0 ,3);

        primaryStage.show();
    }

    private void structGrid() {

    }


}

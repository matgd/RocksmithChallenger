import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Created by mateusz on 20.08.17.
 */
public class Menu extends RocksmithChallenger implements SceneProvider {
    private RocksmithChallenger rocksmithChallenger;
    private GridPane grid;
    private Scene scene;

    private Button goToScoreChaserButton;
    private Button goToOptionsButton;

    public Menu(RocksmithChallenger rocksmithChallenger) {
        this.rocksmithChallenger = rocksmithChallenger;
    }

    private Scene buildScene() {
        structGridPane();
        createSceneWithGridPane();
        applyPropertiesToComponents();
        applyCSS();
        return scene;
    }

    private GridPane structGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20.0);
        grid.setVgap(20.0);
        grid.setPadding(new Insets(25,25,25,25));

        createComponents();
        applyPropertiesToComponents();

        grid.add(goToScoreChaserButton, 0 ,0);
        grid.add(goToOptionsButton, 0,  1);;

        return grid;
    }

    private void createComponents() {
        goToScoreChaserButton = new Button("Score Chaser");
        goToOptionsButton = new Button("Options");
        addEventHandlers();
    }

    private void addEventHandlers() {
        goToScoreChaserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScoreChaser scoreChaser = rocksmithChallenger.getScoreChaser();
                rocksmithChallenger.changeCurrentScene(scoreChaser);
            }
        });

        goToOptionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Options options = rocksmithChallenger.getOptions();
                rocksmithChallenger.changeCurrentScene(options);
            }
        });
    }

    private void applyPropertiesToComponents() {
        double PREF_WIDTH = 600;
        goToScoreChaserButton.setPrefWidth(PREF_WIDTH);
        goToOptionsButton.setPrefWidth(PREF_WIDTH);
    }

    private void createSceneWithGridPane() {
        scene = new Scene(grid, 800, 600);
    }

    private void applyCSS() {
        scene.getStylesheets().add("style.css");
        grid.getStyleClass().add("grid");
    }

    public Scene getScene() {
        return buildScene();
    }

    public GridPane getGridPane() {
        return grid;
    }
}

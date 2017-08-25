import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Created by mateusz on 17.08.17.
 */
public class Options extends RocksmithChallenger implements SceneProvider {
    private RocksmithChallenger rocksmithChallenger;
    private GridPane grid;
    private Scene scene;

    private Score score;
    private TextField currentScoreLimitTextField;
    private TextField usersScoreLimitInput;
    private Button changeScoreLimitButton;

    public Options(RocksmithChallenger rocksmithChallenger, Score score) {
        this.rocksmithChallenger = rocksmithChallenger;
        this.score = score;
    }

    protected Scene buildScene() {
        structGridPane();
        createSceneWithGridPane();
        applyCSS();
        addEventHandlers();
        changeScoreLimitButton.requestFocus();
        return scene;
    }

    private GridPane structGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        createComponents();
        applyPropertiesToComponents();

        grid.add(currentScoreLimitTextField, 0, 0);
        grid.add(usersScoreLimitInput, 0, 1);
        grid.add(changeScoreLimitButton, 0 , 2);

        return grid;
    }

    private void createComponents() {
        currentScoreLimitTextField = score.getScoreField();
        usersScoreLimitInput = new TextField("Write new score limit here.");
        changeScoreLimitButton = new Button("Change score limit.");
    }

    private void applyPropertiesToComponents() {
        usersScoreLimitInput.setEditable(true);
    }

    private void createSceneWithGridPane() {
        scene = new Scene(grid, 800, 600);
    }

    private void applyCSS() {
        //TODO
    }

    private void addEventHandlers() {
        usersScoreLimitInput.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                usersScoreLimitInput.setText("");
            }
        });
    }

    public Scene getScene() {
        return buildScene();
    }

}

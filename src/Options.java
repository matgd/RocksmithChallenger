import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;

/**
 * Created by mateusz on 17.08.17.
 */
public class Options extends RocksmithChallenger {
    private GridPane grid;
    private Scene scene;

    private TextField maxScore;
    private TextInputControl usersMaxScore;
    private Button changeMaxScoreButton;

    protected Scene buildScene() {
        structGridPane();
        return scene;
    }

    private GridPane structGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        createComponents();
       // applyPropertiesToComponents();

        return grid;
    }

    private void createComponents() {

    }


}

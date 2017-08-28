import javafx.geometry.*;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


/**
 * Created by mateusz on 28.08.17.
 */
public class AboutMe extends BasicScene {
    private GridPane grid;

    private Label myGithubLabel;

    public AboutMe(RocksmithChallenger rocksmithChallenger) {
        super(rocksmithChallenger);
    }

    private GridPane structGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20.0);
        grid.setVgap(20.0);
        grid.setPadding(new javafx.geometry.Insets(25,25,25,25));

        createComponents();
        applyPropertiesToComponents();
        addObjectsToGridPane();
        applyCSS();

        return grid;
    }

    private void createComponents() {
        myGithubLabel = new Label("github.com/matgd");
    }

    private void applyPropertiesToComponents() {
        myGithubLabel.getStyleClass().add("-fx-font-family: monospace");
    }

    private void addObjectsToGridPane() {
        grid.add(myGithubLabel, 0, 0);
    }

    private void applyCSS() {
        grid.getStyleClass().add("grid");
    }

    @Override
    protected void setGridPaneInsideBorderPane(GridPane grid) {
        super.setGridPaneInsideBorderPane(structGridPane());
    }

    @Override
    public Scene getScene() {
        Scene scene = super.getScene();
        super.setGridPaneInsideBorderPane(grid);
        return scene;
    }
}

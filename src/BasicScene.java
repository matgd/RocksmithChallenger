import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Created by Mateusz on 2017-08-27.
 */
public class BasicScene implements SceneProvider {
    private GridPane grid;
    private BorderPane borderPane;

    private Scene scene;

    private MenuBar menuBar;

    private javafx.scene.control.Menu menuMenu;
    private MenuItem scoreChaserMenuItem;
    private MenuItem placeholderMenuItem;  //PLACEHOLDER

    private Menu optionsMenu;


    private Scene buildScene() {
        structGridPane();
        createSceneWithGridPaneInsideBorderPane();
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

        return grid;
    }

    private void createComponents() {
        menuBar = new MenuBar();

        menuMenu = new Menu("Menu");
        scoreChaserMenuItem = new MenuItem("Score Chaser");
        placeholderMenuItem = new MenuItem("PLACEHOLDER");

        optionsMenu = new Menu("Options");

    }

    private void applyPropertiesToComponents() {
        borderPane = new BorderPane();
        borderPane.setCenter(grid);
        borderPane.setTop(menuBar);



        menuBar.getMenus().add(menuMenu);

        menuMenu.getItems().add(scoreChaserMenuItem);
        menuMenu.getItems().add(placeholderMenuItem);

        menuBar.getMenus().add(optionsMenu);

    }

    private void createSceneWithGridPaneInsideBorderPane() {
        scene = new Scene(borderPane, 800, 600);
    }

    private void applyCSS() {
        scene.getStylesheets().add("style.css");
        grid.getStyleClass().add("grid");
    }

    public GridPane getGridPane() {
        return grid;
    }

    @Override
    public Scene getScene() {
        return buildScene();
    }
}

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
    private RocksmithChallenger rocksmithChallenger;
    private GridPane grid;
    private BorderPane borderPane;

    private Scene scene;

    private MenuBar menuBar;

    private javafx.scene.control.Menu menuMenu;
    private MenuItem scoreChaserMenuItem;
    private MenuItem placeholderMenuItem;  //PLACEHOLDER

    private javafx.scene.control.Menu optionsMenu;

    private javafx.scene.control.Menu helpMenu;
    private MenuItem aboutMeMenuItem;

    public BasicScene(RocksmithChallenger rocksmithChallenger) {
        this.rocksmithChallenger = rocksmithChallenger;
    }

    private Scene buildScene() {
        structBorderPane();
        structGridPane();
        createSceneWithGridPaneInsideBorderPane();
        applyCSS();
        return scene;
    }

    private BorderPane structBorderPane() {
        borderPane = new BorderPane();
        return borderPane;
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

        helpMenu = new Menu("Help");
        aboutMeMenuItem = new MenuItem("About me");
    }

    private void applyPropertiesToComponents() {
        setGridPaneInsideBorderPane(grid);
        borderPane.setTop(menuBar);

        menuBar.getMenus().add(menuMenu);
        menuMenu.getItems().add(scoreChaserMenuItem);
        menuMenu.getItems().add(placeholderMenuItem);

        menuBar.getMenus().add(optionsMenu);

        menuBar.getMenus().add(helpMenu);
        helpMenu.getItems().add(aboutMeMenuItem);

        addEventHandlers();
    }

    private void addEventHandlers() {
        aboutMeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutMe aboutMe = rocksmithChallenger.getAboutMeScene();
                rocksmithChallenger.changeCurrentScene(aboutMe);
            }
        });
    }

    protected void setGridPaneInsideBorderPane(GridPane grid) {
        borderPane.setCenter(grid);
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

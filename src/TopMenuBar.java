import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Created by mateusz on 29.08.17.
 */
public class TopMenuBar {
    private RocksmithChallenger rocksmithChallenger;

    private MenuBar menuBar;

    private Menu menuMenu;
    private MenuItem scoreChaserMenuItem;

    private Menu optionsMenu;
    private MenuItem customizeScoreMenuItem;

    private Menu helpMenu;
    private MenuItem howThisProgramWorksMenuItem;
    private MenuItem aboutMeMenuItem;

    public TopMenuBar(RocksmithChallenger rocksmithChallenger) {
        this.rocksmithChallenger = rocksmithChallenger;
        createComponents();
        applyPropertiesToComponents();
    }

    private void createComponents() {
        menuBar = new MenuBar();

        menuMenu = new Menu("Menu");
        scoreChaserMenuItem = new MenuItem("Score Chaser");

        optionsMenu = new Menu("Options");
        customizeScoreMenuItem = new MenuItem("Customize score");

        helpMenu = new Menu("Help");
        howThisProgramWorksMenuItem = new MenuItem("How this program works");
        aboutMeMenuItem = new MenuItem("About me");
    }

    private void applyPropertiesToComponents() {
        menuBar.getMenus().add(menuMenu);
        menuMenu.getItems().add(scoreChaserMenuItem);

        menuBar.getMenus().add(optionsMenu);
        optionsMenu.getItems().add(customizeScoreMenuItem);

        menuBar.getMenus().add(helpMenu);
        helpMenu.getItems().add(howThisProgramWorksMenuItem);
        helpMenu.getItems().add(aboutMeMenuItem);

        addEventHandlers();
    }

    private void addEventHandlers() {
        scoreChaserMenuItem.setOnAction(event -> {
            ScoreChaser scoreChaser = rocksmithChallenger.getScoreChaser();
            rocksmithChallenger.changeCurrentScene(scoreChaser);
        });

        aboutMeMenuItem.setOnAction(event -> {
            AboutMe aboutMe = rocksmithChallenger.getAboutMeScene();
            rocksmithChallenger.changeCurrentScene(aboutMe);
        });


        customizeScoreMenuItem.setOnAction(event -> {
            CustomizeScore customizeScore = rocksmithChallenger.getCustomizeScore();
            rocksmithChallenger.changeCurrentScene(customizeScore);
        });
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}

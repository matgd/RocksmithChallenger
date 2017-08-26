import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Label scoreLabel;
    private TextField usersScoreLimitInput;
    private Button changeScoreLimitButton;
    private Button resetScoreButton;
    private Button menuButton;

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
        grid.setHgap(20.0);
        grid.setVgap(20.0);
        grid.setPadding(new Insets(25,25,25,25));

        createComponents();
        applyPropertiesToComponents();

        grid.add(scoreLabel, 0, 0);
        grid.add(usersScoreLimitInput, 0, 1);
        grid.add(changeScoreLimitButton, 0 , 2);
        grid.add(resetScoreButton, 0, 3);
        grid.add(menuButton, 0, 4);

        return grid;
    }

    private void createComponents() {
        scoreLabel = new Label();
        usersScoreLimitInput = new TextField("Write new score limit here.");
        changeScoreLimitButton = new Button("Change score limit");
        resetScoreButton = new Button("Reset score");
        menuButton = new Button("Menu");
    }

    private void applyPropertiesToComponents() {

        scoreLabel.setDisable(true);
        scoreLabel.setMouseTransparent(true);

        usersScoreLimitInput.setEditable(true);

        readjustIndicators();

        double PREF_WIDTH = 600;
        scoreLabel.setPrefWidth(PREF_WIDTH);
        usersScoreLimitInput.setPrefWidth(PREF_WIDTH);
        changeScoreLimitButton.setPrefWidth(PREF_WIDTH);
        resetScoreButton.setPrefWidth(PREF_WIDTH/2);
        menuButton.setPrefWidth(PREF_WIDTH/2);
    }

    private void createSceneWithGridPane() {
        scene = new Scene(grid, 800, 600);
    }

    private void applyCSS() {
        scene.getStylesheets().add("style.css");
        grid.getStyleClass().add("grid");

        scoreLabel.getStyleClass().add("scoreLabel");
    }

    private void addEventHandlers() {
        usersScoreLimitInput.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                usersScoreLimitInput.setText("");
                usersScoreLimitInput.setStyle("-fx-font-fill: black;");
            }
        });

        changeScoreLimitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String usersInput = usersScoreLimitInput.getText();
                    Double usersInputDouble = score.stringWithSpacesToDoubleParser(usersInput);
                    score.setScoreLimit(usersInputDouble);
                    readjustIndicators();
                } catch (NumberFormatException e) {
                    usersScoreLimitInput.setText("You should enter a number.");
                    usersScoreLimitInput.setStyle("-fx-text-fill: red;");
                } catch (ScoreLesserThanOneException e) {
                    usersScoreLimitInput.setText("You should enter a positive number.");
                    usersScoreLimitInput.setStyle("-fx-text-fill: red;");
                }
            }
        });

        resetScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                score.setCurrentScore(0);
                readjustIndicators();
            }
        });

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Menu menu = rocksmithChallenger.getMenu();
                rocksmithChallenger.changeCurrentScene(menu);
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE) {
                    Menu menu = rocksmithChallenger.getMenu();
                    rocksmithChallenger.changeCurrentScene(menu);
                }
            }
        });
    }

    private void readjustIndicators() {
        scoreLabel.setText(String.format(" Current score: %,.0f / %,.0f",
                                                score.getCurrentScore(), score.getScoreLimit()));
    }

    public Scene getScene() {
        return buildScene();
    }

}

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
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
    private TextField currentScoreLimitTextField;
    private TextField usersScoreLimitInput;
    private Button changeScoreLimitButton;
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

        grid.add(currentScoreLimitTextField, 0, 0);
        grid.add(usersScoreLimitInput, 0, 1);
        grid.add(changeScoreLimitButton, 0 , 2);
        grid.add(menuButton, 0, 3);

        return grid;
    }

    private void createComponents() {
        currentScoreLimitTextField = new TextField(String.format("Current score limit: %,.0f", score.getScoreLimit()));
        usersScoreLimitInput = new TextField("Write new score limit here.");
        changeScoreLimitButton = new Button("Change score limit.");
        menuButton = new Button("Menu");
    }

    private void applyPropertiesToComponents() {
        currentScoreLimitTextField.setEditable(false);
        currentScoreLimitTextField.setAlignment(Pos.CENTER);
        currentScoreLimitTextField.setFocusTraversable(false);
        currentScoreLimitTextField.setDisable(true);

        usersScoreLimitInput.setEditable(true);

        double PREF_WIDTH = 600;
        currentScoreLimitTextField.setPrefWidth(PREF_WIDTH);
        usersScoreLimitInput.setPrefWidth(PREF_WIDTH);
        changeScoreLimitButton.setPrefWidth(PREF_WIDTH);
        menuButton.setPrefWidth(PREF_WIDTH/2);
    }

    private void createSceneWithGridPane() {
        scene = new Scene(grid, 800, 600);
    }

    private void applyCSS() {
        scene.getStylesheets().add("style.css");
        grid.getStyleClass().add("grid");


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
        currentScoreLimitTextField.setText(String.format("Current score limit: %,.0f", score.getScoreLimit()));
    }

    public Scene getScene() {
        return buildScene();
    }

}

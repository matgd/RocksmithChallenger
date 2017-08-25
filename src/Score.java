import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * Created by mateusz on 08.08.17.
 */
public class Score {
    private double currentScore;
    private double scoreLimit;
    private double progressPercent;

    ProgressBar scoreBar;
    TextField scoreField;
    TextField scorePercentField;

    public Score(double currentScore, double scoreLimit) {
        this.currentScore = currentScore;
        this.scoreLimit = scoreLimit;

        scoreField = new TextField();
        scoreBar = new ProgressBar();
        scorePercentField = new TextField();

        scoreField.setEditable(false);
        scoreField.setAlignment(Pos.CENTER);
        scoreField.setFocusTraversable(false);

        scorePercentField.setAlignment(Pos.CENTER);
        scorePercentField.setEditable(false);
        scorePercentField.setFocusTraversable(false);
        scorePercentField.setFont(new Font(20));

        readjustIndicators();
    }

    public Score(double scoreLimit) {
        this(0, scoreLimit);
    }

    public ProgressBar getScoreBar() {
        return scoreBar;
    }

    public void setScoreBar(ProgressBar scoreBar) {
        this.scoreBar = scoreBar;
    }

    public TextField getScoreField() {
        return scoreField;
    }

    public void setScoreField(TextField scoreField) {
        this.scoreField = scoreField;
    }

    public double getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(double currentScore) {
        this.currentScore = currentScore;
        readjustIndicators();
    }

    public double getScoreLimit() {
        return scoreLimit;
    }

    public void setScoreLimit(double scoreLimit) throws ScoreLesserThanOneException {
        if(scoreLimit < 1) {
            throw new ScoreLesserThanOneException();
        }
        this.scoreLimit = scoreLimit;
        readjustIndicators();
    }

    public TextField getScorePercentField() {
        return scorePercentField;
    }

    public void setScorePercentField(TextField scorePercentField) {
        this.scorePercentField = scorePercentField;
    }

    public void addToCurrentScore(double additionalScore) {
        currentScore += additionalScore;
        readjustIndicators();
    }

    private void readjustIndicators() {
        scoreField.setText(String.format("%,.0f / %,.0f", currentScore, scoreLimit));
        scoreBar.setProgress(currentScore/scoreLimit);
        progressPercent = (currentScore/scoreLimit)*100;
        scorePercentField.setText(String.format("%.1f%%", progressPercent));
    }

    public Double stringWithSpacesToDoubleParser(String stringWithSpaces) {
        String input = stringWithSpaces;
        String[] inputPartsWithoutSpaces = input.split(" ");
        String stringWithoutSpaces = String.join("", inputPartsWithoutSpaces);
        Double stringWithoutSpacesToDouble = Double.parseDouble(stringWithoutSpaces);
        return stringWithoutSpacesToDouble;
    }
}

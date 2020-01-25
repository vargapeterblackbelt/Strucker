package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpretation.parsed.ParsedStatement;
import hu.elte.strucker.model.interpretation.parsed.ParsedStructogram;
import hu.elte.strucker.model.HealthCheck;
import hu.elte.strucker.recognizer.Expression;
import hu.elte.strucker.recognizer.ExpressionRecognizer;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.awt.*;

import static hu.elte.strucker.view.editor.ViewProperties.*;

@Getter
@Setter
public class Statement extends Structogram {

    private String statement;

    @JsonCreator
    public Statement(@JsonProperty("statement") String statement) {
        this.statement = statement;
    }

    @Override
    protected ParsedStructogram parse(Diagram diagram) {
        ExpressionRecognizer recognizer = new ExpressionRecognizer(diagram.getScope());
        Expression parsedStatement = recognizer.parse(statement);
        setErrors(recognizer.getErrors());
        if (!getErrors().isEmpty()) {
            diagram.setHealthCheck(HealthCheck.ERROR);
        }
        return new ParsedStatement(parsedStatement);
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int balanceHeight, Font font, boolean capture) {
        int height = textHeight(font) + balanceHeight;
        g.setColor(isHovered() && !capture ? DEFAULT_HOVER_COLOR : DEFAULT_FILL_COLOR);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.setColor(hasError() && !capture ? DEFAULT_ERROR_COLOR : DEFAULT_FONT_COLOR);
        drawTextLeft(statement, x, y, width, height, g, font);
        if (!capture) {
            detectionArea = new Rectangle(x, y, width, height);
            drawErrors(g, font);
        }
    }

    @Override
    public int widthNeeded(Font font) {
        return textWidth(statement, font);
    }

    @Override
    public void hover(boolean isHovered) {
        setHovered(isHovered);
    }

    @Override
    public int heightNeeded(Font font) {
        return textHeight(font);
    }

    @Override
    public int countLeafs() {
        return 1;
    }

    @Override
    public Structogram in(int x, int y) {
        if (detectionArea == null) {
            return null;
        }
        return detectionArea.contains(x, y) ? this : null;
    }
}

package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpretation.parsed.ParsedSelection;
import hu.elte.strucker.model.interpretation.parsed.ParsedSequence;
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
public class Selection extends Structogram {

    private String condition;
    private Sequence thenSequence;
    private Sequence elseSequence;

    @JsonCreator
    public Selection(@JsonProperty("condition") String condition,
                     @JsonProperty("thenSequence") Sequence thenSequence,
                     @JsonProperty("elseSequence") Sequence elseSequence) {
        this.condition = condition;
        this.thenSequence = thenSequence;
        this.elseSequence = elseSequence;
        this.thenSequence.setParent(this);
        this.elseSequence.setParent(this);
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int balanceHeight, Font font, boolean capture) {
        g.setColor(isHovered() && !capture ? DEFAULT_HOVER_COLOR : DEFAULT_FILL_COLOR);
        int height = textHeight(font);
        if(!capture)
            detectionArea = new Rectangle(x, y, width, height);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.drawLine(x, y, x + height / 2, y + height);
        g.drawLine(x + width, y, x + width - height / 2, y + height);
        g.setColor(hasError() && !capture? DEFAULT_ERROR_COLOR : DEFAULT_FONT_COLOR);
        drawTextCentered(condition, x, y, width, height, g, font);
        int thenHeight = thenSequence.heightNeeded(font);
        int elseHeight = elseSequence.heightNeeded(font);
        if (thenHeight > elseHeight) {
            thenSequence.draw(g, x, y + height, thenSequence.widthNeeded(font), balanceHeight, font, capture);
            elseSequence.draw(g, x + thenSequence.widthNeeded(font), y + height, width - thenSequence.widthNeeded(font),
                     balanceHeight + (thenHeight - elseHeight)/elseSequence.countLeafs(), font, capture);
        }
        if (thenHeight < elseHeight) {
            thenSequence.draw(g, x, y + height, thenSequence.widthNeeded(font),
                    balanceHeight + (elseHeight - thenHeight)/thenSequence.countLeafs(), font, capture);
            elseSequence.draw(g, x + thenSequence.widthNeeded(font), y + height, width - thenSequence.widthNeeded(font), balanceHeight, font, capture);
        }

        if(thenHeight == elseHeight) {
            thenSequence.draw(g, x, y + height, thenSequence.widthNeeded(font), balanceHeight, font, capture);
            elseSequence.draw(g, x + thenSequence.widthNeeded(font), y + height, width - thenSequence.widthNeeded(font), balanceHeight, font, capture);
        }
    }

    @Override
    public int widthNeeded(Font font) {
        return Math.max(thenSequence.widthNeeded(font) + elseSequence.widthNeeded(font), textWidth(condition, font));
    }

    @Override
    public void hover(boolean isHovered) {
        setHovered(isHovered);
        thenSequence.hover(isHovered);
        elseSequence.hover(isHovered);
    }

    @Override
    public int heightNeeded(Font font) {
        return textHeight(font) + Math.max(thenSequence.heightNeeded(font), elseSequence.heightNeeded(font));
    }

    @Override
    public Structogram in(int x, int y) {
        if (detectionArea != null && detectionArea.contains(x, y)) {
            return this;
        }

        for (Structogram stg : thenSequence.getSequence()) {
            Structogram in = stg.in(x, y);
            if (in != null) {
                return in;
            }
        }

        for (Structogram stg : elseSequence.getSequence()) {
            Structogram in = stg.in(x, y);
            if (in != null) {
                return in;
            }
        }
        return null;
    }

    @Override
    public int countLeafs() {
        return Math.max(thenSequence.countLeafs(), elseSequence.countLeafs());
    }

    @Override
    public ParsedStructogram parse(Diagram diagram) {
        ExpressionRecognizer recognizer = new ExpressionRecognizer(diagram.getCurrentScope());
        Expression parsedCondition = recognizer.parse(condition);
        setErrors(recognizer.getErrors());
        if(!parsedCondition.hasType(Boolean.class)) {
            getErrors().add("Nem logikai kifejezés szerepel a feltételben");
        }
        if(!getErrors().isEmpty()){
            diagram.setHealthCheck(HealthCheck.ERROR);
        }
        return new ParsedSelection(parsedCondition,(ParsedSequence) thenSequence.parse(diagram), (ParsedSequence) elseSequence.parse(diagram));
    }
}

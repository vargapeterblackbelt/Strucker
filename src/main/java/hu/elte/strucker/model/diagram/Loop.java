package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpretation.parsed.ParsedLoop;
import hu.elte.strucker.model.interpretation.parsed.ParsedStructogram;
import hu.elte.strucker.model.HealthCheck;
import hu.elte.strucker.recognizer.Expression;
import hu.elte.strucker.recognizer.ExpressionRecognizer;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static hu.elte.strucker.view.editor.ViewProperties.*;

@Getter
@Setter
public class Loop extends Sequence {

    private String condition;

    @JsonCreator
    public Loop(@JsonProperty("condition") String condition,
                @JsonProperty("sequence") List<Structogram> sequence) {
        super(sequence);
        this.condition = condition;
    }

    @Override
    public ParsedStructogram parse(Diagram diagram) {
        ExpressionRecognizer recognizer = new ExpressionRecognizer(diagram.getScope());
        Expression parsedCondition = recognizer.parse(condition);
        setErrors(recognizer.getErrors());
        if(!parsedCondition.hasType(Boolean.class)) {
            getErrors().add("Nem logikai kifejezés szerepel a feltételben");
        }
        List<ParsedStructogram> list = new ArrayList<>();
        for (Structogram structogram : getSequence()) {
            list.add(structogram.parse(diagram));
        }
        if(!getErrors().isEmpty()){
            diagram.setHealthCheck(HealthCheck.ERROR);
        }
        return new ParsedLoop(list, parsedCondition);
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int balanceHeight, Font font, boolean capture) {
        int height = heightNeeded(font) + balanceHeight *countLeafs();
        detectionArea = new Rectangle(x, y, width, textHeight(font));
        g.setColor(isHovered() ? DEFAULT_HOVER_COLOR : DEFAULT_FILL_COLOR);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        drawTextLeft(condition, x, y, width, textHeight(font), g, font);
        super.draw(g, x+fontSize/2, y+textHeight(font), width-fontSize/2, balanceHeight, font, capture);
        g.setColor(hasError() ? DEFAULT_ERROR_COLOR : DEFAULT_FONT_COLOR);
//        drawErrors(g, font);
    }

    @Override
    public int widthNeeded(Font font) {
        int sequenceWidth = super.widthNeeded(font) + fontSize/2;
        int conditionWidth = textWidth(condition, font);
        return Math.max(sequenceWidth, conditionWidth);
    }

    @Override
    public void hover(boolean isHovered) {
        setHovered(isHovered);
        super.hover(isHovered);
    }

    @Override
    public int heightNeeded(Font font) {
        return super.heightNeeded(font) + textHeight(font);
    }

    @Override
    public Structogram in(int x, int y) {
        if(detectionArea != null && detectionArea.contains(x, y)) {
            return this;
        }
        return super.in(x, y);
    }
}

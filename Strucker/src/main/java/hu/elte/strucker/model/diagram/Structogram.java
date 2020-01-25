package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpretation.parsed.ParsedStructogram;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static hu.elte.strucker.view.editor.ViewProperties.*;

@Getter
@Setter
public abstract class Structogram implements StructogramView {

    @JsonIgnore
    private List<String> errors = new ArrayList<>();

    @JsonIgnore
    private Structogram parent;

    @JsonIgnore
    private boolean hovered = false;

    @JsonIgnore
    protected Rectangle detectionArea;

    protected abstract ParsedStructogram parse(Diagram diagram);

    public boolean hasError() {
        return !errors.isEmpty();
    }

    public static Statement skip() {
        return new Statement("SKIP");
    }

    @JsonIgnore
    public Sequence getParentSequence() {
        return parent instanceof Sequence ? (Sequence) parent : null;
    }

    protected void drawErrors(Graphics g, Font font) {
        if(errors.isEmpty() || !isHovered()) return;
        Font errorFont = font.deriveFont(Font.PLAIN, 14);
        int textHeight = textHeight(errorFont);
        int height = errors.size() * textHeight;
        Optional<String> max = errors.stream().max(Comparator.comparingInt((String text) -> textWidth(text, errorFont)));
        int width = textWidth(max.get(), errorFont);
        g.setColor(Color.red);
        g.fillRect(detectionArea.x, detectionArea.y-5-height, width, height);

        g.setColor(Color.white);
        int y = detectionArea.y-5-height;
        for (String error : errors) {
            drawTextCentered(error, detectionArea.x, y, width, textHeight, g, errorFont);
            y += textHeight;
        }
        g.setColor(Color.black);
    }

}


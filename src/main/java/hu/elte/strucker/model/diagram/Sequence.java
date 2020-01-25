package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpretation.parsed.ParsedSequence;
import hu.elte.strucker.model.interpretation.parsed.ParsedStructogram;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;

@Getter
@Setter
public class Sequence extends Structogram {

    private List<Structogram> sequence = new ArrayList<>();

    @JsonCreator
    public Sequence(@JsonProperty("sequence") List<Structogram> sequence) {
        for (Structogram structogram : sequence) {
            add(structogram);
        }

        if(this.sequence.isEmpty()) {
            add(skip());
        }

    }

    public int size() {
        return sequence.size();
    }

    public void add(Structogram child) {
        child.setParent(this);
        sequence.add(child);
    }

    public void add(int index, Structogram child) {
        child.setParent(this);
        sequence.add(index, child);
    }

    /*public void addBefore(Structogram child, Structogram newChild) {
        sequence.add(sequence.indexOf(child), newChild);
        child.setParent(this);
    }

    public void addAfter(Structogram child, Structogram newChild) {
        sequence.add(sequence.indexOf(child)+1, newChild);
        child.setParent(this);
    }*/

    public int indexOf(Structogram child) {
        return sequence.indexOf(child);
    }

/*    public void move(Structogram child, int index) {
        if(sequence.contains(child)) {
            sequence.remove(child);
            sequence.add(index, child);
        }
    }*/

    public void remove(Structogram child) {
        sequence.remove(child);
        if(sequence.isEmpty()) {
            add(skip());
        }
    }

    @Override
    protected ParsedStructogram parse(Diagram diagram) {
        List<ParsedStructogram> parsedSequence = new ArrayList<>();
        for (Structogram structogram : sequence) {
            ParsedStructogram parse = structogram.parse(diagram);
            parsedSequence.add(parse);
        }
        return new ParsedSequence(parsedSequence);
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int balanceHeight, Font font, boolean capture) {
        for (Structogram stg : sequence) {
            stg.draw(g, x, y, width, balanceHeight, font, capture);
            stg.drawErrors(g, font);
            y += stg.heightNeeded(font) + balanceHeight;
        }
    }

    @Override
    public int widthNeeded(Font font) {
        Optional<Structogram> width = sequence.stream().max(comparingInt(e -> e.widthNeeded(font)));
        return width.map(structogram -> structogram.widthNeeded(font)).orElse(0);
    }

    @Override
    public void hover(boolean isHovered) {
        sequence.forEach(e -> e.hover(isHovered));
    }

    @Override
    public int heightNeeded(Font font) {
        int sum = 0;
        for (Structogram structogram : sequence) {
            int heightNeeded = structogram.heightNeeded(font);
            sum += heightNeeded;
        }
        return sum;
    }

    @Override
    public Structogram in(int x, int y) {
        for (Structogram stg : sequence) {
            Structogram in = stg.in(x, y);
            if(in != null) {
                return in;
            }
        }
        return null;
    }

    @Override
    public int countLeafs() {
        int c = 0;
        for (Structogram stg : sequence) {
            c += stg.countLeafs();
        }
        return c;
    }
}

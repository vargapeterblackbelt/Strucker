package hu.elte.model.structogram;

import hu.elte.model.Explorable;
import lombok.Getter;
import lombok.Setter;

public class Loop extends Sequence {

    @Getter @Setter
    protected String condition;

    public Loop(Explorable parent, String condition) {
        super(parent);
        init(condition);
    }

    public Loop(String condition) {
        super();
        init(condition);
    }

    private void init(String condition) {
        this.condition = condition;
        add(new Action("SKIP"));
    }

    @Override
    public String getExploredName() {
        return "while "+condition;
    }
}

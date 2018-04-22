package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpreter.StructogramInterpreter;
import hu.elte.strucker.model.interpreter.StructogramValidator;
import hu.elte.strucker.model.interpreter.processed.ProcessedStructogram;
import lombok.*;

@Getter
@Setter
public class Loop extends Sequence {

    @NonNull
    private String condition;

    public Loop(String condition) {
        super();
        this.condition = condition;
        init();
    }

    public Loop() {
        super();
        condition = UNKNOWN_LABEL;
        init();
    }

    private void init() {
        setLabel("WHILE " + condition);
    }

    @Override
    public boolean validate(StructogramValidator validator) {
        return super.validate(validator);
    }

    @Override
    public ProcessedStructogram interpret(StructogramInterpreter interpreter) {
        return super.interpret(interpreter);
    }

}

package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpreter.StructogramInterpreter;
import hu.elte.strucker.model.interpreter.StructogramValidator;
import hu.elte.strucker.model.interpreter.processed.ProcessedStructogram;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Loop extends Sequence {

    @Getter
    @Setter
    @NonNull
    private String condition;

    public Loop(String condition) {
        super();
        setLabel("WHILE " + condition);
        this.condition = condition;
    }

    @Override
    public boolean validate(StructogramValidator validator) {
        return super.validate(validator);
    }

    @Override
    public ProcessedStructogram interpret(StructogramInterpreter interpreter) {
        return super.interpret(interpreter);
    }

    @Override
    public StructogramType getType() {
        return StructogramType.LOOP;
    }
}

package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.AbstractExplorable;
import hu.elte.strucker.model.interpreter.StructogramInterpreter;
import hu.elte.strucker.model.interpreter.StructogramValidator;
import hu.elte.strucker.model.interpreter.processed.ProcessedStructogram;

import javax.swing.tree.TreeNode;

public abstract class Structogram extends AbstractExplorable<Structogram> {

    public Structogram() {
        super();
    }

    public abstract boolean validate(StructogramValidator validator);
    public abstract ProcessedStructogram interpret(StructogramInterpreter interpreter);

}


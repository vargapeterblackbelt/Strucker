package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.AbstractExplorable;
import hu.elte.strucker.model.interpreter.processed.ProcessedStructogram;
import hu.elte.strucker.model.interpreter.StructogramInterpreter;
import hu.elte.strucker.model.interpreter.StructogramValidator;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.swing.tree.TreeNode;
import java.util.List;

public abstract class Structogram extends AbstractExplorable<Structogram> {

    public Structogram() {
        super();
    }

    @JsonIgnore
    @Override
    public abstract List<Structogram> getChilds();

    @JsonIgnore
    @Override
    public abstract TreeNode getTree();

    public abstract boolean validate(StructogramValidator validator);

    public abstract ProcessedStructogram interpret(StructogramInterpreter interpreter);

    @JsonIgnore
    public abstract StructogramType getType();

}


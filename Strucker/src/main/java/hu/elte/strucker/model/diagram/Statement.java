package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpreter.StructogramInterpreter;
import hu.elte.strucker.model.interpreter.StructogramValidator;
import hu.elte.strucker.model.interpreter.processed.ProcessedStructogram;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Statement extends Structogram {

    @Setter
    private String statement;

    public Statement(String stat) {
        super();
        this.statement = stat;
        setLabel(stat);
    }

    @Override
    public List<Structogram> getChilds() {
        return new ArrayList<>();
    }

    @Override
    public TreeNode getTree() {
        return new DefaultMutableTreeNode(this);
    }

    @Override
    public boolean validate(StructogramValidator validator) {
        return false;
    }

    @Override
    public ProcessedStructogram interpret(StructogramInterpreter interpreter) {
        return null;
    }

    @Override
    public StructogramType getType() {
        return StructogramType.STATEMENT;
    }
}

package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpreter.StructogramInterpreter;
import hu.elte.strucker.model.interpreter.StructogramValidator;
import hu.elte.strucker.model.interpreter.processed.ProcessedStructogram;
import lombok.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Selection extends Structogram {

    private String condition;
    private Sequence thenSequence;
    private Sequence elseSequence;

    public Selection() {
        condition = UNKNOWN_LABEL;
        init();
    }

    public Selection(String condition) {
        this.condition = condition;
        init();
    }

    private void init() {
        thenSequence = new Sequence();
        elseSequence = new Sequence();
        thenSequence.setLabel("THEN");
        elseSequence.setLabel("ELSE");
        setLabel("IF "+condition);
    }

    @Override
    public List<Structogram> getChilds() {
        List<Structogram> childs = new ArrayList<>();
        childs.add(thenSequence);
        childs.add(elseSequence);
        return childs;
    }

    @Override
    public TreeNode getTree() {
        DefaultMutableTreeNode branchNode = new DefaultMutableTreeNode(this);
        DefaultMutableTreeNode thenNode = (DefaultMutableTreeNode) thenSequence.getTree();
        DefaultMutableTreeNode elseNode = (DefaultMutableTreeNode) elseSequence.getTree();
        branchNode.add(thenNode);
        branchNode.add(elseNode);
        return branchNode;
    }

    @Override
    public boolean validate(StructogramValidator validator) {
        return false;
    }

    @Override
    public ProcessedStructogram interpret(StructogramInterpreter interpreter) {
        return null;
    }

}

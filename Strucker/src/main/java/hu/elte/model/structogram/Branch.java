package hu.elte.model.structogram;

import hu.elte.model.Explorable;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Branch extends Structogram {

    private Sequence thenSequence;
    private Sequence elseSequence;

    @Setter
    private String condition;

    public Branch(String condition, Explorable parent) {
        super(parent);
        init(condition);
    }

    public Branch(String condition) {
        super();
        init(condition);
    }

    private void init(String condition) {
        this.condition = condition;
        elseSequence = new Sequence(this);
        elseSequence.setSequenceType("else");
        thenSequence = new Sequence(this);
        thenSequence.setSequenceType("then");
    }

    @Override
    public boolean hasChilds() {
        return thenSequence.hasChilds() && elseSequence.hasChilds();
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
    public String getExploredName() {
        return "if "+condition;
    }

}

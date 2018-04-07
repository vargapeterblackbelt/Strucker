package hu.elte.model.project;

import hu.elte.interpreter.Parameter;
import hu.elte.model.structogram.Sequence;
import hu.elte.model.structogram.Structogram;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Diagram extends Sequence {

    @Setter @Getter
    private String name;
    @Setter @Getter
    private String type;
    @Getter
    private List<Parameter> parameters;

    public Diagram(String name, String type, Project project) {
        super();
        this.name = name;
        this.type = type;
        this.parent = project;
        parameters = new ArrayList<>();
    }

    @Override
    public TreeNode getTree() {
        DefaultMutableTreeNode diagramNode = new DefaultMutableTreeNode(this);
        for (Structogram stg : sequence) {
            DefaultMutableTreeNode stgNode = (DefaultMutableTreeNode) stg.getTree();
            diagramNode.add(stgNode);
        }
        return diagramNode;
    }

    @Override
    public String getExploredName() {
        return name+"()";
    }

}

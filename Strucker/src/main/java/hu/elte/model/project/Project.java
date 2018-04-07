package hu.elte.model.project;

import hu.elte.model.Explorable;
import hu.elte.model.structogram.Action;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Project implements Explorable<Diagram> {

    @Setter
    private String name;

    @Setter
    private String location;

    @Setter
    private String description;

    @Setter
    private String author;

    @Getter
    private List<Diagram> diagrams;

    @Getter
    private Diagram mainDiagram;

    public Project(String name, String description, String location, String author) {
        this.name = name;
        this.location = location;
        this.author = author;
        this.description = description;
        diagrams = new ArrayList<>();
        mainDiagram = new Diagram("main", "void", this);
        mainDiagram.add(new Action("SKIP"));
    }

    public void addDiagram(Diagram diagram) {
        diagrams.add(diagram);
    }

    public void removeDiagram(Diagram diagram) {
        diagrams.remove(diagram);
    }

    @Override
    public Explorable getParent() {
        return null;
    }

    @Override
    public void setParent(Explorable parent) {

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public boolean hasChilds() {
        return !diagrams.isEmpty();
    }

    @Override
    public List<Diagram> getChilds() {
        return diagrams;
    }

    @Override
    public TreeNode getTree() {
        DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(this);
        DefaultMutableTreeNode mainNode = (DefaultMutableTreeNode) mainDiagram.getTree();
        projectNode.add(mainNode);
        for (Diagram diagram : diagrams) {
            DefaultMutableTreeNode diagramNode = (DefaultMutableTreeNode) diagram.getTree();
            projectNode.add(diagramNode);
        }
        return projectNode;
    }

    @Override
    public String getExploredName() {
        return name;
    }

    @Override
    public String toString() {
        return getExploredName();
    }
}

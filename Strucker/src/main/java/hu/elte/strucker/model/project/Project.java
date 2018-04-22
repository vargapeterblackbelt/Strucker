package hu.elte.strucker.model.project;

import hu.elte.strucker.model.AbstractExplorable;
import hu.elte.strucker.model.diagram.Diagram;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends AbstractExplorable<Diagram> {

    @NonNull
    private String name;
    private String description;
    private String location;

    private List<Diagram> diagrams;

    public Project(String name) {
        super();
        this.name = name;
        description = null;
        location = null;
        init();
    }

    public Project() {
        super();
        init();
    }

    private void init() {
        diagrams = new ArrayList<>();
    }

    public void addDiagram(Diagram d) {
        diagrams.add(d);
    }

    public void removeDiagram(Diagram d) {
        diagrams.remove(d);
    }

    @Override
    public boolean hasChilds() {
        return !diagrams.isEmpty();
    }

    @Override
    public List<Diagram> getChilds() {
        return diagrams;
    }

    public DefaultMutableTreeNode getTree() {
        DefaultMutableTreeNode projectRoot = new DefaultMutableTreeNode(this);
        for (Diagram diagram : diagrams) {
            projectRoot.add((MutableTreeNode) diagram.getTree());
        }
        return projectRoot;
    }

    @Override
    public String getExploredName() {
        return name + " ["+location+"]";
    }
}

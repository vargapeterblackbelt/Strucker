package hu.elte.strucker.model.project;

import hu.elte.strucker.model.diagram.Diagram;
import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Project {

    private List<Diagram> diagrams;

    public Project(String name) {
        this.name = name;
        diagrams = new ArrayList<>();
        description = "";
        location = "";
    }

    public void addDiagram(Diagram d) {
        diagrams.add(d);
    }

    public void removeDiagram(Diagram d) {
        diagrams.remove(d);
    }

    @NonNull
    private String name;
    private String description;
    private String location;

    @JsonIgnore
    public DefaultMutableTreeNode getTree() {
        DefaultMutableTreeNode projectRoot = new DefaultMutableTreeNode(this);
        for (Diagram diagram : diagrams) {
            projectRoot.add((MutableTreeNode) diagram.getTree());
        }
        return projectRoot;
    }
}

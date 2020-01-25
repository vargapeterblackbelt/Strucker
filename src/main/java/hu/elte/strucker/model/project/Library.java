package hu.elte.strucker.model.project;

import hu.elte.strucker.model.diagram.Diagram;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Library {

    @JsonIgnore
    private Project project;

    private List<Diagram> diagrams = new ArrayList<>();

    private String name;

    private boolean permanent = false;

    @JsonCreator
    public Library(@JsonProperty("name") String name, @JsonProperty("diagrams") List<Diagram> diagrams, @JsonProperty("permanent") boolean permanent) {
        project = null;
        this.name = name;
        this.permanent = permanent;
        for (Diagram diagram : diagrams) {
            addDiagram(diagram);
        }
    }

    public void addDiagram(Diagram diagram) {
        diagram.setLibrary(this);
        diagrams.add(diagram);
    }

    public void removeDiagram(Diagram diagram) {
        diagrams.remove(diagram);
    }

}

package hu.elte.strucker.model.project;

import hu.elte.strucker.model.HealthCheck;
import hu.elte.strucker.model.ProjectStatus;
import hu.elte.strucker.model.diagram.Diagram;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static hu.elte.strucker.model.HealthCheck.UNKNOWN;
import static hu.elte.strucker.model.ProjectStatus.SAVED;

@Getter
@Setter
public class Project {

    private String name;

    private String location;

    @JsonIgnore
    private HealthCheck healthCheck = UNKNOWN;

    @JsonIgnore
    private ProjectStatus status = SAVED;

    private List<Library> libraries = new ArrayList<>();

    @JsonCreator
    public Project(@JsonProperty("name") String name,
                   @JsonProperty("location") String location,
                   @JsonProperty("libraries") List<Library> libraries) {
        this.name = name;
        this.location = location;
        for (Library library : libraries) {
            addLibrary(library);
        }
    }

    public void addLibrary(Library library) {
        library.setProject(this);
        libraries.add(library);
    }

    public void removeLibrary(Library library) {
        libraries.remove(library);
    }

    @JsonIgnore
    public List<Diagram> getDiagrams() {
        List<Diagram> diagrams = new ArrayList<>();
        for (Library library : libraries) {
            diagrams.addAll(library.getDiagrams());
        }
        return diagrams;
    }
}

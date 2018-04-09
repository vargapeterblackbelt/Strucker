package hu.elte.strucker.model.project;

import hu.elte.strucker.model.diagram.Diagram;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project {

    private List<Diagram> diagrams;

    @NonNull
    private String name;
    private String description;
    private String location;

}

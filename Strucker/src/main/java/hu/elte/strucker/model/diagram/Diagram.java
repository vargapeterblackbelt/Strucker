package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpreter.Parameter;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Diagram extends Sequence {

    @NonNull
    @Setter
    private String name;
    private List<Parameter> parameters;

    public Diagram(String name, Parameter... parameters) {
        this.name = name;
        this.parameters = new ArrayList<>();
        this.parameters.addAll(Arrays.asList(parameters));
    }

}

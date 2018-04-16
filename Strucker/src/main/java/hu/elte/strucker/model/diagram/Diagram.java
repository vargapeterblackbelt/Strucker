package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpreter.Parameter;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Diagram extends Sequence {

    @NonNull
    private String name = "Diagram neve";
    private String returnType = "Visszatérési érték típusa";
    private List<Parameter> parameters = new ArrayList<>();

    public Diagram(String name, String type, List<Parameter> parameters) {
        this.name = name;
        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    @Override
    public String getExploredName() {
        return name + "()";
    }
}

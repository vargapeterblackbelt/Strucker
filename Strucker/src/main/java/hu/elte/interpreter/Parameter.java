package hu.elte.interpreter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Parameter {
    private String name;
    private String type;
}

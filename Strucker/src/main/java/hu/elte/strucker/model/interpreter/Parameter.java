package hu.elte.strucker.model.interpreter;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Parameter {
    @NonNull
    private String name;
    private String type;
}

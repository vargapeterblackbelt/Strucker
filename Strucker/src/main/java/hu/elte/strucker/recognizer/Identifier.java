package hu.elte.strucker.recognizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Identifier {
    private String name;
    private Class type;
    private Object value;
}

package hu.elte.strucker.model.interpreter;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {
    @NonNull
    private String name;
    private String type;
}

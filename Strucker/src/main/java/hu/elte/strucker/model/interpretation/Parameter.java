package hu.elte.strucker.model.interpretation;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {
    @NonNull
    private String name;
    private Class type;
}

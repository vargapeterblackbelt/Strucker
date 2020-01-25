package hu.elte.strucker.recognizer;

import lombok.Getter;

@Getter
public class Expression {
    private Class type;
    private Operation operation;
    private String text;

    public Expression(Class type, Operation operation, String text) {
        this.type = type;
        this.operation = operation;
        this.text = text;
    }

    public <T> T eval(Class<T> type) throws Exception {
        T eval = (T) operation.eval();
        return eval;
    }

    public Object eval() throws Exception {
        Object eval = operation.eval();
        return eval;
    }

    public boolean hasType(Class type) {
        return this.type == Object.class || this.type.isAssignableFrom(type);
    }

}

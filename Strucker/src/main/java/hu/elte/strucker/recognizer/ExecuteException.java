package hu.elte.strucker.recognizer;

import lombok.Getter;

@Getter
public class ExecuteException extends  Exception {
    public ExecuteException(String msg, Expression expression) {
        super(msg + ": " + expression.getText());
    }
}

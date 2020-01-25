package hu.elte.strucker.model.interpretation.parsed;

import hu.elte.strucker.recognizer.Expression;

public class ParsedStatement extends AbstractParsedStructogram {

    private Expression statement;

    public ParsedStatement(Expression expression) {
        super();
        this.statement = expression;
    }

    @Override
    public void execute() throws Exception {
        statement.eval();
    }
}

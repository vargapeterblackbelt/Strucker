package hu.elte.strucker.model.interpretation.parsed;


import hu.elte.strucker.recognizer.Expression;

public class ParsedSelection extends AbstractParsedStructogram {

    private ParsedSequence thenSequence;
    private ParsedSequence elseSequence;
    private Expression condition;

    public ParsedSelection(Expression condition, ParsedSequence thenSequence, ParsedSequence elseSequence) {
        super();
        this.condition = condition;
        this.thenSequence = thenSequence;
        this.elseSequence = elseSequence;
    }


    @Override
    public void execute() throws Exception {
        Boolean eval = condition.eval(Boolean.class);
        if(eval) {
            thenSequence.execute();
        } else {
            elseSequence.execute();
        }
    }
}

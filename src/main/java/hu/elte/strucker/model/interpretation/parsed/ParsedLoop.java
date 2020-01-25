package hu.elte.strucker.model.interpretation.parsed;

import hu.elte.strucker.recognizer.Expression;
import hu.elte.strucker.recognizer.PotentialInfinteLoopException;

import java.util.List;

public class ParsedLoop extends ParsedSequence {

    private Expression condition;

    public ParsedLoop(List<ParsedStructogram> sequence, Expression condition) {
        super(sequence);
        this.condition = condition;
    }

    @Override
    public void execute() throws Exception {
        int iterationCount = 0;
        while(condition.eval(Boolean.class)) {
            if(iterationCount == 1000000) {
                throw new PotentialInfinteLoopException("Egy ciklus túllépte a megengedett iterációk számát: "+condition.getText());
            }
            super.execute();
            iterationCount++;
        }
    }
}

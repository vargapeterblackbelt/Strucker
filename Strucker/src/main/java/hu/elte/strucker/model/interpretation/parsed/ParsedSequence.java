package hu.elte.strucker.model.interpretation.parsed;

import java.util.List;

public class ParsedSequence extends AbstractParsedStructogram {

    private List<ParsedStructogram> sequence;

    public ParsedSequence(List<ParsedStructogram> sequence) {
        super();
        this.sequence = sequence;
    }

    @Override
    public void execute() throws Exception {
        for (ParsedStructogram parsedStructogram : sequence) {
            parsedStructogram.execute();
        }
    }
}

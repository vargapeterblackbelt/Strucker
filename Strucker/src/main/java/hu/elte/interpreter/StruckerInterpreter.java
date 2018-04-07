package hu.elte.interpreter;

import hu.elte.interpreter.parser.StruckerLangLexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.tool.Grammar;

public class StruckerInterpreter {
    Grammar g = new Grammar(null);

    public StruckerInterpreter() throws RecognitionException {
        new StruckerLangLexer(new UnbufferedCharStream());
    }
}

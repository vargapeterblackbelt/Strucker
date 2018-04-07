// Generated from /home/peter/Asztal/Strucker/src/main/java/hu/elte/interpreter/parser/StruckerLang.g4 by ANTLR 4.7
package hu.elte.interpreter.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StruckerLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NATURAL=1, INTEGER=2, RACIONAL=3, SET_OPEN=4, SET_CLOSE=5, SEQUENCE_OPEN=6, 
		SEQUENCE_CLOSE=7, ASSIGN=8, DEF=9, EQUALS=10, LESSER_EQUALS=11, GREATER_EQUALS=12, 
		LESSER=13, GREATER=14, NOT=15, AND=16, OR=17, IN=18, PLUS=19, MINUS=20, 
		MULTIPLY=21, DIVIDE=22, MOD=23, PUT=24, ADD=25, AT=26, INTO=27, GET=28, 
		REMOVE=29, FROM=30, EMPTY=31, ABORT=32, STRING=33, BRACKET_OPEN=34, BRACKET_CLOSE=35, 
		COMMA=36, ID=37, WS=38;
	public static final int
		RULE_statement = 0;
	public static final String[] ruleNames = {
		"statement"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'{'", "'}'", "'['", "']'", "':='", "':'", "'='", 
		"'<='", "'>='", "'<'", "'>'", "'not'", "'and'", "'or'", "'in'", "'+'", 
		"'-'", "'*'", "'/'", "'%'", "'put'", "'add'", "'at'", "'into'", "'get'", 
		"'remove'", "'from'", "'skip'", "'abort'", null, "'('", "')'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NATURAL", "INTEGER", "RACIONAL", "SET_OPEN", "SET_CLOSE", "SEQUENCE_OPEN", 
		"SEQUENCE_CLOSE", "ASSIGN", "DEF", "EQUALS", "LESSER_EQUALS", "GREATER_EQUALS", 
		"LESSER", "GREATER", "NOT", "AND", "OR", "IN", "PLUS", "MINUS", "MULTIPLY", 
		"DIVIDE", "MOD", "PUT", "ADD", "AT", "INTO", "GET", "REMOVE", "FROM", 
		"EMPTY", "ABORT", "STRING", "BRACKET_OPEN", "BRACKET_CLOSE", "COMMA", 
		"ID", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "StruckerLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StruckerLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementContext extends ParserRuleContext {
		public StatementContext statement;
		public TerminalNode BRACKET_OPEN() { return getToken(StruckerLangParser.BRACKET_OPEN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(StruckerLangParser.BRACKET_CLOSE, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StruckerLangListener ) ((StruckerLangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StruckerLangListener ) ((StruckerLangListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		try {
			int _alt;
			setState(14);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2);
				match(BRACKET_OPEN);
				setState(3);
				((StatementContext)_localctx).statement = statement();
				setState(4);
				match(BRACKET_CLOSE);

				        System.out.println("("+(((StatementContext)_localctx).statement!=null?_input.getText(((StatementContext)_localctx).statement.start,((StatementContext)_localctx).statement.stop):null)+")");
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(10);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(7);
						matchWildcard();
						}
						} 
					}
					setState(12);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}

				        System.out.println("Semmi");
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\23\4\2\t\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\7\2\13\n\2\f\2\16\2\16\13\2\3\2\5\2\21\n\2\3\2\2\2"+
		"\3\2\2\2\2\23\2\20\3\2\2\2\4\5\7$\2\2\5\6\5\2\2\2\6\7\7%\2\2\7\b\b\2\1"+
		"\2\b\21\3\2\2\2\t\13\13\2\2\2\n\t\3\2\2\2\13\16\3\2\2\2\f\n\3\2\2\2\f"+
		"\r\3\2\2\2\r\17\3\2\2\2\16\f\3\2\2\2\17\21\b\2\1\2\20\4\3\2\2\2\20\f\3"+
		"\2\2\2\21\3\3\2\2\2\4\f\20";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
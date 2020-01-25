// Generated from /home/peter/work/git/Strucker/Strucker/src/main/java/hu/elte/strucker/recognizer/StructoLang.g4 by ANTLR 4.7
package hu.elte.strucker.recognizer;

    import hu.elte.strucker.recognizer.ExpressionRecognizer;
    import hu.elte.strucker.recognizer.Expression;
    import lombok.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StructoLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, WS=36, NUMBER=37, ID=38, DIAGRAM_ID=39, 
		String=40;
	public static final int
		RULE_eval = 0, RULE_expr = 1, RULE_function = 2, RULE_list = 3, RULE_id_expr = 4;
	public static final String[] ruleNames = {
		"eval", "expr", "function", "list", "id_expr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'SKIP'", "'return'", "':='", "'true'", "'false'", "'-'", "'not'", 
		"'.size()'", "'.'", "'('", "')'", "'*'", "'/'", "'mod'", "'+'", "'<='", 
		"'>='", "'<'", "'>'", "'='", "'and'", "'or'", "'[]'", "'['", "']'", "'()'", 
		"'add'", "'insert'", "'get'", "'remove'", "'concat'", "'charAt'", "'contains'", 
		"'set'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"WS", "NUMBER", "ID", "DIAGRAM_ID", "String"
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
	public String getGrammarFileName() { return "StructoLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	    @Getter
	    @Setter
	    private ExpressionRecognizer recognizer;


	public StructoLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class EvalContext extends ParserRuleContext {
		public Expression e;
		public ExprContext expr;
		public Token ID;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(StructoLangParser.EOF, 0); }
		public TerminalNode ID() { return getToken(StructoLangParser.ID, 0); }
		public EvalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eval; }
	}

	public final EvalContext eval() throws RecognitionException {
		EvalContext _localctx = new EvalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_eval);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				((EvalContext)_localctx).expr = expr(0);
				setState(11);
				match(EOF);
				((EvalContext)_localctx).e =  ((EvalContext)_localctx).expr.e;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(14);
				match(T__0);
				setState(15);
				match(EOF);
				((EvalContext)_localctx).e =  new Expression(Object.class, () -> null, "SKIP");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(17);
				match(T__1);
				setState(18);
				((EvalContext)_localctx).expr = expr(0);
				setState(19);
				match(EOF);
				((EvalContext)_localctx).e =  recognizer.returns(((EvalContext)_localctx).expr.e);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(22);
				((EvalContext)_localctx).ID = match(ID);
				setState(23);
				match(T__2);
				setState(24);
				((EvalContext)_localctx).expr = expr(0);
				setState(25);
				match(EOF);
				((EvalContext)_localctx).e =  recognizer.assign((((EvalContext)_localctx).ID!=null?((EvalContext)_localctx).ID.getText():null), ((EvalContext)_localctx).expr.e);
				}
				break;
			}
		}
		catch (RecognitionException e) {

			    recognizer.getErrors().add("Nem felismerhető: "+e.getCtx().getText());

		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Expression e;
		public ExprContext l;
		public ExprContext a;
		public Token ID;
		public Token NUMBER;
		public Token String;
		public ExprContext expr;
		public ListContext list;
		public Token DIAGRAM_ID;
		public Token op;
		public ExprContext b;
		public FunctionContext function;
		public TerminalNode ID() { return getToken(StructoLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(StructoLangParser.NUMBER, 0); }
		public TerminalNode String() { return getToken(StructoLangParser.String, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public TerminalNode DIAGRAM_ID() { return getToken(StructoLangParser.DIAGRAM_ID, 0); }
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(31);
				((ExprContext)_localctx).ID = match(ID);
				((ExprContext)_localctx).e =  recognizer.id((((ExprContext)_localctx).ID!=null?((ExprContext)_localctx).ID.getText():null));
				}
				break;
			case 2:
				{
				setState(33);
				match(T__3);
				((ExprContext)_localctx).e =  recognizer.constant(Boolean.class, new Boolean(true));
				}
				break;
			case 3:
				{
				setState(35);
				match(T__4);
				((ExprContext)_localctx).e =  recognizer.constant(Boolean.class, new Boolean(false));
				}
				break;
			case 4:
				{
				setState(37);
				((ExprContext)_localctx).NUMBER = match(NUMBER);
				((ExprContext)_localctx).e =  recognizer.constant(Number.class, new Double((((ExprContext)_localctx).NUMBER!=null?((ExprContext)_localctx).NUMBER.getText():null)));
				}
				break;
			case 5:
				{
				setState(39);
				((ExprContext)_localctx).String = match(String);

				    String s = (((ExprContext)_localctx).String!=null?((ExprContext)_localctx).String.getText():null);
				    s = s.substring(1, s.length()-1);
				    ((ExprContext)_localctx).e =  recognizer.constant(String.class, s);
				    
				}
				break;
			case 6:
				{
				setState(41);
				match(T__5);
				setState(42);
				((ExprContext)_localctx).expr = expr(15);
				((ExprContext)_localctx).e =  recognizer.minus(((ExprContext)_localctx).expr.e);
				}
				break;
			case 7:
				{
				setState(45);
				match(T__6);
				setState(46);
				((ExprContext)_localctx).expr = expr(14);
				((ExprContext)_localctx).e =  recognizer.not(((ExprContext)_localctx).expr.e);
				}
				break;
			case 8:
				{
				setState(49);
				match(T__9);
				setState(50);
				((ExprContext)_localctx).expr = expr(0);
				setState(51);
				match(T__10);
				((ExprContext)_localctx).e =  ((ExprContext)_localctx).expr.e;
				}
				break;
			case 9:
				{
				setState(54);
				match(T__22);
				((ExprContext)_localctx).e =  recognizer.list();
				}
				break;
			case 10:
				{
				setState(56);
				match(T__23);
				setState(57);
				((ExprContext)_localctx).list = list();
				setState(58);
				match(T__24);

				    Expression list = recognizer.list();
				    for(Expression exp : ((ExprContext)_localctx).list.l) {
				        list = recognizer.append(list, exp);
				    }
				    ((ExprContext)_localctx).e =  list;
				    
				}
				break;
			case 11:
				{
				setState(61);
				((ExprContext)_localctx).DIAGRAM_ID = match(DIAGRAM_ID);
				setState(62);
				match(T__9);
				setState(63);
				((ExprContext)_localctx).list = list();
				setState(64);
				match(T__10);
				((ExprContext)_localctx).e =  recognizer.call((((ExprContext)_localctx).DIAGRAM_ID!=null?((ExprContext)_localctx).DIAGRAM_ID.getText():null), ((ExprContext)_localctx).list.l);
				}
				break;
			case 12:
				{
				setState(67);
				((ExprContext)_localctx).DIAGRAM_ID = match(DIAGRAM_ID);
				setState(68);
				match(T__25);
				((ExprContext)_localctx).e =  recognizer.call((((ExprContext)_localctx).DIAGRAM_ID!=null?((ExprContext)_localctx).DIAGRAM_ID.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(115);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(113);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(72);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(73);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(74);
						((ExprContext)_localctx).b = ((ExprContext)_localctx).expr = expr(12);

						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("*"))     {((ExprContext)_localctx).e =  recognizer.mul(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("/"))     {((ExprContext)_localctx).e =  recognizer.div(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("mod"))   {((ExprContext)_localctx).e =  recognizer.mod(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(77);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(78);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__14) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(79);
						((ExprContext)_localctx).b = ((ExprContext)_localctx).expr = expr(11);

						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+"))     {((ExprContext)_localctx).e =  recognizer.add(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("-"))     {((ExprContext)_localctx).e =  recognizer.sub(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(82);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(83);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(84);
						((ExprContext)_localctx).b = ((ExprContext)_localctx).expr = expr(10);

						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<="))    {((ExprContext)_localctx).e =  recognizer.lesserThanEquals(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">="))    {((ExprContext)_localctx).e =  recognizer.greaterThanEquals(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<"))     {((ExprContext)_localctx).e =  recognizer.lesserThan(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              if((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">"))     {((ExprContext)_localctx).e =  recognizer.greaterThan(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);}
						              
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(87);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(88);
						match(T__19);
						setState(89);
						((ExprContext)_localctx).b = ((ExprContext)_localctx).expr = expr(9);
						((ExprContext)_localctx).e =  recognizer.equal(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(92);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(93);
						match(T__20);
						setState(94);
						((ExprContext)_localctx).b = ((ExprContext)_localctx).expr = expr(8);
						((ExprContext)_localctx).e =  recognizer.and(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(97);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(98);
						match(T__21);
						setState(99);
						((ExprContext)_localctx).b = ((ExprContext)_localctx).expr = expr(7);
						((ExprContext)_localctx).e =  recognizer.or(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).b.e);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(102);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(103);
						match(T__7);
						((ExprContext)_localctx).e =  recognizer.size(((ExprContext)_localctx).l.e);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(105);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(106);
						match(T__8);
						setState(107);
						((ExprContext)_localctx).function = function();
						setState(108);
						match(T__9);
						setState(109);
						((ExprContext)_localctx).list = list();
						setState(110);
						match(T__10);

						                  int t = ((ExprContext)_localctx).function.type;
						                  List<Expression> list = ((ExprContext)_localctx).list.l;
						                  String text = (((ExprContext)_localctx).a!=null?_input.getText(((ExprContext)_localctx).a.start,((ExprContext)_localctx).a.stop):null) + "." + (((ExprContext)_localctx).function!=null?_input.getText(((ExprContext)_localctx).function.start,((ExprContext)_localctx).function.stop):null) + "(" + list.toString() + ")";

						                  switch(t) {
						                      case 0:
						                          if(list.size() != 1) {
						                              recognizer.getErrors().add("1 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(List.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.append(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0));
						                          }
						                          break;
						                      case 1:
						                          if(list.size() != 2) {
						                              recognizer.getErrors().add("2 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(List.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.append(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0), ((ExprContext)_localctx).list.l.get(1));
						                          }
						                          break;
						                      case 2:
						                          if(list.size() != 1) {
						                              recognizer.getErrors().add("1 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(Object.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.get(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0));
						                          }
						                          break;
						                      case 3:
						                          if(list.size() != 1) {
						                              recognizer.getErrors().add("1 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(List.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.remove(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0));
						                          }
						                          break;
						                      case 4:
						                          if(list.size() != 1) {
						                              recognizer.getErrors().add("1 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(String.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.concat(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0));
						                          }
						                          break;
						                      case 5:
						                          if(list.size() != 1) {
						                              recognizer.getErrors().add("1 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(String.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.charAt(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0));
						                          }
						                          break;
						                      case 6:
						                          if(list.size() != 1) {
						                              recognizer.getErrors().add("1 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(Boolean.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.contains(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0));
						                          }
						                          break;
						                      case 7:
						                          if(list.size() != 2) {
						                              recognizer.getErrors().add("2 paraméter kell: " + text);
						                              ((ExprContext)_localctx).e =  recognizer.error(List.class, text);
						                          } else {
						                              ((ExprContext)_localctx).e =  recognizer.set(((ExprContext)_localctx).a.e, ((ExprContext)_localctx).list.l.get(0), ((ExprContext)_localctx).list.l.get(1));
						                          }
						                          break;
						                      default: ((ExprContext)_localctx).e =  recognizer.error(List.class, text); break;
						                  }
						              
						}
						break;
					}
					} 
				}
				setState(117);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public int type;
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_function);
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(T__26);
				((FunctionContext)_localctx).type =  0;
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				match(T__27);
				((FunctionContext)_localctx).type =  1;
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				match(T__28);
				((FunctionContext)_localctx).type =  2;
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				match(T__29);
				((FunctionContext)_localctx).type =  3;
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				match(T__30);
				((FunctionContext)_localctx).type =  4;
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 6);
				{
				setState(128);
				match(T__31);
				((FunctionContext)_localctx).type =  5;
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 7);
				{
				setState(130);
				match(T__32);
				((FunctionContext)_localctx).type =  6;
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 8);
				{
				setState(132);
				match(T__33);
				((FunctionContext)_localctx).type =  7;
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ListContext extends ParserRuleContext {
		public List<Expression> l;
		public ExprContext expr;
		public ListContext list;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_list);
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				((ListContext)_localctx).expr = expr(0);

				    ((ListContext)_localctx).l =  new ArrayList();
				    _localctx.l.add(((ListContext)_localctx).expr.e);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				((ListContext)_localctx).expr = expr(0);
				setState(140);
				match(T__34);
				setState(141);
				((ListContext)_localctx).list = list();

				    ((ListContext)_localctx).l =  new ArrayList();
				    _localctx.l.add(((ListContext)_localctx).expr.e);
				    _localctx.l.addAll(((ListContext)_localctx).list.l);
				    
				}
				break;
			}
		}
		catch (Exception e) {

			    e.printStackTrace();
			    recognizer.getErrors().add("Szintaktikus hiba");

		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_exprContext extends ParserRuleContext {
		public String s;
		public Token ID;
		public TerminalNode ID() { return getToken(StructoLangParser.ID, 0); }
		public Id_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_expr; }
	}

	public final Id_exprContext id_expr() throws RecognitionException {
		Id_exprContext _localctx = new Id_exprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_id_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			((Id_exprContext)_localctx).ID = match(ID);
			((Id_exprContext)_localctx).s =  (((Id_exprContext)_localctx).ID!=null?((Id_exprContext)_localctx).ID.getText():null);
			}
		}
		catch (Exception e) {

			    e.printStackTrace();
			    recognizer.getErrors().add("Szintaktikus hiba");

		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u0098\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\37\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3I"+
		"\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3t\n\3\f\3\16\3w\13\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0089\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5\u0093\n\5\3\6\3\6\3\6\3\6\2\3\4\7\2\4\6\b\n"+
		"\2\5\3\2\16\20\4\2\b\b\21\21\3\2\22\25\2\u00b0\2\36\3\2\2\2\4H\3\2\2\2"+
		"\6\u0088\3\2\2\2\b\u0092\3\2\2\2\n\u0094\3\2\2\2\f\r\5\4\3\2\r\16\7\2"+
		"\2\3\16\17\b\2\1\2\17\37\3\2\2\2\20\21\7\3\2\2\21\22\7\2\2\3\22\37\b\2"+
		"\1\2\23\24\7\4\2\2\24\25\5\4\3\2\25\26\7\2\2\3\26\27\b\2\1\2\27\37\3\2"+
		"\2\2\30\31\7(\2\2\31\32\7\5\2\2\32\33\5\4\3\2\33\34\7\2\2\3\34\35\b\2"+
		"\1\2\35\37\3\2\2\2\36\f\3\2\2\2\36\20\3\2\2\2\36\23\3\2\2\2\36\30\3\2"+
		"\2\2\37\3\3\2\2\2 !\b\3\1\2!\"\7(\2\2\"I\b\3\1\2#$\7\6\2\2$I\b\3\1\2%"+
		"&\7\7\2\2&I\b\3\1\2\'(\7\'\2\2(I\b\3\1\2)*\7*\2\2*I\b\3\1\2+,\7\b\2\2"+
		",-\5\4\3\21-.\b\3\1\2.I\3\2\2\2/\60\7\t\2\2\60\61\5\4\3\20\61\62\b\3\1"+
		"\2\62I\3\2\2\2\63\64\7\f\2\2\64\65\5\4\3\2\65\66\7\r\2\2\66\67\b\3\1\2"+
		"\67I\3\2\2\289\7\31\2\29I\b\3\1\2:;\7\32\2\2;<\5\b\5\2<=\7\33\2\2=>\b"+
		"\3\1\2>I\3\2\2\2?@\7)\2\2@A\7\f\2\2AB\5\b\5\2BC\7\r\2\2CD\b\3\1\2DI\3"+
		"\2\2\2EF\7)\2\2FG\7\34\2\2GI\b\3\1\2H \3\2\2\2H#\3\2\2\2H%\3\2\2\2H\'"+
		"\3\2\2\2H)\3\2\2\2H+\3\2\2\2H/\3\2\2\2H\63\3\2\2\2H8\3\2\2\2H:\3\2\2\2"+
		"H?\3\2\2\2HE\3\2\2\2Iu\3\2\2\2JK\f\r\2\2KL\t\2\2\2LM\5\4\3\16MN\b\3\1"+
		"\2Nt\3\2\2\2OP\f\f\2\2PQ\t\3\2\2QR\5\4\3\rRS\b\3\1\2St\3\2\2\2TU\f\13"+
		"\2\2UV\t\4\2\2VW\5\4\3\fWX\b\3\1\2Xt\3\2\2\2YZ\f\n\2\2Z[\7\26\2\2[\\\5"+
		"\4\3\13\\]\b\3\1\2]t\3\2\2\2^_\f\t\2\2_`\7\27\2\2`a\5\4\3\nab\b\3\1\2"+
		"bt\3\2\2\2cd\f\b\2\2de\7\30\2\2ef\5\4\3\tfg\b\3\1\2gt\3\2\2\2hi\f\17\2"+
		"\2ij\7\n\2\2jt\b\3\1\2kl\f\16\2\2lm\7\13\2\2mn\5\6\4\2no\7\f\2\2op\5\b"+
		"\5\2pq\7\r\2\2qr\b\3\1\2rt\3\2\2\2sJ\3\2\2\2sO\3\2\2\2sT\3\2\2\2sY\3\2"+
		"\2\2s^\3\2\2\2sc\3\2\2\2sh\3\2\2\2sk\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2"+
		"\2\2v\5\3\2\2\2wu\3\2\2\2xy\7\35\2\2y\u0089\b\4\1\2z{\7\36\2\2{\u0089"+
		"\b\4\1\2|}\7\37\2\2}\u0089\b\4\1\2~\177\7 \2\2\177\u0089\b\4\1\2\u0080"+
		"\u0081\7!\2\2\u0081\u0089\b\4\1\2\u0082\u0083\7\"\2\2\u0083\u0089\b\4"+
		"\1\2\u0084\u0085\7#\2\2\u0085\u0089\b\4\1\2\u0086\u0087\7$\2\2\u0087\u0089"+
		"\b\4\1\2\u0088x\3\2\2\2\u0088z\3\2\2\2\u0088|\3\2\2\2\u0088~\3\2\2\2\u0088"+
		"\u0080\3\2\2\2\u0088\u0082\3\2\2\2\u0088\u0084\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0089\7\3\2\2\2\u008a\u008b\5\4\3\2\u008b\u008c\b\5\1\2\u008c\u0093"+
		"\3\2\2\2\u008d\u008e\5\4\3\2\u008e\u008f\7%\2\2\u008f\u0090\5\b\5\2\u0090"+
		"\u0091\b\5\1\2\u0091\u0093\3\2\2\2\u0092\u008a\3\2\2\2\u0092\u008d\3\2"+
		"\2\2\u0093\t\3\2\2\2\u0094\u0095\7(\2\2\u0095\u0096\b\6\1\2\u0096\13\3"+
		"\2\2\2\b\36Hsu\u0088\u0092";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from /home/peter/Asztal/Strucker/src/main/java/hu/elte/interpreter/parser/StruckerLang.g4 by ANTLR 4.7
package hu.elte.interpreter.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StruckerLangParser}.
 */
public interface StruckerLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StruckerLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(StruckerLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StruckerLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(StruckerLangParser.StatementContext ctx);
}
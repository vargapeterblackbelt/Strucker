package hu.elte.strucker.recognizer;

import java.util.List;

public interface StructoLangOperations {
    Expression add(Expression a, Expression b);
    Expression sub(Expression a, Expression b);
    Expression mul(Expression a, Expression b);
    Expression div(Expression a, Expression b);
    Expression mod(Expression a, Expression b);
    Expression minus(Expression a);
    Expression not(Expression a);
    Expression and(Expression a, Expression b);
    Expression or(Expression a, Expression b);
    Expression equal(Expression a, Expression b);
    Expression lesserThan(Expression a, Expression b);
    Expression lesserThanEquals(Expression a, Expression b);
    Expression greaterThan(Expression a, Expression b);
    Expression greaterThanEquals(Expression a, Expression b);
    Expression constant(Class type, Object value);
    Expression concat(Expression string1, Expression string2);
    Expression charAt(Expression string, Expression index);
    Expression list();
    Expression get(Expression set, Expression index);
    Expression contains(Expression collection, Expression object);
    Expression append(Expression collection, Expression object);
    Expression append(Expression list, Expression index, Expression object);
    Expression remove(Expression list, Expression index);
    Expression set(Expression list, Expression index, Expression object);
    Expression assign(String id, Expression value);
    Expression size(Expression list);
    Expression id(String id);
    Expression returns(Expression expression);
    Expression call(String diagramId, List<Expression> parameters);
    Expression call(String diagramId);
}

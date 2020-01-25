package hu.elte.strucker.recognizer;

import hu.elte.strucker.model.interpretation.Parameter;
import hu.elte.strucker.model.diagram.Diagram;
import lombok.Getter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ExpressionRecognizer implements StructoLangOperations {
    private Map<String, Identifier> scope;
    private List<String> errors = new ArrayList<>();

    public ExpressionRecognizer(Map<String, Identifier> scope) {
        this.scope = scope;
    }

    @Override
    public Expression add(Expression a, Expression b) {
        String text = a.getText() + "+" + b.getText();
        if (!a.hasType(Number.class) || !b.hasType(Number.class)) {
            if (!a.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + a.getText());
            if (!b.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + b.getText());
            return new Expression(Number.class, () -> null, text);
        } else {
            return new Expression(Number.class, () -> a.eval(Number.class).doubleValue() + b.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression sub(Expression a, Expression b) {
        String text = a.getText() + "-" + b.getText();
        if (!a.hasType(Number.class) || !b.hasType(Number.class)) {
            if (!a.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + a.getText());
            if (!b.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + b.getText());
            return new Expression(Number.class, () -> null, text);
        } else {
            return new Expression(Number.class, () -> a.eval(Number.class).doubleValue() - b.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression mul(Expression a, Expression b) {
        String text = a.getText() + "*" + b.getText();
        if (!a.hasType(Number.class) || !b.hasType(Number.class)) {
            if (!a.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + a.getText());
            if (!b.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + b.getText());
            return new Expression(Number.class, () -> null, text);
        } else {
            return new Expression(Number.class, () -> a.eval(Number.class).doubleValue() * b.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression div(Expression a, Expression b) {
        String text = a.getText() + "/" + b.getText();
        if (!a.hasType(Number.class) || !b.hasType(Number.class)) {
            if (!a.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + a.getText());
            if (!b.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + b.getText());
            return new Expression(Number.class, () -> null, text);
        } else {
            return new Expression(Number.class, () -> {
                if (b.eval(Number.class).doubleValue() == 0)
                    throw new ExecuteException("Nullával való osztást nem értelmezzük", b);
                return a.eval(Number.class).doubleValue() / b.eval(Number.class).doubleValue();
            }, text);
        }
    }

    @Override
    public Expression mod(Expression a, Expression b) {
        String text = a.getText() + " mod " + b.getText();
        if (!a.hasType(Number.class) || !b.hasType(Number.class)) {
            if (!a.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + a.getText());
            if (!b.hasType(Number.class))
                errors.add("Nem számértékű kifejezés: " + b.getText());
            return new Expression(Number.class, () -> null, text);
        } else {
            return new Expression(Number.class, () -> {
                int A = a.eval(Number.class).intValue();
                int B = b.eval(Number.class).intValue();
                return A % B;
            }, text);
        }
    }

    @Override
    public Expression minus(Expression a) {
        String text = "-" + a.getText();
        if (!a.hasType(Number.class)) {
            errors.add("Nem szám értékű kifejezés: " + a.getText());
            return new Expression(Number.class, () -> null, text);
        } else {
            return new Expression(Number.class, () -> -1 * a.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression not(Expression a) {
        String text = "not " + a.getText();
        if (!a.hasType(Boolean.class)) {
            errors.add("Nem logikai kifejezés: " + a.getText());
            return new Expression(Boolean.class, () -> null, text);
        } else {
            return new Expression(Boolean.class, () -> !a.eval(Boolean.class), text);
        }
    }

    @Override
    public Expression and(Expression a, Expression b) {
        String text = a.getText() + " and " + b.getText();
        boolean aOk = a.hasType(Boolean.class);
        boolean bOk = b.hasType(Boolean.class);
        if (!aOk || !bOk) {
            if (!aOk) errors.add("Nem logikai kifejezés: " + a.getText());
            if (!bOk) errors.add("Nem logikai kifejezés: " + b.getText());
            return new Expression(Boolean.class, () -> null, text);
        } else {
            return new Expression(Boolean.class, () -> a.eval(Boolean.class) && b.eval(Boolean.class), text);
        }
    }

    @Override
    public Expression or(Expression a, Expression b) {
        String text = a.getText() + " or " + b.getText();
        boolean aOk = a.hasType(Boolean.class);
        boolean bOk = b.hasType(Boolean.class);
        if (!aOk || !bOk) {
            if (!aOk) errors.add("Nem logikai kifejezés: " + a.getText());
            if (!bOk) errors.add("Nem logikai kifejezés: " + b.getText());
            return new Expression(Boolean.class, () -> null, text);
        } else {
            return new Expression(Boolean.class, () -> a.eval(Boolean.class) || b.eval(Boolean.class), text);
        }
    }

    @Override
    public Expression equal(Expression a, Expression b) {
        String text = a.getText() + " = " + b.getText();
        if (!a.hasType(b.getType())) {
            return new Expression(Boolean.class, () -> false, text);
        } else {
            Operation operation = () -> null;
            if(a.hasType(Number.class)) {
                operation = () -> a.eval(Number.class).doubleValue() == b.eval(Number.class).doubleValue();
            } else {
                if (a.hasType(Boolean.class)) {
                    operation = () -> a.eval(Boolean.class).equals(b.eval(Boolean.class));
                } else {
                    if (a.hasType(String.class)) {
                        operation = () -> a.eval(String.class).equals(b.eval(String.class));
                    } else {
                        if (a.hasType(List.class)) {
                            operation = () -> a.eval(List.class).equals(b.eval(List.class));
                        }
                    }
                }
            }
            return new Expression(Boolean.class, operation, text);
        }
    }

    @Override
    public Expression lesserThan(Expression a, Expression b) {
        String text = a.getText() + " < " + b.getText();
        boolean aOk = a.hasType(Number.class);
        boolean bOk = b.hasType(Number.class);
        if (!aOk || !bOk) {
            if (!aOk) errors.add("Nem szám értékű kifejezés: " + a.getText());
            if (!bOk) errors.add("Nem szám értékű kifejezés: " + b.getText());
            return new Expression(Boolean.class, () -> null, text);
        } else {
            return new Expression(Boolean.class, () -> a.eval(Number.class).doubleValue() < b.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression lesserThanEquals(Expression a, Expression b) {
        String text = a.getText() + " <= " + b.getText();
        boolean aOk = a.hasType(Number.class);
        boolean bOk = b.hasType(Number.class);
        if (!aOk || !bOk) {
            if (!aOk) errors.add("Nem szám értékű kifejezés: " + a.getText());
            if (!bOk) errors.add("Nem szám értékű kifejezés: " + b.getText());
            return new Expression(Boolean.class, () -> null, text);
        } else {
            return new Expression(Boolean.class, () -> a.eval(Number.class).doubleValue() <= b.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression greaterThan(Expression a, Expression b) {
        String text = a.getText() + " > " + b.getText();
        boolean aOk = a.hasType(Number.class);
        boolean bOk = b.hasType(Number.class);
        if (!aOk || !bOk) {
            if (!aOk) errors.add("Nem szám értékű kifejezés: " + a.getText());
            if (!bOk) errors.add("Nem szám értékű kifejezés: " + b.getText());
            return new Expression(Boolean.class, () -> null, text);
        } else {
            return new Expression(Boolean.class, () -> a.eval(Number.class).doubleValue() > b.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression greaterThanEquals(Expression a, Expression b) {
        String text = a.getText() + " >= " + b.getText();
        boolean aOk = a.hasType(Number.class);
        boolean bOk = b.hasType(Number.class);
        if (!aOk || !bOk) {
            if (!aOk) errors.add("Nem szám értékű kifejezés: " + a.getText());
            if (!bOk) errors.add("Nem szám értékű kifejezés: " + b.getText());
            return new Expression(Boolean.class, () -> null, text);
        } else {
            return new Expression(Boolean.class, () -> a.eval(Number.class).doubleValue() >= b.eval(Number.class).doubleValue(), text);
        }
    }

    @Override
    public Expression constant(Class type, Object value) {
        return new Expression(type, () -> value, value.toString());
    }

    @Override
    public Expression concat(Expression string1, Expression string2) {
        String text = string1.getText() + " concat " + string2.getText();
        boolean aOk = string1.hasType(String.class);
        boolean bOk = string2.hasType(String.class);
        if (!aOk || !bOk) {
            if (!aOk) errors.add("Nem string értékű kifejezés: " + string1.getText());
            if (!bOk) errors.add("Nem string értékű kifejezés: " + string2.getText());
            return new Expression(String.class, () -> null, text);
        } else {
            return new Expression(String.class, () -> string1.eval(String.class) + string2.eval(String.class), text);
        }
    }

    @Override
    public Expression charAt(Expression string, Expression index) {
        String text = string.getText() + "[" + index.getText() + "]";
        if (!string.hasType(String.class)) {
            errors.add("Nem string kifejezés: " + string.getText());
            return new Expression(String.class, () -> null, text);
        } else {
            return new Expression(String.class, () -> string.eval(String.class).charAt(index.eval(Number.class).intValue()), text);
        }
    }

    @Override
    public Expression list() {
        List list = new ArrayList();
        Expression listExpression = new Expression(List.class, () -> list, "[]");
        return listExpression;
    }

    @Override
    public Expression get(Expression list, Expression index) {
        String text = list.getText() + "[" + index.getText() + "]";
        boolean error = false;
        if (!list.hasType(List.class)) {
            errors.add("A get művelet csak listára értelmezhető: " + list.getText());
            error = true;
        }
        if (!index.hasType(Number.class)) {
            errors.add("Nem szám értékű kifejezés: " + index.getText());
            error = true;
        }
        if (error) {
            return new Expression(Object.class, () -> null, text);
        }
        return new Expression(Object.class, () -> {
            int i = index.eval(Number.class).intValue();
            List list0 = list.eval(List.class);
            if (list0.isEmpty() || i < 0 || i >= list0.size())
                throw new ExecuteException("A listának nincsen " + i + ". eleme", index);
            return list0.get(i);
        }, text);
    }

    @Override
    public Expression contains(Expression list, Expression object) {
        String text = list.getText() + ".contains(" + object.getText() + ")";
        if (!list.hasType(List.class)) {
            errors.add("A tartalmazás vizsgálata csak lista esetében van értelmezve: " + list.getText());
            return new Expression(Boolean.class, () -> null, text);
        }
        return new Expression(Boolean.class, () -> list.eval(List.class).contains(object.eval()), text);
    }

    @Override
    public Expression append(Expression list, Expression object) {
//        System.out.println("append" + (list==null?"null":list) + " " + (object==null?"null":object));
        String text = list.getText() + ".add(" + object.getText() + ")";
        if (!list.hasType(List.class)) {
            errors.add("Elemet csak listához lehet hozzáfűzni: " + list.getText());
            return new Expression(List.class, () -> null, text);
        }
        return new Expression(List.class, () -> {
            List evalList = list.eval(List.class);
            evalList.add(object.eval());
            return evalList;
        }, text);
    }

    @Override
    public Expression append(Expression list, Expression index, Expression object) {
        String text = list.getText() + ".add(" + index.getText() + ", " + object.getText() + ")";
        boolean error = false;
        if (!list.hasType(List.class)) {
            errors.add("Csak listára van értelmezve az elem beszúrása index alapján: " + list.getText());
            error = true;
        }
        if (!index.hasType(Number.class)) {
            errors.add("Nem szám értékű kifejezés: " + index.getText());
            error = true;
        }
        if (error) {
            return new Expression(List.class, () -> null, text);
        } else {
            return new Expression(List.class, () -> {
                List list0 = list.eval(List.class);
                int i = index.eval(Number.class).intValue();
                if (i < 0) list0.add(0, object.eval());
                else if (i >= list0.size()) list0.add(object.eval());
                else list0.add(i, object.eval());
                return list0;
            }, text);
        }
    }

    @Override
    public Expression remove(Expression list, Expression index) {
        String text = list.getText() + ".removeByIndex(" + index.getText() + ")";
        boolean error = false;
        if (!list.hasType(List.class)) {
            errors.add("Csak listára van értelmezve a törlés index alapján: " + list.getText());
            error = true;
        }
        if (!index.hasType(Number.class)) {
            errors.add("Nem szám értékű kifejezés: " + index.getText());
            error = true;
        }
        if (error) {
            return new Expression(List.class, () -> null, text);
        } else {
            return new Expression(List.class, () -> {
                List list0 = list.eval(List.class);
                int i = index.eval(Number.class).intValue();
                if (!list0.isEmpty() && i >= 0 && i < list0.size()) {
                    list0.remove(i);
                }
                return list0;
            }, text);
        }
    }

    @Override
    public Expression assign(String id, Expression value) {
        if (scope.containsKey(id)) {
            Identifier identifier = scope.get(id);
            if (!value.hasType(identifier.getType())) {
                errors.add("Az azonosító (" + id + ":" + identifier.getType().getSimpleName() + ") típusa nem egyezik a kifejezés típusával: "
                        + value.getText() + ":" + value.getType().getSimpleName());
                return new Expression(identifier.getType(), () -> null, id);
            } else {
                return new Expression(identifier.getType(), () -> {
                    Object eval = value.eval(identifier.getType());
                    identifier.setValue(eval);
                    return eval;
                }, id);
            }
        } else {
            Identifier identifier = new Identifier(id, value.getType(), null);
            scope.put(id, identifier);
            return new Expression(identifier.getType(), () -> {
                Object eval = value.eval(identifier.getType());
                identifier.setValue(eval);
                return eval;
            }, id);
        }
    }

    @Override
    public Expression id(String id) {
        if (!scope.containsKey(id)) {
            errors.add("Ismeretlen azonosító: " + id);
            return new Expression(Object.class, () -> null, id);
        } else {
            Identifier identifier = scope.get(id);
            return new Expression(identifier.getType(), identifier::getValue, id);
        }
    }

    @Override
    public Expression set(Expression list, Expression index, Expression object) {
        String text = list.getText() + ".set(" + index.getText() + ", " + object.getText() + ")";
        boolean error = false;
        if (!list.hasType(List.class)) {
            errors.add("Nem lista típusú kifejezés: " + list.getText());
            error = true;
        }
        if (!index.hasType(Number.class)) {
            errors.add("Nem szám értékű kifejezés: " + index.getText());
            error = true;
        }
        if (error) {
            return new Expression(List.class, () -> null, text);
        } else {
            return new Expression(List.class, () -> {
                List list0 = list.eval(List.class);
                int i = index.eval(Number.class).intValue();
                if (list0.isEmpty() || i < 0 || i >= list0.size()) {
                    throw new ExecuteException("Túlindexelés", index);
                }
                list0.set(i, object.eval());
                return list0;
            }, text);
        }
    }

    @Override
    public Expression size(Expression list) {
        String text = list.getText() + ".size()";
        if (!list.hasType(List.class)) {
            errors.add("Nem lista típusú kifejezés: " + list.getText());
            return new Expression(Number.class, () -> null, text);
        } else {
            return new Expression(Number.class, () -> list.eval(List.class).size(), text);
        }
    }

    @Override
    public Expression call(String diagramId, List<Expression> parameters) {
        Identifier identifier = scope.get(diagramId);
        String text = diagramId + "(...)";
        if (identifier == null) {
            errors.add("Nem azonosítható függvény hívás: " + text);
            return new Expression(Object.class, () -> null, text);
        } else {
            Diagram diagram = (Diagram) identifier.getValue();
            List<Parameter> diagramParameters = diagram.getParameters();
            if (diagramParameters.size() != parameters.size()) {
                errors.add("Nem egyezik meg az elvárt paraméterek száma a megadottakéval: " + text);
                return new Expression(diagram.getType(), () -> null, text);
            }
            for (int i = 0; i < parameters.size(); i++) {
                Expression paramExpr = parameters.get(i);
                Parameter parameter = diagramParameters.get(i);
                if (!paramExpr.hasType(parameter.getType())) {
                    errors.add("Nem egyezik meg az elvárt paraméter típusa a megadottéval: " + text);
                    return new Expression(diagram.getType(), () -> null, text);
                }
            }
            return new Expression(diagram.getType(), () -> diagram.eval(diagram.getType(), parameters), text);
        }
    }

    @Override
    public Expression call(String diagramId) {
        Identifier identifier = scope.get(diagramId);
        String text = diagramId + "(...)";
        if (identifier == null) {
            errors.add("Nem azonosítható függvény hívás: " + text);
            return new Expression(Object.class, () -> null, text);
        } else {
            Diagram diagram = (Diagram) identifier.getValue();
            List<Parameter> diagramParameters = diagram.getParameters();
            if (diagramParameters.size() != 0) {
                errors.add("Nem egyezik meg az elvárt paraméterek száma a megadottakéval: " + text);
                return new Expression(diagram.getType(), () -> null, text);
            }
            return new Expression(diagram.getType(), () -> diagram.eval(diagram.getType()), text);
        }
    }

    @Override
    public Expression returns(Expression expression) {
        if(!expression.hasType(scope.get("return").getType())) {
            errors.add("Nem megfelelő típus tér vissza");
            return new Expression(Object.class, () -> null, "return "+expression.getText());
        }        return new Expression(expression.getType(), () -> {
            Object eval = expression.eval(expression.getType());
            scope.get("return").setValue(eval);
            return eval;
        }, "return " + expression.getText());
    }

    public Expression parse(String expression) {
        ANTLRInputStream input = new ANTLRInputStream(expression);
        StructoLangLexer lexer = new StructoLangLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new StructoLangErrorListener(this));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        StructoLangParser parser = new StructoLangParser(tokenStream);
        parser.setRecognizer(this);
        Expression e = new Expression(Object.class, () -> null, expression);
        try {
            e = parser.eval().e;
        } catch (RecognitionException exp) {
            errors.add("Felismerhetetlen input: "+exp.getInputStream().toString());
        }
        return e;
    }

    public Expression error(Class clazz, String text) {
        return new Expression(clazz, () -> null, text);
    }

    public class StructoLangErrorListener extends BaseErrorListener {


        private ExpressionRecognizer recognizer;

        public StructoLangErrorListener(ExpressionRecognizer recognizer) {
            super();
            this.recognizer = recognizer;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
                throws ParseCancellationException {
            this.recognizer.getErrors().add(msg);
//            throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        }
    }
}

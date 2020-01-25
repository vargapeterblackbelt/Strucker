grammar StructoLang;

@header {
    import hu.elte.strucker.recognizer.ExpressionRecognizer;
    import hu.elte.strucker.recognizer.Expression;
    import lombok.*;
}

@parser::members {

    @Getter
    @Setter
    private ExpressionRecognizer recognizer;

}

eval returns[Expression e]:
    expr EOF {$e = $expr.e;}
|
    'SKIP' EOF{$e = new Expression(Object.class, () -> null, "SKIP");}
|
    'return' expr EOF{$e = recognizer.returns($expr.e);}
|
    ID ':=' expr EOF {$e = recognizer.assign($ID.text, $expr.e);}
;
catch [RecognitionException e] {
    recognizer.getErrors().add("Nem felismerhető: "+e.getCtx().getText());
}

expr returns[Expression e]:
ID                              {$e = recognizer.id($ID.text);}
| 'true'                        {$e = recognizer.constant(Boolean.class, new Boolean(true));}
| 'false'                       {$e = recognizer.constant(Boolean.class, new Boolean(false));}
| NUMBER                        {$e = recognizer.constant(Number.class, new Double($NUMBER.text));}
| String
    {
    String s = $String.text;
    s = s.substring(1, s.length()-1);
    $e = recognizer.constant(String.class, s);
    }
|'-' expr                       {$e = recognizer.minus($expr.e);}
| 'not' expr                    {$e = recognizer.not($expr.e);}
| l=expr '.size()'                          {$e = recognizer.size($l.e);}
| a=expr '.' function '(' list ')'
{
        int t = $function.type;
        List<Expression> list = $list.l;
        String text = $a.text + "." + $function.text + "(" + list.toString() + ")";

        switch(t) {
            case 0:
                if(list.size() != 1) {
                    recognizer.getErrors().add("1 paraméter kell: " + text);
                    $e = recognizer.error(List.class, text);
                } else {
                    $e = recognizer.append($a.e, $list.l.get(0));
                }
                break;
            case 1:
                if(list.size() != 2) {
                    recognizer.getErrors().add("2 paraméter kell: " + text);
                    $e = recognizer.error(List.class, text);
                } else {
                    $e = recognizer.append($a.e, $list.l.get(0), $list.l.get(1));
                }
                break;
            case 2:
                if(list.size() != 1) {
                    recognizer.getErrors().add("1 paraméter kell: " + text);
                    $e = recognizer.error(Object.class, text);
                } else {
                    $e = recognizer.get($a.e, $list.l.get(0));
                }
                break;
            case 3:
                if(list.size() != 1) {
                    recognizer.getErrors().add("1 paraméter kell: " + text);
                    $e = recognizer.error(List.class, text);
                } else {
                    $e = recognizer.remove($a.e, $list.l.get(0));
                }
                break;
            case 4:
                if(list.size() != 1) {
                    recognizer.getErrors().add("1 paraméter kell: " + text);
                    $e = recognizer.error(String.class, text);
                } else {
                    $e = recognizer.concat($a.e, $list.l.get(0));
                }
                break;
            case 5:
                if(list.size() != 1) {
                    recognizer.getErrors().add("1 paraméter kell: " + text);
                    $e = recognizer.error(String.class, text);
                } else {
                    $e = recognizer.charAt($a.e, $list.l.get(0));
                }
                break;
            case 6:
                if(list.size() != 1) {
                    recognizer.getErrors().add("1 paraméter kell: " + text);
                    $e = recognizer.error(Boolean.class, text);
                } else {
                    $e = recognizer.contains($a.e, $list.l.get(0));
                }
                break;
            case 7:
                if(list.size() != 2) {
                    recognizer.getErrors().add("2 paraméter kell: " + text);
                    $e = recognizer.error(List.class, text);
                } else {
                    $e = recognizer.set($a.e, $list.l.get(0), $list.l.get(1));
                }
                break;
            default: $e = recognizer.error(List.class, text); break;
        }
    }
| a=expr op=('*'|'/'|'mod') b=expr
    {
    if($op.text.equals("*"))     {$e = recognizer.mul($a.e, $b.e);}
    if($op.text.equals("/"))     {$e = recognizer.div($a.e, $b.e);}
    if($op.text.equals("mod"))   {$e = recognizer.mod($a.e, $b.e);}
    }
| a=expr op=('+'|'-') b=expr
    {
    if($op.text.equals("+"))     {$e = recognizer.add($a.e, $b.e);}
    if($op.text.equals("-"))     {$e = recognizer.sub($a.e, $b.e);}
    }
| a=expr op=('<='|'>='|'<'|'>') b=expr
    {
    if($op.text.equals("<="))    {$e = recognizer.lesserThanEquals($a.e, $b.e);}
    if($op.text.equals(">="))    {$e = recognizer.greaterThanEquals($a.e, $b.e);}
    if($op.text.equals("<"))     {$e = recognizer.lesserThan($a.e, $b.e);}
    if($op.text.equals(">"))     {$e = recognizer.greaterThan($a.e, $b.e);}
    }
| a=expr '=' b=expr             {$e = recognizer.equal($a.e, $b.e);}
| a=expr 'and' b=expr           {$e = recognizer.and($a.e, $b.e);}
| a=expr 'or' b=expr            {$e = recognizer.or($a.e, $b.e);}
| '(' expr ')'                  {$e = $expr.e;}
| '[]'                          {$e = recognizer.list();}
| '[' list ']'
    {
    Expression list = recognizer.list();
    for(Expression exp : $list.l) {
        list = recognizer.append(list, exp);
    }
    $e = list;
    }
| DIAGRAM_ID '(' list ')'       {$e = recognizer.call($DIAGRAM_ID.text, $list.l);}
| DIAGRAM_ID '()'               {$e = recognizer.call($DIAGRAM_ID.text);}
;
catch [Exception e] {
    e.printStackTrace();
    recognizer.getErrors().add("Szintaktikus hiba");
}

function returns[int type]:
'add'{$type = 0;}
|'insert' {$type = 1;}
|'get' {$type = 2;}
|'remove' {$type = 3;}
|'concat' {$type = 4;}
|'charAt' {$type = 5;}
|'contains' {$type = 6;}
|'set' {$type = 7;}
;

list returns[List<Expression> l]:
expr
    {
    $l = new ArrayList();
    $l.add($expr.e);
    }
|
expr ',' list

    {
    $l = new ArrayList();
    $l.add($expr.e);
    $l.addAll($list.l);
    }
;
catch [Exception e] {
    e.printStackTrace();
    recognizer.getErrors().add("Szintaktikus hiba");
}


id_expr returns[String s]:
ID  {$s = $ID.text;}
;
catch [Exception e] {
    e.printStackTrace();
    recognizer.getErrors().add("Szintaktikus hiba");
}

WS:         [ \n\t\r]+ -> skip;
/*NUMBER:     ('0' | ('-'?[1-9][0-9]*))('.'Digit+)?;*/
NUMBER:     Digit+;
ID:         Letter(Digit|Letter)*;
DIAGRAM_ID: ID'::'ID;
fragment
Letter:     [a-zA-Z];
fragment
Digit:       ('0'..'9');
fragment ESCAPED_QUOTE : '\\"';
String : '"'('\\' ["\\] | ~["\\\r\n])*'"' ;
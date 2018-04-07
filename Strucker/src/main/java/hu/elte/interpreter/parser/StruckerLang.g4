grammar StruckerLang;

fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];

NATURAL : '0' | [1-9]DIGIT*;
INTEGER : ('0') | (('-')?[1-9]DIGIT*);
RACIONAL : INTEGER'.'DIGIT+;
SET_OPEN : '{';
SET_CLOSE : '}';
SEQUENCE_OPEN : '[';
SEQUENCE_CLOSE : ']';
ASSIGN : ':=';
DEF : ':';
EQUALS : '=';
LESSER_EQUALS : '<=';
GREATER_EQUALS : '>=';
LESSER : '<';
GREATER : '>';
NOT : 'not';
AND : 'and';
OR : 'or';
IN : 'in';
PLUS : '+';
MINUS : '-';
MULTIPLY : '*';
DIVIDE : '/';
MOD : '%';
PUT : 'put';
ADD : 'add';
AT : 'at';
INTO : 'into';
GET : 'get';
REMOVE : 'remove';
FROM : 'from';
EMPTY : 'skip';
ABORT : 'abort';
STRING : '"'.*'"';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';
COMMA : ',';
ID : LETTER+(LETTER|DIGIT)*;
WS : [ \t\r\n]+ -> skip;

statement :
    BRACKET_OPEN statement BRACKET_CLOSE
    {
        System.out.println("("+$statement.text+")");
    }
|
    .*
    {
        System.out.println("Semmi");
    }
;
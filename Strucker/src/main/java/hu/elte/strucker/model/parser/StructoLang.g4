grammar StructoLang;

@lexer::header{
    package hu.elte.strucker.model.parser;

}

@parser::header{
    package hu.elte.strucker.model.parser;
}

ASSIGN:         ':=';
EQUALS:         '=';
LESSER_THEN:    '<';
GREATER_THEN:   '>';
AND:            'and';
OR:             'or';
NOT:            'not';
PLUS:           '+';
MINUS:          '-';
MULTIPLY:       '*';
DIVIDE:         '/';
MOD:            '%';
BRACKET_START:  '(';
BRACKET_END:    ')';
DOT:            '.';
COLON:          ':';
QUOTE:          '"';
LIST_START:     '[';
LIST_END:       ']';
SET_START:      '{';
SET_END:        '}';
SET:            'Set';
LIST:           'List';
NATURAL_DATA:   '0' | ([1-9][0-9]*);
INTEGER_DATA:   '0' | ('-'?[1-9][0-9]*);
RACIONAL_DATA:  INTEGER_DATA DOT Digit+;
STRING_DATA:    QUOTE[^QUOTE]QUOTE;
ID:             Letter(Letter|Digit)*;
NATURAL_TYPE:   'N';
INTEGER_TYPE:   'I';
RACIONAL_TYPE:  'R';
STRING_TYPE:    'String';

fragment
Letter:         [A-Za-z];
fragment
Digit:          [0-9];

statement:
    definition
|
    declaration
|
    expression
;

type:
    NATURAL_TYPE
    |
    INTEGER_TYPE
    |
    RACIONAL_TYPE
    |
    SET BRACKET_START TYPE BRACKET_END
    |
    LIST BRACKET_START TYPE BRACKET_END
;

declaration:
    ID type
;

definition:
    ID type ASSIGN expression
;


expression :
    NATURAL_DATA
    |
    RACIONAL_DATA
    |
    INTEGER_DATA
    |
    SET_START

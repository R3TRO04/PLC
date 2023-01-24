grammar BigCalcProg;

program
        : statement+ EOF
        ;

statement
        : expressionStatement
        ;

expressionStatement
        : assignment ';' # assign
        | expression ';' # expr
        ;

assignment
        : ID '=' expression
        ;

expression
        : '(' expression ')'                    # paren
        | expression op=('*' | '/') expression  # mulDiv
        | expression op=('+' | '-') expression  # addSub
        | ID                                    # id
        | Number                                # num
        ;

Number
        : Digit* '.' Digit+
        | Digit+
        ;

Digit
        : [0-9]
        ;


ID
        : [a-zA-Z] Digit*
        ;

WS      : [ \t\r\n\u000C]+ -> skip
        ;

COMMENT
        :   '/*' .*? '*/' -> skip
        ;

LINE_COMMENT
        : '//' ~[\r\n]* -> skip
        ;

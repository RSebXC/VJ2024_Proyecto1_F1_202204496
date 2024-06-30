package analisis;

// importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;

%%

// código de usuario
%{
    
    public LinkedList<Errores> listaErrores = new LinkedList<>();

    private char processEscapedCharacter(String charLiteral) {
        switch (charLiteral) {
            case "\\t":
                return '\t';
            case "\\n":
                return '\n';
            case "\\r":
                return '\r';
            case "\\f":
                return '\f';
            case "\\'":
                return '\'';
            case "\\\"":
                return '\"';
            case "\\\\":
                return '\\';
            default:
                throw new IllegalArgumentException("Caracter escapado no válido: " + charLiteral);
        }
    }
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

// características de JFlex
%cup
%class scanner
%public
%line
%char
%column
%full
//%debug
%ignorecase



// símbolos del sistema

POW = "**"
MAYORIGUAL = ">="
MENORIGUAL = "<="
DIF = "!="
EQUAL = "=="
OR = "||"
CORTU = "[]"
AND = "&&"
CORI = "["
CORD = "]"
XOR = "^"
INCREMENT = "++"
DECREMENT = "--"
PAR1 = "("
PAR2 = ")"

LLAVE1="{"
LLAVE2="}"
DIV = "/"
MENOR = "<"
MAYOR = ">"
MULT = "*"
MOD = "%"
MAS = "+"
COMA = ","
MENOS = "-"
IGUAL = "="
NOT = "!"
FINCADENA = ";"
DPUNTOS = ":"

BLANCOS = [\ \r\t\f\n]+
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
ID=[a-zA-z][a-zA-Z0-9_]*
CADENA = [\"]([^\"])*[\"]
CHAR_LITERAL = '\'' ( \\ [tnrf'\\] | [^\\'] ) '\''
COMENTARIO = "//"[^\n]* "\n"
COMENTARIO_MULTILINEA = "/*" [^*]* ("*" [^/][^*]* )* "*"+"/"

// palabras reservadas
IMPRIMIR = "println"
INT = "int"
DOUBLE = "double"
CHAR = "char"
STRING = "string"
IF="if"
TRUE="true"
FALSE="false"
BOOL="bool"
FOR="for"
BREAK="break"
WHILE = "while"
DOWHILE = "do"
CONTINUE = "continue"
RETURN = "return"
VAR = "var"
CONST = "const"
VOID = "void"
EXECUTE = "start_with"
LIST = "list"
NEW = "new"
APPEND = "append"
PUNTO = "."
REMOVE = "remove"


%%

<YYINITIAL> {IMPRIMIR} {return new Symbol(sym.IMPRIMIR, yyline, yycolumn, yytext());}

<YYINITIAL> {XOR} {return new Symbol(sym.XOR, yyline, yycolumn, yytext());}
<YYINITIAL> {CORD} {return new Symbol(sym.CORD, yyline, yycolumn, yytext());}
<YYINITIAL> {CORI} {return new Symbol(sym.CORI, yyline, yycolumn, yytext());}
<YYINITIAL> {INT} {return new Symbol(sym.INT, yyline, yycolumn, yytext());}
<YYINITIAL> {DOUBLE} {return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext());}
<YYINITIAL> {CHAR} {return new Symbol(sym.CHAR, yyline, yycolumn, yytext());}
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn, yytext());}
<YYINITIAL> {STRING} {return new Symbol(sym.STRING, yyline, yycolumn,yytext());}
<YYINITIAL> {TRUE} {return new Symbol(sym.TRUE, yyline, yycolumn,yytext());}
<YYINITIAL> {FALSE} {return new Symbol(sym.FALSE, yyline, yycolumn,yytext());}
<YYINITIAL> {IF} {return new Symbol(sym.IF, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOL} {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> {FOR} {return new Symbol(sym.FOR, yyline, yycolumn,yytext());}
<YYINITIAL> {BREAK} {return new Symbol(sym.BREAK, yyline, yycolumn,yytext());}
<YYINITIAL> {WHILE} {return new Symbol(sym.WHILE, yyline, yycolumn,yytext());}
<YYINITIAL> {DOWHILE} {return new Symbol(sym.DOWHILE, yyline, yycolumn,yytext());}
<YYINITIAL> {CONTINUE} {return new Symbol(sym.CONTINUE, yyline, yycolumn,yytext());}
<YYINITIAL> {VAR} {return new Symbol(sym.VAR, yyline, yycolumn,yytext());}
<YYINITIAL> {CONST} {return new Symbol(sym.CONST, yyline, yycolumn,yytext());}
<YYINITIAL> {VOID} {return new Symbol(sym.VOID, yyline, yycolumn,yytext());}
<YYINITIAL> {EXECUTE} {return new Symbol(sym.EXECUTE, yyline, yycolumn,yytext());}
<YYINITIAL> {LIST} {return new Symbol(sym.LIST, yyline, yycolumn,yytext());}
<YYINITIAL> {NEW} {return new Symbol(sym.NEW, yyline, yycolumn,yytext());}
<YYINITIAL> {APPEND} {return new Symbol(sym.APPEND, yyline, yycolumn,yytext());}
<YYINITIAL> {REMOVE} {return new Symbol(sym.REMOVE, yyline, yycolumn,yytext());}
<YYINITIAL> {RETURN} {return new Symbol(sym.RETURN, yyline, yycolumn,yytext());}
<YYINITIAL> {ID} {
return new Symbol(sym.ID, yyline, yycolumn,yytext());}


<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn, yytext());}
<YYINITIAL> {PUNTO} {return new Symbol(sym.PUNTO, yyline, yycolumn, yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn, yytext());}
<YYINITIAL> {INCREMENT} {return new Symbol(sym.INCREMENT, yyline, yycolumn, yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn, yytext());}
<YYINITIAL> {LLAVE1} {return new Symbol(sym.LLAVE1, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE2} {return new Symbol(sym.LLAVE2, yyline, yycolumn,yytext());}
<YYINITIAL> {DIV} {return new Symbol(sym.DIV, yyline, yycolumn, yytext());}
<YYINITIAL> {MULT} {return new Symbol(sym.MULT, yyline, yycolumn, yytext());}
<YYINITIAL> {MOD} {return new Symbol(sym.MOD, yyline, yycolumn, yytext());}
<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn, yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn, yytext());}
<YYINITIAL> {DPUNTOS} {return new Symbol(sym.DPUNTOS, yyline, yycolumn, yytext());}
<YYINITIAL> {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn, yytext());}
<YYINITIAL> {DIF} {return new Symbol(sym.DIF, yyline, yycolumn, yytext());}
<YYINITIAL> {MENOR} {return new Symbol(sym.MENOR, yyline, yycolumn, yytext());}
<YYINITIAL> {MAYOR} {return new Symbol(sym.MAYOR, yyline, yycolumn, yytext());}
<YYINITIAL> {OR} {return new Symbol(sym.OR, yyline, yycolumn, yytext());}
<YYINITIAL> {DECREMENT} {return new Symbol(sym.DECREMENT, yyline, yycolumn, yytext());}
<YYINITIAL> {AND} {return new Symbol(sym.AND, yyline, yycolumn, yytext());}
<YYINITIAL> {NOT} {return new Symbol(sym.NOT, yyline, yycolumn, yytext());}
<YYINITIAL> {COMA} {return new Symbol(sym.COMA, yyline, yycolumn, yytext());}
<YYINITIAL> {CORTU} {return new Symbol(sym.CORTU, yyline, yycolumn, yytext());}
<YYINITIAL> {EQUAL} {return new Symbol(sym.EQUALS, yyline, yycolumn, yytext());}
<YYINITIAL> {MAYORIGUAL} {return new Symbol(sym.MAYORIGUAL, yyline, yycolumn, yytext());}
<YYINITIAL> {MENORIGUAL} {return new Symbol(sym.MENORIGUAL, yyline, yycolumn, yytext());}
<YYINITIAL> {POW} {return new Symbol(sym.POW, yyline, yycolumn, yytext());}
{COMENTARIO}   { /* Ignora el comentario de una línea */ }
{COMENTARIO_MULTILINEA}   { /* Ignora el comentario multilinea */}

<YYINITIAL> {CHAR_LITERAL} {
    String charLiteral = yytext();
    charLiteral = charLiteral.substring(1, charLiteral.length() - 1); // Remover comillas simples
    if (charLiteral.length() == 1) {
        return new Symbol(sym.CHAR_LITERAL, yyline, yycolumn, charLiteral.charAt(0));
    } else {
        // Procesar caracteres escapados
        char processedChar = processEscapedCharacter(charLiteral);
        return new Symbol(sym.CHAR_LITERAL, yyline, yycolumn, processedChar);
    }
}

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }

<YYINITIAL> {BLANCOS} {}

<YYINITIAL> . {
    listaErrores.add(new Errores("LEXICO", "El caracter " +
        yytext() + " NO pertenece al lenguaje", yyline, yycolumn));
}

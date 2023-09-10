// la gramática que va a reconocer es la siguiente
// 1.<S>      --> <E> {Resultado}             Selección(1)={(,I} 
// 2.<E>      --> +<E><E>{suma}               Selección(2)={(,I}
// 3.<E>    -->   *<E><E>{mult}               Selección(3)={+}
// 4.<E>    -->   I                           Selección (4)= I
package apcompiladorpilaatributos;

public class ApCompiladorPilaAtributos {

    static Lexico lexico = new Lexico();
    static String cad2 = "¬";
    public static int contadorSuma = 0;
    //  static String cad="((((5+3)))+5+3+2)-1¬";
    static String cad1 = "0123456789.";
    static int elementoCabeza = 0;
    // ultimo ejemplo de una cadena ((5,5/3)+10)>20 & 20.5<10 
    
    static String cad = "((5.5+4.5)*2/2)^2>120+1¬";
    // ((5.5+4.5)*2/2)¬
    // (5.5+4.5)*2
    // 4<7
    static int indice = 0;
    static char sim = ' ';
    static Lexico lex1 = new Lexico();
    static String cadavance = "";
    static Analizador analizador = new Analizador();
    static int contadorRecorrido = 0;

    public static void main(String[] args) {

        analisisLexico();
        cad = lex1.cadenaLexico();
        sim = lex1.darElemento(indice).darTipo();
        cadavance = cadavance + sim;
        // crea los nodos para determinar el fondo de pila con v y el axioma
        // o no terminal inicial s

        NoTerminal v = new NoTerminal("v", 0, 0);
        NoTerminal s = new NoTerminal("s", 0, 0);
        // adiciona a la pila el fondo v y el axiona s. (en la clase analizador se 
        // tiene un arrayList para controlar la pila

        analizador.adicionarNodo(v);
        analizador.adicionarNodo(s);
        // analizador.mostrarCadenaPila();
        NoTerminal nt = null;

        // Realiza un ciclo do-while para controlar la cadena y realiza dos switches para
        // controlar la tabla de control, el primero para evaluar la entrada y el
        // segundo para evaluar los símbolos de pila
        do {
            switch (sim) {
                // los símbolos de entrada son i,+,*
                case 'i':
                    // en nt se almacena el nodo ultimo de la pila
                    nt = analizador.getnode(analizador.ultimo());
                    // los símbolos de pila son s,e,el,t,tl,p,pl,f,)
                    switch (nt.getNombre()) {
                        case "s":
                            proceso1();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ELO":
                            proceso2();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL2":
                            proceso5();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ER":
                            proceso8();
                            analizador.mostrarCadenaPila();
                            break;
                        case "E":
                            proceso21();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ME":
                            proceso13();
                            analizador.mostrarCadenaPila();
                            break;
                        case "MA":
                            proceso16();
                            analizador.mostrarCadenaPila();
                            break;
                        case "T":
                            proceso25();
                            analizador.mostrarCadenaPila();
                            break;
                        case "P":
                            proceso29();
                            analizador.mostrarCadenaPila();
                            break;
                        case "F":
                            proceso33();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                    }
                    break;
                case '+':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "EL":
                            proceso22();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            analizador.mostrarCadenaPila();
                            break;
                        case "PL":
                            proceso31();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '-':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "EL":
                            proceso23();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            analizador.mostrarCadenaPila();
                            break;
                        case "PL":
                            proceso31();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '*':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "TL":
                            proceso26();
                            analizador.mostrarCadenaPila();
                            break;
                        case "PL":
                            proceso31();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '/':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "TL":
                            proceso27();
                            analizador.mostrarCadenaPila();
                            break;
                        case "PL":
                            proceso31();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '^':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "PL":
                            proceso30();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '<':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "ERL":
                            proceso9();
                            analizador.mostrarCadenaPila();
                            break;
                        case "OR":
                            proceso11();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '>':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "ERL":
                            proceso9();
                            analizador.mostrarCadenaPila();
                            break;
                        case "OR":
                            proceso14();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '=':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "ERL":
                            proceso9();
                            analizador.mostrarCadenaPila();
                            break;
                        case "OR":
                            proceso11();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ME":
                            proceso12();
                            analizador.mostrarCadenaPila();
                            break;
                        case "MA":
                            proceso15();
                            analizador.mostrarCadenaPila();
                            break;
                        case "IG":
                            proceso18();
                            analizador.mostrarCadenaPila();
                            break;
                        case "DI":
                            proceso20();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '!':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "ERL":
                            proceso9();
                            analizador.mostrarCadenaPila();
                            break;
                        case "OR":
                            proceso19();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '|':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "ELOL":
                            proceso3();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL2L":
                            proceso7();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ERL":
                            proceso10();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '&':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "EL2L":
                            proceso6();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ERL":
                            proceso10();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '(':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "s":
                            proceso1();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ELO":
                            proceso2();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL2":
                            proceso5();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ER":
                            proceso8();
                            analizador.mostrarCadenaPila();
                            break;
                        case "E":
                            proceso21();
                            break;
                        case "ME":
                            proceso13();
                            analizador.mostrarCadenaPila();
                            break;
                        case "MA":
                            proceso16();
                            analizador.mostrarCadenaPila();
                            break;
                        case "T":
                            proceso25();
                            analizador.mostrarCadenaPila();
                            break;
                        case "P":
                            proceso29();
                            analizador.mostrarCadenaPila();
                            break;
                        case "F":
                            proceso32();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            System.out.println("se rechza por aqui");
                            rechace();
                        //break;
                    }
                    break;
                case ')':
                    
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "ELOL":
                            proceso4();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL2L":
                            proceso7();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ERL":
                            proceso10();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;
                        case "res":
                            resultado();
                            analizador.mostrarCadenaPila();
                            break;
                        case ")":
                            int u = analizador.ultimo();
                            analizador.removeNodo(u);
                            avance();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case '¬':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "ELOL":
                            proceso4();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL2L":
                            proceso7();
                            analizador.mostrarCadenaPila();
                            break;
                        case "ERL":
                            proceso10();
                            analizador.mostrarCadenaPila();
                            break;
                        case "EL":
                            proceso24();
                            analizador.mostrarCadenaPila();
                            break;
                        case "TL":
                            proceso28();
                            break;
                        case "PL":
                            proceso31();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pOR":
                            pOR();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pAND":
                            pAND();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pRelacional":
                            pRelacional();
                            analizador.mostrarCadenaPila();
                            break;
                        case "pComparar":
                            pComparar();
                            analizador.mostrarCadenaPila();
                            break;
                        case "suma":
                            suma();
                            analizador.mostrarCadenaPila();
                            break;
                        case "resta":
                            resta();
                            analizador.mostrarCadenaPila();
                            break;
                        case "mult":
                            mult();
                            analizador.mostrarCadenaPila();
                            break;
                        case "div":
                            div();
                            analizador.mostrarCadenaPila();
                            break;
                        case "exp":
                            exp();
                            analizador.mostrarCadenaPila();
                            break;
                        case "res":
                            resultado();
                            analizador.mostrarCadenaPila();
                            break;

                        default:
                            rechace();
                        //break;
                    }
                    break;
                case 'f':
                    nt = analizador.getnode(analizador.ultimo());
                    switch (nt.getNombre()) {
                        case "sum":
                            suma();
                            break;
                        case "mul":
                            mult();
                            break;
                        case "res":
                            resultado();
                            break;
                    }
                    break;
                default:
                    rechace();
            }
            nt = analizador.getnode(analizador.ultimo());
        } while (!nt.getNombre().equalsIgnoreCase("v"));

        if (nt.getNombre().equalsIgnoreCase("v")) {
            System.out.println("Hay " + contadorSuma + " sumas");
            System.out.println("Se acepta la secuencia ");
        } else {
            System.out.println("Se rechaza la secuencia ");
        }

    }

    public static void proceso1() {
        int u = analizador.ultimo();

        NoTerminal i1 = new NoTerminal("i1", 0, 0);
        NoTerminal res = new NoTerminal("res", 0, 0);
        NoTerminal s1 = new NoTerminal("s1", u, 0);
        NoTerminal ELO = new NoTerminal("ELO", 0, 0);
        analizador.removeNodo(u);
        analizador.adicionarNodo(i1);
        analizador.adicionarNodo(res);
        analizador.adicionarNodo(s1);
        analizador.adicionarNodo(ELO);
    }

    public static void proceso2() {
        NoTerminal nt;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 1);

        NoTerminal s3 = new NoTerminal("s3", nt.getDirec(), 0);
        NoTerminal i1 = new NoTerminal("i1", 0, 0);
        NoTerminal ELOL = new NoTerminal("ELOL", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u, 0);
        NoTerminal EL2 = new NoTerminal("EL2", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i1);
        analizador.adicionarNodo(ELOL);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(EL2);
    }

    public static void proceso3() {
        NoTerminal nt;
        NoTerminal nt2;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);

        NoTerminal s5 = new NoTerminal("s5", nt2.getDirec(), 0);
        NoTerminal i6 = new NoTerminal("i6", 0, 0);
        NoTerminal ELOL = new NoTerminal("ELOL", 0, 0);
        NoTerminal s4 = new NoTerminal("s4", u - 1, 0);
        NoTerminal i5 = new NoTerminal("i5", 0, 0);
        NoTerminal i4 = new NoTerminal("i4", 0, nt.getValor());
        i4.setValor(nt.getValor());
        i4.setValorLogico(nt.getValorLogico());
        i4.setRelacional(true);
        NoTerminal pOR = new NoTerminal("pOR", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u + 2, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt.getValor());
        i2.setValor(nt.getValor());
        i2.setValorLogico(nt.getValorLogico());
        i2.setRelacional(true);
        NoTerminal pRelacional = new NoTerminal("pRelacional", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u + +6, 0);
        NoTerminal EL2 = new NoTerminal("EL2", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s5);
        analizador.adicionarNodo(i6);
        analizador.adicionarNodo(ELOL);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i5);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(pOR);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(pRelacional);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(EL2);
        avance();
    }

    public static void proceso4() {
        NoTerminal nt, nt1;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 2);
        nt1 = analizador.getnode(nt.getDirec());
        nt1.setValor(analizador.getnode(u - 1).getValor());
        nt1.setValor(analizador.getnode(u - 1).getValor());
        nt1.setValorLogico(analizador.getnode(u - 1).getValorLogico());
        nt1.setRelacional(true);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
    }

    public static void proceso5() {
        int u = analizador.ultimo();
        NoTerminal nt = analizador.getnode(u - 1);

        NoTerminal s3 = new NoTerminal("s3", nt.getDirec(), 0);
        NoTerminal i1 = new NoTerminal("i1", 0, 0);
        NoTerminal EL2L = new NoTerminal("EL2L", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u, 0);
        NoTerminal ER = new NoTerminal("ER", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i1);
        analizador.adicionarNodo(EL2L);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(ER);
    }

    public static void proceso6() {
        int u = analizador.ultimo();

        NoTerminal nt1, nt2;

        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);

        if (nt1.getRelacional()) {

        }

        NoTerminal s5 = new NoTerminal("s5", nt2.getDirec(), 0);
        NoTerminal i6 = new NoTerminal("i6", 0, 0);
        NoTerminal EL2L = new NoTerminal("EL2L", 0, 0);
        NoTerminal s4 = new NoTerminal("s4", u - 1, 0);
        NoTerminal i5 = new NoTerminal("i5", 0, 0);
        NoTerminal i4 = new NoTerminal("i4", 0, nt1.getValor());
        i4.setValor(nt1.getValor());
        i4.setValorLogico(nt1.getValorLogico());
        i4.setRelacional(true);
        NoTerminal pAND = new NoTerminal("pAND", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u + 2, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt1.getValor());
        i2.setValor(nt1.getValor());
        i2.setValorLogico(nt1.getValorLogico());
        i2.setRelacional(true);
        NoTerminal pRelacional = new NoTerminal("pRelacional", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u + 6, 0);
        NoTerminal ER = new NoTerminal("ER", 0, 0);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s5);
        analizador.adicionarNodo(i6);
        analizador.adicionarNodo(EL2L);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i5);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(pAND);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(pRelacional);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(ER);
        avance();
    }

    public static void proceso7() {
        NoTerminal nt, nt1, nt2;
        int u = analizador.ultimo();
        nt2 = analizador.getnode(u - 1);
        nt = analizador.getnode(u - 2);
        nt1 = analizador.getnode(nt.getDirec());
        
        if (nt2.getRelacional()) {
            nt1.setValor(nt2.getValor());
            nt1.setValorLogico(nt2.getValorLogico());
            nt1.setRelacional(true);
        } else {
            nt1.setValor(nt2.getValor());
            nt1.setValorLogico(nt2.getValorLogico());
            nt1.setRelacional(false);
        }

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
    }

    public static void proceso8() {
        NoTerminal nt;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 1);

        NoTerminal s3 = new NoTerminal("s3", nt.getDirec(), 0);
        NoTerminal i1 = new NoTerminal("i1", 0, 0);
        NoTerminal ERL = new NoTerminal("ERL", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u, 0);
        NoTerminal E = new NoTerminal("E", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i1);
        analizador.adicionarNodo(ERL);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(E);
    }

    public static void proceso9() {
        int u = analizador.ultimo();

        NoTerminal nt1, nt2;

        nt1 = analizador.getnode(u - 2);
        nt2 = analizador.getnode(u - 1);

        NoTerminal s4 = new NoTerminal("s4", nt1.getDirec(), 0);
        NoTerminal i4 = new NoTerminal("i4", 0, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt2.getValor());
        NoTerminal pComparar = new NoTerminal("pComparar", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u, 0);
        NoTerminal E = new NoTerminal("E", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u - 1, 0);
        NoTerminal OR = new NoTerminal("OR", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(pComparar);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(E);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(OR);
    }

    public static void proceso10() {
        NoTerminal nt, nt1;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 2);
        nt1 = analizador.getnode(nt.getDirec());
        nt1.setValor(analizador.getnode(u - 1).getValor());
        nt1.setValorLogico(analizador.getnode(u - 1).getValorLogico());
        nt1.setRelacional(analizador.getnode(u - 1).getRelacional());
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
    }

    public static void proceso11() {
        int u = analizador.ultimo();
        NoTerminal nt1 = analizador.getnode(u - 1);

        NoTerminal s2 = new NoTerminal("s2", nt1.getDirec(), 0);
        NoTerminal ME = new NoTerminal("ME", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(ME);
        avance();
    }

    public static void proceso12() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(nt1.getDirec());
        //si es 1: "<="
        nt2.setValor(1);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        avance();
    }

    public static void proceso13() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(nt1.getDirec());
        //si es 2: "<"
        nt2.setValor(2);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
    }

    public static void proceso14() {
        int u = analizador.ultimo();

        NoTerminal nt = analizador.getnode(u - 1);

        NoTerminal s2 = new NoTerminal("s2", nt.getDirec(), 0);
        NoTerminal MA = new NoTerminal("MA", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(MA);
        avance();
    }

    public static void proceso15() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(nt1.getDirec());
        //si es 3: ">="
        nt2.setValor(3);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        avance();
    }

    public static void proceso16() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(nt1.getDirec());
        //si es 4: ">"
        nt2.setValor(4);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
    }

    public static void proceso17() {
        int u = analizador.ultimo();
        NoTerminal nt = analizador.getnode(u - 1);
        NoTerminal s2 = new NoTerminal("s2", nt.getDirec(), 0);
        NoTerminal IG = new NoTerminal("IG", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(IG);
        avance();
    }

    public static void proceso18() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(nt1.getDirec());
        //si es 5: "=="
        nt2.setValor(5);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        avance();
    }

    public static void proceso19() {
        int u = analizador.ultimo();
        NoTerminal nt = analizador.getnode(u - 1);
        NoTerminal s2 = new NoTerminal("s2", nt.getDirec(), 0);
        NoTerminal IG = new NoTerminal("DI", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(IG);
        avance();
    }

    public static void proceso20() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(nt1.getDirec());
        //si es 6: "!="
        nt2.setValor(6);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        avance();
    }

    public static void proceso21() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2;
        nt1 = analizador.getnode(u - 1);

        NoTerminal s3 = new NoTerminal("s3", nt1.getDirec(), 0);
        NoTerminal i1 = new NoTerminal("i1", 0, 0);
        NoTerminal EL = new NoTerminal("EL", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u, 0);
        NoTerminal T = new NoTerminal("T", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i1);
        analizador.adicionarNodo(EL);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(T);

    }

    public static void proceso22() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2, nt3;

        nt1 = analizador.getnode(u - 2);
        nt2 = analizador.getnode(u - 1);
        nt3 = analizador.getnode(nt2.getDirec());

        NoTerminal s4 = new NoTerminal("s4", nt1.getDirec(), 0);
        NoTerminal i4 = new NoTerminal("i4", 0, 0);
        NoTerminal EL = new NoTerminal("EL", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u - 1, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt2.getValor());
        NoTerminal suma = new NoTerminal("suma", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u + 2, 0);
        NoTerminal T = new NoTerminal("T", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(EL);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(suma);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(T);
        contadorSuma++;
        avance();
    }

    public static void proceso23() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2, nt3;

        nt1 = analizador.getnode(u - 2);
        nt2 = analizador.getnode(u - 1);
        nt3 = analizador.getnode(nt2.getDirec());

        NoTerminal s4 = new NoTerminal("s4", nt1.getDirec(), 0);
        NoTerminal i4 = new NoTerminal("i4", 0, 0);
        NoTerminal EL = new NoTerminal("EL", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u - 1, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt2.getValor());
        NoTerminal resta = new NoTerminal("resta", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u + 2, 0);
        NoTerminal T = new NoTerminal("T", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(EL);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(resta);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(T);
        avance();
    }

    public static void proceso24() {
        NoTerminal nt, nt1;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 2);
        nt1 = analizador.getnode(nt.getDirec());
        nt1.setValor(analizador.getnode(u - 1).getValor());
        nt1.setValorLogico(analizador.getnode(u - 1).getValorLogico());
        nt1.setRelacional(analizador.getnode(u - 1).getRelacional());
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
    }

    public static void proceso25() {
        int u = analizador.ultimo();

        NoTerminal nt = analizador.getnode(u - 1);

        NoTerminal s3 = new NoTerminal("s3", nt.getDirec(), 0);
        NoTerminal i1 = new NoTerminal("i1", 0, 0);
        NoTerminal TL = new NoTerminal("TL", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u, 0);
        NoTerminal P = new NoTerminal("P", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i1);
        analizador.adicionarNodo(TL);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(P);
    }

    public static void proceso26() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2, nt3;

        nt1 = analizador.getnode(u - 2);
        nt2 = analizador.getnode(u - 1);
        nt3 = analizador.getnode(nt2.getDirec());

        NoTerminal s4 = new NoTerminal("s4", nt1.getDirec(), 0);
        NoTerminal i4 = new NoTerminal("i4", 0, 0);
        NoTerminal TL = new NoTerminal("TL", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u - 1, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt2.getValor());
        NoTerminal mult = new NoTerminal("mult", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u + 2, 0);
        NoTerminal P = new NoTerminal("P", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(TL);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(mult);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(P);
        avance();
    }

    public static void proceso27() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2, nt3;

        nt1 = analizador.getnode(u - 2);
        nt2 = analizador.getnode(u - 1);
        nt3 = analizador.getnode(nt2.getDirec());

        NoTerminal s4 = new NoTerminal("s4", nt1.getDirec(), 0);
        NoTerminal i4 = new NoTerminal("i4", 0, 0);
        NoTerminal TL = new NoTerminal("TL", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u - 1, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt2.getValor());
        NoTerminal mult = new NoTerminal("div", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u + 2, 0);
        NoTerminal P = new NoTerminal("P", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(TL);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(mult);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(P);
        avance();
    }

    public static void proceso28() {
        NoTerminal nt, nt1;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 2);
        nt1 = analizador.getnode(nt.getDirec());
        nt1.setValor(analizador.getnode(u - 1).getValor());
        nt1.setValorLogico(analizador.getnode(u - 1).getValorLogico());
        nt1.setRelacional(analizador.getnode(u - 1).getRelacional());
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
    }

    public static void proceso29() {
        int u = analizador.ultimo();
        NoTerminal nt = analizador.getnode(u - 1);

        NoTerminal s3 = new NoTerminal("s3", nt.getDirec(), 0);
        NoTerminal i1 = new NoTerminal("i1", 0, 0);
        NoTerminal PL = new NoTerminal("PL", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u, 0);
        NoTerminal F = new NoTerminal("F", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i1);
        analizador.adicionarNodo(PL);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(F);
    }

    public static void proceso30() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2, nt3;

        nt1 = analizador.getnode(u - 2);
        nt2 = analizador.getnode(u - 1);
        nt3 = analizador.getnode(nt2.getDirec());

        NoTerminal s4 = new NoTerminal("s4", nt1.getDirec(), 0);
        NoTerminal i4 = new NoTerminal("i4", 0, 0);
        NoTerminal TL = new NoTerminal("PL", 0, 0);
        NoTerminal s3 = new NoTerminal("s3", u - 1, 0);
        NoTerminal i3 = new NoTerminal("i3", 0, 0);
        NoTerminal i2 = new NoTerminal("i2", 0, nt2.getValor());
        NoTerminal exp = new NoTerminal("exp", 0, 0);
        NoTerminal s2 = new NoTerminal("s2", u + 2, 0);
        NoTerminal P = new NoTerminal("F", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.adicionarNodo(s4);
        analizador.adicionarNodo(i4);
        analizador.adicionarNodo(TL);
        analizador.adicionarNodo(s3);
        analizador.adicionarNodo(i3);
        analizador.adicionarNodo(i2);
        analizador.adicionarNodo(exp);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(P);
        avance();
    }

    public static void proceso31() {
        NoTerminal nt, nt1;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 2);
        nt1 = analizador.getnode(nt.getDirec());
        nt1.setValor(analizador.getnode(u - 1).getValor());
        nt1.setValorLogico(analizador.getnode(u - 1).getValorLogico());
        nt1.setRelacional(analizador.getnode(u - 1).getRelacional());
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
    }

    public static void proceso32() {
        
        int u = analizador.ultimo();
        NoTerminal nt = analizador.getnode(u - 1);
        
        NoTerminal s2 = new NoTerminal("s2", nt.getDirec(), 0);
        NoTerminal ELO = new NoTerminal("ELO", 0, 0);
        NoTerminal PD = new NoTerminal(")", 0, 0);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.adicionarNodo(PD);
        analizador.adicionarNodo(s2);
        analizador.adicionarNodo(ELO);
        avance();
    }

    public static void proceso33() {
        Elemento ele;
        NoTerminal nt, nt1;
        int u = analizador.ultimo();
        ele = lex1.darElemento(indice);
        nt = analizador.getnode(u - 1);
        nt1 = analizador.getnode(nt.getDirec());
        nt1.setValor(ele.darValor());
        avance();
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
    }

    public static void pRelacional() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2, nt3, nt4;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        if (nt1.getRelacional() && nt2.getRelacional()) {
            nt4.setValor(nt2.getValor());
            nt4.setValorLogico(nt2.getValorLogico());
            nt4.setRelacional(true);
            analizador.removeNodo(u);
            analizador.removeNodo(u - 1);
            analizador.removeNodo(u - 2);
            analizador.removeNodo(u - 3);
        } else {
            rechace();
        }
    }

    public static void pOR() {
        contadorRecorrido++;
        int u = analizador.ultimo();

        NoTerminal nt1, nt2, nt3, nt4;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        nt4.setValorLogico(nt1.getValorLogico() || nt2.getValorLogico());
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);
    }

    public static void pAND() {
        contadorRecorrido++;
        int u = analizador.ultimo();

        NoTerminal nt1, nt2, nt3, nt4;
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        nt4.setValorLogico(nt1.getValorLogico() && nt2.getValorLogico());
        nt4.setRelacional(true);
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);
    }

    public static void pComparar() {
        int u = analizador.ultimo();
        NoTerminal nt1, nt2, nt3, nt4, nt5;

        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(u - 4);
        nt5 = analizador.getnode(nt4.getDirec());

        boolean res;
        switch ((int) nt3.getValor()) {
            case 1:
                res = nt1.getValor() <= nt2.getValor();
                break;
            case 2:
                res = nt1.getValor() < nt2.getValor();
                break;
            case 3:
                res = nt1.getValor() >= nt2.getValor();
                break;
            case 4:
                res = nt1.getValor() > nt2.getValor();
                break;
            case 5:
                res = nt1.getValor() == nt2.getValor();
                break;
            default:
                res = nt1.getValor() != nt2.getValor();
                break;
        }
        nt5.setValorLogico(res);
        nt5.setRelacional(true);

        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);
        analizador.removeNodo(u - 4);
    }

    public static void suma() {
        
        NoTerminal nt1, nt2, nt3, nt4;
        int u = analizador.ultimo();
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        double sum = nt1.getValor() + nt2.getValor();
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        nt4.setValor(sum);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);

    }

    public static void resta() {
        NoTerminal nt1, nt2, nt3, nt4;
        int u = analizador.ultimo();
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        double sum = nt1.getValor() - nt2.getValor();
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        nt4.setValor(sum);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);

    }

    public static void mult() {
        NoTerminal nt1, nt2, nt3, nt4;
        int u = analizador.ultimo();
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        double mul = nt1.getValor() * nt2.getValor();
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        nt4.setValor(mul);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);

    }

    public static void div() {
        NoTerminal nt1, nt2, nt3, nt4;
        int u = analizador.ultimo();
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        double mul = nt1.getValor() / nt2.getValor();
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        nt4.setValor(mul);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);

    }

    public static void exp() {
        NoTerminal nt1, nt2, nt3, nt4;
        int u = analizador.ultimo();
        nt1 = analizador.getnode(u - 1);
        nt2 = analizador.getnode(u - 2);
        double mul = Math.pow(nt1.getValor(), nt2.getValor());
        nt3 = analizador.getnode(u - 3);
        nt4 = analizador.getnode(nt3.getDirec());
        nt4.setValor(mul);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
        analizador.removeNodo(u - 2);
        analizador.removeNodo(u - 3);

    }

    public static void resultado() {
        NoTerminal nt;
        int u = analizador.ultimo();
        nt = analizador.getnode(u - 1);
        System.out.println("Valor numerico =" + nt.getValor());
        System.out.println("Valor logico =" + nt.getValorLogico());
              
        analizador.removeNodo(u);
        analizador.removeNodo(u - 1);
    }

    public static void analisisLexico() {
        // Este analizador es sencillo determina solo constantes enteras y reales positivas
        // Trabaja los diferentes elementos en un ArrayList que trabaja con la clase Clexico
        // la cual define el ArrayList con la clase CElemento
        // Almacen los valores para poder hallar los resultados

        Elemento ele1;

        int i = 0;
        elementoCabeza = 0;
        int ind = 0;
        char tip = 0;
        char sim1 = cad.charAt(i);
        double val = 0;

        while (sim1 != '¬') {
            // determina si sim1 esta en la cadena de digitos cad1 que es global
            if (cad1.indexOf(sim1) != -1) {
                String num = "";
                while (cad1.indexOf(sim1) != -1) {
                    num = num + sim1;
                    elementoCabeza++;
                    sim1 = cad.charAt(elementoCabeza);

                }
                // en el String num se almacena el entero y se lo almacena en val como doble
                // DeterminarNumero(num);
                if (determinarNumero(num)) {
                    val = Double.parseDouble(num);
                    tip = 'i';

                    // se tipifica el valor como i
                } else {
                    System.out.println("Se rechaza la secuencia");
                    System.exit(0);
                }

            } else {
                // si el simbolo de entrada no esta en cad1 lo tipifica como tal ej
                // +,-,* (,) etc.

                tip = (char) sim1;
                elementoCabeza++;
                sim1 = cad.charAt(elementoCabeza);
                val = 0;

            }

            // con los elementos establecidos anteriormente se crea el elemento y se lo
            // adicina a lex1 que es el objeto de la clase Clexico
            if (tip != ' ') {
                ele1 = new Elemento(tip, val, ind);
                lex1.adicionarElemento(ele1);

                ind = ind + 1;
            }
            //System.out.print("indice ="+ind);

        }
        ele1 = new Elemento('¬', 0, ind);
        lex1.adicionarElemento(ele1);
        lex1.mostrarLexico();
        System.out.println(" cadena" + lex1.cadenaLexico());
    }

    public static boolean determinarNumero(String numero) {
        // Este método recibe un número en string y determina mediante un autómata finito
        // si está o no correcto. El string es una cadena de dígitos y el punto.
        // Retorna un valor booleano.

        int estado = 1, i = 0;
        char simbolo;
        boolean b = true;
        while (i < numero.length() && b) {
            simbolo = numero.charAt(i);
            switch (simbolo) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    switch (estado) {
                        case 1:
                            estado = 2;
                            i++;

                            break;
                        case 2:
                            estado = 2;
                            i++;

                            break;
                        case 3:
                            estado = 4;
                            i++;

                            break;
                        case 4:
                            estado = 4;
                            i++;

                            break;

                    }
                    break;
                case '.':
                    switch (estado) {
                        case 1:
                        case 3:
                        case 4:
                            b = false;
                            break;
                        case 2:
                            estado = 3;
                            i++;

                            break;

                    }
                    break;
                default:
                    b = false;
            }

        }
        return b;
    }

    public static void resultado(double res) {
        System.out.println("Resultado " + res);
    }

    public static void resultado(boolean res) {
        System.out.println("Respuesta: " + res);
    }

    public static void avance() {
        indice++;
        if (indice < cad.length()) {
            sim = lex1.darElemento(indice).darTipo();
            cadavance = cadavance + sim;
            System.out.println("Cadena procesada " + cadavance);

        }
    }

    public static void mostrarContador(int i2) {
        System.out.println("Cantidad de unos " + i2);
    }

    public static void rechace() {
        System.out.println("Se rechaza la secuencia");
        System.exit(0);
    }
}

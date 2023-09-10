// la gramática que va a reconocer es la siguiente

// 1.<S>      --> <E> {Resultado}             Selección(1)={(,I} 
// 2.<E>      --> +<E><E>{suma}               Selección(2)={(,I}
// 3.<E>    -->   *<E><E>{mult}               Selección(3)={+}
// 4.<E>    -->   I                           Selección (4)= I


package apcompiladorrecursivoatributos;

public class ApCompiladorRecursivoAtributos {
    
    static String cad = "(((((2+3¬";
    static String cad1 = "0123456789.";
    // variable indice es global y controla el indice del objwro lex1
    static int indice=0;
    static char sim=' ';
    static Lexico lex1 = new Lexico();
    static String cadavance="";
    
    public static void main(String[] args) {
       /* InputStreamReader isr= new InputStreamReader(System.in);
        BufferedReader flujoE = new BufferedReader(isr);*/
        analisisLexico();
        cad=lex1.cadenaLexico();
        sim=lex1.darElemento(indice).darTipo();
        cadavance=cadavance+sim;
        
        procS();
        if (sim=='¬')
            System.out.println("Se acepta la secuencia ");
        else
            System.out.println("Se rechaza la secuencia ");
    
    }    
    
    public static void procS(){
        // <S> --> <E>{Resultado} 
        
        switch (sim) {
            case 'i':case '(':
                    NoTerminal s1 = new NoTerminal("s1",0,0);
                    procELO(s1);
                    resultado(s1);
                    System.out.println("valor logico:" + s1.getValorLogico());
                    System.out.println("relacional:" + s1.getRelacional());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }        
    }
    
    public static void procELO(NoTerminal s1){
        switch (sim) {
            case 'i':case '(':
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procEL2(s2);
                    procELOL(s2, s1);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }     
    }
    
    public static void procELOL(NoTerminal i1, NoTerminal s1){
        switch (sim) {
            case '|':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procEL2(s2);
                    pRelacional(i1, s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    pOR(i1, s2, s3);
                    procELOL(s3, s1);
                    return;
            case ')':case '¬':
                    s1.setValor(i1.getValor());
                    s1.setValorLogico(i1.getValorLogico());
                    s1.setRelacional(i1.getRelacional());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }     
    }
    
    public static void procEL2(NoTerminal s1){
        switch (sim) {
            case 'i':case'(':
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procER(s2);
                    procEL2L(s2, s1);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }   
    }
    
    public static void procEL2L(NoTerminal i1, NoTerminal s1){
        switch (sim) {
            case '&':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procER(s2);
                    pRelacional(i1, s2);
                    NoTerminal s3 = new NoTerminal("s3", 0, 0);
                    pAND(i1, s2, s3);
                    procEL2L(s3, s1);
                    return;
            case '|': case ')': case '¬':
                    s1.setValor(i1.getValor());
                    s1.setValorLogico(i1.getValorLogico());
                    s1.setRelacional(i1.getRelacional());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procER(NoTerminal s1){
        switch (sim) {
            case 'i':case'(':
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procE(s2);
                    procERL(s2, s1);
                    
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }  
    }
    
    public static void procERL(NoTerminal i1, NoTerminal s1){
        switch (sim) {
            case '<':case'>':case '=': case '!':
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procOR(s2);
                    NoTerminal s3 = new NoTerminal("s3", 0, 0);
                    procE(s3);
                    pComparar(i1, s3, s2, s1);
                    return;
            case '|': case '&': case ')': case '¬': 
                    s1.setValor(i1.getValor());
                    s1.setValorLogico(i1.getValorLogico());
                    s1.setRelacional(i1.getRelacional());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procOR(NoTerminal s1){
        switch (sim) {
            case '<':
                    //proceso 11
                    avance();
                    procME(s1);
                    return;
            case '>': 
                    //proceso 14
                    avance();
                    procMA(s1);
                    return;
            case '=':
                    //proceso 17
                    avance();
                    procIG(s1);
                    return;
            case '!':
                    //proceso 19
                    avance();
                    procDI(s1);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procME(NoTerminal s1){
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(1);
                    return;
            case 'i': case '(':
                    s1.setValor(2);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procMA(NoTerminal s1){
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(3);
                    return;
            case 'i': case '(':
                    s1.setValor(4);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procIG(NoTerminal s1){
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(5);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procDI(NoTerminal s1){
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(6);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procE(NoTerminal s1){
        switch (sim) {
            case 'i':case '(':
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procT(s2);
                    procEL(s2, s1);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procEL(NoTerminal i1, NoTerminal s1){
        switch (sim) {
            case '+':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procT(s2);
                    NoTerminal s3 = new NoTerminal("s3", 0, 0);
                    suma(i1.getValor(), s2.getValor(), s3);
                    procEL(s3, s1);
                    return;
            case '-':
                    avance();
                    NoTerminal s4 = new NoTerminal("s4", 0, 0);
                    procT(s4);
                    NoTerminal s5 = new NoTerminal("s5", 0, 0);
                    resta(i1.getValor(), s4.getValor(), s5);
                    procEL(s5, s1);
                    return;
            case '<':case '>':case '=':case '!':case '|':case '&':case ')':case '¬':
                    s1.setValor(i1.getValor());
                    s1.setValorLogico(i1.getValorLogico());
                    s1.setRelacional(i1.getRelacional());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procT(NoTerminal s1){
        switch (sim) {
            case 'i':case '(':
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procP(s2);
                    procTL(s2, s1);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procTL(NoTerminal i1, NoTerminal s1){
        switch (sim) {
            case '*':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procP(s2);
                    NoTerminal s3 = new NoTerminal("s3", 0, 0);
                    mult(i1.getValor(), s2.getValor(), s3);
                    procTL(s3, s1);
                    return;
            case '/':
                    avance();
                    NoTerminal s4 = new NoTerminal("s4", 0, 0);
                    procP(s4);
                    NoTerminal s5 = new NoTerminal("s5", 0, 0);
                    div(i1.getValor(), s4.getValor(), s5);
                    procTL(s5, s1);
                    return;
            case '+':case '-':case '=':case '!':case '|':case '&':case ')':case '¬':case '<': case '>':
                    s1.setValor(i1.getValor());
                    s1.setValorLogico(i1.getValorLogico());
                    s1.setRelacional(i1.getRelacional());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procP(NoTerminal s1){
        switch (sim) {
            case 'i':case '(':
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procF(s2);
                    procPL(s2, s1);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procPL(NoTerminal i1, NoTerminal s1){
        switch (sim) {
            case '^':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2", 0, 0);
                    procP(s2);
                    NoTerminal s3 = new NoTerminal("s3", 0, 0);
                    exp(i1.getValor(), s2.getValor(), s3);
                    procTL(s3, s1);
                    return;
            case '*': case '/':case '+':case '-':case '=':case '!':case '|':case '&':case ')':case '¬':case '<': case '>':
                    s1.setValor(i1.getValor());
                    s1.setValorLogico(i1.getValorLogico());
                    s1.setRelacional(i1.getRelacional());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void procF(NoTerminal s1){
        switch (sim) {
            case 'i':
                    Elemento ele = lex1.darElemento(indice);
                    s1.setValor(ele.darValor());
                    avance();
                    return;   
            case '(':
                    avance();
                    procELO(s1);
                    avance();
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        } 
    }
    
    public static void pRelacional(NoTerminal i1, NoTerminal i2){
        if(!(i1.getRelacional() && i2.getRelacional())){
            rechace();
        }
    }
    
    public static void pComparar(NoTerminal i1, NoTerminal i2, NoTerminal i3, NoTerminal s1){
        boolean res;
        System.out.println("Entro en Comparar=====================================================");
        switch ((int) i3.getValor()) {
            case 1:
                res = i1.getValor() <= i2.getValor();
                break;
            case 2:
                res = i1.getValor() < i2.getValor();
                break;
            case 3:
                res = i1.getValor() >= i2.getValor();
                break;
            case 4:
                res = i1.getValor() > i2.getValor();
                break;
            case 5:
                res = i1.getValor() == i2.getValor();
                break;
            default:
                res = i1.getValor() != i2.getValor();
                break;
        }
        
        System.out.println("respuesta: " + res);
        s1.setRelacional(true);
        s1.setValorLogico(res);
    }
    
    public static void pAND(NoTerminal i1, NoTerminal i2, NoTerminal s1){
        s1.setRelacional(true);
        s1.setValorLogico(i1.getValorLogico() && i2.getValorLogico());
    }
    
    public static void pOR(NoTerminal i1, NoTerminal i2, NoTerminal s1){
        s1.setRelacional(true);
        s1.setValorLogico(i1.getValorLogico() || i2.getValorLogico());
    }
    
    public static void suma(double valor1, double valor2, NoTerminal nt){
        System.out.println("ENTRO A LA SUMA: ");
        nt.setValor(valor1+valor2);
        System.out.println("valor: "  + nt.getValor());
    }
    
    public static void resta(double valor1, double valor2, NoTerminal nt){
        
        nt.setValor(valor1-valor2);
    }
    
    public static void mult(double valor1, double valor2, NoTerminal nt){
        
        nt.setValor(valor1*valor2);
    }
    
    public static void div(double valor1, double valor2, NoTerminal nt){
        
        nt.setValor(valor1/valor2);
    }
    
    public static void exp(double valor1, double valor2, NoTerminal nt){
        
        nt.setValor(Math.pow(valor1, valor2));
    }
    
    public static void resultado(NoTerminal res){
        
        System.out.println("");
        System.out.println("===============================");
        if(res.getRelacional()){
            System.out.println("Resultado = "+res.getValorLogico());
            System.out.println("Resultado = "+res.getValor());
        }else{
            System.out.println("Resultado = "+res.getValor());
            System.out.println("Resultado = "+res.getValorLogico());
        }
        System.out.println("===============================\n");
        
        
    }
    
        public static void analisisLexico(){
        // Este analizador es sencillo determina solo constantes enteras y reales positivas
        // Trabaja los diferentes elementos en un ArrayList que trabaja con la clase Clexico
        // la cual define el ArrayList con la clase CElemento
        // Almacen los valores para poder hallar los resultados
        
        Elemento ele1; 
        
        int i=0;
        int ind=0;
        char tip=0;
        char sim1=cad.charAt(i);
        double val=0;
        
        while (sim1!='¬'){
            // determina si sim1 esta en la cadena de digitos cad1 que es global
            if (cad1.indexOf(sim1)!=-1){
                String num="";
                while(cad1.indexOf(sim1)!=-1){
                    num=num+sim1;
                    i++;
                    sim1=cad.charAt(i);
        
                }
                // en el String num se almacena el entero y se lo almacena en val como doble
                // DeterminarNumero(num);
                if (determinarNumero(num)){
                    val=Double.parseDouble(num);
                    tip='i';
                
                    // se tipifica el valor como i
                }
                else{
                    System.out.println("Se rechaza la secuencia");
                    System.exit(0);
                }
        
            }
            else {
               // si el simbolo de entrada no esta en cad1 lo tipifica como tal ej
               // +,-,* (,) etc.
                
               tip=(char)sim1;
               i++;
               sim1=cad.charAt(i);
               val=0;
              
            }
        
            // con los elementos establecidos anteriormente se crea el elemento y se lo
            // adicina a lex1 que es el objeto de la clase Clexico
            if (tip!=' '){
            ele1=new Elemento(tip,val,ind);
            lex1.adicionarElemento(ele1);
            
            ind=ind+1;
            }
            //System.out.print("indice ="+ind);
               
        }
        ele1=new Elemento('¬',0,ind);
        lex1.adicionarElemento(ele1);
        lex1.mostrarLexico();
        System.out.println(" cadena"+lex1.cadenaLexico());
    }
    
    public static boolean determinarNumero(String numero){
     // Este método recibe un número en string y determina mediante un autómata finito
     // si está o no correcto. El string es una cadena de dígitos y el punto.
     // Retorna un valor booleano.
     
        int estado=1,i=0;
        char simbolo;
        boolean b=true;
        while (i<numero.length()&&b) {
            simbolo = numero.charAt(i);
            switch (simbolo) {
                case '0':case '1':case '2':case '3':case '4':case '5':case '6':  
                case '7':case '8':case '9':    
                    switch (estado) {
                        case 1:
                           estado=2;
                           i++;
        
                           break;
                        case 2:
                           estado=2;
                           i++;
        
                           break;
                        case 3:
                           estado=4;
                           i++;
        
                           break;
                        case 4:
                            estado=4;
                           i++;
        
                           break;
                    
                    }
                    break;
                case '.':    
                    switch (estado) {
                        case 1:case 3: case 4:
                           b=false;
                           break;
                        case 2:
                           estado=3;
                           i++;
        
                           break;
                        
                    
                    }
                    break;
                default: b=false;
            }
        
    }
        return b;
    }

    public static void avance(){
           indice++;
       if (indice<cad.length()) {
            sim=lex1.darElemento(indice).darTipo();
            cadavance=cadavance+sim;
            System.out.println("Cadena procesada "+cadavance);
       }
    }
    
    public static void mostrarContador(int i2){
    System.out.println("Cantidad de unos "+i2);
    }
    
    public static void rechace(){
        System.out.println("Se rechaza la secuencia");
        System.exit(0);
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apcompiladorpilaatributos;

/**
 *
 * @author SistemaInvestigacion
 */
import java.util.*;

public class Analizador {

    private ArrayList<NoTerminal> pila;
    
    // metodos
    
    public Analizador(){
        pila = new ArrayList();
    }
    
    public int ultimo(){
        return pila.size()-1;
    }
    
    public NoTerminal getnode(int u){
        return (NoTerminal)pila.get(u);
    }
    
    public void removeNodo(int i){
        pila.remove(i);
    }
    
    public void adicionarNodo(NoTerminal nt){
        pila.add(nt);
        toString();
    
    }

    @Override
    public String toString() {
        return "Analizador{" + "pila=" + pila + '}';
    }
    
    public void mostrarCadenaPila(){
        int tam=pila.size();
        NoTerminal nt;
        for (int i=0;i<tam;i++){
            nt=pila.get(i);
            System.out.println("indice "+i+" nombre "+nt.getNombre()+" direc "+nt.getDirec()+
                    " valor " + nt.getValor());
        }
    }
    
   
}

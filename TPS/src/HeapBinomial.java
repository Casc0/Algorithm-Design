import java.util.ArrayList;
import java.util.List;

public class HeapBinomial {

    private List<ArbolBinomial> arboles = new ArrayList<ArbolBinomial>();

    public HeapBinomial(NodoBinomial raiz) {
        ArbolBinomial unArbol = new ArbolBinomial(raiz);
        this.arboles.add(unArbol);
    }

    public HeapBinomial(){

    }

    //inserta un nuevo elemento
    public void insertar(Comparable x){
        NodoBinomial nuevoNodo = new NodoBinomial(x);
        ArbolBinomial nuevo = new ArbolBinomial(nuevoNodo);

        //Caso crear Raiz
        if(arboles.isEmpty()){
            this.arboles.add(nuevo);
        }else if(!this.pertenece(x)){
            //si no pertenece, se añade

            /* Siempre se compara con la posicion 1, porque a medida que se unen, se elimina uno de los unidos,
            quedando asi el nuevo arbol en la posicion 0 y se moveria el de pos 2 a pos 1.*/
            arboles.addFirst(nuevo);
            ArbolBinomial actual = arboles.get(1);
            while(nuevo.getOrden() == actual.getOrden()){
                //si tienen el mismo orden hay que unir, si no solo se añade al arbol
                unirArboles(nuevo, actual);
                nuevo = arboles.get(0);
                actual = arboles.get(1);
            }
        }
    }

    private void unirArboles(ArbolBinomial nuevo, ArbolBinomial actual){
        if(nuevo.getRaiz().getElem().compareTo(actual.getRaiz().getElem()) < 0){
            //nuevo es menor que actual, actual se hace hijo de nuevo
            nuevo.unir(actual);
            arboles.remove(1);
        }else{
            actual.unir(nuevo);
            arboles.removeFirst();
        }
    }

    public boolean pertenece(Comparable x){
        boolean yaExiste = false;
        ArbolBinomial arbolActual = this.arboles.get(0);

        while(!yaExiste && arbolActual != null){
            yaExiste = arbolActual.pertenece(x);
        }

        return yaExiste;
    }

    public void unirHeaps(HeapBinomial heap){

    }

}

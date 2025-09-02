import java.util.ArrayList;
import java.util.List;

public class HeapBinomial {

    private List<ArbolBinomial> arboles = new ArrayList<ArbolBinomial>();

    public HeapBinomial(Comparable x) {
        NodoBinomial raiz = new NodoBinomial(x);
        ArbolBinomial unArbol = new ArbolBinomial(raiz);
        this.arboles.add(unArbol);
    }

    public HeapBinomial(){

    }

    /*
    ANTERIOR insertar

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
            quedando asi el nuevo arbol en la posicion 0 y se moveria el de pos 2 a pos 1.
            arboles.addFirst(nuevo);
            ArbolBinomial actual = arboles.get(1);
            while(nuevo.getOrden() == actual.getOrden()){
                //si tienen el mismo orden hay que unir, si no solo se añade al arbol
                unirArboles(nuevo, actual, 0, 1);
                nuevo = arboles.get(0);
                actual = arboles.get(1);
            }
        }
    }
    */

    //inserta un nuevo elemento
    public void insertar(Comparable x){

        //Caso crear Raiz
        if(arboles.isEmpty()){
            NodoBinomial raiz = new NodoBinomial(x);
            ArbolBinomial nuevo = new ArbolBinomial(raiz);
            this.arboles.add(nuevo);
        }else if(!this.pertenece(x)){
            HeapBinomial nuevoHeap =  new HeapBinomial(x);
            unir(nuevoHeap);
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

    public void unir(HeapBinomial heap){
        List<ArbolBinomial> arbolesUnir = heap.arboles;
        while(!arbolesUnir.isEmpty()){
            ArbolBinomial arbolNuevo = arbolesUnir.remove(0);
            int ordenNuevo = arbolNuevo.getOrden();
            unirAux(arbolNuevo, ordenNuevo);
        }

    }

    public void unirAux(ArbolBinomial arbolNuevo, int ordenNuevo){
        int posActual = 0;
        do {
            ArbolBinomial arbolActual = arboles.get(posActual);
            int ordenActual = arbolActual.getOrden();

            //busca si el orden del arbol a añadir ya existe. Si existe los une y busca otros conflictos
            if(ordenNuevo == ordenActual) {
                //Lo añade donde se repite, empujando al repetido una posicion más atras.
                arboles.add(posActual, arbolNuevo);

                if(arbolNuevo.compareTo(arbolActual) < 0){
                    //nuevo es menor que actual, actual se hace hijo de nuevo
                    arbolNuevo.unir(arbolActual);
                    arboles.remove(posActual + 1);
                }else{
                    //actual es menor que nuevo, nuevo se hace hijo de actual
                    arbolActual.unir(arbolNuevo);
                    arboles.remove(posActual);
                }

                //Corrección ante posible repetición
                ArbolBinomial siguiente = arboles.get(posActual+1);


                //Pregunta por el siguiente, ya que podria ser el unico que comparta orden con el nuevo que se creo
                while(siguiente != null && siguiente.getOrden() == ordenActual){

                }

            }
            posActual++;
        }while(arboles.get(posActual) != null);
    }


}

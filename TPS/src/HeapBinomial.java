import java.util.ArrayList;
import java.util.List;

public class HeapBinomial {

    private List<ArbolBinomial> arboles = new ArrayList<ArbolBinomial>();

    public HeapBinomial(Comparable x) {
        NodoBinomial raiz = new NodoBinomial(x);
        ArbolBinomial unArbol = new ArbolBinomial(raiz);
        this.arboles.add(unArbol);
    }

    public HeapBinomial(List<ArbolBinomial> arboles) {
        this.arboles = arboles;
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

    //Une dos heaps
    public void unir(HeapBinomial heap){
        List<ArbolBinomial> arbolesUnir = heap.arboles;
        if(!arbolesUnir.isEmpty()){
            //une uno por uno a los arboles del segundo heap
            while(!arbolesUnir.isEmpty()){
                ArbolBinomial arbolNuevo = arbolesUnir.remove(0);
                int ordenNuevo = arbolNuevo.getOrden();
                unirAux(arbolNuevo, ordenNuevo);
            }
        }
    }

    //Añade un arbol a un heap.
    private void unirAux(ArbolBinomial arbolNuevo, int ordenNuevo){
        int posActual = 0;
        boolean fin = false;
        do {
            ArbolBinomial arbolActual = arboles.get(posActual);
            int ordenActual = arbolActual.getOrden();

            //busca si el orden del arbol a añadir ya existe. Si existe los une y busca otros conflictos
            if(ordenNuevo == ordenActual) {
                //Lo añade donde se repite, empujando al repetido una posicion más atras.
                arboles.add(posActual, arbolNuevo);

                //une los dos arboles con el mismo orden
                arbolNuevo = unirArboles(arbolNuevo, arbolActual, posActual);

                //Corrección ante posible repetición
                arbolActual = arboles.get(posActual+1);

                //Pregunta por el siguiente, ya que podria ser el unico que comparta orden con el nuevo que se creo
                while(arbolActual != null && arbolActual.getOrden() == ordenActual){
                    arbolNuevo = unirArboles(arbolNuevo, arbolActual, posActual);
                }

                //Corta la ejecución ya que le encontro lugar al arbol
                fin = true;
            }else{
                //itera mientras no encuentre un mismo orden
                posActual++;
            }
        }while(arboles.get(posActual) != null && !fin);

        //si no le encontro lugar, es porque su orden es mayor a todos los del heap actual, lo carga al final.
        if(!fin){
            arboles.add(posActual, arbolNuevo);
        }
    }

    //une dos arboles binomiales
    private ArbolBinomial unirArboles(ArbolBinomial arbolNuevo, ArbolBinomial arbolActual, int posicion){
        if(arbolNuevo.compareTo(arbolActual) < 0){
            //nuevo es menor que actual, actual se hace hijo de nuevo
            arbolNuevo.unir(arbolActual);
            arboles.remove(posicion + 1);
        }else{
            //actual es menor que nuevo, nuevo se hace hijo de actual
            arbolActual.unir(arbolNuevo);

            //cambio el puntero a su nuevo padre
            arbolNuevo =  arbolActual;
            arboles.remove(posicion + 1);
        }

        return arbolNuevo;
    }

    //Extrae el minimo elemento del heap. Siempre es una de la raices de uno de los arboles.
    public Object extraerMin(){
        Object min = null;
        if(!arboles.isEmpty()) {
            int posMin = 0;
            ArbolBinomial arbolMin = arboles.get(posMin);
            for (int i = 1; i < this.arboles.size(); i++) {
                ArbolBinomial arbolActual = arboles.get(i);
                if (arbolMin.compareTo(arbolActual) < 0) {
                    arbolMin = arbolActual;
                }
            }
            arboles.remove(posMin);
            List<ArbolBinomial> nuevaLista = arbolMin.extraerMin();
            if(!nuevaLista.isEmpty()){
                HeapBinomial nuevoHeap = new HeapBinomial(nuevaLista);
                unir(nuevoHeap);
            }
        }

        return min;
    }

    //retorna la posición de la menor raiz de la lista
    public int buscarMin(){
        int posMin = -1;
        if(!arboles.isEmpty()){
            posMin = 0;
            ArbolBinomial min = arboles.get(posMin);
            for(int i = 1; i < this.arboles.size(); i++){
                ArbolBinomial arbolActual = arboles.get(i);
                if(min.compareTo(arbolActual) < 0){
                    min = arbolActual;
                }
            }
        }

        return posMin;
    }

    public boolean disminuirClave(Comparable x, Comparable valor){
        boolean disminuido = false;
        if(!arboles.isEmpty()){
            ArbolBinomial arbolActual = this.arboles.get(0);
            while(!disminuido && arbolActual != null){
                disminuido = arbolActual.disminuir(x);
            }
            if(disminuido){

            }
        }
        return disminuido;
    }

    //FALTA
    public boolean eliminar(Comparable x){
        boolean eliminado = false;
        if(!arboles.isEmpty()){
            ArbolBinomial arbolActual = this.arboles.get(0);
            while(!eliminado && arbolActual != null){
                eliminado = arbolActual.eliminar(x);
            }
            if(eliminado){

            }
        }
        return eliminado;
    }



}

package Punto1;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinomial{

    private NodoBinomial raiz;
    private int orden;

    public ArbolBinomial(NodoBinomial raiz){
        this.raiz = raiz;
        orden = 0;
    }

    public ArbolBinomial(NodoBinomial raiz, int orden){
        this.raiz =  raiz;
        this.orden = orden;
    }

    public NodoBinomial getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoBinomial raiz) {
        this.raiz = raiz;
    }

    public List<ArbolBinomial> eliminar(Comparable x) {
        List<ArbolBinomial> nuevaLista = null;
        if (raiz != null) {
            boolean eliminar = disminuir(x, (Comparable) Integer.MIN_VALUE);
            if(eliminar){
                nuevaLista = extraerMin();
            }
        }
        return nuevaLista;
    }

    public boolean disminuir(Comparable x, Comparable valor){
        boolean disminuido = false;

        NodoBinomial nodo = this.buscar(valor);
        if(nodo != null){
            disminuido = true;
            //Reemplazo el valor del nodo por el nuevo valor
            nodo.setElem(valor);
            NodoBinomial padre = nodo.getPadre();

            //Intercambio los valores mientras el padre sea mayor que el nodo
            while(padre != null && padre.compareTo(nodo) > 0){
                Comparable aux = padre.getElem();
                padre.setElem(nodo.getElem());
                nodo.setElem(aux);
                nodo = padre;
                padre = nodo.getPadre();
            }
        }

        return disminuido;
    }

    public boolean pertenece(Comparable x){
        boolean yaExiste = false;

        if(raiz != null){
            yaExiste = buscarAux(raiz, x) != null;
        }

        return yaExiste;
    }


    public NodoBinomial buscar(Comparable x){
        NodoBinomial nodo = null;

        if(raiz != null){
            buscarAux(raiz, x);
        }

        return nodo;
    }

    private NodoBinomial buscarAux(NodoBinomial nodo, Comparable x){
        NodoBinomial nodoEncontrado = null;

        if(nodo != null){
            int comp = nodo.getElem().compareTo(x);

            if(comp == 0){
                // Si x es igual al nodo, el elemento pertenece
                nodoEncontrado = nodo;

            }else if(comp > 0){
                // Si x es mayor al nodo, se llama recursivamente con los hijos
                nodoEncontrado = buscarAux(nodo.getHijo(), x);
            }

            if(nodoEncontrado == null){
                // Si x es menor al nodo o no lo encontro en los hijos hay que buscar en los arboles hermano.
                nodoEncontrado = buscarAux(nodo.getHermano(), x);
            }
        }

        return nodoEncontrado;
    }



    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void unir(ArbolBinomial otro){
        //Unir dos arboles, donde el actual es el que tiene la menor raiz
        NodoBinomial raizOtro = otro.getRaiz();
        //La raiz del otro se convierte en hijo del actual
        raizOtro.setHermano(this.raiz.getHijo());
        this.raiz.setHijo(raizOtro);
        raizOtro.setPadre(this.raiz);
        //Se actualiza el orden
        this.orden++;
    }

    //Retorna la lista de los hijos que quedan despues de eliminar la raiz. Orden 0 retorna lista vacia.
    public List<ArbolBinomial> extraerMin() {
        List<ArbolBinomial> lista = new ArrayList<ArbolBinomial>();
        NodoBinomial nodoActual = raiz.getHijo();

        //Ya se sabe que el primer hijo es de orden n-1 y que hay n hijos. n = orden.
        for(int i = orden; i > 0; i--){
            //añade el nuevo arbol
            lista.add(new ArbolBinomial(nodoActual, i-1));
            NodoBinomial aux = nodoActual.getHermano();
            //corta los enlaces, ya que pasan a ser arboles separados
            nodoActual.setHermano(null);
            nodoActual.setPadre(null);
            nodoActual = aux;
        }

        return lista;
    }

    public Comparable getElemRaiz(){
        return this.raiz.getElem();
    }

    public int compareTo(ArbolBinomial otro) {
        return this.raiz.compareTo(otro.raiz);
    }
}

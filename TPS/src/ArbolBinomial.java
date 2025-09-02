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

    public boolean eliminar(Comparable x){
        boolean eliminar = false;

        if(raiz != null){
            int comp = raiz.getElem().compareTo(x);

            if(comp > 0){
                // Si x es mayor a la raiz, se llama recursivamente con los hijos
                auxPertenece(x, raiz.getHijo());

            }else if(comp == 0){
                // Si x es igual a la raiz, el elemento pertenece
                eliminar = true;

                //FALTA BUSCAR MENOR Y REEMPLAZAR EL ELIMINADO

            }
            // Si x es menor a la raiz, como es un heap minimo, no va a estar dentro del arbol. Aun hay que buscar en los arboles hermano.
        }

        return eliminar;
    }

    public boolean pertenece(Comparable x){
        boolean yaExiste = false;

        if(raiz != null){
            int comp = raiz.getElem().compareTo(x);

            if(comp > 0){
                // Si x es mayor a la raiz, se llama recursivamente con los hijos
                auxPertenece(x, raiz.getHijo());

            }else if(comp == 0){
                // Si x es igual a la raiz, el elemento pertenece
                yaExiste = true;
            }
            // Si x es menor a la raiz, como es un heap minimo, no va a estar dentro del arbol. Aun hay que buscar en los arboles hermano.
        }

        return yaExiste;
    }

    private boolean auxPertenece(Comparable x, NodoBinomial nodo){
        boolean yaExiste = false;

        if(nodo != null){
            int comp = nodo.getElem().compareTo(x);
            if(comp == 0){
                // Si x es igual al nodo, el elemento pertenece
                yaExiste = true;


            }else if(comp > 0){
                // Si x es mayor al nodo, se llama recursivamente con los hijos
                yaExiste = auxPertenece(x, nodo.getHijo());
            }

            // Si x es menor al nodo, como es un heap minimo, no va a estar dentro del sub-arbol. Aun hay que buscar en los arboles hermano.
            if((!yaExiste || comp < 0) && nodo.getHermano() != null){
                // Si x es menor al nodo o no estaba en los hijos, se llama recursivamente con los hermanos, si es que existen
                yaExiste = auxPertenece(x, nodo.getHermano());
            }
        }

        return yaExiste;
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

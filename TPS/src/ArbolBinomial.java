public class ArbolBinomial{

    private NodoBinomial raiz;

    public ArbolBinomial(NodoBinomial raiz){
        this.raiz = raiz;
    }

    public NodoBinomial getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoBinomial raiz) {
        this.raiz = raiz;
    }

    public boolean pertenece(Comparable x){
        boolean yaExiste = false;

        if(raiz != null){
            if(raiz.getElem().compareTo(x) > 0){
                // Si x es mayor a la raiz, se llama recursivamente con el primer hijo
                this.pertenece(x, raiz.getHijo());
            }else if(raiz.getElem().compareTo(x) == 0){
                // Si x es igual a la raiz, el elemen
                yaExiste = true;
            }
            // Si x es menor a la raiz, como es un heap minimo, no va a estar dentro del arbol.
        }

        return yaExiste;
    }

    private boolean pertenece(Comparable x, NodoBinomial nodo){
        boolean yaExiste = false;

        if(nodo != null){
            int comp = nodo.getElem().compareTo(x);

        }

        return yaExiste;
    }
}

public class NodoBinomial {
    private Comparable elem;
    private NodoBinomial padre, hijo, hermano;

    //precisa de un int degree?

    public NodoBinomial(Comparable elem) {
        this.elem = elem;
    }

    public NodoBinomial() {
        elem = null;
    }

    public NodoBinomial getHijo() {
        return hijo;
    }

    public Comparable getElem() {
        return elem;
    }

    public NodoBinomial getHermano() {
        return hermano;
    }

    public NodoBinomial getPadre() {
        return padre;
    }

    public void setPadre(NodoBinomial padre) {
        this.padre = padre;
    }

    public void setHermano(NodoBinomial hermano) {
        this.hermano = hermano;
    }

    public void setHijo(NodoBinomial hijo) {
        this.hijo = hijo;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public int compareTo(NodoBinomial otro) {
        return this.elem.compareTo(otro.elem);
    }
}

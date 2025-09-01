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

    public void insertar(Comparable x){
        //Caso crear Raiz
        if(arboles.isEmpty()){
            NodoBinomial nuevaRaiz = new NodoBinomial(x);
            ArbolBinomial unArbol = new ArbolBinomial(nuevaRaiz);
            this.arboles.add(unArbol);
        }else if(!this.pertenece(x)){

        }{



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
}

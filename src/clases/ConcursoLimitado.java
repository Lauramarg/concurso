
package clases;


public class ConcursoLimitado extends Concurso {
protected final Integer nIntentos;
protected Integer contIntentos;

    public ConcursoLimitado(String nombre, Integer nProblemas, Integer tiempo,Integer nIntentos) {
        super(nombre, nProblemas, tiempo);
        this.nIntentos=nIntentos;
        contIntentos=0;
    }

    @Override
    protected boolean envioValido(String nombre, Integer nProblema) {
        int cont=0;
        for(Envio e: envios){
            if(e.getNombreEquipo()==nombre && e.getNumeroDeProblema()==nProblema){
                cont++;
            }
        }
        if(cont<nIntentos){
            return true;
        }
        return false;
    }
    
}

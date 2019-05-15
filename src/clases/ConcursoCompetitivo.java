
package clases;

import static clases.Evaluacion.ACEPTADO;
import static clases.Evaluacion.RECHAZADO;
import java.util.HashSet;
import java.util.Set;


public class ConcursoCompetitivo extends Concurso {
    private Set<Envio> aceptados;
    public ConcursoCompetitivo(String nombre, Integer nProblemas, Integer tiempo) {
        super(nombre, nProblemas, tiempo);
        aceptados=new HashSet<Envio>();
    }

    @Override
    protected boolean envioValido(String nombre, Integer nProblema) {
        for(Envio e: envios){
            if(problemaResuelto(nProblema)){
                return false;
            }
        }
        return true;
    }
    
    public boolean problemaResuelto(Integer nProblema){
        for(Envio e: envios){
            if(e.getNumeroDeProblema()==nProblema && e.getEvaluacion()==ACEPTADO){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Envio registrarEnvio(String nombre,Integer nProblema,String respuesta){
         if (participantes.contains(nombre) && problemaValido(nProblema) && respuestaValida(respuesta) && enMarcha && !yaFueAceptado(nombre, nProblema) && envioValido(nombre,nProblema)) {
            if (soluciones.get(nProblema) == respuesta) {
                envios.add(new Envio(nombre, nProblema, respuesta, ACEPTADO));
                aceptados.add(envios.get(envios.size() - 1));
            } else {
                envios.add(new Envio(nombre, nProblema, respuesta, RECHAZADO));
            }
            return envios.get(envios.size() - 1);
        }
        return null;
    }
}

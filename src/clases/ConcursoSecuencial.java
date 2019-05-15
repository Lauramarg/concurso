
package clases;

import static clases.Evaluacion.ACEPTADO;
import static clases.Evaluacion.RECHAZADO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ConcursoSecuencial extends ConcursoLimitado{
    private Map<String,Integer> estadoConcurso;
    private Integer problemaActual;
    
    public ConcursoSecuencial(String nombre, Integer nProblemas, Integer tiempo, Integer nIntentos) {
        super(nombre, nProblemas, tiempo, nIntentos);
        estadoConcurso=new HashMap<String,Integer>();
        problemaActual=1;
    }
    
  
    @Override
    public boolean iniciar(long fin){
          if (preparado) {
            iniciado = true;
            envios = new ArrayList<Envio>();
            fin = System.currentTimeMillis() + (tiempo * 60000);
            
        }
          for(String p:participantes){
              estadoConcurso.put(p,problemaActual);
          }
        return iniciado;
    }
    @Override
     protected boolean envioValido(String nombre, Integer nProblema) {
        if(estadoConcurso.get(nombre)==nProblema){
            return true;
        }
        return false;
    }
     
    @Override
      public Envio registrarEnvio(String nombre, Integer nProblema, String respuesta) {
        if (participantes.contains(nombre) && problemaValido(nProblema) && respuestaValida(respuesta) && enMarcha && !yaFueAceptado(nombre, nProblema) && envioValido(nombre, nProblema)) {
            if (soluciones.get(nProblema) == respuesta) {
                envios.add(new Envio(nombre, nProblema, respuesta, ACEPTADO));
                problemaActual++;
                estadoConcurso.put(nombre,problemaActual);
            } else {
                envios.add(new Envio(nombre, nProblema, respuesta, RECHAZADO));
            }
            return envios.get(envios.size() - 1);
        }
        return null;
    }
}

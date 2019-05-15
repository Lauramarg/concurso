
package clases;


public class Envio {
    private final String nombreEquipo;
    private final Integer numeroDeProblema;
    private final String respuesta;
    private final Evaluacion evaluacion;

    public Envio(String nombreEquipo, Integer numeroDeProblema, String respuesta, Evaluacion evaluacion) {
        this.nombreEquipo = nombreEquipo;
        this.numeroDeProblema = numeroDeProblema;
        this.respuesta = respuesta;
        this.evaluacion = evaluacion;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public Integer getNumeroDeProblema() {
        return numeroDeProblema;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }
    
    
}

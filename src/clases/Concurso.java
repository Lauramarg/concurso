package clases;

import static clases.Evaluacion.ACEPTADO;
import static clases.Evaluacion.RECHAZADO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Concurso {

    protected String nombre;
    protected final Integer nProblemas;
    protected Set<String> participantes;
    protected final Integer tiempo;
    protected ArrayList<String> soluciones;
    protected ArrayList<Envio> envios;
    protected boolean iniciado;

    protected boolean enMarcha;
    protected boolean haFinalizado;
    protected boolean preparado;

    public Concurso(String nombre, Integer nProblemas, Integer tiempo) {
        this.nombre = nombre;
        this.nProblemas = nProblemas;
        this.tiempo = tiempo;
        participantes = new HashSet<String>();
        soluciones = new ArrayList<String>(nProblemas);
        iniciado = false;
        

    }

    public void anadirEquipos(String... equipos) {
        for (String equipo : equipos) {
            participantes.add(equipo);
        }
    }

    public boolean eliminarEquipo(String equipo) {
        return participantes.remove(equipo);
    }

    public void establecerSolucion(String solucion, Integer nProblema) {
        if (nProblema <= nProblemas) {
            soluciones.add(nProblema, solucion);
        }

    }

    public void consultarSolucion(Integer nProblema) {
        System.out.println(soluciones.get(nProblema - 1));
    }

    public void preparar() {
        if (soluciones.size() == nProblemas) {
            preparado = true;
        }
        preparado = false;
    }

    public boolean iniciar(long fin) {
        if (preparado) {
            iniciado = true;
            envios = new ArrayList<Envio>();
            fin = System.currentTimeMillis() + (tiempo * 60000);

        }
        return iniciado;
    }

    public boolean enMarcha(long fin) {

        if (iniciado && System.currentTimeMillis() < fin) {
            enMarcha = true;
        }
        enMarcha = false;
        return enMarcha;
    }

    public boolean haFinalizado(long fin) {
        if (iniciado && !enMarcha) {
            haFinalizado = true;
        }
        haFinalizado = false;
        return haFinalizado;
    }

    public Envio registrarEnvio(String nombre, Integer nProblema, String respuesta) {
        if (participantes.contains(nombre) && problemaValido(nProblema) && respuestaValida(respuesta) && enMarcha && !yaFueAceptado(nombre, nProblema) && envioValido(nombre, nProblema)) {
            if (soluciones.get(nProblema) == respuesta) {
                envios.add(new Envio(nombre, nProblema, respuesta, ACEPTADO));
            } else {
                envios.add(new Envio(nombre, nProblema, respuesta, RECHAZADO));
            }
            return envios.get(envios.size() - 1);
        }
        return null;
    }

    public boolean problemaValido(Integer nProblema) {
        if (nProblema > 0 && nProblema <= nProblemas) {
            return true;
        }
        return false;
    }

    public boolean respuestaValida(String respuesta) {
        if (!respuesta.isEmpty() && respuesta != null) {
            return true;
        }
        return false;
    }

    public boolean yaFueAceptado(String nombre, Integer nProblema) {
        for (Envio e : envios) {
            if (e.getNombreEquipo() == nombre && e.getNumeroDeProblema() == nProblema && e.getEvaluacion() != ACEPTADO) {
                return true;
            }
        }
        return false;
    }

    protected abstract boolean envioValido(String nombre, Integer nProblema);

    public String getNombre() {
        return nombre;
    }

    public Integer getnProblemas() {
        return nProblemas;
    }

    public Set<String> getParticipantes() {
        return participantes;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public ArrayList<String> getSoluciones() {
        return soluciones;
    }

    public ArrayList<Envio> getEnvios() {
        return envios;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public boolean isEnMarcha() {
        return enMarcha;
    }

    public boolean isHaFinalizado() {
        return haFinalizado;
    }

    public boolean isPreparado() {
        return preparado;
    }

}

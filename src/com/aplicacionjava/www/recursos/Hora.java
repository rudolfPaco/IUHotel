/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 *
 * @author rudolf
 */
public class Hora {
    private int horero = 0;
    private int minutero = 0;
    private int segundero = 0;
    private int horaInicialCritica = 0;
    private int minutoInicialCritica = 0;
    private int horaFinalCritica = 5;
    private int minutoFinalCritica = 0;    

    /**
     * metodo constructor que inicializa el horero, minutero y segundero con la hora actual. formato de hora: HH:mm:ss
     */
    public Hora () {
        this.horero = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        this.minutero = Calendar.getInstance().get(Calendar.MINUTE);
        this.segundero = Calendar.getInstance().get(Calendar.SECOND);
    }
    
    /**
     *
     * @param hora que inicializa el horero, minutero y segundero mediante el formato: HH:mm:ss
     */
    public Hora(String hora){
        StringTokenizer texto_filtrado = new StringTokenizer(hora, ":");
        int[] arreglo = new int[texto_filtrado.countTokens()];
        int contador = 0;
        while(texto_filtrado.hasMoreElements()){
            arreglo[contador] = Integer.parseInt(texto_filtrado.nextToken());
            contador++;
        }
        this.horero = arreglo[0];
        this.minutero = arreglo[1];
    }

    /**
     *
     * @return el horero deacuerdo a la hora establecida.
     */
    public int getHorero() {
        return horero;
    }

    /**
     *
     * @return el minutero deacuerdo a la hora establecida.
     */
    public int getMinutero() {
        return minutero;
    }
    /**
     *
     * @return el segundero deacuerdo a la hora establecida.
     */
    public int getSegundero(){
        return segundero;
    }

    /**
     *
     * @return la hora establecida en formato: HH:mm:ss (ej. 12:00:05)
     */
    public String getHora(){        
        String cadenaHorero = String.valueOf(getHorero());
        String cadenaMinutero = String.valueOf(getMinutero());
        String cadenaSegundero = String.valueOf(getSegundero());
        
        if(getMinutero() >= 0 && getMinutero() <= 9)
            cadenaMinutero = "0"+getMinutero();
        if(getHorero() >= 0 && getHorero()<= 9)
            cadenaHorero = "0"+getHorero();
        if(getSegundero()>= 0 && getSegundero()<= 9)
            cadenaSegundero = "0"+getSegundero();
        return cadenaHorero+":"+cadenaMinutero+":"+cadenaSegundero;
    }

    /**
     *
     * @param horaMenor resta a la hora establecida. (ej. new Hora(12:00).getDiferenciaHora(new Hora(11:00)) el resultado sera: 1.0 hs.)
     * @return un numero de tipo double, que representa la diferencia de horas.
     */
    public double getDiferenciaHora(Hora horaMenor) {
        Date fechaMenor= new Fecha(new Fecha().getFecha()+" "+horaMenor.getHora()+":"+horaMenor.getMinutero()+":"+horaMenor.getSegundero()).getDate();
        Date fechaMayor= new Fecha(new Fecha().getFecha()+" "+getHora()+":"+getMinutero()+":"+getSegundero()).getDate();
        
        Calendar calendarioMenor = Calendar.getInstance();
        Calendar calendarioMayor = Calendar.getInstance();
        
        calendarioMenor.setTime(fechaMenor);
        calendarioMayor.setTime(fechaMayor);

        double totalHora = (calendarioMayor.getTimeInMillis() - calendarioMenor.getTimeInMillis());
        totalHora = redondear(totalHora/1000/60/60, 2);
        return totalHora;
    }
    /**
     *
     * @param horaMenor resta a la hora establecida. (ej. new Hora(12:10).getDiferenciaHora(new Hora(12:00)) el resultado sera: 10.0 min.)
     * @return un numero de tipo double, que representa la diferencia de minutos.
     */
    public double getDiferenciaMinutos (Hora horaMenor) {
        Date fechaMenor= new Fecha(new Fecha().getFecha()+" "+horaMenor.getHora()+":"+horaMenor.getMinutero()+":"+horaMenor.getSegundero()).getDate();
        Date fechaMayor= new Fecha(new Fecha().getFecha()+" "+getHora()+":"+getMinutero()+":"+getSegundero()).getDate();
        
        Calendar calendarioMenor = Calendar.getInstance();
        Calendar calendarioMayor = Calendar.getInstance();
        
        calendarioMenor.setTime(fechaMenor);
        calendarioMayor.setTime(fechaMayor);

        double totalMinutos = (calendarioMayor.getTimeInMillis() - calendarioMenor.getTimeInMillis());
        totalMinutos = redondear(totalMinutos/1000/60, 2);
        return totalMinutos;
    }

    /**
     *
     * @param hora es comparado con la hora establecida haciendo una resta de horas, para determinar cual es la hora MAYOR.
     * @return true si y solo si, la hora establecida es MAYOR a la hora que se compara. caso contrario devuelve false, por que la hora establecida es MENOR.
     */
    public boolean esMayor(Hora hora) {
        boolean respuesta = false;
        if(getDiferenciaMinutos(hora) > 0)
            respuesta = true;
        return respuesta;
    }
    /**
     *
     * @param hora es comparado con la hora establecida haciendo una resta de horas, para determinar cual es la hora MENOR.
     * @return true si y solo si, la hora establecida es MENOR a la hora que se compara. caso contrario devuelve false, por que la hora establecida es MAYOR.
     */
    public boolean esMenor(Hora hora) {
        boolean respuesta = false;
        if(getDiferenciaMinutos(hora) < 0)
            respuesta = true;
        return respuesta;
    }

    /**
     *
     * @return la clase Date, respecto a la hora establecida.
     */
    public Date getTime() {
        Date fechaDate = new Fecha("yyyy-MM-dd HH:mm:ss").getDate(new Fecha().getFecha());
        Calendar calendarioFechaDate = new GregorianCalendar();
        calendarioFechaDate.setTime(fechaDate);
        
        return calendarioFechaDate.getTime();
    }

    /**
     * inicializa las variables, para determinar si la hora establecida esta en este rango de horaInicialCritica hasta la horaFinalCritica, lo mismo con los minutos: minutoInicialCritico hasta minutoFinalCritico.
     * @param horaInicialCritica variable de tipo entero, representa la hora inicial critica.
     * @param minutoInicialCritica variable de tipo entero, representa el minuto inicial critica.
     * @param horaFinalCritica variable de tipo entero, representa la hora final critica.
     * @param minutoFinalCritica variable de tipo entero, representa el minuto final critica.
     */
    public void setRangoHoraCritica(int horaInicialCritica, int minutoInicialCritica, int horaFinalCritica, int minutoFinalCritica){
        this.horaInicialCritica = horaInicialCritica;
        this.minutoInicialCritica = minutoInicialCritica;
        this.horaFinalCritica = horaFinalCritica;
        this.minutoFinalCritica = minutoFinalCritica;
    }

    /**
     *
     * @return true si y solo si la hora establecida se encuentra entre el rango de hora y minuto critico.
     */
    public boolean esCritico(){
        boolean critico = false;
        if(getHorero() >= horaInicialCritica && getHorero() < horaFinalCritica || getMinutero() >= minutoInicialCritica && getMinutero() < minutoFinalCritica)
            critico = true;        
        return critico;
    }
    
    /**
     *
     * @return AM si y solo si la hora establecida se encuentra en el tiempo mañanero caso contrario devuelve: PM si la hora establecida se encuentra en el tiempo tarde o noche.
     */
    public String getFormato(){
        Date fechaDate = new Fecha("YYYY-MM-dd").getDate(new Fecha().getFecha());
        Calendar calendarioFechaDate = new GregorianCalendar();
        calendarioFechaDate.setTime(fechaDate);
        
        //calendarioFechaDate.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        return Calendar.getInstance().get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
    }
    private double redondear( double numero, int decimales ) {
        return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
    }
    /*public static void main(String[] arg){
        Hora hora = new Hora();
        System.out.println(hora.getFormato());
        //hora.setRangoHoraCritica(0, 0, 12, 0);
        System.out.println("la diferencia de horas es: "+hora.getDiferenciaMinutos(new Hora("08:30"))+" "+hora.getFormato());
        System.out.println("la hora es: "+hora.getHora());
        //hora.setRangoHoraCritica(0, 0, 8, 0);
        System.out.println("el rango inicial critico es: hr. "+hora.horaInicialCritica+" min. "+hora.minutoInicialCritica);
        System.out.println("el rango final critico es: hr. "+hora.horaFinalCritica+" min. "+hora.minutoFinalCritica);
        if(hora.esCritico())
            System.out.println("la hora es critico");
        else 
            System.out.println("la hora NO  es critico");
    }*/    
}

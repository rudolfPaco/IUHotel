/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author rudolf
 */
public class Fecha {
    private final String formato;
    private String fecha = "";
    
    /**
     * construye la fecha de hoy, en formato yyyy-MM-dd (año-Mes-dia).
     */
    public Fecha(){
        this.formato = "yyyy-MM-dd";
        fecha = new SimpleDateFormat(formato, Locale.getDefault()).format(new Date());
    }

    /**
     *
     * @param fecha, construye una fecha predefinida, en formato yyyy-MM-dd (año-Mes-dia).
     */
    public Fecha(String fecha){
        this.formato = "yyyy-MM-dd";
        this.fecha = fecha;
    }

    /**
     *
     * @return el dia del mes, a la fecha establecida.
     */
    public int getDia(){
        int dia = 0;
        if(!fecha.isEmpty()){
            Date fechaDia = getDate(fecha);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaDia);
            dia = calendario.get(Calendar.DAY_OF_MONTH);
        }else
            dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        
        return dia;
    }

    /**
     *
     * @return el mes del año, a la fecha establecida. los meses empiezan por 0 hasta 11 (ej. 0=enere, 1=febrero, 2=marzo, 3=abril, 4=mayo...., 10=noviembre, 11=diciembre)
     */
    public int getMes(){
        int mes = 0;
        if(!fecha.isEmpty()){
            Date fechaMes = getDate(fecha);            
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaMes);
            mes = calendario.get(Calendar.MONTH);
        }else
            mes = Calendar.getInstance().get(Calendar.MONTH);
        return mes;
    }

    /**
     *
     * @return el año a la fecha establecida.
     */
    public int getAno(){
        int ano = 0;
        if(!fecha.isEmpty()){
            Date fechaAno = getDate(fecha);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaAno);
            ano = calendario.get(Calendar.YEAR);
        }else
            ano = Calendar.getInstance().get(Calendar.YEAR);
        return ano;
    }

    /**
     *
     * @param ano modifica a la fecha, cambiando solo el año.
     */
    public void setAno(int ano){
        if(!fecha.isEmpty()){
            
            int mesFecha = getMes() + 1;
            int diaFecha = getDia();
            
            String mes = "";
            String dia = "";
            
            if(mesFecha >= 0 && mesFecha < 10)
                mes = "0"+mesFecha;
            else
                mes = String.valueOf(mesFecha);
            if(diaFecha >= 0 && diaFecha < 10)
                dia = "0"+diaFecha;
            else
                dia = String.valueOf(diaFecha);
            
            fecha = ano+"-"+mes+"-"+dia;
        }
    }
    
    /**
     *
     * @param mes modifica a la fecha, cambiando el dia del mes deacuerdo a la fecha establecida.
     */
    public void setMes(int mes){
        if(!fecha.isEmpty()){
            
            int ano = getAno();
            int diaFecha = getDia();
            
            String dia = "";
            
            if(diaFecha >= 0 && diaFecha < 10)
                dia = "0"+diaFecha;
            else
                dia = String.valueOf(diaFecha);
            
            if(mes >= 0 && mes < 10)
                fecha = ano+"-0"+mes+"-"+dia;
            else            
                fecha = ano+"-"+mes+"-"+dia;
        }
    }

    /**
     *
     * @param dia modifica a la fecha, cambiando el dia del mes deacuerdo a la fecha establecida.
     */
    public void setDia(int dia){
        if(!fecha.isEmpty()){
            
            int ano = getAno();
            int mesFecha = getMes() + 1;            
            
            String mes = "";
            
            if(mesFecha >= 0 && mesFecha < 10)
                mes = "0"+mesFecha;
            else
                mes = String.valueOf(mesFecha);
            
            if(dia >= 0 && dia < 10)
                fecha = ano+"-"+mes+"-0"+dia;
            else
                fecha = ano+"-"+mes+"-"+dia;
            
        }
    }
    
    /**
     *
     * @param fecha construye un objeto de tipo Date, con el formato de fecha yyyy-MM-dd (año-mes-dia). deacuerdo a la fecha establecida.
     * @return objeto de tipo Date
     */
    public Date getDate(String fecha){
        Date fechaDate = new Date();
        try {
            SimpleDateFormat formateando = new SimpleDateFormat(formato, Locale.getDefault());
            formateando.setLenient(false);
            fechaDate = formateando.parse(fecha);
        } catch (ParseException e) {
            System.out.println("error...! Fecha.getDate(String fecha): "+e.getMessage());
        }
        return fechaDate;
    }

    /**
     *
     * @return objeto de tipo Date, con el formato de fecha yyyy-MM-dd (año-mes-dia). deacuerdo a la fecha establecida.
     */
    public Date getDate(){
        Date fechaDate = new Date();
        try {
            SimpleDateFormat formateando = new SimpleDateFormat(formato+" HH:mm:ss", Locale.getDefault());
            formateando.setLenient(false);
            fechaDate = formateando.parse(fecha+" "+new Hora().getHora());
        } catch (ParseException e) {
            System.out.println("error...! Fecha.getDate(): "+e.getMessage());
        }
        return fechaDate;
    }

    /**
     *
     * @return la edad en numero entero. desde la fecha establecida hasta la fecha actual.
     */
    public int getEdad(){
        Date dateNacimiento = null;
        
        if(fecha.isEmpty())
            dateNacimiento = new Date();
        else
            dateNacimiento = getDate(fecha);
        
        Calendar fechaNacimiento = Calendar.getInstance();
        Calendar fechaActual = Calendar.getInstance();
        
        fechaNacimiento.setTime(dateNacimiento);
        
        int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int edadMeses =fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int edadDias = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if(edadMeses < 0 || (edadMeses == 0 && edadDias < 0)){
            edad--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return edad;
    }

    /**
     *
     * @return la fecha en formato EEE-dd/MMM (ej. LUN-22/FEB)
     */
    public String getFechaDiaMes(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        
        return new SimpleDateFormat("EEE-dd/MMM", Locale.getDefault()).format(calendario.getTime()).toUpperCase();
    }

    /**
     *
     * @return el mes en formato MMMM (ej. FEBRERO) perteneciente a la fecha establecida.
     */
    public String getFechaMes(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        
        return new SimpleDateFormat("MMMM", Locale.getDefault()).format(calendario.getTime()).toUpperCase();
    }

    /**
     *
     * @return el dia en formato EEE (ej. LUN) perteneciente a la fecha establecida.
     */
    public String getFechaDiaCorto(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        
        return new SimpleDateFormat("EEE", Locale.getDefault()).format(calendario.getTime()).toUpperCase();
    }

    /**
     *
     * @return el dia en formato EEEE (ej. LUNES) perteneciente a la fecha establecida.
     */
    public String getFechaDia(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(calendario.getTime()).toUpperCase();
    }
    /**
     *
     * @return el mes y el año de la fecha establecida, en formato MMMM 'de' YYYY (ej. febrero de 2018)
     */
    public String getMesAno(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat("MMMM 'de' YYYY", Locale.getDefault()).format(calendario.getTime()).toLowerCase();
    }

    /**
     *
     * @return la fecha en formato yyyy-MM-dd (año-mes-dia)
     */
    public String getFecha(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat(formato, Locale.getDefault()).format(calendario.getTime());
    }    

    /**
     *
     * @return la fecha completa, en fomato EEEE dd 'de' MMMM 'del' YYYY (ej. lunes 27 de Septiembre del 2018)
     */
    public String getFecha1(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat("EEEE dd 'de' MMMM 'del' YYYY", Locale.getDefault()).format(calendario.getTime()).toLowerCase();
    }

    /**
     *
     * @return la fecha completa, en formato dd 'de' MMMM 'del' YYYY (ej. 28 de Diciembre del 2018)
     */
    public String getFecha2(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat("dd 'de' MMMM 'del' YYYY", Locale.getDefault()).format(calendario.getTime()).toLowerCase();
    }

    /**
     *
     * @return la fecha completa, en formato dd-MMM-YY (ej. 11-MAR-2018)
     */
    public String getFecha3(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat("dd-MMM-YYYY", Locale.getDefault()).format(calendario.getTime()).toUpperCase();
    }

    /**
     *
     * @return la fecha completa en formato yyyy-MM-dd (ej. 2018-05-11)
     */
    public String getFecha4(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendario.getTime()).toUpperCase();
    }

    /**
     *
     * @return la fecha completa, en formato EEEE, dd/MMM/YY (ej. 22/mar/18)
     */
    public String getFecha5(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat("EEEE, dd/MMM/YY", Locale.getDefault()).format(calendario.getTime()).toLowerCase();
    }
    
    /**
     *
     * @return la fecha completa, en formato, EEE-dd/MMM/YYYY (ej. MAR-17/SEP/19)
     */
    public String getFecha6(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        return new SimpleDateFormat("EEE-dd/MMM/YY", Locale.getDefault()).format(calendario.getTime()).toUpperCase();
    }
    
    /**
     *
     * @param fechaConsulta se compara en igualdad con la fecha establecida, compara la igualdad con el dia, mes y año.
     * @return true o false, si los atributos dia, mes y año son iguales entonces devuelve true caso contrario devuelve false.
     */
    public boolean esIgual(Fecha fechaConsulta){
        boolean igual = false;
        if(getDia() == fechaConsulta.getDia() && getMes() == fechaConsulta.getMes() && getAno() == fechaConsulta.getAno())
            igual = true;
        return igual;
    }

    /**
     *
     * @param fechaConsulta se compara con la fecha establecida mediante el metodo before(Date d)
     * @return true si y solo si la fecha establecida es MAYOR a la fecha de consulta. caso contrario devuelve false.
     */
    public boolean esMayor(Fecha fechaConsulta){
        boolean esMayor = false;
        if(getDate().before(fechaConsulta.getDate()))
            esMayor = true;
        return esMayor;
    }

    /**
     *
     * @param fechaConsulta se compara con la fecha establecida mediante el metodo before(Date d)
     * @return true si y solo si la fecha establecida es MENOR a la fecha de consulta. caso contrario devuelve false.
     */
    public boolean esMenor(Fecha fechaConsulta){
        boolean esMenor = false;
        if(fechaConsulta.getDate().before(getDate()))
            esMenor = true;
        return esMenor;
    }
    
    /**
     *
     * @param fechaMenor es restado por la fecha establecida, ej. fechaMayor.restarDias(fechaMenor) 
     * @return los dias restantes de la diferencia entre la fechaMayor.restarDias(fechaMenor) o fechaMenor.restarDias(fechaMayor) el resultado siempre sera un numero positivo
     */
    public int restarDias(Fecha fechaMenor){
        int diasRestados = 0;
        
        GregorianCalendar calendarioMayor = new GregorianCalendar();
        calendarioMayor.setTime(getDate(fecha));
        
        GregorianCalendar calendarioMenor = new GregorianCalendar();        
        calendarioMenor.setTime(fechaMenor.getDate());
                
        if(calendarioMenor.get(Calendar.YEAR) == calendarioMayor.get(Calendar.YEAR)){
            diasRestados = (calendarioMayor.get(Calendar.DAY_OF_YEAR) - calendarioMenor.get(Calendar.DAY_OF_YEAR));
        }else{
            int rangoAnos = Math.abs(calendarioMayor.get(Calendar.YEAR) - calendarioMenor.get(Calendar.YEAR));
        
            for(int i = 0; i <= rangoAnos; i++){
                int diasAno = calendarioMayor.isLeapYear(calendarioMayor.get(Calendar.YEAR)) ? 366 : 365;
                if(i==0){
                    diasRestados += diasAno - calendarioMayor.get(Calendar.DAY_OF_YEAR);
                }else 
                    if(i == rangoAnos){
                        diasRestados += calendarioMenor.get(Calendar.DAY_OF_YEAR);
                    }else{
                        diasRestados += diasAno;
                    }
            }
        }
        return diasRestados;
    }

    /**
     *
     * @return la fecha con el proximo dia a la fecha establecida (ej. la fecha establecida es: 2018-06-07, el proximo dia es: 2018-06-08)
     */
    public Fecha getProximoDia(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        calendario.add(Calendar.DAY_OF_YEAR, 1);        
        
        return new Fecha(new SimpleDateFormat(formato, Locale.getDefault()).format(calendario.getTime()));
    }

    /**
     *
     * @return la fecha con el dia anterior a la fecha establecida. (ej. la fecha establecida es: 2018-06-07, el dia anterior es: 2018-06-06)
     */
    public Fecha getDiaAnterior(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        calendario.add(Calendar.DAY_OF_YEAR, -1);
        
        return new Fecha(new SimpleDateFormat(formato, Locale.getDefault()).format(calendario.getTime()));
    }

    /**
     *
     * @param dias suma o resta a los dias de la fecha establecida.
     * @return la fecha completa, dependiendo de la suma o resta de los dias asignados (ej. la fecha establecida es: 2018-06-07, dias: -5 entonces devuelve la fecha: 2018-06-02 caso contrario dias: 5 retorna fecha: 2018-06-12)
     */
    public Fecha getFechaProximoAnterior(int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        calendario.add(Calendar.DAY_OF_YEAR, dias);
        
        return new Fecha(new SimpleDateFormat(formato, Locale.getDefault()).format(calendario.getTime()));
    }

    /**
     *
     * @return la fecha completa, con el mes anterior a la fecha establecida. (ej. la fecha establecida: 2018-06-07, entonces devuelve la fecha: 2018-05-07)
     */
    public Fecha getMesAnterior(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        calendario.add(Calendar.MONTH, -1);
        
        return new Fecha(new SimpleDateFormat(formato, Locale.getDefault()).format(calendario.getTime()));
    }
    /**
     *
     * @return la fecha completa, con el mes proximo a la toString establecida. (ej. la fecha establecida: 2018-06-07, entonces devuelve la fecha: 2018-07-07)
     */
    public Fecha getMesProximo(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(getDate(fecha));
        calendario.add(Calendar.MONTH, 1);
        
        return new Fecha(new SimpleDateFormat(formato, Locale.getDefault()).format(calendario.getTime()));
    }        

    /**
     *
     * @return la cantidad de dias respecto a la fecha establecida. (ej. junio = 30 dias.)
     */
    public int getCantidadDiasMes(){
        int dias = 0;
        int mes = getMes() + 1;
        int ano = getAno();
        if(mes >= 1 && mes < 12 ){
            switch(mes){
                case 1:  // Enero
                case 3:  // Marzo
                case 5:  // Mayo
                case 7:  // Julio
                case 8:  // Agosto
                case 10:  // Octubre
                case 12: // Diciembre
                return 31;
                case 4:  // Abril
                case 6:  // Junio
                case 9:  // Septiembre
                case 11: // Noviembre
                return 30;
                case 2:  // Febrero
                if ( ((ano%100 == 0) && (ano%400 == 0)) || ((ano%100 != 0) && (ano%  4 == 0)) )
                    dias = 29;  // Año Bisiesto
                else
                    dias = 28;
            }
        }else
            System.out.println("El mes debe estar entre 1 y 12");
        return dias;
    }

    /**
     *
     * @param nombreMes hace un cambio, el nombre del mes por el numero del 1 al 12 que representa los meses del año.
     * @return un numero representado el mes que ha ingresado. (ej. enero devuelve 1, septiembre devuelve 9.)
     */
    public int numeroMes(String nombreMes){
        int numeroMes = 0;
        switch(nombreMes){
            case "enero":  // Enero
                numeroMes = 1;
            break;
            case "marzo":  // Marzo
                numeroMes = 3;
            break;
            case "mayo":  // Mayo
                numeroMes = 5;
            break;
            case "julio":  // Julio
                numeroMes = 7;
            break;    
            case "agosto":  // Agosto
                numeroMes = 8;
            break;
            case "octubre":  // Octubre
                numeroMes = 10;
            break;
            case "diciembre": // Diciembre
                numeroMes = 12;
            break;            
            case "abril":  // Abril
                numeroMes = 4;
            break;
            case "junio":  // Junio
                numeroMes = 6;
            break;
            case "septiembre":  // Septiembre
                numeroMes = 9;
            break;
            case "noviembre": // Noviembre
                numeroMes = 11;
            break;
            case "febrero":  // Febrero
                numeroMes = 2;
            break;
        }
        return numeroMes;       
    }

    /**
     *
     * @return true si y solo si la fecha ingresada en el metodo constructor esta vacia caso contrario devuelve false. (ej. Fecha fecha = new fecha("") se ha creado una fecha con referencia al dia de hoy. )
     */
    public boolean estaVacia(){
        boolean verificador = false;
        if(fecha.isEmpty())
            verificador = true;
        return verificador;
    }

    /**
     *
     * @return la fecha completa, ingresada en el metodo constructor al inicializar el objecto.
     */
    public String fecha(){
        return fecha;
    }

    /**
     * obtener el mes completo de la fecha establecida, recibe dos valores de tipo entero, el numero de mes y el numero de año.
     * @param mes numero del mes de la fecha establecida. ej. jun = 6, jul = 7, agosto = 8, ... etc.
     * @param ano numero de año de la fecha establecida.
     * @return la fecha con el primer dia del mes y del año establecido. ej.: 2018-07-01
     */
    public Fecha getMesFecha(int mes, int ano){
        Fecha primeraFecha = new Fecha();
        mes++;        
        String cadenaFecha = "";
        if(mes >= 0 && mes < 10)
            cadenaFecha = ano+"-0"+mes+"-01";
        else
            cadenaFecha = ano+"-"+mes+"-01";

        primeraFecha = new Fecha(cadenaFecha);
        return primeraFecha;
    }
    /*public static void main(String[] arg){
        Fecha fo = new Fecha();
        System.out.println("dia="+fo.getDia());
        System.out.println("la fecha es: "+fo.fecha());
        Fecha fecha = new Fecha("2016-02-22");
        Fecha f = new Fecha();
        fo.setAno(1994);
        fo.setDia(15);
        fo.setMes(12);
        System.out.println("la fecha con el año :"+1994+" es: "+fo.fecha);
        System.out.println(" y el año es: "+fo.getAno());
        System.out.println("LA FECHA CON EL FORMATO dd-MMM-YY es: "+f.getFecha3());
        System.out.println("el ano: "+fecha.getAno());
        System.out.println("el mes: "+f.getMes());
        System.out.println("el dia es: "+fecha.getDia());
        System.out.println("el Date es: "+fecha.getDate());
        System.out.println("es: "+fecha.getFechaMes());
        System.out.println("fecha ANTERIOR ES: "+fecha.getDiaAnterior().getFecha5());
        
        

        Fecha fecha1 = new Fecha();
        if(fecha1.esIgual(new Fecha("2018-06-07"))){
            System.out.println("ES IGUAL");
        }else
            System.out.println("NO ES IGUAL");
        System.out.println("la resta es: "+fecha1.restarDias(new Fecha("2018-02-22")));
        System.out.println("la cantidad de dias del mes a la fecha 2018-06-07 es: "+new Fecha().getCantidadDiasMes());
    }*/
}

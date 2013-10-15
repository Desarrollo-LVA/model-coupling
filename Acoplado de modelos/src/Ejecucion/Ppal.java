package Ejecucion;

import Base.ModeloMalla;
import Interpolacion.Shepard;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ppal 
{
    
    public static void main(String[] args) 
    {
        File archivoEntrada = new File("F:\\Proyectos\\Acoplado PEMSA\\Modulo_Calculo_Acoplado\\Archivo de estampado.nas");
        File archivoSalida = new File("F:\\Proyectos\\Acoplado PEMSA\\Modulo_Calculo_Acoplado\\Archivo de calculo.bdf");

        ModeloMalla fuente = new ModeloMalla(archivoEntrada);
        ModeloMalla destino = new ModeloMalla(archivoSalida);
        try 
        {            
            fuente.cargar();
            fuente.cargarEspesores(new File("F:\\Proyectos\\Acoplado PEMSA\\Modulo_Calculo_Acoplado\\Espesores.asc"));
            destino.cargar();
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fuente.generaCentros();
        destino.generaCentros();
        
        Shepard shepard = new Shepard(fuente,destino);
        System.out.println(fuente.estadistico());
        
//        System.out.println("Original por vertices y global");
//        shepard.originalVerticesNoLocal(12.0);        
//        System.out.println(destino.estadistico());
//        
//        System.out.println("Original por centros y global");
//        shepard.originalCentrosNoLocal(12.0);
//        System.out.println(destino.estadistico());
//        
//        System.out.println("Original por vertices y local");
//        shepard.originalVerticesLocal(12.0,3.0);
//        System.out.println(destino.estadistico());
//        
//        System.out.println("Original por centros y local");
//        shepard.originalCentrosLocal(12.0,3.0);
//        System.out.println(destino.estadistico());
//        
//        System.out.println("R mayuscula por centros y local");
//        shepard.rMayusCentrosLocal(12.0,10.0);
//        System.out.println(destino.estadistico());
//        
//        System.out.println("R mayuscula por centros y global");
//        shepard.rMayusCentrosNoLocal(12.0);
//        System.out.println(destino.estadistico());
//        
        System.out.println("R mayuscula por vertices y local");
        shepard.rMayusVerticesLocal(28.0);
        System.out.println(destino.estadistico());
        try 
        {
            destino.generarCSV("Espesores generados R mayuscula.csv");
    //        System.out.println("R mayuscula por vertices y global");
    //        shepard.rMayusVerticesNoLocal();
    //        System.out.println(destino.estadistico());
              
    //        System.out.println("R mayuscula por vertices y global");
    //        shepard.originalVerticesNoLocal(12.0);
    //        System.out.println(destino.estadistico());
    //        
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

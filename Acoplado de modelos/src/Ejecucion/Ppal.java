package Ejecucion;

import Base.ModeloMalla;
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
        //File archivoSalida = new File("F:\\Proyectos\\Acoplado PEMSA\\Modulo_Calculo_Acoplado\\Archivo de calculo.bdf");

        ModeloMalla fuente = new ModeloMalla(archivoEntrada);
        //ModeloMalla destino = new ModeloMalla(archivoSalida);
        try 
        {            
            fuente.cargar();
          //  destino.cargar();
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Ppal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

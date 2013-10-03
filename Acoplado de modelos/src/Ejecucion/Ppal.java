package Ejecucion;

import Base.ModeloMalla;
import java.io.File;

public class Ppal 
{
    File archivoEntrada = new File("F:\\Proyectos\\Acoplado PEMSA\\Modulo_Calculo_Acoplado\\Archivo de estampado.nas");
    File archivoSalida = new File("F:\\Proyectos\\Acoplado PEMSA\\Modulo_Calculo_Acoplado\\Archivo de calculo.bdf");
    
    ModeloMalla fuente = new ModeloMalla(archivoEntrada);
    ModeloMalla destino = new ModeloMalla(archivoSalida);
}

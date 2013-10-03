
package Base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class ModeloMalla 
{
    HashMap elementos, nodos;
    File fuente;

    public ModeloMalla(File fuente) 
    {
        this.fuente = fuente;
    }
    
    void cargar() throws FileNotFoundException, IOException
    {
        BufferedReader bf = new BufferedReader(new FileReader(fuente));
        String nombre = fuente.getName();
        if(nombre.endsWith(".nas"))
            cargaNAS(bf);
        else
            if(nombre.endsWith(".bdf"))
                cargaBDF(bf);
    }

    private void cargaNAS(BufferedReader bf) 
    {
        
    }

    private void cargaBDF(BufferedReader bf) throws IOException 
    {
        String linea;
        int indice;
        double x,y,z;
        Nodo nodo;
        do
        {
            linea = bf.readLine();
            if(linea.startsWith("GRID"))
            {
                indice = Integer.parseInt(linea.substring(9, 14));
                x = Double.parseDouble(linea.substring(24,31));
                y = Double.parseDouble(linea.substring(32,39));
                z = Double.parseDouble(linea.substring(39,46));
                nodo = new Nodo(new double[]{x,y,z});
                nodos.put(new Integer(indice), nodo);
            }
           /* else
                if(linea.startsWith("CQUAD4"))
                    
                else
                    if(linea.startsWith("CTRIA3"))
                        
                    
             */               
        }while(!linea.startsWith("ENDDATA"));
    }
}

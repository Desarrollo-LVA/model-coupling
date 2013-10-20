
package Base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class ModeloMalla 
{
    HashMap elementos, nodos;
    File fuente;

    public ModeloMalla(File fuente) 
    {
        this.fuente = fuente;
        elementos = new HashMap(10000);
        nodos = new HashMap(10000);
    }
    
    public void cargar() throws FileNotFoundException, IOException
    {
        BufferedReader bf = new BufferedReader(new FileReader(fuente));
        String nombre = fuente.getName();
        if(nombre.endsWith(".nas"))
            cargaNAS(bf);
        else
            if(nombre.endsWith(".bdf"))
                cargaBDF(bf);
        bf.close();
    }

    private void cargaNAS(BufferedReader bf) throws IOException 
    {
        String linea;
        int indice;
        double x,y,z,w;
        Nodo nodo;
        Elemento elemento;
        
        do
        {
            linea = bf.readLine();
            
            if(linea.startsWith("GRID"))
            {
                indice = Integer.parseInt(linea.substring(19, 24));
                
                x = Double.parseDouble(linea.substring(41,56));
                y = Double.parseDouble(linea.substring(56,72));
                linea = bf.readLine();
                z = Double.parseDouble(linea.substring(9,24));
                nodo = new Nodo(new double[]{x,y,z});
                nodos.put(new Integer(indice), nodo);
            }
            else
                if(linea.startsWith("CQUAD4"))
                {
                    indice = Integer.parseInt(linea.substring(11, 16));
                    x = Double.parseDouble(linea.substring(27,32));
                    y = Double.parseDouble(linea.substring(35,40));
                    z = Double.parseDouble(linea.substring(43,48));
                    w = Double.parseDouble(linea.substring(51));
                    
                    elemento = new Elemento(new int[]{(int)x,(int)y,(int)z,(int)w});
                    elementos.put(new Integer(indice), elemento);
                }   
                else
                    if(linea.startsWith("CTRIA3"))
                    {
                        indice = Integer.parseInt(linea.substring(9, 14));
                        x = Double.parseDouble(linea.substring(25,32));
                        y = Double.parseDouble(linea.substring(33,40));
                        z = Double.parseDouble(linea.substring(41));

                        elemento = new Elemento(new int[]{(int)x,(int)y,(int)z});
                        elementos.put(new Integer(indice), elemento);
                    }
        }while(!linea.startsWith("ENDDATA"));
    }

    private void cargaBDF(BufferedReader bf) throws IOException 
    {
        String linea;
        int indice;
        double x,y,z,w;
        Nodo nodo;
        Elemento elemento;
        do
        {
            linea = bf.readLine();
            if(linea.startsWith("GRID"))
            {
                indice = Integer.parseInt(linea.substring(9, 14));
                
                x = Double.parseDouble(linea.substring(24,31));
                y = Double.parseDouble(linea.substring(32,40));
                z = Double.parseDouble(linea.substring(40));
                
                
                nodo = new Nodo(new double[]{x,y,z});
                nodos.put(new Integer(indice), nodo);
            }
            else
                if(linea.startsWith("CQUAD4"))
                {
                    indice = Integer.parseInt(linea.substring(9, 14));
                    x = Double.parseDouble(linea.substring(25,32));
                    y = Double.parseDouble(linea.substring(33,40));
                    z = Double.parseDouble(linea.substring(41,48));
                    w = Double.parseDouble(linea.substring(49));
                    
                    elemento = new Elemento(new int[]{(int)x,(int)y,(int)z,(int)w});
                    elementos.put(new Integer(indice), elemento);
                }   
                else
                    if(linea.startsWith("CTRIA3"))
                    {
                        indice = Integer.parseInt(linea.substring(9, 14));
                        x = Double.parseDouble(linea.substring(25,32));
                        y = Double.parseDouble(linea.substring(33,40));
                        z = Double.parseDouble(linea.substring(41));

                        elemento = new Elemento(new int[]{(int)x,(int)y,(int)z});
                        elementos.put(new Integer(indice), elemento);
                    }
        }while(!linea.startsWith("ENDDATA"));
    }
    
    public void cargarEspesores(File archivo) throws FileNotFoundException, IOException
    {
        String nombre = archivo.getName();
        BufferedReader bf = new BufferedReader(new FileReader(archivo));
        
        if(nombre.endsWith(".asc"))
            cargaASC(bf);
        else
            if(nombre.endsWith(".csv"))
                cargaCSV(bf); 
        bf.close();
    }

    private void cargaASC(BufferedReader bf) throws IOException {
        String linea,temp;
        int indice;
        double valor;
            
        do
        {
            linea = bf.readLine();
            if(linea.length() == 21)
            {
                temp = linea.substring(3, 8);
                indice = Integer.parseInt(temp);
                valor = Double.parseDouble(linea.substring(9));
                Elemento get = (Elemento)elementos.get(new Integer(indice));
                get.propiedad = valor;
                
            }
            
            
        }while(!linea.endsWith("       0"));
    }

    private void cargaCSV(BufferedReader bf) throws IOException 
    {
        String linea,temp;
        int indice;
        double valor;
        
        do
        {
            linea = bf.readLine();
            if(linea.startsWith("Elem"))
            {
                indice = Integer.parseInt(linea.substring(6,11));
                valor = Double.parseDouble(linea.substring(12));
                Elemento get = (Elemento)elementos.get(new Integer(indice));
                get.propiedad = valor;
                
            }
        }while(!linea.isEmpty());
    }

    public Iterator listaElementos() 
    {
        return elementos.entrySet().iterator();
    }

    public Nodo getNodo(int vertFuente) 
    {
        return (Nodo)nodos.get(new Integer(vertFuente));
    }

    public void muestra() 
    {
        System.out.println(fuente.getName());
        System.out.println("Elementos: "+elementos.size());
        System.out.println("Nodos: "+nodos.size()+"\n");
        Iterator iterator = elementos.entrySet().iterator();
        double min=100,max=0;
        while(iterator.hasNext())
        {
            Map.Entry next = (Map.Entry) iterator.next();
            Object value = next.getValue();
            double valor = ((Elemento)value).valor();
            min = (min > valor)?valor:min;
            max = (max < valor)?valor:max;
            //System.out.println(next.getKey() + "\t" +value);
            
        }
        System.out.println("Máximo: "+max+" Mínimo: "+min);
    }

    public void generaCentros() 
    {
        Iterator listaElementos = listaElementos();
        Nodo n;
        while(listaElementos.hasNext())
        {
            Elemento next = (Elemento) ((Map.Entry)listaElementos.next()).getValue();
            n = new Nodo(new double[]{0,0,0});
            for(int nodo : next.vertices())
            {
                Nodo nodo1 = getNodo(nodo);
                for (int i = 0; i < 3;i++) 
                {
                    n.vertices[i] += nodo1.vertices[i]/nodo1.vertices.length;
                }
            }
            
            next.centro(n);
        }
    }

    public int numeElementos() 
    {
        return this.elementos.size();
    }
    
    public String estadistico()
    {
        Object[] toArray = elementos.values().toArray();
        double media = 0,desv = 0;
        
        for (Object elemento:toArray) 
        {
            media += ((Elemento)elemento).valor();
        }
        media = media / toArray.length;
        
        for(Object elemento: toArray)
        {
            desv += Math.pow(((Elemento)elemento).valor() - media,2);
        }
        
        desv = Math.sqrt(desv / toArray.length);
        return "Media = "+ media +" Desviacion = "+desv;
    }
    
    public File generarASC(String nombre) throws IOException
    {
        String parent = fuente.getParent();
        File salida = new File(parent+"\\"+nombre);
        if(!salida.exists())
            salida.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(salida));
        String linea;
        Iterator listaElementos = listaElementos();
        while(listaElementos.hasNext())
        {
            Map.Entry entrada = (Map.Entry)listaElementos.next();
            linea = "   "+ entrada.getKey() +"  "+((Elemento)entrada.getValue()).valor();
            bw.write(linea);
            bw.newLine();
        }
        bw.close();
        return salida;
    }
    
    public File generarCSV(String nombre) throws IOException
    {
        String parent = fuente.getParent();
        File salida = new File(parent+"\\"+nombre);
        if(!salida.exists())
            salida.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(salida));
        String linea;
        Iterator listaElementos = listaElementos();
        while(listaElementos.hasNext())
        {
            Map.Entry entrada = (Map.Entry)listaElementos.next();
            linea = "Elem  "+ entrada.getKey() +","+((Elemento)entrada.getValue()).valor();
            bw.write(linea);
            bw.newLine();
        }
        bw.close();
        return salida;
    }
}

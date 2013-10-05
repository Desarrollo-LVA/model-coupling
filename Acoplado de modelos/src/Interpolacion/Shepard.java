package Interpolacion;

import Base.Elemento;
import Base.ModeloMalla;
import Base.Nodo;
import java.util.Iterator;
import java.util.Map.Entry;

public class Shepard 
{
    ModeloMalla fuente,destino;

    public Shepard(ModeloMalla fuente, ModeloMalla destino) 
    {
        this.fuente = fuente;
        this.destino = destino;
    }
    
    public void originalVerticesNoLocal(double paramDistancia)
    {
        Iterator elemFuente;
        Elemento entrada,salida;
        Iterator elemDestino = destino.listaElementos();
        double sumaDistancias,sumaValores,distancia,ponderacion;
        
        while(elemDestino.hasNext()) //Recorre elementos destino
        {
            sumaDistancias = sumaValores = 0; // inicializa sumas
            salida = (Elemento)((Entry)elemDestino.next()).getValue();
            elemFuente = fuente.listaElementos(); //carga fuentes
            while(elemFuente.hasNext()) //recorre elementos fuente
            {
                entrada = (Elemento)((Entry)elemFuente.next()).getValue();
                distancia = distancia(entrada,salida);
                ponderacion = Math.pow(distancia, paramDistancia);
                
                sumaDistancias += 1/ponderacion;
                sumaValores += entrada.valor() / ponderacion;
            }
            
            salida.valor(sumaValores / sumaDistancias);
        }
    }
    
    public void originalVerticesLocal(double paramDistancia,double paramRadio)
    {
        Iterator elemFuente;
        Elemento entrada,salida;
        Iterator elemDestino = destino.listaElementos();
        double sumaDistancias,sumaValores,distancia,ponderacion;
        
        while(elemDestino.hasNext()) //Recorre elementos destino
        {
            sumaDistancias = sumaValores = 0; // inicializa sumas
            salida = (Elemento)((Entry)elemDestino.next()).getValue();
            elemFuente = fuente.listaElementos(); //carga fuentes
            while(elemFuente.hasNext()) //recorre elementos fuente
            {
                entrada = (Elemento)((Entry)elemFuente.next()).getValue();
                distancia = distancia(entrada,salida);
                if(distancia < paramRadio)
                {
                    ponderacion = Math.pow(distancia, paramDistancia);

                    sumaDistancias += 1/ponderacion;
                    sumaValores += entrada.valor() / ponderacion;
                }
            }
            
            salida.valor(sumaValores / sumaDistancias);
        }
    }
    
    public void originalCentrosNoLocal(double paramDistancia)
    {
        Iterator elemFuente;
        Elemento entrada,salida;
        
        Iterator elemDestino = destino.listaElementos();
        double sumaDistancias,sumaValores,distancia,ponderacion;
        
        fuente.generaCentros();
        destino.generaCentros();
        while(elemDestino.hasNext()) //Recorre elementos destino
        {
            sumaDistancias = sumaValores = 0; // inicializa sumas
            salida = (Elemento)((Entry)elemDestino.next()).getValue();
            elemFuente = fuente.listaElementos(); //carga fuentes
            while(elemFuente.hasNext()) //recorre elementos fuente
            {
                entrada = (Elemento)((Entry)elemFuente.next()).getValue();
                distancia = distanciaCentros(entrada,salida);
                ponderacion = Math.pow(distancia, paramDistancia);

                sumaDistancias += 1/ponderacion;
                sumaValores += entrada.valor() / ponderacion;
                
            }
            
            salida.valor(sumaValores / sumaDistancias);
        }
    }
    
    public void originalCentrosLocal(double paramDistancia,double paramRadio)
    {
        
    }
    
    public void rMayusVerticesNoLocal(double paramDistancia)
    {
        
    }
    
    public void rMayusVerticesLocal(double paramDistanca,double paramRadio)
    {
        
    }
    
    public void rMayusCentrosNoLocal(double paramDistancia)
    {
        
    }
    
    public void rMayusCentrosLocal(double paramDistancia,double paramRadio)
    {
        
    }
    public double distancia(Elemento entrada, Elemento salida) 
    {
        double distancia = 0;
        int[] vEnt = entrada.vertices();
        int[] vSal = salida.vertices();
        Nodo nodoFu,nodoDe;
        for(int vertFuente:vEnt)
        {
            nodoFu = fuente.getNodo(vertFuente);
            for(int vertSalida:vSal)
            {
                nodoDe = destino.getNodo(vertSalida);
                distancia += Nodo.distancia(nodoFu,nodoDe);
            }
        }
        return distancia / (vEnt.length * vSal.length);
    }

    private double distanciaCentros(Elemento entrada, Elemento salida) 
    {
        return Nodo.distancia(entrada.centro(), salida.centro());
    }
}

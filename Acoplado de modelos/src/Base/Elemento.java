package Base;

public class Elemento 
{
    int[] nodos;
    double propiedad;
    Nodo centro;

    public Elemento(int[] nodos) 
    {
        this.nodos = nodos;
        propiedad = 0;
    }

    public int[] vertices() 
    {
        return nodos;
    }

    public double valor() 
    {
        return propiedad;
    }
    
    public void valor(double valor)
    {
        propiedad = valor;
    }
    
    public String toString()
    {
        String salida = "";
        for (int i = 0; i < nodos.length; i++) 
        {
            salida += nodos[i] + " | ";
        }
        salida += "valor = "+propiedad;
        return salida;
    }

    void centro(Nodo n) {
        centro = n;
    }
    
    public Nodo centro()
    {
        return centro;
    }
}

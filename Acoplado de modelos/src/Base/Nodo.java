package Base;

public class Nodo 
{
    double[] vertices;

    public Nodo(double[] vertices) 
    {
        this.vertices = vertices;
    }
    
    public static double distancia(Nodo a,Nodo b)
    {
        double total = 0;
        for (int i = 0; i < 3; i++) 
        {
            total += Math.pow(a.vertices[i] - b.vertices[i],2);
        }
        return Math.sqrt(total);
    }
    
    
}

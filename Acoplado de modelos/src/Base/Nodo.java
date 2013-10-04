package Base;

public class Nodo 
{
    double[] vertices;

    public Nodo(double[] vertices) 
    {
        this.vertices = vertices;
        for (int i = 0; i < vertices.length; i++) 
        {
            System.out.print(vertices[i] +" ");
        }
        System.out.println("");
    }
    
    
}

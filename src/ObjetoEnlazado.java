
public class ObjetoEnlazado {
	IComparable contenido1;
	
	
	public ObjetoEnlazado(IComparable obj){
		contenido1 = obj;
	}
	  public IComparable contenido = null;
      public ObjetoEnlazado Anterior = null;
      public ObjetoEnlazado Siguiente = null;
}

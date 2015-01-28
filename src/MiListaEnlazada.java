
public class MiListaEnlazada {
	public ObjetoEnlazado primerObjeto = null;
    public int Count = 0;


    public void Add(IComparable obj)
    {
        ObjetoEnlazado nuevoObjeto = new ObjetoEnlazado(obj) {  };
        if (primerObjeto == null)
        {
            primerObjeto = nuevoObjeto;
            Count++;
            return;
        }
        else
        {
            ObjetoEnlazado aux = primerObjeto;
            while (aux.Siguiente != null)
            {
                aux = aux.Siguiente;
            }
            aux.Siguiente = nuevoObjeto;
            nuevoObjeto.Anterior = aux;
        }
        Count++;
    }
    public IComparable getMiListaEnlazada(int index)
    {
        
            ObjetoEnlazado auxi = primerObjeto;
            for (int i = 0; i < index; i++)
            {
                auxi = auxi.Siguiente;
            }

            return auxi.contenido;
        }
    public void setMiListaEnlazada(int index)
        {
            ObjetoEnlazado auxi = primerObjeto;
            for (int i = 0; i < index; i++)
            {
                auxi = auxi.Siguiente;
            }

          auxi.contenido = getMiListaEnlazada(index);
        }
    
    public void Remove(IComparable borrar)
    {
        ObjetoEnlazado aux = primerObjeto;
        while (aux.contenido != borrar)
        {
            aux = aux.Siguiente;
            if (aux == null)
            {
                return;
            }
        }
        if (aux.Anterior != null)
        {
            aux.Anterior.Siguiente = aux.Siguiente;
        }
        else
        {
            primerObjeto = aux.Siguiente;
        }
        if (aux.Siguiente != null)
        {
            aux.Siguiente.Anterior = aux.Anterior;
        }
        Count--;
    }
    public void Remove(int index)
    {
        ObjetoEnlazado aux = primerObjeto;
        for (int i = 0; i < index; i++)
        {
            aux = aux.Siguiente;
        }
        if (aux.Anterior != null)
        {
            aux.Anterior.Siguiente = aux.Siguiente;
        }
        else
        {
            primerObjeto = aux.Siguiente;
        }
        if (aux.Siguiente != null)
        {
            aux.Siguiente.Anterior = aux.Anterior;
        }
        Count--;
    }
    public void Clear()
    {
        primerObjeto = null;
        Count = 0;
    }

    public void Sort()
    {
        IComparable[] ordenado = Quicksort(ToArray(), 0, Count - 1);
        Clear();
        for (int i = 0; i < ordenado.length; i++)
        {
            Add(ordenado[i]);
        }
    }


    public IComparable[] ToArray()
    {
        IComparable[] dev = new IComparable[Count];
        for (int i = 0; i < Count; i++)
        {
            dev[i] = getMiListaEnlazada(i);
        }
        return dev;
    }


    public IComparable[] Quicksort(IComparable[] vector, int izquierda, int derecha)
    {
        // estas dos variables nos sirven para ir achicando la zona de comparación 
        int i = izquierda;
        int j = derecha;

        // tomamos este elemento para comparar todos los demás contra él
        // los números menores deberán quedar a la izquierda de él
        // y los mayores a la derecha
        // este pivot podría ser cualquier otro, elegimos el medio por practicidad
        IComparable pivot = vector[(izquierda + derecha) / 2];

        // como a este método se lo llama recursivamente,        
        // si i es mayor que j, significa que nos quedamos sin zona de comparación
        while (i <= j)
        {
            // mientras que los elmeentos sean menores que el pivot
            // achicamos la zona de comparación izquierda
            while (vector[i].compareTo(pivot) < 0)
            {
                i++;
            }

            // mientras que los elementos sean mayores que el pivot
            // achicamos la zona de comparación derecha
            while (vector[j].compareTo(pivot) > 0)
            {
                j--;
            }

            // si i es menor que j, significa que algún elemento está del lado equivocado del pivot
            if (i <= j)
            {
                // intercambiamos los elementos
                IComparable tmp = vector[i];
                vector[i] = vector[j];
                vector[j] = tmp;

                //achicamos la zona de comparación
                i++;
                j--;
            }
        }

        // si es posible, hacemos una llamada recursiva para ordenar lo que quedó a la izquierda del pivot
        if (izquierda < j)
        {
            Quicksort(vector, izquierda, j);
        }

        // si es posible, hacemos una llamada recursiva para ordenar lo que quedó a la derecha del pivot
        if (i < derecha)
        {
            Quicksort(vector, i, derecha);
        }
        return vector;
    }
    public String toString()
    {
        String cadena = "";
        ObjetoEnlazado auxiliar = primerObjeto;
        while (auxiliar != null)
        {
            cadena += auxiliar.contenido.ToString() + ", ";
            auxiliar = auxiliar.Siguiente;
        }
        return cadena;
    }
}


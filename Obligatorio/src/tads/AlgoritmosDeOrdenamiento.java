package tads;

public class AlgoritmosDeOrdenamiento {

    public void seleccion(int[] array){
        int aux=0;
        for (int i=0; i<array.length-1;i++){
            int minimo=i;
            for (int j=i+1;j<array.length;j++){
                if (array[j]<array[minimo]){
                    minimo=j;
                }
            }
            aux=array[i];
            array[i]=array[minimo];
            array[minimo]=aux;
        }

    }

    public void bubbleSort(int[] array){
        int aux=0;
        for (int i=0; i<array.length-1; i++){
            for (int j=0;j<array.length-1; j++) {
                if (array[j]>array[j+1]){
                    aux=array[j];
                    array[j]=array[j+1];
                    array[j+1]=aux;
                }
            }
        }
    }

    public void insercion(int[] array){
        int aux=0,position=0;
        for (int i=0;i<array.length;i++){
            position = i;
            aux = array[i];
            while (position>0 && array[i-1]>aux) {
                array[position]=array[position-1];
                position--;
            }
            array[position]=aux;
        }
    }

    public void shellSort(int[] array){
        int aux=0,salto = array.length/2;
        while (salto>0){
            for (int i=salto;i<array.length;i++){
                int j=i-salto;
                while(j>=0){
                    int k = j+salto;
                    if (array[j]<=array[k]){
                        break;
                    } else {
                        aux = array[j];
                        array[j]=array[k];
                        array[k]=aux;
                        j-=salto;
                    }
                }
            }
            salto=salto/2;
        }
    }

    public void heapSort(int[] array){
        int[] arrayFinal= new int[array.length];
        for (int i=0; i<array.length-1;i++){
            int minimo=i;
            for (int j=i+1;j<array.length;j++){
                if (array[j]<array[minimo]){
                    minimo=j;
                }
            }
            arrayFinal[minimo]=array[minimo];
            //array.remove(minimo);
        }
    }

    public void mergeSort(int[] arrayA, int[] arrayB){  //array A y B previamente ordenados como por ejemplo con bubble sort.
        int i,j,k;
        int arrayC[] = new int[arrayA.length+arrayB.length];
        for (i=j=k=0;i<arrayA.length && j<arrayB.length;k++){
            if (arrayA[i]<arrayB[j]){
                arrayC[k] = arrayA[i];
                i++;
            } else {
                arrayC[k] = arrayB[j];
                j++;
            }
        }
        for (; i<arrayA.length;i++,k++){
            arrayC[k]=arrayA[i];
        }
        for (;j<arrayB.length;j++,k++){
            arrayC[k]=arrayB[j];
        }
    }

    public void quickSort(int[] array,int primero,int ultimo){
        int i=primero;
        int j=ultimo;
        int aux;
        int pivote=array[(primero+ultimo)/2];
        do {
            while (array[i]<pivote){
                i++;
            }
            while (array[j]>pivote){
                j--;
            }
            if (i<=j) {
                aux=array[i];
                array[i]=array[j];
                array[j]=aux;
                i++;
                j--;
            }
        } while (i<=j);
        if (primero <j){
            quickSort(array,primero,j);
        }
        if (i<ultimo){
            quickSort(array,i,ultimo);
        }
    }
}

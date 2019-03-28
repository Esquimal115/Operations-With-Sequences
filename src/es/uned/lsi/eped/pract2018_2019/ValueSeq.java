package es.uned.lsi.eped.pract2018_2019;

import es.uned.lsi.eped.DataStructures.Stack;

public class ValueSeq extends Value {

    private String value;
    private boolean acarreo=false;
    private Stack<Integer> op1 = new Stack<>();
    private Stack<Integer> op2 = new Stack<>();
    private Stack<Integer> result = new Stack<>();

	/* Constructor: recibe un String con el valor numérico */
	public ValueSeq(String s) {
	    this.value = s;
	}
	
	/* Método que transforma el valor numérico en un String */
	public String toString() {

	    return value.toString();
	}

	/* Método que modifica el valor numérico llamante, sumándole el valor numérico parámetro */
	public void addValue(Value n) {

	    String valorIni = value;
	    String valorSum = n.toString();

	    for (int i = 0; i<valorIni.length(); i ++){

	        op1.push(Integer.parseInt(String.valueOf(valorIni.charAt(i))));
        }

	    for (int i = 0; i< valorSum.length(); i++){

	        op2.push(Integer.parseInt(String.valueOf(valorSum.charAt(i))));
        }

	    /* Comprobamos que ambas pilas tienen el mismo tamaño
        *  para que no se produzca un NullPointerException*/

       while (!op1.isEmpty() || !op2.isEmpty()){

           // Con los siguientes if's controlamos que ambas
           // pilas tengan el mismo tamaño
           if (op1.isEmpty() && !op2.isEmpty()){
               op1.push(0);
           }
           if (!op1.isEmpty() && op2.isEmpty()){
               op2.push(0);
           }


           int aux1 = op1.getTop();
           int aux2 = op2.getTop();
           op1.pop();
           op2.pop();

           int res = aux1 + aux2;

           if (acarreo){
               res = res +1;
               acarreo=false;
           }

           if (res >= 10){
            res = res-10;
            acarreo= true;
            result.push(res);
           }else{
               result.push(res);
               acarreo=false;
           }
       }

       /* Con esta condicion controlamos que si se han vaciado las
        * pilas pero el acarreo esta a true añadimos un "1" a la pila resultado*/
       if (op1.isEmpty() && op2.isEmpty() && acarreo==true){
           result.push(1);
       }


                this.value="";
               while (!result.isEmpty()){
                   int resul = result.getTop();
                   this.value = this.value + resul;
                   //System.out.print(value);
                   result.pop();
               }







	}

	/* Método que modifica el valor numérico llamante, restándole el valor numérico parámetro */
	/* Sabemos que el mayor es el valor numérico llamante */
	public void subValue(Value n) {
	}

	/* Método que modifica el valor numérico llamante, restándolo del valor numérico parámetro */
	/* Sabemos que el mayor es el valor numérico parámetro */
	public void subFromValue(Value n) {
	}

	/* Método que modifica el valor numérico llamante, multiplicándolo por el valor numérico parámetro */
	public void multValue(Value n) {
	}

	/* Método que indica si el valor numérico llamante es mayor que el valor numérico parámetro */
	public boolean greater(Value n) {
		return false;
	}

	/* Método que indica si el valor numérico es cero */
	public boolean isZero() {
		return false;
	}

}

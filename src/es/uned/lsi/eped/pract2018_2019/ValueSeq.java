package es.uned.lsi.eped.pract2018_2019;

import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.Stack;

public class ValueSeq extends Value {

    private String value;
    private boolean acarreo = false;
    private Stack<Integer> op1 = new Stack<>();
    private Stack<Integer> op2 = new Stack<>();
    private Stack<Integer> result = new Stack<>();

    /* Constructor: recibe un String con el valor numérico */
    public ValueSeq(String s) {
        this.value = s;
    }

    /* Método que transforma el valor numérico en un String */
    public String toString() {

        return value;
    }

    /* Método que modifica el valor numérico llamante, sumándole el valor numérico parámetro */
    public void addValue(Value n) {

        String valorIni = value;
        String valorSum =((ValueSeq)n).value;

        apilaOperandos(valorIni, valorSum);

        /* Comprobamos que ambas pilas tienen el mismo tamaño
         *  para que no se produzca un NullPointerException */

        while (!op1.isEmpty() || !op2.isEmpty()) {

            int aux1, aux2;

            // Con este metodo anyadimos 0's a la pila en caso de que no tengan el mismo tamaño
            anyadeZeros();

            aux1 = op1.getTop();
            aux2 = op2.getTop();
            op1.pop();
            op2.pop();

            int res = aux1 + aux2;

            if (acarreo) {
                res = res + 1;
                acarreo = false;
            }

            if (res >= 10) {
                res = res - 10;
                acarreo = true;
                result.push(res);
            } else {
                result.push(res);
                acarreo = false;
            }
        }

        /* Con esta condicion controlamos que si se han vaciado las
         * pilas pero el acarreo esta a true añadimos un "1" a la pila resultado*/

        if (op1.isEmpty() && op2.isEmpty() && acarreo == true) {
            result.push(1);
            acarreo = false;
        }
        this.value = "";

        while (!result.isEmpty()) {
            int resul = result.getTop();
            this.value = this.value + resul;
            result.pop();
        }
    }

    /* Método que modifica el valor numérico llamante, restándole el valor numérico parámetro
     * Sabemos que el mayor es el valor numérico llamante */

    public void subValue(Value n) {

        String valorIni = value;
        String valorSum =((ValueSeq)n).value;

        //Si ambos valores son iguales, devolvemos 0 directamente

        if (valorIni.equals(valorSum)){
            this.value = "0";
            return;
        }

        apilaOperandos(valorIni, valorSum);

        /* Comprobamos que ambas pilas tienen el mismo tamaño
         *  para que no se produzca un NullPointerException*/

        while (!op1.isEmpty() || !op2.isEmpty()) {

            int aux1, aux2;

            anyadeZeros();

            // Obtenemos el valor del top de la pila
            aux1 = op1.getTop();
            aux2 = op2.getTop();
            op1.pop();
            op2.pop();
            int res;

            if (acarreo) {
                aux2 += 1;
                acarreo = false;
            }

            if (aux1 < aux2) {
                aux1 += 10;
                res = aux1 - aux2;
                result.push(res);
                acarreo = true;
            } else {
                res = aux1 - aux2;
                result.push(res);
                acarreo = false;
            }

            if (op1.isEmpty() && op2.isEmpty()){
                this.value = "";

                while (!result.isEmpty()) {

                        int resul = result.getTop();
                        this.value = this.value + resul;
                        result.pop();
                }
                removeZeros();
            }
        }
    }


    /* Método que modifica el valor numérico llamante, restándolo del valor numérico parámetro
     * Sabemos que el mayor es el valor numérico parámetro */
    public void subFromValue(Value n) {

        String valorIni = value;
        String valorSum =((ValueSeq)n).value;

        //Si ambos valores son iguales, devolvemos 0 directamente

        if (valorIni.equals(valorSum)){
            this.value = "0";
            return;
        }

        apilaOperandos(valorIni, valorSum);

        /* Comprobamos que ambas pilas tienen el mismo tamaño
         *  para que no se produzca un NullPointerException*/

        while (!op1.isEmpty() || !op2.isEmpty()) {

            int aux1, aux2;

            anyadeZeros();

            // Obtenemos el valor del top de la pila
            aux1 = op1.getTop();
            aux2 = op2.getTop();
            op1.pop();
            op2.pop();
            int res;

            if (acarreo) {
                aux1 += 1;
                acarreo = false;
            }

            if (aux2 < aux1) {
                aux2 += 10;
                res = aux2 - aux1;
                result.push(res);
                acarreo = true;
            } else {
                res = aux2 - aux1;
                result.push(res);
                acarreo = false;
            }

            if (op1.isEmpty() && op2.isEmpty()){
                this.value = "";

                while (!result.isEmpty()) {

                        int resul = result.getTop();
                        this.value = this.value + resul;
                        result.pop();
                    }

                removeZeros(); //Mientras el resultado empiece por 0, lo eliminamos
            }
        }
    }


    /* Método que modifica el valor numérico llamante, multiplicándolo por el valor numérico parámetro */
    public void multValue(Value n) {

        int cont = 0;
        int carry;
        int operando2;
        int res;
        ValueSeq acum = new ValueSeq("");
        List<Integer> multiplicando = new List<>();

        String multip = ((ValueSeq)n).value;
        String multiplicador = this.value;

        if (multip.equals("0") || multiplicador.equals("0")){
            this.value = "0";
            return;
        }

        //El numero de arriba se queda fijo como una lista (Multiplicando)
        //El numero de abajo se recorre como una pila (Multiplicador)

        for (int i = 1; i<= multip.length(); i++){
            multiplicando.insert(i, Integer.parseInt(String.valueOf(multip.charAt(i-1))));
        }


        for (int i = 0; i< multiplicador.length(); i ++){
            op2.push(Integer.parseInt(String.valueOf(multiplicador.charAt(i))));
        }

        //El while controla al multipliador

        while (!op2.isEmpty()) {
            operando2 = op2.getTop();
            carry = 0;
            op2.pop();

            //El for se encarga de ir recorriendo la Lista del multiplicando

            for (int j = multiplicando.size(); j >= 1; j--) {
                int operando1 = multiplicando.get(j);

                res = operando1 * operando2;

                if (carry > 0) {
                    res += carry;
                    carry = 0;
                }

                if (res > 9) {
                    carry = res / 10;
                }

                res = res % 10;
                result.push(res);
            }

            if (carry > 0){
                result.push(carry);
            }

            String aux="";

            int resul;
            while (!result.isEmpty()) {

                resul = result.getTop();
                aux = aux + resul;
                result.pop();
            }

            if (cont > 0){
                for (int i = 0; i< cont; i++){
                    result.push(0);
                    resul = result.getTop();
                    aux = aux + resul;
                    result.pop();
                }
            }

            ValueSeq sum = new ValueSeq(aux);
            acum.addValue(sum);
            cont ++;

        }

        this.value = acum.toString();
    }

    /* Método que indica si el valor numérico llamante es mayor que el valor numérico parámetro */
    public boolean greater(Value n) {

        Queue<Integer> llamante = new Queue<>();
        Queue<Integer> parametro = new Queue<>();

        String valorLlamante = ((ValueSeq)n).value;

        /* Si el valor llamante tiene una longitud mayor que el parametro que le pasamos,
        devolvemos TRUE directamente */

        if (this.value.length() > n.toString().length()){
            return true;
        }

        /* En caso de que ambos Strings tengan la misma longitud, apilamos ambos Strings,
           quedando el primer número en el top de la pila. Entonces vamos comparando el top de ambas pilas
           hasta que el el valor de la pila llamante sea mayor que el top del parametro que le pasamos
         */

        if (n.toString().length() == this.value.length()){
            for (int i = 0; i< this.value.length(); i++){
                parametro.enqueue(Integer.parseInt(String.valueOf(valorLlamante.charAt(i))));
                llamante.enqueue(Integer.parseInt(String.valueOf(this.value.charAt(i))));
            }

            while (!llamante.isEmpty()){
                int llam = llamante.getFirst();
                int param = parametro.getFirst();
                llamante.dequeue();
                parametro.dequeue();

                if (llam > param){
                    return true;
                }
                else if (llam > param){
                    return false;
                }
            }
        }
        return false;
    }

    /* Método que indica si el valor numérico es cero */
    public boolean isZero() {

      if (this.value.equals("0")){
          return true;
      }
      return false;
    }

    //Metodo para apilar los operandos de la suma y la resta
    private void apilaOperandos(String valorIni, String valorSum) {

        for (int i = 0; i < valorIni.length(); i++) {
            op1.push(Integer.parseInt(String.valueOf(valorIni.charAt(i))));
        }

        for (int i = 0; i < valorSum.length(); i++) {
            op2.push(Integer.parseInt(String.valueOf(valorSum.charAt(i))));
        }
    }

    private void removeZeros() {
        while (value.startsWith("0")) {
            value = value.substring(1);
        }
    }

    private void anyadeZeros() { //Meotdo que añade 0 a las pilas
        if (op1.isEmpty() && !op2.isEmpty()) {
            op1.push(0);
        }
        if (!op1.isEmpty() && op2.isEmpty()) {
            op2.push(0);
        }
    }

}

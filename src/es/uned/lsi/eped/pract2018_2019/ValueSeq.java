package es.uned.lsi.eped.pract2018_2019;

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
        String valorSum = n.toString();

        apilaOperandos(valorIni, valorSum);

        /* Comprobamos que ambas pilas tienen el mismo tamaño
         *  para que no se produzca un NullPointerException*/

        while (!op1.isEmpty() || !op2.isEmpty()) {

            int aux1, aux2;
            // Con los siguientes if's controlamos que ambas
            // pilas tengan el mismo tamaño
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

    /* Método que modifica el valor numérico llamante, restándole el valor numérico parámetro */
    /* Sabemos que el mayor es el valor numérico llamante */
    public void subValue(Value n) {

        String valorIni = value;
        String valorSum = n.toString();

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
            // Con este metodo anyadimos 0 a la pila en caso de que no tengan el mismo tamaño
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

                while (value.startsWith("0")){
                    value = value.substring(1);
                }

            }
            // Si el primer elemento del String es un 0, lo eliminarmos
         /*   if (this.value.charAt(0) == '0'){
                this.value = value.substring(1);
            }*/
        }
    }

    /* Método que modifica el valor numérico llamante, restándolo del valor numérico parámetro */
    /* Sabemos que el mayor es el valor numérico parámetro */
    public void subFromValue(Value n) {

        String valorIni = value;
        String valorSum = n.toString();

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
            // Con los siguientes if's controlamos que ambas
            // pilas tengan el mismo tamaño
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
                //Mientras el resultado empiece por 0, lo eliminamos
                while (value.startsWith("0")){
                    value = value.substring(1);
                }
            }
        }
    }

        //Meotdo que añade 0 a las pilas
    private void anyadeZeros() {
        if (op1.isEmpty() && !op2.isEmpty()) {
            op1.push(0);
        }
        if (!op1.isEmpty() && op2.isEmpty()) {
            op2.push(0);
        }
    }

    /* Método que modifica el valor numérico llamante, multiplicándolo por el valor numérico parámetro */
    public void multValue(Value n) {
    }

    /* Método que indica si el valor numérico llamante es mayor que el valor numérico parámetro */
    public boolean greater(Value n) {
        /*
        todo -> implemntar esto para numeros de mismo tamaño
         */

        Queue<Integer> llamante = new Queue<>();
        Queue<Integer> parametro = new Queue<>();

        String valorLlamante = n.toString();

        if (this.value.length() > n.toString().length()){
            return true;
        }

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
            }
        }

        return false;
    }

    /* Método que indica si el valor numérico es cero */
    public boolean isZero() {
        if(this.value.equals("0")){
            return true;
        }
        return false;
    }

    private void apilaOperandos(String valorIni, String valorSum) {
        for (int i = 0; i < valorIni.length(); i++) {

            op1.push(Integer.parseInt(String.valueOf(valorIni.charAt(i))));
        }

        for (int i = 0; i < valorSum.length(); i++) {

            op2.push(Integer.parseInt(String.valueOf(valorSum.charAt(i))));
        }

    }
}

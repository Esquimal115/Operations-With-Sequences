package es.uned.lsi.eped.pract2018_2019;

public class ValueInt extends Value {

	/* Atributo que guarda el valor numérico */
	private int value;
	
	/* Constructor: recibe un String con el valor numérico */
	public ValueInt(String s) {
		this.value = Integer.parseInt(s);
	}
	
	/* Método que transforma el valor numérico en un String */
	public String toString() {
		return Integer.toString(this.value);
	}
	
	/* Método que modifica el valor numérico llamante, sumándole el valor numérico parámetro */
	public void addValue(Value n) {
		this.value += ((ValueInt) n).value;
	}
	
	/* Método que modifica el valor numérico llamante, restándole el valor numérico parámetro */
	public void subValue(Value n) {
		this.value -= ((ValueInt) n).value;
	}
	
	/* Método que modifica el valor numérico llamante, restándolo del valor numérico parámetro */
	public void subFromValue(Value n) {
		this.value = ((ValueInt) n).value - this.value;
	}
	
	/* Método que modifica el valor numérico llamante, multiplicándolo por el valor numérico parámetro */
	public void multValue(Value n) {
		this.value *= ((ValueInt) n).value;
	}
	
	/* Método que indica si el valor numérico llamante es mayor que el valor numérico parámetro */
	public boolean greater(Value n) {
		return this.value > ((ValueInt) n).value;
	}

	/* Método que indica si el valor numérico llamante es cero */
	public boolean isZero() {
		return this.value == 0;
	}
}

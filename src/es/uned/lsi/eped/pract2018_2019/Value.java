package es.uned.lsi.eped.pract2018_2019;

public abstract class Value {
	public enum ValueClass {
		INT, SEQ
	}
	
	protected static ValueClass valueClass;

	public static Value construct(String s) {
	    Value value = null;
		switch ( valueClass ) {
		case INT :
			value = new ValueInt(s);
			break;
		case SEQ:
			value = new ValueSeq(s);
			break;
		}
		return value;
	}
	
	/* Método que transforma el valor numérico en un String */
	public abstract String toString();
	
	/* Método que modifica el valor numérico llamante, sumándole el valor numérico parámetro */
	public abstract void addValue(Value n);

	/* Método que modifica el valor numérico llamante, restándole el valor numérico parámetro */
	public abstract void subValue(Value n);

	/* Método que modifica el valor numérico llamante, restándolo del valor numérico parámetro */
	public abstract void subFromValue(Value n);

	/* Método que modifica el valor numérico llamante, multiplicándolo por el valor numérico parámetro */
	public abstract void multValue(Value n);

	/* Método que indica si el valor numérico llamante es mayor que el valor numérico parámetro */
	public abstract boolean greater(Value n);
	
	/* Método que indica si el valor numérico llamante es cero */
	public abstract boolean isZero();
}

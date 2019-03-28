package es.uned.lsi.eped.pract2018_2019;

public class Operand extends Node {

	private byte signum = 0;
	private Value value;
	
	/* Constructor */
	public Operand(String s) {
		String aux = s;
		if ( s.charAt(0) == '-' ) {
			/* El carácter - al comienzo de la cadena supone un número negativo */
			this.signum = -1;
			aux = s.substring(1);
		}
		this.value = Value.construct(aux);
		if ( this.signum != -1 ) {
			this.signum = (byte) ((this.value.isZero())?0:1);
		}
	}
	
	/* Operaciones de consulta (getters) */
	
    /* Nodo tipo operando */
	public Node.NodeType getNodeType() {
		return Node.NodeType.OPERAND;
	}
	
	/* Devuelve el signo del operando *
	 * Positivo: +1                   *
	 * Cero:      0                   *
	 * Negativo: -1                   */
	public int sig() {
		return this.signum;
	}

	/* Devuelve el valor del operando prescindiendo del signo */
	public Value val() {
		return this.value;
	}
	
	/* Transforma el operando a un String */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if ( this.signum == -1 ) {
			builder.append('-');
		}
		builder.append(this.value.toString());
		return(builder.toString());
	}
	
	/* Operaciones modificadoras */
	
	/* Suma considerando signo */
	public void add(Operand n) {
		if ( this.signum == n.sig() ) {
			this.value.addValue(n.val());
		} else {
			if ( this.value.greater(n.val()) ) {
				this.value.subValue(n.val());
			} else {
				this.value.subFromValue(n.val());
				this.signum = (byte) ((this.value.isZero())?0:n.sig());
			}
		}
	}
	
	/* Resta considerando signo */
	public void sub(Operand n) {
		if ( this.signum == -n.sig() ) {
			this.value.addValue(n.val());
		} else {
			if ( this.value.greater(n.val()) ) {
				this.value.subValue(n.val());
			} else {
				this.value.subFromValue(n.val());
				this.signum = (byte) ((this.value.isZero())?0:-n.sig());
			}
		}
	}	

	/* Producto considerando signo */
	public void mult(Operand n) {
		this.signum *= n.sig();
		if ( this.signum != 0 ) {
			this.value.multValue(n.val());
		} else {
			this.value = Value.construct("0");
		}
	}
	
}

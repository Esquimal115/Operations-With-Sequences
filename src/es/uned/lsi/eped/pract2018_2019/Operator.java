package es.uned.lsi.eped.pract2018_2019;

public class Operator extends Node {
	public enum OperatorType {
		ADD, SUB, MULT
	}

	private Operator.OperatorType operator;
	
	/* Constructor */
	public Operator(Operator.OperatorType operator) {
		this.operator = operator;
	}
	
	/* Nodo tipo operador */
	public NodeType getNodeType() {
		return Node.NodeType.OPERATOR;
	}

	/* Devuelve el tipo de operador */
	public Operator.OperatorType getOperatorType() {
		return this.operator;
	}
}

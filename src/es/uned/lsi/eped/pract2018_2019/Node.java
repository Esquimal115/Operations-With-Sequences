package es.uned.lsi.eped.pract2018_2019;

public abstract class Node {
	public enum NodeType {
		OPERATOR, OPERAND
	}
	
	/* Prescribe un getter que devuelve el tipo de nodo (Operando/Operador) */
	public abstract NodeType getNodeType();
}

package es.uned.lsi.eped.pract2018_2019;

import es.uned.lsi.eped.DataStructures.BTree;
import es.uned.lsi.eped.DataStructures.Stack;

public class StackMachine {

    private static Stack<Operand> pilaOperaciones = new Stack<>();
    private static Operator operator;
    private static Operand resultado;

    /**
     * Metodo privado para recorrer el árbol en postOrden
     *
     * @param nodo
     * @return Operand
     */

    private Operand postOrden(BTree<Node> nodo) {

        //Recorremos el arbol en postorden

        Node raiz = nodo.getRoot();

        if (nodo.getLeftChild()!= null) {

            postOrden((BTree<Node>) nodo.getLeftChild());
        }
        if (nodo.getLeftChild()!= null) {
            postOrden((BTree<Node>) nodo.getRightChild());
        }


        /* Una vez leido el arbol, vamos comprobando si el nodo es un operando
           o un operador
        */

        if (raiz.getNodeType() == Node.NodeType.OPERAND) {
            Operand op = (Operand) raiz;
            pilaOperaciones.push(op);
        }

        if (raiz.getNodeType() == Node.NodeType.OPERATOR) {
            operator = (Operator) raiz;
            Node valor1;
            Node valor2;

            valor1 = pilaOperaciones.getTop();
            pilaOperaciones.pop();
            valor2 = pilaOperaciones.getTop();
            pilaOperaciones.pop();

            Operand operando1 = (Operand) valor1;
            Operand operando2 = (Operand) valor2;

            //Dependiendo del tipo de operador leido realizaremos una operacion u otra

            switch (operator.getOperatorType()) {
                case ADD:
                    operando1.add(operando2);
                    pilaOperaciones.push(operando1);
                    break;

                case SUB:
                    operando2.sub(operando1);
                    pilaOperaciones.push(operando2);
                    break;
                case MULT:
                    operando1.mult(operando2);
                    pilaOperaciones.push(operando1);
                    break;
            }
        }

        resultado = pilaOperaciones.getTop();
        return resultado;
    }

    public StackMachine() {

    }

    /**
     * Metodo que ejecuta a la funcion postOrden para devolver el resultado final
     * @param syn
     * @return Operand
     *
     */
    public Operand execute(SynTree syn) {

        Operand resultado = postOrden(syn.getSynTree());
        return resultado;
    }
}

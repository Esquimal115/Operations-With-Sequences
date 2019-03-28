package es.uned.lsi.eped.pract2018_2019;

import es.uned.lsi.eped.DataStructures.BTree;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.Stack;

import static es.uned.lsi.eped.pract2018_2019.StackMachine.*;

public class Main2 {


    public static void main(String[] args) {

        Value.valueClass = Value.ValueClass.INT;

        String exp = "* * - 23 45 - 12 34 + 12 4";
        StackMachine sm = new StackMachine();
        SynTree res = new SynTree(exp);

        Operand result = sm.execute(res);
        System.out.println(result);

        System.out.println("****** A otra cosa mariposa *******");
        ValueSeq v1 = new ValueSeq("9");
        ValueSeq v2 = new ValueSeq("-1");

        v1.addValue(v2);

    }
}


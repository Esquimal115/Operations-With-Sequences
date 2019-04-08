package es.uned.lsi.eped.pract2018_2019;

import es.uned.lsi.eped.DataStructures.BTree;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.Stack;

import static es.uned.lsi.eped.pract2018_2019.StackMachine.*;
import static java.lang.Math.*;

public class Main2 {


    public static void main(String[] args) {

       /* Value.valueClass = Value.ValueClass.INT;

        String exp = "- -1000 -100";
        StackMachine sm = new StackMachine();
        SynTree res = new SynTree(exp);

        Operand result = sm.execute(res);
        System.out.println(result);

        System.out.println("****** Cosas ValueSEQ *******");
        ValueSeq v1 = new ValueSeq("1");
        ValueSeq v2 = new ValueSeq("0");

        v1.subValue(v2);*/
        String str1 = "41549622603955309777243716069997997007620439937711509062916";
        String str2 = "41549622603955309777243716069997997007620439937711509062916";

        if (str1.equals(str2)){

            System.out.println("Iguales");
       }else{
            System.out.println("WRONG!!");
        }


    }
}


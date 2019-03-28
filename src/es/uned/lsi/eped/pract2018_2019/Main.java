package es.uned.lsi.eped.pract2018_2019;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	private static void printUsage() {
		System.err.println("Error en los parámetros de entrada:");
		System.err.println("-Primer parámetro: INT para enteros Java / SEQ para enteros con secuencias");
		System.err.println("-Segundo parámetro: nombre del fichero con las expresiones a evaluar");
	}
	
	public static void main(String [] args) throws IOException{
		if ( args.length != 2 ) {
			/* Se necesitan, exactamente, dos parámetros */
			printUsage();
		} else {
			/* Primer parámetro: clase de valores a utilizar (int/seq) */
			String value = args[0];
			switch (value.toLowerCase()) {
			case "int":
				Value.valueClass = Value.ValueClass.INT; // Se usarán valores de la clase ValueInt
				break;
			case "seq":
				Value.valueClass = Value.ValueClass.SEQ; // Se usarán valores de la clase ValueSeq
				break;
			default:
				/* Error en el primer parámetro */
				printUsage();
				System.exit(1);
			}
			/* Segundo parámetro: fichero de expresiones a evaluar */
			String expressions = args[1];
			StackMachine sm = new StackMachine();
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(new 
						FileInputStream(expressions), "UTF-8"));
				String exp;
				while((exp = in.readLine())!=null) {
					/* Imprimimos el resultado de ejecutar la máquina pila *
					 * sobre el árbol sintáctico construido en base a la   *
					 * expresión leída                                     */
					if ( !exp.equals("") ) {
						if ( !exp.startsWith("#") ) {
							System.out.println(sm.execute(new SynTree(exp)).toString());
						} else {
							System.out.println(exp);
						}
					}
				}
				in.close();
			} catch (IOException ex) {
				/* Error en el segundo parámetro */
				System.err.println("No se puede abrir el fichero de expresiones para su lectura.");
			}
		}
	}
}

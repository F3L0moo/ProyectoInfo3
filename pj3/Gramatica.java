import javax.swing.plaf.nimbus.State;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Gramatica{

	public static void main(String[] args) throws Exception{
		//Su codigo aqui
		if (args.length == 0) {
			System.out.println("El uso correcto del sistema es: *Nombre de archivo* (-afn, -afd o -check) *Nombre de archivo de salida");
			return;
		}
		if(args[1].equals("-afn")) {
			GLDaAFN afn = new GLDaAFN(args[0]);
			afn.getInfo();
		}
		if(args[1].equals("-afd")){

		}
		if(args[1].equals("-check")){

		}

		//################ GLD a AFN ########################################
		
		

		
	}
}
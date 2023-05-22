import java.util.*;
import java.io.*;

public class Gramatica{

	static void pasarGLDaAFN(String archivo) {
		GLDaAFN afn = new GLDaAFN(archivo);
		afn.getInfo();
		afn.createAFNfile(archivo);
	}

	public static void main(String[] args) throws Exception{
		//Su codigo aqui
		if (args.length == 0) {
			System.out.println("El uso correcto del sistema es: *Nombre de archivo* (-afn, -afd o -check) *Nombre de archivo de salida");
			return;
		}
		if(args[1].equals("-afn")) {
			pasarGLDaAFN(args[0]);
			
		}
		if(args[1].equals("-afd")){

		}
		if(args[1].equals("-check")){

		}

		//################ GLD a AFN ########################################
		
		
	}
}
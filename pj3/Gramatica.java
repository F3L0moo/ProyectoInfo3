import javax.swing.plaf.nimbus.State;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Gramatica{

	static void pasarGLDaAFN(String archivo) {
		GLDaAFN afn = new GLDaAFN(archivo);
		//afn.getInfo();
		afn.createAFNfile(archivo);
	}

	static void pasarGLDaAFD(String archivo) {
		GLDaAFN afn = new GLDaAFN(archivo);
		//afn.getInfo();
		afn.createAFNfile(archivo);
	}

	static void checker(String archivoGramatica, String archivoCuerdas){
		Checker check = new Checker(archivoGramatica, archivoCuerdas);
		check.getInfo();

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
			pasarGLDaAFD(args[0]);
		}
		if(args[1].equals("-check")){
			checker(args[0], args[3]);
		}

		//################ GLD a AFN ########################################
		
		
	}
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.io.FileWriter;


public class Checker{
    ArrayList<String> cuerdas = new ArrayList<String>();
    ArrayList<String> transiciones = new ArrayList<String>();

    public String[] estadosNumeros = {};
    public String[] abcedario = {};
    public String[] estadosO = {};
    public String abc = "";
    public String inicial = "";
    public String[] estadosF = {};
    public int numVariables = 0;
    
    
    public Checker(String archivoGramatica, String archivoCuerdas) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(archivoGramatica));
            String cadena = "";
            cadena = file.readLine();
            this.estadosO = cadena.split(",");
            String[] estados = cadena.split(","); 
            cadena = file.readLine();
            this.abc = cadena;
            this.abcedario = cadena.split(",");
            cadena = file.readLine();
            this.inicial = cadena;   

            String estadosFT = "";
            int cont = 1;
            int contEq = 1;
            int numVariablesT = estados.length;
            while ((cadena = file.readLine()) != null) {
                String[] prueba = {};
                prueba = cadena.split("->");
                char[] separador = {};

                if(prueba[1].length() > 2) {    
                    separador = prueba[1].toCharArray();
                    numVariablesT++;
                    transiciones.add(prueba[0]+ "->" + separador[0] + prueba[0]+cont);
                    transiciones.add(prueba[0]+cont+ "->" + separador[1] + separador[2]);
                    cont++;

                    
                } else if(prueba[1].length() == 2) {
                    separador = prueba[1].toCharArray();
                    String var = separador[1]+"";
                        if(var.equals(var.toUpperCase())){
                            transiciones.add(cadena);
                        }else {
                            numVariablesT++;
                            transiciones.add(prueba[0]+"->"+separador[0]+prueba[0]+contEq);
                            transiciones.add(prueba[0]+contEq+"->"+separador[1]);
                        }
                } else if(prueba[1].length() == 1) {
                    estadosFT += prueba[0] + ",";

                    transiciones.add(cadena);
                }

            }
            this.numVariables = numVariablesT;
            this.estadosF = estadosFT.split(",");
            file.close(); 

            BufferedReader filec = new BufferedReader(new FileReader(archivoCuerdas));

            while ((cadena = filec.readLine()) != null) {
                cuerdas.add(cadena);
            }
        }catch(FileNotFoundException e) {
            System.out.println("ARCHIVO NO VALIDO");
        }catch(IOException e) {

        }
        
    }

    public void getInfo() {
        for(int x = 0; x < transiciones.size(); x++) {
            System.out.println(transiciones.get(x));
        }
        for (int i = 0; i < cuerdas.size(); i++) {
            System.out.println(cuerdas.get(i));
        }
    }
}


//Hecho por Fernando Josue Lopez Moore 22000114
//Hecho por Daniel Herrarte 22001722
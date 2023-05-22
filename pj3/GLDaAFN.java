import java.util.HashMap;
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

public class GLDaAFN {
    HashMap<String, String> estadosGLD = new HashMap<>();
    //Queue<String> gld = new LinkedList<String>();
    ArrayList<String> transiciones = new ArrayList<String>();

    public String[] estadosNumeros = {};
    public String[] abcedario = {};
    public String[] estadosO = {};
    public String abc = "";
    public String inicial = "";
    public String[] estadosF = {};
    public int numVariables = 0;
    
    
    public GLDaAFN(String archivo) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(archivo));
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

        }catch(FileNotFoundException e) {
            System.out.println("ARCHIVO NO VALIDO");
        }catch(IOException e) {

        }
        
    }

    public void getInfo() {
        for(int x = 0; x < this.estadosO.length; x++) {
            System.out.print(this.estadosO[x] + " ");
        }
        for(int x = 0; x < this.abcedario.length; x++) {
            System.out.print(this.abcedario[x] + " ");
        }
        //System.out.println(this.prueba[1]);
        System.out.println(this.inicial);
        System.out.println(this.estadosF[0]);
        System.out.println(transiciones.size());
        for(int x = 0; x < transiciones.size(); x++) {
            System.out.println(transiciones.get(x));
        }
    }

    public void createAFNfile(String archivo){
        String nAr = archivo;

        String subnAr = archivo.substring(0, nAr.length() - 4);
        subnAr = subnAr + ".afd";
        System.out.println(subnAr);

        String esFinAFD = "";
        int estact = 100000;
        try {
            FileWriter archivoNuevo = new FileWriter(subnAr);

            for(int x = 0; x < estadosO.length; x++){
                for(int y = 0; y < estadosF.length; y++){
                    if(estadosO[x].equals(estadosF[y])){
                        if(estact != x) {
                            esFinAFD+=(x+1)+",";
                        }
                        estact = x;
                    }
                }
            }
            String finAFD = esFinAFD.substring(0, esFinAFD.length() - 1);



            archivoNuevo.write(this.abc + "\n");
            archivoNuevo.write((this.numVariables+1) + "\n");
            archivoNuevo.write(finAFD + "\n");



            archivoNuevo.close();
        } catch (Throwable e) {
            System.out.println("Hubo un error al crear el archivo, intente mas tarde");
        }
        
    }
}
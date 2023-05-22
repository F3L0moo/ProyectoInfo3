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

public class GLDaAFD {
    HashMap<String, String> estadosAFD = new HashMap<>();
    ArrayList<String> variables = new ArrayList<String>();
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

            for(int i = 0; i < estados.length; i++){
                variables.add(estados[i]);
            }

            cadena = file.readLine();
            this.abc = cadena;
            this.abcedario = cadena.split(",");
            cadena = file.readLine();
            this.inicial = cadena;   

            String estadosFT = "";
            int cont = 1;
            int contEq = 1;
            int contExtr = 0;
            int numVariablesT = estados.length;
            int pos = 1;
            while ((cadena = file.readLine()) != null) {
                char[] cadenachar = cadena.toCharArray();
                //String estadoActual = cadenachar[0]+"";
                String[] prueba = {};
                prueba = cadena.split("->");
                char[] separador = {};

                if(prueba[1].length() > 2) { 

                    //if(!prueba[0].equals(estadoActual)){
                    //    pos++;
                    //}   

                    separador = prueba[1].toCharArray();
                    numVariablesT++;
                    transiciones.add(prueba[0]+ "->" + separador[0] + prueba[0]+cont);
                    pos = variables.indexOf(prueba[0])+1;
                    variables.add(pos, prueba[0]+cont);
                    transiciones.add(prueba[0]+cont+ "->" + separador[1] + separador[2]);
                    cont++;
                    //contExtr++;
                } else if(prueba[1].length() == 2) {
                    separador = prueba[1].toCharArray();
                    String var = separador[1]+"";
                        if(var.equals(var.toUpperCase())){
                            transiciones.add(cadena);
                        }else {
                            numVariablesT++;
                            pos = variables.indexOf(prueba[0])+1;
                            variables.add(pos, prueba[0]+contEq);
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
        for(int x = 0; x < variables.size(); x++) {
            System.out.println(variables.get(x));
        }
    }

    public void createAFNfile(String archivo){
        String nAr = archivo;

        String subnAr = archivo.substring(0, nAr.length() - 4);
        subnAr = subnAr + ".afd";
        System.out.println(subnAr);

        String esFinAFD = "";
        int estact = 100000;

        String salto = "\n";
        try {
            FileWriter archivoNuevo = new FileWriter(subnAr);

            for(int x = 0; x < this.estadosO.length; x++){
                for(int y = 0; y < estadosF.length; y++){
                    if(estadosO[x].equals(estadosF[y])){
                        if(estact != x) {
                            esFinAFD+=(x+1)+",";
                        }
                        estact = x;
                    }
                }
            }

            for (int i = 0; i < variables.size(); i++) {
                System.out.println(variables.get(i));
            }

            String finAFD = esFinAFD.substring(0, esFinAFD.length() - 1);

            archivoNuevo.write(this.abc + "\n");
            archivoNuevo.write((this.numVariables+1) + "\n");
            archivoNuevo.write(finAFD + "\n");

            for(int i = 0; i < this.abcedario.length; i++){
                String acumulador = "0,";
                for(int x = 0; x < transiciones.size(); x++) {
                    String[] tempo = transiciones.get(x).split("->");
                    //System.out.println(tempo[0] + " var");
                    //System.out.println(tempo[1] + " trans");
                    //System.out.println("===============================");
                    char[] contenedor = tempo[1].toCharArray();
                    String abc = contenedor[0]+"";
                    System.out.println(abc+" abc");
    
                    if(this.abcedario[i].equals(abc)) {
                        System.out.println(tempo[0] + " pos actual");
                        int transiAFD = variables.indexOf(tempo[0]) + 1;
                        System.out.println(transiAFD + " numero var");
                        acumulador+=transiAFD+",";
                    }else {
                        acumulador+="0,";
                    }
                }
                estadosAFD.put(this.abcedario[i], acumulador);
                System.out.println(estadosAFD.get(this.abcedario[i]));
                archivoNuevo.write(estadosAFD.get(this.abcedario[i]));
                archivoNuevo.write(salto);
            }

            archivoNuevo.close();
        } catch (Throwable e) {
            System.out.println("Hubo un error al crear el archivo, intente de nuevo");
        }
        
    }
}
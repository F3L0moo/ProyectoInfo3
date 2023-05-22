import java.util.*;
import java.io.*;

public class GLDaAFN {
    //Field definitions
    HashMap<String, String> estadosGLD = new HashMap<>();
    //Queue<String> gld = new LinkedList<String>();
    ArrayList<String> transiciones = new ArrayList<String>();
    //Global vars
    public String[] estadosNumeros = {};
    public String[] abcedario = {};
    public String[] estadosO = {};
    public String abc = "";
    public String inicial = "";
    public String[] estadosF = {};
    
    //Constructor
    public GLDaAFN(String archivo) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(archivo));
            String cadena = "";
            String estadosFT = "";

            cadena = file.readLine(); //Reads 1st line
            this.estadosO = cadena.split(","); 
            cadena = file.readLine(); //Reads 2nd line
            this.abc = cadena;
            this.abcedario = cadena.split(",");
            cadena = file.readLine(); //Reads 3rd line
            this.inicial = cadena;   

            int cont = 1; //Class counter
            int contEq = 1;

            while ((cadena = file.readLine()) != null) /*reads til the end of the file*/ {
                String[] prueba = {};
                prueba = cadena.split("->");
                char[] separador = {};

                if (prueba[1].length() > 2) /*Checks if terminal is more than one char*/ {    
                    separador = prueba[1].toCharArray();
                    transiciones.add(prueba[0]+ "->" + separador[0] + prueba[0]+cont);
                    transiciones.add(prueba[0]+cont+ "->" + separador[1] + separador[2]);
                    cont++;
                } else if (prueba[1].length() == 2) /*2nd Check is equal to 2*/ {
                    separador = prueba[1].toCharArray();
                    String var = separador[1]+"";
                        if (var.equals(var.toUpperCase())) {
                            transiciones.add(cadena);
                        } else {
                            transiciones.add(prueba[0]+"->"+separador[0]+prueba[0]+contEq);
                            transiciones.add(prueba[0]+contEq+"->"+separador[1]);
                        }
                } else if (prueba[1].length() == 1) /*3rd Check is equal to 1*/ {
                    estadosFT += prueba[0] + ",";
                    transiciones.add(cadena);
                }
            }
            this.estadosF = estadosFT.split(","); //Writes to class field estados finales
            file.close(); //Closes the file 

        } catch (FileNotFoundException e) {
            System.out.println("ARCHIVO NO VALIDO");
        } catch (IOException e) {
            //Default exception
        }
    }

    public void getInfo() {
        for (int x = 0; x < this.estadosO.length; x++) {
            System.out.print(this.estadosO[x] + " ");
        }
        for (int x = 0; x < this.abcedario.length; x++) {
            System.out.print(this.abcedario[x] + " ");
        }
        
        //System.out.println(this.prueba[1]);
        System.out.println(this.inicial);
        System.out.println(this.estadosF[0]);
        System.out.println(transiciones.size());
        for (int x = 0; x < transiciones.size(); x++) {
            System.out.println(transiciones.get(x));
        }
    }

    public void createAFNfile(String archivo) {
        String nAr = archivo;

        String subnAr = archivo.substring(0, nAr.length() - 4);
        subnAr = subnAr + ".afn";
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
            archivoNuevo.write(transiciones.size() + "\n");
            archivoNuevo.write(finAFD + "\n");



            archivoNuevo.close();
        } catch (Throwable e) {
            System.out.println("Hubo un error al crear el archivo, intente mas tarde");
        }
    }
}
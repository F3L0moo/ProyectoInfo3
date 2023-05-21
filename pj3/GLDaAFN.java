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
    public int transEstados = 0;
    public String[] estadosF = {};
    
    
    public GLDaAFN(String archivo) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(archivo));
            String cadena = "";
            cadena = file.readLine();
            this.estadosO = cadena.split(",");
            String[] estados = cadena.split(",");
            int transEstadosT = estados.length + 1; 
            cadena = file.readLine();
            this.abc = cadena;
            this.abcedario = cadena.split(",");
            cadena = file.readLine();
            this.inicial = cadena;   

            String estadosFT = "";
            while ((cadena = file.readLine()) != null) {
                String[] prueba = {};
                prueba = cadena.split("->");
                char[] separador = {};

                if(prueba[1].length() > 2) {
                    for (int x = 0; x < estados.length; x++) {
                        if (prueba[0].equals(estados[x])) {
                            transEstadosT = transEstadosT + 1;
                        }
                    }
                    separador = prueba[1].toCharArray();
                    transiciones.add(prueba[0]+ "->" + separador[0] + separador[2]);
                    transiciones.add(prueba[0]+ "->" + separador[1] + separador[2]);
                } else if(prueba[1].length() == 2) {
                    transiciones.add(cadena);
                } else if(prueba[1].length() == 1) {
                    estadosFT += prueba[0] + ",";

                    transiciones.add(cadena);
                }

            }
            this.estadosF = estadosFT.split(",");

            this.transEstados = transEstadosT;
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
        System.out.println(this.estadosF[1]);
        System.out.println(this.transEstados);
        for(int x = 0; x < transiciones.size(); x++) {
            System.out.println(transiciones.get(x));
        }
    }

    public void createAFNfile(String archivo){
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
            archivoNuevo.write(this.transEstados + "\n");
            archivoNuevo.write(finAFD + "\n");



            archivoNuevo.close();
        } catch (Throwable e) {
            System.out.println("Hubo un error al crear el archivo, intente mas tarde");
        }
        
    }
}
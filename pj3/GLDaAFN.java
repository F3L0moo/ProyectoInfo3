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

public class GLDaAFN {
    HashMap<String, String> estadosGLD = new HashMap<>();
    //Queue<String> gld = new LinkedList<String>();
    ArrayList<String> transiciones = new ArrayList<String>();

    public String[] estadosNumeros = {};
    public String[] abcedario = {};
    public String[] estadosO = {};
    public String inicial = "";
    
    
    public GLDaAFN(String archivo) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(archivo));
            String cadena = "";
            cadena = file.readLine();
            this.estadosO = cadena.split(",");
            String[] estados = cadena.split(",");
            int transEstados = estados.length; 
            cadena = file.readLine();
            this.abcedario = cadena.split(",");
            String[] abecedario = cadena.split(",");
            cadena = file.readLine();
            this.inicial = cadena;   

            while ((cadena = file.readLine()) != null) {
                String[] prueba = {};
                prueba = cadena.split("->");
                char[] separador = {};

                if(prueba[1].length() < 2) {
                    for (int x = 0; x < estados.length; x++) {
                        if (prueba[0] == estados[x]) {
                            transEstados++;
                        }
                    }
                    separador = prueba[1].toCharArray();
                    transiciones.add(prueba[0] + separador[0] + separador[2]);
                    transiciones.add(prueba[0] + separador[1] + separador[2]);
                } else {

                }

            }
            
            file.close(); 

        }catch(FileNotFoundException e) {
            System.out.println("ARCHIVO NO VALIDO");
        }catch(IOException e) {

        }
        
    }

    public void getInfo() {
        System.out.println(this.estados[2]);
        for(int x = 0; x < this.abcedario.length; x++) {
            System.out.print(this.abcedario[x] + " ");
        }
        System.out.println(this.prueba[1]);
        System.out.println(this.inicial);
    }
}
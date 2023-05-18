import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.Queue;

public class GLDaAFN {
    HashMap<String, String> estadosGLD = new HashMap<>();
    Queue<String> gld = new LinkedList<String>();

    public String numEstados = "";
    public String abcedario = "";
    public String inicial = "";
    
    
    public GLDaAFN(String archivo) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(archivo));
            String cadena = "";
            cadena = file.readLine();
            this.numEstados = cadena;
            cadena = file.readLine();
            this.abcedario = cadena;
            cadena = file.readLine();
            this.inicial = cadena;   
            file.close(); 

            while((cadena = file.readLine()) != null) {
                String[] conte = {};
                char[] separador = {};
                conte = cadena.split("->");
                if(cadena.length() > 2) {
                    
                }
            }
        }catch(FileNotFoundException e) {
            System.out.println("ARCHIVO NO VALIDO");
        }catch(IOException e) {

        }
        
    }

    public void getInfo() {
        System.out.println(this.numEstados);
        System.out.println(this.abcedario);
        System.out.println(this.inicial);
    }
}
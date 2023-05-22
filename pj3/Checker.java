import java.io.*;
import java.util.*;

public class Checker {
    //Variables
    String directory = "";
    ArrayList<String> lines = new ArrayList<>();

    //Constructor
    public Checker(String fileToCheck) {
        try {
            this.directory = fileToCheck;//Saving to class vars
            BufferedReader br = new BufferedReader(new FileReader(this.directory));//Initializing reader
            String chain = "";

            while ((chain = br.readLine()) != null) {
                String[] chains;
                lines.add(chain);
                chains = chain.split("->");

                if (chains[1].length() > 2) /*Checks if terminal is more than one char*/ {
                    
                }    
                //     separador = prueba[1].toCharArray();
                //     transiciones.add(prueba[0]+ "->" + separador[0] + prueba[0]+cont);
                //     transiciones.add(prueba[0]+cont+ "->" + separador[1] + separador[2]);
                //     cont++;
                // } else if (prueba[1].length() == 2) /*2nd Check is equal to 2*/ {
                //     separador = prueba[1].toCharArray();
                //     String var = separador[1]+"";
                //         if (var.equals(var.toUpperCase())) {
                //             transiciones.add(cadena);
                //         } else {
                //             transiciones.add(prueba[0]+"->"+separador[0]+prueba[0]+contEq);
                //             transiciones.add(prueba[0]+contEq+"->"+separador[1]);
                //         }
                // } else if (prueba[1].length() == 1) /*3rd Check is equal to 1*/ {
                //     estadosFT += prueba[0] + ",";
                //     transiciones.add(cadena);
                // }
            }
            br.close();
        } catch (Exception e) {}
        
    }
    //Metodos
    public void getInfo() {
        System.out.println("Program Stats:\n");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(this.lines.get(i));
        }
    }
}

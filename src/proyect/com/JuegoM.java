package proyect.com;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class JuegoM {

    private final int Espacio_Linea = 73;

    private String pregunta;
    private String ResA, ResB, ResC, ResD;
    private String ResCorrecta;
    private int DineroG;
    private BufferedReader in;

    public JuegoM() {
        DineroG = 0;

        try {
            in = new BufferedReader(new FileReader("src/files/questionFile"));
        } catch (IOException e) {
            System.err.println("Archivo erroneo: " + e);
            System.exit(1);
        }
    }

    public void siguientePreg() {
    	 if (DineroG == 0) {
             DineroG = 100000;
         } else if (DineroG == 500000) {
             DineroG = 500000+250000;
         } else if (DineroG == 750000) {
             DineroG = 750000+250000;
         } else if (DineroG == 1000000) {
             DineroG = 1000000+1500000;
         } else if (DineroG == 2500000) {
             DineroG = 2500000+2500000;
         } else if (DineroG == 5000000) {
             DineroG = 5000000+5000000;
         } else if (DineroG == 10000000) {
             DineroG = 10000000+2500000;
         } else if (DineroG == 12500000) {
             DineroG = 12500000+2500000;
         } else if (DineroG == 15000000) {
             DineroG = 15000000+2500000;
         } else if (DineroG == 17500000) {
             DineroG = 17500000+2500000;
         } else if (DineroG == 20000000) {
             DineroG = 20000000+5000000; 
         } else {
             DineroG = 100000 + DineroG;
         }
    	 
        pregunta = extraerPreg();

        ResA = extraerRes("A");
        ResB = extraerRes("B");
        ResC = extraerRes("C");
        ResD = extraerRes("D");

        ResCorrecta = extraerCorrectaPreg();
    }

    private void salir() {
        System.err.println("Error en el archivo de preguntas");
        System.exit(1);
    }

    private String extraerPreg() {

        String Preg = "";
        try {

            String linea = in.readLine();
            if (linea == null) {
                salir();
            }
            StringTokenizer Muestrax = new StringTokenizer(linea);
            String MuestraPrin = Muestrax.nextToken();
            if (!MuestraPrin.equals("<Q>")) {
                salir();
            }
            boolean listo = false;
            while (Muestrax.hasMoreTokens() && !listo) {
                MuestraPrin = Muestrax.nextToken();
                if (MuestraPrin.equals("</Q>")) {
                    listo = true;
                } else {
                    Preg += " " + MuestraPrin;
                }
            }
            while (!listo && (linea = in.readLine()) != null) {
                Muestrax = new StringTokenizer(linea);
                while (Muestrax.hasMoreTokens() && !listo) {
                    MuestraPrin = Muestrax.nextToken();
                    if (MuestraPrin.equals("</Q>")) {
                        listo = true;
                    } else {
                        Preg += " " + MuestraPrin;
                    }
                }
            }

            if (!listo) //No </Q> en el archivo
            {
                salir();
            }
        } catch (IOException e) {
            System.err.println("Archivo Erroneo: " + e);
            System.exit(1);
        }

        return formatoPreg(Preg);
    }

    private String formatoPreg(String Preg) {
        StringTokenizer tokenizer = new StringTokenizer(Preg);
        int Contador = 0;
        String pregx = "";
        String Muestra;
        while (tokenizer.hasMoreTokens()) {
            Muestra = tokenizer.nextToken();
            if (Muestra.length() + Contador > Espacio_Linea) {
                pregx = pregx + "\n" + Muestra + " ";
                Contador = Muestra.length() + 1;
            } else if (Muestra.length() + Contador == Espacio_Linea) {
                pregx = pregx + Muestra + "\n";
                Contador = 0;
            } else {
                pregx = pregx + Muestra + " ";
                Contador = Contador + Muestra.length() + 1;
            }
        }
        return pregx;
    }

    private String extraerRes(String Res) {
        String Resp = "";
        String linea;
        try {
            linea = in.readLine();
            StringTokenizer Muestra = new StringTokenizer(linea);
            if (Muestra.countTokens() < 3) {
                salir();
            }
            String MuestraP = Muestra.nextToken();
            if (!MuestraP.equals("<" + Res + ">")) {
                salir();
            }
            while (Muestra.hasMoreTokens()) {
                MuestraP = Muestra.nextToken();
                if (MuestraP.equals("</" + Res + ">")) {
                    return Resp;
                } else {
                    Resp = Resp + MuestraP + " ";
                }
            }
            salir();
        } catch (IOException e) {
            salir();
        }
        return Resp;
    }

    private String extraerCorrectaPreg() {
        String RespCorrecta = "";
        String linea;
        try {
            linea = in.readLine();
            StringTokenizer Muestra = new StringTokenizer(linea);
            if (Muestra.countTokens() < 3) {
                salir();
            }
            String MuestraP = Muestra.nextToken();
            if (!MuestraP.equals("<ANS>")) {
                salir();
            }
            RespCorrecta = Muestra.nextToken();
            MuestraP = Muestra.nextToken();
            if (!MuestraP.equals("</ANS>")) {
                salir();
            }
            return RespCorrecta;
        } catch (IOException e) {
            salir();
        }
        return RespCorrecta;
    }

    public String obtenerResp() {

        return pregunta;
    }

    public String obtextodePreg(String respuesta) {
        if (respuesta.equals("A")) {
            return ResA;
        }
        if (respuesta.equals("B")) {
            return ResB;
        }
        if (respuesta.equals("C")) {
            return ResC;
        }
        if (respuesta.equals("D")) {
            return ResD;
        } else {
            return "";
        }
    }

    public boolean esResCorrecta(String adivinar) {
        return (adivinar.equals(ResCorrecta));
    }

    public boolean haGanado() {
        return (DineroG == 25000000);
    }

    public String obtenerDineroGanado() {
        String Dinero = "";
        if (DineroG == 25000000) {
            Dinero = "₡ 25,000,000";
        } else if (DineroG == 500000) {
            Dinero = "₡ 5,00000.00 Zona Segura ";
        } else if (DineroG == 10000000) {
            Dinero = "₡ 10,000,000 Zona Segura ";
        }else {
            Dinero = Dinero + "₡ " + DineroG;
        }
        return Dinero;
    }

    public static void main(String[] args) {
        new JuegoGUI(new JuegoM());
    }
}

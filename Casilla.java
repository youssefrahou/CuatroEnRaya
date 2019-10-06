public class Casilla {
    boolean vacia = true;
    String dibujo;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Casilla(String dibujo) {
        this.dibujo = dibujo;
    }

    public String getDibujo() {
    	return dibujo;
    }

    public void setDibujo() {
        if (Tablero.jugadorActual == Tablero.jugador1) {
            dibujo = ANSI_GREEN + "[O]" + ANSI_RESET;
        }
        if (Tablero.jugadorActual == Tablero.jugador2) {
            dibujo = ANSI_BLUE + "[X]" + ANSI_RESET;
        }

    }
    public void setRojo(){
        if (Tablero.jugadorActual == Tablero.jugador1) {
            dibujo = ANSI_RED + "[O]" + ANSI_RESET;
        }
        if (Tablero.jugadorActual == Tablero.jugador2) {
            dibujo = ANSI_RED + "[X]" + ANSI_RESET;
        }
    }

    public void setLlena() {
        vacia = false;
    }



}


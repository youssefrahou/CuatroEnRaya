import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

public class Tablero {

    private boolean finPartida = false;
    static String jugadorActual;
    static String jugador1;
    static String jugador2;
    private static int columna = 6;
    private Casilla[][] tablero = new Casilla[6][6];
    private static int filaActual;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";


    private void generarTablero() {

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = new Casilla( "."+j+".");
            }
        }

    }


    private void imprimir() {


        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j].getDibujo() + " ");
            }
            System.out.println();
        }


    }

    private void pedirNombres() {

        Scanner entrada = new Scanner(System.in);

        System.out.print("Introduce el nombre del jugador 1: ");
        jugador1 = entrada.nextLine();

        System.out.print("Introduce el nombre del jugador 2: ");
        jugador2 = entrada.nextLine();

        System.out.println();
        System.out.println();
        System.out.println();

    }

    private void pedirDatos() {

        Scanner entrada = new Scanner(System.in);

        jugadorActual = jugador2;

        do {
            if (jugadorActual == jugador2) {
                jugadorActual = jugador1;
            } else {
                jugadorActual = jugador2;
            }



            try{
                System.out.println("Elija una posición " + jugadorActual);
                System.out.print("Columna: ");
                columna = entrada.nextInt();


                comprobarVacia(columna);

                ponerFicha(columna);
                imprimir();
                comprobarGanado(columna);
            }catch (ArrayIndexOutOfBoundsException e){

                System.out.println(ANSI_RED + "Por favor, elija una columna de la 0 a la 5" + ANSI_RESET);
                System.out.println();
                System.out.println();
                System.out.println();

                if (jugadorActual == jugador2) {
                    jugadorActual = jugador1;
                } else {
                    jugadorActual = jugador2;
                }

            }

        } while (!finPartida);

    }

    private void setFinPartida(){

        System.out.println(ANSI_YELLOW + "¡Has ganado " + jugadorActual + "!" + ANSI_RESET);
        finPartida = true;

    }

    private void comprobarVacia(int col) {



        if (!tablero[0][col].vacia) {

            if (jugadorActual == jugador2) {
                jugadorActual = jugador1;
            } else {
                jugadorActual = jugador2;
            }

            System.out.println(ANSI_RED + "Elija una columna correcta" + ANSI_RESET);
            pedirDatos();
        }
    }

    private void ponerFicha(int col) {
        for (int i = tablero.length - 1; i >= 0; i--) {
            if (tablero[i][col].vacia) {
                tablero[i][col].setLlena();
                tablero[i][col].setDibujo();
                filaActual=i;
                break;
            }
        }
    }

    private void comprobarGanado(int col){

        for (int i= -3; i <=0; i++ ){
            if ((i==0 || i==-3)){
                for (int k = -3; k <=3; k++){
                    if (k==3 || k==0 || k==-3){

                        int direcFila = 0;
                        int direcCol = 0;
                        int cont=0;

                        switch (i){
                            case -3: direcCol= 1;
                            break;
                            case 0: direcCol= 0;
                            break;
                            case 3: direcCol =-1;
                            break;
                        }

                        switch (k){
                            case -3: direcFila= 1;
                            break;
                            case 0: direcFila= 0;
                            break;
                            case 3: direcFila =-1;
                            break;
                        }

                        for (int m=0; m < 7; m++){

                            if ((col+i)+m*direcCol >= 0 && (col+i)+m*direcCol < tablero.length && (filaActual+k)+m*direcFila >=0 && (filaActual+k)+m*direcFila < tablero.length){
                                if (tablero[filaActual][col].getDibujo()==tablero[(filaActual+k)+m*direcFila][(col+i)+m*direcCol].getDibujo()){
                                    if (i == 0 && k == 0) {
                                    	cont = 0;
                                    } else {
                                    	cont++;
                                    }
                                    if (cont==4){
                                        tablero[filaActual][col].setRojo();
                                        tablero[(filaActual+k)+(m-1)*direcFila][(col+i)+(m-1)*direcCol].setRojo();
                                        tablero[(filaActual+k)+(m-2)*direcFila][(col+i)+(m-2)*direcCol].setRojo();
                                        tablero[(filaActual+k)+(m-3)*direcFila][(col+i)+(m-3)*direcCol].setRojo();

                                        imprimir();
                                        setFinPartida();
                                    }

                                }else{
                                    cont=0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void jugar(){

        generarTablero();
        pedirNombres();
        imprimir();
        pedirDatos();

    }
}


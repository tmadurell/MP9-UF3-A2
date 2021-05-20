package Pescamines;

import java.io.*;

public class Main {

    /** Aquí jugarem amb castella */
    public static boolean castellano = false;

    /** Aquí jugarem amb catalá */
    public static boolean catala = false;

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     * @throws Exception
     *             the exception
     */
    public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean opcio = true;
	boolean opcio2 = true;
	boolean opcio3 = true;
	boolean llengua = true;
	boolean partidaMenu = true;
	int dimensions = 0;
	int dimensions2 = 0;
	int numeroMinas = 0;

	// Selector de idioma b�sico
	while (opcio3) {
		System.out.println("Si us plau, selecciona un idioma / Por favor, selecciona el idioma");
	    System.out.println("a) Catala");
	    System.out.println("b) Castella");
	    String idioma = br.readLine();
	    EscribirRecord gestorGuardado = new EscribirRecord();
	    String fileName = "Victorias.txt";
	    String nombre;

	    // Solicitud del nombre de usuario para el record
	    System.out.println("Por favor, introduce tu nombre");
	    nombre = br.readLine();
	    
	    do {
		// Men� castellano
		if (idioma.equals("b") | idioma.equals("Espa�ol") | idioma.equals("Castella") | idioma.equals("Castellano")) {
		    castellano = true;
		    while (opcio) {
			opcio2 = true;
			System.out.println("Escoge una opci�n (a, b, c, d, e)");
			System.out.println("a) Empezar partida");
			System.out.println("b) Ver puntuaciones");
			System.out.println("c) Cambiar usuario");
			System.out.println("d) Salir.");

			String linea = br.readLine();

			do {
			    // Tableros por defecto o personalizado
			    if (linea.equals("a")) {
				System.out
					.println("Introduzca las dimensiones del tablero y la cantidad de minas\na) Principiante: 10 minas y 9x9\nb) Intermedio: 40 minas y 16x16\nc) Avanzado: 99 minas y 16x30\nd) Personalizado: introduce tus propios valores\ne) Volver al men� principal");
				String lineaPartida = br.readLine();
				do {
				    if (lineaPartida.equals("a")) {
					// Dimensiones del tablero por defecto.
					// Se replica en los siguientes
					dimensions = 9;
					dimensions2 = 9;
					numeroMinas = 10;
					// Inicio del tiempo de juego para el
					// record
					long inicioJuego = System.nanoTime();
					// Creaci�n del tablero de juego
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					// Fin del tiempo de partida
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					// Si la partida termin� en victoria, se
					// escribe el record
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("b")) {
					dimensions = 16;
					dimensions2 = 16;
					numeroMinas = 40;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}

				    }
				    if (lineaPartida.equals("c")) {
					dimensions = 16;
					dimensions2 = 30;
					numeroMinas = 99;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("d")) {
					// Personalizado, se solicitan los datos
					// al usuario
					System.out
						.println("Introduzca las dimensiones del lado 1 (Hasta 100)");
					dimensions = (Integer.parseInt(br
						.readLine()));
					if (dimensions > 100) {
					    System.err
						    .println("Error. M�ximo 100, por favor\n");
					    break;
					}
					System.out
						.println("Introduzca las dimensiones del lado 2 (Hasta 100)");
					dimensions2 = (Integer.parseInt(br
						.readLine()));
					if (dimensions2 > 100) {
					    System.err
						    .println("Error. M�ximo 100, por favor\n");
					    break;
					}
					System.out
						.println("Introduzca el n�mero de minas");
					numeroMinas = (Integer.parseInt(br
						.readLine()));
					if (numeroMinas >= dimensions
						* dimensions2) {
					    System.err
						    .println("Error. Todas las casillas ser�n minas, la victoria es imposible\n");
					    break;
					}
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("e")) {
					// Volver al men� principal
					partidaMenu = false;
					opcio2 = false;
				    }
				    partidaMenu = false;
				} while (partidaMenu);

			    }
			    if (linea.equals("b")) {
				// Leer el fichero de victorias
				gestorGuardado.getWins();
				opcio2 = false;
			    }
			    if (linea.equals("c")) {
				// Cambio del nombre de usuario
				System.out
					.println("Introduce el nombre de usuario");
				nombre = br.readLine();
				opcio2 = false;
			    }
			    if (linea.equals("d")) {
				// Salir del programa
				opcio3 = false;
				llengua = false;
				opcio = false;
				opcio2 = false;
			    }

			    // Error en la selecci�n, pausa y regreso al men�
			    if (!(linea.equals("a") || linea.equals("b")
				    || linea.equals("c") || linea.equals("d"))) {
				System.err
					.println("Error. Por favor, introduce a, b, c � d");
				try {
				    Thread.sleep(2000);
				} catch (InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				opcio2 = false;
			    }
			} while (opcio2);

		    }
		}
		// Menú catala
		if (idioma.equals("a") | idioma.equals("Catala")) {
		    catala = true;
		    while (opcio) {
			opcio2 = true;
			System.out.println("Choose an option (a, b, c, d, e)");
			System.out.println("a) Start game");
			System.out.println("b) See records");
			System.out.println("c) Change username");
			System.out.println("d) Exit.");

			String linea = br.readLine();

			do {
			    // Tableros por defecto o personalizado
			    if (linea.equals("a")) {
				System.out
					.println("Enter the dimensions of the board and the number of mines\na) Beginner: 10 mines y 9x9\nb) Intermediate: 40 mines y 16x16\nc) Advanced: 99 mines y 16x30\nd) Custom: enter your own values\ne) Return to main menu");
				String lineaPartida = br.readLine();
				do {
				    if (lineaPartida.equals("a")) {
					// Dimensiones del tablero por defecto.
					// Se replica en los siguientes
					dimensions = 9;
					dimensions2 = 9;
					numeroMinas = 10;
					// Inicio del tiempo de juego para el
					// record
					long inicioJuego = System.nanoTime();
					// Creaci�n del tablero de juego
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					// Fin del tiempo de partida
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					// Si la partida termin� en victoria, se
					// escribe el record
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("b")) {
					dimensions = 16;
					dimensions2 = 16;
					numeroMinas = 40;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}

				    }
				    if (lineaPartida.equals("c")) {
					dimensions = 16;
					dimensions2 = 30;
					numeroMinas = 99;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("d")) {
					// Personalizado, se solicitan los datos
					// al usuario
					System.out
						.println("Enter side 1 size (Until 100)");
					dimensions = (Integer.parseInt(br
						.readLine()));
					if (dimensions > 100) {
					    System.err
						    .println("Error. Maximum 100, please\n");
					    break;
					}
					System.out
						.println("Enter side 2 size (Until 100)");
					dimensions2 = (Integer.parseInt(br
						.readLine()));
					if (dimensions2 > 100) {
					    System.err
						    .println("Error. Maximum 100, please\n");
					    break;
					}
					System.out
						.println("Enter the number of mines");
					numeroMinas = (Integer.parseInt(br
						.readLine()));
					if (numeroMinas >= dimensions
						* dimensions2) {
					    System.err
						    .println("Error. All boxes will be mines, victory is impossible\n");
					    break;
					}
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("e")) {
					// Volver al men� principal
					partidaMenu = false;
					opcio2 = false;
				    }
				    partidaMenu = false;
				} while (partidaMenu);

			    }
			    if (linea.equals("b")) {
				// Leer el fichero de victorias
				gestorGuardado.getWins();
				opcio2 = false;
			    }
			    if (linea.equals("c")) {
				// Cambio del nombre de usuario
				System.out.println("Enter the username");
				nombre = br.readLine();
				opcio2 = false;
			    }
			    if (linea.equals("d")) {
				// Salir del programa
				opcio3 = false;
				llengua = false;
				opcio = false;
				opcio2 = false;
			    }

			    // Error en la selecci�n, pausa y regreso al men�
			    if (!(linea.equals("a") || linea.equals("b")
				    || linea.equals("c") || linea.equals("d"))) {
				System.err
					.println("Error. Please, input a, b, c � d");
				try {
				    Thread.sleep(2000);
				} catch (InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				opcio2 = false;
			    }
			} while (opcio2);

		    }
		}
		// Error en la selecci�n, pausa y regreso al men�
		if (!(idioma.equals("a") || idioma.equals("b")
			|| idioma.equals("Espa�ol") || idioma.equals("English"))) {
		    System.err
			    .println("Error. Por favor, selecciona el idioma");
		    System.err.println("Please, select your language");
		    try {
			Thread.sleep(2000);
		    } catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		    }
		    llengua = false;
		}

	    } while (llengua == true);
	}
    }
}

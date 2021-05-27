package Pescamines;

import java.io.*;

public class ClientJoc {

    /** Aquí jugarem amb castella */
    public static boolean castellano = false;

    /** Aquí jugarem amb catalá */
    public static boolean catala = false;


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

	// Selecionar el idioma básico
	while (opcio3) {
		System.out.println("Si us plau, selecciona un idioma / Por favor, selecciona el idioma");
	    System.out.println("a) Catala");
	    System.out.println("b) Castella");
	    String idioma = br.readLine();
	    EscribirRecord gestorGuardado = new EscribirRecord();
	    String fileName = "Victorias.txt";
	    String nombre;

	    // Solicitud del nombre de usuario para el record
		System.out.println("Si us plau introdueix un nom / Por favor, introduce tu nombre");
	    nombre = br.readLine();
	    
	    do {
		// Menú castellano
		if (idioma.equals("b") | idioma.equals("B") | idioma.equals("Español") | idioma.equals("Castella") | idioma.equals("Castellano")) {
		    castellano = true;
		    while (opcio) {
			opcio2 = true;
			System.out.println("Escoge una opción (a, b, c, d, e)");
			System.out.println("a) Empezar una partida");
			System.out.println("b) Ver puntuaciones");
			System.out.println("c) Cambiar usuario");
			System.out.println("d) Salir.");

			String linea = br.readLine();

			do {
			    // Tableros por defecto o personalizado
			    if (linea.equals("a")) {
				System.out
						.println("Introduzca las dimensiones del tablero y la cantidad de minas\na) Fácil: 10 minas y 9x9\nb) Intermedio: 40 minas y 16x16\nc) Difícil: 99 minas y 16x30\nd) Personalizado: introduce tus propios valores\ne) Volver al menú principal");
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
					// Creación del tablero de juego
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					// Fin del tiempo de partida
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					// Si la partida terminá en victoria, se
					// escribe el record
					if (TableroJoc.record) {
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
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJoc.record) {
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
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJoc.record) {
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
						    .println("Error. Máximo 100, por favor\n");
					    break;
					}
					System.out
						.println("Introduzca las dimensiones del lado 2 (Hasta 100)");
					dimensions2 = (Integer.parseInt(br
						.readLine()));
					if (dimensions2 > 100) {
					    System.err
						    .println("Error. Máximo 100, por favor\n");
					    break;
					}
					System.out
						.println("Introduzca el número de minas");
					numeroMinas = (Integer.parseInt(br
						.readLine()));
					if (numeroMinas >= dimensions
						* dimensions2) {
					    System.err
						    .println("Error. Todas las casillas serán minas, la victoria es imposible\n");
					    break;
					}
					long inicioJuego = System.nanoTime();
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJoc.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("e")) {
					// Volver al menú principal
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

			    // Error en la selección, pausa y regreso al menú
			    if (!(linea.equals("a") || linea.equals("b")
				    || linea.equals("c") || linea.equals("d"))) {
				System.err
					.println("Error. Por favor, introduce a, b, c ó d");
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
		if (idioma.equals("a") | idioma.equals("Catala") | idioma.equals("A")) {
		    catala = true;
		    while (opcio) {
			opcio2 = true;
			System.out.println("Trieu una opció (a, b, c, d, e)");
			System.out.println("a) Començar el joc");
			System.out.println("b) Veure puntuacions");
			System.out.println("c) Canviar usuari");
			System.out.println("d) Sortir.");

			String linea = br.readLine();

			do {
			    // Tableros por defecto o personalizado
			    if (linea.equals("a")) {
				System.out
						.println("Introduïu les dimensions de l'tauler i la quantitat de mines \n a) Principiant: 10 mines i 9x9 \n b) Intermedi: 40 mines i 16x16 \n c) Dificil: 99 mines i 16x30 \n d) Personalitzat: introdueix els teus propis valors \n e) Tornar al menú principal ");
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
					// Creación del tablero de juego
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					// Fin del tiempo de partida
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					// Si la partida termin� en victoria, se
					// escribe el record
					if (TableroJoc.record) {
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
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJoc.record) {
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
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJoc.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("d")) {
					// Personalizado, se solicitan los datos
					// al usuario
						System.out
								.println("Introduïu les dimensions de l'costat 1 (Fins a 100)");
					dimensions = (Integer.parseInt(br
						.readLine()));
					if (dimensions > 100) {
						System.err
								.println("Error. Maxim 100, si us plau\n");
					    break;
					}
						System.out
								.println("Introduïu les dimensions de l'costat 2 (Fins a 100)");
					dimensions2 = (Integer.parseInt(br
						.readLine()));
					if (dimensions2 > 100) {
						System.err
								.println("Error. Maxim 100, si us plau\n");
					    break;
					}
						System.out
								.println("Introdueix el número de mines");
					numeroMinas = (Integer.parseInt(br
						.readLine()));
					if (numeroMinas >= dimensions
						* dimensions2) {
						System.err
								.println("Error. Totes les caselles seran mines, la victòria és impossible\n");
						break;
					}
					long inicioJuego = System.nanoTime();
					TableroJoc juego = new TableroJoc(
						dimensions, dimensions2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJoc.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("e")) {
					// Volver al menú principal
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
					System.out.println("Escriu al nom de usuari");
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
						.println("Error. Si us plau, introdueix a, b, c ó d");
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
		// Error en la selección, pausa y regreso al menú
		if (!(idioma.equals("a") || idioma.equals("b")
			|| idioma.equals("Español") || idioma.equals("English"))) {
		    System.err
			    .println("Error. Por favor, selecciona el idioma");
			System.err.println("Si us plau, seleccioneu un idioma");
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

package Pescamines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TableroJoc {


    private static class Tablero {

	/** The oculto. */
	public boolean oculto = true;

	/** The mina. */
	public final boolean mina;

	/** The colindantes. */
	public int colindantes = 0;

	/**
	 * Instantiates a new tablero.
	 *
	 * @param mina
	 *            the mina
	 */
	public Tablero(boolean mina) {
	    this.mina = mina;
	}
    }

    /** The dimension. */
    private final int dimension;

    /** The dimension2. */
    private final int dimension2;

    /** The board. */
    private Tablero[][] board;

    /** The victoria. */
    private boolean victoria = false;

    /** The c. */
    private Tablero c;

    /** The numero minas2. */
    private int numeroMinas2;

    /** The movimientos. */
    public static int movimientos = 0;

    /** The record. */
    public static boolean record = false;

    /**
     * Instantiates a new tablero juego.
     *
     * @param dimension
     *            the dimension
     * @param dimension2
     *            the dimension2
     * @param numeroMinas
     *            the numero minas
     * @throws NumberFormatException
     *             the number format exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */

    // Se crea el tablero de juego y se solicita la introducción de coordenadas
    // mediante el método. No para hasta que victoria sea true
    public TableroJoc(int dimension, int dimension2, int numeroMinas)
	    throws NumberFormatException, IOException {
	this.dimension = dimension;
	this.dimension2 = dimension2;
	numeroMinas2 = numeroMinas;
	crearBoard(numeroMinas);
	calcularColindantes();
	verBoard();
	do {
	    solicitarCoordenadas();
	} while (victoria == false);
    }

    /**
     * Crear board.
     *
     * @param numeroMinas
     *            the numero minas
     */
    // Crea el tablero y coloca las minas de forma aleator�a con shuffle
    public void crearBoard(int numeroMinas) {
	movimientos = 0;
	record = false;
	List<Tablero> minas = new ArrayList<Tablero>();
	for (int i = 0; i < dimension * dimension2; i++) {
	    minas.add(new Tablero(i < numeroMinas));
	}
	Collections.shuffle(minas);
	board = new Tablero[dimension + 2][dimension2 + 2];
	for (int i = 0; i < dimension + 2; i++) {
	    for (int j = 0; j < dimension2 + 2; j++) {
		board[i][j] = (i == 0 || j == 0 || i == dimension + 1 || j == dimension2 + 1) ? new Tablero(
			false) : minas.remove(0);
	    }
	}
    }

    /**
     * Solicitar coordenadas.
     *
     * @throws NumberFormatException
     *             the number format exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */

    // Solicita las coordenadas al usuario y comprueba las condiciones de
    // victoria y derrota tras cada turno
    public void solicitarCoordenadas() throws NumberFormatException,
	    IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	if (ClientJoc.castellano) {
		System.out.println("Introduzca una coordenada vertical Y (1-" + dimension
				+ ")");
		int coordenadaY = (Integer.parseInt(br.readLine()));
		System.out.println("Introduzca una coordenada horizontal X (1-" + dimension2
				+ ")");
	    int coordenadaX = (Integer.parseInt(br.readLine()));
	    c = board[coordenadaY][coordenadaX];
	    movimientos++;
	    descubrir(coordenadaY, coordenadaX);
	    verBoard();
	    comprobarDerrota();
	    comprobarVictoria();
	    calcularColindantes();
	}
	if (ClientJoc.catala) {
		System.out.println("Introduïu una coordenada vertical Y (1-" + dimension + ")");
	    int coordenadaY = (Integer.parseInt(br.readLine()));
		System.out.println("Introduïu una coordenada horitzontal X (1-" + dimension2 + ")");
	    int coordenadaX = (Integer.parseInt(br.readLine()));
	    c = board[coordenadaY][coordenadaX];
	    movimientos++;
	    descubrir(coordenadaY, coordenadaX);
	    verBoard();
	    comprobarDerrota();
	    comprobarVictoria();
	    calcularColindantes();
	}
    }



    // Método para descubrir las casillas colindantes cuando sale un 0.
    // Recursivo.
    public boolean descubrir(int x, int y) {
	if (!dentroTablero(x, y)) {
	    return false;
	}
	c = board[x][y];

	if (!c.oculto) {
	    return c.mina;
	}

	c.oculto = false;

	if (c.mina) {
	    return true;
	}

	if (c.colindantes > 0) {
	    return false;
	}

	for (int i = x - 1; i <= x + 1; i++) {
	    for (int j = y - 1; j <= y + 1; j++) {
		descubrir(i, j);
	    }
	}

	return false;
    }

    /**
     * Dentro tablero.
     *
     * @param x
     *            the x
     * @param y
     *            the y
     * @return true, if successful
     */
    // Método para comprobar si la casilla est� dentro del tablero.
    public boolean dentroTablero(int x, int y) {
	if (x > 0 && y > 0 && x <= dimension && y <= dimension2) {
	    return true;
	} else {
	    return false;
	}

    }

    /**
     * Comprobar derrota y mostrar donde estaban las minas.
     */
    public void comprobarDerrota() {
	if (c.mina) {
	    if (ClientJoc.castellano) {
		System.out.println("\n\nAquí estaban las minas\n\n");
		for (int i = 1; i <= dimension; i++) {
		    System.out.printf("%2d| ", i);
		    for (int j = 1; j <= dimension2; j++) {
			Tablero f = board[i][j];
			if (f.oculto && f.mina) {
			    f.oculto = false;
			}
			if (f.oculto) {
			    System.out.print(".  ");
			} else if (f.mina) {
			    System.out.print("*  ");
			} else {
			    System.out.print(f.colindantes + "  ");
			}
		    }
		    System.out.println();
		}
		System.out.print("    ");
		for (int j = 1; j <= dimension2; j++) {
		    System.out.print(j + " ");
		}
		System.out.println();

		System.out.println("\n\nHas perdido. Vuelve a intentarlo\n\n");
		movimientos = 0;
		victoria = true;
	    }
	    if (ClientJoc.catala) {
		System.out.println("\n\nAquí hi havien mines\n\n");
		for (int i = 1; i <= dimension; i++) {
		    System.out.printf("%2d| ", i);
		    for (int j = 1; j <= dimension2; j++) {
			Tablero f = board[i][j];
			if (f.oculto && f.mina) {
			    f.oculto = false;
			}
			if (f.oculto) {
			    System.out.print(".  ");
			} else if (f.mina) {
			    System.out.print("*  ");
			} else {
			    System.out.print(f.colindantes + "  ");
			}
		    }
		    System.out.println();
		}
		System.out.print("    ");
		for (int j = 1; j <= dimension2; j++) {
		    System.out.print(j + " ");
		}
		System.out.println();

		System.out.println("\n\nPerds. Torna-ho a provar\n\n");
		movimientos = 0;
		victoria = true;

	    }

	}
    }

    /**
     * Comprobar victoria y mostrar el número de movimientos.
     */
    public void comprobarVictoria() {
	int aux = 0;
	for (int i = 1; i <= dimension; i++) {
	    for (int j = 1; j <= dimension2; j++) {

		c = board[i][j];
		if (!c.oculto && !c.mina) {
		    aux++;
		}
	    }
	}

	if (ClientJoc.castellano) {

	    if (aux == (dimension * dimension2) - numeroMinas2) {
		System.out.println("\n\nHas ganado en " + movimientos
			+ " movimientos. Enhorabuena\n\n");
		victoria = true;
		record = true;
	    }
	}
	if (ClientJoc.catala) {
	    if (aux == (dimension * dimension2) - numeroMinas2) {
		System.out.println("\n\nHas guanyat en " + movimientos
			+ " moviments. Enhorabona\n\n");
		victoria = true;
		record = true;
	    }

	}

    }

    /**
     * Calcular colindantes.
     */
    public void calcularColindantes() {
	for (int i = 1; i <= dimension; i++) {
	    for (int j = 1; j <= dimension2; j++) {
		int aux = 0;
		for (int di = -1; di <= 1; di++) {
		    for (int dj = -1; dj <= 1; dj++) {
			if (board[i + di][j + dj].mina)
			    aux++;
		    }
		}
		board[i][j].colindantes = aux;
	    }
	}
    }

    /**
     * Ver board.
     */
    public void verBoard() {
	for (int i = 1; i <= dimension; i++) {
	    System.out.printf("%2d| ", i);
	    for (int j = 1; j <= dimension2; j++) {
		Tablero f = board[i][j];
		if (f.oculto) {
		    System.out.print(".  ");
		} else if (f.mina) {
		    System.out.print("*  ");
		} else {
		    System.out.print(f.colindantes + "  ");
		}
	    }
	    System.out.println();
	}
	System.out.print("    ");
	for (int j = 1; j <= dimension2; j++) {
	    System.out.print(j + " ");
	}
	System.out.println();
    }


    public int getMovimientos() {
	return movimientos;
    }
}
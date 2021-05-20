package Pescamines;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class EscribirRecord {

    /** The path. */
    // Obtenci�n del directorio del usuario, as�, siempre hay permisos de
    // escritura
    static String path = System.getProperty("user.home").replace("\\", "\\\\")
	    + "\\";

    /** The full path. */
    String fullPath = path + "Victorias.txt";

    /** The full path2. */
    String fullPath2 = path + "VictoriasOrdenado.txt";

    /** The victorias. */
    Path victorias = Paths.get(path, "Victorias.txt");

    /** The charset. */
    Charset charset = Charset.forName("ISO-8859-1");

    /**
     * Gets the wins.
     *
     * @return the wins
     */
    // Lectura del fichero de victorias
    public void getWins() {
	try {
	    FileReader fileReader = new FileReader(fullPath2);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    StringBuffer stringBuffer = new StringBuffer();
	    String line;
	    while ((line = bufferedReader.readLine()) != null) {
		stringBuffer.append(line);
		stringBuffer.append("\n");
	    }
	    fileReader.close();
	    if (Main.castellano) {
		System.out
			.println("\n\nOrdenado por tiempo\n\nLos records son:\n\n");
	    }
	    if (Main.catala) {
		System.out.println("\n\nSort by time\n\nThe records are:\n\n");
	    }
	    System.out.println(stringBuffer.toString());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Save wins.
     *
     * @param fileName
     *            the file name
     * @param nombre
     *            the nombre
     * @param wins
     *            the wins
     * @param tiempo
     *            the tiempo
     */
    // Guardado de los records
    public void saveWins(String fileName, String nombre, int wins, double tiempo) {

	try (PrintWriter out = new PrintWriter(new BufferedWriter(
		new FileWriter(path + fileName, true)))) {
	    out.print("Tiempo: " + tiempo + " segundos. " + nombre + ". ");
	    out.println("Movimientos: " + wins);
	    out.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Gestionar guardado.
     *
     * @param tiempo
     *            the tiempo
     * @param nombreFichero
     *            the nombre fichero
     * @param nombre
     *            the nombre
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    // M�todo para guardar, con la conversi�n a segundos del tiempo, y orden de
    // ordenar
    public void gestionarGuardado(long tiempo, String nombreFichero,
	    String nombre) throws IOException {
	double segundos = tiempo / 1000000000.0;
	saveWins(nombreFichero, nombre, TableroJuego.movimientos, segundos);
	Ordenar();
    }

    /**
     * Ordenar.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    // Coge el fichero sin ordenar y lo ordena en otro nuevo, que ser� el
    // mostrado al usuario.
    public void Ordenar() throws IOException {
	BufferedReader reader = null;
	PrintWriter outputStream = null;
	ArrayList<String> rows = new ArrayList<String>();

	try {
	    reader = new BufferedReader(new FileReader(fullPath));
	    outputStream = new PrintWriter(new FileWriter(fullPath2));

	    String file;
	    while ((file = reader.readLine()) != null) {
		rows.add(file);
	    }
	    Collections.sort(rows);
	    String[] strArr = rows.toArray(new String[0]);
	    for (String cur : strArr)
		outputStream.println(cur);
	} finally {
	    if (reader != null) {
		reader.close();
	    }
	    if (outputStream != null) {
		outputStream.close();
	    }
	}

    }
}

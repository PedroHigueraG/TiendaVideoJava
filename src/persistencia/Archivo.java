package persistencia;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Cistian Meneses y Pedro Higuera
 */
public class Archivo {
    BasesDatos bd;

    public void leerArchivo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            String linea;
            bd = new BasesDatos();
            bd.EstablecerConexion();

            // Apertura del fichero y creacion de BufferedReader
            archivo = new File("C:\\Users\\USER\\Datos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            //llamado a los metodos
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                empleado(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                usuario(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                actor(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                pelicula(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                categoria(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                peliculaCategoria(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                peliculaActor(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                formato(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                estado(linea);
            }
            for (int i = 0; i < 10; i++) {
                linea = br.readLine();
                cinta(linea);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrado del fichero 
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void empleado(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        bd.llenarEmpleado(id, partes[1], partes[2], partes[3], partes[4]);

    }

    public void usuario(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        bd.llenarUsuario(id, partes[1], partes[2], partes[3], partes[4], partes[5]);
    }

    public void actor(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        bd.llenarActor(id, partes[1], partes[2]);
    }

    public void pelicula(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        bd.llenarPelicula(id, partes[1], partes[2]);
    }

    public void categoria(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        bd.llenarCategoria(id, partes[1]);
    }

    public void peliculaCategoria(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        int idpeliculafk = Integer.parseInt(partes[1]);
        int idcategoriafk = Integer.parseInt(partes[2]);
        bd.llenarPeliculaCategoria(id, idpeliculafk, idcategoriafk);
    }

    public void peliculaActor(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        int idpeliculafk = Integer.parseInt(partes[1]);
        int idactorfk = Integer.parseInt(partes[2]);
        bd.llenarPeliculaActor(id, idpeliculafk, idactorfk);
    }

    public void formato(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        bd.llenarFormato(id, partes[1]);
    }

    public void estado(String linea) {
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        bd.llenarEstado(id, partes[1]);
    }
    
    public void cinta(String linea){
        String[] partes = linea.split("/");
        int id = Integer.parseInt(partes[0]);
        int idpeliculafk = Integer.parseInt(partes[1]);
        int idformatofk = Integer.parseInt(partes[2]);
        int idestadofk = Integer.parseInt(partes[3]);
        bd.llenarCinta(id, idpeliculafk, idformatofk, idestadofk);
    }
}

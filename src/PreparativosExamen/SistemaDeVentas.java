import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SistemaDeVentas {
    class DBHelper {
        private final String URL = "jdbc:mysql://localhost:3306/ventas";
        private final String USER = "root";
        private final String PASSWORD = "";

        // M�todo para ejecutar una consulta sin devolver resultados
        public static void ejecutarConsulta(String consulta) {
            try {
                // Establecer la conexi�n con la base de datos
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                // Crear la declaraci�n
                try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                    // Ejecutar la consulta
                    statement.executeUpdate();
                }

                // Cerrar la conexi�n
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // M�todo para ejecutar una consulta y devolver un conjunto de resultados
        public ResultSet ejecutarConsultaConResultado(String consulta) {
            try {
                // Establecer la conexi�n con la base de datos
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                // Crear la declaraci�n
                PreparedStatement statement = connection.prepareStatement(consulta);

                // Ejecutar la consulta y devolver el conjunto de resultados
                return statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

class Producto{
        private int producto_id;
        private String nombre;
        private double precio_por_unidad;
        private int stock;

    public Producto(int producto_id, String nombre, double precio_por_unidad, int stock) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio_por_unidad = precio_por_unidad;
        this.stock = stock;
        }

    @Override
    public String toString() {
        return "Producto{" +
                "producto_id=" + producto_id +
                ", nombre='" + nombre + '\'' +
                ", precio_por_unidad=" + precio_por_unidad +
                ", stock=" + stock +
                '}';
    }
}

    class Vendedor {
        private int vendedor_id;
        private String nombre;
        private String apellido;
        private String dni;
        private Date fecha_nacimiento;
        private Date fecha_contratacion;

        public Vendedor(int vendedor_id, String nombre, String apellido, String dni, Date fecha_nacimiento, Date fecha_contratacion) {
            this.vendedor_id = vendedor_id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.dni = dni;
            this.fecha_nacimiento = fecha_nacimiento;
            this.fecha_contratacion = fecha_contratacion;
        }

        public Vendedor(String consultaBusqueda) {
            String consulta = "SELECT * FROM vendedores WHERE id = '" + vendedor_id + "'";
            DBHelper dbHelper = new DBHelper();

            try {
                ResultSet resultSet = dbHelper.ejecutarConsultaConResultado(consulta);

                if (resultSet.next()) {

                    this.nombre = resultSet.getString("nombre");
                    this.apellido = resultSet.getString("apellido");
                    this.dni = resultSet.getString("dni");
                    this.fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
                    this.fecha_contratacion = resultSet.getDate("fecha_contratacion");
                } else {
                    System.out.println("No se pudo encontrar ningun vendedor con el ID ingresado");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Vendedor(String nombre, String apellido, String dni, Date fechaNacimiento, Date fechaContratacion) {
        }
    }

    class Comerciales {
        // Otros métodos y propiedades de la clase Comerciales

        public static Vendedor obtenerVendedorPorID(int vendedorID) {
            String consulta = "SELECT * FROM vendedores WHERE vendedor_id = " + vendedorID;
            
            DBHelper dbHelper = new DBHelper();

            try {
                // Ejecutar la consulta y obtener el conjunto de resultados
                ResultSet resultSet = dbHelper.ejecutarConsultaConResultado(consulta);

                // Verificar si hay resultados
                if (resultSet != null && resultSet.next()) {
                    
                    return new Vendedor(
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("dni"),
                            resultSet.getDate("fecha_nacimiento"),
                            resultSet.getDate("fecha_contratacion")
                    );
                } else {
                    System.out.println("No se encontró ningún vendedor con el ID proporcionado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static ArrayList<Vendedor> listadoDeVendedores() {
            // Consulta SQL para obtener todos los registros de la tabla 'vendedores'
            String consulta = "SELECT * FROM vendedores";

            // Utilizar la clase DBHelper para ejecutar la consulta SQL correspondiente
            DBHelper dbHelper = new DBHelper();

            // Lista para almacenar los objetos Vendedor
            ArrayList<Vendedor> listaDeVendedores = new ArrayList<>();

            try {
                // Ejecutar la consulta y obtener el conjunto de resultados
                ResultSet resultSet = dbHelper.ejecutarConsultaConResultado(consulta);

                // Iterar sobre los resultados y crear objetos Vendedor
                while (resultSet != null && resultSet.next()) {
                    Vendedor vendedor = new Vendedor(
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("dni"),
                            resultSet.getDate("fecha_nacimiento"),
                            resultSet.getDate("fecha_contratacion")
                    );

                    // Agregar el objeto Vendedor a la lista
                    listaDeVendedores.add(vendedor);
                }

                // Retornar la lista completa de vendedores
                return listaDeVendedores;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // En caso de excepción, retornar una lista vacía o manejar de otra manera según sea necesario
            return new ArrayList<>();
        }
    }
    class Productos {

        public static void generarInforme() {
            String consulta = "SELECT * FROM productos";

            DBHelper dbHelper = new DBHelper();

            try {
                ResultSet resultSet = dbHelper.ejecutarConsultaConResultado(consulta);

                System.out.println("Informe de Productos en Stock:");
                System.out.printf("%-30s%-10s%-10s%-10s%n", "Producto", "Stock", "Precio", "Total");
                System.out.println("----------------------------------------------------------");

                double valorTotalTodosProductos = 0;

                while (resultSet != null && resultSet.next()) {
                    String nombreProducto = resultSet.getString("nombre");
                    int stock = resultSet.getInt("stock");
                    double precioPorUnidad = resultSet.getDouble("precio_por_unidad");

                    double valorTotalProducto = stock * precioPorUnidad;

                    System.out.printf("%-30s%-10d%-10.2f%-10.2f%n", nombreProducto, stock, precioPorUnidad, valorTotalProducto);

                    valorTotalTodosProductos += valorTotalProducto;
                }

                System.out.println("----------------------------------------------------------");
                System.out.printf("%-50s%-10.2f%n", "Total:", valorTotalTodosProductos);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public static Producto obtenerProducto(int productoID) {
            String consulta = "SELECT * FROM productos WHERE producto_id = " + productoID;

            DBHelper dbHelper = new DBHelper();

            try {
                ResultSet resultSet = dbHelper.ejecutarConsultaConResultado(consulta);

                if (resultSet != null && resultSet.next()) {
                    return new Producto(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getDouble("precio_por_unidad"),
                            resultSet.getInt("stock")
                    );
                } else {
                    System.out.println("No se encontró ningún producto con el ID proporcionado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static Producto obtenerProductoMasVendido() {
            String consulta = "SELECT producto_id, SUM(cantidad_vendida) as total_vendido FROM ventas GROUP BY producto_id ORDER BY total_vendido DESC LIMIT 1";

            DBHelper dbHelper = new DBHelper();
            try {
                ResultSet resultSet = dbHelper.ejecutarConsultaConResultado(consulta);

                if (resultSet != null && resultSet.next()) {
                    int productoIDMasVendido = resultSet.getInt("producto_id");

                    // Utilizar el método obtenerProducto con la ID obtenida
                    return obtenerProducto(productoIDMasVendido);
                } else {
                    System.out.println("No se encontró ningún producto con el ID proporcionado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public static void main(String[] args){
        SistemaDeVentas.generarInforme();
    }
}

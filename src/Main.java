import collection.LibraryTree;
import collection.TreeNode;
import iterator.Iterator;
import model.Libro;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Configurar la estructura base
        TreeNode<Object> raiz = new TreeNode<>("Biblioteca Central");

        TreeNode<Object> catCiencias = new TreeNode<>("Ciencias Exactas");
        TreeNode<Object> catLiteratura = new TreeNode<>("Literatura");

        // Agregamos los libros con su ubicación por estante
        catCiencias.addChild(new TreeNode<>(new Libro("Física Universitaria", "Sears Zemansky", "Física", "Pasillo A - Estante 1")));
        catCiencias.addChild(new TreeNode<>(new Libro("Cálculo de una variable", "James Stewart", "Matemáticas", "Pasillo A - Estante 2")));
        catLiteratura.addChild(new TreeNode<>(new Libro("Cien Años de Soledad", "Gabriel García Márquez", "Novela", "Pasillo C - Estante 5")));

        raiz.addChild(catCiencias);
        raiz.addChild(catLiteratura);

        LibraryTree<Object> biblioteca = new LibraryTree<>(raiz);
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // 2. Menú Interactivo
        while (!salir) {
            System.out.println("\n======================================================");
            System.out.println(" 📚 Sistema de Búsqueda - Biblioteca ");
            System.out.println("======================================================");
            System.out.println("1. Explorar todo el catálogo");
            System.out.println("2. Buscar libro por palabra clave (Título o Autor)");
            System.out.println("3. Agregar nuevo libro");
            System.out.println("4. Salir");
            System.out.println("======================================================");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("\n--- Catálogo Completo ---");
                    Iterator<Object> iteradorTotal = biblioteca.createIterator();
                    while (iteradorTotal.hasNext()) {
                        Object elemento = iteradorTotal.next();
                        if (elemento instanceof Libro) {
                            System.out.println("  📖 Libro: " + ((Libro) elemento).toString());
                        } else {
                            System.out.println("📁 Categoría: " + elemento.toString());
                        }
                    }
                    break;

                case "2":
                    System.out.print("\nIngrese el término de búsqueda: ");
                    // trim() quita espacios accidentales al inicio/final y toLowerCase() lo pasa todo a minúsculas
                    String keyword = scanner.nextLine().trim().toLowerCase();
                    System.out.println("Buscando...");

                    Iterator<Object> iteradorBusqueda = biblioteca.createIterator();
                    boolean encontrado = false;

                    while (iteradorBusqueda.hasNext()) {
                        Object elemento = iteradorBusqueda.next();
                        if (elemento instanceof Libro) {
                            Libro libro = (Libro) elemento;
                            // Comparamos convirtiendo también los datos del libro a minúsculas
                            if (libro.getTitulo().toLowerCase().contains(keyword) ||
                                    libro.getAutor().toLowerCase().contains(keyword)) {
                                System.out.println("✅ Encontrado: " + libro.toString());
                                encontrado = true;
                            }
                        }
                    }
                    if (!encontrado) {
                        System.out.println("❌ No se encontraron resultados para: '" + keyword + "'");
                    }
                    break;

                case "3":
                    System.out.print("\nIngrese el título del libro: ");
                    String titulo = scanner.nextLine().trim();
                    System.out.print("Ingrese el autor: ");
                    String autor = scanner.nextLine().trim();
                    System.out.print("Ingrese la categoría: ");
                    String categoria = scanner.nextLine().trim();
                    System.out.print("Ingrese la ubicación (Estante): ");
                    String estante = scanner.nextLine().trim();

                    // LÓGICA DE CATEGORÍAS DINÁMICAS
                    TreeNode<Object> categoriaDestino = null;

                    // Buscamos si la categoría ya existe en los hijos de la raíz (ignorando mayúsculas/minúsculas)
                    for (TreeNode<Object> nodo : raiz.getChildren()) {
                        if (nodo.getData() instanceof String) {
                            String nombreCat = (String) nodo.getData();
                            if (nombreCat.equalsIgnoreCase(categoria)) {
                                categoriaDestino = nodo;
                                break;
                            }
                        }
                    }

                    // Si no la encontramos, creamos un nuevo nodo para la categoría
                    if (categoriaDestino == null) {
                        // Formateamos para que la primera letra sea mayúscula por estética
                        String nombreCategoriaLimpio = categoria.substring(0, 1).toUpperCase() + categoria.substring(1).toLowerCase();
                        categoriaDestino = new TreeNode<>(nombreCategoriaLimpio);
                        raiz.addChild(categoriaDestino);
                        System.out.println("✨ Nueva categoría de biblioteca '" + nombreCategoriaLimpio + "' creada automáticamente.");
                    }

                    // Agregamos el libro a la categoría destino (ya sea la existente o la nueva)
                    categoriaDestino.addChild(new TreeNode<>(new Libro(titulo, autor, categoria, estante)));
                    System.out.println("✅ Libro '" + titulo + "' agregado exitosamente a la categoría.");
                    break;

                case "4":
                    salir = true;
                    System.out.println("Cerrando el sistema... ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }
}
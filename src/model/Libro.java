package model;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private String estante; // Nueva variable de ubicación

    public Libro(String titulo, String autor, String categoria, String estante) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.estante = estante;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public String getEstante() { return estante; }

    @Override
    public String toString() {
        return titulo + " (Autor: " + autor + ", Categoría: " + categoria + ", Estante: " + estante + ")";
    }
}
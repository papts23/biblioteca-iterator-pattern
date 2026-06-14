package model;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private String pabellon; // Nuevo atributo agregado
    private String estante;

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public Libro(String titulo, String autor, String categoria, String pabellon, String estante) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.pabellon = pabellon;
        this.estante = estante;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getPabellon() {
        return pabellon;
    }

    public String getEstante() {
        return estante;
    }

    @Override
    public String toString() {
        return titulo + " (Autor: " + autor + ", Categoría: " + categoria + " | Ubicación: Pabellón " + pabellon + ", Estante: " + estante + ")";
    }
}
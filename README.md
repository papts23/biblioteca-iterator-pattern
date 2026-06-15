# Sistema de Búsqueda Bibliotecaria - Patrón Iterator

## Descripción
Sistema de consola en Java para gestionar y buscar libros. El catálogo está organizado mediante una estructura de datos jerárquica (Árbol N-ario) de categorías y subcategorías, aplicando el patrón de diseño de comportamiento **Iterator**.

## Arquitectura y Justificación del Algoritmo

El sistema se diseñó bajo los principios SOLID, separando la estructura de datos, el algoritmo de recorrido y la interfaz de usuario:

1. **Estructura (Árbol N-ario):** Representa fielmente una biblioteca real (`Raíz -> Categorías -> Libros`). Permite agregar nuevas categorías dinámicamente.
2. **Normalización (Modelo):** La clase `Libro` formatea automáticamente sus atributos críticos (títulos y pabellones a mayúsculas) para garantizar búsquedas exactas e insensibles a *case*.
3. **Patrón Iterator:** Implementación de las interfaces `Aggregate` e `Iterator` para desacoplar el cliente del árbol.

### Justificación del Algoritmo de Recorrido: DFS Iterativo
Para recorrer el árbol, se eligió el algoritmo de **Búsqueda en Profundidad (DFS - Depth First Search)** implementado de forma **iterativa** utilizando una Pila (`Stack`):

* **¿Por qué DFS?:** En una biblioteca, los datos objetivos (libros) se encuentran en las hojas de las ramas (categorías). DFS es ideal para llegar rápidamente a las hojas y agotar una categoría completa antes de pasar a la siguiente.
* **¿Por qué Iterativo con Pila y no Recursivo?:** Un iterador puro debe mantener su estado entre cada llamada de `next()`. Si se usara recursividad, el método no podría "pausarse" para devolver un solo elemento al cliente. Usar una `Stack` privada encapsulada dentro de `DepthFirstIterator` permite guardar el estado exacto del recorrido, cumpliendo estrictamente con la definición del patrón Iterator sin bloquear el hilo de ejecución.

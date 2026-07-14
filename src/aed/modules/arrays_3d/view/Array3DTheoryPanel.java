package aed.modules.arrays_3d.view;

import aed.core.view.TheoryPanel;

public class Array3DTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Arreglo Tridimensional (Array 3D)</h1>

        <h2 style='color:#0D47A1;'>¿Qué es un arreglo tridimensional?</h2>
        <p>Un <b>arreglo tridimensional</b> es una estructura de datos que almacena elementos en una malla de <b>capas (profundidad)</b>, <b>filas</b> y <b>columnas</b>. Cada elemento se accede mediante tres índices: <code>[capa][fila][columna]</code>.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características:</b>
        <ul>
          <li>Estructura tipo cubo: profundidad × filas × columnas</li>
          <li>Acceso directo: O(1) mediante <code>[d][f][c]</code></li>
          <li>Almacenamiento contiguo en memoria (row-major por capa)</li>
          <li>Útil para representar volúmenes, imágenes RGB, video frames</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Visualización por capas</h2>
        <p>Un arreglo 3D se visualiza como una secuencia de <b>capas 2D</b>. Cada capa es una matriz de filas × columnas. La interfaz permite navegar entre capas con los botones <b>Anterior / Siguiente</b>.</p>

        <h2 style='color:#0D47A1;'>Operaciones principales</h2>

        <h3>Asignar / Obtener</h3>
        <pre>
    cubo.set(1, 2, 3, 42);   // cubo[1][2][3] = 42
    int v = cubo.get(1, 2, 3); // v = 42</pre>

        <h3>Llenar / Aleatorio</h3>
        <p><code>fill(valor)</code> asigna un valor en todas las posiciones. <code>fillRandom()</code> llena con valores aleatorios 0-99.</p>

        <h3>Obtener una capa (slice)</h3>
        <p><code>getSlice(d)</code> devuelve la capa <b>d</b> como una matriz 2D de filas × columnas.</p>

        <h3>Búsqueda lineal</h3>
        <p>Recorre todas las capas, filas y columnas hasta encontrar el valor. Retorna la posición <code>[d, f, c]</code> o <code>null</code>. Complejidad: O(d×f×c).</p>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Complejidad</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Acceso [d][f][c]</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Recorrer todo</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(d×f×c)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Obtener capa</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(f×c)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Búsqueda</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(d×f×c)</td></tr>
        </table>
        </body></html>
        """;

    public Array3DTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

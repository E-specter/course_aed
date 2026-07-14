package aed.modules.arrays_2d.view;

import aed.core.view.TheoryPanel;

public class Array2DTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Arreglo Bidimensional (Array 2D)</h1>

        <h2 style='color:#0D47A1;'>¿Qué es un arreglo bidimensional?</h2>
        <p>Un <b>arreglo bidimensional</b> (matriz) es una estructura de datos que almacena elementos en una cuadrícula de <b>filas</b> y <b>columnas</b>. Cada elemento se accede mediante dos índices: <code>[fila][columna]</code>.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características:</b>
        <ul>
          <li>Estructura tipo tabla (filas × columnas)</li>
          <li>Acceso directo: O(1) mediante <code>[f][c]</code></li>
          <li>Almacenamiento contiguo por filas (row-major)</li>
          <li>Útil para representar matrices matemáticas, imágenes, tableros</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Operaciones</h2>

        <h3>Asignar / Obtener</h3>
        <pre>
    matriz.set(2, 3, 42);   // matriz[2][3] = 42
    int v = matriz.get(2, 3); // v = 42</pre>

        <h3>Llenar / Aleatorio</h3>
        <p><code>fill(valor)</code> asigna el mismo valor a todas las celdas. <code>fillRandom()</code> llena con valores aleatorios 0-99.</p>

        <h3>Obtener fila o columna</h3>
        <p><code>getRow(f)</code> devuelve un arreglo 1D con la fila <b>f</b>. <code>getCol(c)</code> devuelve la columna <b>c</b>.</p>

        <h3>Transposición</h3>
        <p>Intercambia filas por columnas. Una matriz <code>M×N</code> se convierte en <code>N×M</code>. El elemento <code>[f][c]</code> pasa a <code>[c][f]</code>.</p>

        <h3>Búsqueda lineal</h3>
        <p>Recorre toda la matriz hasta encontrar el valor. Retorna la posición <code>[fila, columna]</code> o <code>null</code>. Complejidad: O(n×m).</p>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Complejidad</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Acceso [f][c]</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Recorrer todo</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n×m)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Transponer</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n×m)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Búsqueda</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n×m)</td></tr>
        </table>
        </body></html>
        """;

    public Array2DTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

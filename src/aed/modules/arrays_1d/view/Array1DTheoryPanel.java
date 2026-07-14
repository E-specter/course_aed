package aed.modules.arrays_1d.view;

import aed.core.view.TheoryPanel;

public class Array1DTheoryPanel extends TheoryPanel {

    private static final String HTML_CONTENT = """
        <html>
        <head>
        <style>
            body { font-family: 'Segoe UI', Arial, sans-serif; padding: 30px; color: #212121; }
            h1 { color: #1A73E8; font-size: 24px; border-bottom: 2px solid #1A73E8; padding-bottom: 8px; }
            h2 { color: #0D47A1; font-size: 18px; margin-top: 24px; }
            p { font-size: 14px; line-height: 1.6; text-align: justify; }
            code { background: #F5F5F5; padding: 2px 6px; border-radius: 3px; font-family: Consolas; font-size: 13px; }
            pre { background: #F5F5F5; padding: 12px; border-radius: 6px; font-family: Consolas; font-size: 13px; border-left: 3px solid #1A73E8; }
            .note { background: #E3F2FD; padding: 12px; border-radius: 6px; border-left: 3px solid #1A73E8; margin: 12px 0; }
            ul { font-size: 14px; line-height: 1.8; }
            table { border-collapse: collapse; margin: 12px 0; }
            th, td { border: 1px solid #E0E0E0; padding: 8px 14px; text-align: center; }
            th { background: #1A73E8; color: white; }
        </style>
        </head>
        <body>
            <h1>Arreglo Unidimensional (Array 1D)</h1>

            <h2>¿Qué es un arreglo unidimensional?</h2>
            <p>
            Un <b>arreglo unidimensional</b> (también llamado <i>vector</i> o <i>array</i>)
            es una estructura de datos que almacena una colección de elementos del mismo tipo
            en posiciones de memoria contiguas. Cada elemento se accede mediante un <b>índice</b>
            numérico que comienza en <code>0</code>.
            </p>

            <div class="note">
            <b>Características principales:</b>
            <ul>
                <li>Almacena elementos de un mismo tipo de dato</li>
                <li>Las posiciones son contiguas en memoria</li>
                <li>Acceso directo a cualquier elemento mediante su índice → O(1)</li>
                <li>Tamaño fijo (en su forma clásica) o dinámico (con redimensión)</li>
            </ul>
            </div>

            <h2>Operaciones fundamentales</h2>

            <h3>1. Agregar / Insertar</h3>
            <p>
            Se añade un elemento al final del arreglo o en una posición específica.
            Si se inserta en una posición intermedia, los elementos siguientes se
            desplazan una posición a la derecha.
            </p>
            <pre>
    // Agregar al final
    arreglo.add(42);

    // Insertar en índice específico
    arreglo.insert(2, 15);  // Inserta 15 en la posición 2
            </pre>

            <h3>2. Eliminar</h3>
            <p>
            Se remueve el elemento en un índice dado. Los elementos posteriores
            se desplazan una posición a la izquierda para llenar el hueco.
            </p>
            <pre>
    arreglo.delete(3);  // Elimina el elemento en índice 3
            </pre>

            <h3>3. Modificar</h3>
            <p>
            Se actualiza el valor de un elemento en una posición específica.
            </p>
            <pre>
    arreglo.set(2, 99);  // Cambia el valor en índice 2 a 99
            </pre>

            <h3>4. Búsqueda</h3>
            <p>
            <b>Búsqueda lineal (secuencial):</b> Recorre el arreglo elemento por elemento
            hasta encontrar el valor buscado. Complejidad: O(n).
            </p>
            <pre>
    int pos = arreglo.linearSearch(42);
            </pre>
            <p>
            <b>Búsqueda binaria:</b> Requiere que el arreglo esté ordenado.
            Divide el arreglo en mitades sucesivamente hasta encontrar el elemento.
            Complejidad: O(log n).
            </p>
            <pre>
    int pos = arreglo.binarySearch(42);
            </pre>

            <h3>5. Ordenamiento</h3>
            <p><b>Burbuja (Bubble Sort):</b> O(n²)</p>
            <p>Compara pares adyacentes y los intercambia si están en el orden incorrecto.
            Los elementos más grandes "burbujean" hacia el final.</p>
            <pre>
    for (int i = 0; i < n-1; i++)
        for (int j = 0; j < n-1-i; j++)
            if (arr[j] > arr[j+1]) intercambiar(j, j+1);
            </pre>

            <p><b>Selección (Selection Sort):</b> O(n²)</p>
            <p>Encuentra el elemento más pequeño y lo coloca al inicio.
            Repite para el resto del arreglo.</p>
            <pre>
    for (int i = 0; i < n-1; i++) {
        int min = i;
        for (int j = i+1; j < n; j++)
            if (arr[j] < arr[min]) min = j;
        intercambiar(i, min);
    }
            </pre>

            <p><b>Inserción (Insertion Sort):</b> O(n²)</p>
            <p>Toma cada elemento y lo inserta en la posición correcta
            dentro de la parte ya ordenada del arreglo.</p>
            <pre>
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i-1;
        while (j >= 0 && arr[j] > key) {
            arr[j+1] = arr[j];
            j--;
        }
        arr[j+1] = key;
    }
            </pre>

            <h2>Complejidad algorítmica</h2>
            <table>
                <tr><th>Operación</th><th>Peor caso</th><th>Mejor caso</th></tr>
                <tr><td>Acceso (get)</td><td>O(1)</td><td>O(1)</td></tr>
                <tr><td>Inserción</td><td>O(n)</td><td>O(1)</td></tr>
                <tr><td>Eliminación</td><td>O(n)</td><td>O(1)</td></tr>
                <tr><td>Búsqueda lineal</td><td>O(n)</td><td>O(1)</td></tr>
                <tr><td>Búsqueda binaria</td><td>O(log n)</td><td>O(1)</td></tr>
                <tr><td>Burbuja</td><td>O(n²)</td><td>O(n)</td></tr>
                <tr><td>Selección</td><td>O(n²)</td><td>O(n²)</td></tr>
                <tr><td>Inserción</td><td>O(n²)</td><td>O(n)</td></tr>
            </table>
        </body>
        </html>
        """;

    public Array1DTheoryPanel() {
        super("");
        setHtmlContent(HTML_CONTENT);
    }
}

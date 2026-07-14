package aed.modules.singly_list.view;

import aed.core.view.TheoryPanel;

public class SinglyListTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Lista Simplemente Enlazada</h1>

        <h2 style='color:#0D47A1;'>¿Qué es una lista simplemente enlazada?</h2>
        <p>Una <b>lista simplemente enlazada</b> es una estructura de datos lineal donde cada elemento (<b>nodo</b>) contiene un valor y un puntero al siguiente nodo. El último nodo apunta a <code>null</code>.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características:</b>
        <ul>
          <li>Inserción y eliminación en cabeza: O(1)</li>
          <li>Acceso por índice: O(n) (recorrido secuencial)</li>
          <li>No requiere memoria contigua</li>
          <li>Crecimiento dinámico sin redimensión</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Estructura de un nodo</h2>
        <pre>
    class Node&lt;T&gt; {
        T data;        // valor del nodo
        Node&lt;T&gt; next;  // puntero al siguiente nodo
    }</pre>

        <h2 style='color:#0D47A1;'>Operaciones</h2>

        <h3>Add First / Add Last</h3>
        <p><code>addFirst</code> inserta al inicio (O(1)). <code>addLast</code> recorre hasta el final (O(n)).</p>

        <h3>Insertar en posición</h3>
        <p>Recorre hasta la posición <code>index-1</code>, enlaza el nuevo nodo. O(n).</p>

        <h3>Eliminar</h3>
        <p>Re-enlaza el nodo anterior al siguiente del eliminado. O(n).</p>

        <h3>Búsqueda lineal</h3>
        <p>Recorre la lista comparando valores. O(n).</p>

        <h3>Ordenamiento (Burbuja)</h3>
        <p>Intercambia los datos de los nodos sin reordenar los punteros. O(n²).</p>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Complejidad</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Add First</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Add Last / Insert</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Eliminar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Acceder / Buscar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Ordenar burbuja</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n²)</td></tr>
        </table>
        </body></html>
        """;

    public SinglyListTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

package aed.modules.doubly_list.view;

import aed.core.view.TheoryPanel;

public class DoublyListTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Lista Doblemente Enlazada</h1>

        <h2 style='color:#0D47A1;'>¿Qué es una lista doblemente enlazada?</h2>
        <p>Una <b>lista doblemente enlazada</b> es similar a la simplemente enlazada, pero cada nodo tiene <b>dos punteros</b>: uno al siguiente nodo y otro al anterior. Esto permite recorrer la lista en ambos sentidos.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características:</b>
        <ul>
          <li>Inserción/eliminación en ambos extremos: O(1)</li>
          <li>Recorrido bidireccional (siguiente y anterior)</li>
          <li>Más memoria por nodo (dos punteros vs uno)</li>
          <li>Operaciones de inserción/eliminación más simples al tener referencia al previo</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Estructura de un nodo</h2>
        <pre>
    class Node&lt;T&gt; {
        Node&lt;T&gt; prev;  // puntero al nodo anterior
        T data;         // valor del nodo
        Node&lt;T&gt; next;  // puntero al siguiente nodo
    }</pre>

        <h2 style='color:#0D47A1;'>Operaciones</h2>

        <h3>Add First / Add Last</h3>
        <p>Ambos extremos tienen acceso directo mediante <code>head</code> y <code>tail</code>. Ambas operaciones: O(1).</p>

        <h3>Insertar / Eliminar</h3>
        <p>Al tener referencia al nodo anterior, no se necesita recorrer desde la cabeza para re-enlazar. La inserción/eliminación en una posición arbitraria sigue siendo O(n) por el acceso, pero el re-enlazado es O(1).</p>

        <h3>Recorrido inverso</h3>
        <p>Desde <code>tail</code> se puede recorrer hacia atrás usando el puntero <code>prev</code>.</p>

        <h2 style='color:#0D47A1;'>Comparativa con lista simple</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Simple</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Doble</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Add First</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Add Last</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Eliminar último</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Recorrido inverso</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>No</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Sí</td></tr>
        </table>
        </body></html>
        """;

    public DoublyListTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

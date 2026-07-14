package aed.modules.circular_list.view;

import aed.core.view.TheoryPanel;

public class CircularListTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Lista Circular</h1>

        <h2 style='color:#0D47A1;'>¿Qué es una lista circular?</h2>
        <p>Una <b>lista circular</b> es una variante de la lista simplemente enlazada donde el <b>último nodo apunta al primero</b>, formando un ciclo. No existe un nodo que apunte a <code>null</code>.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características principales:</b>
        <ul>
          <li>El último nodo apunta al primero (cabeza)</li>
          <li>No hay valor <code>null</code> de referencia</li>
          <li>Se puede recorrer infinitamente en ciclo</li>
          <li>Útil para aplicaciones como turnos (round-robin)</li>
          <li>Requiere un nodo cabeza como referencia fija</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Estructura</h2>
        <pre>
    class Node&lt;T&gt; {
        T data;
        Node&lt;T&gt; next;  // apunta al siguiente (o al primero si es el último)
    }</pre>

        <p>Visualmente, los nodos se disponen en un <b>círculo</b>, con flechas que conectan cada nodo al siguiente, y el último al primero:</p>
        <pre style='text-align:center;'>
            ┌─────────────┐
            ↓             │
          [5] → [8] → [3] ┘</pre>

        <h2 style='color:#0D47A1;'>Operaciones</h2>

        <h3>Agregar / Insertar</h3>
        <p><code>add(valor)</code> agrega al final manteniendo la circularidad. <code>insert(index, valor)</code> inserta en una posición, reajustando los punteros.</p>

        <h3>Eliminar</h3>
        <p>Requiere re-enlazar el nodo anterior al siguiente del eliminado, manteniendo la referencia circular.</p>

        <h3>Recorrido</h3>
        <p>A diferencia de las listas lineales, no se puede usar <code>null</code> como condición de término. Se debe llevar un contador o comparar con el nodo cabeza.</p>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Complejidad</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Agregar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Insertar / Eliminar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Acceder / Buscar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
        </table>
        </body></html>
        """;

    public CircularListTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

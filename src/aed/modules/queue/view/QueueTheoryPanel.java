package aed.modules.queue.view;

import aed.core.view.TheoryPanel;

public class QueueTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Cola (Queue)</h1>

        <h2 style='color:#0D47A1;'>¿Qué es una cola (Queue)?</h2>
        <p>Una <b>Queue</b> (cola) es una estructura de datos lineal que sigue el principio <b>FIFO</b> (<i>First In, First Out</i>): el primer elemento agregado es el primero en ser removido.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características:</b>
        <ul>
          <li>Acceso restringido: inserciones por el final (rear), extracciones por el frente (front)</li>
          <li>FIFO: primero en entrar, primero en salir</li>
          <li>Operaciones fundamentales: enqueue, dequeue, peek</li>
          <li>Análogo a una fila de personas</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Operaciones</h2>

        <h3>Enqueue</h3>
        <p>Inserta un elemento al <b>final</b> (rear) de la cola. O(1).</p>
        <pre>
    cola.enqueue(42);  // 42 se coloca al final</pre>

        <h3>Dequeue</h3>
        <p>Remueve y retorna el elemento del <b>frente</b> (front). O(1).</p>
        <pre>
    int valor = cola.dequeue();  // retorna el frente y lo elimina</pre>

        <h3>Peek</h3>
        <p>Retorna el elemento del frente sin removerlo. O(1).</p>
        <pre>
    int valor = cola.peek();  // solo consulta el frente</pre>

        <h2 style='color:#0D47A1;'>Implementación con lista enlazada</h2>
        <p>La Queue se implementa con una lista simplemente enlazada con dos referencias: <code>head</code> (frente) y <code>tail</code> (final). <code>enqueue</code> agrega por <code>tail</code>, <code>dequeue</code> remueve por <code>head</code>.</p>

        <h2 style='color:#0D47A1;'>Aplicaciones</h2>
        <ul>
          <li>Gestión de procesos (scheduling)</li>
          <li>Buffers de datos (streaming)</li>
          <li>BFS (Breadth-First Search) en grafos</li>
          <li>Colas de impresión</li>
        </ul>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Complejidad</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Enqueue</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Dequeue</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Peek</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Buscar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
        </table>
        </body></html>
        """;

    public QueueTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

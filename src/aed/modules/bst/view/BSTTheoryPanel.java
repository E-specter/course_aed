package aed.modules.bst.view;

import aed.core.view.TheoryPanel;

public class BSTTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Árbol Binario de Búsqueda (BST)</h1>

        <h2 style='color:#0D47A1;'>¿Qué es un BST?</h2>
        <p>Un <b>Árbol Binario de Búsqueda (BST)</b> es un árbol binario que cumple la siguiente propiedad: para cada nodo, todos los valores en el <b>subárbol izquierdo</b> son <b>menores</b> que el nodo, y todos los valores en el <b>subárbol derecho</b> son <b>mayores</b>.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Propiedad fundamental:</b>
        <pre style='margin:4px 0;'>
        izquierdo &lt; raíz &lt; derecho
        para <b>TODO</b> nodo del árbol</pre>
        </div>

        <h2 style='color:#0D47A1;'>Operaciones</h2>

        <h3>Inserción</h3>
        <p>Compara el nuevo valor con la raíz. Si es menor, va al subárbol izquierdo; si es mayor, al derecho. Repite hasta encontrar un espacio vacío. O(h) donde h es la altura.</p>
        <pre>
    si valor &lt; nodo.actual → ir a la izquierda
    si valor &gt; nodo.actual → ir a la derecha
    si espacio vacío → insertar aquí</pre>

        <h3>Búsqueda</h3>
        <p>Sigue la misma lógica que inserción. En un BST balanceado, la búsqueda es O(log n). En el peor caso (árbol degenerado), O(n).</p>

        <h3>Eliminación (3 casos)</h3>
        <p><b>Caso 1:</b> Nodo hoja → se elimina directamente.</p>
        <p><b>Caso 2:</b> Un solo hijo → se reemplaza por ese hijo.</p>
        <p><b>Caso 3:</b> Dos hijos → se reemplaza por el <b>sucesor in-order</b> (el menor del subárbol derecho) y se elimina ese sucesor.</p>

        <h3>Recorrido in-order</h3>
        <p>En un BST, el recorrido <b>in-order</b> visita los elementos en <b>orden ascendente</b>.</p>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Promedio</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Peor caso</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Insertar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(log n)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Buscar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(log n)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Eliminar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(log n)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Recorrido</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
        </table>
        </body></html>
        """;

    public BSTTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

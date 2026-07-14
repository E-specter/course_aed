package aed.modules.binary_tree.view;

import aed.core.view.TheoryPanel;

public class BinaryTreeTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Árbol Binario</h1>

        <h2 style='color:#0D47A1;'>¿Qué es un árbol binario?</h2>
        <p>Un <b>árbol binario</b> es una estructura de datos jerárquica donde cada nodo tiene <b>como máximo dos hijos</b>: izquierdo y derecho.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características:</b>
        <ul>
          <li>Cada nodo tiene un valor y referencias a hijo izquierdo y derecho</li>
          <li>El primer nodo se llama <b>raíz</b> (root)</li>
          <li>Los nodos sin hijos se llaman <b>hojas</b> (leaves)</li>
          <li>No hay límite en la profundidad del árbol</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Estructura de un nodo</h2>
        <pre>
    class TreeNode&lt;T&gt; {
        T data;            // valor del nodo
        TreeNode left;     // hijo izquierdo
        TreeNode right;    // hijo derecho
    }</pre>

        <h2 style='color:#0D47A1;'>Recorridos</h2>
        <p><b>In-Order:</b> izquierdo → raíz → derecho</p>
        <pre>
    inOrder(nodo):
        inOrder(nodo.izquierdo)
        visitar(nodo)
        inOrder(nodo.derecho)</pre>

        <p><b>Pre-Order:</b> raíz → izquierdo → derecho</p>
        <pre>
    preOrder(nodo):
        visitar(nodo)
        preOrder(nodo.izquierdo)
        preOrder(nodo.derecho)</pre>

        <p><b>Post-Order:</b> izquierdo → derecho → raíz</p>
        <pre>
    postOrder(nodo):
        postOrder(nodo.izquierdo)
        postOrder(nodo.derecho)
        visitar(nodo)</pre>

        <h2 style='color:#0D47A1;'>Operaciones</h2>
        <p><b>Insertar:</b> se inserta por nivel (level-order), ocupando la primera posición disponible.</p>
        <p><b>Eliminar:</b> se reemplaza el nodo a eliminar con el nodo más profundo y se elimina ese.</p>
        <p><b>Buscar:</b> recorrido level-order hasta encontrar el valor.</p>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Peor caso</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Insertar (level-order)</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Buscar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Eliminar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Recorridos</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
        </table>
        </body></html>
        """;

    public BinaryTreeTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

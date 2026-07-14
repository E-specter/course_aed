package aed.modules.stack.view;

import aed.core.view.TheoryPanel;

public class StackTheoryPanel extends TheoryPanel {
    private static final String HTML = """
        <html><body style='font-family:Segoe UI;padding:30px;color:#212121;'>
        <h1 style='color:#1A73E8;border-bottom:2px solid #1A73E8;padding-bottom:8px;'>Pila (Stack)</h1>

        <h2 style='color:#0D47A1;'>¿Qué es una pila (Stack)?</h2>
        <p>Un <b>Stack</b> (pila) es una estructura de datos lineal que sigue el principio <b>LIFO</b> (<i>Last In, First Out</i>): el último elemento agregado es el primero en ser removido.</p>

        <div style='background:#E3F2FD;padding:12px;border-radius:6px;border-left:3px solid #1A73E8;margin:12px 0;'>
        <b>Características:</b>
        <ul>
          <li>Acceso restringido: solo se opera en el tope (top)</li>
          <li>LIFO: último en entrar, primero en salir</li>
          <li>Operaciones fundamentales: push, pop, peek</li>
          <li>Análogo a una pila de platos</li>
        </ul>
        </div>

        <h2 style='color:#0D47A1;'>Operaciones</h2>

        <h3>Push</h3>
        <p>Inserta un elemento en el <b>tope</b> de la pila. O(1).</p>
        <pre>
    pila.push(42);  // 42 queda en el tope</pre>

        <h3>Pop</h3>
        <p>Remueve y retorna el elemento del <b>tope</b>. O(1).</p>
        <pre>
    int valor = pila.pop();  // retorna el tope y lo elimina</pre>

        <h3>Peek</h3>
        <p>Retorna el elemento del tope sin removerlo. O(1).</p>
        <pre>
    int valor = pila.peek();  // solo consulta</pre>

        <h2 style='color:#0D47A1;'>Implementación con lista enlazada</h2>
        <p>El Stack se implementa sobre una lista simplemente enlazada donde el <b>tope</b> es la cabeza de la lista. Cada nodo contiene el valor y un puntero al siguiente nodo hacia abajo.</p>

        <h2 style='color:#0D47A1;'>Aplicaciones</h2>
        <ul>
          <li>Evaluación de expresiones (notación postfija)</li>
          <li>Deshacer/Rehacer (Ctrl+Z)</li>
          <li>Navegación en navegadores (atrás/adelante)</li>
          <li>Llamadas a funciones (call stack)</li>
        </ul>

        <h2 style='color:#0D47A1;'>Complejidad</h2>
        <table style='border-collapse:collapse;margin:12px 0;'>
          <tr style='background:#1A73E8;color:white;'><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Operación</th><th style='padding:8px 14px;border:1px solid #E0E0E0;'>Complejidad</th></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Push</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Pop</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Peek</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(1)</td></tr>
          <tr><td style='padding:8px 14px;border:1px solid #E0E0E0;'>Buscar</td><td style='padding:8px 14px;border:1px solid #E0E0E0;'>O(n)</td></tr>
        </table>
        </body></html>
        """;

    public StackTheoryPanel() {
        super("");
        setHtmlContent(HTML);
    }
}

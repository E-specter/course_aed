package aed.modules.queue.controller;

import aed.core.view.Theme;
import aed.modules.queue.model.LinkedQueue;
import aed.modules.queue.view.QueuePracticePanel;

public class QueueController {
    private LinkedQueue<Integer> model;

    public QueueController() {
        model = new LinkedQueue<>();
    }

    public void ejecutar(String accion, int valor, QueuePracticePanel vw) {
        switch (accion) {
            case "enqueue" -> enqueue(valor, vw);
            case "dequeue" -> dequeue(vw);
            case "peek" -> peek(vw);
            case "buscar" -> buscar(valor, vw);
            case "limpiar" -> limpiar(vw);
        }
    }

    private void enqueue(int v, QueuePracticePanel vw) {
        model.enqueue(v);
        actualizar(vw);
        int last = model.size() - 1;
        vw.getRenderer().highlightCell(last, Theme.SUCCESS, "Enqueue: " + v + " (rear)");
        vw.limpiarCampos();
    }

    private void dequeue(QueuePracticePanel vw) {
        try {
            int v = model.dequeue();
            actualizar(vw);
            vw.getRenderer().setStatusMessage("Dequeue: " + v + " (front)", Theme.ERROR);
        } catch (RuntimeException e) {
            vw.getRenderer().setStatusMessage("Queue vacío", Theme.ERROR);
        }
    }

    private void peek(QueuePracticePanel vw) {
        try {
            int v = model.peek();
            vw.getRenderer().highlightCell(0, Theme.WARNING, "Front: " + v);
        } catch (RuntimeException e) {
            vw.getRenderer().setStatusMessage("Queue vacío", Theme.ERROR);
        }
    }

    private void buscar(int v, QueuePracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("Queue vacío", Theme.ERROR);
            return;
        }
        int pos = model.search(v);
        if (pos >= 0) {
            vw.getRenderer().highlightCell(pos, Theme.SUCCESS,
                    "Valor " + v + " en posición [" + pos + "]");
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void limpiar(QueuePracticePanel vw) {
        model.clear();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Queue limpiado", Theme.TEXT_SECONDARY);
    }

    private void actualizar(QueuePracticePanel vw) { vw.actualizar(); }
    public Object[] getDatos() { return model.toArray(); }
    public int getSize() { return model.size(); }
}

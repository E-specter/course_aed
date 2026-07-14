package aed.modules.stack.controller;

import aed.core.view.Theme;
import aed.modules.stack.model.LinkedStack;
import aed.modules.stack.view.StackPracticePanel;

public class StackController {
    private LinkedStack<Integer> model;

    public StackController() {
        model = new LinkedStack<>();
    }

    public void ejecutar(String accion, int valor, StackPracticePanel vw) {
        switch (accion) {
            case "push" -> push(valor, vw);
            case "pop" -> pop(vw);
            case "peek" -> peek(vw);
            case "buscar" -> buscar(valor, vw);
            case "limpiar" -> limpiar(vw);
        }
    }

    private void push(int v, StackPracticePanel vw) {
        model.push(v);
        actualizar(vw);
        vw.getRenderer().highlightCell(0, Theme.SUCCESS, "Push: " + v + " (nuevo tope)");
        vw.limpiarCampos();
    }

    private void pop(StackPracticePanel vw) {
        try {
            int v = model.pop();
            actualizar(vw);
            vw.getRenderer().setStatusMessage("Pop: " + v, Theme.ERROR);
        } catch (RuntimeException e) {
            vw.getRenderer().setStatusMessage("Stack vacío", Theme.ERROR);
        }
    }

    private void peek(StackPracticePanel vw) {
        try {
            int v = model.peek();
            vw.getRenderer().highlightCell(0, Theme.WARNING, "Tope: " + v);
        } catch (RuntimeException e) {
            vw.getRenderer().setStatusMessage("Stack vacío", Theme.ERROR);
        }
    }

    private void buscar(int v, StackPracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("Stack vacío", Theme.ERROR);
            return;
        }
        int pos = model.search(v);
        if (pos >= 0) {
            vw.getRenderer().highlightCell(pos, Theme.SUCCESS,
                    "Valor " + v + " encontrado en posición " + pos + " desde el tope");
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void limpiar(StackPracticePanel vw) {
        model.clear();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Stack limpiado", Theme.TEXT_SECONDARY);
    }

    private void actualizar(StackPracticePanel vw) { vw.actualizar(); }
    public Object[] getDatos() { return model.toArray(); }
    public int getSize() { return model.size(); }
}

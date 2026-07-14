package aed.modules.doubly_list.controller;

import aed.core.view.Theme;
import aed.modules.doubly_list.model.DoublyLinkedList;
import aed.modules.doubly_list.view.DoublyListPracticePanel;

public class DoublyListController {
    private DoublyLinkedList<Integer> model;

    public DoublyListController() {
        model = new DoublyLinkedList<>();
    }

    public void ejecutar(String accion, int valor, int indice, DoublyListPracticePanel vw) {
        switch (accion) {
            case "first" -> addFirst(valor, vw);
            case "last" -> addLast(valor, vw);
            case "insertar" -> insertar(valor, indice, vw);
            case "eliminar" -> eliminar(indice, vw);
            case "obtener" -> obtener(indice, vw);
            case "modificar" -> modificar(valor, indice, vw);
            case "buscar" -> buscar(valor, vw);
            case "ordenar" -> ordenar(vw);
            case "limpiar" -> limpiar(vw);
        }
    }

    private void addFirst(int v, DoublyListPracticePanel vw) {
        model.addFirst(v);
        actualizar(vw);
        vw.getRenderer().highlightCell(0, Theme.SUCCESS, "Add First: " + v);
        vw.limpiarCampos();
    }

    private void addLast(int v, DoublyListPracticePanel vw) {
        model.addLast(v);
        actualizar(vw);
        vw.getRenderer().highlightCell(model.size() - 1, Theme.PRIMARY, "Add Last: " + v);
        vw.limpiarCampos();
    }

    private void insertar(int v, int i, DoublyListPracticePanel vw) {
        try {
            model.insert(i, v);
            actualizar(vw);
            vw.getRenderer().highlightCell(i, Theme.PRIMARY, "Insertado " + v + " en [" + i + "]");
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void eliminar(int i, DoublyListPracticePanel vw) {
        try {
            int v = model.get(i);
            model.remove(i);
            actualizar(vw);
            vw.getRenderer().setStatusMessage("Eliminado " + v + " en [" + i + "]", Theme.ERROR);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void obtener(int i, DoublyListPracticePanel vw) {
        try {
            int v = model.get(i);
            vw.getRenderer().highlightCell(i, Theme.INFO, "Valor en [" + i + "] = " + v);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void modificar(int v, int i, DoublyListPracticePanel vw) {
        try {
            int ant = model.get(i);
            model.set(i, v);
            actualizar(vw);
            vw.getRenderer().highlightCell(i, Theme.WARNING, "[" + i + "] " + ant + " → " + v);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void buscar(int v, DoublyListPracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("Lista vacía", Theme.ERROR);
            return;
        }
        int pos = model.search(v);
        if (pos >= 0) {
            vw.getRenderer().highlightCell(pos, Theme.SUCCESS, "Valor " + v + " en [" + pos + "]");
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void ordenar(DoublyListPracticePanel vw) {
        if (model.size() < 2) {
            vw.getRenderer().setStatusMessage("Se necesitan al menos 2 elementos", Theme.ERROR);
            return;
        }
        model.sort();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Lista ordenada (burbuja)", Theme.SUCCESS);
    }

    private void limpiar(DoublyListPracticePanel vw) {
        model.clear();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Lista limpiada", Theme.TEXT_SECONDARY);
    }

    private void actualizar(DoublyListPracticePanel vw) { vw.actualizar(); }
    public Object[] getDatos() { return model.toArray(); }
    public int getSize() { return model.size(); }
}

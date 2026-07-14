package aed.modules.arrays_1d.controller;

import aed.core.view.Theme;
import aed.modules.arrays_1d.model.Array1D;
import aed.modules.arrays_1d.view.Array1DPracticePanel;

public class Array1DController {
    private Array1D model;

    public Array1DController() {
        this.model = new Array1D();
    }

    public void ejecutar(String accion, int valor, int indice, Array1DPracticePanel view) {
        switch (accion) {
            case "agregar" -> agregar(valor, view);
            case "insertar" -> insertar(valor, indice, view);
            case "modificar" -> modificar(valor, indice, view);
            case "eliminar" -> eliminar(indice, view);
            case "buscar" -> buscar(valor, view);
            case "buscarBin" -> buscarBinaria(valor, view);
            case "burbuja" -> burbuja(view);
            case "seleccion" -> seleccion(view);
            case "insercion" -> insercion(view);
            case "limpiar" -> limpiar(view);
        }
    }

    private void agregar(int valor, Array1DPracticePanel view) {
        model.add(valor);
        actualizarVista(view);
        view.getRenderer().highlightCell(model.size() - 1, Theme.SUCCESS,
                "Agregado: " + valor);
        view.limpiarCampos();
    }

    private void insertar(int valor, int indice, Array1DPracticePanel view) {
        try {
            model.insert(indice, valor);
            actualizarVista(view);
            view.getRenderer().highlightCell(indice, Theme.PRIMARY,
                    "Insertado " + valor + " en [" + indice + "]");
            view.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            view.getRenderer().setStatusMessage(
                    "Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void modificar(int valor, int indice, Array1DPracticePanel view) {
        try {
            int anterior = model.get(indice);
            model.set(indice, valor);
            actualizarVista(view);
            view.getRenderer().highlightCell(indice, Theme.WARNING,
                    "Modificado: [" + indice + "] " + anterior + " → " + valor);
            view.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            view.getRenderer().setStatusMessage(
                    "Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void eliminar(int indice, Array1DPracticePanel view) {
        try {
            int eliminado = model.get(indice);
            model.delete(indice);
            actualizarVista(view);
            view.getRenderer().setStatusMessage(
                    "Eliminado: " + eliminado + " en [" + indice + "]", Theme.ERROR);
            view.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            view.getRenderer().setStatusMessage(
                    "Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void buscar(int valor, Array1DPracticePanel view) {
        if (model.isEmpty()) {
            view.getRenderer().setStatusMessage("Array vacío", Theme.ERROR);
            return;
        }
        int pos = model.linearSearch(valor);
        if (pos >= 0) {
            view.getRenderer().highlightCell(pos, Theme.SUCCESS,
                    "Valor " + valor + " encontrado en [" + pos + "]");
        } else {
            view.getRenderer().setStatusMessage(
                    "Valor " + valor + " no encontrado", Theme.ERROR);
        }
        view.limpiarCampos();
    }

    private void buscarBinaria(int valor, Array1DPracticePanel view) {
        if (model.isEmpty()) {
            view.getRenderer().setStatusMessage("Array vacío", Theme.ERROR);
            return;
        }
        int pos = model.binarySearch(valor);
        if (pos >= 0) {
            view.getRenderer().highlightCell(pos, Theme.SUCCESS,
                    "B. Binaria: " + valor + " encontrado en [" + pos + "]");
        } else {
            view.getRenderer().setStatusMessage(
                    "B. Binaria: " + valor + " no encontrado (¿está ordenado?)", Theme.ERROR);
        }
        view.limpiarCampos();
    }

    private void burbuja(Array1DPracticePanel view) {
        if (model.size() < 2) {
            view.getRenderer().setStatusMessage("Se necesitan al menos 2 elementos", Theme.ERROR);
            return;
        }
        model.bubbleSort();
        actualizarVista(view);
        view.getRenderer().setStatusMessage("Ordenado por burbuja", Theme.SUCCESS);
    }

    private void seleccion(Array1DPracticePanel view) {
        if (model.size() < 2) {
            view.getRenderer().setStatusMessage("Se necesitan al menos 2 elementos", Theme.ERROR);
            return;
        }
        model.selectionSort();
        actualizarVista(view);
        view.getRenderer().setStatusMessage("Ordenado por selección", Theme.SUCCESS);
    }

    private void insercion(Array1DPracticePanel view) {
        if (model.size() < 2) {
            view.getRenderer().setStatusMessage("Se necesitan al menos 2 elementos", Theme.ERROR);
            return;
        }
        model.insertionSort();
        actualizarVista(view);
        view.getRenderer().setStatusMessage("Ordenado por inserción", Theme.SUCCESS);
    }

    private void limpiar(Array1DPracticePanel view) {
        model.clear();
        actualizarVista(view);
        view.getRenderer().setStatusMessage("Array limpiado", Theme.TEXT_SECONDARY);
        view.limpiarCampos();
    }

    private void actualizarVista(Array1DPracticePanel view) {
        view.actualizar(model.toArray(), model.size());
    }
}

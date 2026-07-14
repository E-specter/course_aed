package aed.modules.arrays_3d.controller;

import aed.core.view.Theme;
import aed.modules.arrays_3d.model.Array3D;
import aed.modules.arrays_3d.view.Array3DPracticePanel;

public class Array3DController {
    private Array3D model;
    private int currentDepth;

    public Array3DController() {
        model = new Array3D(3, 4, 4);
        currentDepth = 0;
    }

    public void ejecutar(String accion, int valor, int capa, int fila, int col, Array3DPracticePanel view) {
        switch (accion) {
            case "asignar" -> asignar(valor, capa, fila, col, view);
            case "obtener" -> obtener(capa, fila, col, view);
            case "llenar" -> llenar(valor, view);
            case "aleatorio" -> aleatorio(view);
            case "buscar" -> buscar(valor, view);
            case "sumar" -> sumar(view);
            case "limpiar" -> limpiar(view);
        }
    }

    private void asignar(int v, int d, int r, int c, Array3DPracticePanel vw) {
        try {
            model.set(d, r, c, v);
            actualizar(vw);
            vw.getRenderer().highlightCell(r, c, Theme.SUCCESS,
                    "Asignado: [" + d + "][" + r + "][" + c + "] = " + v);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void obtener(int d, int r, int c, Array3DPracticePanel vw) {
        try {
            currentDepth = d;
            int v = model.get(d, r, c);
            actualizar(vw);
            vw.getRenderer().highlightCell(r, c, Theme.PRIMARY,
                    "Valor en [" + d + "][" + r + "][" + c + "] = " + v);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void llenar(int v, Array3DPracticePanel vw) {
        model.fill(v);
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Todos los valores = " + v, Theme.WARNING);
    }

    private void aleatorio(Array3DPracticePanel vw) {
        model.fillRandom();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Valores aleatorios generados", Theme.SUCCESS);
    }

    private void buscar(int v, Array3DPracticePanel vw) {
        if (model.depth() == 0 || model.rows() == 0 || model.cols() == 0) {
            vw.getRenderer().setStatusMessage("Cubo vacío", Theme.ERROR);
            return;
        }
        int[] pos = model.search(v);
        if (pos != null) {
            currentDepth = pos[0];
            actualizar(vw);
            vw.getRenderer().highlightCell(pos[1], pos[2], Theme.SUCCESS,
                    "Valor " + v + " en [" + pos[0] + "][" + pos[1] + "][" + pos[2] + "]");
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void sumar(Array3DPracticePanel vw) {
        int total = model.sum();
        vw.getRenderer().setStatusMessage("Suma total: " + total, Theme.SUCCESS);
    }

    private void limpiar(Array3DPracticePanel vw) {
        model.clear();
        currentDepth = 0;
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Cubo limpiado", Theme.TEXT_SECONDARY);
    }

    public void capaAnterior(Array3DPracticePanel vw) {
        if (currentDepth > 0) {
            currentDepth--;
            actualizar(vw);
        }
    }

    public void capaSiguiente(Array3DPracticePanel vw) {
        if (currentDepth < model.depth() - 1) {
            currentDepth++;
            actualizar(vw);
        }
    }

    public int getCurrentDepth() { return currentDepth; }
    public int getDepth() { return model.depth(); }
    public int getRows() { return model.rows(); }
    public int getCols() { return model.cols(); }
    public int[][] getSliceData() { return model.getSlice(currentDepth); }

    private void actualizar(Array3DPracticePanel vw) {
        vw.actualizarSlice();
    }
}

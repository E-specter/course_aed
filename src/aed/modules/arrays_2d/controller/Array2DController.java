package aed.modules.arrays_2d.controller;

import aed.core.view.Theme;
import aed.modules.arrays_2d.model.Array2D;
import aed.modules.arrays_2d.view.Array2DPracticePanel;

public class Array2DController {
    private Array2D model;

    public Array2DController() {
        model = new Array2D(4, 4);
    }

    public void ejecutar(String accion, int valor, int fila, int col, Array2DPracticePanel view) {
        switch (accion) {
            case "asignar" -> asignar(valor, fila, col, view);
            case "obtener" -> obtener(fila, col, view);
            case "llenar" -> llenar(valor, view);
            case "aleatorio" -> aleatorio(view);
            case "fila" -> fila(fila, view);
            case "columna" -> columna(col, view);
            case "transponer" -> transponer(view);
            case "buscar" -> buscar(valor, view);
            case "sumar" -> sumar(view);
            case "limpiar" -> limpiar(view);
        }
    }

    private void asignar(int v, int f, int c, Array2DPracticePanel vw) {
        try {
            model.set(f, c, v);
            actualizar(vw);
            vw.getRenderer().highlightCell(f, c, Theme.SUCCESS, "Asignado: [" + f + "][" + c + "] = " + v);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void obtener(int f, int c, Array2DPracticePanel vw) {
        try {
            int v = model.get(f, c);
            vw.getRenderer().highlightCell(f, c, Theme.PRIMARY, "Valor en [" + f + "][" + c + "] = " + v);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void llenar(int v, Array2DPracticePanel vw) {
        model.fill(v);
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Matriz llenada con " + v, Theme.WARNING);
    }

    private void aleatorio(Array2DPracticePanel vw) {
        model.fillRandom();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Matriz llenada con valores aleatorios", Theme.SUCCESS);
    }

    private void fila(int f, Array2DPracticePanel vw) {
        try {
            int[] row = model.getRow(f);
            StringBuilder sb = new StringBuilder("Fila " + f + ": ");
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]).append(i < row.length - 1 ? ", " : "");
            }
            vw.getRenderer().setStatusMessage(sb.toString(), Theme.INFO);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void columna(int c, Array2DPracticePanel vw) {
        try {
            int[] col = model.getCol(c);
            StringBuilder sb = new StringBuilder("Columna " + c + ": ");
            for (int i = 0; i < col.length; i++) {
                sb.append(col[i]).append(i < col.length - 1 ? ", " : "");
            }
            vw.getRenderer().setStatusMessage(sb.toString(), Theme.INFO);
            vw.limpiarCampos();
        } catch (IndexOutOfBoundsException e) {
            vw.getRenderer().setStatusMessage("Error: " + e.getMessage(), Theme.ERROR);
        }
    }

    private void transponer(Array2DPracticePanel vw) {
        model.transpose();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Matriz transpuesta: " + model.rows() + "\u00D7" + model.cols(), Theme.PRIMARY_DARK);
    }

    private void buscar(int v, Array2DPracticePanel vw) {
        if (model.rows() == 0 || model.cols() == 0) {
            vw.getRenderer().setStatusMessage("Matriz vacía", Theme.ERROR);
            return;
        }
        int[] pos = model.search(v);
        if (pos != null) {
            vw.getRenderer().highlightCell(pos[0], pos[1], Theme.SUCCESS,
                    "Valor " + v + " encontrado en [" + pos[0] + "][" + pos[1] + "]");
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void sumar(Array2DPracticePanel vw) {
        int total = model.sum();
        vw.getRenderer().setStatusMessage("Suma total: " + total, Theme.SUCCESS);
    }

    private void limpiar(Array2DPracticePanel vw) {
        model.clear();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Matriz limpiada", Theme.TEXT_SECONDARY);
    }

    private void actualizar(Array2DPracticePanel vw) {
        vw.actualizar(model.getData(), model.rows(), model.cols());
    }
}

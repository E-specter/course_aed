package aed.modules.bst.controller;

import aed.core.view.Theme;
import aed.modules.bst.model.BinarySearchTree;
import aed.modules.bst.view.BSTPracticePanel;

public class BSTController {
    private BinarySearchTree<Integer> model;

    public BSTController() {
        model = new BinarySearchTree<>();
    }

    public void ejecutar(String accion, int valor, BSTPracticePanel vw) {
        switch (accion) {
            case "insertar" -> insertar(valor, vw);
            case "eliminar" -> eliminar(valor, vw);
            case "buscar" -> buscar(valor, vw);
            case "inorder" -> inOrder(vw);
            case "preorder" -> preOrder(vw);
            case "postorder" -> postOrder(vw);
            case "limpiar" -> limpiar(vw);
        }
    }

    private void insertar(int v, BSTPracticePanel vw) {
        model.insert(v);
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Insertado: " + v, Theme.SUCCESS);
        vw.limpiarCampos();
    }

    private void eliminar(int v, BSTPracticePanel vw) {
        if (model.delete(v)) {
            actualizar(vw);
            vw.getRenderer().setStatusMessage("Eliminado: " + v, Theme.ERROR);
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void buscar(int v, BSTPracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("BST vacío", Theme.ERROR);
            return;
        }
        if (model.search(v)) {
            vw.getRenderer().highlightNode(v, Theme.SUCCESS, "Valor " + v + " encontrado");
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void inOrder(BSTPracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("BST vacío", Theme.ERROR);
            return;
        }
        vw.getRenderer().highlightNode(null, null, null);
        vw.getRenderer().setStatusMessage("In-Order: " + model.inOrder() + " (ordenado)", Theme.PRIMARY);
    }

    private void preOrder(BSTPracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("BST vacío", Theme.ERROR);
            return;
        }
        vw.getRenderer().highlightNode(null, null, null);
        vw.getRenderer().setStatusMessage("Pre-Order: " + model.preOrder(), Theme.PRIMARY);
    }

    private void postOrder(BSTPracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("BST vacío", Theme.ERROR);
            return;
        }
        vw.getRenderer().highlightNode(null, null, null);
        vw.getRenderer().setStatusMessage("Post-Order: " + model.postOrder(), Theme.PRIMARY);
    }

    private void limpiar(BSTPracticePanel vw) {
        model.clear();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("BST limpiado", Theme.TEXT_SECONDARY);
    }

    private void actualizar(BSTPracticePanel vw) { vw.actualizar(); }
    public BinarySearchTree.Node<Integer> getRoot() { return model.getRoot(); }
    public int getSize() { return model.size(); }
}

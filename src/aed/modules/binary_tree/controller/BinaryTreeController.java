package aed.modules.binary_tree.controller;

import aed.core.view.Theme;
import aed.modules.binary_tree.model.BinaryTree;
import aed.modules.binary_tree.model.TreeNode;
import aed.modules.binary_tree.view.BinaryTreePracticePanel;

public class BinaryTreeController {
    private BinaryTree<Integer> model;

    public BinaryTreeController() {
        model = new BinaryTree<>();
    }

    public void ejecutar(String accion, int valor, BinaryTreePracticePanel vw) {
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

    private void insertar(int v, BinaryTreePracticePanel vw) {
        model.insert(v);
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Insertado: " + v, Theme.SUCCESS);
        vw.limpiarCampos();
    }

    private void eliminar(int v, BinaryTreePracticePanel vw) {
        if (model.delete(v)) {
            actualizar(vw);
            vw.getRenderer().setStatusMessage("Eliminado: " + v, Theme.ERROR);
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void buscar(int v, BinaryTreePracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("Árbol vacío", Theme.ERROR);
            return;
        }
        if (model.search(v)) {
            vw.getRenderer().highlightNode(v, Theme.SUCCESS, "Valor " + v + " encontrado");
        } else {
            vw.getRenderer().setStatusMessage("Valor " + v + " no encontrado", Theme.ERROR);
        }
        vw.limpiarCampos();
    }

    private void inOrder(BinaryTreePracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("Árbol vacío", Theme.ERROR);
            return;
        }
        String result = model.inOrder();
        vw.getRenderer().highlightNode(null, null, null);
        vw.getRenderer().setStatusMessage("In-Order: " + result, Theme.PRIMARY);
    }

    private void preOrder(BinaryTreePracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("Árbol vacío", Theme.ERROR);
            return;
        }
        String result = model.preOrder();
        vw.getRenderer().highlightNode(null, null, null);
        vw.getRenderer().setStatusMessage("Pre-Order: " + result, Theme.PRIMARY);
    }

    private void postOrder(BinaryTreePracticePanel vw) {
        if (model.isEmpty()) {
            vw.getRenderer().setStatusMessage("Árbol vacío", Theme.ERROR);
            return;
        }
        String result = model.postOrder();
        vw.getRenderer().highlightNode(null, null, null);
        vw.getRenderer().setStatusMessage("Post-Order: " + result, Theme.PRIMARY);
    }

    private void limpiar(BinaryTreePracticePanel vw) {
        model.clear();
        actualizar(vw);
        vw.getRenderer().setStatusMessage("Árbol limpiado", Theme.TEXT_SECONDARY);
    }

    private void actualizar(BinaryTreePracticePanel vw) { vw.actualizar(); }
    public TreeNode<Integer> getRoot() { return model.getRoot(); }
    public int getSize() { return model.size(); }
}

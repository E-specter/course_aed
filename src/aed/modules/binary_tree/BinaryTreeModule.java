package aed.modules.binary_tree;

import aed.core.Module;
import aed.modules.binary_tree.controller.BinaryTreeController;
import aed.modules.binary_tree.view.BinaryTreePracticePanel;
import aed.modules.binary_tree.view.BinaryTreeTheoryPanel;
import javax.swing.JPanel;

public class BinaryTreeModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Árbol Binario"; }

    @Override
    public String getDescription() { return "Árbol binario con inserción level-order y 3 recorridos"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new BinaryTreeTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new BinaryTreePracticePanel(new BinaryTreeController());
    }
}

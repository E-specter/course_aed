package aed.modules.bst;

import aed.core.Module;
import aed.modules.bst.controller.BSTController;
import aed.modules.bst.view.BSTPracticePanel;
import aed.modules.bst.view.BSTTheoryPanel;
import javax.swing.JPanel;

public class BSTModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "BST (B\u00FAsqueda)"; }

    @Override
    public String getDescription() { return "Árbol Binario de Búsqueda con inserción ordenada y 3 casos de eliminación"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new BSTTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new BSTPracticePanel(new BSTController());
    }
}

package aed.modules.doubly_list;

import aed.core.Module;
import aed.modules.doubly_list.controller.DoublyListController;
import aed.modules.doubly_list.view.DoublyListPracticePanel;
import aed.modules.doubly_list.view.DoublyListTheoryPanel;
import javax.swing.JPanel;

public class DoublyListModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Lista Doblemente Enlazada"; }

    @Override
    public String getDescription() { return "Operaciones con lista doblemente enlazada"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new DoublyListTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new DoublyListPracticePanel(new DoublyListController());
    }
}

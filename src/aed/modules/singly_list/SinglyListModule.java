package aed.modules.singly_list;

import aed.core.Module;
import aed.modules.singly_list.controller.SinglyListController;
import aed.modules.singly_list.view.SinglyListPracticePanel;
import aed.modules.singly_list.view.SinglyListTheoryPanel;
import javax.swing.JPanel;

public class SinglyListModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Lista Simplemente Enlazada"; }

    @Override
    public String getDescription() { return "Operaciones con lista simplemente enlazada"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new SinglyListTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new SinglyListPracticePanel(new SinglyListController());
    }
}

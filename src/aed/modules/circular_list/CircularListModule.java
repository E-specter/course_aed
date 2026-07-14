package aed.modules.circular_list;

import aed.core.Module;
import aed.modules.circular_list.controller.CircularListController;
import aed.modules.circular_list.view.CircularListPracticePanel;
import aed.modules.circular_list.view.CircularListTheoryPanel;
import javax.swing.JPanel;

public class CircularListModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Lista Circular"; }

    @Override
    public String getDescription() { return "Operaciones con lista circular enlazada"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new CircularListTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new CircularListPracticePanel(new CircularListController());
    }
}

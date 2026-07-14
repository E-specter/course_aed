package aed.modules.arrays_2d;

import aed.core.Module;
import aed.modules.arrays_2d.controller.Array2DController;
import aed.modules.arrays_2d.view.Array2DPracticePanel;
import aed.modules.arrays_2d.view.Array2DTheoryPanel;
import javax.swing.JPanel;

public class Array2DModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Arreglo Bidimensional"; }

    @Override
    public String getDescription() { return "Operaciones con matrices 2D"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new Array2DTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new Array2DPracticePanel(new Array2DController());
    }
}

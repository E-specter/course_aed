package aed.modules.arrays_3d;

import aed.core.Module;
import aed.modules.arrays_3d.controller.Array3DController;
import aed.modules.arrays_3d.view.Array3DPracticePanel;
import aed.modules.arrays_3d.view.Array3DTheoryPanel;
import javax.swing.JPanel;

public class Array3DModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Arreglo Tridimensional"; }

    @Override
    public String getDescription() { return "Operaciones con arreglos 3D por capas"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new Array3DTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new Array3DPracticePanel(new Array3DController());
    }
}

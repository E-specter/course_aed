package aed.modules.arrays_1d;

import aed.core.Module;
import aed.modules.arrays_1d.controller.Array1DController;
import aed.modules.arrays_1d.view.Array1DPracticePanel;
import aed.modules.arrays_1d.view.Array1DTheoryPanel;
import javax.swing.JPanel;

public class Array1DModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() {
        return "Arreglo Unidimensional";
    }

    @Override
    public String getDescription() {
        return "Operaciones con arreglos 1D: insertar, eliminar, modificar, buscar y ordenar";
    }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) {
            theoryPanel = new Array1DTheoryPanel();
        }
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        Array1DController controller = new Array1DController();
        return new Array1DPracticePanel(controller);
    }
}

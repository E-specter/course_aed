package aed.modules.stack;

import aed.core.Module;
import aed.modules.stack.controller.StackController;
import aed.modules.stack.view.StackPracticePanel;
import aed.modules.stack.view.StackTheoryPanel;
import javax.swing.JPanel;

public class StackModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Pila (Stack)"; }

    @Override
    public String getDescription() { return "Estructura LIFO: push, pop, peek"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new StackTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new StackPracticePanel(new StackController());
    }
}

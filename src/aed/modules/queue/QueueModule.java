package aed.modules.queue;

import aed.core.Module;
import aed.modules.queue.controller.QueueController;
import aed.modules.queue.view.QueuePracticePanel;
import aed.modules.queue.view.QueueTheoryPanel;
import javax.swing.JPanel;

public class QueueModule implements Module {
    private JPanel theoryPanel;

    @Override
    public String getName() { return "Cola (Queue)"; }

    @Override
    public String getDescription() { return "Estructura FIFO: enqueue, dequeue, peek"; }

    @Override
    public JPanel getTheoryPanel() {
        if (theoryPanel == null) theoryPanel = new QueueTheoryPanel();
        return theoryPanel;
    }

    @Override
    public JPanel getPracticePanel() {
        return new QueuePracticePanel(new QueueController());
    }
}

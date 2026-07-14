package aed.core;

import javax.swing.JPanel;

public interface Module {
    String getName();
    String getDescription();
    JPanel getTheoryPanel();
    JPanel getPracticePanel();
}

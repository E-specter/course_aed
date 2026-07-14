package aed.core.controller;

import aed.core.Module;
import aed.core.view.MainWindow;
import aed.core.view.MainWindow.TreeNodeData;
import aed.core.view.Theme;
import aed.core.view.TheoryPanel;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private MainWindow mainWindow;
    private JFrame ventanaPractica = null;
    private Module currentModule = null;
    private Map<String, Module> moduleRegistry = new HashMap<>();

    public MainController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.setVisible(true);
        mainWindow.getTree().addTreeSelectionListener(this::onTreeSelection);
        mainWindow.getTree().setSelectionRow(0);
    }

    public void registerModule(String moduleKey, Module module) {
        moduleRegistry.put(moduleKey, module);
    }

    private void onTreeSelection(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                mainWindow.getTree().getLastSelectedPathComponent();
        if (node == null || !node.isLeaf()) return;

        cerrarPractica();

        TreeNodeData data = (TreeNodeData) node.getUserObject();
        if (data == null || data.moduleKey == null) return;

        Module module = moduleRegistry.get(data.moduleKey);
        if (module != null) {
            cargarModulo(module);
        } else {
            cargarPlaceholder(data.label);
        }
    }

    private void cargarModulo(Module module) {
        currentModule = module;
        JPanel theoryPanel = module.getTheoryPanel();
        if (theoryPanel instanceof TheoryPanel) {
            ((TheoryPanel) theoryPanel).addEjecutarListener(() -> abrirPractica(module));
        }
        mainWindow.setContent(theoryPanel);
    }

    private void cargarPlaceholder(String moduleName) {
        currentModule = null;
        String html = "<html><body style='font-family:Segoe UI; padding:50px; text-align:center;'>"
                + "<h2 style='color:#757575;'>" + moduleName + "</h2>"
                + "<p style='color:#9E9E9E; font-size:16px; margin-top:20px;'>"
                + "Este m\u00F3dulo se implementar\u00E1 en una fase posterior.</p>"
                + "</body></html>";
        TheoryPanel panel = new TheoryPanel(html);
        panel.ejecutarButton.setEnabled(false);
        mainWindow.setContent(panel);
    }

    public void abrirPractica(Module modulo) {
        if (ventanaPractica != null) {
            ventanaPractica.dispose();
        }
        JFrame frame = new JFrame("Pr\u00E1ctica - " + modulo.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(modulo.getPracticePanel());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPractica = null;
            }
        });
        frame.setSize(Theme.PRACTICE_WIDTH, Theme.PRACTICE_HEIGHT);
        frame.setLocationRelativeTo(mainWindow);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setState(Frame.NORMAL);
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();
        ventanaPractica = frame;
    }

    private void cerrarPractica() {
        if (ventanaPractica != null) {
            ventanaPractica.dispose();
            ventanaPractica = null;
        }
    }
}

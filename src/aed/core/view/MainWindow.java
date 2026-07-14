package aed.core.view;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MainWindow extends JFrame {
    private JTree tree;
    private JPanel contentPanel;

    public MainWindow() {
        setTitle("Curso: Algoritmos y Estructuras de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Theme.WINDOW_WIDTH, Theme.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 500));
        initComponents();
    }

    private void initComponents() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerSize(4);
        splitPane.setBorder(null);
        splitPane.setResizeWeight(0.0);

        DefaultMutableTreeNode root = buildTreeModel();
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setFont(Theme.BODY_FONT);
        tree.setRowHeight(30);
        tree.setBackground(Theme.PANEL_BG);
        tree.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setBackgroundSelectionColor(new Color(0xBB, 0xDE, 0xFB));
        renderer.setBorderSelectionColor(null);
        tree.setCellRenderer(renderer);

        JScrollPane treeScroll = new JScrollPane(tree);
        treeScroll.setBorder(null);
        treeScroll.setMinimumSize(new Dimension(200, 0));
        splitPane.setLeftComponent(treeScroll);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Theme.PANEL_BG);
        splitPane.setRightComponent(contentPanel);

        splitPane.setDividerLocation(Theme.DIVIDER_SIZE);
        add(splitPane);
    }

    private DefaultMutableTreeNode buildTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Estructuras");

        DefaultMutableTreeNode arrays = addCategory(root, "Arreglos");
        addLeaf(arrays, "Unidimensional", "arrays_1d");
        addLeaf(arrays, "Bidimensional", "arrays_2d");
        addLeaf(arrays, "Tridimensional", "arrays_3d");

        DefaultMutableTreeNode lists = addCategory(root, "Listas");
        addLeaf(lists, "Simplemente Enlazada", "singly_list");
        addLeaf(lists, "Doblemente Enlazada", "doubly_list");
        addLeaf(lists, "Circular", "circular_list");
        addLeaf(lists, "Pila (Stack)", "stack");
        addLeaf(lists, "Cola (Queue)", "queue");

        DefaultMutableTreeNode trees = addCategory(root, "Árboles");
        addLeaf(trees, "Árbol Binario", "binary_tree");
        addLeaf(trees, "BST (B\u00FAsqueda)", "bst");

        return root;
    }

    private DefaultMutableTreeNode addCategory(DefaultMutableTreeNode parent, String name) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new TreeNodeData(name, null));
        parent.add(node);
        return node;
    }

    private void addLeaf(DefaultMutableTreeNode parent, String displayName, String moduleKey) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new TreeNodeData(displayName, moduleKey));
        parent.add(node);
    }

    public JTree getTree() {
        return tree;
    }

    public void setContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static class TreeNodeData {
        public final String label;
        public final String moduleKey;

        public TreeNodeData(String label, String moduleKey) {
            this.label = label;
            this.moduleKey = moduleKey;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}

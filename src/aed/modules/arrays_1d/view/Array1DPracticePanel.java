package aed.modules.arrays_1d.view;

import aed.core.view.Theme;
import aed.modules.arrays_1d.controller.Array1DController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Array1DPracticePanel extends JPanel {
    private Array1DController controller;
    private Array1DRenderer renderer;
    private JTextField txtValor;
    private JTextField txtIndice;
    private JLabel lblSize;

    public Array1DPracticePanel(Array1DController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 0));
        setBackground(Theme.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        renderer = new Array1DRenderer();
        renderer.setPreferredSize(new Dimension(0, 300));
        renderer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER));
        add(renderer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(0, 8));
        bottomPanel.setBackground(Theme.BACKGROUND);
        bottomPanel.setBorder(new EmptyBorder(12, 16, 16, 16));

        JPanel infoBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 4));
        infoBar.setBackground(Theme.BACKGROUND);
        lblSize = new JLabel("Tama\u00F1o: 0");
        lblSize.setFont(Theme.BODY_FONT);
        lblSize.setForeground(Theme.TEXT_SECONDARY);
        infoBar.add(lblSize);
        bottomPanel.add(infoBar, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridBagLayout());
        controls.setBackground(Theme.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 4, 3, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtValor = new JTextField(6);
        txtValor.setFont(Theme.BODY_FONT);
        txtIndice = new JTextField(6);
        txtIndice.setFont(Theme.BODY_FONT);

        c.gridx = 0; c.gridy = 0;
        controls.add(new JLabel("Valor:"), c);
        c.gridx = 1;
        controls.add(txtValor, c);

        c.gridx = 2;
        controls.add(new JLabel("Índice:"), c);
        c.gridx = 3;
        controls.add(txtIndice, c);

        c.gridx = 4; c.gridwidth = 2;
        controls.add(crearBoton("Agregar", Theme.SUCCESS, e -> ejecutar("agregar")), c);

        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        controls.add(crearBoton("Insertar", Theme.PRIMARY, e -> ejecutar("insertar")), c);
        c.gridx = 1;
        controls.add(crearBoton("Modificar", Theme.WARNING, e -> ejecutar("modificar")), c);
        c.gridx = 2;
        controls.add(crearBoton("Eliminar", Theme.ERROR, e -> ejecutar("eliminar")), c);
        c.gridx = 3;
        controls.add(crearBoton("Buscar", Theme.INFO, e -> ejecutar("buscar")), c);
        c.gridx = 4;
        controls.add(crearBoton("Buscar Bin", Theme.INFO, e -> ejecutar("buscarBin")), c);
        c.gridx = 5;
        controls.add(crearBoton("Limpiar", Theme.TEXT_SECONDARY, e -> ejecutar("limpiar")), c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 3;
        controls.add(crearBoton("Burbuja", Theme.PRIMARY_DARK, e -> ejecutar("burbuja")), c);
        c.gridx = 3;
        controls.add(crearBoton("Selección", Theme.PRIMARY_DARK, e -> ejecutar("seleccion")), c);
        c.gridx = 5;
        controls.add(crearBoton("Inserción", Theme.PRIMARY_DARK, e -> ejecutar("insercion")), c);

        bottomPanel.add(controls, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color bg, java.awt.event.ActionListener listener) {
        JButton btn = new JButton(texto);
        btn.setFont(Theme.SMALL_FONT);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(6, 14, 6, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        return btn;
    }

    private void ejecutar(String accion) {
        try {
            int valor = txtValor.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtValor.getText().trim());
            int indice = txtIndice.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtIndice.getText().trim());
            controller.ejecutar(accion, valor, indice, this);
        } catch (NumberFormatException ex) {
            renderer.setStatusMessage("Ingrese valores numéricos válidos", Theme.ERROR);
        }
    }

    public void actualizar(int[] data, int size) {
        renderer.setData(data);
        lblSize.setText("Tamaño: " + size);
    }

    public Array1DRenderer getRenderer() {
        return renderer;
    }

    public void limpiarCampos() {
        txtValor.setText("");
        txtIndice.setText("");
    }
}

package tsp;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel implements ActionListener {

    private JPanel RPanel = new JPanel();
    private JButton btnViewResult;
    private ResultSet ResultSet;
    private JComboBox AlgorithmList = new JComboBox();
    private JComboBox RondeList = new JComboBox();
    private Hoofdscherm parent;

    public ResultPanel(Hoofdscherm parent) {
        this.parent = parent;
        setLayout(new GridBagLayout());
        getResultPanel();
    }

    public void getResultPanel() {
        GridBagConstraints c = new GridBagConstraints();
        Font font = new Font("Courier", Font.BOLD, 16);
        JLabel Result = new JLabel("Resultaat:");

        Result.setFont(font);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(Result, c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Algoritme:"), c);

        //JComboBox AlgorithmList = new JComboBox();
        AlgorithmList.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        add(AlgorithmList, c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Ronde:"), c);

        //JComboBox RondeList = new JComboBox();
        RondeList.setEditable(false);
        c.gridx = 1;
        c.gridy = 2;
        add(RondeList, c);

        btnViewResult = new JButton("Bekijk");
        btnViewResult.addActionListener(this);
        c.gridx = 1;
        c.gridy = 4;
        add(btnViewResult, c);
    }

    public void setResultPanel(ResultSet RS) {
        // Comboboxen alle gebruikte algoritmes en rondes toewijzen
        ResultSet = RS;
        AlgorithmList.removeAllItems();
        for (Result temp : ResultSet.getAlgorithms()) {
            AlgorithmList.addItem(temp.getAlgorithm());
        }
        AlgorithmList.setSelectedItem(ResultSet.getBest().getAlgorithm());
        AlgorithmList.setEditable(true);

        RondeList.removeAllItems();
        for (int i = 1; i <= ResultSet.getRondes(); i++) {
            RondeList.addItem("Ronde " + i);
        }
        RondeList.setSelectedIndex(ResultSet.getBestRonde());
        RondeList.setEditable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        parent.setActiveResultLog((String) AlgorithmList.getSelectedItem(), (int) RondeList.getSelectedIndex());
    }

}

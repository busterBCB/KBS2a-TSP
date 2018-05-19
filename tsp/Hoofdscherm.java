package tsp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

public class Hoofdscherm extends JFrame implements ActionListener {

    private JButton btnStart, btnTest;
    private ProductList List = new ProductList();
    private JList<Algorithm> lstAlgorithm;
    private ArrayList<Algorithm> algorithms;
    private JSpinner sprAantalP;
    private JSpinner sprAantalR;
    private JTextPane activeResultLog;
    private JTextPane resultLog;
    private Result R;
    private Tekenpanel TekenPanel = new Tekenpanel();
    private JPanel mainPanel = new JPanel();
    private JPanel RPanel = new JPanel();
    private JPanel logPanel = new JPanel();
    private ResultPanel RP = new ResultPanel(this);
    private ResultSet RS;
    ArrayList<Result> Ronde = new ArrayList<Result>();

    public Hoofdscherm() {
        int width = 680 / 2 - 30;
        setTitle("TSP");
        setSize(680, 680);
        setLayout(new GridLayout(2, 2));
        mainPanel.setLayout(new FlowLayout());
        Font font = new Font("Courier", Font.BOLD, 16);
        //setPreferredSize(new Dimension(width, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        algorithms = new ArrayList<>();
        algorithms.add(new Greedy());
        algorithms.add(new LimitedBruteForce());
        algorithms.add(new II_opt());

        JLabel lblAlgoritm = new JLabel("Algoritmes");
        lblAlgoritm.setPreferredSize(new Dimension(width, 20));
        lblAlgoritm.setFont(font);
        mainPanel.add(lblAlgoritm);

        lstAlgorithm = new JList(algorithms.toArray());
        lstAlgorithm.setPreferredSize(new Dimension(width, 50));
        lstAlgorithm.setSelectedIndex(0);
        mainPanel.add(lstAlgorithm);

        mainPanel.add(new JLabel("Aantal pakketjes:"));
        sprAantalP = new JSpinner(new SpinnerNumberModel(5, 1, 25, 1));
        mainPanel.add(sprAantalP);

        mainPanel.add(new JLabel("Aantal Rondes:"));
        sprAantalR = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        mainPanel.add(sprAantalR);

        btnStart = new JButton("Start");
        btnStart.addActionListener(this);
        mainPanel.add(btnStart);

        //Log Panel
        logPanel.setLayout(new GridLayout(1, 2, 10, 10));

        resultLog = new JTextPane();
        resultLog.setPreferredSize(new Dimension(this.getWidth() - 30, 200));
        resultLog.setEditable(false);
        logPanel.add(resultLog);

        activeResultLog = new JTextPane();
        activeResultLog.setPreferredSize(new Dimension(this.getWidth() - 30, 200));
        activeResultLog.setEditable(false);
        logPanel.add(activeResultLog);

        add(mainPanel);
        add(TekenPanel);
        add(RP);
        add(logPanel);

    }

    public void setActiveResultLog(String algorithm, int Ronde) {
        Result specific = RS.getSpecific(Ronde, algorithm);
        double RekenTijd = specific.getRekentijd() / 1000000000.0;
        activeResultLog.setText(String.format(
                "Huidige weergave:\n\n"
                + "Algoritme: %s\n"
                + "Bereken tijd: %fs\n"
                + "Afstand: %.5f", specific.getAlgorithm(), RekenTijd, specific.getAfstand()
        ));
        TekenPanel.setResult(specific);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnStart) {
            Ronde.clear();
            RS = new ResultSet();
            for (int i = 0; i < (int) sprAantalR.getValue(); i++) {
                Ronde.clear();
                List.RandomSet((int) sprAantalP.getValue());

                for (Algorithm algorithm : lstAlgorithm.getSelectedValuesList()) {
                    R = algorithm.BerekenRoute(List.getList());
                    Ronde.add(R);
                }
                RS.addResult(Ronde);
            }
            System.out.println(RS);
            //Result BestResult = RS.getBest();
            //System.out.println("BestResult" + BestResult);
            TekenPanel.setResult(R);

            Result bestResult = RS.getBest();
            double RekenTijd = bestResult.getRekentijd() / 1000000000.0;
            // Beste Route weergeven in log
            resultLog.setText(String.format(
                    "Kortste Route:\n\n"
                    + "Algoritme: %s\n"
                    + "Bereken tijd: %fs\n"
                    + "Afstand: %.5f", bestResult.getAlgorithm(), RekenTijd, bestResult.getAfstand()
            ));
            //activeResultLog leeg halen na nieuwe start
            activeResultLog.setText(String.format(
                    ""
            ));
            RP.setResultPanel(RS);
            repaint();

        }
    }
}

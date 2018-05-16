
package tsp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;


public class Hoofdscherm extends JFrame implements ActionListener {
    private JButton btnStart;
    private ProductList List = new ProductList();
    private JList<Algorithm> lstAlgorithm;
    private ArrayList<Algorithm> algorithms;
    private JSpinner sprAantalP;
    private JSpinner sprAantalK;
    private JTextPane resultLog;
    private Result R;
    private Tekenpanel TekenPanel = new Tekenpanel();
    private JPanel mainPanel = new JPanel();
    private JPanel LPanel = new JPanel();
    
    public Hoofdscherm() {
        int width = 680 / 2 - 30;
        setTitle("TSP");
        setSize(680, 680);
        setLayout(new GridLayout(2,2));
        mainPanel.setLayout(new FlowLayout());
        LPanel.setLayout(new FlowLayout());
        Font font = new Font("Courier", Font.BOLD,16);
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
        sprAantalP = new JSpinner(new SpinnerNumberModel(5, 0, 100, 1));
        mainPanel.add(sprAantalP);
        
        mainPanel.add(new JLabel("Aantal keer:"));
        sprAantalK = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
        mainPanel.add(sprAantalK);
        
        btnStart = new JButton("Start");
        btnStart.addActionListener(this);
        mainPanel.add(btnStart);
        
        resultLog = new JTextPane();
        resultLog.setPreferredSize(new Dimension(this.getWidth() - 30, 200));
        resultLog.setEditable(false);
        LPanel.add(resultLog);
        
        add(mainPanel);
        add(TekenPanel);
        add(LPanel);

       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnStart){
            List.RandomSet((int) sprAantalP.getValue());
            System.out.println("RList" + List);
            
            for (Algorithm algorithm : lstAlgorithm.getSelectedValuesList()) {
                R = algorithm.BerekenRoute(List.getList());
                //System.out.println(algorithm.BerekenRoute(List.getList()));
            }
            System.out.println(R);
            TekenPanel.setResult(R);
            repaint();
            //R.clearRoute();
        }
        
    }

}

//            LimitedBruteForce A1 = new LimitedBruteForce();
//            R = A1.BerekenRoute(List.getList());
//            System.out.println(R);
//            TekenPanel.setResult(R);
//            repaint();
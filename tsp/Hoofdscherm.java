
package tsp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;


public class Hoofdscherm extends JFrame implements ActionListener {
    private ProductList List;
    private JList<Algorithm> lstAlgorithm;
    private ArrayList<Algorithm> algorithms;
    
    public Hoofdscherm(ProductList L) {
        List = L;
        int width = 680 / 2 - 30;
        setTitle("TSP");
        setSize(680, 480);
        setLayout(new FlowLayout());
        Font font = new Font("Courier", Font.BOLD,16);
        setPreferredSize(new Dimension(width, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        algorithms = new ArrayList<>();
        algorithms.add(new Greedy(List.getList()));
        algorithms.add(new LimitedBruteForce(List.getList()));
        
        
        JLabel lblAlgoritm = new JLabel("Algoritme(s)");
        lblAlgoritm.setPreferredSize(new Dimension(width, 20));
        lblAlgoritm.setFont(font);
        add(lblAlgoritm);

        lstAlgorithm = new JList(algorithms.toArray());
        lstAlgorithm.setPreferredSize(new Dimension(310, 50));
        lstAlgorithm.setSelectedIndex(0);
        add(lstAlgorithm);
        
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }

}
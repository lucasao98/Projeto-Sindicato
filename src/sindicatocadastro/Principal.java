package sindicatocadastro;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Principal extends JFrame implements ActionListener{
    JPanel back = new JPanel();
    JMenu men = new JMenu("Cadastro");
    JMenu lista = new JMenu("Visualizar");
    JMenu opc = new JMenu("Opções");
    JMenuBar barramenu = new JMenuBar();
    JMenuItem sair = new JMenuItem("Sair");
    JMenuItem est = new JMenuItem("Associados");
    JMenuItem vis = new JMenuItem("Associados");
    
    public Principal(){
        setJMenuBar(barramenu);
        opc.add(sair);
        men.add(est);
        lista.add(vis);
        barramenu.add(men);
        barramenu.add(lista);
        barramenu.add(opc);
        est.addActionListener(this);
        sair.addActionListener(this);
        vis.addActionListener(this);

        back.setSize(600,600);
        back.setBackground(Color.LIGHT_GRAY);
        add(back);
        
        setLayout(null);
        setResizable(false);
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Menu Principal");
    }
    
    public static void main(String[] args) {
        Principal menu = new Principal();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == sair){
           int confirma= JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?","Atenção",JOptionPane.YES_NO_CANCEL_OPTION);
            if(confirma == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        else if(ae.getSource() == est){
            Cadastros cd = new Cadastros();
            this.dispose();
        }
        else if(ae.getSource() == vis){
            VisualizarAssociados visass = new VisualizarAssociados();
            this.dispose();
        }
        
    }
}

package sindicatocadastro;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.sql.*;
import br.com.conexao.ModuloConexao;
import java.awt.AlphaComposite;
import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class VisualizarAssociados extends JFrame implements ActionListener{
    JPanel back = new JPanel();
    Font f2 = new Font("SansSerif", Font.BOLD, 15);
    JTable tabela = new JTable();
    Label lblid = new Label("Número de Id");
    Label lblnome = new Label("Nome");
    Button proc = new Button("Procurar");
    Button voltar = new Button("Voltar");
    TextField txtid = new TextField();
    TextField txtnomealt = new TextField();
    File imagem;
    JScrollPane jscro = new JScrollPane(tabela);
    
    
    //--------Banco de Dados-----//
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    private void preencherTabela(){
        ArrayList dados = new ArrayList();
        String [] Colunas = new String[]{"ID","Nome","Data Nasc.","Data ADM"};
        String sql = "select * from Associados";
        
        conexao = ModuloConexao.conector();
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            do{
                dados.add(new Object[]{rs.getInt("idassoc"),rs.getString("nome"),rs.getString("datanasc"),rs.getString("dataadm")});
                
            }while(rs.next());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        tabela.setModel(modelo);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(0).setResizable(false);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(1).setResizable(false);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(2).setResizable(false);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(tabela.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    }
    
    private void procurar(){
        String sql = "select * from Associados where idassoc=? or nome=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtid.getText());
            pst.setString(2,txtnomealt.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                AlterarDados altdados = new AlterarDados();
                altdados.txtaltid.setText(rs.getString(1));
                altdados.txtnome.setText(rs.getString(2));
                altdados.txtdatanasc.setText(rs.getString(3));
                altdados.comboestciv.setSelectedItem(rs.getString(4));
                altdados.txtprof.setText(rs.getString(5));
                altdados.txtnac.setText(rs.getString(6));
                altdados.txtnac.setText(rs.getString(7));
                altdados.comboleresc.setSelectedItem(8);
                altdados.txtfil.setText(rs.getString(9));
                altdados.txtdataadm.setText(rs.getString(10));
                altdados.txtres.setText(rs.getString(11));
                altdados.txtcart.setText(rs.getString(12));
                altdados.txtmar.setText(rs.getString(13));
                altdados.txtinps.setText(rs.getString(14));
                altdados.lblImagem.setIcon(new ImageIcon(rs.getBytes("img")));
                
            }
            else if((txtid.getText().isEmpty())&&(txtnomealt.getText().isEmpty())){
                JOptionPane.showMessageDialog(null,"Campos Vazios");
                VisualizarAssociados vas = new VisualizarAssociados();
            }
            else{
                JOptionPane.showMessageDialog(null,"Associado não cadastrado");
                VisualizarAssociados vas = new VisualizarAssociados();
                dispose();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public VisualizarAssociados(){ 
        preencherTabela();
        lblid.setBounds(450,100,100,20);
        lblid.setFont(f2);
        txtid.setBounds(450,120,100,20);
        lblnome.setBounds(450,150,48,20);
        lblnome.setFont(f2);
        txtnomealt.setBounds(450,170,150,20);
        proc.setBounds(430,400,100,30);
        proc.addActionListener(this);
        voltar.setBounds(550,400,80,30);
        voltar.addActionListener(this);
        
        tabela.setBounds(20,50,400,400);
        tabela.setBorder(BorderFactory.createLineBorder(Color.black,2));
        back.setBackground(Color.LIGHT_GRAY);
        back.setSize(600,500);
        back.setLayout(null);
        
        back.add(tabela);
        back.add(lblid);
        back.add(txtid);
        back.add(lblnome);
        back.add(txtnomealt);
        back.add(proc);
        back.add(voltar);
        
        add(back);
        setTitle("Visualizar Associados");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650,500);
    }
    
    public static void main(String[] args) {
        VisualizarAssociados va = new VisualizarAssociados();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == proc){
            procurar();
            this.dispose();
        }
        else if(ae.getSource() == voltar){
            Principal prp = new Principal();
            this.dispose();
        }
    }
}

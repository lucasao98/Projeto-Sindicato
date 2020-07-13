package sindicatocadastro;

import br.com.conexao.ModuloConexao;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.sql.*;
import javax.swing.ImageIcon;

public class AlterarDados extends JFrame implements ActionListener{
    JPanel bck = new JPanel();
    JPanel fotopanel = new JPanel();
    JPanel cadspanel = new JPanel();
    Label nome = new Label("*Nome");
    Border blackline,blacklineft;
    Font f = new Font("Sansserif",Font.ITALIC,15);
    TextField txtnome = new TextField();
    TextField txtprof = new TextField();
    TextField txtnac = new TextField();
    TextField txtdata = new TextField();
    TextField txtnat = new TextField();
    TextField txtfil = new TextField();
    TextField txtres = new TextField();
    TextField txtcart = new TextField();
    TextField txtmar = new TextField();
    TextField txtinps = new TextField();
    TextField txtdatanasc = new TextField();
    TextField txtdataadm = new TextField();
    TextField txtaltid = new TextField();
    Label data = new Label("*Data de Nascimento");
    Label dataadm = new Label("*Data de Admissão");
    Label lblest = new Label("Estado Civil");
    Label lblres = new Label("Residência");
    Label lblprof = new Label("Profissão");
    Label lblnac = new Label("Nacionalidade");
    Label lblnat = new Label("Naturalidade");
    Label lblfil = new Label("Filiação");
    Label lblcart = new Label("Carteira Profissional");
    Label lblmar = new Label("Marítima");
    Label lblinps = new Label("INPS");
    Label lblobg = new Label("*Campos Obrigatórios");
    Label lblleresc = new Label("Sabe ler e escrever");
    String [] estadocivil = {"Solteiro","Casado","Separado","Divorciado","Viúvo"};
    String [] sabeler = {"Sim","Não"};
    JComboBox comboestciv = new JComboBox(estadocivil);
    JComboBox comboleresc = new JComboBox(sabeler);
    Button btnalterar = new Button("Alterar");
    Button voltar = new Button("Voltar");
    Label lblaltid = new Label("Id");
    Button deletar = new Button("Deletar");
    Button procass = new Button("Procurar");
    JLabel lblImagem = new JLabel();
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private void alterar(){
        String sql = "update Associados set nome=? where idassoc=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtnome.getText());
            //pst.setString(2,txtdatanasc.getText());
            //pst.setString(3,comboestciv.getSelectedItem().toString());
           // pst.setString(4,txtprof.getText());
            //pst.setString(5,txtnac.getText());
            //pst.setString(6,txtnat.getText());
            //pst.setString(7,comboleresc.getSelectedItem().toString());
            //pst.setString(8,txtfil.getText());
            //pst.setString(9,txtdataadm.getText());
            //pst.setString(10,txtres.getText());
            //pst.setString(11,txtcart.getText());
            //pst.setString(12,txtmar.getText());
            //pst.setString(13,txtinps.getText());
            pst.setString(2,txtaltid.getText());

            JOptionPane.showMessageDialog(null,"Dados do Associado foram alterados com sucesso!");
            
            if((txtnome.getText().isEmpty())&&(txtdatanasc.getText().isEmpty())&&(txtdataadm.getText().isEmpty())){
                JOptionPane.showMessageDialog(null,"Preencha os campos Obrigatórios");
            }
            else{
                int adicionado = pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null,"Associado alterado com sucesso!");
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover este associado?","Atenção",JOptionPane.YES_NO_OPTION);
            if(confirma == JOptionPane.YES_OPTION){
                String sql = "delete from Associados where nome=?";
                
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1,txtnome.getText());
                    
                    int apagado = pst.executeUpdate();
                    
                        if(apagado > 0){
                            JOptionPane.showMessageDialog(null,"Associado removido om sucesso");
                        }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e);
                }
            }
    }
    
    public AlterarDados(){
        conexao = ModuloConexao.conector();
        blackline = BorderFactory.createLineBorder(Color.BLACK);
        blacklineft = BorderFactory.createLineBorder(Color.BLACK,2);
        bck.setSize(200,200);
        bck.setBackground(Color.LIGHT_GRAY);
        bck.setLayout(new GridBagLayout());
        GridBagConstraints gbcft = new GridBagConstraints();
        GridBagConstraints gbccds = new GridBagConstraints();
        gbcft.ipadx = 250;
        gbcft.ipady = 220;
        gbcft.insets = new Insets(0,0,-20,0);
        gbccds.gridy = 1;
        gbccds.ipadx = 730;
        gbccds.ipady = 330;
        gbccds.insets = new Insets(20,0,0,0);
        
        fotopanel.setBackground(Color.LIGHT_GRAY);
        fotopanel.setBorder(BorderFactory.createTitledBorder(blacklineft,"Foto"));
        fotopanel.setLayout(null);
        
        cadspanel.setBackground(Color.LIGHT_GRAY);
        cadspanel.setBorder(BorderFactory.createTitledBorder(blackline,"Dados"));
        cadspanel.setLayout(null);
        
        
        nome.setBounds(15,25,50,20);
        txtnome.setBounds(80,25,300,20);
        txtdatanasc.setBounds(160,60,80,20);
        data.setBounds(10,60,200,20);
        comboestciv.setBounds(100,100,100,20);
        lblest.setBounds(10,85,100,40);
        lblprof.setBounds(10,130,80,30);
        txtprof.setBounds(90,135,150,20);
        lblnac.setBounds(10,170,94,20);
        txtnac.setBounds(110,170,150,20);
        lblnat.setBounds(10,205,90,20);
        txtnat.setBounds(100,205,150,20);
        lblleresc.setBounds(10,235,150,20);
        comboleresc.setBounds(150,235,80,20);
        lblfil.setBounds(10,264,55,20);
        txtfil.setBounds(65,264,180,20);
        dataadm.setBounds(10,300,130,20);
        txtdataadm.setBounds(150,300,80,20);
        lblres.setBounds(400,26,80,20);
        txtres.setBounds(480,26,180,20);
        lblcart.setBounds(400,70,138,20);
        txtcart.setBounds(540,70,150,20);
        lblmar.setBounds(400,110,60,20);
        txtmar.setBounds(460,110,180,20);
        lblinps.setBounds(400,150,40,20);
        txtinps.setBounds(450,150,180,20);
        btnalterar.setBounds(520,290,100,30);
        lblImagem.setBounds(0,0,250,220);
        lblobg.setBounds(400,200,150,50);
        voltar.setBounds(640,290,80,30); 
        deletar.setBounds(530,290,80,30);
        lblaltid.setBounds(400,180,30,20);
        txtaltid.setBounds(430,180,70,20);
        procass.setBounds(400,290,100,30);
        procass.addActionListener(this);
        btnalterar.addActionListener(this);
        voltar.addActionListener(this);
        
        lblprof.setFont(f);
        nome.setFont(f);
        data.setFont(f);
        lblest.setFont(f);
        lblnac.setFont(f);
        lblnat.setFont(f);
        lblleresc.setFont(f);
        lblfil.setFont(f);
        dataadm.setFont(f);
        lblres.setFont(f);
        lblcart.setFont(f);
        lblmar.setFont(f);
        lblinps.setFont(f);
        lblobg.setFont(f);
        lblaltid.setFont(f);
        
        cadspanel.add(nome);
        cadspanel.add(txtnome);
        cadspanel.add(txtdata);
        cadspanel.add(txtdatanasc);
        cadspanel.add(data);
        cadspanel.add(comboestciv);
        cadspanel.add(lblest);
        cadspanel.add(lblprof);
        cadspanel.add(txtprof);
        cadspanel.add(lblnac);
        cadspanel.add(txtnac);
        cadspanel.add(lblnat);
        cadspanel.add(txtnat);
        cadspanel.add(comboleresc);
        cadspanel.add(lblleresc);
        cadspanel.add(lblfil);
        cadspanel.add(txtfil);
        cadspanel.add(dataadm);
        cadspanel.add(txtdataadm);
        cadspanel.add(lblres);
        cadspanel.add(txtres);
        cadspanel.add(lblcart);
        cadspanel.add(txtcart);
        cadspanel.add(lblmar);
        cadspanel.add(lblinps);
        cadspanel.add(txtmar);
        cadspanel.add(txtinps);
        cadspanel.add(btnalterar);
        cadspanel.add(lblobg);
        cadspanel.add(voltar);
        cadspanel.add(deletar);
        cadspanel.add(lblaltid);
        cadspanel.add(txtaltid);
        cadspanel.add(procass);
        fotopanel.add(lblImagem);
        
        bck.add(fotopanel,gbcft);
        bck.add(cadspanel,gbccds);
        add(bck);
        
        
        setTitle("Alterar Dados");
        setVisible(true);
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        AlterarDados altdados = new AlterarDados();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == voltar){
            VisualizarAssociados visas1 = new VisualizarAssociados();
            this.dispose();
        }  
        else if(ae.getSource() == btnalterar){
            alterar();
            VisualizarAssociados visas2 = new VisualizarAssociados();
            this.dispose();
            
        }
    }
}

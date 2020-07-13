package sindicatocadastro;

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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.sql.*;
import br.com.conexao.ModuloConexao;
import javax.swing.JOptionPane;

public class SindicatoCadastro extends JFrame implements ActionListener {
    Label logo = new Label("SINDICATO DOS ESTIVADORES");
    Label usua = new Label("Usuário");
    Label sen = new Label("Senha");
    Font f = new Font("SansSerif", Font.BOLD, 22);
    Font f2 = new Font("SansSerif", Font.BOLD, 18);
    JPanel titulo = new JPanel();
    JPanel back = new JPanel();
    JPanel areareq = new JPanel();
    Button login = new Button("Entrar");
    TextField txtlogin = new TextField();
    JPasswordField txtsenha = new JPasswordField();
    //------------Conexao com Banco de Dados-----------------/
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void entrar(){
        String sql = "select * from user where usuario=? and senha=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtlogin.getText());
            pst.setString(2,txtsenha.getText());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                Principal mn = new Principal();
                this.dispose();
                conexao.close();
            }else{
                JOptionPane.showMessageDialog(null,"Usuário e/ou Senha não cadastrados");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    public SindicatoCadastro(){
        conexao = ModuloConexao.conector();
        //------------Background------------------//
        back.setLayout(new GridBagLayout());
        GridBagConstraints gbctit = new GridBagConstraints();
        gbctit.ipadx = 40;
        gbctit.ipady = 150;
        gbctit.insets = new Insets(20,0,0,0);
        GridBagConstraints gbcreq = new GridBagConstraints();
        gbcreq.ipadx = 190;
        gbcreq.gridy = 1;
        gbcreq.insets = new Insets(50,10,10,10);
        back.setBackground(Color.LIGHT_GRAY);
        back.setSize(390,468);
        
        //----------------Configuração de Panels-------------------------//
        titulo.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titulo.setBackground(Color.LIGHT_GRAY);
        
        
        areareq.setBorder(BorderFactory.createLineBorder(Color.black,2));
        areareq.setBackground(Color.LIGHT_GRAY);
        areareq.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();
        GridBagConstraints gbc3 = new GridBagConstraints();
        c.ipadx = 100;
        c.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 100;
        gbc2.gridx = 2;
        gbc2.ipadx = 110;
        gbc2.ipady = 5;
        gbc2.gridx = 1;
        gbc3.gridx = 1;
        gbc3.ipadx = 100;
        gbc3.ipady = 5;
        gbc3.insets = new Insets(10,10,10,10);
        //--------------------Configuração de Componentes------------------//
        usua.setFont(f2);
        sen.setFont(f2);
        //-----------------------Colocar os componentes na tela-------------//
        logo.setFont(f);
        logo.setBounds(30,50,350,20);
        titulo.add(logo);
        back.add(titulo,gbctit);
        back.add(areareq,gbcreq);
        add(back);
        areareq.add(usua);
        areareq.add(txtlogin,c);
        areareq.add(sen,gbc);
        areareq.add(txtsenha,gbc2);
        areareq.add(login,gbc3);
        login.addActionListener(this);
        //Configurações da Janela em Geral-----------------------------------//
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,600);
        setResizable(false);
        
        
    }
    
    public static void main(String[] args) {
        SindicatoCadastro janela = new SindicatoCadastro();
    }
    
     @Override
    public void actionPerformed(ActionEvent ae) {
            entrar();
    }
}



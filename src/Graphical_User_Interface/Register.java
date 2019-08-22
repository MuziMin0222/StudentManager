package Graphical_User_Interface;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Register extends JFrame implements ActionListener{
	
	Font font = new Font("����_GB2312",Font.BOLD+Font.ITALIC,20);
	Font font1 = new Font("����_GB2312",Font.BOLD+Font.ITALIC,15);
	
	JTextField register_account;
	JTextField register_name;
	JTextField register_password;
	
	String account;
	String password;
	String connectDB = "jdbc:mysql://127.0.0.1:5726/lhm?useSSL=true";// ����Դ
	String JDriver = "com.mysql.jdbc.Driver";// SQL���ݿ�����
	Connection conR;
    Statement stmtR;
	
	JButton JB_sure = new JButton("ȷ��");
	JButton JB_reset = new JButton("����");
	
	String path = "D:/code/workspace_eclipse_2018/StudentManager/src/Graphical_User_Interface/choice.png";
	String path1 = "D:/code/workspace_eclipse_2018/StudentManager/src/Graphical_User_Interface/�ɹ�.png";
	
	JPanel jpl = new JPanel();
	JPanel jpl1 = new JPanel();
	JPanel jpl2 = new JPanel();
	JPanel jpl3 = new JPanel();
	
	public Register(String ac,String pw){
		account = ac;
		password = pw;
		try {
	        Class.forName(JDriver);
	        conR = DriverManager.getConnection(connectDB, account, password);
	        stmtR = conR.createStatement();
	    } catch (ClassNotFoundException | SQLException e) {
	    	dispose();
	    }
		this.setTitle("����Աע��");
		this.setSize(280,230);
		this.setLayout(new GridLayout(4,2,15,20));
		this.setLocationRelativeTo(null);
		
		ImageIcon bg = new ImageIcon(path);                        //����ͼƬ
		JLabel label = new JLabel(bg);
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		JPanel imgP = (JPanel)this.getContentPane();
		imgP.setOpaque(false);
		
		JLabel lba = new JLabel("�˺ţ�");							//�˺�
		register_account = new JTextField(10);						
		lba.setFont(font);	
		register_account.setFont(font1);
		jpl.add(lba);
		jpl.add(register_account);
		jpl.setOpaque(false);
		this.add(jpl);
		
		JLabel lbn = new JLabel("������");							//�û���
		register_name = new JTextField(10);						
		lbn.setFont(font);	
		register_name.setFont(font1);
		jpl1.add(lbn);
		jpl1.add(register_name);
		jpl1.setOpaque(false);
		this.add(jpl1);
		
		JLabel lbp = new JLabel("���룺");							//����
		register_password = new JTextField(10);						
		lbp.setFont(font);
		register_password.setFont(font1);
		jpl2.add(lbp);
		jpl2.add(register_password);
		jpl2.setOpaque(false);
		this.add(jpl2);
		
		JB_sure.setActionCommand("sure");
		JB_sure.addActionListener(this);
		jpl3.add(JB_sure);
		
		JB_reset.setActionCommand("reset");
		JB_reset.addActionListener(this);
		jpl3.add(JB_reset);
		
		jpl3.setOpaque(false);
		this.add(jpl3);
		
        this.setVisible(true);
		this.setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("sure")){
			String user = register_account.getText();
			String name = register_name.getText();
			String pwd = register_password.getText();
			
			if(!user.equals("") && !name.equals("") && !pwd.equals("")){
				try{
					ResultSet rs = stmtR.executeQuery("select * from My_Adminstrator");
					while (rs.next()) {
						if(rs.getString("Account").equals(user)){
							JOptionPane.showMessageDialog(null,"���û��Ѿ�����");
							stmtR.close();
					        conR.close();
							dispose();
							break;
			            }
					}
					//http://blog.csdn.net/a497785609/article/details/47686659
					String str = "create login "+user+" with password = '"+pwd
								 +"', default_database=Databaseproject";
					
					String str1 = "create user "+user+" for login "+ user+" with default_schema=Databaseproject";
					
					String str2 = "exec sp_addrolemember 'db_owner', '"+user+"'";
					
					String sql = "insert into My_Adminstrator values('"+user+"','"+name+"','"+pwd+"')";
					
					stmtR.executeUpdate(str);
					stmtR.executeUpdate(str1);
					stmtR.executeUpdate(str2);
					stmtR.executeUpdate(sql);
					
					JOptionPane.showMessageDialog(null,"ע��ɹ�");
					stmtR.close();
			        conR.close();
			        dispose();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"�û�Ȩ�޲���");
					try {
						stmtR.close();
						conR.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			        dispose();
				}
			}else{
				JOptionPane.showMessageDialog(null,"��Ϣ����Ϊ��");
			}
		}
		
		if(e.getActionCommand().equals("reset")){
			register_account.setText(null);
			register_name.setText(null);
			register_password.setText(null);
		}
	}
}

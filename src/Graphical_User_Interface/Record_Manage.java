package Graphical_User_Interface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Record_Manage extends JFrame implements ActionListener{
	String account;
	String password;
	String connectDB = "jdbc:mysql://127.0.0.1:5726/lhm?useSSL=true"; //����Դ
	String JDriver = "com.mysql.jdbc.Driver";	//SQL���ݿ�����
	Connection conR;
    Statement statement;
	
	Font font0 = new Font("����_GB2312",Font.BOLD+Font.ITALIC,40);
	Font fontarea = new Font("����_GB2312",Font.BOLD,25);
	Font fontjb = new Font("����_GB2312",Font.BOLD,15);
	Font fontjt = new Font("����_GB2312",Font.BOLD,20);
	
	String path = "D:/code/workspace_eclipse_2018/StudentManager/src/Graphical_User_Interface/11.jpg";
	String path1 = "D:/code/workspace_eclipse_2018/StudentManager/src/Graphical_User_Interface/��ҳ.png";
	
	JButton JB_back = new JButton("����");
	JButton JB_add = new JButton("¼��");
	JButton JB_change = new JButton("�޸�");
	JButton JB_search = new JButton("��ѯ");
	JButton JB_display = new JButton("���");

	JTextField jtid;
	JTextField jtname;
	JRadioButton jrbsexm;
	JRadioButton jrbsexw;
	JTextField jtage;
	JTextField jtgrade;
	JTextField jtclass;
	JTextField jtaward_nametext;	//��������
	JTextField jtaward_leveltext;	//�����ȼ�
	JTextField jtpunishment_nametext;	//��������
	JTextField jtpunishment_leveltext;	//���ֵȼ�
	JTextArea jthomeinfor_namearea;
	JTextField jtphysical_eyetext;
	JTextField jtphysical_staturetext;
	JTextField jtphysical_weighttext;
	JTextField jtphysical_feitext;
	JTextField jtresume_backgroundtext;
	JTextField jtresume_skilltext;
	JTextArea jtresume_area;
	
	JScrollPane jsinfor;
	JTextArea jtinfor;
	
	JPanel jpl = new JPanel(null);
	
	//�������
	int snum;	String strnum;
	String sname;
	String ssex;
	int sage; String strage;
	String sgrade;
	String sclass;
	//�������
	String saward_name;
	String saward_level;
	String spunishment_name;
	String spunishment_level;
	//��ͥ���
	String shomesituation;
	//������
	String sphysical_eye;
	String sphysical_stature;
	String sphysical_weight;
	String sphysical_fei;
	//������Ϣ
	String sbackground;
	String sskill;
	String sprojectexperience;
	
	boolean flag_add = false;
	boolean flag_change = false;
	boolean flag_search = false;
	boolean flag_display = false;
	
	public Record_Manage(String ac, String pd){
		account = ac;
		password = pd;
		
		try {
	        Class.forName(JDriver);
	        conR = DriverManager.getConnection(connectDB, account, password);
	        statement = conR.createStatement();
	    } catch (ClassNotFoundException | SQLException e) {
	    	dispose();
	    }
		
		this.setTitle("ѧ������ϵͳ");	
		this.setSize(516, 730);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		this.setLocationRelativeTo(null);
		
		ImageIcon bg = new ImageIcon(path);                            //����ͼƬ
		JLabel label = new JLabel(bg);
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		JPanel imgP = (JPanel)this.getContentPane();
		imgP.setOpaque(false);
		
		JLabel lb0 = new JLabel("ѧ����������");                           //����
		lb0.setFont(font0);
		this.add(lb0);
		
		JButton jbt = new JButton();							 		//����
		jbt.setPreferredSize(new Dimension(400,1));  
		jbt.setBorderPainted(false);
		jbt.setContentAreaFilled(false);
		this.add(jbt);
		
		JB_add.setBorderPainted(false);									//¼��
		JB_add.setFont(fontjb);
		JB_add.setActionCommand("jbadd");
		JB_add.addActionListener(this);
		this.add(JB_add);
		
		JB_change.setBorderPainted(false);								//�޸�
		JB_change.setActionCommand("jbchange");
		JB_change.addActionListener(this);
		JB_change.setFont(fontjb);
		this.add(JB_change);
		
		JB_search.setBorderPainted(false);								//��ѯ
		JB_search.setActionCommand("jbsearch");
		JB_search.addActionListener(this);
		JB_search.setFont(fontjb);
		this.add(JB_search);
		
		JB_display.setBorderPainted(false);								//���
		JB_display.setActionCommand("jbdisplay");
		JB_display.addActionListener(this);
		JB_display.setFont(fontjb);
		this.add(JB_display);
		
		JB_back.setBorderPainted(false);								//����
		JB_back.setActionCommand("jbback");
		JB_back.addActionListener(this);
		JB_back.setFont(fontjb);
		this.add(JB_back);
		
		JButton jbt1 = new JButton();							 		//����
		jbt1.setPreferredSize(new Dimension(400,0));  
		jbt1.setBorderPainted(false);
		jbt1.setContentAreaFilled(false);
		this.add(jbt1);
		
		this.setVisible(true);
		this.setResizable(false);
	}
//***********************************************************************
	public JPanel createAdd(){											//¼�����
		jpl.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		jpl.setPreferredSize(new Dimension(516, 700));
		
		JLabel lbbasic = new JLabel("������Ϣ��");							//������Ϣ
		lbbasic.setFont(fontjt);
		jpl.add(lbbasic);
		
		JButton jbt = new JButton();							 		//����
		jbt.setPreferredSize(new Dimension(400,0));  
		jbt.setBorderPainted(false);
		jbt.setContentAreaFilled(false);
		jpl.add(jbt);
		
		JLabel lbid = new JLabel("ѧ�ţ�");   							//ѧ��
		lbid.setFont(fontjt);
		jtid = new JTextField(5);
		jtid.setFont(fontjt);
		jpl.add(lbid);
		jpl.add(jtid);
		
		JLabel lbname = new JLabel("������");								//����
		lbname.setFont(fontjt);
		jtname = new JTextField(5);
		jtname.setFont(fontjt);
		jpl.add(lbname);
		jpl.add(jtname);
		
		JLabel lbsex = new JLabel("�Ա�");   							//�Ա�
		lbsex.setFont(fontjt);
		jpl.add(lbsex);
		
		ButtonGroup bgsex = new ButtonGroup();
		
		jrbsexm = new JRadioButton("��",false);
		jrbsexm.setFont(fontjt);
		jrbsexm.setContentAreaFilled(false);
		jrbsexm.setActionCommand("man");
		jrbsexm.addActionListener(this);
		jpl.add(jrbsexm);
		
		jrbsexw = new JRadioButton("Ů",false);
		jrbsexw.setFont(fontjt);
		jrbsexw.setContentAreaFilled(false);
		jrbsexw.setActionCommand("woman");
		jrbsexw.addActionListener(this);
		jpl.add(jrbsexw);
		
		bgsex.add(jrbsexw);
		bgsex.add(jrbsexm);
		
		JLabel lbage = new JLabel("���䣺");									//����
		lbage.setFont(fontjt);
		jtage = new JTextField(5);
		jtage.setFont(fontjt);
		jpl.add(lbage);
		jpl.add(jtage);
		
		JLabel lbgrade = new JLabel("�꼶��");   								//�꼶
		lbgrade.setFont(fontjt);
		jtgrade = new JTextField(5);
		jtgrade.setFont(fontjt);
		jpl.add(lbgrade);
		jpl.add(jtgrade);
			
		JLabel lbclass = new JLabel("�༶��");								//�༶
		lbclass.setFont(fontjt);
		jtclass = new JTextField(5);
		jtclass.setFont(fontjt);
		jpl.add(lbclass);
		jpl.add(jtclass);
		
		JLabel lbawward = new JLabel("���������");								//�������
		lbawward.setFont(fontjt);
		jpl.add(lbawward);
		
		ButtonGroup bgaward = new ButtonGroup();
		
		JRadioButton jrbawardfalse = new JRadioButton("��",false);
		jrbawardfalse.setFont(fontjt);
		jrbawardfalse.setContentAreaFilled(false);
		jrbawardfalse.setActionCommand("awardfalse");
		jrbawardfalse.addActionListener(this);
		jpl.add(jrbawardfalse);
		
		JRadioButton jrbawardtrue = new JRadioButton("��",false);
		jrbawardtrue.setFont(fontjt);
		jrbawardtrue.setContentAreaFilled(false);
		jrbawardtrue.setActionCommand("awardtrue");
		jrbawardtrue.addActionListener(this);
		jpl.add(jrbawardtrue);
		
		bgaward.add(jrbawardfalse);
		bgaward.add(jrbawardtrue);
		
		JButton jbt1 = new JButton();							 			//����
		jbt1.setPreferredSize(new Dimension(400,0));  
		jbt1.setBorderPainted(false);
		jbt1.setContentAreaFilled(false);
		jpl.add(jbt1);
		
		JLabel lbaward_name = new JLabel("�������ƣ�");							//��������
		lbaward_name.setFont(fontjt);
		jtaward_nametext = new JTextField(5);
		jtaward_nametext.setFont(fontjt);
		jpl.add(lbaward_name);
		jpl.add(jtaward_nametext);
		
		JLabel lbaward_level = new JLabel("�����ȼ���");						//�����ȼ�
		lbaward_level.setFont(fontjt);
		jtaward_leveltext = new JTextField(5);
		jtaward_leveltext.setFont(fontjt);
		jpl.add(lbaward_level);
		jpl.add(jtaward_leveltext);
		
		JLabel lbpunishment = new JLabel("���������");							//�������
		lbpunishment.setFont(fontjt);
		jpl.add(lbpunishment);
		
		ButtonGroup bgpunishment = new ButtonGroup();
		
		JRadioButton jrbpunishmentfalse = new JRadioButton("��",false);
		jrbpunishmentfalse.setFont(fontjt);
		jrbpunishmentfalse.setContentAreaFilled(false);
		jrbpunishmentfalse.setActionCommand("punishmentfalse");
		jrbpunishmentfalse.addActionListener(this);
		jpl.add(jrbpunishmentfalse);
		
		JRadioButton jrbpunishmenttrue = new JRadioButton("��",false);
		jrbpunishmenttrue.setFont(fontjt);
		jrbpunishmenttrue.setContentAreaFilled(false);
		jrbpunishmenttrue.setActionCommand("punishmenttrue");
		jrbpunishmenttrue.addActionListener(this);
		jpl.add(jrbpunishmenttrue);
		
		bgpunishment.add(jrbpunishmentfalse);
		bgpunishment.add(jrbpunishmenttrue);
		
		JButton jbt2 = new JButton();							 			//����
		jbt2.setPreferredSize(new Dimension(400,0));  
		jbt2.setBorderPainted(false);
		jbt2.setContentAreaFilled(false);
		jpl.add(jbt2);
		
		JLabel lbpunishment_name = new JLabel("�������ͣ�");	
		lbpunishment_name.setFont(fontjt);
		jtpunishment_nametext = new JTextField(5);
		jtpunishment_nametext.setFont(fontjt);
		jpl.add(lbpunishment_name);
		jpl.add(jtpunishment_nametext);
		
		JLabel lbpunishment_level = new JLabel("���ֵȼ���");
		lbpunishment_level.setFont(fontjt);
		jtpunishment_leveltext = new JTextField(5);
		jtpunishment_leveltext.setFont(fontjt);
		jpl.add(lbpunishment_level);
		jpl.add(jtpunishment_leveltext);
		
		JLabel lbhomeinfor_name = new JLabel("��ͥ�����");
		lbhomeinfor_name.setFont(fontjt);
		jthomeinfor_namearea = new JTextArea(1,10);
		jthomeinfor_namearea.setText("��ͥסַ��\n��ͥ��Ա��");
		jpl.add(lbhomeinfor_name);
		jpl.add(jthomeinfor_namearea);
		
		JButton jbt5 = new JButton();							 			//����
		jbt5.setPreferredSize(new Dimension(400,0));  
		jbt5.setBorderPainted(false);
		jbt5.setContentAreaFilled(false);
		jpl.add(jbt5);
		
		JLabel lbphysical = new JLabel("��������");
		lbphysical.setFont(fontjt);
		jpl.add(lbphysical);
		
		JButton jbt3 = new JButton();							 			//����
		jbt3.setPreferredSize(new Dimension(400,0));  
		jbt3.setBorderPainted(false);
		jbt3.setContentAreaFilled(false);
		jpl.add(jbt3);
		
		JLabel lbphysical_eye = new JLabel("������");
		lbphysical_eye.setFont(fontjt);
		jtphysical_eyetext = new JTextField(5);
		jtphysical_eyetext.setFont(fontjt);
		jpl.add(lbphysical_eye);
		jpl.add(jtphysical_eyetext);
		
		JLabel lbphysical_stature = new JLabel("��ߣ�");
		lbphysical_stature.setFont(fontjt);
		jtphysical_staturetext = new JTextField(5);
		jtphysical_staturetext.setFont(fontjt);
		jpl.add(lbphysical_stature);
		jpl.add(jtphysical_staturetext);
		
		JLabel lbphysical_weight = new JLabel("���أ�");
		lbphysical_weight.setFont(fontjt);
		jtphysical_weighttext = new JTextField(5);
		jtphysical_weighttext.setFont(fontjt);
		jpl.add(lbphysical_weight);
		jpl.add(jtphysical_weighttext);
		
		JLabel lbphysical_fei = new JLabel("�λ�����");
		lbphysical_fei.setFont(fontjt);
		jtphysical_feitext = new JTextField(5);
		jtphysical_feitext.setFont(fontjt);
		jpl.add(lbphysical_fei);
		jpl.add(jtphysical_feitext);
		
		JLabel lbresume = new JLabel("������Ϣ��");								//������Ϣ
		lbresume.setFont(fontjt);
		jpl.add(lbresume);
		
		JButton jbt4 = new JButton();							 			//����
		jbt4.setPreferredSize(new Dimension(400,0));  
		jbt4.setBorderPainted(false);
		jbt4.setContentAreaFilled(false);
		jpl.add(jbt4);
		
		JLabel lbresume_background = new JLabel("����������");
		lbresume_background.setFont(fontjt);
		jtresume_backgroundtext = new JTextField(5);
		jtresume_backgroundtext.setFont(fontjt);
		jpl.add(lbresume_background);
		jpl.add(jtresume_backgroundtext);
		
		JLabel lbresume_skill = new JLabel("ְҵ���ܣ�");
		lbresume_skill.setFont(fontjt);
		jtresume_skilltext = new JTextField(5);
		jtresume_skilltext.setFont(fontjt);
		jpl.add(lbresume_skill);
		jpl.add(jtresume_skilltext);
		
		JLabel lbresume_experience = new JLabel("��Ŀ������");
		lbresume_experience.setFont(fontjt);
		jtresume_area = new JTextArea(1,10);
		jtresume_area.setText("��Ŀ���ƣ�\n���εĽ�ɫ��");
		jpl.add(lbresume_experience);
		jpl.add(jtresume_area);
		
		if(flag_search||flag_display){
		}else{
			JButton jbsure = new JButton("ȷ��");
			jbsure.setFont(fontjb);
			jbsure.setActionCommand("jblogsure");
			jbsure.addActionListener(this);
			jpl.add(jbsure);
		}
		
		return jpl;
	}
	
	public JPanel createChange(){											//�޸Ľ���
		jpl = createAdd();
		jtid.setText(strnum);
		jtname.setText(sname);
		if(ssex.equals("��")){
			jrbsexm.setSelected(true);
		}else{
			jrbsexw.setSelected(true);
		}
		jtage.setText(strage);
		jtgrade.setText(sgrade);
		jtclass.setText(sclass);
		jtaward_nametext.setText(saward_name);
		jtaward_leveltext.setText(saward_level);
		jtpunishment_nametext.setText(spunishment_name);
		jtpunishment_leveltext.setText(spunishment_level);
		jthomeinfor_namearea.setText(shomesituation);
		jtphysical_eyetext.setText(sphysical_eye);
		jtphysical_staturetext.setText(sphysical_stature);
		jtphysical_weighttext.setText(sphysical_weight);
		jtphysical_feitext.setText(sphysical_fei);
		jtresume_backgroundtext.setText(sbackground);
		jtresume_skilltext.setText(sskill);
		jtresume_area.setText(sprojectexperience);
		
		return jpl;
	}
	
	public JPanel createSearch(){											//��ѯ����
		jpl = createAdd();
		jtid.setText(strnum);
		jtname.setText(sname);
		if(ssex.equals("��")){
			jrbsexm.setSelected(true);
		}else{
			jrbsexw.setSelected(true);
		}
		jtage.setText(strage);
		jtgrade.setText(sgrade);
		jtclass.setText(sclass);
		jtaward_nametext.setText(saward_name);
		jtaward_leveltext.setText(saward_level);
		jtpunishment_nametext.setText(spunishment_name);
		jtpunishment_leveltext.setText(spunishment_level);
		jthomeinfor_namearea.setText(shomesituation);
		jtphysical_eyetext.setText(sphysical_eye);
		jtphysical_staturetext.setText(sphysical_stature);
		jtphysical_weighttext.setText(sphysical_weight);
		jtphysical_feitext.setText(sphysical_fei);
		jtresume_backgroundtext.setText(sbackground);
		jtresume_skilltext.setText(sskill);
		jtresume_area.setText(sprojectexperience);
		
		return jpl;
	}
	
	public JPanel createDisplay(){											//�������
		jpl.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		jpl.setPreferredSize(new Dimension(516, 700));
		
		JLabel lbinfor = new JLabel("ѧ��������ȫ����Ϣ��");
		lbinfor.setFont(fontarea);
		jpl.add(lbinfor);
		
		JButton jbt = new JButton();							 		//����
		jbt.setPreferredSize(new Dimension(400,0));  
		jbt.setBorderPainted(false);
		jbt.setContentAreaFilled(false);
		jpl.add(jbt);
		
		jtinfor = new JTextArea(26,40);
		jtinfor.setFont(fontjb);
		jtinfor.setText("ѧ����Ϣ���£�\n");
		jtinfor.setCaretPosition(0);
		
		jsinfor = new JScrollPane(jtinfor);
		
		jpl.add(jsinfor);
		
		return jpl;
	}
//***********************************************************************	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("jbadd")){							//¼�����
			flag_add = true;
			flag_change = false;
			flag_search = false;
			flag_display = false;
			
			jpl.removeAll();
			jpl.repaint();
			jpl = createAdd();
			jpl.setOpaque(false);
			this.add(jpl);
			this.validate();
		}
		
		if(e.getActionCommand().equals("jbchange")){						//�޸Ĳ���
			flag_add = false;
			flag_change = true;
			flag_search = false;
			flag_display = false;
			
			strnum = JOptionPane.showInputDialog("����Ҫ���µ�ѧ�ţ�");
			boolean flag = false;
			if(strnum.equals("")){
				JOptionPane.showMessageDialog(null,"ѧ�Ų���Ϊ�գ�����");
			}else{
				snum = Integer.valueOf(strnum).intValue();
				try {
					ResultSet result_snum = statement.executeQuery("select * from Record_Manage");
					while (result_snum.next()) {
						int num = Integer.valueOf(result_snum.getString("snum")).intValue();
						if(num == snum){
							flag = true;
							break;
			            }
					}
					if(flag){
						ResultSet result_snum_infor = statement.executeQuery("select * "
													+"from Record_Manage where snum = '"+snum+"'");
						while(result_snum_infor.next()){
							sname = result_snum_infor.getString("sname");
							ssex = result_snum_infor.getString("ssex");
							strage = result_snum_infor.getString("sage");
							sage = Integer.valueOf(strage).intValue();
							sgrade = result_snum_infor.getString("sgrade");
							sclass = result_snum_infor.getString("sclass");
							saward_name = result_snum_infor.getString("saward_name");
							saward_level = result_snum_infor.getString("saward_level");
							spunishment_name = result_snum_infor.getString("spunishment_name");
							spunishment_level = result_snum_infor.getString("spunishment_level");
							shomesituation = result_snum_infor.getString("shomesituation");
							sphysical_eye = result_snum_infor.getString("sphysical_eye");
							sphysical_stature = result_snum_infor.getString("sphysical_stature");
							sphysical_weight = result_snum_infor.getString("sphysical_weight");
							sphysical_fei = result_snum_infor.getString("sphysical_fei");
							sbackground = result_snum_infor.getString("sbackground");
							sskill = result_snum_infor.getString("sskill");
							sprojectexperience = result_snum_infor.getString("sprojectexperience");
						}
						jpl.removeAll();
						jpl.repaint();
						jpl = createChange();
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}else{
						JOptionPane.showMessageDialog(null,"ѧ��������Ϣ�����ڣ��봴��");
						jpl.removeAll();
						jpl.repaint();
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"���ݿ����ʧ�ܣ�����");
				}
			}
		}
		
		if(e.getActionCommand().equals("jbsearch")){						//��ѯ����
			flag_add = false;
			flag_change = false;
			flag_search = true;
			flag_display = false;
			
			boolean flag = false;
			strnum = JOptionPane.showInputDialog("����Ҫ��ѯ��ѧ�ţ�");
			if(strnum.equals("")){
				JOptionPane.showMessageDialog(null,"ѧ�Ų���Ϊ�գ�����");
			}else{
				snum = Integer.valueOf(strnum).intValue();
				try {
					ResultSet result_snum = statement.executeQuery("select * from Record_Manage");
					while (result_snum.next()) {
						int num = Integer.valueOf(result_snum.getString("snum")).intValue();
						if(num == snum){
							flag = true;
							break;
			            }
					}
					if(flag){
						ResultSet result_snum_infor = statement.executeQuery("select * "
													+"from Record_Manage where snum = '"+snum+"'");
						while(result_snum_infor.next()){
							sname = result_snum_infor.getString("sname");
							ssex = result_snum_infor.getString("ssex");
							strage = result_snum_infor.getString("sage");
							sage = Integer.valueOf(strage).intValue();
							sgrade = result_snum_infor.getString("sgrade");
							sclass = result_snum_infor.getString("sclass");
							saward_name = result_snum_infor.getString("saward_name");
							saward_level = result_snum_infor.getString("saward_level");
							spunishment_name = result_snum_infor.getString("spunishment_name");
							spunishment_level = result_snum_infor.getString("spunishment_level");
							shomesituation = result_snum_infor.getString("shomesituation");
							sphysical_eye = result_snum_infor.getString("sphysical_eye");
							sphysical_stature = result_snum_infor.getString("sphysical_stature");
							sphysical_weight = result_snum_infor.getString("sphysical_weight");
							sphysical_fei = result_snum_infor.getString("sphysical_fei");
							sbackground = result_snum_infor.getString("sbackground");
							sskill = result_snum_infor.getString("sskill");
							sprojectexperience = result_snum_infor.getString("sprojectexperience");
						}
						jpl.removeAll();
						jpl.repaint();
						jpl = createSearch();
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}else{
						JOptionPane.showMessageDialog(null,"ѧ��������Ϣ�����ڣ��봴��");
						jpl.removeAll();
						jpl.repaint();
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"���ݿ����ʧ�ܣ�����");
				}
			}
		}
		
		if(e.getActionCommand().equals("jbdisplay")){						//�������
			flag_add = false;
			flag_change = false;
			flag_search = false;
			flag_display = true;
			
			boolean flag = false;
			try {
				ResultSet result_infor_exist = statement.executeQuery("select * from Record_Manage");
				while(result_infor_exist.next()){
					flag = true;
					break;
				}
				if(flag){
					jpl.removeAll();
					jpl.repaint();
					jpl = createDisplay();
					ResultSet result_infor = statement.executeQuery("select * from Record_Manage");
					while(result_infor.next()){
						strnum = result_infor.getString("snum");
						sname = result_infor.getString("sname");
						ssex = result_infor.getString("ssex");
						strage = result_infor.getString("sage");
						sage = Integer.valueOf(strage).intValue();
						sgrade = result_infor.getString("sgrade");
						sclass = result_infor.getString("sclass");
						saward_name = result_infor.getString("saward_name");
						saward_level = result_infor.getString("saward_level");
						spunishment_name = result_infor.getString("spunishment_name");
						spunishment_level = result_infor.getString("spunishment_level");
						shomesituation = result_infor.getString("shomesituation");
						sphysical_eye = result_infor.getString("sphysical_eye");
						sphysical_stature = result_infor.getString("sphysical_stature");
						sphysical_weight = result_infor.getString("sphysical_weight");
						sphysical_fei = result_infor.getString("sphysical_fei");
						sbackground = result_infor.getString("sbackground");
						sskill = result_infor.getString("sskill");
						sprojectexperience = result_infor.getString("sprojectexperience");
						jtinfor.append("ѧ�ţ�"+strnum+"\n");
						jtinfor.append("������"+sname+"\n");
						jtinfor.append("�Ա�"+ssex+"\n");
						jtinfor.append("���䣺"+sage+"\n");
						jtinfor.append("�꼶��"+sgrade+"\n");
						jtinfor.append("�༶��"+sclass+"\n");
						jtinfor.append("�������ƣ�"+saward_name+"\n");
						jtinfor.append("�����ȼ���"+saward_level+"\n");
						jtinfor.append("�������ƣ�"+spunishment_name+"\n");
						jtinfor.append("���ֵȼ���"+spunishment_level+"\n");
						jtinfor.append("��ͥ�����"+shomesituation+"\n");
						jtinfor.append("������"+sphysical_eye+"\n");
						jtinfor.append("��ߣ�"+sphysical_stature+"\n");
						jtinfor.append("���أ�"+sphysical_weight+"\n");
						jtinfor.append("�λ�����"+sphysical_fei+"\n");
						jtinfor.append("����������"+sbackground+"\n");
						jtinfor.append("ְҵ���ܣ�"+sskill+"\n");
						jtinfor.append("��Ŀ������"+sprojectexperience+'\n'+"*****"
										+ "************************"+'\n');
					}
					jpl.setOpaque(false);
					this.add(jpl);
					this.validate();
				}else{
					JOptionPane.showMessageDialog(null,"ѧ����������û����Ϣ������");
					jpl.removeAll();
					jpl.repaint();
					jpl.setOpaque(false);
					this.add(jpl);
					this.validate();
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,"���ݿ����ʧ�ܣ�����");
			}
		}
		
		if(e.getActionCommand().equals("jbback")){							//���ز���
			try {
				statement.close();
				conR.close();
				dispose();
			} catch (SQLException e1) {
			}
			dispose();	
		}
		
		if(e.getActionCommand().equals("man")){								//�õ��Ա�
			ssex = "��";
		}
		
		if(e.getActionCommand().equals("woman")){							//�õ��Ա�
			ssex = "Ů";
		}
		
		if(e.getActionCommand().equals("awardfalse")){						//�����������
			jtaward_nametext.setText("��");
			jtaward_leveltext.setText("��");
		}
		
		if(e.getActionCommand().equals("awardtrue")){						//�����������
			jtaward_nametext.setText("");
			jtaward_leveltext.setText("");
		}
		
		if(e.getActionCommand().equals("punishmentfalse")){					//�����������
			jtpunishment_nametext.setText("��");
			jtpunishment_leveltext.setText("��");
		}
		
		if(e.getActionCommand().equals("punishmenttrue")){					//�����������
			jtpunishment_nametext.setText("");
			jtpunishment_leveltext.setText("");
		}
		
		if(e.getActionCommand().equals("jblogsure")){						//ȷ����ť
			boolean flag_exist = true;
			if(jtid.getText().equals("")){
				JOptionPane.showMessageDialog(null,"ѧ�Ų���Ϊ�գ�����");
			}else{
				snum = Integer.valueOf(jtid.getText()).intValue();
				try {
					if(flag_add){
						ResultSet result_snum = statement.executeQuery("select snum from Record_Manage");
						while (result_snum.next()) {
							int num = Integer.valueOf(result_snum.getString("snum")).intValue();
							if(num == snum){
								JOptionPane.showMessageDialog(null,"��ѧ��������Ϣ�Ѵ���");
								flag_exist = false;
								break;
				            }
						}
					}
					if(flag_change){
						flag_exist = true;
					}
					if(flag_exist){
						if(jtage.getText().equals("")){
							JOptionPane.showMessageDialog(null,"���䲻��Ϊ�գ�����");
						}else{
							//�������
							sname = jtname.getText();
							sage = Integer.valueOf(jtage.getText()).intValue();
							sgrade = jtgrade.getText();
							sclass = jtclass.getText();
							//�������
							saward_name = jtaward_nametext.getText();
							saward_level = jtaward_leveltext.getText();
							//�������
							spunishment_name = jtpunishment_nametext.getText();
							spunishment_level= jtpunishment_leveltext.getText();
							//��ͥ���
							shomesituation = jthomeinfor_namearea.getText();
							//������
							sphysical_eye = jtphysical_eyetext.getText();
							sphysical_stature = jtphysical_staturetext.getText();
							sphysical_weight = jtphysical_weighttext.getText();
							sphysical_fei = jtphysical_feitext.getText();
							//������Ϣ
							sbackground = jtresume_backgroundtext.getText();
							sskill = jtresume_skilltext.getText();
							sprojectexperience = jtresume_area.getText();
							
							if(flag_add){
								String insert = "insert into Record_Manage values('"+snum+"','"+sname+"','"+ssex+"','"
										+sage+"','"+sgrade+"','"+sclass+"','"+saward_name+"','"+saward_level+"','"
										+spunishment_name+"','"+spunishment_level+"','"+shomesituation+"','"+sphysical_eye
										+"','"+sphysical_stature+"','"+sphysical_weight+"','"+sphysical_fei+"','"
										+sbackground+"','"+sskill+"','"+sprojectexperience+"')";
								statement.executeUpdate(insert);
								JOptionPane.showMessageDialog(null,"¼��ɹ�������");
							}
							if(flag_change){
//								String delete = "delete from Record_Manage where snum = '"+snum+"'";
								String update = "update Record_Manage set snum = '"+snum+"',sname = '"
										+sname+"', ssex = '"+ssex+"', sage = '"+sage+"', sgrade = '"+sgrade+"', sclass = '"
										+sclass+"', saward_name = '"+saward_name+"', saward_level = '"+saward_level+"', spunishment_name = '"
										+spunishment_name+"', spunishment_level = '"+spunishment_level+"', shomesituation = '"
										+shomesituation+"', sphysical_eye = '"+sphysical_eye+"', sphysical_stature = '"
										+sphysical_stature+"', sphysical_weight = '"+sphysical_weight+"', sphysical_fei = '"
										+sphysical_fei+"', sbackground = '"+sbackground+"', sskill = '"+sskill+"', sprojectexperience = '"
										+sprojectexperience+"' where snum = '"+snum+"'";
								statement.executeUpdate(update);
								JOptionPane.showMessageDialog(null,"�޸ĳɹ�������");
							}
						}
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"���ݿ����ʧ�ܣ�����");
				}
			}
		}
	}
//	public static void main(String[] args){
//		new Record_Manage("YC","123");
//	}
}

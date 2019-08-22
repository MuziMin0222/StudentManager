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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Score_Manage extends JFrame implements ActionListener{
	String account;
	String password;
	String connectDB = "jdbc:mysql://127.0.0.1:5726/lhm?useSSL=true"; //����Դ
	String JDriver = "com.mysql.jdbc.Driver";	//SQL���ݿ�����
	Connection conR;
    Statement statement;
	
	Font font0 = new Font("����_GB2312",Font.BOLD+Font.ITALIC,40);
	Font fontjb = new Font("����_GB2312",Font.BOLD,15);
	Font fontjt = new Font("����_GB2312",Font.BOLD,20);
	Font fontarea = new Font("����_GB2312",Font.BOLD,25);
	
	String path = "D:/code/workspace_eclipse_2018/StudentManager/src/Graphical_User_Interface/����.png";
	Font font = new Font("����_GB2312",Font.BOLD+Font.ITALIC,20);
	
	JPanel jpl = new JPanel(null);
	
	JButton JB_back = new JButton("����");
	JButton JB_add = new JButton("¼��");
	JButton JB_change = new JButton("�޸�");
	JButton JB_search = new JButton("��ѯ");
	JButton JB_display = new JButton("���");
	
	JTextField jtid;
	JTextField jtchinese;
	JTextField jtmath;
	JTextField jtenglish;
	
	JTextField jthighmath;
	JTextField jtphysics;
	JTextField jthighenglish;
	
	JTextField jtalgorithm;
	JTextField jtdatastructure;
	JTextField jtdatabase;
	
	int snum; String strnum;
	float chinese; 
	float math;	
	float english;	
	float highmath;	
	float physical;	
	float highenglish;
	float salgorithm;	
	float datastructure;	
	float sdatabase;	
	
	JScrollPane jsinfor;
	JTextArea jtinfor;
	
	boolean flag_add = false;
	boolean flag_change = false;
	boolean flag_search = false;
	boolean flag_display = false;
	
	public Score_Manage(String ac, String pw){
		account = ac;
		password = pw;
		try {
	        Class.forName(JDriver);
	        conR = DriverManager.getConnection(connectDB, account, password);
	        statement = conR.createStatement();
	    } catch (ClassNotFoundException | SQLException e) {
	    	dispose();
	    }
		
		this.setTitle("ѧ������ϵͳ");	
		this.setSize(516 , 530);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
		this.setLocationRelativeTo(null);
		
		ImageIcon bg = new ImageIcon(path);                            //����ͼƬ
		JLabel label = new JLabel(bg);
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		JPanel imgP = (JPanel)this.getContentPane();
		imgP.setOpaque(false);
		
		JLabel lb0 = new JLabel("ѧ���ɼ�����");                           //����
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
		
		jpl.setLayout(new FlowLayout(FlowLayout.LEFT,30,5));
		jpl.setPreferredSize(new Dimension(516, 530));
		
		//ѧ��***************************************
		JLabel lbid = new JLabel("ѧ��ѧ�ţ�");   
		lbid.setFont(fontjt);
		jpl.add(lbid);
		
		jtid = new JTextField(5);
		jtid.setFont(fontjt);
		jpl.add(jtid);
		
		//����
		JButton jbt8 = new JButton();							 		
		jbt8.setPreferredSize(new Dimension(400,0));  
		jbt8.setBorderPainted(false);
		jbt8.setContentAreaFilled(false);
		jpl.add(jbt8);
		
		//��ѧ�ɼ�***************************************
		JLabel lbenterscore = new JLabel("��ѧ�ɼ���              ");
		lbenterscore.setFont(fontjt);
		jpl.add(lbenterscore);
		
		//��ѧ�ڳɼ�***************************************
		JLabel lblastscore = new JLabel("      ��ѧ�ڳɼ���");   
		lblastscore.setFont(fontjt);
		jpl.add(lblastscore);
		
		//����
		JButton jbt = new JButton();							 		
		jbt.setPreferredSize(new Dimension(400,0));  
		jbt.setBorderPainted(false);
		jbt.setContentAreaFilled(false);
		jpl.add(jbt);
		
		//����
		JLabel lbchinesescore = new JLabel("���ĳɼ���");
		lbchinesescore.setFont(fontjt);
		jpl.add(lbchinesescore);
		
		jtchinese = new JTextField(5);
		jtchinese.setFont(fontjt);
		jpl.add(jtchinese);
		
		//����
		JLabel lbhighmath = new JLabel("�����ɼ���");
		lbhighmath.setFont(fontjt);
		jpl.add(lbhighmath);
		
		jthighmath = new JTextField(5);
		jthighmath.setFont(fontjt);
		jpl.add(jthighmath);
		
		//����
		JButton jbt1 = new JButton();							 		
		jbt1.setPreferredSize(new Dimension(400,0));  
		jbt1.setBorderPainted(false);
		jbt1.setContentAreaFilled(false);
		jpl.add(jbt1);
		
		//��ѧ
		JLabel lbmathscore = new JLabel("��ѧ�ɼ���");
		lbmathscore.setFont(fontjt);
		jpl.add(lbmathscore);
		
		jtmath = new JTextField(5);
		jtmath.setFont(fontjt);
		jpl.add(jtmath);
		
		//����
		JLabel lbphysics = new JLabel("����ɼ���");
		lbphysics.setFont(fontjt);
		jpl.add(lbphysics);
		
		jtphysics = new JTextField(5);
		jtphysics.setFont(fontjt);
		jpl.add(jtphysics);
		
		//����
		JButton jbt2 = new JButton();							 		
		jbt2.setPreferredSize(new Dimension(400,0));  
		jbt2.setBorderPainted(false);
		jbt2.setContentAreaFilled(false);
		jpl.add(jbt2);
		
		//Ӣ��
		JLabel lbenglishscore = new JLabel("Ӣ��ɼ���");
		lbenglishscore.setFont(fontjt);
		jpl.add(lbenglishscore);
		
		jtenglish = new JTextField(5);
		jtenglish.setFont(fontjt);
		jpl.add(jtenglish);
		
		//Ӣ��
		JLabel lbhighenligh = new JLabel("Ӣ��ɼ���");
		lbhighenligh.setFont(fontjt);
		jpl.add(lbhighenligh);
		
		jthighenglish = new JTextField(5);
		jthighenglish.setFont(fontjt);
		jpl.add(jthighenglish);
		
		//����
		JButton jbt3 = new JButton();							 		
		jbt3.setPreferredSize(new Dimension(400,0));  
		jbt3.setBorderPainted(false);
		jbt3.setContentAreaFilled(false);
		jpl.add(jbt3);
		
		//��ѧ�ڳɼ�***************************************
		JLabel lbnextscore = new JLabel("��ѧ�ڳɼ���");   
		lbnextscore.setFont(fontjt);
		jpl.add(lbnextscore);
		
		//����
		JButton jbt4 = new JButton();							 		
		jbt4.setPreferredSize(new Dimension(400,0));  
		jbt4.setBorderPainted(false);
		jbt4.setContentAreaFilled(false);
		jpl.add(jbt4);
		
		//�㷨
		JLabel lbalgorithm = new JLabel("�㷨�ɼ���");
		lbalgorithm.setFont(fontjt);
		jpl.add(lbalgorithm);
		
		jtalgorithm = new JTextField(5);
		jtalgorithm.setFont(fontjt);
		jpl.add(jtalgorithm);
		
		//����
		JButton jbt5 = new JButton();							 		
		jbt5.setPreferredSize(new Dimension(400,0));  
		jbt5.setBorderPainted(false);
		jbt5.setContentAreaFilled(false);
		jpl.add(jbt5);
		
		//���ݽṹ
		JLabel lbdatastructure = new JLabel("���ݽṹ��");
		lbdatastructure.setFont(fontjt);
		jpl.add(lbdatastructure);
		
		jtdatastructure = new JTextField(5);
		jtdatastructure.setFont(fontjt);
		jpl.add(jtdatastructure);
		
		
		JButton jbt7 = new JButton();							 		
		jbt7.setPreferredSize(new Dimension(45,1));  
		jbt7.setBorderPainted(false);
		jbt7.setContentAreaFilled(false);
		jpl.add(jbt7);
		
		//ȷ����ť
		if(flag_add || flag_change){
			JButton JB_sure = new JButton("ȷ��");
			JB_sure.setBorderPainted(false);								
			JB_sure.setActionCommand("jbsure");
			JB_sure.addActionListener(this);
			JB_sure.setFont(fontjb);
			jpl.add(JB_sure);
		}
				
		//����
		JButton jbt6 = new JButton();							 		
		jbt6.setPreferredSize(new Dimension(400,0));  
		jbt6.setBorderPainted(false);
		jbt6.setContentAreaFilled(false);
		jpl.add(jbt6);
		
		//���ݿ�
		JLabel lbdatabase = new JLabel("���ݿ��ۣ�");
		lbdatabase.setFont(fontjt);
		jpl.add(lbdatabase);
		
		jtdatabase = new JTextField(5);
		jtdatabase.setFont(fontjt);
		jpl.add(jtdatabase);
		
		return jpl;
	}
	
	public JPanel createChange(){										//�޸Ľ���
		jpl = createAdd();
		
		jtid.setText(strnum);
		jtchinese.setText(chinese+"");
		jtmath.setText(math+"");
		jtenglish.setText(english+"");
		jthighmath.setText(highmath+"");
		jtphysics.setText(physical+"");
		jthighenglish.setText(highenglish+"");
		jtalgorithm.setText(salgorithm+"");
		jtdatastructure.setText(datastructure+"");
		jtdatabase.setText(sdatabase+"");
		
		return jpl;
	}
	
	public JPanel createSearch(){										//��ѯ����
		jpl = createAdd();
		jtid.setText(strnum);
		jtchinese.setText(chinese+"");
		jtmath.setText(math+"");
		jtenglish.setText(english+"");
		jthighmath.setText(highmath+"");
		jtphysics.setText(physical+"");
		jthighenglish.setText(highenglish+"");
		jtalgorithm.setText(salgorithm+"");
		jtdatastructure.setText(datastructure+"");
		jtdatabase.setText(sdatabase+"");
		
		return jpl;
	}
	
	public JPanel createDisplay(){										//�������
		jpl.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		jpl.setPreferredSize(new Dimension(516, 530));
		
		JLabel lbinfor = new JLabel("ѧ���ɼ���ȫ����Ϣ��");
		lbinfor.setFont(fontarea);
		jpl.add(lbinfor);
		
		JButton jbt = new JButton();							 		//����
		jbt.setPreferredSize(new Dimension(400,0));  
		jbt.setBorderPainted(false);
		jbt.setContentAreaFilled(false);
		jpl.add(jbt);
		
		jtinfor = new JTextArea(15,40);
		jtinfor.setFont(fontjb);
		jtinfor.setText("ѧ���ɼ���Ϣ���£�\n");
		jtinfor.setCaretPosition(0);
		
		jsinfor = new JScrollPane(jtinfor);
		jpl.add(jsinfor);
		
		return jpl;
	}
//***********************************************************************	
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("jbadd")){						//¼�����
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
		
		if(e.getActionCommand().equals("jbchange")){					//�޸Ĳ���
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
					ResultSet result_snum_score = statement.executeQuery("select snum from Score_Manage");
					while (result_snum_score.next()) {
						int num = Integer.valueOf(result_snum_score.getString("snum")).intValue();
						if(num == snum){
							flag = true;
							break;
			            }
					}
					if(flag){
						ResultSet result_infor = statement.executeQuery("select * "
													+"from Score_Manage where snum = '"+snum+"'");
						while(result_infor.next()){
							chinese = result_infor.getFloat("chinese");
							math = result_infor.getFloat("math");
							english = result_infor.getFloat("english");
							
							highmath = result_infor.getFloat("highmath");
							physical = result_infor.getFloat("physical");
							highenglish = result_infor.getFloat("highenglish");
							
							salgorithm = result_infor.getFloat("salgorithm");
							datastructure = result_infor.getFloat("datastructure");
							sdatabase = result_infor.getFloat("sdatabase");
						}
						jpl.removeAll();
						jpl.repaint();
						
						jpl = createChange();
						
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}else{
						JOptionPane.showMessageDialog(null,"ѧ���ɼ���Ϣ�����ڣ��봴��");
						jpl.removeAll();
						jpl.repaint();
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"����ʧ�ܣ�����");
				}
			}
		}
		
		if(e.getActionCommand().equals("jbsearch")){					//���Ҳ���
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
					ResultSet result_snum = statement.executeQuery("select * from Score_Manage");
					while (result_snum.next()) {
						int num = Integer.valueOf(result_snum.getString("snum")).intValue();
						if(num == snum){
							flag = true;
							break;
			            }
					}
					if(flag){
						ResultSet result_infor = statement.executeQuery("select * "
													+"from Score_Manage where snum = '"+snum+"'");
						while(result_infor.next()){
							chinese = result_infor.getFloat("chinese");
							math = result_infor.getFloat("math");
							english = result_infor.getFloat("english");
							
							highmath = result_infor.getFloat("highmath");
							physical = result_infor.getFloat("physical");
							highenglish = result_infor.getFloat("highenglish");
							
							salgorithm = result_infor.getFloat("salgorithm");
							datastructure = result_infor.getFloat("datastructure");
							sdatabase = result_infor.getFloat("sdatabase");
						}
						jpl.removeAll();
						jpl.repaint();
						jpl = createSearch();
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}else{
						JOptionPane.showMessageDialog(null,"ѧ���ɼ���Ϣ�����ڣ��봴��");
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
		
		if(e.getActionCommand().equals("jbdisplay")){					//�������
			flag_add = false;
			flag_change = false;
			flag_search = false;
			flag_display = true;
			
			boolean flag = false;
			try {
				ResultSet result_infor_exist = statement.executeQuery("select * from Score_Manage order by snum");
				while (result_infor_exist.next()) {
					flag = true;
					break;
				}
				
				if(flag){
					jpl.removeAll();
					jpl.repaint();
					jpl = createDisplay();
					ResultSet result_infor = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor.next()){
						strnum = result_infor.getString("snum");
						
						chinese = result_infor.getFloat("chinese");
						math = result_infor.getFloat("math");
						english = result_infor.getFloat("english");
						
						highmath = result_infor.getFloat("highmath");
						physical = result_infor.getFloat("physical");
						highenglish = result_infor.getFloat("highenglish");
						
						salgorithm = result_infor.getFloat("salgorithm");
						datastructure = result_infor.getFloat("datastructure");
						sdatabase = result_infor.getFloat("sdatabase");
						
						jtinfor.append("ѧ�ţ�"+strnum+"\n");
						jtinfor.append("��ѧ�ɼ���"+"\n");
						jtinfor.append("���ģ�"+chinese+"\n");
						jtinfor.append("��ѧ��"+math+"\n");
						jtinfor.append("Ӣ�"+english+"\n");
						jtinfor.append("��ѧ�ڳɼ���"+"\n");
						jtinfor.append("������"+highmath+"\n");
						jtinfor.append("���"+physical+"\n");
						jtinfor.append("��ѧӢ�"+highenglish+"\n");
						jtinfor.append("��ѧ�ڳɼ���"+"\n");
						jtinfor.append("�㷨��"+salgorithm+"\n");
						jtinfor.append("���ݽṹ��"+datastructure+"\n");
						jtinfor.append("���ݿ��ۣ�"+sdatabase+'\n'+"*****"
										+ "****************"+'\n');
					}
					jpl.setOpaque(false);
					this.add(jpl);
					this.validate();
				}else{
					JOptionPane.showMessageDialog(null,"ѧ���ɼ�����û����Ϣ������");
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
		
		if(e.getActionCommand().equals("jbback")){						//���ز���
			try {
				statement.close();
				conR.close();
				dispose();
			} catch (SQLException e1) {
			}
			dispose();
		}
		
		if(e.getActionCommand().equals("jbsure")){						//ȷ������
			boolean exist_R = false;
			boolean exist_Score = true;
			
			if(jtid.getText().equals("")){
				JOptionPane.showMessageDialog(null,"ѧ�Ų���Ϊ�գ�����");
			}else{
				try{
					snum = Integer.valueOf(jtid.getText()).intValue();
					chinese = Float.parseFloat(jtchinese.getText());
					math = Float.parseFloat(jtmath.getText());
					english = Float.parseFloat(jtenglish.getText());
					highmath = Float.parseFloat(jthighmath.getText());
					physical = Float.parseFloat(jtphysics.getText());
					highenglish = Float.parseFloat(jthighenglish.getText());
					salgorithm = Float.parseFloat(jtalgorithm.getText());
					datastructure = Float.parseFloat(jtdatastructure.getText());
					sdatabase = Float.parseFloat(jtdatabase.getText());
					
					String insert = "insert into Score_Manage values('"+snum+"','"+chinese+"','"+math+"','"
							+english+"','"+highmath+"','"+physical+"','"+highenglish+"','"+salgorithm+"','"
							+datastructure+"','"+sdatabase+"')";
					
					if(flag_add){
						ResultSet result_snum_R = statement.executeQuery("select snum from Record_Manage");
						while (result_snum_R.next()){
							int num_R = Integer.valueOf(result_snum_R.getString("snum")).intValue();
							if(snum == num_R){
								exist_R = true;
								break;
				            }
						}
						if(exist_R){
							ResultSet result_snum_Score = statement.executeQuery("select snum from Score_Manage");
							while (result_snum_Score.next()) {
								int num_Score = Integer.valueOf(result_snum_Score.getString("snum")).intValue();
								if(snum == num_Score){
									exist_Score = false;
									break;
					            }
							}
							if(exist_Score){
								statement.executeUpdate(insert);
								JOptionPane.showMessageDialog(null,"¼��ɹ�������");
							}else{
								JOptionPane.showMessageDialog(null,"�Ѵ��ڳɼ���Ϣ������Ҫ����¼��");
							}
						}else{
							JOptionPane.showMessageDialog(null,"ѧ�������в����ڸ�ѧ�����޷�¼��ѧ����Ϣ");
						}
					}
					
					if(flag_change){
						String delete = "delete from Score_Manage where snum = '"+snum+"'";
						statement.executeUpdate(delete);
						statement.executeUpdate(insert);
						JOptionPane.showMessageDialog(null,"�޸ĳɹ�������");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"���ݿ����ʧ�ܣ�����");
				}
			}
		}
	}
//	public static void main(String[] args){
//		new Score_Manage("YC","123");
//	}
}

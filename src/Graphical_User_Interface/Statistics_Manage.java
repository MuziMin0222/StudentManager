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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Statistics_Manage extends JFrame implements ActionListener{
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
	Font font = new Font("����_GB2312",Font.BOLD+Font.ITALIC,20);
	
	String path = "D:/code/workspace_eclipse_2018/StudentManager/src/Graphical_User_Interface/����.png";
	
	JPanel jpl = new JPanel(null);
	
	JButton JB_average_course = new JButton("��ѧ�Ƴɼ���ƽ����");
	JButton JB_average_student = new JButton("ĳѧ�����Ƴɼ���ƽ����");
	JButton JB_grade_score = new JButton("���꼶��ʾѧ���ɼ�");
	JButton JB_class_score = new JButton("���༶��ʾѧ���ɼ�");
	JButton JB_back = new JButton("����");
	
	JScrollPane jsinfor;
	JTextArea jtinfor;
	
	String strnum;	int snum;
	String strgradenum; int gradenum;
	String strclassnum; int classnum;
	float chinese;
	float math;
	float english;
	float highmath;
	float physical;
	float highenglish;
	float salgorithm;
	float datastructure;
	float sdatabase;
	float sum;
	float average;
	int n = 0;
	
	public Statistics_Manage(String ac, String pw){
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
		
		ImageIcon bg = new ImageIcon(path);                            		//����ͼƬ
		JLabel label = new JLabel(bg);
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		JPanel imgP = (JPanel)this.getContentPane();
		imgP.setOpaque(false);
		
		JLabel lb0 = new JLabel("ѧ��ͳ�ơ���ѯ����");                          	 //����
		lb0.setFont(font0);
		this.add(lb0);
		
		JButton jbt = new JButton();							 			//����
		jbt.setPreferredSize(new Dimension(400,1));  
		jbt.setBorderPainted(false);
		jbt.setContentAreaFilled(false);
		this.add(jbt);
		
		JB_average_course.setBorderPainted(false);							//��ѧ��ƽ����
		JB_average_course.setFont(fontjb);
		JB_average_course.setActionCommand("jbaverage_course");
		JB_average_course.addActionListener(this);
		this.add(JB_average_course);
		
		JB_average_student.setBorderPainted(false);							//��ѯĳѧ��ƽ����
		JB_average_student.setActionCommand("jbaverage_student");
		JB_average_student.addActionListener(this);
		JB_average_student.setFont(fontjb);
		this.add(JB_average_student);
		
		JB_grade_score.setBorderPainted(false);								//���꼶��ʾ�ɼ�
		JB_grade_score.setActionCommand("jbgrade_score");
		JB_grade_score.addActionListener(this);
		JB_grade_score.setFont(fontjb);
		this.add(JB_grade_score);
		
		JB_class_score.setBorderPainted(false);								//���༶��ʾ�ɼ�
		JB_class_score.setActionCommand("jbclass_score");
		JB_class_score.addActionListener(this);
		JB_class_score.setFont(fontjb);
		this.add(JB_class_score);
		
		JB_back.setBorderPainted(false);									//����
		JB_back.setActionCommand("jbback");
		JB_back.addActionListener(this);
		JB_back.setFont(fontjb);
		this.add(JB_back);
		
		JButton jbt1 = new JButton();							 			//����
		jbt1.setPreferredSize(new Dimension(400,0));  
		jbt1.setBorderPainted(false);
		jbt1.setContentAreaFilled(false);
		this.add(jbt1);
		
		this.setVisible(true);
		this.setResizable(false);
	}

//***********************************************************************
	public JPanel createGUI(){												//��������
		jpl.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		jpl.setPreferredSize(new Dimension(516, 530));
		
		jtinfor = new JTextArea(13,40);
		jtinfor.setFont(fontjb);
		
		jsinfor = new JScrollPane(jtinfor);
		jpl.add(jsinfor);
		
		return jpl;
	}
//***********************************************************************	
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("jbaverage_course")){				//���Ƴɼ���ƽ���ֲ���
			boolean flag = false;
			try {
				ResultSet result_infor_chinese = statement.executeQuery("select * from Score_Manage order by snum");
				if(result_infor_chinese != null){
					flag = true;
				}
				if(flag){
					jpl.removeAll();
					jpl.repaint();
					jpl = createGUI();
					jtinfor.setText("���Ƴɼ���ƽ�������£�\n");
					
					jtinfor.append("���ĳɼ���\n");
					sum = 0;
					while(result_infor_chinese.next()){
						strnum = result_infor_chinese.getString("snum");
						chinese = result_infor_chinese.getFloat("chinese");
						n++;
						sum += chinese;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+chinese+"\n");
					}
					average = sum / n;
					jtinfor.append("���ĵ�ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("��ѧ�ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_math = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_math.next()){
						strnum = result_infor_math.getString("snum");
						math = result_infor_math.getFloat("math");
						n++;
						sum += math;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+math+"\n");
					}
					average = sum / n;
					jtinfor.append("��ѧ��ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("Ӣ��ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_english = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_english.next()){
						strnum = result_infor_english.getString("snum");
						english = result_infor_english.getFloat("english");
						n++;
						sum += english;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+english+"\n");
					}
					average = sum / n;
					jtinfor.append("Ӣ���ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("�����ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_highmath = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_highmath.next()){
						strnum = result_infor_highmath.getString("snum");
						highmath = result_infor_highmath.getFloat("highmath");
						n++;
						sum += highmath;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+highmath+"\n");
					}
					average = sum / n;
					jtinfor.append("������ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("����ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_physical = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_physical.next()){
						strnum = result_infor_physical.getString("snum");
						physical = result_infor_physical.getFloat("physical");
						n++;
						sum += physical;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+physical+"\n");
					}
					average = sum / n;
					jtinfor.append("�����ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("��ѧӢ��ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_highenglish = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_highenglish.next()){
						strnum = result_infor_highenglish.getString("snum");
						highenglish = result_infor_highenglish.getFloat("highenglish");
						n++;
						sum += highenglish;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+highenglish+"\n");
					}
					average = sum / n;
					jtinfor.append("��ѧӢ���ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("�㷨�ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_salgorithm = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_salgorithm.next()){
						strnum = result_infor_salgorithm.getString("snum");
						salgorithm = result_infor_salgorithm.getFloat("salgorithm");
						n++;
						sum += salgorithm;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+salgorithm+"\n");
					}
					average = sum / n;
					jtinfor.append("�㷨��ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("���ݽṹ�ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_datastructure = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_datastructure.next()){
						strnum = result_infor_datastructure.getString("snum");
						datastructure = result_infor_datastructure.getFloat("datastructure");
						n++;
						sum += datastructure;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+datastructure+"\n");
					}
					average = sum / n;
					jtinfor.append("���ݽṹ��ƽ���ɼ��ǣ�"+average+'\n'+"**************************"+'\n');
					
					jtinfor.append("���ݿ��۳ɼ���\n");
					sum = 0; n = 0;
					ResultSet result_infor_sdatabase = statement.executeQuery("select * from Score_Manage order by snum");
					while(result_infor_sdatabase.next()){
						strnum = result_infor_sdatabase.getString("snum");
						sdatabase = result_infor_sdatabase.getFloat("sdatabase");
						n++;
						sum += sdatabase;
						jtinfor.append("ѧ�ţ�"+strnum+"    ");
						jtinfor.append("�ɼ���"+sdatabase+"\n");
					}
					average = sum / n;
					jtinfor.append("���ݿ��۵�ƽ���ɼ��ǣ�"+average+'\n'+"**************************");
					
					jtinfor.setCaretPosition(0);
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
		
		if(e.getActionCommand().equals("jbaverage_student")){				//ĳѧ�����Ƴɼ���ƽ����
			jpl.removeAll();
			jpl.repaint();
			jpl = createGUI();
			jtinfor.setText("��ѧ�����Ƴɼ���ƽ�������£�\n");
			
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
						sum = 0;
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
							jtinfor.append("ѧ�ţ�"+strnum+"\n");
//							jtinfor.append("��ѧ�ɼ���"+"\n");
							jtinfor.append("���ģ�"+chinese+"\n");
							jtinfor.append("��ѧ��"+math+"\n");
							jtinfor.append("Ӣ�"+english+"\n");
//							jtinfor.append("��ѧ�ڳɼ���"+"\n");
							jtinfor.append("������"+highmath+"\n");
							jtinfor.append("���"+physical+"\n");
							jtinfor.append("��ѧӢ�"+highenglish+"\n");
//							jtinfor.append("��ѧ�ڳɼ���"+"\n");
							jtinfor.append("�㷨��"+salgorithm+"\n");
							jtinfor.append("���ݽṹ��"+datastructure+"\n");
							jtinfor.append("���ݿ��ۣ�"+sdatabase+'\n'+"*****"
											+ "****************"+'\n');
						}
						sum = chinese+math+english+highmath+physical+highenglish+salgorithm+datastructure+sdatabase; 
						average = sum / 9;
						jtinfor.append("��ѧ����ƽ���ɼ��ǣ�"+average+'\n'+"**************************");
						
						jtinfor.setCaretPosition(0);
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
		
		if(e.getActionCommand().equals("jbgrade_score")){					//���꼶��ʾѧ���ɼ�����
			jpl.removeAll();
			jpl.repaint();
			jpl = createGUI();
			jtinfor.setText("���꼶ѧ���ɼ����£�\n");
			
			boolean flag = false;
			strgradenum = JOptionPane.showInputDialog("����Ҫ��ѯ���꼶��");
			if(strgradenum.equals("")){
				JOptionPane.showMessageDialog(null,"�꼶�Ų���Ϊ�գ�����");
			}else{
				gradenum = Integer.valueOf(strgradenum).intValue();
				try {
					ResultSet result_grade = statement.executeQuery("select sgrade from Record_Manage");
					
					while (result_grade.next()) {
						int num = Integer.valueOf(result_grade.getString("sgrade")).intValue();
						if(num == gradenum){
							flag = true;
							break;
			            }
					}
					
					if(flag){
						ResultSet result_grade_snum = statement.executeQuery("select snum from Record_Manage where sgrade = '"+gradenum+"'");
						ArrayList<Integer> grade_id = new ArrayList<>();
						while(result_grade_snum.next()){
							String strid = result_grade_snum.getString("snum");
							int x = Integer.valueOf(strid).intValue();
							grade_id.add(x);
						}
						for(int element: grade_id){
							ResultSet result = statement.executeQuery("select * from Score_Manage where snum = '"+element+"'");
							while(result.next()){
								chinese = result.getFloat("chinese");
								math = result.getFloat("math");
								english = result.getFloat("english");
								highmath = result.getFloat("highmath");
								physical = result.getFloat("physical");
								highenglish = result.getFloat("highenglish");
								salgorithm = result.getFloat("salgorithm");
								datastructure = result.getFloat("datastructure");
								sdatabase = result.getFloat("sdatabase");
								
								jtinfor.append("ѧ�ţ�"+element+"\n");
//								jtinfor.append("��ѧ�ɼ���"+"\n");
								jtinfor.append("���ģ�"+chinese+"\n");
								jtinfor.append("��ѧ��"+math+"\n");
								jtinfor.append("Ӣ�"+english+"\n");
//								jtinfor.append("��ѧ�ڳɼ���"+"\n");
								jtinfor.append("������"+highmath+"\n");
								jtinfor.append("���"+physical+"\n");
								jtinfor.append("��ѧӢ�"+highenglish+"\n");
//								jtinfor.append("��ѧ�ڳɼ���"+"\n");
								jtinfor.append("�㷨��"+salgorithm+"\n");
								jtinfor.append("���ݽṹ��"+datastructure+"\n");
								jtinfor.append("���ݿ��ۣ�"+sdatabase+'\n'+"*****"
												+ "****************"+'\n');
							}
						}
						jtinfor.setCaretPosition(0);
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}else{
						JOptionPane.showMessageDialog(null,"ѧ�������в����ڸ��꼶ѧ�����봴��");
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

		if(e.getActionCommand().equals("jbclass_score")){					//���༶��ʾѧ���ɼ�����
			jpl.removeAll();
			jpl.repaint();
			jpl = createGUI();
			jtinfor.setText("�ð༶ѧ���ɼ����£�\n");
			
			boolean flag = false;
			strclassnum = JOptionPane.showInputDialog("����Ҫ��ѯ�İ༶��");
			if(strclassnum.equals("")){
				JOptionPane.showMessageDialog(null,"�༶�Ų���Ϊ�գ�����");
			}else{
				classnum = Integer.valueOf(strclassnum).intValue();
				try {
					
					ResultSet result_class = statement.executeQuery("select sclass from Record_Manage");
					
					while (result_class.next()) {
						int num = Integer.valueOf(result_class.getString("sclass")).intValue();
						if(num == classnum){
							flag = true;
							break;
			            }
					}
					
					if(flag){
						ResultSet result_class_snum = statement.executeQuery("select snum from Record_Manage where sclass= '"+classnum+"'");
						ArrayList<Integer> class_id = new ArrayList<>();
						while(result_class_snum.next()){
							String strid = result_class_snum.getString("snum");
							int x = Integer.valueOf(strid).intValue();
							class_id.add(x);
						}
						for(int element: class_id){
							ResultSet result = statement.executeQuery("select * from Score_Manage where snum = '"+element+"'");
							while(result.next()){
								chinese = result.getFloat("chinese");
								math = result.getFloat("math");
								english = result.getFloat("english");
								highmath = result.getFloat("highmath");
								physical = result.getFloat("physical");
								highenglish = result.getFloat("highenglish");
								salgorithm = result.getFloat("salgorithm");
								datastructure = result.getFloat("datastructure");
								sdatabase = result.getFloat("sdatabase");
								
								jtinfor.append("ѧ�ţ�"+element+"\n");
//								jtinfor.append("��ѧ�ɼ���"+"\n");
								jtinfor.append("���ģ�"+chinese+"\n");
								jtinfor.append("��ѧ��"+math+"\n");
								jtinfor.append("Ӣ�"+english+"\n");
//								jtinfor.append("��ѧ�ڳɼ���"+"\n");
								jtinfor.append("������"+highmath+"\n");
								jtinfor.append("���"+physical+"\n");
								jtinfor.append("��ѧӢ�"+highenglish+"\n");
//								jtinfor.append("��ѧ�ڳɼ���"+"\n");
								jtinfor.append("�㷨��"+salgorithm+"\n");
								jtinfor.append("���ݽṹ��"+datastructure+"\n");
								jtinfor.append("���ݿ��ۣ�"+sdatabase+'\n'+"*****"
												+ "****************"+'\n');
							}
						}
						jtinfor.setCaretPosition(0);
						jpl.setOpaque(false);
						this.add(jpl);
						this.validate();
					}else{
						JOptionPane.showMessageDialog(null,"ѧ�������в����ڸð༶��ѧ�����봴��");
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
		
		if(e.getActionCommand().equals("jbback")){							//���ز���
			try {
				statement.close();
				conR.close();
				dispose();
			} catch (SQLException e1) {
			}
			dispose();
		}
	}
//	public static void main(String[] args){
//		new Statistics_Manage("YC","123");
//	}
}

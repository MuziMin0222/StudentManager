package Graphical_User_Interface;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Choice extends JFrame implements ItemListener{
	String account;
	String password;
	
	JComboBox<String> JC_box = new JComboBox<String>();
	
	Font font = new Font("����_GB2312",Font.BOLD+Font.ITALIC,20);
	
	String path = "D:/code/workspace_eclipse_2018/StudentManager/src/Graphical_User_Interface/Choice.png";
	
	JPanel jpl = new JPanel();
	
	public Choice(String ac, String pd){
		account = ac;
		password = pd;
		
		this.setTitle("ѧ������ϵͳ");
		this.setSize(250,200);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		
		ImageIcon bg = new ImageIcon(path);                        //����ͼƬ
		JLabel label = new JLabel(bg);
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		JPanel imgP = (JPanel)this.getContentPane();
		imgP.setOpaque(false);
		
		JC_box.addItem("����ѡ��");
		JC_box.addItem("ѧ����������");
		JC_box.addItem("ѧ��ѧ������");
		JC_box.addItem("ѧ���ɼ�����");
		JC_box.addItem("ͳ�ơ���ѯ");
		JC_box.setSelectedItem("ѡ����");   							//Ĭ��ѡ��
		JC_box.setFont(font);
		
		JC_box.addItemListener(this);
		
		jpl.add(JC_box);
		jpl.setOpaque(false);
		
        this.add(jpl);
        
        this.setVisible(true);
		this.setResizable(true);
	}
	
	public void itemStateChanged(ItemEvent e){                          //������Item
		if(e.getStateChange() == ItemEvent.SELECTED){
			if(JC_box.getSelectedItem()=="ѧ����������"){                   //ѧ����������
				new Record_Manage(account,password);
				JC_box.setSelectedIndex(0);
			}
			
			if(JC_box.getSelectedItem()=="ѧ��ѧ������"){				//ѧ��ѧ������
				new School_Manage(account,password);
				JC_box.setSelectedIndex(0);
			}
			
			if(JC_box.getSelectedItem()=="ѧ���ɼ�����"){				//ѧ���ɼ�����
				new Score_Manage(account,password);
				JC_box.setSelectedIndex(0);
			}
			
			if(JC_box.getSelectedItem()=="ͳ�ơ���ѯ"){					//ͳ�ơ���ѯ
				new Statistics_Manage(account,password);
				JC_box.setSelectedIndex(0);
			}
		}
	}
}
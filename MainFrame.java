import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel filePane;
	public JLabel hour = new JLabel("12");
	public JLabel minute = new JLabel("12");
	public JLabel second = new JLabel("12");
	public int hh = 0;
	public int mm = 0;
	public int ss = 0;
	public DefaultListModel<String> dlm = new DefaultListModel<String>();
	public String ringpath = "./我的小可爱.mp3";
	public JList<String> clocklist;
	public int selectedindex = 0;

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
	
	public MainFrame() {
		setTitle("Little Clock by Guo Ziyang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(414, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.getContentPane().setBackground(new Color(221,160,221));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 413, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("设置");
		menuBar.add(mnNewMenu);
		
		class SetPath extends JFrame{
			private static final long serialVersionUID = 1L;

			public SetPath() {
				setTitle("设置闹铃路径");
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				setSize(360, 130);
				setLocationRelativeTo(null);
				
				filePane = new JPanel();
				filePane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(filePane);
				filePane.setLayout(null);
				
				JTextField filechoice = new JTextField();
				filechoice.setBounds(20, 30, 250, 29);
				filechoice.setText(ringpath);
				filePane.add(filechoice);
				
				JButton choose = new JButton("浏览");
				choose.setBounds(275, 30, 70, 29);
				choose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser jfilechooser = new JFileChooser("选择闹铃");
						MusicFileFilter mff = new MusicFileFilter();
						jfilechooser.addChoosableFileFilter(mff);
						jfilechooser.setFileFilter(mff);
						if(jfilechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {  
						     ringpath = jfilechooser.getSelectedFile().getAbsolutePath();
						     filechoice.setText(ringpath);
						} 
					}
				});
				filePane.add(choose);

				JButton choosecancel = new JButton("恢复默认");
				choosecancel.setBounds(180, 70, 90, 29);
				choosecancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ringpath = "./我的小可爱.mp3";
						filechoice.setText(ringpath);
					}
				});
				filePane.add(choosecancel);
				
				JButton comfirmchoice = new JButton("确定");
				comfirmchoice.setBounds(265, 70, 90, 29);
				comfirmchoice.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				filePane.add(comfirmchoice);
				
				JLabel bullshit = new JLabel("默认为精美闹铃哦");
				bullshit.setBounds(20, 5, 120, 30);
				filePane.add(bullshit);
			}
		}
		
		JMenuItem menuItem = new JMenuItem("设置闹铃路径");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetPath setpath = new SetPath();
				setpath.setVisible(true);
			}
		});
		mnNewMenu.add(menuItem);
		
		JMenu menu = new JMenu("工具");
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("啥都没有");
		menu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("没想到吧");
		menu.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("但你可以");
		menu.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("捐助作者");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Donation donation = new Donation();
			}
		});
		menu.add(menuItem_5);
		
		JMenu menu_1 = new JMenu("帮助");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("关于");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
			}
		});
		menu_1.add(menuItem_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("logo.jpg")));
		lblNewLabel.setBounds(167, 25, 75, 34);
		contentPane.add(lblNewLabel);
		
		hour.setHorizontalAlignment(SwingConstants.CENTER);
		hour.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		hour.setBounds(65, 55, 76, 80);
		contentPane.add(hour);
		
		minute.setHorizontalAlignment(SwingConstants.CENTER);
		minute.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		minute.setBounds(168, 55, 76, 80);
		contentPane.add(minute);
		
		second.setHorizontalAlignment(SwingConstants.CENTER);
		second.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		second.setBounds(271, 55, 76, 80);
		contentPane.add(second);

		JLabel dot1 = new JLabel(":");
		dot1.setHorizontalAlignment(SwingConstants.CENTER);
		dot1.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		dot1.setBounds(145, 55, 19, 80);
		contentPane.add(dot1);
		
		JLabel dot2 = new JLabel(":");
		dot2.setHorizontalAlignment(SwingConstants.CENTER);
		dot2.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		dot2.setBounds(248, 55, 19, 80);
		contentPane.add(dot2);
		
		SpinnerModel modelhh = new SpinnerNumberModel(0, 0, 24, 1);
		SpinnerModel modelmm = new SpinnerNumberModel(0, 0, 60, 1);
		
		JSpinner clock_choice_hh = new JSpinner(modelhh);
		clock_choice_hh.setBounds(107, 142, 50, 26);
		contentPane.add(clock_choice_hh);
		
		JSpinner clock_choice_mm = new JSpinner(modelmm);
		clock_choice_mm.setBounds(201, 142, 50, 26);
		contentPane.add(clock_choice_mm);
		
		JLabel lblNewLabel_1 = new JLabel("设置闹铃：");
		lblNewLabel_1.setBounds(19, 147, 65, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel Label1 = new JLabel("时");
		Label1.setBounds(167, 147, 19, 16);
		contentPane.add(Label1);
		
		JLabel label2 = new JLabel("分");
		label2.setBounds(258, 147, 19, 16);
		contentPane.add(label2);
		
		JButton deletering = new JButton("删除该闹铃");
		deletering.setEnabled(false);
		deletering.setBounds(147, 328, 117, 29);
		deletering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlm.removeElementAt(selectedindex);
			}
		});
		contentPane.add(deletering);
		
		clocklist = new JList<String>(dlm);
		clocklist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				deletering.setEnabled(true);
				selectedindex = e.getFirstIndex();
			}
		});
		JScrollPane scrollPane = new JScrollPane(clocklist);
		clocklist.setBounds(43, 199, 325, 123);
		scrollPane.setBounds(43, 199, 325, 123);
		contentPane.add(scrollPane);
		
		JButton addclock = new JButton("添加闹铃");
		addclock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int clock_hh = (int) clock_choice_hh.getValue();
				int clock_mm = (int) clock_choice_mm.getValue();
				String showring = String.format("%02d", clock_hh) +":"+ String.format("%02d", clock_mm);
				dlm.addElement(showring);
			}
		});
		addclock.setBounds(285, 142, 117, 29);
		contentPane.add(addclock);
		
		JLabel lblNewLabel_2 = new JLabel("已添加闹铃");
		lblNewLabel_2.setBounds(176, 180, 75, 16);
		contentPane.add(lblNewLabel_2);

		setVisible(true);
		
		RunPerSecond runpersecond = new RunPerSecond();
		Thread persecond = new Thread(runpersecond);
		persecond.setDaemon(true);
		persecond.start();
	}
	

	class MusicFileFilter extends FileFilter{
		public String getDescription() {
			return "*.mp3";
		}
		public boolean accept(File file) {
			String name = file.getName();
			return file.isDirectory() || name.toLowerCase().endsWith(".mp3");
		}
	}
	
	class About extends JFrame{
		private static final long serialVersionUID = 1L;
		public About(){
			setTitle("关于");
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setSize(300, 180);
			setLocationRelativeTo(null);
			
			JPanel aboutPane = new JPanel();
			aboutPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(aboutPane);
			aboutPane.setLayout(null);
			
			JLabel aboutlabel = new JLabel();
			String about = "<html><body>一个十分简陋的小闹钟 by Guo Ziyang<br>欢迎大家帮忙发现和解决bug<br><br>Github：https://github.com/CN-GuoZiyang<br>Gmail：guoziyang0033@gmail.com</body></html>";
			aboutlabel.setText(about);
			aboutlabel.setBounds(10, 10, 290, 100);
			aboutPane.add(aboutlabel);
			
			JButton aboutyes = new JButton("确定");
			aboutyes.setBounds(105, 110, 90, 29);
			aboutyes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			aboutPane.add(aboutyes);
			
			setVisible(true);
		}
	}
	
	class Donation extends JFrame{
		private static final long serialVersionUID = 1L;
		public Donation() {
			setTitle("捐助作者");
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setSize(300, 180);
			setLocationRelativeTo(null);
			
			JPanel donationPane = new JPanel();
			donationPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(donationPane);
			donationPane.setLayout(null);
			
			JLabel donationlabel = new JLabel();
			String donationstr = "<html><body><center>什么？兄弟？<br>你真的要捐助我吗？<br>这么nice的？<br>算了算了<br>你要是真的觉得我做的好<br>打开我的GitHub给我个star就很感激了</center></body></html>";
			donationlabel.setText(donationstr);
			donationlabel.setBounds(40, 10, 290, 100);
			donationPane.add(donationlabel);
			
			JButton opengithub = new JButton("打开github");
			opengithub.setBounds(10, 120, 110, 29);
			opengithub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Desktop desktop = Desktop.getDesktop();  
					try {
						desktop.browse(new URI("https://github.com/CN-GuoZiyang"));
					}catch(Exception e1) {
						System.out.print(e1);
					}
				}
			});
			donationPane.add(opengithub);
			
			JButton donationyes = new JButton("确定");
			donationyes.setBounds(175, 120, 110, 29);
			donationyes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			donationPane.add(donationyes);
			
			setVisible(true);
		}
	}
	

	class AlarmFrame extends JFrame{
		private static final long serialVersionUID = 1L;
		public AlarmFrame(){
			setTitle("闹铃响啦！！！");
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setSize(300, 100);
			setLocationRelativeTo(null);

			JPanel alarmPane = new JPanel();
			alarmPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(alarmPane);
			alarmPane.setLayout(null);

			JLabel alarmlabel = new JLabel("闹铃响啦！赶快该干嘛干嘛啦！！！");
			alarmlabel.setBounds(50, 10, 250, 29);
			alarmPane.add(alarmlabel);

			JButton alarmaddten = new JButton("十分钟再响");
			alarmaddten.setBounds(50, 40, 90, 29);
			MP3Player alarm = new MP3Player(new File(ringpath));
			alarm.setDaemon(true);
			alarmaddten.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int t_hh, t_mm;
					if(60 - mm <= 10){
						t_hh = hh + 1;
						if(t_hh >= 24){
							t_hh -= 24;
						}
						t_mm = mm - 50;
					}else{
						t_hh = hh;
						t_mm = mm + 10;
					}
					String t = String.format("%02d", t_hh) +":"+ String.format("%02d", t_mm);
					dlm.addElement(t);
					setVisible(false);
					alarm.interrupt();
					alarm.player.close();
				}
			});
			alarmPane.add(alarmaddten);

			JButton alarmoff = new JButton("关闭闹铃");
			alarmoff.setBounds(160, 40, 90, 29);
			alarmoff.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					alarm.player.close();
					alarm.interrupt();
				}
			});
			alarmPane.add(alarmoff);

			setVisible(true);

			alarm.run();
		}
	}

	class MP3Player extends Thread{
		Player player;
		File music;

		public MP3Player(File filename){
			this.music = filename;
		}
		public void run() {
			super.run();
			try {
				play();     
			} catch (FileNotFoundException | JavaLayerException e) {
				e.printStackTrace();
			}
		}
		public void play() throws FileNotFoundException, JavaLayerException {
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
			player = new Player(buffer);
			player.play(); 
		}
	}
	
	class RingFrame extends Thread{
		public RingFrame(){
			this.run();
		}
		public void run(){
			AlarmFrame alarmframe = new AlarmFrame();
		}
	}

	class RunPerSecond implements Runnable{
		 public void run(){
			 while(true){
				Calendar calendar = Calendar.getInstance();
				hh = calendar.get(Calendar.HOUR_OF_DAY);
				mm = calendar.get(Calendar.MINUTE);
				ss = calendar.get(Calendar.SECOND);
				hour.setText(String.format("%02d", hh));
				minute.setText(String.format("%02d", mm));
				second.setText(String.format("%02d", ss));
				String clocknow = String.format("%02d", hh) + ":" + String.format("%02d", mm);
				if(dlm.getSize() > 0){
				 	for(int i = 0; i < dlm.getSize(); i++){
						String clockcheck = dlm.elementAt(i);
						if(clockcheck.equals(clocknow) && ss == 0){
							dlm.remove(i);
							RingFrame ringframe = new RingFrame();
							ringframe.setDaemon(true);
							break;
						}
					}	
				}
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					System.out.print(e);
				}
			}
		}
	}
}
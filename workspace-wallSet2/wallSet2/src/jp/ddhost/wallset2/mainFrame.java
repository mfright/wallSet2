package jp.ddhost.wallset2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class mainFrame extends JFrame
{
	Container contentPane;//���̃t���[����ContentPane

	static String imagePath = "C:\\Users\\_\\Documents\\PHOTOS\\DSC_0260.JPG";

	public static void main(String[] args) throws Exception
	{
		//�ǎ��摜�p�X�̎w��
		if(args.length > 0){
			imagePath = args[0];
		}

		//FrameTest2�̍쐬
		mainFrame myFrame = new mainFrame();



	}

	private void trayIcon(){
		try{
			// �^�X�N�g���C�A�C�R���𐶐�
	        Image image = ImageIO.read(
	                getClass().getResourceAsStream("trayicon.png"));
	        final TrayIcon icon = new TrayIcon(image,"WALL SETTER");


	        // �|�b�v�A�b�v���j���[�𐶐�
	        PopupMenu menu = new PopupMenu();

	        // Quit�A�C�e��
	        MenuItem exitItem = new MenuItem("QUIT");
	        exitItem.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });

	        // ���j���[�ɃA�C�e���ǉ�
	        menu.add(exitItem);
	        icon.setPopupMenu(menu);

	        // �^�X�N�g���C�Ɋi�[
	        SystemTray.getSystemTray().add(icon);



		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public mainFrame() {

		//�^�X�N�g���C�̃A�C�R����\��
		trayIcon();


		//���̃t���[����ContentPane���擾
		contentPane = this.getContentPane();

		// contentPane�̔w�i��ɂ��A��΍��W�z�u�ɂ���
		contentPane.setBackground(new Color(0,118,163));
		getContentPane().setLayout(null);

		this.setBounds(300,300,300,300);

		// �t���[����I���ł��Ȃ��悤�ɂ���
		this.setFocusableWindowState(false);

		//�t���[����������A�A�v���P�[�V�������I��������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//�t���[���̘g���O��
		this.setUndecorated(true);

		//�J�����_�[��\������
		dispCalendar();

		//�������̕\��
		JLabel calendarBox = new JLabel();
		calendarBox.setOpaque(true);
		calendarBox.setBackground(new Color(255,255,255,125));
		calendarBox.setBounds(10,10,220,220);
		contentPane.add(calendarBox);


		//�摜�\���pImageIcon
		ImageIcon icon1 = new ImageIcon(imagePath);

		//�ǂݍ��񂾉摜�̃T�C�Y
		int height = icon1.getIconHeight();//�摜�̍���
		int width = icon1.getIconWidth();//�摜�̕�
		System.out.println(width+"�~"+height);

		//�摜�\���pJLabel
		JLabel imageLabel = new JLabel(icon1);
		imageLabel.setBounds(0,0,width,height);
		imageLabel.setVerticalAlignment(JLabel.CENTER);

		// contentPane�ɉ摜�\���pJLabel��ǉ�
		contentPane.add(imageLabel);

		//�E�B���h�E�̃T�C�Y�ƈʒu�𒲐�
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //�f�B�X�v���C�T�C�Y
		int x = (d.width - width);	//�t���[����X���W
		int y= (d.height-height)/2;		//�t���[����Y���W
		this.setBounds(x, y, width, height);







		//�t���[����\��������B
		this.setVisible(true);

		//�t���[�����Ŕw�ʂɑ���
		this.toBack();


	}



	private void dispCalendar(){
		String[] weekName = {"Sun", "Mon", "Tue", "Wed",
                "Thu", "Fri", "Sat"};

		Calendar myCalendar = Calendar.getInstance();

		// ���ݓ���
		int year = myCalendar.get(Calendar.YEAR);	//�N
		int month = myCalendar.get(Calendar.MONTH) + 1;	//��
		int day = myCalendar.get(Calendar.DATE);	//��
		int week = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;//�j��(�ԍ�)
		int days = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);//���̓���


		//System.out.println(year + "�N" + month + "��" + day + "��");
		//System.out.println("(" + week_name[week] + ")");
		//System.out.println(hour + "��" + minute + "��" + second + "�b");

		int[] xOffsets = {20,50,80,110,140,170,200};
		int[] yOffsets = {80,110,140,170,200,230,260,290};

		createLabel(year + "/" + month,Color.black,20,20);
		createLabel("Sun",Color.red,xOffsets[0],50);
		createLabel("Mon",Color.black,xOffsets[1],50);
		createLabel("Tue",Color.black,xOffsets[2],50);
		createLabel("Wed",Color.black,xOffsets[3],50);
		createLabel("Thu",Color.black,xOffsets[4],50);
		createLabel("Fri",Color.black,xOffsets[5],50);
		createLabel("Sat",Color.blue,xOffsets[6],50);

		//myCalendar.set(Calendar.DATE, 1);
		//int firstDayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;
		//createLabel("1",Color.black,xOffsets[firstDayOfWeek],50);

		myCalendar.set(Calendar.DATE, 1);
		int xLine = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;
		int yLine = 0;

		for(int i=1;i<=days;i++){
			if(i==day){
				createLabel(i+"", Color.yellow, xOffsets[xLine], yOffsets[yLine]);
			}else{
				createLabel(i+"", Color.black, xOffsets[xLine], yOffsets[yLine]);
			}

			xLine++;
			if(xLine>6){
				xLine = 0;
				yLine++;
			}
		}
	}

	private void createLabel(String message,Color stringColor,int x,int y){
		JLabel myLabel = new JLabel(message);
		myLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		myLabel.setForeground(stringColor);

		//myLabel.setOpaque(true);
		//myLabel.setBackground(new Color(200,200,200,150));
		myLabel.setBounds(x,y, 200,30);
		contentPane.add(myLabel);
	}

}
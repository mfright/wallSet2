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
	Container contentPane;//このフレームのContentPane

	static String imagePath = "C:\\Users\\_\\Documents\\PHOTOS\\DSC_0260.JPG";

	public static void main(String[] args) throws Exception
	{
		//壁紙画像パスの指定
		if(args.length > 0){
			imagePath = args[0];
		}

		//FrameTest2の作成
		mainFrame myFrame = new mainFrame();



	}

	private void trayIcon(){
		try{
			// タスクトレイアイコンを生成
	        Image image = ImageIO.read(
	                getClass().getResourceAsStream("trayicon.png"));
	        final TrayIcon icon = new TrayIcon(image,"WALL SETTER");


	        // ポップアップメニューを生成
	        PopupMenu menu = new PopupMenu();

	        // Quitアイテム
	        MenuItem exitItem = new MenuItem("QUIT");
	        exitItem.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });

	        // メニューにアイテム追加
	        menu.add(exitItem);
	        icon.setPopupMenu(menu);

	        // タスクトレイに格納
	        SystemTray.getSystemTray().add(icon);



		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public mainFrame() {

		//タスクトレイのアイコンを表示
		trayIcon();


		//このフレームのContentPaneを取得
		contentPane = this.getContentPane();

		// contentPaneの背景を青にし、絶対座標配置にする
		contentPane.setBackground(new Color(0,118,163));
		getContentPane().setLayout(null);

		this.setBounds(300,300,300,300);

		// フレームを選択できないようにする
		this.setFocusableWindowState(false);

		//フレームを閉じた時、アプリケーションを終了させる
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//フレームの枠を外す
		this.setUndecorated(true);

		//カレンダーを表示する
		dispCalendar();

		//白い箱の表示
		JLabel calendarBox = new JLabel();
		calendarBox.setOpaque(true);
		calendarBox.setBackground(new Color(255,255,255,125));
		calendarBox.setBounds(10,10,220,220);
		contentPane.add(calendarBox);


		//画像表示用ImageIcon
		ImageIcon icon1 = new ImageIcon(imagePath);

		//読み込んだ画像のサイズ
		int height = icon1.getIconHeight();//画像の高さ
		int width = icon1.getIconWidth();//画像の幅
		System.out.println(width+"×"+height);

		//画像表示用JLabel
		JLabel imageLabel = new JLabel(icon1);
		imageLabel.setBounds(0,0,width,height);
		imageLabel.setVerticalAlignment(JLabel.CENTER);

		// contentPaneに画像表示用JLabelを追加
		contentPane.add(imageLabel);

		//ウィンドウのサイズと位置を調整
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //ディスプレイサイズ
		int x = (d.width - width);	//フレームのX座標
		int y= (d.height-height)/2;		//フレームのY座標
		this.setBounds(x, y, width, height);







		//フレームを表示させる。
		this.setVisible(true);

		//フレームを最背面に送る
		this.toBack();


	}



	private void dispCalendar(){
		String[] weekName = {"Sun", "Mon", "Tue", "Wed",
                "Thu", "Fri", "Sat"};

		Calendar myCalendar = Calendar.getInstance();

		// 現在日時
		int year = myCalendar.get(Calendar.YEAR);	//年
		int month = myCalendar.get(Calendar.MONTH) + 1;	//月
		int day = myCalendar.get(Calendar.DATE);	//日
		int week = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;//曜日(番号)
		int days = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);//月の日数


		//System.out.println(year + "年" + month + "月" + day + "日");
		//System.out.println("(" + week_name[week] + ")");
		//System.out.println(hour + "時" + minute + "分" + second + "秒");

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
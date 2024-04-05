package Chat;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ClientFrame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JButton btnFile = new JButton(); // 파일열기
	private JButton btnEmoticon = new JButton(); // 이모티콘
	private JButton btnDisCon = new JButton("접속종료");
	private DefaultListModel dlm = new DefaultListModel<>();
	private JList<DefaultListModel<String>> list = new JList(dlm);
	private JTextField msgField = new JTextField(35);
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	public String uid = "";
	public String uip = "";
	public int uport;
	private JFrame frame = null;

	// 소켓의 생성
	private Socket socket;
	public ObjectOutputStream out;

	public ClientFrame(String id, String uip, String port) {
		this.uid = id;
		this.uip = uip;
		this.uport = Integer.parseInt(port);
		init(id, uip, uport);

		this.setSize(550, 500);
		this.setLayout(new BorderLayout());

		top.setLayout(new FlowLayout());
		top.add(btnDisCon);

		ImageIcon ii1 = new ImageIcon(
				new ImageIcon("images/fileopen.png").getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT));
		btnFile.setIcon(ii1);
		ImageIcon ii2 = new ImageIcon(
				new ImageIcon("emoticon/img1.png").getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT));
		btnEmoticon.setIcon(ii2);

		btnFile.setToolTipText("파일열기");
		btnEmoticon.setToolTipText("이모티콘");
		btnDisCon.setToolTipText("종료");
		msgField.setToolTipText("메시지를 입력 후 엔터키를 누르세요.");

		bottom.add(msgField);
		bottom.add(btnFile);
		bottom.add(btnEmoticon);

		btnFile.addActionListener(this);
		btnEmoticon.addActionListener(this);
		btnDisCon.addActionListener(this);
		msgField.addKeyListener(this);

		frame = this;

		this.add(BorderLayout.NORTH, top);
		this.add(BorderLayout.CENTER, new JScrollPane(list));
		this.add(BorderLayout.SOUTH, bottom);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// 키이벤트 시작-----------------------------------------------
	@Override
	public void keyPressed(KeyEvent ke) {
		// 엔터를 누르면 서버로 데이터를 송신(write & flush)한다.
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				ChatMsg msg = new ChatMsg(ChatMsg.PROT_MESSAGE, uid, msgField.getText());
				out.writeObject(msg);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			msgField.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
	// 키이벤트 끝-----------------------------------------------

	// 버튼 이벤트 시작-------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnDisCon) {
			if (socket != null && socket.isConnected()) {
				try {
					int confirm = JOptionPane.showConfirmDialog(this, "서버와 접속을 종료하시겠습니까?");
					if (confirm == JOptionPane.OK_OPTION) {
						out.close();
						socket.close();
						socket = null;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "미접속 상태입니다. 접속을 먼저 해 주세요.");
			}
		} else if (obj == btnFile) {
			if (socket != null) {
				JPopupMenu popupmenu = new JPopupMenu("Edit");
				JMenuItem image = new JMenuItem("이미지");
				JMenuItem normal = new JMenuItem("일반파일");
				image.addActionListener(new FileChooserListener(this, 1));
				normal.addActionListener(new FileChooserListener(this, 2));
				popupmenu.add(image);
				popupmenu.add(normal);
				popupmenu.show(btnFile, btnFile.getX() - 380, btnFile.getY());
			} else {
				JOptionPane.showMessageDialog(btnFile, "서버와 연결되어 있지 않아 파일 전송을 할 수 없습니다.");
			}
		} else if (obj == btnEmoticon) {
			JDialog emoticonDlg = new JDialog(this);
			emoticonDlg.setSize(400, 320);
			emoticonDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			emoticonDlg.setLayout(new BorderLayout());
			emoticonDlg.setTitle("이모티콘 선택");
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3, 4));

			File imgDir = new File("emoticon");
			File[] imgFiles = imgDir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith("png") || name.endsWith("jpg") || name.endsWith("gif");
				}
			});

			for (int i = 0; i < imgFiles.length; i++) {
				ImageIcon icon = new ImageIcon(imgFiles[i].getAbsolutePath());
				JLabel lbl = new JLabel(icon);
				lbl.setSize(20, 20);
				lbl.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (socket != null) {
							ImageIcon icon = (ImageIcon) lbl.getIcon();
							try {
								ChatMsg msg = new ChatMsg(ChatMsg.PROT_IMAGE, uid, "이미지 전송");
								msg.setImage(icon);
								out.writeObject(msg);
								out.flush();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(lbl, "서버와 연결되어 있지 않아 이미지를 전송하지 않습니다.");
						}
						emoticonDlg.dispose();
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				panel.add(lbl);
			}
			emoticonDlg.add(panel, BorderLayout.CENTER);
			emoticonDlg.setResizable(false);
			emoticonDlg.setVisible(true);
		}
	}
	// 버튼 이벤트 끝-------------------------------------------

	public boolean init(String uid, String uip, int uport) {
		try {
			String serverIp = uip;
			// 소켓을 생성하여 연결을 요청한다.
			socket = new Socket(serverIp, uport);
			out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("서버에 연결되었습니다.");

			// 접속자 이름전송
			ChatMsg msg = new ChatMsg(ChatMsg.PROT_LOGIN, uid, " 님이 접속 하였습니다.");
			out.writeObject(msg);
			out.flush();

			// 클라이언트 소켓을 처리할 쓰레드를 생성 및 기동한다.
			Thread receiver = new ClientReceiver(socket);
			receiver.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 클라이언트 소켓을 처리할 쓰레드 생성
	class ClientReceiver extends Thread {
		Socket socket;
		ObjectInputStream in;

		ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
			}
		}

		public void run() {
			int nRow = 0;
			while (in != null) {
				try {
					Object oMsg = in.readObject();
					if (oMsg instanceof ChatMsg) {
						ChatMsg msg = (ChatMsg) oMsg;
						String strMsg = null;
						switch (msg.getProtocol()) {
						case ChatMsg.PROT_LOGIN:
						case ChatMsg.PROT_MESSAGE:
						case ChatMsg.PROT_LOGOUT:
							strMsg = "[" + msg.getName() + "][" + msg.getMessage() + "]";
							System.out.println(strMsg);
							dlm.add(nRow++, strMsg);
							break;
						case ChatMsg.PROT_IMAGE:
							strMsg = "[" + msg.getName() + "] 님이 이미지아이콘을 보냈습니다.";
							System.out.println(strMsg);
							ImageIcon icon = msg.getImage();
							if (icon.getIconWidth() > 120 || icon.getIconHeight() > 120) {
								Image image = icon.getImage();
								Image chgImg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
								icon = new ImageIcon(chgImg);
							}
							dlm.add(nRow++, strMsg);
							dlm.add(nRow++, icon);
							break;
						case ChatMsg.PROT_FILE:
							if (!msg.getName().equals(uid)) {
								strMsg = "[" + msg.getName() + "] 님이 파일을 보냈습니다. [" + msg.getMessage() + "]";
								dlm.add(nRow++, strMsg);
								System.out.println(strMsg);

								int nResult = JOptionPane.showConfirmDialog(frame,
										"[" + msg.getName() + "] 님이 파일을 보냈습니다. [" + msg.getMessage() + "] 저장하시겠습니까?");
								if (nResult == JOptionPane.OK_OPTION) {
									JFileChooser jfc = new JFileChooser(
											FileSystemView.getFileSystemView().getHomeDirectory());
									jfc.setDialogTitle("파일 저장:");
									jfc.setSelectedFile(new File(msg.getMessage()));
									int returnValue = jfc.showSaveDialog(frame); // 열기 모드
									// 선택 버튼 클릭인지 여부 판단
									if (returnValue == JFileChooser.APPROVE_OPTION) {
										File files = jfc.getSelectedFile();
										writeFile(msg.getFileContent(), files.getAbsolutePath(), false);
										dlm.add(nRow++, "[" + files.getAbsolutePath() + "]로 파일을 저장했습니다.");
									} else {
										dlm.add(nRow++, "[" + msg.getMessage() + "] 저장을 취소했습니다.");
									}
								}
							}
							break;
						}
					} else {
						dlm.addElement("처리할 수 없는 메시지가 도착했습니다. 메시지타입:" + oMsg.getClass().getSimpleName());
					}
				} catch (Exception e) {
				}
			}
		} // run
	}// end class ClientReceiver

	public static byte[] serialize(Object obj) {
		try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
			try (ObjectOutputStream o = new ObjectOutputStream(b)) {
				o.writeObject(obj);
			} catch (IOException e) {
			}
			return b.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * deserialize bytes to message
	 * 
	 * @param bytes message in bytes
	 * @return message instance
	 */
	public static Object deserialize(byte[] bytes) {
		try (ByteArrayInputStream b = new ByteArrayInputStream(bytes); ObjectInputStream o = new ObjectInputStream(b)) {
			return o.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] readFile(String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName);) {
			byte[] b = new byte[(int) fis.getChannel().size()];
			fis.read(b);
			return b;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/******************************************
	 * 파일 쓰기
	 * 
	 * @param data
	 * @param fileFullName
	 * @param isW
	 * @return
	 */
	public boolean writeFile(byte[] data, String fileFullName, boolean isAppend) {
		try (FileOutputStream fos = new FileOutputStream(fileFullName, isAppend);) {
			File file = new File(fileFullName);
			if (file.getParentFile().isDirectory() == false) {
				file.getParentFile().mkdirs();
			}
			fos.write(data);
			fos.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	class FileChooserListener implements ActionListener {
		Component parent = null;
		int type = 1; // 1:이미지, 2:normal

		public FileChooserListener(Component parent, int type) {
			this.parent = parent;
			this.type = type;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("파일 선택");
			if (type == 1) {
				// 파일필터 추가
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF and JPG images", "png", "gif",
						"jpg");
				jfc.setFileFilter(filter);
			}
			// 다중 선택 가능
			jfc.setMultiSelectionEnabled(true);
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

			int returnValue = jfc.showOpenDialog(parent); // 열기 모드
			// 선택 버튼 클릭인지 여부 판단
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File[] files = jfc.getSelectedFiles();
				for (File x : files) {
					try {
						if (type == 1) { // 이미지
							ChatMsg msg = new ChatMsg(ChatMsg.PROT_IMAGE, uid, "이미지 전송");
							ImageIcon icon = new ImageIcon(x.getAbsolutePath());
							msg.setImage(icon);
							out.writeObject(msg);
							out.flush();
						} else if (type == 2) { // 파일
							ChatMsg msg = new ChatMsg(ChatMsg.PROT_FILE, uid, "파일 전송");
							byte[] content = readFile(x.getAbsolutePath());
							msg.setMessage(x.getName());
							msg.setFileContent(content);
							out.writeObject(msg);
							out.flush();
							System.out.println(x.getAbsolutePath());
							dlm.addElement("파일을 전송했습니다. [" + x.getAbsolutePath() + "]");
						} else {
							JOptionPane.showMessageDialog(parent, "처리할 수 없는 타입입니다.[" + type + "]");
							System.out.println();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
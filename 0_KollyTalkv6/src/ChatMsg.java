
import java.io.Serializable;

import javax.swing.ImageIcon;

public class ChatMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PROT_LOGIN   = "100";
	public static final String PROT_MESSAGE = "200";
	public static final String PROT_IMAGE   = "300";
	public static final String PROT_FILE    = "400";
	public static final String PROT_DRAW    = "500";
	public static final String PROT_LOGOUT  = "600";
	
	private String protocol;	//100:login, 200:메시지, 300:image, 400:file, 500:LOGOUT
	private String name;
	private String message;
	private String timestamp;
	private byte[] fileContent;
	private ImageIcon image;
	
	public ChatMsg(String protocol, String name, String timestamp, String message) {
		this.protocol = protocol;
		this.name = name ;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] content) {
		this.fileContent = content;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
}

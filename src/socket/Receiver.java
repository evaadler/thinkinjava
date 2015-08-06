package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 基于UDP的网络编程
 * 
 * @author Nina
 * 
 */
public class Receiver {
	public static void main(String[] args) {
		DatagramSocket ds = null;
		try {
			//UDP接收端连接
			ds = new DatagramSocket(9999);
			//定义将UDP的数据包接收到什么地方
			byte[] buf = new byte[1024];
			//定义UDP的数据接受包
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			//接收数据包
			ds.receive(dp);
			String str = new String(dp.getData(),0, dp.getLength());
			System.out.println(str);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

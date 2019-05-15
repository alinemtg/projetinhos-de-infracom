
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server_TCP {
	public static void main (String[] args) throws IOException {
		int port = 3001; 

		try {
			ServerSocket tmpsocket = new ServerSocket(port);
			System.out.println("No aguardo do cliente");
			Socket socket = tmpsocket.accept();

			while(true) {
								
				DataInputStream inData = new DataInputStream (socket.getInputStream());
				String stringuiline = inData.readUTF();
				if (stringuiline.equals("cabousse")) {
					System.out.println("eh isto, tchau.");
					break;
		        }
				System.out.println ("yay, recebemos essa msg: " + stringuiline);

				// ~~~~~ pseudochatbot ~~~~~
				
				String outMsg;
				if (stringuiline.contains("dango")) {
					outMsg = "a familia dango eh uma familia feliz";
				}else {
					outMsg = "fale uma coisa mais fofa";
				}

				// ~~~~~ end do pseudochatbot ~~~~~

				DataOutputStream outData = new DataOutputStream (socket.getOutputStream()); 
				outData.writeUTF(outMsg);
				System.out.println ("yay again, enviamos uma resposta pro cliente!");

			} 
			socket.close();

		} catch (BindException e) {
			System.out.println("Endereço em uso");
		} catch (Exception e) {
			System.out.println("Vixi, deu esse erro aq: " + e);
		}
	}
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class P2P_am {
	
	// ~~~~~~~~~~ parte de envio ~~~~~~~~~~
	
	public static void main (String[] args) throws IOException {
		try {
			AloThread receive = new AloThread();
			receive.start();
			
			DatagramSocket clientSocket = new DatagramSocket();
	        InetAddress IPServer = InetAddress.getByName("localhost"); // definindo o IP do servidor que vai se comunicar
	        byte[] sendData;

	        Scanner in = new Scanner(System.in); 
        	System.out.println ("Conversem aí de boinhas");

	        while (true) {
	        	
		        // ~~~~~ leitura da msg que se deseja enviar ~~~~~
		        		        String sendDataS = in.nextLine();
		        
		        if (sendDataS.equals("cabousse")) {
		        	System.out.println("eh isto, tchau.");
		        	break;
		        }
		
		        // ~~~~~ enviando a msg lida ~~~~~
		        
		        sendData = (sendDataS).getBytes(); 
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPServer, 5000); // definindo os dados que vao ser enviados para o servidor
		        clientSocket.send(sendPacket); // enviando para o servidor
		       			
	        }
	        
		}catch (Exception e) {
			System.out.println("ih, deu esse erro aq: " + e);
		}
	}
}

	// ~~~~~~~~~~ parte de recebimento ~~~~~~~~~~

class AloThread extends Thread {
	public AloThread() {}
	public void run() {

		try {
			byte[] receiveData = new byte[1000];

			DatagramSocket receiveSocket = new DatagramSocket(8888);
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				receiveSocket.receive(receivePacket);
				String messageReceived = new String(receivePacket.getData(), 0, receivePacket.getLength());
				System.out.println("infraparrotie: " + messageReceived);
			}
			
		}catch (Exception e) {
			System.out.println("ih, deu esse erro aq: " + e);
		}
	}
}

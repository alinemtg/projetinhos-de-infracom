
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server_UDP {

	public static void main(String[] args) throws IOException {

		DatagramSocket serverSocket = new DatagramSocket(5000); // abrindo socket na porta 5000
		byte[] receiveData = new byte[1000];
		byte[] sendData;
		InetAddress clientIP;
		int port;

		while(true) {
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); // definindo o pacote que vai receber os dados do pacote inicial, do cliente
			serverSocket.receive(receivePacket); // recebendo os dados

			clientIP = receivePacket.getAddress(); // pega o IP
			port = receivePacket.getPort(); // pega a porta
			
			String messageReceived = new String(receivePacket.getData(), 0, receivePacket.getLength()); // extrai a mensagem que ta nos dados do pacote
			System.out.println ("Recebemos esta mensagem: "+ messageReceived);

			// ~~~~~  pseudochatBot ~~~~~
			
			if (messageReceived.contains("oi") || !messageReceived.contains("basilio")) {
				sendData = "olaris".getBytes();
			} else if (messageReceived.contains("basilio")) {
				sendData = "sem tempo pra crossfiteiros irmao".getBytes();
			} else {
				sendData = "ué".getBytes();
			}   
			
			// ~~~~ end do pseudochatBot ~~~~~

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, port); // definindo pacote com dados a serem enviados
			serverSocket.send(sendPacket); // enviando o bixo
			System.out.println ("IHUUU, enviamos uma mensagem para o cliente");
		
		}
	}
}

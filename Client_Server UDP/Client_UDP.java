
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_UDP {

	public static void main (String args[]) throws IOException {

        DatagramSocket clientSocket = new DatagramSocket(); // 
        InetAddress IPServer = InetAddress.getByName("localhost"); // definindo o IP do servidor que vai se comunicar
        byte[] sendData;
        byte[] receiveData = new byte[1000];

        Scanner in = new Scanner(System.in); 
        while (true) {
        	
	        // ~~~~~ leitura da msg que se deseja enviar ~~~~~
	        
        	System.out.print ("Digite uma mensagenzinha pro servidor: ");
	        String sendDataS = in.nextLine();
	        
	        if (sendDataS.equals("cabousse")) {
	        	System.out.println("eh isto, tchau.");
	        	break;
	        }
	
	        // ~~~~~ enviando a msg lida ~~~~~
	        
	        sendData = (sendDataS).getBytes(); 
	        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPServer, 5000); // definindo os dados que vao ser enviados para o servidor
	        clientSocket.send(sendPacket); // enviando para o servidor
	       
	        long time1 = System.nanoTime();
	        System.out.println("Sua mensagem foi enviada nesse momento incrível marcado em nanosegundos: " + time1); // status 
	
	        // ~~~~~ recebendo resposta do servidor ~~~~~
	        
	        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	        clientSocket.receive(receivePacket);
	        long time2 = System.nanoTime();
	        String messageReceived = new String(receivePacket.getData(), 0, receivePacket.getLength()); // extrai a mensagem que ta nos dados do pacote
	        System.out.println ("O servidor mandou isso aq ó: " + messageReceived);
	        System.out.println ("RTT (em nanosegundos): " + (time2-time1)); // calcula rtt
	        
        }
        clientSocket.close();

	}
	
}


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client_TCP {
	public static void main (String[] args) throws IOException {
		int port = 3001;
		String address = "localhost";

		try {
			Socket socket = new Socket (address, port);
			Scanner in = new Scanner(System.in); 
			DataOutputStream outData;
			DataInputStream inData;

			while(true) {

				// ~~~~~ leitura da msg que se deseja enviar ~~~~~

				System.out.print ("Digite uma mensagenzinha pro servidor: ");
				String mensagenzinha = in.nextLine();

				// ~~~~~ enviando msg pro servidor ~~~~~

				outData = new DataOutputStream (socket.getOutputStream()); 
				outData.writeUTF(mensagenzinha);

				long time1 = System.nanoTime();
				System.out.println("Sua mensagem foi enviada nesse momento incrível marcado em nanosegundos: " + time1); // status 
				
				if (mensagenzinha.equals("cabousse")) {
					System.out.println("eh isto, tchau.");
					break;
				}
				
				// ~~~~~ recebendo resposta ~~~~~

				inData = new DataInputStream (socket.getInputStream());
				long time2 = System.nanoTime();
				String stringuiline = inData.readUTF();
				System.out.println ("O servidor mandou isso aq ó: " + stringuiline); 
				System.out.println ("RTT (em nanosegundos): " + (time2-time1)); // calcula rtt
				
			}
			socket.close();

		} catch (ConnectException e) {
			System.out.print("Não deu pra chegar ao destino, sorry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
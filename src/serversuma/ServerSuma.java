package serversuma;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSuma{

	private static int PORT = 9001; //Se define el puerto
	
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT); //Se define el socket con el puerto definido
                System.err.println("Server listening on port " + PORT +" using TCP connection \n");
		int suma=0; //Variable donde se guardara la suma de los dos números
		String num1, num2; //Variables usadas para almacenar los números ingresador por el cliente
		try{
			while(true){ //BUCLE QUE SIEMPRE VA A DEJAR AL SERVER EN ESCUCHA 
				Socket socket = serverSocket.accept(); //Acepta la conexión
				DataInputStream datRecibe = new DataInputStream(socket.getInputStream()); //Canal de entrada para los datos del cliente
				try{ //Recupera los datos ingresados por el cliente
                                    //Canales de entrada
						num1=datRecibe.readUTF(); //Se recibe el primer número
						
                                                num2=datRecibe.readUTF(); //Se recibe el segundo número
                                                
						suma=Integer.parseInt(num1)+Integer.parseInt(num2); //Se transforma los números recibidos a enteros para poder realizar la operacion
					PrintWriter out = new PrintWriter(socket.getOutputStream(),true); //Generar un canal de salida para la respuesta
					out.println("suma= "+String.valueOf(suma)); //Impime la respuesta
				} finally{
					socket.close(); //Se cierra la conexión
				}
			}
			
		}finally{
			serverSocket.close(); //Se cierra el servidor
		}
	}
}

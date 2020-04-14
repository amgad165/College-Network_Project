package thread;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client {
	


		 
		
	
	static String username[]={"user","user1","user2","user3"};
	static String passwords[]={"pass","pass1","pass2","pass3"};
	static String userN;
	static String passW;
	static String current_user;
	static String sent_to;

	public static void main(String args[]) {

		try {			 
			   


			InetAddress ip = InetAddress.getByName("localhost");
			Socket clientSocket = new Socket(ip, 4000);
			System.out.println("Connecting to server...");
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			
			Scanner scanner = new Scanner(System.in);
			while (true){
				String login_user=input.readUTF();
				System.out.println("server: "+login_user);

			userN=scanner.nextLine();
			output.writeUTF(userN);
		String confirm_user=input.readUTF();
			if (confirm_user.equalsIgnoreCase("please Enter your password :")){
				System.out.println("server: "+confirm_user);
				passW=scanner.nextLine();
				output.writeUTF(passW);
			String confirm_passW=input.readUTF();
			 if (confirm_passW.equalsIgnoreCase("Username and password confirmed")){
					System.out.println("server: "+confirm_passW);


					break;
					
				
				}
			 else if(confirm_passW.equalsIgnoreCase("password is incorrect")){
					System.out.println("server: "+confirm_passW);
					continue;

				}
			}
			else if(confirm_user.equalsIgnoreCase("username is incorrect")){
				System.out.println("server: "+confirm_user);
				continue;

			}
			
				
				
			
			
			else{
				continue;
			}
			}
			String confirm_connection=input.readUTF();
			System.out.println("Server: " + confirm_connection);

			
			while (true) {
				
				String serverAsk = input.readUTF();
				System.out.println("Server: " + serverAsk);

			String 	choice =scanner.nextLine();
				output.writeUTF(choice);
				if (choice.equalsIgnoreCase("send new")){

					String subject=input.readUTF();
					System.out.println("Server: "+ subject);
					String subject_inp=scanner.nextLine();
					output.writeUTF(subject_inp);
					String to=input.readUTF();
					System.out.println("Server: "+ to);
					String to_inp=scanner.nextLine();
					output.writeUTF(to_inp);
		
					
					String body=input.readUTF();

					System.out.println("Server: "+ body);

	
					while(true){
						
					String body_inp=scanner.nextLine();

					output.writeUTF(body_inp);
					if (body_inp.equalsIgnoreCase(".")){
						String confirm_sent=input.readUTF();
						System.out.println("server: "+confirm_sent);

						break;
					}
					else{
						continue;
					}
					}
					

				continue;
				}
				else if (choice.equalsIgnoreCase("show mailbox")){
					
					String SENTorRECEIVED=input.readUTF();
					System.out.println("server: "+SENTorRECEIVED);
					
				String	sent_received=scanner.nextLine();
				output.writeUTF(sent_received);
				if (sent_received.equalsIgnoreCase("sent")){
		        	System.out.println("server : ");

				      while (true){ 
				        String read_f=input.readUTF(); 
				        if (read_f.equalsIgnoreCase("mailbox sent")){
				        	break;
				        }
				        else{
				        	System.out.println( read_f);
				        }
				        
				}

					
					}
				else if(sent_received.equalsIgnoreCase("received")){
					
					System.out.println("server : ");

				      while (true){ 
				        String read_f=input.readUTF(); 
				        if (read_f.equalsIgnoreCase("mailbox sent")){
				        	break;
				        }
				        else{
				        	System.out.println( read_f);
				        }
				        
				}

					
				}
				
				continue;
				
}					
				else if(choice.equalsIgnoreCase("show mail")){
					String id=input.readUTF();
					System.out.println("server: "+ id);
					int id_inp=scanner.nextInt();
					output.writeInt(id_inp);
					System.out.println("server:");

				      while (true){ 
				        String read_f=input.readUTF(); 
				        if (read_f.equalsIgnoreCase("mail sent")){
				        	break;
				        }

				        else{
						System.out.println(read_f);
						

				        }
				}
				      continue;}
				else if (choice.equalsIgnoreCase("close")) {
					System.out.println("Closing the connection with the server");
					clientSocket.close();
					System.out.println("The connection is closed");
					break;
				}

						continue;
					
			
			}
			scanner.close();
			input.close();
			output.close();

			} catch (IOException e) {
			System.out.println("Connection with server terminated");
		}
			
	}

}

package thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class server {

	static String username[]={"user","user1","user2","user3"};
	static String passwords[]={"pass","pass1","pass2","pass3"};
	static String userN;
	static String passW;
	static String current_user;
	static String sent_to;

	static ServerSocket serverSocket;

	public static void main(String args[]) {
		try {
			serverSocket = new ServerSocket(4000);
			System.out.println("Server started");
			
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("A New client [" + clientSocket + "] connected");
				Thread Client = new ClientConnection(clientSocket);
				Client.start();
			}
		} catch (Exception e) {
			System.out.println("Problem with socket server");
		}
	}

	static class ClientConnection extends Thread {
		final private Socket clientSocket;

		public ClientConnection(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public void run() {
			try {

				DataInputStream input = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
				Scanner scanner = new Scanner(System.in);
				outer:
					while (true){
					output.writeUTF("Enter your username :");
					userN=input.readUTF();
					inner:
					for(int i =0 ; i<4;i++) {
						
						if(userN.equalsIgnoreCase(username[i])){
							
							output.writeUTF("please Enter your password :");
							
							passW=input.readUTF();
							if (passW.equalsIgnoreCase(passwords[i])){
								output.writeUTF("Username and password confirmed");
								current_user=userN;
								break outer;

							}
							else {
								output.writeUTF("password is incorrect");
								continue outer;

							}
						}
						else if(i==3) {
							output.writeUTF("username is incorrect");
							i=0;
							continue outer;

							
						}
						continue inner;
						
					}}
				output.writeUTF("Connected");

				 
					int i=0;
					int id=0;

				while (true) {				

					FileWriter mywriter = new FileWriter("mail_sent\\"+current_user+".txt",true);
					FileWriter mywriter3 = new FileWriter("iterator\\"+current_user+".txt",true);


					output.writeUTF(" OPTIONS: SEND NEW, SHOW MAILBOX, SHOW MAIL mail_id, Close");
					String choice = input.readUTF();
					

					if (choice.equalsIgnoreCase("send new")){
						++i;
						mywriter3.write(i);
						mywriter3.write("\n");
						mywriter3.close();



						BufferedReader reader = new BufferedReader(new FileReader("iterator\\"+current_user+".txt"));
						int lines = 0;
						while (reader.readLine() != null){ 
							lines++;}
						
						reader.close();
						mywriter.write("\n");

						mywriter.write("mail"+lines);

						mywriter.write("\n");
						mywriter.write("from:");

						mywriter.write(current_user);
						
						output.writeUTF("subject:");
						String subject_inp=input.readUTF();
						mywriter.write("\n");
						mywriter.write("subject:");
						mywriter.write(subject_inp);
						output.writeUTF("to: ");
						
						String to_inp=input.readUTF();
						

						mywriter.write("\n");
						mywriter.write("to: ");
						mywriter.write(to_inp);
						sent_to=to_inp;
						//Start data implementation here
						output.writeUTF("Data: ");


						//write into the received mail file
						 FileWriter mywriter2 = new FileWriter("mail_received\\"+sent_to+".txt",true);

							mywriter2.write("\n");
							mywriter2.write("mail"+i);

						 mywriter2.write("from:");
						mywriter2.write(current_user);
						mywriter2.write("\n");
						mywriter2.write("subject:");
						mywriter2.write(subject_inp);
						
					

						mywriter.write("\n");
						//continue here
						mywriter.write("body: ");
						while(true){
							
						String body_inp=input.readUTF();
						if(body_inp.equalsIgnoreCase(".")){
							
							output.writeUTF("mail sent succesfully");

						    mywriter.close();

							break;
						
						}
						else{
						mywriter.write("\n");
						mywriter.write(body_inp);
						mywriter2.write("\n");
						mywriter2.write("body: ");
						mywriter2.write(body_inp);

						continue;
						}
						}
					    

					    mywriter2.close();
					}

						
					else if (choice.equalsIgnoreCase("show mailbox")) {
						
						output.writeUTF("sent messages or received messages ?");
					String	sent_received=input.readUTF();
					if (sent_received.equalsIgnoreCase("sent")){
						
						      File file = new File("mail_sent\\"+current_user+".txt"); 
						      
						      BufferedReader br = new BufferedReader(new FileReader(file)); 
						      
						      String st; 
						      while ((st = br.readLine()) != null) {
						        output.writeUTF(st); 
						      }
						      output.writeUTF("mailbox sent");
						    }
					else if (sent_received.equalsIgnoreCase("received")){
						File file = new File("mail_received\\"+current_user+".txt"); 
					      
					      BufferedReader br = new BufferedReader(new FileReader(file)); 
					      
					      String st; 
					      while ((st = br.readLine()) != null) {
					        output.writeUTF(st); 
					      }
					      output.writeUTF("mailbox sent");
						
						
					}
					
					continue;}
					
					else if(choice.equalsIgnoreCase("show mail")){
						output.writeUTF("Enter Email id :");
						int id_inp=input.readInt();
						
						File file = new File("mail_sent\\"+current_user+".txt"); 
					      
					      BufferedReader br = new BufferedReader(new FileReader(file)); 
					      int k=0;
					      String st;
					      String str=""+id_inp;
					      String str1=""+(id_inp+1);
					      
						      boolean  h=false;

							   
					      while ((st = br.readLine()) != null) {
					    	  k++;

					    	  if (st.equalsIgnoreCase("mail"+str)){
					    		 h=true;
						     

					    		
					    	  }
					    	  else if(st.equalsIgnoreCase("mail"+str1)){
					    		  
					    		  h=false;

					    		  break;
					    	  }
					    	

					    	  if(h==true){
					    		  
						        	output.writeUTF(st);
						        	

					    	  
					      }
					    	  else if(h==false){
					    		  continue;
					    	  }
					    	
					    	

					 

  
					    	  
					      }
					      
					     

					    
						
			    		  output.writeUTF("mail sent");
						
						
					}
						
					
					else if (choice.equals("close")) {
						System.out.println("Closing the connection with this client [" + clientSocket + "]");
						clientSocket.close();

						break;
					}
					else {
						continue;

						
					}

				}

				scanner.close();
				input.close();
				output.close();
			} catch (IOException e) {
				System.out.println("Connection with this client [" + clientSocket + "] is terminated");
			}
		}
	}
}
import java.net.*;
import java.io.*;
class Client
{
	public static void main(String[] args) 
	{
		String [] songChoice = new String[5];
		try
				{
				//will stop when track is recieved
					Socket s = new Socket("192.168.1.1",7000);
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					DataInputStream dis = new DataInputStream(s.getInputStream());
					for(int i =0;i<5;i++)
						songChoice[i] =""+dis.readUTF();
					
					
					dos.writeUTF(songChoice[0]);
					
					
					dis.close();
					s.close();
					
				}
				catch(Throwable t)
				{
					System.out.print("client error");
				}
	}
}
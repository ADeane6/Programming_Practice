import java.net.*;
import java.io.*;
import java.util.*;

class ClientV2
{
	public static void main(String[] args) 
	{
		String [] songChoice = new String[5];
		try
			{
					//will stop when track is recieved
					Socket s = new Socket("localhost",7000);
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					DataInputStream dis = new DataInputStream(s.getInputStream());
					int k = 1;
					while (k != 0)
					{
					for(int i =0;i<5;i++)
					{
						songChoice[i] =""+dis.readUTF();
						System.out.println((i+1)+": " + songChoice[i]);
					}
					
					System.out.print("Please Choose a song(1-5): ");
					Scanner scanner = new Scanner(System.in);
					k = scanner.nextInt();
					if (k == 0)
						break;

					dos.writeUTF(songChoice[k-1]);
					}
					
					
					dis.close();
					s.close();
				
			}
			catch(IOException e)
			{
				System.out.print("client error\n" + e);
			}
	}
}
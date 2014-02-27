import java.net.*; 
import java.io.*;
import java.util.*;

//Globals--------------------------------------------------------------------------------
class Lists
{
	public static String [] tracks = new String[100]; //User friendly Song names
	public static String [] songs = new String[100]; //Directory location of songs
	public static String [] votes = new String[5]; //Current Vote Options
	public static int voteCast = 0; //Number of votes cast

}

//Main Class----------------------------------------------------------------------------
class ServerSocV1
{
	//RNG for five vote options
	public static int RnG(int i)
	{
		Random rn = new Random();
		return (rn.nextInt(18)+0);
	}

	//Main where Lists are filled and Thread is started
	public static void main(String[] args) 
	{
		try //Try catch block for sockets
		{
			int clientNum = 0; //Number of active clients
			
			int count = 0; //number of total songs
			
			//Directory location of songs	
			File songF = new File("songs.txt");
			Scanner read = new Scanner(songF);
			while(read.hasNextLine())
			{
				Lists.songs[count]=read.nextLine();
				count++;
			}

			//User friendly Song names
			File trackF = new File("tracks.txt");
			Scanner scan = new Scanner(trackF);
			count=0;
			while(scan.hasNextLine())
			{
				Lists.tracks[count]=scan.nextLine();
				count++;
			}

			//filling Vote list for first time
			for (int i = 0; i < 5; i++)
			{
				Lists.votes[i] = Lists.tracks[RnG(count)];
			}


			System.out.println("Listening for connections");
			boolean listening= true;
			ServerSocket serverSocket= new ServerSocket(7000);

			while(listening)
			{
				Socket s = new Socket();
				s = serverSocket.accept();
				clientNum++; //Increment Client after connection established
				new ClientHandler(s).start(); //Start the thread

				//check if all votes cast
				if (Lists.voteCast == clientNum)
				{
					//New vote options
					for (int i = 0; i < 5; i++)
					{
						Lists.votes[i] = Lists.tracks[RnG(count)];
					}
					Lists.voteCast = 0; //Reset
				}
			}
		}
		catch(IOException e)
		{
			System.out.println("couldn't listen on port 7000\n" + e);
		}
	}
}
//Custom Thread----------------------------------------------------------------------------
class ClientHandler extends Thread
{
	private Socket socket = null;

	public ClientHandler(Socket socket)
	{
		super("ClientHandler");
		this.socket=socket;
	}

	public void run()
	{
		
	
		try
		{
				
			//Output Song for Vote
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			for(int i=0;i<5;i++)
			{
	           	out.writeUTF(Lists.votes[i]);
          	}

		  	//Get vote option
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String song=""+in.readUTF();
			for(int i=0;i<Lists.tracks.length;i++)
			{		
				if(song.equals(Lists.tracks[i]))
				song = Lists.songs[i];
			}

			System.out.println(song); //Print Vote

			Lists.voteCast++; //Inc VoteCast
		
			socket.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

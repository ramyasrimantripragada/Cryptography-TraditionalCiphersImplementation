import java.util.*;
public class Cryptography
{
	ArrayList<String> up=new ArrayList<String>(
	List.of("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
	ArrayList<String> low=new ArrayList<String>(
	List.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
	Random r=new Random();
	private String msg;
	private String key;
	Cryptography(){}
	Cryptography(String msg,String key)
	{
		this.msg=msg;
		this.key=key;
	}
	public void setObj(String msg,String key)
	{
		this.msg=msg;
		this.key=key;
	}
	public String getKey()
	{
		return this.key;
	}
	public String ceaserEncrypt()
	{
		String emsg="";
		String s[]=(msg).split("");
		int key=1+r.nextInt(24);
		for(String i:s)
		{
			if(up.contains(i))
			{
				int index=up.indexOf(i);
				index=(index+key)%26;
				emsg=emsg+up.get(index);
			}
			else if(low.contains(i))
			{
				int index=low.indexOf(i);
				index=(index+key)%26;
				emsg=emsg+low.get(index);
			}
			else
			    emsg=emsg+i;
		}
		this.key=Integer.toString(key);
		return emsg;
	}
	public String ceaserDecrypt()
	{
		String dmsg="";
		String s[]=(msg).split("");
		int key=Integer.parseInt(this.key);
		for(String i:s)
		{
			if(up.contains(i))
			{
				int index=up.indexOf(i);
				index=(index-key+26)%26;
				dmsg=dmsg+up.get(index);
			}
			else if(low.contains(i))
			{
				int index=low.indexOf(i);
				index=(index-key+26)%26;
				dmsg=dmsg+low.get(index);
			}
			else
			    dmsg=dmsg+i;
		}
		this.key=Integer.toString(key);
		return dmsg;
	}
	public String columnarEncrypt()
	{
		String emsg="";
		int length=msg.length();
		int row=2+r.nextInt(length/2);
		int col=length/row+1;
		if(length<(row*col))
		{
			for(int i=0;i<row*col-length;i++)
			   this.msg=this.msg+"@";
		}
		else
		    col=col-1;
		String s[]=(msg).split("");
		String m[][]=new String[row][col];
		int i,j,count=0;
		for(i=0;i<row;i++)
		    for(j=0;j<col;j++)
		        m[i][j]=s[count++];
		String t[][]=new String[col][row];
		for(i=0;i<col;i++)
		    for(j=0;j<row;j++)
		        t[i][j]=m[j][i];
		for(i=0;i<col;i++)
		    for(j=0;j<row;j++)
		        emsg=emsg+t[i][j];
		this.key=Integer.toString(row)+"-"+Integer.toString(col);
		return emsg;
	}
	public String columnarDecrypt()
	{
		String dmsg="";
		String d[]=(key).split("-");
		int row=Integer.parseInt(d[1]);
		int col=Integer.parseInt(d[0]);
		String t[][]=new String[row][col];
		String s[]=(msg).split("");
		int count=0,i,j;
		for(i=0;i<row;i++)
		    for(j=0;j<col;j++)
		        t[i][j]=s[count++];
		String m[][]=new String[col][row];
		for(i=0;i<col;i++)
		    for(j=0;j<row;j++)
		        m[i][j]=t[j][i];
		for(i=0;i<col;i++)
		    for(j=0;j<row;j++)
		        if(!m[i][j].equals("@"))
					dmsg=dmsg+m[i][j];
		return dmsg;
	}
	public static void main(String args[])
	{
		System.out.println("\n\t\t\t****************************************\n\t\t\t*CRYPTOGRAPHY USING TRADITIONAL CIPHERS*\n\t\t\t****************************************\n\n\n\n");
		System.out.println("\t\t\tMADE BY: RAMYA SRI MANTRIPRAGADA");
		System.out.println("\n\n\n\n\n\t\t\t\tPRESS ANY KEY TO CONTINUE!!");
		Scanner s=new Scanner(System.in);
		String e=s.nextLine();
		System.out.print("\033[H\033[2J");
		Random r=new Random();
		System.out.print("Do you want to encrypt of decrypt your message:[y/n] ");
		String choice=s.nextLine();
		Cryptography c=new Cryptography();
		while(choice.equals("y")||choice.equals("Y"))
		{ 
			System.out.print("\033[H\033[2J");
			System.out.print("What do you want to do: \n1.Encryption \n2.Decryption: \n\nEnter your choice: ");
			String ch=s.nextLine();
			if(ch.equals("1"))
			{
				System.out.print("\nEnter a message: " );
				String msg=s.nextLine();
				c.setObj(msg,"0");
				int num=1+r.nextInt(2);
				if(num==1)
					System.out.println("\n\nYour encrypted message: "+c.columnarEncrypt()+"\nYour key: "+c.getKey());
				else
					System.out.println("\n\nYour encrypted message: "+c.ceaserEncrypt()+"\nYour key: "+c.getKey());
			}
			else if(ch.equals("2"))
			{
				System.out.print("\nEnter your encrypted message: " );
				String msg=s.nextLine();
				System.out.print("\nEnter key: ");
				String key=s.nextLine();
				c.setObj(msg,key);
				if(key.contains("-"))
				   System.out.println("\n\nYour decrypted message: "+c.columnarDecrypt());
				else
				   System.out.println("\n\nYour decrypted message: "+c.ceaserDecrypt());
			  }
			  else
			      System.out.println("\n\nWrong choice");
			  System.out.print("\n\nDo you want to continue: [y/n] ");
			  choice=s.nextLine();	
		}
		System.out.print("\033[H\033[2J");
		System.out.println("\n\n\t\t\t*SUCCESSFULLY TERMINATED!!*\n\n\n\t\t\t\t*THANKYOU!!*");
		
	}
}

//server
import java.util.*;
import java.net.*;
import java.io.*;

public class ServerCRC{
	public static void main(String[] args) throws Exception{
		ServerSocket ws = new ServerSocket(2000);
		while(true){
			System.out.println("Server is running...\n");

			Socket cons = ws.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(cons.getInputStream()));
			String dividend =inFromClient.readLine();
			String divisor =inFromClient.readLine();
			System.out.println("Codeword Received: "+dividend);
			System.out.println("Divisor: "+divisor);

			String rem= mod2Div(dividend, divisor);
			System.out.println("Remainder: "+rem);

			if(rem.equals("0")){
				System.out.println("No Error");
			}
			else{
				System.out.println("Error");
			}

			inFromClient.close();
		}
	}
	public static String mod2Div(String dividend_str,String divisor_str)
    {
        List<Integer> dividend=new ArrayList<>();
        List<Integer> divisor=new ArrayList<>();
        for(char c: dividend_str.toCharArray())
        {
            dividend.add(Character.getNumericValue(c));
        }
        for(char c: divisor_str.toCharArray())
        {
            divisor.add(Character.getNumericValue(c));
        }
        List<Integer> quo=new ArrayList<>();
        int n=divisor.size();
        while(dividend.size()>=n)
        {
            if(dividend.get(0)==0)
            {
                quo.add(0);
            }
            else
            {
                quo.add(1);
            }
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                temp.add(dividend.get(i)^divisor.get(i));
            }
            while(temp.size()!=0 && temp.get(0)==0)
            {
                temp.remove(0);
            }
            List<Integer> sublist = new ArrayList<>(dividend.subList(n, dividend.size()));
            dividend.clear();
            dividend.addAll(temp);
            dividend.addAll(sublist);

        }
        String rem = "";
        for (Integer num : dividend) {
            rem+= num;
        }
		if(rem.length()==0)
            return "0";
        return rem;
    }
}

//client
import java.util.*;
import java.io.*;
import java.net.*;

class ClientCRC{
	public static void main(String[] args) throws Exception{

		BufferedReader informUser= new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket= new Socket("localhost",2000);
        DataOutputStream outToServer=new DataOutputStream(clientSocket.getOutputStream());


        System.out.print("Enter the Dividend: ");
        String dividend=informUser.readLine();
        System.out.print("Enter the Divisor: ");
        String divisor=informUser.readLine();


		String tempDividend = dividend + "0".repeat(divisor.length()-1);
		System.out.println(tempDividend);

		String rem = mod2Div(tempDividend, divisor);
		System.out.println("The Remainder: "+rem);
		int len = rem.length();
		rem="0".repeat(divisor.length()-1-len)+rem;

		String codeWord = dividend+rem;
		System.out.println("CodeWord: "+codeWord);
		outToServer.writeBytes(codeWord+"\n");
		outToServer.flush();
		outToServer.writeBytes(divisor+"\n");
		outToServer.flush();

		outToServer.close();
        clientSocket.close();
		 
	}
	public static String mod2Div(String dividend_str,String divisor_str)
    {
        List<Integer> dividend=new ArrayList<>();
        List<Integer> divisor=new ArrayList<>();
        for(char c: dividend_str.toCharArray())
        {
            dividend.add(Character.getNumericValue(c));
        }
        for(char c: divisor_str.toCharArray())
        {
            divisor.add(Character.getNumericValue(c));
        }
        List<Integer> quo=new ArrayList<>();
        int n=divisor.size();
        while(dividend.size()>=n)
        {
            if(dividend.get(0)==0)
            {
                quo.add(0);
            }
            else
            {
                quo.add(1);
            }
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                temp.add(dividend.get(i)^divisor.get(i));
            }
            while(temp.size()!=0 && temp.get(0)==0)
            {
                temp.remove(0);
            }
            List<Integer> sublist = new ArrayList<>(dividend.subList(n, dividend.size()));
            dividend.clear();
            dividend.addAll(temp);
            dividend.addAll(sublist);

        }
        String rem = "";
        for (Integer num : dividend) {
            rem+= num;
        }
		if(rem.length()==0)
            return "0";
        return rem;
    }
}
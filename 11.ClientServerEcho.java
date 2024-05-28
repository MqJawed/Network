//Client program
import java.net.*;
import java.io.*;
public class Client
{
    public static void main(String args[])throws IOException
    {
        if((args.length<2)||(args.length>3))
            throw new IllegalArgumentException("Parameter(s):<server><word>[<port>]");
        String server=args[0];
        byte[] byteBuffer=args[1].getBytes();
        int servPort=(args.length==3)?Integer.parseInt(args[2]):7;
        Socket socket=new Socket(server,servPort);
        System.out.println("Connected to server...sending echo string");
        InputStream in=socket.getInputStream();
        OutputStream out=socket.getOutputStream();
        out.write(byteBuffer);
        int totalBytesRcvd=0;
        int bytesRcvd;
        while(totalBytesRcvd<byteBuffer.length)
        {
            if((bytesRcvd=in.read(byteBuffer,totalBytesRcvd,byteBuffer.length-
            totalBytesRcvd))==-1)
                throw new SocketException("Connection closed prematurely");
            totalBytesRcvd+=bytesRcvd;
        }
        System.out.println("Received :"+new String(byteBuffer));
        socket.close();
    }
}



import java.net.*;
import java.io.*;
public class Server
{
    private static final int BUFSIZE=80;
    public static void main(String args[])throws IOException
    {
        if(args.length!=1)
            throw new IllegalArgumentException("Parameter(s):<Port>");
        int servPort=Integer.parseInt(args[0]);
        ServerSocket servSock=new ServerSocket(servPort);
        int recvMsgSize;
        byte[] byteBuffer=new byte[BUFSIZE];
        while(true)
        {
            Socket clntSock=servSock.accept();
            InputStream in=clntSock.getInputStream();
            OutputStream out= clntSock.getOutputStream();
            while((recvMsgSize=in.read(byteBuffer))!=-1)
                out.write(byteBuffer,0,recvMsgSize);
            clntSock.close();
        }
    }
}

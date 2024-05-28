import java.net.InetAddress;
import java.net.UnknownHostException;
class LocalRemote {
    public static void main(String[] args) {
        try{
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getByName("programiz.com").getHostAddress());
        }catch (UnknownHostException ue){
            ue.printStackTrace();
        }
    }
}

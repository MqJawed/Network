import java.util.Scanner;

public class ClassNetHost {
    public static void main(String[] args) {
        String arr[] = new String[4];
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        arr = ip.split("\\.");
        while (arr.length != 4){
            System.out.println("Invalid Ip");
            ip =  scanner.nextLine();
            arr = ip.split("\\.");
        }
        if(arr!=null){
            int first = Integer.parseInt(arr[0]);
            if(first>=0 && first<=127){
                System.out.println("Class A");
                System.out.println("Network ID: "+arr[0]+".0.0.0");
                System.out.println("Host ID: "+ip);
            }else if(first>=128 && first<=191){
                System.out.println("Class B");
                System.out.println("Network ID: "+arr[0]+"."+arr[1]+".0.0");
                System.out.println("Host ID: "+ip);
            }else if(first>=192 && first<=223){
                System.out.println("Class C");
                System.out.println("Network ID: "+arr[0]+"."+arr[1]+"."+arr[2]+".0");
                System.out.println("Host ID: "+ip);
            }else if(first>=224 && first<=239){
                System.out.println("Class D");
                System.out.println("MultiCasting");
            }else if(first>=240 && first<=255){
                System.out.println("Class E");
                System.out.println("Experimental");
            }else{
                System.out.println("Invalid IP");
            }
        }
        
    }
}


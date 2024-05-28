import java.util.Scanner;

public class NetOnly {
    public static void main(String[] args) {
        System.out.println("Enter IP with subnet in slash: ");
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        String arr[] = new String[2];
        arr = ip.split("/");
        int subnet = Integer.parseInt(arr[1]);
        if(subnet%8!=0){
            System.out.println("Invalid subnet");
            return;
        }
        String iponly[] = new String[4];
        iponly = arr[0].split("\\.");
        int i = 0;
        String netid = "";
        while(subnet != 0 || i!=4){
            if(subnet != 0){
                netid += (Integer.parseInt(iponly[i++])&255) + "";
                subnet -= 8;
            }else{
                netid += "0";
                i++;
                if(i==4){
                    break;
                }
            }
            netid+=".";
        }
        System.out.println(netid);
    }

}


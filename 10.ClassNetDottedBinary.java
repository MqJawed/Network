import java.util.Scanner;

public class ClassNetDottedBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an IP address:");
        String ip = scanner.nextLine();

        String[] octets;
        boolean isBinary = false;

        // Check if the input is in binary format
        if (ip.matches("[01]+(\\.[01]+){3}")) {
            isBinary = true;
            octets = ip.split("\\.");
        } else {
            octets = ip.split("\\.");
            if (octets.length != 4 || !isValidDecimalOctets(octets)) {
                System.out.println("Invalid IP address format.");
                return;
            }
        }

        int first = Integer.parseInt(octets[0], isBinary ? 2 : 10);

        if ((first >= 0 && first <= 127) || (isBinary && first >= 0 && first <= 0b01111111)) {
            System.out.println("Class A");
            System.out.println("Network ID: " + octets[0] + ".0.0.0");
            System.out.println("Host ID: " + (isBinary ? ip : octetsToDecimal(octets)));
        } else if ((first >= 128 && first <= 191) || (isBinary && first >= 0b10000000 && first <= 0b10111111)) {
            System.out.println("Class B");
            System.out.println("Network ID: " + octets[0] + "." + octets[1] + ".0.0");
            System.out.println("Host ID: " + (isBinary ? ip : octetsToDecimal(octets)));
        } else if ((first >= 192 && first <= 223) || (isBinary && first >= 0b11000000 && first <= 0b11011111)) {
            System.out.println("Class C");
            System.out.println("Network ID: " + octets[0] + "." + octets[1] + "." + octets[2] + ".0");
            System.out.println("Host ID: " + (isBinary ? ip : octetsToDecimal(octets)));
        } else if ((first >= 224 && first <= 239) || (isBinary && first >= 0b11100000 && first <= 0b11101111)) {
            System.out.println("Class D");
            System.out.println("Multicasting");
        } else if ((first >= 240 && first <= 255) || (isBinary && first >= 0b11110000 && first <= 0b11111111)) {
            System.out.println("Class E");
            System.out.println("Experimental");
        } else {
            System.out.println("Invalid IP address.");
        }
    }

    private static boolean isValidDecimalOctets(String[] octets) {
        for (String octet : octets) {
            try {
                int num = Integer.parseInt(octet);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static String octetsToDecimal(String[] octets) {
        StringBuilder decimalIP = new StringBuilder();
        for (String octet : octets) {
            decimalIP.append(Integer.parseInt(octet, 2)).append(".");
        }
        return decimalIP.substring(0, decimalIP.length() - 1);
    }
}

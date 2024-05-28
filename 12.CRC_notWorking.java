import java.util.*;

public class CRCErrorDetection {
    private static final int[] CRC_DIVISOR_BITS = {1, 1, 0, 1}; // CRC-4 polynomial x^3 + x + 1

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data bits
        System.out.print("Enter data bits (binary): ");
        String dataBitsString = scanner.nextLine();
        int[] dataBits = new int[dataBitsString.length()];
        for (int i = 0; i < dataBitsString.length(); i++) {
            dataBits[i] = Character.getNumericValue(dataBitsString.charAt(i));
        }

        // Perform CRC division
        int[] remainder = divideByCRC(dataBits);

        // Append remainder to original data bits
        int[] codeword = new int[dataBits.length + remainder.length];
        System.arraycopy(dataBits, 0, codeword, 0, dataBits.length);
        System.arraycopy(remainder, 0, codeword, dataBits.length, remainder.length);

        System.out.println("Codeword with CRC: " + Arrays.toString(codeword));
        System.out.println("Send this codeword to the receiver.");

        // Simulate transmission (flip some bits randomly)
        simulateTransmission(codeword);

        // Receiver side
        // Calculate CRC remainder
        int[] receivedDataBits = Arrays.copyOf(codeword, codeword.length);
        int[] receivedRemainder = divideByCRC(receivedDataBits);

        // Check if remainder is zero (no error detected)
        boolean noError = true;
        for (int bit : receivedRemainder) {
            if (bit != 0) {
                noError = false;
                break;
            }
        }

        if (noError) {
            System.out.println("No error detected. Data received successfully.");
        } else {
            System.out.println("Error detected. Data received may be corrupted.");
        }

        scanner.close();
    }

    // Perform CRC division
private static int[] divideByCRC(int[] dataBits) {
    int[] remainder = new int[3]; // For CRC-4

    for (int i = 0; i < dataBits.length; i++) {
        if (remainder[0] == 1) {
            for (int j = 0; j < 3; j++) {
                remainder[j] = remainder[j] ^ CRC_DIVISOR_BITS[j];
            }
        }
        if (i < dataBits.length - 1) {
            shiftRight(remainder);
            remainder[2] = dataBits[i];
        }
    }

    return remainder;
}
    // Simulate transmission by flipping some bits randomly
    private static void simulateTransmission(int[] codeword) {
        Random random = new Random();
        for (int i = 0; i < codeword.length; i++) {
            if (random.nextDouble() < 0.1) { // Flip 10% of bits
                codeword[i] = (codeword[i] == 0) ? 1 : 0;
            }
        }
        System.out.println("Simulated transmission: " + Arrays.toString(codeword));
    }

    // Helper method to shift bits right by 1 position
    private static void shiftRight(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = 0;
    }
}


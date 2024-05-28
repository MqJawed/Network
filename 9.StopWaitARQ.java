import java.util.Scanner;

public class StopAndWaitARQ {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of frames to transmit: ");
        int numFrames = scanner.nextInt();
        
        int[] frames = new int[numFrames];
        System.out.println("Enter " + numFrames + " frames:");
        for (int i = 0; i < numFrames; i++) {
            frames[i] = scanner.nextInt();
        }
        
        System.out.println("\nWith Stop-and-Wait ARQ, the frames will be sent in the following manner:");
        
        for (int i = 0; i < numFrames; i++) {
            System.out.println("Sending frame " + frames[i]);
            System.out.print("Enter ACK for frame " + frames[i] + ": ");
            int ack = scanner.nextInt();
            if (ack == frames[i]) {
                System.out.println("Received ACK for frame " + frames[i]);
            } else {
                System.out.println("NACK received for frame " + frames[i] + ". Resending frame.");
                i--; // Resend the current frame
            }
        }
        
        System.out.println("\nAll frames transmitted successfully.");
        scanner.close();
    }
}


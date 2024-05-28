import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class SelectiveRepeatARQ {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter window size: ");
        int windowSize = scanner.nextInt();
        
        System.out.print("Enter number of frames to transmit: ");
        int numFrames = scanner.nextInt();
        
        int[] frames = new int[numFrames];
        System.out.println("Enter " + numFrames + " frames:");
        for (int i = 0; i < numFrames; i++) {
            frames[i] = scanner.nextInt();
        }
        ArrayList<Integer> que = new ArrayList<>();

        System.out.println("\nWith Selective Repeat ARQ, the frames will be sent in the following manner:");
        int i = 0;
        int p = 0;
        while(i<frames.length){
            for (; i < windowSize; i++) {
                System.out.println("Sending frame " + frames[i]);
                que.add(frames[i]);
            }
            
            for (; p < windowSize; p++) {
                System.out.print("Enter ACK for frame " + frames[p] + ": ");
                int ack = scanner.nextInt();
                if (ack == frames[p]) {
                    System.out.println("Received ACK for frame " + frames[p]);
                    que.remove(Integer.valueOf(frames[p]));
                } else {
                    System.out.println("NACK received for frame " + frames[p]);
                }
            }
            while(!que.isEmpty()){
                for (int j = 0; j < que.size(); j++) {
                    System.out.println("ReSending frame " + que.get(j));
                }
                
                for (int j = 0; j < que.size(); j++) {
                    System.out.print("Enter ACK for frame " + que.get(j) + ": ");
                    int ack = scanner.nextInt();
                    if (ack == que.get(j)) {
                        System.out.println("Received ACK for frame " + que.get(j));
                        que.remove(Integer.valueOf(que.get(j)));
                    } else {
                        System.out.println("NACK received for frame " + que.get(j));
                    }
                }
            }
            i = windowSize;
            p = windowSize;
            windowSize = Math.min(2*windowSize,frames.length);
            System.out.println();
        }
        
        
        
        System.out.println("\nAll frames transmitted successfully.");
        scanner.close();
    }
}


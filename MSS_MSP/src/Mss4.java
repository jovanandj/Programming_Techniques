import java.util.Arrays;

public class Mss4 {

    //  This method is a modified version of MSS4, which outputs the first and last position
//  of the maximum subsequence sum.
    public static int[] mss4(int[] a, int p1, int p2) {
        int maxSum = 0, sum = 0, i = p1;
        //initialize first and last position variables
        int firstPositionCounter = 0, first = -1;
        int last = -1;
        int[] answer = new int[3];

        while (i < p2) {
            sum = sum + a[i];
            if (sum > maxSum) {
                maxSum = sum;
                //Save positions of the max subsequence
                first = firstPositionCounter;
                last = i;
            } else if (sum < 0) {
                sum = 0;
                //initialize again first position of the max subsequence
                firstPositionCounter = i + 1;
            }
            i++;
        }

        answer[0] = maxSum;
        answer[1] = first + 1;
        answer[2] = last + 1;

        return answer;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{10, 3, 2, -12, -8, 6, 9, -2, 5, -10, -12};
        int[] array = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

        int[] results;
        System.out.println("Input array: " + Arrays.toString(array));

        //Test modified MSS4
        results = mss4(array, 0, array.length);
        System.out.println("MSS: " + results[0] + ", first position: " + results[1] + ", last position: " + results[2]);
    }
}

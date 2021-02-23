import java.util.Arrays;

public class Mssp {

    //  Method linearMSP is a linear time solution for maximum product problem.
    public static long linearMSP(int[] a, int p1, int p2) {
        long positiveProduct = 0, negativeProduct = 0, maxProduct = 0;
        int i = p1;

        while (i < p2) {
            long temp = positiveProduct;

            // update maximum product ending at current index
            positiveProduct = Math.max(a[i], Math.max(a[i] * positiveProduct, a[i] * negativeProduct));

            // update minimum product ending at current index
            negativeProduct = Math.min(a[i], Math.min(a[i] * temp, a[i] * negativeProduct));
            maxProduct = Math.max(maxProduct, positiveProduct);
            i++;
        }
        return maxProduct;
    }

//    msp3 is recursive method for solving the maximum subsequence product problem,
//   based on the recursive mss3 for maximum subsequence sum problem.
    public static long msp3(int[] a, int p1, int p2) {
        long maxProduct = 0;
        int m;
        long L, R;

//       if (p2 - p1 < 2) {
//            maxProduct = 1;
//            for (int r = p1; r <= p2; r++) {
//                maxProduct = maxProduct * a[r];
//            }
//
        if(p1 == p2){
            if(a[p1] > 0){
                maxProduct = a[p1];
            }
            else{
                L = a[p1];
            }
        } else {
            m = (int) (p1 + p2) / 2;
            L = msp3(a, p1, m);
            R = msp3(a, m + 1, p2);

            long productLt = 1, productRt = 1;
            long positiveProductLeft = 0, negativeProductLeft = 0;
            long positiveProductRight = 0, negativeProductRight = 0;

            for (int i = m; i >= p1; i--) {
                productLt = productLt * a[i];
                positiveProductLeft = Math.max(positiveProductLeft, productLt);
                negativeProductLeft = Math.min(negativeProductLeft, productLt);

            }
            for (int j = m + 1; j <= p2; j++) {
                productRt = productRt * a[j];
                positiveProductRight = Math.max(positiveProductRight, productRt);
                negativeProductRight = Math.min(negativeProductRight, productRt);

            }

            maxProduct = Math.max(L, Math.max(R, Math.max(positiveProductLeft * positiveProductRight, negativeProductLeft * negativeProductRight)));
        }

        return maxProduct;
    }

    public static void main(String[] args) {

//        int[] array = new int[]{2, -5, 2, 6, -1, -2};
//        int[] array = new int[]{6, -3, -10, 0, 2};
//        int[] array = new int[]{-6, 4, -5, 8, -10, 0, 8};
//        int[] array = new int[]{-2, -9, 2, -3, 3, 2};
//        int[] array = new int[]{5, -2, 3, 0, 4, -2, 3, 1, -6, 4, -2, 5};


//        int[] array = new int[]{-1, -2, -3, -4, -5, -8, -7, -6, -9};
//        int[] array = new int[]{1, -1, 2, -2, 3, -3, 4, -4, 5, -5, 6, -6, 7, -7};
//        int[] array = new int[]{1, -1, 2, -2, 3, -3, 4, -4, 5, -5};
//        int[] array = new int[]{0, 0, 7, 8, 5, 6, 4, 2, -15};
//        int[] array = new int[]{1, -1, 2, -2, 3, -3, 4, -4, 5, -5, 6, -6, 7, -7};
//        int[] array = new int[]{0, 3};
//        int[] array = new int[]{-6, -5};
        int[] array = new int[]{-7, 6, 0, -5, 7};

        int[] results;
        System.out.println("Input array: " + Arrays.toString(array));


        //Test MSP
        System.out.println("Linear MSSP: " + linearMSP(array, 0, array.length));
        System.out.println("MSSP3: " + msp3(array, 0, array.length - 1));
    }
}

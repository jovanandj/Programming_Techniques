import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Topological_sort {
    LinkedList bag = new LinkedList();
    LinkedList<Integer>[] successors;
    int[] predecessors;
    LinkedList outputRank;  //Output array
    int topsortsNum = 0;   //counter for all topological sorts

    public void topsorts() {

        int obj = -1;

        if (bag.size() == 0) {
            //Output the output array
            if (topsortsNum <= 50) {
                System.out.println(outputRank);
            }
            topsortsNum++;
        } else {
            int firstElement = (int) bag.getFirst();

            while (obj != firstElement) {

                //Take last item out of the Bag and put it in the output array
                obj = (int) bag.removeLast();
                outputRank.add(obj);

                //traverse its succ list , reduce the pred count for
                // each successor, and if it goes to zero, put it in the Bag
                decreasePredCount(obj);

                topsorts();

                //Reverse the above
                int returningItem = (int) outputRank.removeLast();
                bag.addFirst(returningItem);
                increasePredCount(returningItem);

            }

        }
    }


    public void read(int n, String filename) {
        BufferedReader reader;
        int first = -1, second = -1;
        predecessors = new int[n];
        successors = new LinkedList[n];
        outputRank = new LinkedList();

        String[] splited;

        try {
            reader = new BufferedReader(new FileReader(filename));

            String line = reader.readLine();

            while (first != 0 || second != 0) {
                line = reader.readLine();
                splited = line.split("\\s+");
                first = Integer.parseInt(splited[0]);
                second = Integer.parseInt(splited[1]);

                if (first == 0 && second == 0)
                    break;

                predecessors[second-1]++;
                if (successors[first-1] == null) {
                    successors[first-1] = new LinkedList<Integer>();
                }
                successors[first-1].addFirst(second);

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void baginalization() {
        for (int i = 0; i < predecessors.length; i++) {
            if (predecessors[i] == 0) {
                bag.add(i+1);
            }
        }

    }

    public void decreasePredCount(int item) {
        //Traverse through succ list, reduce the pred count for each successor,
        // and if it goes to zero, put it in the Bag
        if (successors[item-1] != null) {
            ListIterator<Integer> iterator = successors[item-1].listIterator();
            while (iterator.hasNext()) {
                int next_item = iterator.next();
                predecessors[next_item-1]--;

                if (predecessors[next_item-1] == 0)
                    bag.add(next_item);
            }
        }

    }

    public void increasePredCount(int item) {
        //Traverse through succ list for each successor, if the pred count is zero,
        // remove it from the Bag, and increase the pred count
        if (successors[item-1] != null) {
            ListIterator<Integer> iterator = successors[item-1].listIterator();
            while (iterator.hasNext()) {
                int next_item = iterator.next();

                if (predecessors[next_item-1] == 0)
                    bag.remove(bag.indexOf(next_item));

                predecessors[next_item-1]++;
            }
        }

    }


}

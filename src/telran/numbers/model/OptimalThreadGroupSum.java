package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

import java.util.Arrays;

public class OptimalThreadGroupSum extends GroupSum {

    private final int N_THREADS = 4;
    private final OneGroupSum[] tasks;
    private final Thread[] threads;

    public OptimalThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
        this.threads = new Thread[N_THREADS];
        this.tasks = new OneGroupSum[numberGroups.length];
        for (int i = 0; i < numberGroups.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }
    }


    @Override
    public int computeSum() {
  //      if (numberGroups.length % N_THREADS == 0) {
            try {
            for (int j = 0; j < numberGroups.length; j += N_THREADS) {
                for (int i = 0; i < N_THREADS; i++) {
                    threads[i] = new Thread(tasks[j+i]);
                    threads[i].start();
                }
     //           for (int i = 0; i < N_THREADS; i++) {
                        threads[N_THREADS-1].join();
                }
   //         }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
  //      }
        return Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();
    }
}

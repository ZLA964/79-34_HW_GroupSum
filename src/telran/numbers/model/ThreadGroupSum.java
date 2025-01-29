package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {

    private final int N_THREADS;
    private final int N_TASKS;
    private final OneGroupSum[] tasks;
    private final Thread[] threads;

    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
        N_THREADS = numberGroups.length;
        N_TASKS = N_THREADS;
        this.threads = new Thread[N_THREADS];
        this.tasks = new OneGroupSum[N_TASKS];
        for (int i = 0; i < N_TASKS; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }
    }


    @Override
    public int computeSum() {

        for (int i = 0; i < N_THREADS; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }
        try {
 //           for (int i = 0; i < N_THREADS; i++) {
                threads[N_THREADS-1].join();
 //           }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();
    }
}

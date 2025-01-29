package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {

    private final int N_THREADS;
    private final OneGroupSum[] tasks;
    private final Thread[] threads;

    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
        N_THREADS = numberGroups.length;
        this.threads = new Thread[N_THREADS];
        this.tasks = new OneGroupSum[N_THREADS];
    }



    @Override
    public int computeSum() {
        for(int i=0; i<N_THREADS; i++){
            tasks[i] = new OneGroupSum(numberGroups[i]);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }
        for(int i=0; i<N_THREADS; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
        return Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();
    }
}

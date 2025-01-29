package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum {

    private final int N_TASKS;
    private final OneGroupSum[] tasks;
    private final int poolSize;

    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
        N_TASKS = numberGroups.length;
//        this.poolSize = Runtime.getRuntime().availableProcessors();
        poolSize=4;
        this.tasks = new OneGroupSum[N_TASKS];
        for(int i = 0; i< N_TASKS; i++){
            tasks[i] = new OneGroupSum(numberGroups[i]);}
    }



    @Override
    public int computeSum() {
//        System.out.println("poolSize -> " + poolSize);
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for(int i = 0; i< N_TASKS; i++){
            executorService.execute(tasks[i]);
        }
        executorService.shutdown(); // выполнение всех задач в очереди.
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();
    }
}

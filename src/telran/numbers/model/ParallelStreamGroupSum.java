package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

import java.util.Arrays;


public class ParallelStreamGroupSum extends GroupSum {

    private final OneGroupSum[] tasks;

    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
        int numTasks = numberGroups.length;
        this.tasks = new OneGroupSum[numTasks];
        for (int i = 0; i < numTasks; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }
    }

    @Override
    public int computeSum() {
        return Arrays.stream(tasks)
                .parallel()
                .peek(OneGroupSum::run)
                .mapToInt(OneGroupSum::getSum)
                .sum();
    }


}

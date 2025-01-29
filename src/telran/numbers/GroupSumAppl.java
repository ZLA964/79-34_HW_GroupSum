package telran.numbers;

import telran.numbers.model.*;
import telran.numbers.test.GroupSumPerformanceTest;

import java.util.Random;

public class GroupSumAppl {
    private static final int N_GROUPS = 10_000;
    private static final int NUMBERS_PER_GROUP = 10_000;
    private static final int[][] numbers = new int[N_GROUPS][NUMBERS_PER_GROUP];
    private static Random random = new Random();

    public static void main(String[] args) {
        long start0 = System.currentTimeMillis();
        fillArray();
        GroupSum threadGroupSum = new ThreadGroupSum(numbers);
        GroupSum executorGroupSum = new ExecutorGroupSum(numbers);
        GroupSum streamGroupSum = new ParallelStreamGroupSum(numbers);
        GroupSum peekStreamGroupSum = new ParallelPeekStreamGroupSum(numbers);
        long finich0 = System.currentTimeMillis();
        System.out.println("time to prepare tests -> " + (finich0-start0));

        start0 = System.currentTimeMillis();
        new GroupSumPerformanceTest( "ThreadGroupSum", threadGroupSum).runTest();
        new GroupSumPerformanceTest("StreamGroupSum", streamGroupSum ).runTest();
        new GroupSumPerformanceTest("PEEK StreamGroupSum", peekStreamGroupSum ).runTest();
        new GroupSumPerformanceTest("ExecutorGroupSum", executorGroupSum).runTest();



        finich0 = System.currentTimeMillis();
        System.out.println("time to make all tests -> " + (finich0-start0));

    }

    private static void fillArray() {
        for(int i=0; i< numbers.length; i++) {
            for (int j =0; j < numbers[i].length; j++) {
                numbers[i][j] = random.nextInt();
            }
        }
    }
}

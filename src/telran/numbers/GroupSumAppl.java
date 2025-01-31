package telran.numbers;

import telran.numbers.model.*;
import telran.numbers.test.GroupSumPerformanceTest;

import java.util.Random;

public class GroupSumAppl {
    private static final int N_GROUPS = 10_000;
    private static final int NUMBERS_PER_GROUP = 10_000;
    private static final int[][] numbers = new int[N_GROUPS][NUMBERS_PER_GROUP];
    private static final Random random = new Random();

    public static void main(String[] args) {
        long start0 = System.currentTimeMillis();
        fillArray();
        GroupSum threadGroupSum = new ThreadGroupSum(numbers);
        GroupSum executorGroupSum = new ExecutorGroupSum(numbers);
        GroupSum streamGroupSum = new ParallelStreamGroupSum(numbers);
        GroupSum peekStreamGroupSum = new ParallelPeekStreamGroupSum(numbers);
        GroupSum optimalThreadGroupSum = new OptimalThreadGroupSum(numbers);
        GroupSum eduardParallelStreamGroupSum = new EduardParallelStreamGroupSum(numbers);
        long finich0 = System.currentTimeMillis();
        System.out.println("time to prepare tests -> " + (finich0-start0));

        start0 = System.currentTimeMillis();
        new GroupSumPerformanceTest("Executor Group Sum", executorGroupSum).runTest();
        new GroupSumPerformanceTest("Executor Group Sum", executorGroupSum).runTest();
        new GroupSumPerformanceTest( "OptimalThread Sum", optimalThreadGroupSum).runTest();
        new GroupSumPerformanceTest( "OptimalThread Sum", optimalThreadGroupSum).runTest();
        new GroupSumPerformanceTest( " Thread Group Sum", threadGroupSum).runTest();
        new GroupSumPerformanceTest( " Thread Group Sum", threadGroupSum).runTest();
        new GroupSumPerformanceTest("    StreamGroupSum", streamGroupSum ).runTest();
        new GroupSumPerformanceTest("    StreamGroupSum", streamGroupSum ).runTest();
        new GroupSumPerformanceTest("PeekStreamGroupSum", peekStreamGroupSum ).runTest();
        new GroupSumPerformanceTest("PeekStreamGroupSum", peekStreamGroupSum ).runTest();
        new GroupSumPerformanceTest("EduardParallelStream", eduardParallelStreamGroupSum ).runTest();
        new GroupSumPerformanceTest("EduardParallelStream", eduardParallelStreamGroupSum ).runTest();
        
        new GroupSumPerformanceTest( "OptimalThread Sum", optimalThreadGroupSum).runTest();
        new GroupSumPerformanceTest( " Thread Group Sum", threadGroupSum).runTest();




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

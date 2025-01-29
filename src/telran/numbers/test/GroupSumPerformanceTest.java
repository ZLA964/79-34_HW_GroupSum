package telran.numbers.test;

import telran.numbers.model.GroupSum;

public class GroupSumPerformanceTest {
    private String name;
    private GroupSum groupSum;

    public GroupSumPerformanceTest(String name, GroupSum groupSum) {
        this.groupSum = groupSum;
        this.name = name;
    }

    public void runTest() {
        long start = System.currentTimeMillis();
        int sum = groupSum.computeSum();
        long finish = System.currentTimeMillis();
        System.out.println("test name: " + name + "\t, duration: " + (finish - start) + " , sum = " + sum);
    }
}

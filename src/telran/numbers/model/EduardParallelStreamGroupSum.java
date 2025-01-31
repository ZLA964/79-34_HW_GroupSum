package telran.numbers.model;



import java.util.Arrays;


public class EduardParallelStreamGroupSum extends GroupSum {


    public EduardParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        return Arrays.stream(numberGroups)
                .parallel()
                .mapToInt(nums -> Arrays.stream(nums).sum())
                .sum();
    }


}

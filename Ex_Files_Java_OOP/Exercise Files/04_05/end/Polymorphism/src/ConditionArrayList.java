import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ConditionArrayList extends ArrayList<Integer> {

    private Predicate<Integer> condition;

    public ConditionArrayList(Predicate<Integer> predicate,
                              Integer... nums) {
        super(new ConditionArrayList(predicate, Arrays.asList(nums)));
    }

    public ConditionArrayList(Predicate<Integer> predicate,
                              List<Integer> arrayList) {
        super(arrayList.stream()
                .filter(predicate)
                .collect(Collectors.toList()));
        this.condition = predicate;
    }

    @Override
    public void add(int index, Integer element) {
        if (isEligible(element)) {
            super.add(index, element);
        }
    }

    @Override
    public boolean add(Integer element) {
        if (isEligible(element)) {
            return super.add(element);
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return super.addAll(c.stream()
                .filter(this::isEligible)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean addAll(int index,
                          Collection<? extends Integer> c) {
        return super.addAll(index, c.stream()
                .filter(this::isEligible)
                .collect(Collectors.toList()));
    }

    @Override
    public Integer set(int index,
                       Integer element) {
        if (isEligible(element)) {
            return super.set(index, element);
        } else {
            System.out.println(element + " is not odd. ");
            return Integer.MIN_VALUE;
        }
    }


    @Override
    public void replaceAll(UnaryOperator<Integer> operator) {
        super.replaceAll(operator);
        super.removeIf(n -> !isEligible(n));
    }

    public boolean isEligible(Integer element) {
        return condition.test(element);
    }

}

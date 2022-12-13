package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] array = {1, 2, 2, 3, 4, 7, 5, 7};
        System.out.println(minValue(array));
        //
        List<Integer> list = Arrays.asList(1, 2, 4);
        System.out.println(oddOrEven(list));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a,b)->a * 10 + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        // Используем свойства четности целых чисел:
        // 1) Cумма четных чисел четна
        // 2) Сумма четного и нечетного чисел нечетна
        // 3) Сумма четного количества нечетных чисел четно
        // 4) Сумма нечетного количества нечетных чисел нечетна
        Map<Boolean, List<Integer>> map = integers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        // true - массив четных чисел (сумма четная)
        // false - массив нечетных чисел
        return map.get(map.get(false).size() % 2 != 0);
    }
}

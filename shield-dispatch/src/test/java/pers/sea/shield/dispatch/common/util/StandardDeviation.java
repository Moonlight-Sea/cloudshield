package pers.sea.shield.dispatch.common.util;

import java.math.BigDecimal;
import java.math.MathContext;

public class StandardDeviation {

    public static void main(String[] args) {
        // 将数字存储为BigDecimal以确保精度
        BigDecimal[] numbers = {BigDecimal.valueOf(10), BigDecimal.valueOf(20),
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(40)};

        // 计算平均值
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal num : numbers) {
            sum = sum.add(num);
        }
        BigDecimal mean = sum.divide(BigDecimal.valueOf(numbers.length), MathContext.DECIMAL128);

        // 计算每个数与平均值的差的平方
        BigDecimal sumOfSquaredDifferences = BigDecimal.ZERO;
        for (BigDecimal num : numbers) {
            BigDecimal diff = num.subtract(mean);
            BigDecimal squaredDiff = diff.multiply(diff);
            sumOfSquaredDifferences = sumOfSquaredDifferences.add(squaredDiff);
        }

        // 计算标准差
        BigDecimal variance = sumOfSquaredDifferences.divide(BigDecimal.valueOf(numbers.length),
                MathContext.DECIMAL128);
        BigDecimal standardDeviation = BigDecimal.valueOf(Math.sqrt(variance.doubleValue()));

        System.out.println("平均值: " + mean);
        System.out.println("标准差: " + standardDeviation);
    }


}

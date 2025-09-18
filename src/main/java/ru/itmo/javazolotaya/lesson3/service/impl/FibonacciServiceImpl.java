package ru.itmo.javazolotaya.lesson3.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itmo.javazolotaya.lesson3.service.FibonacciService;

import java.util.HashMap;
import java.util.Map;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    private final boolean cacheEnabled;
    private final Map<Integer, Long> cache = new HashMap<>();

    public FibonacciServiceImpl(@Value("${fibonacci.useCache:true}") boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    @Override
    public long getFibonacci(int n) {
        if (!cacheEnabled) {
            return calculateFibonacci(n);
        }
        return getFibonacciCached(n);
    }

    private long getFibonacciCached(int n) {
        if (cache.containsKey(n)) {
            System.out.println("Берём из кеша: F(" + n + ")");
            return cache.get(n);
        }
        long calculated = calculateFibonacci(n);
        cache.put(n, calculated);
        return calculated;
    }

    private long calculateFibonacci(int n) {
        System.out.println("Вычисляю F(" + n + ") ...");
        if (n < 0) throw new IllegalArgumentException("отрицательное число");
        if (n == 0) return 0;
        if (n == 1) return 1;

        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long tmp = a + b;
            a = b;
            b = tmp;
        }
        return b;
    }
}
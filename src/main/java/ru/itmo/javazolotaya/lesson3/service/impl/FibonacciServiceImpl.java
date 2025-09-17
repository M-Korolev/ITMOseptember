package ru.itmo.javazolotaya.lesson3.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.itmo.javazolotaya.lesson3.service.FibonacciService;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    private final boolean cacheEnabled;

    public FibonacciServiceImpl(@Value("${fibonacci.useCache:true}") boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    @Override
    @Cacheable("fibonacci")
    public long getFibonacci(int n) {
        System.out.println( "cacheEnabled = " + cacheEnabled);
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

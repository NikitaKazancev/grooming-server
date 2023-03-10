package ru.nk.grooming.types.functions;

public interface Func3Args<first, second, third, R> {
    R apply(first arg1, second arg2, third arg3);
}

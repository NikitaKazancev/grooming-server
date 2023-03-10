package ru.nk.grooming.types;

public interface EntityWithMerge<T> {
    void merge(T inputEntity);
}

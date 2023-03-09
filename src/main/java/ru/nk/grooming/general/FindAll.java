package ru.nk.grooming.general;

public interface FindAll<ObjectType> {
    Iterable<ObjectType> apply();
}

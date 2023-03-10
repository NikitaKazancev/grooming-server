package ru.nk.grooming.types.functions;

import java.util.List;

public interface FindAll<ObjectType> {
    List<ObjectType> apply();
}

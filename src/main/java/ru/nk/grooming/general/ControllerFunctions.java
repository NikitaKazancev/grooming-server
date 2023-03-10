package ru.nk.grooming.general;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nk.grooming.types.functions.Func2Args;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.types.functions.VoidPropFunc;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ControllerFunctions {
    public <ObjectType, PropType> ResponseEntity<ResponseWithStatus<ObjectType>> responseWithStatus(
            PropType property,
            Func2Args<PropType, HttpServletRequest, ResponseWithStatus<ObjectType>> findFunction,
            HttpServletRequest request
    ) {
        ResponseWithStatus<ObjectType> response = findFunction.apply(property, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public <ObjectType, PropType> ResponseEntity<ResponseWithStatus<ObjectType>> responseWithStatus(
            PropType property,
            Function<PropType, ResponseWithStatus<ObjectType>> findFunction
    ) {
        ResponseWithStatus<ObjectType> response = findFunction.apply(property);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public <ObjectType> ResponseEntity<ResponseWithStatus<ObjectType>> responseWithStatus(
            VoidPropFunc<ResponseWithStatus<ObjectType>> findFunction
    ) {
        ResponseWithStatus<ObjectType> response = findFunction.apply();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public <T> ResponseEntity<StatusCode> statusCode(
            T propertyOrObject,
            Func2Args<T, HttpServletRequest, StatusCode> function,
            HttpServletRequest request
    ) {
        StatusCode response = function.apply(propertyOrObject, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

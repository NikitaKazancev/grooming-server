package ru.nk.grooming.components.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.general.RequestFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final AuthService authService;
    private final RequestFunctions requestFunctions;

    public StatusCode save(ProductEntity product, HttpServletRequest request) {
        return requestFunctions.save(
                product,
                product.getName(),
                productRepo::findByName,
                productRepo::save,
                request
        );
    }

    public ResponseWithStatus<ProductEntity> findById(Long id) {
        return requestFunctions.findBy(id, productRepo::findById);
    }

    public Iterable<ProductEntity> findAll() {
        return productRepo.findAll();
    }
}

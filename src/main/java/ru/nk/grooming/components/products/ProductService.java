package ru.nk.grooming.components.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.general.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ServiceFunctions functions;

    public StatusCode save(ProductEntity product, HttpServletRequest request) {
        return functions.saveWithAuth(
                product,
                product.getName(),
                productRepo::findByName,
                productRepo::save,
                request
        );
    }

    public ResponseWithStatus<ProductEntity> findById(Long id) {
        return functions.findBy(id, productRepo::findById);
    }

    public Iterable<ProductEntity> findAll() {
        return productRepo.findAll();
    }
}

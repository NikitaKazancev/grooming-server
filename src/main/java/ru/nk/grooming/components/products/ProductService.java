package ru.nk.grooming.components.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.general.requests.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ServiceFunctions functions;

    public List<ProductEntity> findAll() {
        return productRepo.findAll();
    }
    public ResponseWithStatus<ProductEntity> findById(Long id) {
        return functions.findBy(id, productRepo::findById);
    }
    public StatusCode save(ProductEntity product, HttpServletRequest request) {
        return functions.saveWithAuth(
                product,
                product.getName(),
                productRepo::findByName,
                productRepo::save,
                request
        );
    }
    public StatusCode change(ProductEntity product, HttpServletRequest request) {
        return functions.changeWithAuth(
                product,
                productRepo::findByName,
                product.getName(),
                productRepo::save,
                request
        );
    }
    public StatusCode deleteById(Long id, HttpServletRequest request) {
        return functions.deleteByWithAuth(
                id,
                productRepo::findById,
                productRepo::deleteById,
                request
        );
    }
}

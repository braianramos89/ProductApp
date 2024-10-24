package ar.edu.undef.fie.productApp.services;

import ar.edu.undef.fie.productApp.entities.Producto;
import ar.edu.undef.fie.productApp.exceptions.ProductoNotFoundException;
import ar.edu.undef.fie.productApp.repositories.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @Override
    @Transactional
    public Producto agregarProducto(Producto productoRequest) {
        return productoRepository.save(productoRequest);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Long id, Producto productoRequest) {
        Producto productoExistente = getProductoById(id);
        productoExistente.setNombre(productoRequest.getNombre());
        productoExistente.setCantidad(productoRequest.getCantidad());
        return productoRepository.save(productoExistente);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
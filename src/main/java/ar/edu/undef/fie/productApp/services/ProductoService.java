package ar.edu.undef.fie.productApp.services;

import ar.edu.undef.fie.productApp.entities.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(Long id);
    Producto agregarProducto(Producto productoRequest);
    Producto actualizarProducto(Long id, Producto productoRequest);
    void eliminarProducto(Long id);
}

package ar.edu.undef.fie.productApp;

import ar.edu.undef.fie.productApp.controllers.ProductoController;
import ar.edu.undef.fie.productApp.entities.Producto;
import ar.edu.undef.fie.productApp.services.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

public class ProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductoServiceImpl productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    void testGetAllProductos() throws Exception {
        // Preparar datos de prueba
        Producto producto1 = new Producto(1L,"nombre 1", 100);
        Producto producto2 = new Producto(2L,"nombre 2", 200);
        List<Producto> productos = Arrays.asList(producto1, producto2);

        // Simular el comportamiento del servicio
        when(productoService.getAllProductos()).thenReturn(productos);

        // Ejecutar la solicitud y verificar los resultados
        mockMvc.perform(get("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("nombre 1"))
                .andExpect(jsonPath("$[1].nombre").value("nombre 2"));

        // Verificar que el método del servicio fue llamado una vez
        verify(productoService, times(1)).getAllProductos();
    }

    @Test
    void testGetNewProductoById() throws Exception {
        // Preparar datos de prueba
        Producto producto = new Producto(1L, "Producto 1", 123);

        // Simular el comportamiento del servicio
        when(productoService.getProductoById(1L)).thenReturn(producto);

        // Ejecutar la solicitud y verificar los resultados
        mockMvc.perform(get("/api/v1/productos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto 1"));

        // Verificar que el método del servicio fue llamado una vez
        verify(productoService, times(1)).getProductoById(1L);
    }

    @Test
    void testCrearNewProducto() throws Exception {
        // Preparar datos de prueba
        Producto producto = new Producto(1L, "Producto1", 150);

        // Simular el comportamiento del servicio
        when(productoService.agregarProducto(any(Producto.class))).thenReturn(producto);

        // Ejecutar la solicitud y verificar los resultados
        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Producto1\", \"cantidad\": 150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto1"))
                .andExpect(jsonPath("$.cantidad").value(150));

        // Verificar que el método del servicio fue llamado una vez
        verify(productoService, times(1)).agregarProducto(any(Producto.class));
    }


}

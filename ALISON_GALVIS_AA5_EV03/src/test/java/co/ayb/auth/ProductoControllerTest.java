package co.ayb.auth;

import co.ayb.auth.model.Producto;
import co.ayb.auth.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void debeListarProductos() throws Exception {
        productoRepository.deleteAll();
        productoRepository.save(new Producto(
                "Camiseta básica", "Camisetas", "M", "Blanco",
                new BigDecimal("45000"), 10, "Camiseta de prueba"));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre").value("Camiseta básica"));
    }

    @Test
    void debeCrearProducto() throws Exception {
        productoRepository.deleteAll();

        String body = """
                {
                  "nombre": "Pantalón básico",
                  "categoria": "Pantalones",
                  "talla": "M",
                  "color": "Azul",
                  "precio": 80000,
                  "stock": 5,
                  "descripcion": "Pantalón de prueba"
                }
                """;

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Pantalón básico"));
    }
}

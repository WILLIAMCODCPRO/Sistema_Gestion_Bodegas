package com.s1.gestion_bodegas.mapper;

import com.s1.gestion_bodegas.dto.request.ProductoRequestDTO;
import com.s1.gestion_bodegas.dto.response.BodegaResponseDTO;
import com.s1.gestion_bodegas.dto.response.ProductoResponseDTO;
import com.s1.gestion_bodegas.model.Bodega;
import com.s1.gestion_bodegas.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
        public ProductoResponseDTO entidadADTO(Producto producto, BodegaResponseDTO bodegaResponseDTO) {
            if (producto == null) return null;
            return new ProductoResponseDTO(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getCategoria(),
                    producto.getPrecio(),
                    producto.getStockProducto(),
                    bodegaResponseDTO
            );
        }

        public Producto DTOAEntidad(ProductoRequestDTO productoRequestDTO, Bodega bodega) {
            if (productoRequestDTO == null) return null;
            Producto producto = new Producto();
            producto.setNombre(productoRequestDTO.nombre());
            producto.setCategoria(productoRequestDTO.categoria());
            producto.setPrecio(productoRequestDTO.precio());
            producto.setStockProducto(productoRequestDTO.stockProducto());
            producto.setBodega(bodega);
            return producto;

        }

        public void actualizarEntidadDesdeDTO(ProductoRequestDTO productoRequestDTO, Producto producto, Bodega bodega){
            if (productoRequestDTO == null || producto == null || bodega == null) return;
            producto.setNombre(productoRequestDTO.nombre());
            producto.setCategoria(productoRequestDTO.categoria());
            producto.setPrecio(productoRequestDTO.precio());
            producto.setStockProducto(productoRequestDTO.stockProducto());
            producto.setBodega(bodega);
        }
}

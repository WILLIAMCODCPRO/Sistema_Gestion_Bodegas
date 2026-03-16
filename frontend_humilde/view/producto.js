// Función genérica con token y manejo de errores personalizados
function apiFetch(url, options = {}) {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión");
        return Promise.reject("No token");
    }

    return fetch(url, {
        ...options,
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token,
            ...options.headers
        }
    })
    .then(async response => {

        const data = await response.json().catch(() => null);

        if (response.status === 401) {
            localStorage.removeItem("token");
            throw new Error("Sesión expirada");
        }

        if (!response.ok) {

            // si el backend envía errores de validación
            if (data && data.errorCode) {
                throw data;
            }

            // error normal
            const mensajeError = data?.message || "Error en la petición";
            const codigoError = data?.errorCode ? ` (${data.errorCode})` : "";
            const timestamp = data?.timestamp ? ` [${data.timestamp}]` : "";

            throw new Error(mensajeError + codigoError + timestamp);
        }

        return data;
    });
}


// GET por ID
function getById(id) {
    return apiFetch(`http://localhost:8080/api/producto/${id}`);
}


// Listar todos
function listar() {
    return apiFetch(`http://localhost:8080/api/producto`);
}


// Crear producto
function crear(nombre, categoria, precio, stockProducto, bodegaId) {

    const nuevoProducto = {
        nombre: nombre,
        categoria: categoria,
        precio: Number(precio),
        stockProducto: Number(stockProducto),
        bodegaId: Number(bodegaId)
    };

    return apiFetch("http://localhost:8080/api/producto", {
        method: "POST",
        body: JSON.stringify(nuevoProducto)
    });
}


// Actualizar producto
function actualizarPersonalizada(id, nombre, categoria, precio, stockProducto, bodegaId) {

    const datosActualizados = {
        nombre: nombre,
        categoria: categoria,
        precio: Number(precio),
        stockProducto: Number(stockProducto),
        bodegaId: Number(bodegaId)
    };

    return apiFetch(`http://localhost:8080/api/producto/${id}`, {
        method: "PUT",
        body: JSON.stringify(datosActualizados)
    });
}


// Eliminar producto
function eliminar(id) {
    return apiFetch(`http://localhost:8080/api/producto/${id}`, {
        method: "DELETE"
    });
}

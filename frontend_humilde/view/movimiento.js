// Función genérica con token
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

        // sesión expirada
        if (response.status === 401) {
            localStorage.removeItem("token");
            throw new Error("Sesión expirada");
        }

        // errores del backend
        if (!response.ok) {

            if (data && data.errorCode) {
                throw data;
            }

            const msg = data?.message || "Error en la petición";
            const code = data?.errorCode ? ` (${data.errorCode})` : "";
            const timestamp = data?.timestamp ? ` [${data.timestamp}]` : "";

            throw new Error(msg + code + timestamp);
        }

        // manejo de 204
        if (response.status === 204) {
            return null;
        }

        return data;
    });
}



// GET movimiento por ID
function getMovimientoById(id) {
    return apiFetch(`http://localhost:8080/api/movimientos/${id}`);
}


// Listar movimientos
function listarMovimientos() {
    return apiFetch(`http://localhost:8080/api/movimientos`);
}


// Crear movimiento
function crearMovimiento(
    tipoMovimiento,
    productoID,
    cantidadProducto,
    bodegaOrigenID,
    bodegaDestinoID
) {

    const nuevoMovimiento = {

        tipoMovimiento: tipoMovimiento,

        productoID: Number(productoID),

        cantidadProducto: Number(cantidadProducto),

        bodegaOrigenID: bodegaOrigenID
            ? Number(bodegaOrigenID)
            : null,

        bodegaDestinoID: bodegaDestinoID
            ? Number(bodegaDestinoID)
            : null
    };

    return apiFetch("http://localhost:8080/api/movimientos", {
        method: "POST",
        body: JSON.stringify(nuevoMovimiento)
    });
}
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

            // si el backend mandó errores de validación
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


// GET bodega por ID
function getBodegaById(id) {
    return apiFetch(`http://localhost:8080/api/bodega/${id}`);
}


// Listar todas las bodegas
function listarBodegas() {
    return apiFetch(`http://localhost:8080/api/bodega`);
}


// Crear bodega
function crearBodega(nombre, ubicacion, capacidad, encargado) {

    const nuevaBodega = {
        nombre: nombre,
        ubicacion: ubicacion,
        capacidad: Number(capacidad),
        encargado: encargado
    };

    return apiFetch("http://localhost:8080/api/bodega", {
        method: "POST",
        body: JSON.stringify(nuevaBodega)
    });
}


// Actualizar bodega
function actualizarBodega(id, nombre, ubicacion, capacidad, encargado) {

    const datosActualizados = {
        nombre: nombre,
        ubicacion: ubicacion,
        capacidad: Number(capacidad),
        encargado: encargado
    };

    return apiFetch(`http://localhost:8080/api/bodega/${id}`, {
        method: "PUT",
        body: JSON.stringify(datosActualizados)
    });
}


// Eliminar bodega
function eliminarBodega(id) {
    return apiFetch(`http://localhost:8080/api/bodega/${id}`, {
        method: "DELETE"
    });
}
let token = null;

function login() {

    fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nombreUsuario: document.getElementById("username").value,
            password: document.getElementById("password").value
        })
    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Credenciales no válidas");
        }

        return response.json();
    })
    .then(data => {

        localStorage.setItem("token", data.token);

        console.log("Token guardado:", data.token);
        alert("Login exitoso");
    })
    .catch(error => {

       
        localStorage.removeItem("token");

        alert(error.message);
        console.error(error);
    });
}

function getProductos() {

    
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/producto", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}

function getBodegas() {

    
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/bodega", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}

function getMovimientos() {

   
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/movimientos", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}

function getAuditoria() {

   
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/auditoria", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}

function registrarUsuario(){

    const nombreUsuario = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;
    const rol = document.getElementById("regRol").value;

    fetch("http://localhost:8080/auth/register", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            nombreUsuario: nombreUsuario,
            password: password,
            rol: rol
        })

    })
    .then(async res => {

        const data = await res.json().catch(() => null);

        if(!res.ok){

            // errores de validación
            if(data && data.errorCode === "VALIDATION_FAILED"){

                let mensaje = "ERRORES DE VALIDACIÓN\n\n";

                for(const campo in data.errors){
                    mensaje += campo + ": " + data.errors[campo] + "\n";
                }

                throw new Error(mensaje);
            }

            throw new Error(data?.message || "Error en la petición");
        }

        return data;

    })
    .then(data => {

        alert("Usuario registrado con éxito");

        console.log("Usuario registrado:", data);

        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);

    })
    .catch(err => {

        alert(err.message);

        console.error("Error:", err);

        document.getElementById("resultado").textContent =
            err.message;

    });

}

function getStockBajo() {

   
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/producto/stockbajo", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}

function buscarMovimientosPorFecha(){

    const fecha1 = document.getElementById("fecha1").value;
    const fecha2 = document.getElementById("fecha2").value;

    fetch(`http://localhost:8080/api/movimientos/${fecha1}/${fecha2}`, {

        method: "GET",

        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }

    })
    .then(res => res.json())
    .then(data => {

        console.log("Movimientos encontrados:", data);

        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);

    })
    .catch(err => {

        console.error("Error:", err);

        document.getElementById("resultado").textContent =
            "Error: " + err;

    });

}

function buscarAuditoriaUsuario(){

    const idUsuario = document.getElementById("usuarioId").value;

    fetch(`http://localhost:8080/api/auditoria/usuario/${idUsuario}`, {

        method: "GET",

        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }

    })
    .then(async res => {

        const data = await res.json().catch(() => null);

        // si el backend devolvió error
        if(!res.ok){

            if(data){
                throw new Error(
                    " " + data.message +
                    "\nCódigo: " + data.errorCode +
                    "\nEstado: " + data.status
                );
            }

            throw new Error("Error desconocido en la petición");
        }

        return data;

    })
    .then(data => {

        console.log("Auditoría encontrada:", data);

        if(!data || data.length === 0){
            document.getElementById("resultado").textContent =
                "No hay auditoría para este usuario";
            return;
        }

        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);

    })
    .catch(err => {

        console.error("Error:", err);

        

        document.getElementById("resultado").textContent =
            err.message;

    });

}

function getStockTotalBodega() {

   
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/reporte/bodega/stock", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}

function getProductosMasMovidos() {

   
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/reporte/productos/masmovidos", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}

function getUsuario() {

    
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    fetch("http://localhost:8080/api/usuario", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });

    
}





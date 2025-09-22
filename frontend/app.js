const API_URL = "http://44.218.226.220:8080/propiedades";

document
	.getElementById("form-propiedad")
	.addEventListener("submit", async (e) => {
		e.preventDefault();
		const propiedad = {
			direccion: document.getElementById("direccion").value,
			precio: parseFloat(document.getElementById("precio").value),
			tamaño: parseFloat(document.getElementById("tamano").value),
			descripcion: document.getElementById("descripcion").value,
		};

		const res = await fetch(API_URL, {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(propiedad),
		});

		if (res.ok) {
			alert("Propiedad creada");
			cargarPropiedades();
		} else {
			alert("Error al crear propiedad");
		}
	});

async function cargarPropiedades() {
	const res = await fetch(API_URL);
	const data = await res.json();
	const tabla = document.getElementById("tabla-propiedades");
	tabla.innerHTML = "";

	data.forEach((p) => {
		tabla.innerHTML += `
        <tr>
            <td>${p.id}</td>
            <td>${p.direccion}</td>
            <td>${p.precio}</td>
            <td>${p.tamaño}</td>
            <td>${p.descripcion}</td>
            <td>
                <button onclick="eliminar(${p.id})">Eliminar</button>
                <button onclick="editar(${p.id})">Editar</button>
            </td>
        </tr>`;
	});
}

async function eliminar(id) {
	if (confirm("¿Seguro que quieres eliminar esta propiedad?")) {
		await fetch(`${API_URL}/${id}`, { method: "DELETE" });
		cargarPropiedades();
	}
}

async function editar(id) {
	const nuevaDireccion = prompt("Nueva dirección:");
	const nuevoPrecio = prompt("Nuevo precio:");
	const nuevoTamano = prompt("Nuevo tamaño:");
	const nuevaDescripcion = prompt("Nueva descripción:");

	const propiedad = {
		direccion: nuevaDireccion,
		precio: parseFloat(nuevoPrecio),
		tamaño: parseFloat(nuevoTamano),
		descripcion: nuevaDescripcion,
	};

	await fetch(`${API_URL}/${id}`, {
		method: "PUT",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(propiedad),
	});
	cargarPropiedades();
}

cargarPropiedades();

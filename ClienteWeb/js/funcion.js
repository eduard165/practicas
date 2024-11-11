const URL_WS_CLIENTE = 'http://localhost:8084/WSRestM/api/cliente'; 
const URL_WS_PAGOS = 'http://localhost:8084/WSRestM/api/pagos/obtener-pagos-por-cliente'; 

async function obtenerCliente() {
    const correo = document.getElementById('correo').value; 
    const clienteInfo = document.getElementById('cliente-info');
    clienteInfo.innerHTML = '<p>Cargando información del cliente...</p>'; 

    try {
        const respuesta = await fetch(`${URL_WS_CLIENTE}/${encodeURIComponent(correo)}`, { 
            method: 'GET'
        });
        if (respuesta.ok) {
            const cliente = await respuesta.json();
            mostrarInformacionCliente(clienteInfo, cliente); 
            
            if (cliente && cliente.cliente && cliente.cliente.idCliente) { 
                await obtenerInformacionPagos(cliente.cliente.idCliente); 
            }
        } else {
            throw new Error(`Error en la petición: ${respuesta.status}`);
        }
    } catch (error) {
        console.log('Error en la petición', error);
        clienteInfo.innerHTML = '<p>Hubo un error al consultar la información del cliente...</p>';
    }
}

function mostrarInformacionCliente(clienteInfo, cliente) {
    clienteInfo.innerHTML = '';

    if (cliente && cliente.cliente) {
        clienteInfo.innerHTML = `
            <strong>Nombre:</strong> ${cliente.cliente.nombre} <br>
            <strong>Apellido Paterno:</strong> ${cliente.cliente.apellidoPaterno} <br>
            <strong>Apellido Materno:</strong> ${cliente.cliente.apellidoMaterno} <br>
            <strong>Correo:</strong> ${cliente.cliente.correo} <br>
            <strong>Teléfono:</strong> ${cliente.cliente.telefono} <br>
            <strong>Estatura:</strong> ${cliente.cliente.estatura} m <br>
            <strong>Peso:</strong> ${cliente.cliente.peso} kg <br>
            <strong>Fecha de Nacimiento:</strong> ${cliente.cliente.fechaNacimiento} <br>
        `;
    } else {
        clienteInfo.innerHTML = '<p>No se encontró información del cliente.</p>';
    }
}

async function obtenerInformacionPagos(clienteId) {
    const listaPagos = document.getElementById('lista-pagos');
    listaPagos.innerHTML = '<p>Cargando información de pagos...</p>'; 

    try {
        const respuesta = await fetch(`${URL_WS_PAGOS}/${clienteId}`, { 
            method: 'GET'
        });
        if (respuesta.ok) {
            const pagos = await respuesta.json();
            console.log(pagos); 
            mostrarInformacionPagos(listaPagos, pagos); 
        } else {
            throw new Error(`Error en la petición: ${respuesta.status}`);
        }
    } catch (error) {
        console.log('Error en la petición', error);
        listaPagos.innerHTML = '<p>Hubo un error al consultar la información de pagos...</p>';
    }
}

function mostrarInformacionPagos(listaPagos, pagos) {
    listaPagos.innerHTML = '';

    if (pagos && pagos.length > 0) {
        let html = '<ul>';
        pagos.forEach(pago => {
            html += `
                <li>
                    <strong>Fecha de Pago:</strong> ${pago.fechaPago} <br>
                    <strong>Monto:</strong> $${pago.monto} <br>
                    <strong>Tipo de Pago:</strong> ${pago.tipoPago} <br>
                    <strong>Descuento:</strong> ${pago.descuento} <br>
                    <strong>Porcentaje de Descuento:</strong> ${pago.porcentajeDescuento} <br>
                </li>
            `;
        });
        html += '</ul>';
        listaPagos.innerHTML = html; 
    } else {
        listaPagos.innerHTML = '<p>No se encontraron pagos para este cliente.</p>';
    }
}
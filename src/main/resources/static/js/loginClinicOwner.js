// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function loginClinicOwner() {

    datos = {}

    datos.email = document.getElementById('email').value
    datos.password = document.getElementById('password').value
    
    const request = await fetch('/api/loginClinicOwner', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos),
    });
    const response =  await request.json();
    if (request.status == 200) {
      localStorage.setItem('token', response.jwt);
      localStorage.setItem('email', datos.email);
      
      // Decodificar el JWT para obtener el rol del usuario
      const payload = decodeJwt(response.jwt);
      
      // Verificar si el rol es PetOwner antes de redirigir
      if (payload.role === 'CLINIC_OWNER') {
          window.location.href = "/petOwners.html"; // Redirige a la página de PetOwner
      } else {
          alert("Acceso denegado. No tienes permisos para acceder a esta página.");
          window.location.href = "/loginClinicOwner.html"; // O cualquier página que elijas
      }
  } else {
      alert(response.message || 'Error al iniciar sesión');
  }

}

// Función para decodificar el JWT y extraer el payload
function decodeJwt(token) {
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  return JSON.parse(jsonPayload);
}
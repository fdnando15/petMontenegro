// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registerPet() {

    datos = {}

    datos.name = document.getElementById('name').value
    datos.birthDay = document.getElementById('birthDate').value
    
    const request = await fetch('/api/registerPet', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      },
      body: JSON.stringify(datos),
    });
    
    if (request.ok) {
        alert('Pet registrado correctamente')
        window.location.href = '/pets.html';
    } else {
        alert('Error al registrar el Pet')
    }
}
 
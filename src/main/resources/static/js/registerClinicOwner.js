// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registerClinicOwner() {

    datos = {}

    datos.name = document.getElementById('name').value
    datos.email = document.getElementById('email').value
    datos.phone = document.getElementById('phone').value
    datos.password = document.getElementById('password').value
    
    const request = await fetch('/api/registerClinicOwner', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos),
    });

    //const response =  await request.json();        
    
    console.log(datos)
    if (request.ok) {
      window.location.href = 'loginClinicOwner.html';
      } else {
      alert('Error al registrar el ClinicOwner');
  }

}
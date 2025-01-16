// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function loginPetOwner() {

    datos = {}

    datos.email = document.getElementById('email').value
    datos.password = document.getElementById('password').value
    
    const request = await fetch('/api/loginPetOwner', {
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
        window.location.href = "/pets.html";
    }else{
        alert(response)
    }
}
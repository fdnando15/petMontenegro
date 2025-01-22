// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registerAbandonPet() {
  const formData = new FormData();

  formData.append('name', document.getElementById('name').value);
  formData.append('birthDay', document.getElementById('birthDate').value);
  formData.append('url', document.getElementById('photo').files[0]);

  const request = await fetch('/api/registerAbandonPet', {
      method: 'POST',
      headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('token')
      },
      body: formData,
  });

  if (request.ok) {
      alert('Pet registrado correctamente');
      window.location.href = '/abandonPets.html';
  } else {
      alert('Error al registrar el abandon Pet');
  }
}

 
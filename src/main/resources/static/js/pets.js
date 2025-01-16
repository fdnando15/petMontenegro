// Call the dataTables jQuery plugin
$(document).ready(function() {

    cargarPets()
    mostrarLocalStorage()

  $('#pets').DataTable();

  actualizarEmail()
});


/*function obtenerIdPetOwnerDesdeURL() {
  const path = window.location.pathname; // Obtiene el path de la URL
  const partes = path.split('/'); // Divide la URL por las "/"
  const id = partes[partes.indexOf('petOwners') + 1]; // Encuentra el ID después de "petOwners"
  return id;
}*/

function actualizarEmail() {
  //document.querySelector('#txt-email-usuario').innerHTML = localStorage.getItem();
  document.getElementById('txt-email-usuario').innerHTML = localStorage.getItem('email');
}

function mostrarLocalStorage() {
  console.log('Contenido de localStorage:');
  for (let i = 0; i < localStorage.length; i++) {
    const key = localStorage.key(i);
    console.log(`${key}: ${localStorage.getItem(key)}`);
  }
}

async function cargarPets() {

  /*const petOwnerId = obtenerIdPetOwnerDesdeURL(); // Extrae el ID de la URL
  if (!petOwnerId) {
    console.error('No se pudo obtener el ID del propietario de mascotas.');
    return;
  }*/

  const request = await fetch('api/petOwners/pets', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  });
  const pets = await request.json();
  console.log(pets)


  let listadoHtml = '';
  for (let pet of pets) {
    let botonEliminar = '<a href="#" onclick="eliminarPet(' + pet.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let petHtml = '<tr><td>' + pet.name  + '</td><td>' + pet.birthDay + '</td><td>'+ botonEliminar + '</td></tr>';
    listadoHtml += petHtml;
  }

document.querySelector('#pets tbody').outerHTML = listadoHtml;

}

async function eliminarPet(id) {
  alert('Eliminar el petOwner con id: ' + id);
  const request = await fetch('api/petOwners/pets/' + id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  });
  // Verificamos si la eliminación fue exitosa
  if (request.ok) {
    // Si la eliminación fue exitosa, redirigimos a la página petOwners.html
    window.location.href = '/pets.html';
  } else {
    alert('Error al eliminar el pet');
  }

}
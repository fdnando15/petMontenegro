// Call the dataTables jQuery plugin
$(document).ready(function() {

    cargarPets()

  $('#pets').DataTable();
});


/*function obtenerIdPetOwnerDesdeURL() {
  const path = window.location.pathname; // Obtiene el path de la URL
  const partes = path.split('/'); // Divide la URL por las "/"
  const id = partes[partes.indexOf('petOwners') + 1]; // Encuentra el ID después de "petOwners"
  return id;
}*/



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
    let botonEliminar = '<a href="#" onclick="eliminarpetOwner(' + pet.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let petHtml = '<tr><td>'+pet.id+'</td><td>' + pet.name + ' ' + pet.birthDate + '</td><td>'
                    + '</td><td>' + botonEliminar + '</td></tr>';
    listadoHtml += petHtml;
  }

document.querySelector('#pets tbody').outerHTML = listadoHtml;

}
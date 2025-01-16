// Call the dataTables jQuery plugin
$(document).ready(function() {

    cargarPetOwners()

  $('#petOwners').DataTable();

  actualizarEmail()
});

function actualizarEmail() {
  document.querySelector('#txt-email-usuario').innerHTML = localStorage.getItem('email');

}



async function cargarPetOwners() {
  const request = await fetch('api/petOwners', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  });
  console.log(request)
  const petOwners = await request.json();
  console.log(petOwners)


  let listadoHtml = '';
  for (let petOwner of petOwners) {
    let botonEliminar = '<a href="#" onclick="eliminarpetOwner(' + petOwner.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let petOwnerHtml = '<tr><td>'+ petOwner.name +'</td><td>'
                    + petOwner.phone+'</td><td>'+ petOwner.birthDay
                    + '</td><td>' + botonEliminar + '</td></tr>';
    listadoHtml += petOwnerHtml;
  }

document.querySelector('#petOwners tbody').outerHTML = listadoHtml;

}


async function eliminarpetOwner(id) {
  alert('Eliminar el petOwner con id: ' + id);
  const request = await fetch('api/petOwners/' + id, {
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
    window.location.href = '/petOwners.html';
  } else {
    alert('Error al eliminar el PetOwner');
  }

}
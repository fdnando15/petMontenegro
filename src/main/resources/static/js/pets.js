// Call the dataTables jQuery plugin
$(document).ready(function() {

    actualizarEmail();
    cargarPets().then(() => {
        $('#pets').DataTable();
    });
    mostrarLocalStorage();
  
});



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

    let petHtml = '<tr><td><img width="60px" height="60px" src="'+ (pet.url ? pet.url.replace("/resources","") : "/img/uploads/dog.jpg") +'" /></td><td><a href="/consultations.html?id='+pet.id+'">' + pet.name  + '</a></td><td>' + pet.birthDay + '</td><td>'+ botonEliminar + '</td></tr>';
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


// Call the dataTables jQuery plugin
$(document).ready(function() {

    cargarAbandonPets()
    mostrarLocalStorage()

  $('#pets').DataTable();

  actualizarEmail()
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

async function cargarAbandonPets() {


  const request = await fetch('api/abandonPets', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  });
  const abandonPets = await request.json();
  console.log(abandonPets)


  let listadoHtml = '';
  for (abandonPet of abandonPets) {
    let botonAdoptar = '<a href="#" onclick="eliminarAbandonPet(' + abandonPet.id + ')" class="btn btn-success btn-circle"><i class="fas fa-check"></i></a>';

    let petHtml = '<tr><td><img width="60px" height="60px" src="'+ (abandonPet.url ? abandonPet.url.replace("/resources","") : "https://www.ecestaticos.com/imagestatic/clipping/b93/4a7/b934a73f42cfe61d874e563914aedf17/estos-son-los-perros-mas-feos-del-mundo.jpg?mtime=1622868118") +'" /></td><td><a href="/consultations.html?id='+abandonPet.id+'">' + abandonPet.name  + '</a></td><td>' + abandonPet.birthDay + '</td><td>'+ botonAdoptar + '</td></tr>';    listadoHtml += petHtml;
  }

document.querySelector('#pets tbody').outerHTML = listadoHtml;

}

async function eliminarAbandonPet(id) {
  alert('Eliminar el petOwner con id: ' + id);
  const request = await fetch('api/abandonPets/' + id, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  });
  // Verificamos si la eliminación fue exitosa
  if (request.ok) {
    window.location.href = '/pets.html';
  } else {
    alert('Error al eliminar el pet');
  }

}



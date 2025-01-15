let clinicOwners = []; // Variable global para almacenar los clinicOwners

// Llamada a la función al cargar la página
$(document).ready(function() {
  cargarClinicOwners();
});

async function cargarClinicOwners() {
  try {

      const response = await fetch('api/clinicOwners', {
          method: 'GET',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          }
      });

      if (!response.ok) {
          throw new Error('Error al cargar los clinic owners.');
      }

      clinicOwners = await response.json(); // Guarda el array de clinicOwners en la variable global
      console.log(clinicOwners);

      // Obtén el select con el id "clinicOwners"
      const selectClinicOwners = document.querySelector('#clinicOwners');

      // Limpia las opciones previas del select
      selectClinicOwners.innerHTML = '<option value="">--Selecciona--</option>';

      // Genera las opciones dinámicamente
      for (let clinicOwner of clinicOwners) {
          const option = document.createElement('option');
          option.value = clinicOwner.id; // Usa el ID como valor
          option.textContent = clinicOwner.name; // Muestra el nombre como texto
          selectClinicOwners.appendChild(option); // Agrega la opción al select
      }
  } catch (error) {
      console.error('Error al cargar clinic owners:', error);
      alert('Hubo un error al cargar los clinic owners.');
  }
}

async function registerPetOwners() {
  const datos = {}

  datos.name = document.getElementById('name').value;
  datos.email = document.getElementById('email').value;
  datos.phone = document.getElementById('phone').value;
  datos.password = document.getElementById('password').value;
  datos.role = "PET_OWNER";
  
  // Obtener el clinicOwner completo desde el select
  const clinicOwnerId = document.getElementById('clinicOwners').value;
  
  // Asegúrate de que se haya seleccionado un clinicOwner
  if (!clinicOwnerId) {
      alert('Por favor, selecciona un Clinic Owner.');
      return;
  }

  // Buscar el objeto clinicOwner completo
  const clinicOwner = clinicOwners.find(owner => owner.id === parseInt(clinicOwnerId));

  if (clinicOwner) {
      datos.clinicOwner = clinicOwner; // Enviar el objeto completo
  } else {
      alert('Por favor, selecciona un Clinic Owner válido.');
      return;
  }
  
  const request = await fetch('api/petOwners', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos),
  });

  console.log(datos)
    if (request.ok) {
      window.location.href = 'loginPetOwner.html';
      } else {
      alert('Error al registrar el ClinicOwner');
  }
  
}

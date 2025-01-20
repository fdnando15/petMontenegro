// Call the dataTables jQuery plugin
$(document).ready(function() {

    //findVetId() PROBLEM here need vetId;
    //cargarAvailableSlots(1, document.getElementById('date').value) PROBLEM HERE NEED DATE BEFORE TO SEND;
    cargarAvailableSlots(1, '2025/06/01')

});


async function registerConsultation(petId) {

    datos = {}

    datos.name = document.getElementById('subject').value
    datos.birthDay = document.getElementById('date').value
    datos.description = document.getElementById('description').value
    datos.slot = document.getElementById('slot').value
    
    const request = await fetch('/api/newConsultation/' + petId, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      },
      body: JSON.stringify(datos),
    });

    const response =  await request.json();      
    
    if (request.ok) {
        alert('Consultation registrado correctamente')
        window.location.href = '/consultations.html';
    } else {
        alert('Error al registrar la consultation')
    }
}

async function cargarAvailableSlots(VetId, date) {
    try {
  
        const response = await fetch('api/availableSlots/' + VetId + '/?date=' + date, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
  
        if (!response.ok) {
            throw new Error('Error al cargar las consultas del veterinario disponibles.');
        }
  
        slots = await response.json(); // Guarda el array de clinicOwners en la variable global
        console.log(slots);
  
        // Obtén los slots del select
        const selectSlots = document.querySelector('#slot');
  
        // Limpia las opciones previas del select
        selectSlots.innerHTML = '<option value="">--Selecciona--</option>';
  
        // Genera las opciones dinámicamente
        for (let slot of slots) {
            const option = document.createElement('option');
            option.value = slot;
            selectSlots.appendChild(option); // Agrega la opción al select
        }
    } catch (error) {
        console.error('Error al cargar slots:', error);
        alert('Hubo un error al cargar los slots.');
    }
  }
 
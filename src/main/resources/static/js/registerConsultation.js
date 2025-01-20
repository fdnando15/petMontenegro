// Call the dataTables jQuery plugin
$(document).ready(function() {


    //findVetId() PROBLEM here need vetId;
    //cargarAvailableSlots(1, document.getElementById('date').value) PROBLEM HERE NEED DATE BEFORE TO SEND;

});

$('#date').change(function() {
    cargarAvailableSlots(1, document.getElementById('date').value);
}
);

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

/*  async function getVetId(petId) {}*/

async function registerConsultation(petId) {

    datos = {}

    datos.subject = document.getElementById('subject').value
    datos.date = document.getElementById('date').value  
    datos.description = document.getElementById('description').value
    datos.slotTime = document.getElementById('slot').value
    
    const request = await fetch('/api/newConsultation/' + petId, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      },
      body: JSON.stringify(datos),
    });
     
    
    if (request.ok) {
        alert('Consultation registrado correctamente')
        window.location.href = '/consultations.html';
    } else {
        alert('Error al registrar la consultation')
    }
}

async function cargarAvailableSlots(VetId, date) {
    try {
  
        const response = await fetch('api/availableSlots/' + VetId + '/' + date, {
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
  
        let slotMap = {
            'NINE_AM_PART1': '9:00 - 9:30',
            'NINE_AM_PART2': '9:30 - 10:00',
            'TEN_AM_PART1': '10:00 - 10:30',
            'TEN_AM_PART2': '10:30 - 11:00',
            'ELEVEN_AM_PART1': '11:00 - 11:30',
            'ELEVEN_AM_PART2': '11:30 - 12:00',
            'TWELVE_PM_PART1': '12:00 - 12:30',
            'TWELVE_PM_PART2': '12:30 - 13:00',
            'THREE_PM_PART1': '13:00 - 13:30',
            'THREE_PM_PART2': '13:30 - 14:00',
            'FOUR_PM_PART1': '14:00 - 14:30',
            'FOUR_PM_PART2': '14:30 - 15:00',
            'FIVE_PM_PART1': '15:00 - 15:30',
            'FIVE_PM_PART2': '15:30 - 16:00',
            'SIX_PM_PART1': '16:00 - 16:30',
            'SIX_PM_PART2': '16:30 - 17:00',
            'SEVEN_PM_PART1': '17:00 - 17:30',
            'SEVEN_PM_PART2': '17:30 - 18:00',
            'EIGHT_PM_PART1': '18:00 - 18:30',
            'EIGHT_PM_PART2': '18:30 - 19:00',
            'NINE_PM_PART1': '19:00 - 19:30',
            'NINE_PM_PART2': '19:30 - 20:00'
        }
        // Genera las opciones dinámicamente
        for (let slot of slots) {
            const option = document.createElement('option');
            option.value = slot;
            option.textContent = slotMap[slot];
            selectSlots.appendChild(option); // Agrega la opción al select
        }
    } catch (error) {
        console.error('Error al cargar slots:', error);
        alert('Hubo un error al cargar los slots.');
    }
  }
 
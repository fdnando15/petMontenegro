function getQueryParam(name) {
    let urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

$(document).ready(function() {

    actualizarEmail()
    //mostrarLocalStorage()
    cargarConsultations(getQueryParam("id"))
    let url = $("#consultation").attr('href');
    url = url + "?id=" + getQueryParam("id");

    $("#consultation").attr('href', url);
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



async function cargarConsultations(petId) {
    const request = await fetch('api/consultations/'+ petId, {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      }
    });
    const consultations = await request.json();
    console.log(consultations)
  
  
    let listadoHtml = '';
    for (consultation of consultations) {
      
      let consultationHtml = '<tr><td>' + consultation.subject  + '</a></td><td>' + consultation.date + '</td><td>'+ consultation.description + '</td><td>'
      + consultation.slotTime +'</td></tr>';
      listadoHtml += consultationHtml;
    }
  
  document.querySelector('#consultations tbody').outerHTML = listadoHtml;
  }
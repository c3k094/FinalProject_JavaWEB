const patientId = document.getElementById('patientId').value;
const manipulationsData = document.getElementById('manipulationsData');

getManipulations(patientId);

function getManipulations(patientId) {
    fetch(`http://localhost:8080/api/${patientId}/manipulations`, {
        headers: {
            "Accept": "application/json"
        }
    }).then(res => res.json())
        .then(data => {
            manipulationsData.innerHTML = "";
            showManipulations(data);
        });
}

function showManipulations(data) {
    for (const manip of data) {
        manipulationsData.innerHTML += `
        <tr>
        <td><span>${manip.manipulationDate}</span></td>
        <td><span>${manip.manipulation}</span></td>
        <td><span>${manip.doctorFirstname + '' + manip.doctorLastName}</span></td>
        <td><span>${manip.additionalInformation}</span></td>
        <td class="text-center align-middle">
           <div class="btn-group align-top">
               <a class="btn btn-sm btn-outline-secondary" href="/manipulations/${manip.id}/edit">Edit</a>
               <a class="btn btn-sm btn-outline-secondary" href="/manipulations/${manip.id}/delete"><i class="fa fa-trash"></i></a>
           </div>
        </td>
    </tr>
        `
    }

}


$(document).ready(function () {
    $('#dtVerticalScrollExample').DataTable({
      "scrollY": "200px",
      "scrollCollapse": true,
    });
    $('.dataTables_length').addClass('bs-select');
  });

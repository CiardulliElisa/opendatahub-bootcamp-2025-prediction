async function getData() {
  console.log("in the getData method")
  const url = "https://mobility.api.opendatahub.com/v2/tree/ParkingStation/*/latest?where=scode.eq.\"103\",sactive.eq.true";

  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }

    const json = await response.json();
    const result = json.data.ParkingStation.stations;
    displayResult(JSON.stringify(result));
  } catch (error) {
    console.error(error.message);
  }
}

function displayResult(result) {
  console.log("in displayresult: " + result)
  const paragraph = document.getElementById('data');
  if (result && result.length > 0) {
    paragraph.textContent = `Station Data: ` + result;
  } else {
    paragraph.textContent = "No data available.";
  }
}

window.onload = getData;


function saveData(jsonData){
    let csv = '';

    // Extract headers
    const headers = Object.keys(jsonData[0]);
    csv += headers.join(',') + '\n';

    // Extract values
    jsonData.forEach(obj => {
      const values = headers.map(header => obj[header]);
      csv += values.join(',') + '\n';
    });

    var fs = require('fs');
    fs.writeFile('./parkingLotData.csv',csv,(err)={
        if(err){
            console.log(err)
            throw new Error(err)
        }
  });

}



async function getData() {
  console.log("in the getData method")
  const baseUrl = "https://tourism.api.opendatahub.com/v1/EventShort";

  const params = new URLSearchParams({
    pagenumber: "1",
  });

  const url = `${baseUrl}?${params.toString()}`;

  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }

    const json = await response.json();
    console.log(json.Items);
    return json;
  } catch (error) {
    console.error(error.message);
  }
}



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



async function getData() {
  console.log("in the getData method")
  const baseUrl = "https://mobility.api.opendatahub.com/v2/tree%2Cnode/ParkingStation/%2A/latest";

  const params = new URLSearchParams({
    limit: 200,
    offset: 0,
    shownull: false,
    distinct : true,
    timezone: UTC
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
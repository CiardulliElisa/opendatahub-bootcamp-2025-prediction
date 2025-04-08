async function getData() {
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
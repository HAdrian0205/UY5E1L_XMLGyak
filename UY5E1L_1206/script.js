const jsonUrl = 'kurzusfelvetelUY5E1L.json';

async function fetchJson() {
  try {
    const response = await fetch(jsonUrl);
    const jsonData = await response.json();

    flattenObject(jsonData, '');
  } catch (error) {
    console.error('Hiba történt:', error);
  }
}

function flattenObject(obj, indent) {
  for (const key in obj) {
    if (obj.hasOwnProperty(key)) {
      const value = obj[key];
      if (typeof value === 'object' && value !== null) {
        document.body.innerHTML += `<p>${indent}${key}</p>`;
        flattenObject(value, indent + '&nbsp;&nbsp;');
      } else {
        document.body.innerHTML += `<p>${indent}${key}: ${value}</p>`;
      }
    }
  }
}

fetchJson();
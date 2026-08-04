let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", search);

async function search(){
    let inputBox = document.getElementById("search");
    let value = inputBox.value;
    const url = `http://localhost:8080/spotify/albuns?name=${value}`;
    try {
        const response = await fetch(url);
        if(!response.ok){
            throw new Error("[ERROR] Response status: " + response.status);
        }
        const result = await response.json();
        printJson(result);
    } catch (error) {
        console.error(error.message);
    }
}

function printJson(json){
    let container = document.getElementById("card");
    container.textContent = "";
    json.forEach(
        album => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
            <div id="card"> 
                <h2>${album.title}</h2>
                <img src="${album.capeUrl}">   
                <p>${album.author}</p>
                <p>${album.releaseDate.substring(0, 4)}
            </div>
        `
        container.appendChild(card);
    });
}
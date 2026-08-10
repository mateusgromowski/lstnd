let searchButton = document.getElementById("searchButton");
let inputBox = document.getElementById("search");

searchButton.addEventListener("click", search);
inputBox.addEventListener('keydown', (event) => {
    if(event.key == "Enter"){
        event.preventDefault();
        search();
    }
});

async function search(){
    let inputBox = document.getElementById("search");
    let value = inputBox.value;
    const url = `/spotify/albums?name=${value}`;
    try {
        let mainText = document.getElementById("center");
        mainText.textContent = "loading..."
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
    let container = document.getElementById("container");
    let searchText = document.getElementById("center");
    searchText.textContent = "";
    container.textContent = "";
    json.forEach(
        album => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
            
                <h2>${album.title}</h2>
                <img src="${album.capeUrl}">   
                <p>${album.author}</p>
                <p>${album.releaseDate.substring(0, 4)}
            
            <div class="overlay">
                <button class="open-btn">See reviews</button>
            </div>
        `
        card.addEventListener("click", () => {
            window.location.href = `/review.html?id=${album.id}`;
        });
        container.appendChild(card);
        
    });
}
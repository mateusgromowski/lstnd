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
        console.table(result);
    } catch (error) {
        console.error(error.message);
    }
}
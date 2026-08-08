const albumId = new URLSearchParams(window.location.search).get("id");
showAlbum();
async function showAlbum(){
    const url = `/spotify/albums/${albumId}`;
    try {
        const response = await fetch(url);
        if(!response.ok){
            throw new Error(`Response status: ${response.status}`);
        }
        const responseJson = await response.json();
        printAlbum(responseJson);
    } catch (error) {
        console.error(error.message);
    }
}

function printAlbum(album){
    let albumInfo = document.getElementById("albumInfo");
    albumInfo.innerHTML = `
        <img src="${album.capeUrl}">
        <h3>${album.title}</h3>
        <p>${album.author}</p>
        <p>${album.releaseDate}</p>
    `
}
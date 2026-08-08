const albumId = new URLSearchParams(window.location.search).get("id");

showAlbum();

async function showAlbum() {
    const url = `/spotify/albums/${albumId}`;

    try {
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }

        const responseJson = await response.json();

        printAlbum(responseJson);

    } catch (error) {
        console.error(error.message);
    }
}

function printAlbum(album) {
    const albumInfo = document.getElementById("albumInfo");

    albumInfo.innerHTML = `
        <img class="album-cover" src="${album.capeUrl}" alt="Capa do álbum">

        <div class="album-details">
            <h1>${album.title}</h1>

            <div class="album-meta">
                <p>${album.author}</p>
                <p>${album.releaseDate}</p>
            </div>
        </div>
    `;
}
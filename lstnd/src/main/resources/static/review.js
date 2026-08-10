const albumId = new URLSearchParams(window.location.search).get("id");
const sendBtn = document.getElementById("send");
sendBtn.addEventListener("click", sendReview);
showAlbum();
getReviews();

async function sendReview(){
    const username = document.getElementById("namebox").value;
    const reviewBox = document.getElementById("review")
    let review = reviewBox.value;
    reviewBox.value = "";
    const response = await fetch(`/reviews/${albumId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "user_name": username,
            "review": review,
            "score": 0
        })
    }); 
    await getReviews();
}
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

function printReviews(json){
    let reviews = document.getElementById("reviews");
    json.forEach(
        review => {
            const reviewCard = document.createElement("div");
            reviewCard.className = "reviewBox";
            reviewCard.innerHTML = `
                <div id="username">
                    <h2>${review.userName}</h2>
                </div>
                <div id="review">
                    ${review.review}
                </div>
            `

            reviews.appendChild(reviewCard);
    });
}
async function getReviews() {
    const url = `/reviews/list/${albumId}`;
    document.getElementById("reviews").textContent = "";
    try {
        const response = await fetch(url);
        if(!response.ok){
            throw new Error(`Response status: ${response.status}`);
        }
        const responseJson = await response.json();
        printReviews(responseJson);
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
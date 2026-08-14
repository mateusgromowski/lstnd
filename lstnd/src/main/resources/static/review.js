const albumId = new URLSearchParams(window.location.search).get("id");
const sendBtn = document.getElementById("send");
sendBtn.addEventListener("click", sendReview);
const stars = document.querySelectorAll(".star");
let score = 0;
stars.forEach(star => {

    star.addEventListener("mouseenter", () => {

        const hoveredScore = Number(star.dataset.score);

        stars.forEach(s => {
            const starScore = Number(s.dataset.score);

            s.classList.toggle(
                "active",
                starScore <= hoveredScore
            );
        });

    });

    star.addEventListener("click", () => {
        score = Number(star.dataset.score);
    });

});

document.querySelector(".rating").addEventListener("mouseleave", () => {

    stars.forEach(star => {
        const starScore = Number(star.dataset.score);

        star.classList.toggle(
            "active",
            starScore <= score
        );
    });

});
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
            "score": score
        })
    }); 
    await getReviews();
    await showAlbum(); 
}
async function showAlbum() {
    const url = `albums/${albumId}`;
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

function getStars(score){
    let stars = "";

    for (let i = 1; i <= 5; i++) {
        stars += i <= score ? "★" : "☆";
    }

    return stars; 
}

function printReviews(json) {
    const reviews = document.getElementById("reviews");
    reviews.textContent = "";

    json.forEach(review => {

        const reviewCard = document.createElement("div");
        reviewCard.className = "reviewBox";

        let stars = getStars(review.score);
        

        reviewCard.innerHTML = `
            <div class="review-header">
                <h2>${review.userName}</h2>

                <div class="review-stars">
                    ${stars}
                </div>
            </div>

            <div class="review-text">
                ${review.review}
            </div>
        `;

        reviews.appendChild(reviewCard);
    });
}
async function getReviews() {
    const url = `/reviews?spotifyId=${albumId}`;
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
    let stars = getStars(album.score);
    albumInfo.innerHTML = `
        <img class="album-cover" src="${album.capeUrl}" alt="Capa do álbum">
        <div class="album-details">
            <h1>${album.title}</h1>
            <div class="album-meta">
                <p>${album.author}</p>
                <p>${album.releaseDate}</p>
                <div class="review-stars">${stars}</div>
            </div>
        </div>
    `;
}
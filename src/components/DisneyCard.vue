<template>
    <div>
        <div id="character-card">
            <div id="character-card-details">
                <h1>{{ card.name }}</h1>
                <img class="image" :src="card.imageUrl" alt="">
                <div class="film" v-if="card.films && card.films.length > 0">
                    <img class="icon-img" src="../assets/film.png"/>
                    <h2>Film Career</h2>
                    <p>{{ card.films.join(', ') }}</p>
                </div>
                <div class="tv" v-if="card.tvShows && card.tvShows.length > 0">
                    <img class="icon-img" src="../assets/tv icon.png"/>
                    <h2>TV Career</h2>
                    <p>{{ card.tvShows.join(', ') }}</p>
                </div>
                <div class="games" v-if="card.videoGames && card.videoGames.length > 0">
                    <img class="icon-img" src="../assets/games.png"/>
                    <h2>Video Games</h2>
                    <p>{{ card.videoGames.join(', ') }}</p>
                </div>
                <div class="park" v-if="card.parkAttractions && card.parkAttractions.length > 0">
                    <img class="icon-img" src="../assets/park.png">
                    <h2>Park Attractions</h2>
                    <p> {{ card.parkAttractions.join(', ') }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'disneyCard',
    props: {
        card: Object
    },
};
</script>

<style scoped>
.image{
    border-radius: 3px;
    margin-bottom: 10px;
}

.icon-img{
    margin-top: 10px;
    max-width: 40px;
}

</style>
<template>
    <div>
        <div id="character_card">
            <div id="character_card_details">
                <h1>{{ card.name }}</h1>
                <img :src="card.imageUrl" alt="">
                <div v-if="card.films && card.films.length > 0">
                    <h2>Film Career</h2>
                    <p>{{ card.films.join(', ') }}</p>
                </div>
                <div v-if="card.tvShows && card.tvShows.length > 0">
                    <h2>TV Career</h2>
                    <p>{{ card.tvShows.join(', ') }}</p>
                </div>
                <div v-if="card.videoGames && card.videoGames.length > 0">
                    <h2>Video Games</h2>
                    <p>{{ card.videoGames.join(', ') }}</p>
                </div>
                <div v-if="card.parkAttractions && card.parkAttractions.length > 0">
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
/* CSS for your component goes here */
</style>
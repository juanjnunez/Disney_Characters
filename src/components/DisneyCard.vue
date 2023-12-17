<template>
    <div>
        {{ card.name }}
        <img :src="card.imageUrl" alt="">
        <div v-if="card.videoGames && card.videoGames.length > 0">
            <p>Video Games</p>
            {{ card.videoGames.join(', ') }}
        </div>
    </div>
</template>

<script>
export default {
    name: 'disneyCard',
    props: ['card'],
    data() {
        return {
            // data properties go here
        };
    },
    methods: {
        // methods go here
    }
};
</script>

<style scoped>
/* CSS for your component goes here */
</style>
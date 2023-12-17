<template>
  <div>
    <disneyCard v-for="card in cards" :key="card._id" :card="card"/>
  </div>
</template>

<script>
import disneyService from '@/Services/DisneyService';
import disneyCard from '@/components/DisneyCard.vue';

export default {
  name: 'disneyList',

  data() {
    return {
      cards: [],
    };
  },

  created(){
    disneyService.getCharacters()
    .then((response) => {
      this.cards = response.data.data;
    })
  },

  components : { disneyCard },

  methods: {
    // methods go here
  }
};
</script>

<style scoped>
/* CSS for your component goes here */
</style>
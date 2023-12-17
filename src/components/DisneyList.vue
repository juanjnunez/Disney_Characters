<template>
  <div>
    <div v-if="isLoading" class="spinner"></div>
    <div v-else>
      <disneyCard :key="currentCard._id" :card="currentCard" />
      <button class="button" @click="previousCharacter" :disabled="counter <= 0">Previous</button>
      <button class="button" @click="nextCharacter" :disabled="counter >= cards.length - 1">Next</button>
    </div>
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
      isLoading: true,
      counter: 0
    };
  },

  created() {
    disneyService.getCharacters()
      .then((response) => {
        this.cards = response.data.data;
        this.isLoading = false;
      })
      .catch(error => {
        console.error('Error fetching characters:', error);
        this.isLoading = false;
      });
  },

  components: { disneyCard },

  methods: {
    nextCharacter() {
      if (this.counter < this.cards.length - 1) {
        this.counter++;
      }
    },
    previousCharacter() {
      if (this.counter > 0) {
        this.counter--;
      }
    }
  },

  computed: {
    currentCard() {
      if (this.counter >= 0 && this.counter < this.cards.length) {
        return this.cards[this.counter];
      }
      return null;
    }
  }

};
</script>

<style scoped>
@keyframes spin {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}
</style>

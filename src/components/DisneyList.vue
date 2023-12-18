<template>
  <div>
    <div v-if="isLoading" class="spinner"></div>
    <div class="card" v-else>
      <div class="card-header">
        <button class="button-icon previous" @click="previousCharacter" :disabled="counter <= 0">Previous</button>
        <button class="button-icon next" @click="nextCharacter" :disabled="counter >= cards.length - 1">Next</button>
      </div>
      <div class="card-content" :class="{ 'expanded': expanded }">
        <disneyCard :key="currentCard._id" :card="currentCard" />
        <button class="see-more" @click="toggleExpand">{{ expanded ? 'See less ↑' : 'See more ↓' }}</button>
      </div>
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
      counter: 0,
      expanded: false
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
    },

    toggleExpand() {
      this.expanded = !this.expanded;
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
.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  position: relative;
  max-width: 350px;
  margin-left: auto;
  margin-right: auto;
}

.card-header {
  display: flex;
  justify-content: flex-end;
  padding: 10px;
}

.button-icon {
  background: none;
  border: none;
  cursor: pointer;
  padding: 5px 10px;
  font-size: 16px;
}

.button-icon:hover {
  background-color: #ddd;
}

.see-more {
  z-index: 2;
  background: #9696f5;
  color: white;
  border: none;
  border-radius: 30px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  font-size: 14px;
  font-weight: bold;
  text-align: center;
  padding: 10px 15px;
  text-decoration: none;
  display: inline-block;
  cursor: pointer;
  transition: background-color 0.3s, box-shadow 0.3s;
  position: absolute;
  bottom: 0;
  transform: translateX(-50%);
  margin-bottom: 10px;
}

.see-more:hover,
.see-more:focus {
  background-color: #7a7aeb;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
  outline: none;
}

/* Initial state */
.card-content {
  padding: 20px;
  position: relative;
  max-height: 400px;
  overflow: hidden;
  transition: max-height 0.3s ease;
}

.card-content::before {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 20%; 
    background: linear-gradient(to top, rgba(255, 255, 255, 1), rgba(255, 255, 255, 0));
    z-index: 1; 
}

.card-content.expanded {
  padding-bottom: 60px;
  max-height: 100%;
}

.card-content.expanded::before {
    opacity: 0; 
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}
</style>

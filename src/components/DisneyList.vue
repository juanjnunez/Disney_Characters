<template>
  <div>
    <div v-if="isLoading" class="spinner"></div>
    <div class="card" v-else>
      <div class="card-header">
        <button class="button-icon previous" @click="previousCharacter" :disabled="counter <= 0">Previous</button>
        <button class="button-icon next" @click="nextCharacter" :disabled="counter >= cards.length - 1">Next</button>
      </div>
      <div class="card-content" :class="{ 'expanded': expanded }" ref="cardContent">
        <disneyCard :key="currentCard._id" :card="currentCard" />
        <div class="gradient" v-show="shouldShowSeeMore"></div>
        <button class="see-more" :class="{ 'expanded': expanded }" @click="toggleExpand"
          v-show="expanded || shouldShowSeeMore">
          {{ expanded ? 'See less ↑' : 'See more ↓' }}
        </button>
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
      expanded: false,
      contentHeight: 0
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

  mounted() {
    this.$nextTick(() => {
      if (this.$refs.cardContent) {
        this.contentHeight = this.$refs.cardContent.clientHeight;
      }
    });
  },

  updated() {
    this.$nextTick(() => {
      if (this.$refs.cardContent) {
        this.contentHeight = this.$refs.cardContent.clientHeight;
      }
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
    },

    checkContentHeight() {
      if (this.$refs.cardContent) {
        this.contentHeight = this.$refs.cardContent.clientHeight;
      } else {
        this.contentHeight = 399;
      }
    },
  },

  computed: {
    currentCard() {
      if (this.counter >= 0 && this.counter < this.cards.length) {
        return this.cards[this.counter];
      }
      return null;
    },

    shouldShowSeeMore() {
      return !this.expanded && this.contentHeight > 400;
    },
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
  margin-bottom: 30px;
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
  padding: 5px 15px;
  text-decoration: none;
  display: inline-block;
  cursor: pointer;
  position: absolute;
  transform: translateX(-50%);
  margin-bottom: 15px;
  bottom: 0;
}

.see-more.expanded {
  bottom: -5px;
  margin-bottom: 15px;
}

.see-more:hover {
  background-color: #6d6dde;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
  outline: none;
}

/* Initial state */
.card-content {
  padding-bottom: 15px;
  margin: 20px;
  position: relative;
  max-height: 450px;
  overflow: hidden;
}

.gradient {
  content: '';
  position: absolute;
  bottom: 0px;
  left: 0;
  width: 100%;
  height: 20%;
  background: linear-gradient(to top, rgba(255, 255, 255, 1), rgba(255, 255, 255, 0));
  z-index: 1;
}

.card-content.expanded {
  padding-bottom: 50px;
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

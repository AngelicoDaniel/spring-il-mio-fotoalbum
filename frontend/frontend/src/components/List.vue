<script>
import FotoCard from "./FotoCard.vue";

export default {
  data() {
    return {
      title: "La mia lista di foto!"
    }
  },

  components: {
    FotoCard,
  },
};
</script>

<template>
  <h1 class="mt-3">{{ title }}</h1>
  <div class="row mt-5">
      <FotoCard/>
   </div>
</template>

<style lang="scss" scoped></style>
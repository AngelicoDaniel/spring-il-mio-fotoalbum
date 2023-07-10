<script>
import List from "./components/List.vue";
import navbar from "./components/navbar.vue"

export default {
  data() {
    return {

    }
  },

  components: {
    List,navbar,
  },
};
</script>

<template>
  <navbar/>
  <div class="container">
    <List/>
  </div>
</template>

<style lang="scss" scoped></style>
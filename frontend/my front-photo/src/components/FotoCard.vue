<script>
// import MyComponent from "./components/MyComponent.vue";

export default {
  data() {
    return {

    }
  },

props:{
  photo:Object
},

};
</script>

<template>
  <div v-if="photo.visible !== false" class="col-4 col-md-6">
    <div class="card mb-3 text-light">
      <div class="row g-0">
        <div class="col-md-4">
          <img :src="photo.url" class="img-fluid rounded-start" :alt="photo.title">
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <h5 class="card-title">{{ photo.title }}</h5>
            <p class="card-text">{{ photo.description }}</p>
            <p class="card-text"><small class="text-body-secondary">{{ photo.createdAt }}</small></p>
            <button class="btn btn-light">Torna alla lista</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped></style>


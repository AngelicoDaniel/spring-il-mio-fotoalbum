<script>
import FotoCard from "./FotoCard.vue";
import axios from "axios";

export default {
  data() {
    return {
      photos: [],
      isVisible: false,
      title: "La mia lista di foto!",
      filterText: ""
    };
  },

  created() {
    const endpoint = "http://localhost:8080/api/v1";
    const getPhotos = async () => {
      try {
        const response = await axios.get(endpoint + "/photos");
        this.photos = response.data;
      } catch (error) {
        console.log(error);
      }
    };

    getPhotos();
  },

  computed: {
    filteredPhotos() {
      return this.photos.filter(photo => {
        return photo.title.toLowerCase().includes(this.filterText.toLowerCase());
      });
    }
  },

  methods: {
    filterPhotos() {
      // Esegui il filtraggio delle foto
      // Utilizzando il computed property filteredPhotos
    }
  },

  components: {
    FotoCard
  }
};
</script>
<template>
  <div class="titolo-ricerca row">
    <div class="col-6">
      <h1 class="mt-3">{{ title }}</h1>
    </div>


    <div class="col-6 d-flex flex-column justify-content-center">
      <input type="text" v-model="filterText" placeholder="Filtra per titolo">
      <!-- <button @click="filterPhotos">Filtra</button> -->
    </div>

  </div>
  <div v-if="filteredPhotos.length !== 0">
    <div class="row mt-5">
      <FotoCard v-for="photo in filteredPhotos" :key="photo.id" :photo="photo"/>
    </div>
  </div>
  <h1 class="fw-bold mt-5" v-else>Al momento non ci sono foto</h1>
</template>
<style lang="scss" scoped></style>
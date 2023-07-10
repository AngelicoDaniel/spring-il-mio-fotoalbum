
<script>
import FotoCard from "./FotoCard.vue";
import axios from "axios";

export default {
  components: {
    FotoCard,
  },

  data() {
    return {
      photos: [],
      title: "La mia lista di foto!",
      filterText: "",
      pageNumber: 0,
      pageSize: 4,
    };
  },

  async created() {
    await this.getPhotos();
  },

  computed: {
    filteredPhotos() {
      return this.photos.filter((photo) =>
        photo.title.toLowerCase().includes(this.filterText.toLowerCase())
      );
    },

    totalPages() {
      return Math.ceil(this.filteredPhotos.length / this.pageSize);
    },

    paginatedPhotos() {
      const startIndex = this.pageNumber * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.filteredPhotos.slice(startIndex, endIndex);
    },
  },

  methods: {
    async getPhotos() {
      const endpoint = "http://localhost:8080/api/v1/photos";
      try {
        const response = await axios.get(endpoint);
        this.photos = response.data;
      } catch (error) {
        console.log(error);
      }
    },

    previousPage() {
      if (this.pageNumber > 0) {
        this.pageNumber--;
      }
    },

    nextPage() {
      if (this.pageNumber < this.totalPages - 1) {
        this.pageNumber++;
      }
    },
  },
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

  <div v-if="paginatedPhotos.length !== 0">
    <div class="row mt-4">
      <FotoCard v-for="photo in paginatedPhotos" :key="photo.id" :photo="photo"/>
    </div>

    <div class="pagination d-flex justify-content-center mt-4">
      <button class="text-light fw-bold mx-2" @click="previousPage" :disabled="pageNumber === 0">Pagina precedente</button>
      <span class="mx-2">Pagina {{ pageNumber + 1 }} di {{ totalPages }}</span>
      <button class="text-light fw-bold mx-2" @click="nextPage" :disabled="pageNumber === totalPages - 1">Pagina successiva</button>
    </div>
  </div>

  <h1 class="fw-bold mt-5" v-else>Al momento non ci sono foto</h1>
</template>
<style scoped>
</style>
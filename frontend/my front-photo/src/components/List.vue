<script>
import FotoCard from "./FotoCard.vue";
import axios from "axios";
// import  {endpoint}  from "../stores/endpoint.js"

export default {
  data() {
    return {
      photos: [],
      isVisible:false,
      title: "La mia lista di foto!"
    }
},

created() {
  const endpoint = "http://localhost:8080/api/v1";
  const getPhotos = async () => {
    try {
      const response = await axios.get(endpoint + '/photos');
      this.photos = response.data;

    } catch (error) {
      console.log(error);
    }

      console.log(this.photos);

  };

  getPhotos();
},


  components: {
    FotoCard,
  },
};
</script>

<template>
  <div v-if="this.photos.lenght != 0" >
    <h1 class="mt-3">{{ title }}</h1>
    <div class="row mt-5">
        <FotoCard v-for="photo in photos" :key="photo.id" :photo="photo"/>
     </div>
  </div>
  <div v-else="" >Non ci sono foto al momento</div>


</template>

<style lang="scss" scoped></style>
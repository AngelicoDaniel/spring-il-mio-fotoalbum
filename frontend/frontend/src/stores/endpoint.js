import { useLocalStorage } from "@vueuse/core"


export const useEndpointStore = defineStore("endpoint", {
    state: () => {
        return {
            endpoint: "http://localhost:8080/api/v1",
        };
    },
   },
);   
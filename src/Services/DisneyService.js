import axios from 'axios';

const http = axios.create({
    baseURL : "https://api.disneyapi.dev/character"
});

export default {
    getCharacters(){
        return http.get()
    }
}
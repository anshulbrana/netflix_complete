import axios from 'axios'

export const MovieList = async (movieType) => { 
    const responseMovies = axios.get(`/javanetflix/get-movie/${movieType}`);
    return responseMovies
};
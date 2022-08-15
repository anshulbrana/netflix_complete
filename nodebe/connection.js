const mongoose = require('mongoose')
const movieSchema = require('./schemas/movie-schema')

//For connection
mongoose.connect('mongodb://localhost/movie');

mongoose.connection.once('open',function(){
    // console.log('connection suceed')

    const movie = {
        title: 'Shrek-6',
        releaseDate: '10-October-2023',
        category: 'Action, Adventure, Comedy',
        movieDirector: 'Animation'
    }

    new movieSchema(movie).save()

}).on('error',function(error){
    console.log('error is ',error)
})
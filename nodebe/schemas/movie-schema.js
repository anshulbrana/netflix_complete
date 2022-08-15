const mongoose = require('mongoose')

const movieSchema = mongoose.Schema({
    
    title: String,
    releaseDate: String,
    category: String,
    movieDirector: String
})

module.exports = mongoose.model('movies',movieSchema)
const mongoose = require('mongoose')

const movieDefSchema = new mongoose.Schema({
    title: {
        type: String,
        trim: true,
    },
    releaseDate: {
        type: String,
        trim: true,
    },
    category: {
        type: String,
        trim: true,
    },
    movieDirector: {
        type: String,
        trim: true,
    },
    externalId: {
        type: String,
        trim: true,
    }
})

module.exports = mongoose.model('MoviesDefinitions', movieDefSchema)
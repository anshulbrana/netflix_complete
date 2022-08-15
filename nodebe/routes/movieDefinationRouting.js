const express = require('express')
// const fetch = require('node-fetch')
const axios = require('axios')

let baseURL = "http://localhost:8070/netflix/"

const movieDefModel = require('../models/movieDefModel')

const movieModel = require('../models/movieDefModel')

const Router = express.Router()

Router.post('/add-moviedef', async (req, res) => {
    const url = baseURL.concat('movies')
    const formResponse = req.body
    const header = {
        'Content-Type': 'application/json',
    }
    await axios.post(url, formResponse, {header});

    const movieObj = new movieDefModel(formResponse)
    await movieObj.save()
    return res.status(200).json(movieObj)
})

Router.get('/get-movie/:category', async(req, res) => {
    const {category} = req.params
    console.log(category);
    const movies = await movieDefModel.find({category: category}, {_id: 0 })
   return res.status(200).json(movies)
});

function handleErrors(response) {
    if(!response.ok) {
      throw new Error("Request failed " + response.statusText);
    }
    return response;
}


module.exports = Router

import { render } from '@testing-library/react'
import React from 'react'
import Main from '../components/Main'
import Row from '../components/Row'
import requests from '../Requests'

const Home = () => {

  console.log("-----------HOOOOOOOMEEEEEE----------")
  var tokenAccess = sessionStorage.getItem("sessionToken");
  console.log(tokenAccess);
  console.log("---------END HOOOOOMEEEEE------------")


  var axios = require('axios');
  var bearer = 'Bearer '.concat(tokenAccess.replaceAll('"', ''))

  console.log(bearer)
  
var config = {
  method: 'get',
  url: 'https://localhost:8443/apiman-gateway/Netflix/movies/1.1?apikey=77fd0277-104c-4192-be6d-8f7a83c598b3',
  headers: { 
    'Authorization': bearer 
  }
};

axios(config)
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});

  return (
    
    <>
    <Main />
    <Row rowID= '1' title = 'Action' movieCat="Action" />
    <Row rowID= '2' title = 'Adventure' movieCat="Adventure" />
    <Row rowID= '3' title = 'Comedy' movieCat="Comedy"   />
    <Row rowID= '4' title = 'Animation' movieCat="Animation" />

    </>
  )
}


export default Home
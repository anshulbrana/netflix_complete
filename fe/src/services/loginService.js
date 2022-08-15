var axios = require('axios');
var qs = require('qs');
let data = ""


export const postLoginData = async(value) => {

  var data = qs.stringify({
    'client_id': 'test',
    'grant_type': 'password',
    'username': value.username,
    'password': value.password
  });

var config = {
  method: 'post',
  url: 'http://localhost:8080/auth/realms/test-netflix/protocol/openid-connect/token',
  headers: { 
    'Content-Type': 'application/x-www-form-urlencoded', 
    'Cookie': 'connect.sid=s%3A2ABOi8BCcATPltHMBrzCJwKGb-7qI6gK.KGiCUg%2BgXV3ExGkXQD7%2F8KYnUK5a%2BO2jlAS%2Ba19rN0Q'
  },
  data : data
};

await axios(config)
.then(function (response) {
   data = JSON.stringify(response.data.access_token)

})
.catch(function (error) {
  data=400
});

return data

}
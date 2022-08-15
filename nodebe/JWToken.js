const jwt = require('jsonwebtoken')

function JWToken(request, response, next) {
    const bearer = request.headers.token
    
    if (bearer) {
        const token = bearer.split(' ')[1]

        jwt.verify(token, process.env.TOKEN_SECRET, (error, decoded) => {
            if (error) {
                return response.status(401).json({ "msg": "Invalid Token!" })
            }
            request.decoded = decoded
            next()
        })
    } else {
        return response.status(403).json({ "msg": "Access denied!" })
    }
}
module.exports = JWToken
import axios from 'axios'

export const postSignupData = async(value) => {
	try {
        const header = {
            'Content-Type': 'application/json',
        }
        await axios.post(`/javanetflix/users`, value, {header});
        window.location.href="/login"
    } catch (e) {
        throw e;
    }
  }
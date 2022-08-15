import { Link, useNavigate, Navigate } from 'react-router-dom';

import React, {useState, useEffect} from 'react'
import { postLoginData } from '../services/loginService';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [getToken, SetToken] = useState();
  
  var tokenAccess = sessionStorage.getItem("sessionToken");
  const handleSubmit = async (e) => {
    e.preventDefault();
    const value = {password, username}
    let token = await postLoginData(value)
    if (token !== 400) {
      sessionStorage.setItem("sessionToken", token);
      SetToken(token)
      
      window.location.href="/"
    }
    else{
      alert("Username or password is wrong")
    }
  

  };
  

  
  // const [accessTokens, setaccessToken] = useState();
 
  // const handleSubmit = async (e) => {
  //   e.preventDefault();
  //   try {
  //     const data = {email, password}
  //     const returnValue = await postLoginData(data);
  //     // console.log("Anshul Rana")
  //     // console.log(returnValue)
  //     tokenAccess = returnValue
  //     // setaccessToken(returnValue);
  //     console.log("-----------NNNNNNNEWWWWW----------")
  //     console.log(tokenAccess)
  //     console.log("-----------ENDDDDDDDDD----------")
  //     
  //     setaccessToken(tokenAccess)

  //     // return <Navigate to='/signup'  />

  //     window.location.href="/"
  //   } catch (error) {
  //     console.log(error);
  //   }
  // };
 

 


  return (
    <>
    <div className='w-full h-screen'>
       <img
        className='hidden sm:block absolute w-full h-full object-cover'
        src='https://assets.nflxext.com/ffe/siteui/vlv3/f841d4c7-10e1-40af-bcae-07a3f8dc141a/f6d7434e-d6de-4185-a6d4-c77a2d08737b/US-en-20220502-popsignuptwoweeks-perspective_alpha_website_medium.jpg'
        alt='/'
      />
      <div className='bg-black/60 fixed top-0 left-0 w-full h-screen'></div>

      <div className='fixed w-full px-4 py-24 z-50'>
        <div className='max-w-[450px] h-[600px] mx-auto bg-black/75 text-white'>
          <div className='max-w-[320px] mx-auto py-16'>
             <h1 className='text-3xl font-bold'>Sign In</h1>

             <form 
             onSubmit={handleSubmit}
             className='w-full flex flex-col py-4'>
               <input
                  // onChange={(e) => setEmail(e.target.value)}
                  onChange={(e) => setUsername(e.target.value)}
                  className='p-3 my-2 bg-gray-700 rouded'
                  type='text'
                  placeholder='User Name'
                  autoComplete='text'
                />

                <input
                  onChange={(e) => setPassword(e.target.value)}
                  // onChange={(e) => setEmail(e.target.value)}
                  className='p-3 my-2 bg-gray-700 rouded'
                  type='password'
                  placeholder='Password'
                  autoComplete='password'
                />
                  
                  <button className='bg-red-600 py-3 my-6 rounded font-bold'>
                    Sign In
                  </button>
                

               {/* </Link> */}
               {/* </Link> */}
               <div className='flex justify-between items-center text-sm text-gray-600'>
                 <p>
                   <input className='mr-2' type='checkbox' />
                   Remember me
                 </p>
                 <p>Need Help?</p>
               </div>
               <p className='py-8'>
                 <span className='text-gray-600'>New to Netflix?</span>{' '}
                 <Link to='/signup'>Sign Up</Link>
               </p>
              </form>
          </div>
        </div>
        </div>
      
      </div>
    
    </>
  )
}

export default Login

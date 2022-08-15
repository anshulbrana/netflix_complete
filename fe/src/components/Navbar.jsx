import React from 'react'
import { Link } from 'react-router-dom'
// import { Link, useNavigate } from 'react-router-dom';
const logout = () => {
  sessionStorage.clear();
  window.location.href = '/login';
}

const Navbar = () => {

  // const navigate = useNavigate();
  return (
    <div className='flex items-center justify-between p-4 z-[100] w-full absolute'>
      <Link to='/'>
         <h1 className='text-red-600 text-4xl font-bold cursor-pointer'>
          NETFLIX
        </h1>
        </Link>

        <div>
        {/* <Link to='/login'>
            <button className='text-white pr-4'>Sign In</button>
            </Link> */}
            <button onClick={logout} className='bg-red-600 px-6 py-2 rounded cursor-pointer text-white'>
              Log Out
            </button>
        </div>
    </div> 

  )
}

export default Navbar
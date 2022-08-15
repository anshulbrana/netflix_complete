import { Route, Routes, BrowserRouter, Navigate } from 'react-router-dom';
// import Main from './components/Main';
// import { Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
// import { AuthContextProvider } from './context/AuthContext';
// import Home from './pages/Home';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Account from './pages/Account';
import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./Keycloak"
import PrivateRoute from 'react-router-keycloak/dist/PrivateRoute';
import React, { useState } from "react";
// import {AuthContextProvider} from './context/AuthContext';



function App() {
  let { user } = false
  var tokenAccess = sessionStorage.getItem("sessionToken");
  if (tokenAccess != null) {
    user = true
  }
  
  return (
    <>
    <ReactKeycloakProvider authClient={keycloak}>

    <Navbar />    
    
    <Routes>
    <Route exact path='/' element=
      {user ? <Home /> : <Navigate to="/login" />}/>
    <Route
             path="/secured"
             element={
               <PrivateRoute>
                 <Home />
               </PrivateRoute>
             }
           />
     <Route path = '/login' element={<Login />} />
    <Route path = '/signup' element={<SignUp />} />  
    <Route path = '/account' element={<Account />} />  
    </Routes>
    </ReactKeycloakProvider>
    </>
  );
}

export default App;

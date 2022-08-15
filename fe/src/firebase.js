// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyDUl_tzPY9E7F5AYSqUdwMLHTmGe_Jcn-M",
  authDomain: "netflix-clone-c3eb5.firebaseapp.com",
  projectId: "netflix-clone-c3eb5",
  storageBucket: "netflix-clone-c3eb5.appspot.com",
  messagingSenderId: "851907468306",
  appId: "1:851907468306:web:c6cdeaf8f075404700cccb"
};

// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const db = getFirestore(app)
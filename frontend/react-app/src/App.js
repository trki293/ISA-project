import logo from './logo.svg';
import StartPage from './components/UnauthorizeUser/StartPage';
import GetAllBoatUnauthorize from './components/UnauthorizeUser/GetAllBoatUnauthorize';
import GetAllCottagesUnauthorize from './components/UnauthorizeUser/GetAllCottagesUnauthorize';
import GetAllInstructionsUnauthorize from './components/UnauthorizeUser/GetAllInstructionsUnauthorize';
import RegistrationPage from './components/UnauthorizeUser/RegistrationPage';
import ConfirmationPage from './components/UnauthorizeUser/ConfirmationPage'
import NavBar from "./components/NavBar";
import LoginPage from "./components/UnauthorizeUser/LoginPage";
import React, { useState, useEffect } from "react";

import { BrowserRouter as Router, Redirect, Route, Switch } from "react-router-dom";
import './App.css';

function App() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const userId = localStorage.getItem("userId");
  const userRole = localStorage.getItem("roleUser");

  useEffect(() => {
    const handleWindowResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleWindowResize);

    return () => {
      window.removeEventListener("resize", handleWindowResize);
    };
  }, []);


const redirect = () => {
  if(userRole === "Client"){
    return <Redirect to="/client/homePage"></Redirect>
  }
  if(userRole === "SystemAdmin"){
    return <Redirect to="/systemAdmin/homePage"></Redirect>
  }
}

  return (
    <div className="App">
      <Router>

        <div>
          <>
          {(userRole === "CLIENT" &&  <Redirect to="/client/homePage"></Redirect> )}
          {(userRole === "SYSTEM_ADMIN" &&  <Redirect to="/systemAdmin/homePage"></Redirect> )}
          </>

          <>
            {(userRole === undefined || userRole === null) && (
              <NavBar user="unregistred" />
            )}
            {(userRole === "SYSTEM_ADMIN") && (
              <NavBar user="systemAdmin" />
            )}
            {(userRole === "CLIENT") && (
              <NavBar user="client" />
            )}
          </>

          <Switch>
            <Route exact path="/" component={StartPage}></Route>
            <Route exact path="/boats/unauthorize" component={GetAllBoatUnauthorize}></Route>
            <Route exact path="/cottages/unauthorize" component={GetAllCottagesUnauthorize}></Route>
            <Route exact path="/instructions/unauthorize" component={GetAllInstructionsUnauthorize}></Route>
            <Route exact path="/register" component={RegistrationPage}></Route>
            <Route exact path="/confirm_account/:token" component={ConfirmationPage}></Route>
            <Route exact path="/login" component={LoginPage}></Route>
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
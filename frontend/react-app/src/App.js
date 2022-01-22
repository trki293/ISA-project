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
import HomePageClient from './components/Client/HomePageClient';
import AllBoatsForClient from './components/Client/AllBoatsForClient';
import GetBoatQuickBookingsForClient from './components/Client/GetBoatQuickBookingsForClient';
import CreateBoatReservation from './components/Client/CreateBoatReservation';
import AllCottagesForClient from './components/Client/AllCottagesForClient';
import GetCottageQuickBookingsForClient from './components/Client/GetCottageQuickBookingsForClient';
import CreateCottageReservation from './components/Client/CreateCottageReservation';
import AllInstructionsForClient from './components/Client/AllInstructionsForClient';
import GetInstructionsQuickBookingsForClient from './components/Client/GetInstructionsQuickBookingsForClient';
import CreateInstructionsReservation from './components/Client/CreateInstructionsReservation';
import AllHistoryCottageReservation from './components/Client/AllHistoryCottageReservation';
import CreateCottageReview from './components/Client/CreateCottageReview';
import AllHistoryBoatReservation from './components/Client/AllHistoryBoatReservation';
import CreateBoatReview from './components/Client/CreateBoatReview';
import AllHistoryInstructionsReservation from './components/Client/AllHistoryInstructionsReservation';
import CreateInstructionsReview from './components/Client/CreateInstructionsReview';
import AllFutureReservation from './components/Client/AllFutureReservation';
import GetBoatsForClientComplaint from './components/Client/GetBoatsForClientComplaint';
import GetCottagesForClientComplaint from './components/Client/GetCottagesForClientComplaint';
import GetInstructionsForClientComplaint from './components/Client/GetInstructionsForClientComplaint';
import CreateBoatComplaint from './components/Client/CreateBoatComplaint';
import CreateInstructorComplaint from './components/Client/CreateInstructorComplaint';
import CreateCottageComplaint from './components/Client/CreateCottageComplaint';

import { BrowserRouter as Router, Redirect, Route, Switch } from "react-router-dom";

import './App.css';
import UserProfile from './components/Client/UserProfile';
import DeleteAccountRequest from './components/Client/DeleteAccountRequest';
import HomePageSystemAdmin from './components/SystemAdmin/HomePageSystemAdmin';
import AllEntities from './components/SystemAdmin/AllEntities';
import AllRegistrationRequests from './components/SystemAdmin/AllRegistrationRequests';
import CreateResponseForRegistrationRequest from './components/SystemAdmin/CreateResponseForRegistrationRequest';
import AllDeleteAccountRequests from './components/SystemAdmin/AllDeleteAccountRequests';
import CreateResponseForDeleteAccountRequest from './components/SystemAdmin/CreateResponseForDeleteAccountRequest';
import AllReports from './components/SystemAdmin/AllReports';
import CreateResponseForBoatOwner from './components/SystemAdmin/CreateResponseForBoatOwner';
import CreateResponseForCottageOwner from './components/SystemAdmin/CreateResponseForCottageOwnerReport';
import CreateResponseForInstructor from './components/SystemAdmin/CreateResponseForInstructorReport';
import AllComplaints from './components/SystemAdmin/AllComplaints';
import CreateResponseForInstructionsComplaint from './components/SystemAdmin/CreateResponseForInstructionsComplaint';
import CreateResponseForCottageComplaint from './components/SystemAdmin/CreateResponseForCottageComplaint';
import CreateResponseForBoatComplaint from './components/SystemAdmin/CreateResponseForBoatComplaint';
import AllReviews from './components/SystemAdmin/AllReviews';
import CreateSystemAdmin from './components/SystemAdmin/CreateSystemAdmin';
import SystemParameters from './components/SystemAdmin/SystemParameters';
import UserProfileAdmin from './components/SystemAdmin/UserProfileAdmin';
import ChangePasswordAfterFirstLoginAdmin from './components/UnauthorizeUser/ChangePasswordAfterFirstLoginAdmin';


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
  if(userRole === "CLIENT"){
    return <Redirect to="/client/homePage"></Redirect>
  }
  if(userRole === "SYSTEM_ADMIN"){
    return <Redirect to="/adminSystem/homePage"></Redirect>
  }
}

  return (
    <div className="App">
      <Router>

        <div>
          <>
          {(userRole === "CLIENT" &&  <Redirect to="/client/homePage"></Redirect> )}
          {(userRole === "SYSTEM_ADMIN" &&  <Redirect to="/adminSystem/homePage"></Redirect> )}
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
            <Route exact path="/client/homePage" component={HomePageClient}></Route>
            <Route exact path="/client/boatsForClient" component={AllBoatsForClient}></Route>
            <Route exact path="/client/quickReservations/getForBoat/:id" component={GetBoatQuickBookingsForClient}></Route>
            <Route exact path="/client/reservation/boat/:boatId/:beginDate/:endDate" component={CreateBoatReservation}></Route>
            <Route exact path="/client/cottagesForClient" component={AllCottagesForClient}></Route>
            <Route exact path="/client/quickReservations/getForCottage/:id" component={GetCottageQuickBookingsForClient}></Route>
            <Route exact path="/client/reservation/cottage/:cottageId/:beginDate/:endDate" component={CreateCottageReservation}></Route>
            <Route exact path="/client/instructionsForClient" component={AllInstructionsForClient}></Route>
            <Route exact path="/client/quickReservations/getForInstructions/:id" component={GetInstructionsQuickBookingsForClient}></Route>
            <Route exact path="/client/reservation/instructions/:instructionsId/:beginDate/:endDate" component={CreateInstructionsReservation}></Route>
            <Route exact path="/client/history/cottagesReservation" component={AllHistoryCottageReservation}></Route>
            <Route exact path="/client/review/cottage/:reservationId" component={CreateCottageReview}></Route>
            <Route exact path="/client/history/boatsReservation" component={AllHistoryBoatReservation}></Route>
            <Route exact path="/client/review/boat/:reservationId" component={CreateBoatReview}></Route>
            <Route exact path="/client/history/instructionsReservation" component={AllHistoryInstructionsReservation}></Route>
            <Route exact path="/client/review/instructions/:reservationId" component={CreateInstructionsReview}></Route>
            <Route exact path="/client/future/AllReservations" component={AllFutureReservation}></Route>
            <Route exact path="/client/boatForComplaint" component={GetBoatsForClientComplaint}></Route>
            <Route exact path="/client/cottagesForComplaint" component={GetCottagesForClientComplaint}></Route>
            <Route exact path="/client/instructionsForComplaint" component={GetInstructionsForClientComplaint}></Route>
            <Route exact path="/client/complaint/boat/:boatId" component={CreateBoatComplaint}></Route>
            <Route exact path="/client/complaint/cottage/:cottageId" component={CreateCottageComplaint}></Route>
            <Route exact path="/client/complaint/instructor/:instructorId" component={CreateInstructorComplaint}></Route>
            <Route exact path="/client/profile" component={UserProfile}></Route>
            <Route exact path="/client/deleteRequest/create" component={DeleteAccountRequest}></Route>
            <Route exact path="/adminSystem/homePage" component={HomePageSystemAdmin}></Route>
            <Route exact path="/adminSystem/entities" component={AllEntities}></Route>
            <Route exact path="/adminSystem/registrationRequests" component={AllRegistrationRequests}></Route>
            <Route exact path="/adminSystem/registrationRequest/reject/:requestId" component={CreateResponseForRegistrationRequest}></Route>
            <Route exact path="/adminSystem/deleteAccountRequests" component={AllDeleteAccountRequests}></Route>
            <Route exact path="/adminSystem/deleteAccountRequest/Response/:requestId" component={CreateResponseForDeleteAccountRequest}></Route>
            <Route exact path="/adminSystem/reports" component={AllReports}></Route>
            <Route exact path="/adminSystem/boatOwnerReport/review/:requestId" component={CreateResponseForBoatOwner}></Route>
            <Route exact path="/adminSystem/cottageOwnerReport/review/:requestId" component={CreateResponseForCottageOwner}></Route>
            <Route exact path="/adminSystem/instructorReport/review/:requestId" component={CreateResponseForInstructor}></Route>
            <Route exact path="/adminSystem/complaints" component={AllComplaints}></Route>
            <Route exact path="/adminSystem/cottageComplaint/review/:id" component={CreateResponseForCottageComplaint}></Route>
            <Route exact path="/adminSystem/boatComplaint/review/:id" component={CreateResponseForBoatComplaint}></Route>
            <Route exact path="/adminSystem/instructionsComplaint/review/:id" component={CreateResponseForInstructionsComplaint}></Route>
            <Route exact path="/adminSystem/reviews" component={AllReviews}></Route>
            <Route exact path="/adminSystem/createSystemAdmin" component={CreateSystemAdmin}></Route>
            <Route exact path="/adminSystem/systemParameters" component={SystemParameters}></Route>
            <Route exact path="/adminSystem/profile" component={UserProfileAdmin}></Route>
            <Route exact path="/changePassword/:email" component={ChangePasswordAfterFirstLoginAdmin}></Route>
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
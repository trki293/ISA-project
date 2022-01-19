import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";
import { Button } from "@material-ui/core";


const ConfirmationPage = () => {

    const [textHeader, setTextHeader] = useState("")
    const [showButton, setShowButton] = useState(false)

    useEffect(() => {
        var splitID = []
        splitID = window.location.href.split("/");
        const token = splitID[4];
        alert(token)
        axios
            .put('http://localhost:8080/auth/confirm_account/' + token, {}
            )
            .then((res) => {
                alert("Token founded!")
                setTextHeader("Successfuly confirmed account! You can now login on our system!")
                setShowButton(true);
            }).catch(function (error) {
                alert("Token not founded!")
                setTextHeader("Your token is invalid or token not founded! Please, contact our system admins!")
                setShowButton(false);


            });
    }, []);


    return (
        <div>
            <br />
            <h1>{textHeader}</h1>
            <br />
            { showButton? <Link to="/login"><Button>Go back to login</Button></Link> : null }
            
        </div>
    );

    /*
const redirect = ();
  */
};

export default ConfirmationPage;
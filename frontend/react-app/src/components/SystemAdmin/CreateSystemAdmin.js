import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Grid,
    TextField,
    Button,
    TableContainer
} from "@material-ui/core";
import {
    Combobox,
    ComboboxInput,
    ComboboxPopover,
    ComboboxList,
    ComboboxOption,
    ComboboxOptionText,
} from "@reach/combobox";
import "@reach/combobox/styles.css";
import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import isMatch from 'date-fns/isMatch'
import { set } from "date-fns";


const CreateSystemAdmin = () => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [streetName, setStreetName] = useState("");
    const [streetNumber, setStreetNumber] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [password, setPassword] = useState("");
    const [confirmationPassword, setConfirmationPassword] = useState("");

    const createSystemAdmin = () => {
        axios
            .post("http://localhost:8080/system_admins/createNew", {
                firstName: firstName,
                lastName: lastName,
                email: email,
                address: {
                    streetName: streetName,
                    streetNumber: streetNumber,
                    city: city,
                    country: country
                },
                phoneNumber: phoneNumber, 
                password : password,
                confirmationPassword : confirmationPassword
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly updated info!");
                window.location.href = "http://localhost:3000/adminSystem/homePage";
            });
    }

    return (
        <div>
            <br />
            <br />
            <TextField label="Email" value={email} onChange={(e) => setEmail(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="First Name" value={firstName} onChange={(e) => setFirstName(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Street Name" value={streetName} onChange={(e) => setStreetName(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Street Number" value={streetNumber} onChange={(e) => setStreetNumber(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="City" value={city} onChange={(e) => setCity(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Country" value={country} onChange={(e) => setCountry(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Phone Number" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField type="password" value={password} onChange={(e) => setPassword(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField type="password" value={confirmationPassword} onChange={(e) => setConfirmationPassword(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <Button onClick={() => createSystemAdmin(true)} variant="contained" color="primary">Create</Button>
            
        </div>
    );
};

export default CreateSystemAdmin;
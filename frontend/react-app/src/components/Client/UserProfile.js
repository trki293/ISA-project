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


const UserProfile = () => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [streetName, setStreetName] = useState("");
    const [streetNumber, setStreetNumber] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [penaltyPoints, setPenaltyPoints] = useState(-1);
    const [loyaltyPoints, setLoyaltyPoints] = useState(-1);
    const [categoryOfUser, setCategoryOfUser] = useState("");
    const [canUpdate, setCanUpdate] = useState(false);
    const [existCurrentDeleteReq, setExistCurrentDeleteReq] = useState(true);


    useEffect(() => {
        axios
            .get("http://localhost:8080/clients/getByEmail/" + localStorage.getItem("userEmail"), {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                setFirstName(res.data.firstName);
                setLastName(res.data.lastName);
                setEmail(res.data.email);
                setStreetName(res.data.residentalAddress.streetName);
                setStreetNumber(res.data.residentalAddress.streetNumber);
                setCity(res.data.residentalAddress.city);
                setCountry(res.data.residentalAddress.country);
                setPhoneNumber(res.data.phoneNumber);
                setPenaltyPoints(res.data.penaltyPoints);
                setLoyaltyPoints(res.data.loyaltyPoints);
                setCategoryOfUser(res.data.statuseOfUser);
                axios
                    .get("http://localhost:8080/delete_account_requests/checkIfExistCurrentRequest/" + localStorage.getItem("userEmail"), {
                        headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                    }
                    )
                    .then((result) => {
                        setExistCurrentDeleteReq(result.data);
                    });
            });
    }, []);

    const updateUser = () => {
        axios
            .put("http://localhost:8080/clients/update", {
                firstName: firstName,
                lastName: lastName,
                email: email,
                address: {
                    streetName: streetName,
                    streetNumber: streetNumber,
                    city: city,
                    country: country
                },
                phoneNumber: phoneNumber
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly updated info!");
                window.location.href = "http://localhost:3000/client/profile";
            });
    }

    return (
        <div>
            <br />
            <br />
            <TextField disabled="true" label="Email" value={email} onChange={(e) => setEmail(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="First Name" disabled={!canUpdate} value={firstName} onChange={(e) => setFirstName(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Last Name" disabled={!canUpdate} value={lastName} onChange={(e) => setLastName(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Street Name" disabled={!canUpdate} value={streetName} onChange={(e) => setStreetName(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Street Number" disabled={!canUpdate} value={streetNumber} onChange={(e) => setStreetNumber(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="City" disabled={!canUpdate} value={city} onChange={(e) => setCity(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Country" disabled={!canUpdate} value={country} onChange={(e) => setCountry(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Phone Number" disabled={!canUpdate} value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Penalty Points" disabled="true" value={penaltyPoints} onChange={(e) => setPenaltyPoints(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Loyalty Points" disabled="true" value={loyaltyPoints} onChange={(e) => setLoyaltyPoints(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Category Of User" disabled="true" value={categoryOfUser} onChange={(e) => setCategoryOfUser(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <div hidden={canUpdate}>
                <Button onClick={() => setCanUpdate(true)} variant="contained" color="primary">Update</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                {!existCurrentDeleteReq && <Link to="/client/deleteRequest/create"><Button variant="contained" color="primary">Delete Account</Button></Link>}
            </div>
            <div hidden={!canUpdate}>
                <Button onClick={() => updateUser()} variant="contained" color="primary">Save</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <Link to="/client/homePage"><Button variant="contained" color="primary">Cancel</Button></Link>
            </div>
        </div>
    );
};

export default UserProfile;
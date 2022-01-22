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


const ChangePasswordAfterFirstLoginAdmin = () => {

    const [newPasswordField, setNewPasswordField] = useState("");
    const [confirmNewPasswordField, setConfirmNewPasswordField] = useState("");

    useEffect(() => {
        var splitID = []
        splitID = window.location.href.split("/");
        const token = splitID[4];
        alert(token);
    }, []);

    const changePassword = () => {
        axios
            .put("http://localhost:8080/system_admins/changePassword/"+localStorage.getItem("userEmail"), {
                newPassword : newPasswordField,
                confirmNewPassword : confirmNewPasswordField
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
            <TextField label="Password" type="password" value={newPasswordField} onChange={(e) => setNewPasswordField(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField label="Confirmation Password" type="password" value={confirmNewPasswordField} onChange={(e) => setConfirmNewPasswordField(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <Button onClick={() => changePassword()} variant="contained" color="primary">Update</Button>
            
        </div>
    );
};

export default ChangePasswordAfterFirstLoginAdmin;
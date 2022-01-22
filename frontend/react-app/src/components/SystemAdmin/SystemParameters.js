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


const SystemParameters = () => {
    const [id,setId] = useState(-1);
	
	
	const [bookingFee,setBookingFee] = useState(-1);
	const [canUpdate,setCanUpdate] = useState(false);
	
	const [incomeFromReservations,setIncomeFromReservations] = useState(-1);
	
	
	const [pointsForClients,setPointsForClients] = useState(-1);
	
	
	const [pointsForProviders,setPointsForProviders] = useState(-1);
	
	
	const [thresholdForSilver,setThresholdForSilver] = useState(-1);
	
	
	const [thresholdForGold,setThresholdForGold] = useState(-1);
	
	
	const [discountForRegular,setDiscountForRegular] = useState(-1);
	
	
	const [discountForSilver,setDiscountForSilver] = useState(-1);
	
	
	const [discountForGold,setDiscountForGold] = useState(-1);
    

    useEffect(() => {
        axios
            .get("http://localhost:8080/system_parameters/get", {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                setId(res.data.id);
	            setBookingFee(res.data.bookingFee);
                setIncomeFromReservations(res.data.incomeFromReservations);
                setPointsForClients(res.data.pointsForClients);
                setPointsForProviders(res.data.pointsForProviders);
                setThresholdForSilver(res.data.thresholdForSilver); 
                setThresholdForGold(res.data.thresholdForGold);
                setDiscountForRegular(res.data.discountForRegular);
                setDiscountForSilver(res.data.discountForSilver);
                setDiscountForGold(res.data.discountForGold);
            });
        
    }, []);

    const updateSystemParameters = () => {
        axios
            .put("http://localhost:8080/system_parameters/update", {
                id:id,
                bookingFee:bookingFee,
                incomeFromReservations:incomeFromReservations,
                pointsForClients:pointsForClients,
                pointsForProviders:pointsForProviders,
                thresholdForSilver:thresholdForSilver, 
                thresholdForGold:thresholdForGold,
                discountForRegular:discountForRegular,
                discountForSilver:discountForSilver,
                discountForGold:discountForGold
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
            <TextField label="ID" value={id} disabled={true} onChange={(e) => setId(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Booking Fee" value={bookingFee} onChange={(e) => setBookingFee(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Income From Reservations" value={incomeFromReservations} onChange={(e) => setIncomeFromReservations(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Points For Clients" value={pointsForClients} onChange={(e) => setPointsForClients(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Points For Providers" value={pointsForProviders} onChange={(e) => setPointsForProviders(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Threshold For Silver" value={thresholdForSilver} onChange={(e) => setThresholdForSilver(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Threshold For Gold" value={thresholdForGold} onChange={(e) => setThresholdForGold(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Discount For Regular" value={discountForRegular} onChange={(e) => setDiscountForRegular(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate}  label="Discount For Silver" value={discountForSilver} onChange={(e) => setDiscountForSilver(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField disabled={!canUpdate} label="Discount For Gold" value={discountForGold} onChange={(e) => setDiscountForGold(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <div hidden={canUpdate}>
                <Button onClick={() => setCanUpdate(true)} variant="contained" color="primary">Update</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div hidden={!canUpdate}>
                <Button onClick={() => updateSystemParameters()} variant="contained" color="primary">Save</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <Link to="/client/homePage"><Button variant="contained" color="primary">Cancel</Button></Link>
            </div>
        </div>
    );
};

export default SystemParameters;
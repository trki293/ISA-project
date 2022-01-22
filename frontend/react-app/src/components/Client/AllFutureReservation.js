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


const AllFutureReservation = () => {

    const [rows, setRows] = useState([]);
    const [subscriptionList, setSubscriptionList] = useState([]);

    const [copyRows, setCopyRows] = useState({});


    const [showForm, setShowForm] = useState(true);
    const [submited, setSubmited] = useState(false);


    const [beginDateAsc, setBeginDateAsc] = useState("");
    const [durationAsc, setDurationAsc] = useState(false);
    const [priceAsc, setPriceAsc] = useState(false);

    useEffect(() => {
        axios
            .get("http://localhost:8080/reservations/getFutureReservations/" + localStorage.getItem("userEmail"), {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                setRows(res.data);
                setCopyRows(res.data);
            });
    }, []);

    const TableHeader = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    BEGIN DATE
                </TableCell>
                <TableCell>
                    END DATE
                </TableCell>
                <TableCell>
                    TITLE
                </TableCell>
                <TableCell>
                    PRICE
                </TableCell>
                <TableCell>
                    STATUS OF RESERVATION
                </TableCell>
                <TableCell>
                    TYPE OF RESERVATION
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const cancelReservation = (reservationId,type) =>{
        let reservationType = ""
        if (type==="BOAT_RESERVATION"){
            reservationType="boat";
        }
        if (type==="COTTAGE_RESERVATION"){
            reservationType="cottage";
        }
        if (type==="INSTRUCTIONS_RESERVATION"){
            reservationType="instructions";
        }
        axios
            .put("http://localhost:8080/"+reservationType+"_reservations/cancel_"+reservationType+"_reservation/"+ reservationId, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/client/boatsForClient";
            });
    }

    const TableContent = (
        <TableBody>
            {rows.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.beginDate}</TableCell>
                    <TableCell>{row.endDate}</TableCell>
                    <TableCell>{row.title}</TableCell>
                    <TableCell>{row.price}</TableCell>
                    <TableCell>{row.statusOfReservation}</TableCell>
                    <TableCell>{row.typeOfReservation}</TableCell>
                    {(row.possibleToCancel) ? <TableCell><Button onClick={()=>cancelReservation(row.id, row.typeOfReservation)}>Cancel Reservation</Button></TableCell> : ""}
                </TableRow>
            ))}
        </TableBody>
    );

    return (
        <div>
            <br />
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeader}
                            {TableContent}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
        </div>
    );
};

export default AllFutureReservation;
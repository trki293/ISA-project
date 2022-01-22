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


const AllHistoryBoatReservation = () => {

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
            .get("http://localhost:8080/boat_reservations/getHistoryOfReservation/" + localStorage.getItem("userEmail"), {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                setRows(res.data);
                setCopyRows(res.data);
            });
    }, []);

    const sortList = (field) => {
        alert(field);
        if (field === 'begin_date') {

            setBeginDateAsc(!beginDateAsc);

            let ascBeginDate = beginDateAsc === true ? "asc" : "desc";
            alert(beginDateAsc);
            axios
                .post("http://localhost:8080/boat_reservations/history/sort/begin_date/" + ascBeginDate, rows, {
                    headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                }
                )
                .then((res) => {
                    setRows(res.data);
                    setCopyRows(res.data);
                });
        }
        if (field === 'duration') {

            setDurationAsc(!durationAsc);

            let ascDuration = durationAsc === true ? "asc" : "desc";
            alert(durationAsc);
            axios
                .post("http://localhost:8080/boat_reservations/history/sort/duration/" + ascDuration, rows, {
                    headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                }
                )
                .then((res) => {
                    setRows(res.data);
                    setCopyRows(res.data);
                });
        }
        if (field === 'price') {

            setPriceAsc(!priceAsc);
            let ascPrice = priceAsc === true ? "asc" : "desc";

            axios
                .post("http://localhost:8080/boat_reservations/history/sort/price/" + ascPrice, rows, {
                    headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                }
                )
                .then((res) => {
                    setRows(res.data);
                    setCopyRows(res.data);
                });
        }
    }




    const TableHeader = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    <Button onClick={() => sortList('begin_date')}>BEGIN DATE</Button>
                </TableCell>
                <TableCell>
                    <Button onClick={() => sortList('duration')}>DURATION (HOUR)</Button>
                </TableCell>
                <TableCell>
                    COTTAGE TITLE
                </TableCell>
                <TableCell>
                    <Button onClick={() => sortList('price')}>PRICE</Button>
                </TableCell>
                <TableCell>
                    STATUS OF RESERVATION
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContent = (
        <TableBody>
            {rows.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.beginDate}</TableCell>
                    <TableCell>{row.duration}</TableCell>
                    <TableCell>{row.boat.title}</TableCell>
                    <TableCell>{row.price}</TableCell>
                    <TableCell>{row.statusOfReservation}</TableCell>
                    {(!row.userReviewed) ? <TableCell><Link to={`/client/review/boat/${row.id}`}><Button>Review</Button></Link></TableCell> : ""}
                </TableRow>
            ))}
        </TableBody>
    );

    return (
        <div>
            <br />
            <br />
            <Link to={"/client/boatForComplaint"}><Button>Create Boat Complaint</Button></Link>
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

export default AllHistoryBoatReservation;
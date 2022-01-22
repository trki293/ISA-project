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


const AllRegistrationRequests = () => {

    const [rows, setRows] = useState([]);

    const [copyRows, setCopyRows] = useState({});

    const [citySearch, setCitySearch,] = useState("");
    const [countrySearch, setCountrySearch] = useState("");

    const [minPricePerHourSearch, setMinPricePerHourSearch] = useState(-1);
    const [maxPricePerHourSearch, setMaxPricePerHourSearch] = useState(-1);


    const [minAverageGradeSearch, setMinAverageGradeSearch] = useState(-1);
    const [maxAverageGradeSearch, setMaxAverageGradeSearch] = useState(-1);

    const [beginDateSearch, setBeginDateSearch] = useState("");
    const [endDateSearch, setEndDateSearch] = useState("");

    const [showForm, setShowForm] = useState(true);
    const [submited, setSubmited] = useState(false);


    const [titleAsc, setTitleAsc] = useState(false);
    const [cityAsc, setCityAsc] = useState(false);
    const [countryAsc, setCountryAsc] = useState(false);
    const [averageGradeAsc, setAverageGradeAsc] = useState(false);
    const [priceAsc, setPriceAsc] = useState(false);
    const [cottageId, setCottageId] = useState(-1);
    const [beginDate, setBeginDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [numberOfPerson, setNumberOfPerson] = useState(1);

    useEffect(() => {
        axios
            .get("http://localhost:8080/registration_requests/getAllNewlyReceivedRequests", {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                setRows(res.data);
            });
    }, []);


    const TableHeader = (
        <TableHead>
            <TableRow>
                <TableCell  >
                    ID
                </TableCell>
                <TableCell>
                    FULL NAME
                </TableCell>
                <TableCell>
                    EMAIL
                </TableCell>
                <TableCell>
                    PHONE NUMBER
                </TableCell>
                <TableCell>
                    STATUS OF REQUEST
                </TableCell>
                <TableCell>
                    TYPE OF USER
                </TableCell>
                <TableCell>
                    REASON
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const approveRequest = (id) =>{
        axios
        .put("http://localhost:8080/registration_requests/changeStatus/"+localStorage.getItem("userEmail"), {
            requestId: id,
            stateOfRequest: 'APPROVED'
        },  {
            headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
        }
        )
        .then((res) => {
            alert("Succesfuly approved request!");
            window.location.href="http://localhost:3000/systemAdmin/homePage";
        });
    }


    const TableContent = (
        <TableBody>
            {rows.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.firstName} {row.lastName}</TableCell>
                    <TableCell>{row.email}</TableCell>
                    <TableCell>{row.phoneNumber}</TableCell>
                    <TableCell>{row.stateOfRequest}</TableCell>
                    <TableCell>{row.typeOfUser}</TableCell>
                    <TableCell>{row.registrationReason}</TableCell>
                    <TableCell><Button onClick={()=>approveRequest(row.id)}>APPROVE</Button></TableCell>
                    <TableCell><Link to={`/adminSystem/registrationRequest/reject/${row.id}`}><Button>REJECT</Button></Link></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    return (
        <div>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeader}
                            {TableContent}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br />
            <br />
        </div>
    );
};

export default AllRegistrationRequests;
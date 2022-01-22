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


const AllComplaints = () => {

    const [rowsBoatComplaint, setRowsBoatComplaint] = useState([]);
    const [rowsCottageComplaint, setRowsCottageComplaint] = useState([]);
    const [rowsInstructionsComplaint, setRowsInstructionsComplaint] = useState([]);

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
            .get("http://localhost:8080/boat_complaints/getBoatComplaints", {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((resBoat) => {
                setRowsBoatComplaint(resBoat.data);
                axios
                    .get("http://localhost:8080/cottage_complaints/getCottageComplaints", {
                        headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                    }
                    )
                    .then((resCottage) => {
                        setRowsCottageComplaint(resCottage.data);
                        axios
                            .get("http://localhost:8080/instructions_complaints/getInstructionsComplaints", {
                                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                            }
                            )
                            .then((resInstructions) => {
                                setRowsInstructionsComplaint(resInstructions.data);
                            });
                    });
            });
    }, []);


    const TableHeaderBoat = (
        <TableHead>
            <TableRow>
                <TableCell  >
                    ID
                </TableCell>
                <TableCell>
                    CLIENT FIRST NAME
                </TableCell>
                <TableCell>
                    CLIENT LAST NAME
                </TableCell>
                <TableCell>
                    BOAT TITLE
                </TableCell>
                <TableCell>
                    BOAT OWNER FIRST NAME
                </TableCell>
                <TableCell>
                    BOAT OWNER LAST NAME
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableHeaderCottage = (
        <TableHead>
            <TableRow>
                <TableCell  >
                    ID
                </TableCell>
                <TableCell>
                    CLIENT FIRST NAME
                </TableCell>
                <TableCell>
                    CLIENT LAST NAME
                </TableCell>
                <TableCell>
                    COTTAGE TITLE
                </TableCell>
                <TableCell>
                    COTTAGE OWNER FIRST NAME
                </TableCell>
                <TableCell>
                    COTTAGE OWNER LAST NAME
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableHeaderInstructions = (
        <TableHead>
            <TableRow>
                <TableCell  >
                    ID
                </TableCell>
                <TableCell>
                    CLIENT FIRST NAME
                </TableCell>
                <TableCell>
                    CLIENT LAST NAME
                </TableCell>
                <TableCell>
                    INSTRUCTOR FIRST NAME
                </TableCell>
                <TableCell>
                    INSTRUCTOR LAST NAME
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const approveRequest = (id) => {
        axios
            .put("http://localhost:8080/registration_requests/changeStatus/" + localStorage.getItem("userEmail"), {
                requestId: id,
                stateOfRequest: 'APPROVED'
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly approved request!");
                window.location.href = "http://localhost:3000/systemAdmin/homePage";
            });
    }


    const TableContentBoat = (
        <TableBody>
            {rowsBoatComplaint.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.clientFirstName}</TableCell>
                    <TableCell>{row.clientLastName}</TableCell>
                    <TableCell>{row.boatTitle}</TableCell>
                    <TableCell>{row.boatOwnerFirstName}</TableCell>
                    <TableCell>{row.boatOwnerLastName}</TableCell>
                    <TableCell><Link to={`/adminSystem/boatComplaint/review/${row.id}`}><Button>CREATE REVIEW OF REPORT</Button></Link></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableContentCottage = (
        <TableBody>
            {rowsCottageComplaint.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.clientFirstName}</TableCell>
                    <TableCell>{row.clientLastName}</TableCell>
                    <TableCell>{row.cottageTitle}</TableCell>
                    <TableCell>{row.cottageOwnerFirstName}</TableCell>
                    <TableCell>{row.cottageOwnerLastName}</TableCell>
                    <TableCell><Link to={`/adminSystem/cottageComplaint/review/${row.id}`}><Button>CREATE REVIEW OF COMPLAINT</Button></Link></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableContentInstructions = (
        <TableBody>
            {rowsInstructionsComplaint.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.clientFirstName}</TableCell>
                    <TableCell>{row.clientLastName}</TableCell>
                    <TableCell>{row.instructorFirstName}</TableCell>
                    <TableCell>{row.instructorLastName}</TableCell>
                    <TableCell><Link to={`/adminSystem/instructionsComplaint/review/${row.id}`}><Button>CREATE REVIEW OF COMPLAINT</Button></Link></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    return (
        <div>
            <br /><br />
            <h4>COTTAGE OWNER COMPLAINT</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderCottage}
                            {TableContentCottage}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br /><br />
            <h4>BOAT OWNER COMPLAINT</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderBoat}
                            {TableContentBoat}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br /><br />
            <h4>INSTRUCTOR COMPLAINT</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderInstructions}
                            {TableContentInstructions}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
        </div>
    );
};

export default AllComplaints;
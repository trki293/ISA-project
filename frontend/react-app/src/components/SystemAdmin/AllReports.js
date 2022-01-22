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


const AllReports = () => {

    const [rowsBoatOwnerReport, setRowsBoatOwnerReport] = useState([]);
    const [rowsCottageOwnerReport, setRowsCottageOwnerReport] = useState([]);
    const [rowsInstructorReport, setRowsInstructorReport] = useState([]);

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
            .get("http://localhost:8080/boat_owner_reports/getOnlySanctionReportForSystemAdmin", {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((resBoatOwner) => {
                setRowsBoatOwnerReport(resBoatOwner.data);
                axios
                    .get("http://localhost:8080/cottage_owner_reports/getOnlySanctionReportForSystemAdmin", {
                        headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                    }
                    )
                    .then((resCottageOwner) => {
                        setRowsCottageOwnerReport(resCottageOwner.data);
                        axios
                            .get("http://localhost:8080/instructor_reports/getOnlySanctionReportForSystemAdmin", {
                                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                            }
                            )
                            .then((resInstructor) => {
                                setRowsInstructorReport(resInstructor.data);
                            });
                    });
            });
    }, []);


    const TableHeader = (
        <TableHead>
            <TableRow>
                <TableCell  >
                    ID
                </TableCell>
                <TableCell>
                    CONTENT
                </TableCell>
                <TableCell>
                    TYPE OF REPORT
                </TableCell>
                <TableCell>
                    STATUS OF REPORT
                </TableCell>
                <TableCell>
                    REPORTING TYPE
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


    const TableContentBoatOwner = (
        <TableBody>
            {rowsBoatOwnerReport.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.content}</TableCell>
                    <TableCell>{row.typeOfReport}</TableCell>
                    <TableCell>{row.statusOfReport}</TableCell>
                    <TableCell>{row.reportingType}</TableCell>
                    <TableCell><Link to={`/adminSystem/boatOwnerReport/review/${row.id}`}><Button>CREATE REVIEW OF REPORT</Button></Link></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableContentCottageOwner = (
        <TableBody>
            {rowsCottageOwnerReport.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.content}</TableCell>
                    <TableCell>{row.typeOfReport}</TableCell>
                    <TableCell>{row.statusOfReport}</TableCell>
                    <TableCell>{row.reportingType}</TableCell>
                    <TableCell><Link to={`/adminSystem/cottageOwnerReport/review/${row.id}`}><Button>CREATE REVIEW OF REPORT</Button></Link></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableContentInstructor = (
        <TableBody>
            {rowsInstructorReport.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.content}</TableCell>
                    <TableCell>{row.typeOfReport}</TableCell>
                    <TableCell>{row.statusOfReport}</TableCell>
                    <TableCell>{row.reportingType}</TableCell>
                    <TableCell><Link to={`/adminSystem/instructorReport/review/${row.id}`}><Button>CREATE REVIEW OF REPORT</Button></Link></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    return (
        <div>
            <br /><br />
            <h4>COTTAGE OWNER REPORT</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeader}
                            {TableContentCottageOwner}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br /><br />
            <h4>BOAT OWNER REPORT</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeader}
                            {TableContentBoatOwner}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br /><br />
            <h4>INSTRUCTOR REPORT</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeader}
                            {TableContentInstructor}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
        </div>
    );
};

export default AllReports;
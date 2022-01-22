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


const GetCottagesForClientComplaint = () => {

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
        var splitID = []
        splitID = window.location.href.split("/");
        setCottageId(splitID[6]);
        axios
            .get("http://localhost:8080/cottage_reservations/getCottagesForClientComplaint/" + localStorage.getItem("userEmail") , {
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
                <TableCell  >
                    COTTAGE ID
                </TableCell>
                <TableCell>
                    COTTAGE TITLE
                </TableCell>
                <TableCell>
                    OWNER FIRST NAME
                </TableCell>
                <TableCell>
                    OWNER LAST NAME
                </TableCell>
            </TableRow>
        </TableHead>
    );


    const TableContent = (
        <TableBody>
            {rows.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.cottageId}</TableCell>
                    <TableCell>{row.cottageTitle}</TableCell>
                    <TableCell>{row.cottageOwnerFirstName}</TableCell>
                    <TableCell>{row.cottageOwnerLastName}</TableCell>
                    <TableCell><Link to={`/client/complaint/cottage/${row.cottageId}`}><Button>CHOOSE</Button></Link></TableCell>
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

export default GetCottagesForClientComplaint;
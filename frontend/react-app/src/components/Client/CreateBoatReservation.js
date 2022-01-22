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
import DateTimePicker from '@mui/lab/DateTimePicker';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';

const CreateBoatReservation = () => {

    const [rows, setRows] = useState([]);

    const [additionaServiceList, setAdditionaServiceList] = useState([]);

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
    const [boatId, setBoatId] = useState(-1);
    const [beginDate, setBeginDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [numberOfPerson, setNumberOfPerson] = useState(1);

    useEffect(() => {
        var splitID = []
        splitID = window.location.href.split("/");
        alert(splitID[5]);
        setBoatId(splitID[5]);
        setBeginDate(splitID[6]);
        setEndDate(splitID[7]);

        axios
            .get("http://localhost:8080/boats/getAdditionalServicesForBoat/" + splitID[5], {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                setRows(res.data);
                setAdditionaServiceList([]);
            });
    }, []);

    const TableHeader = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ADITIONAL SERVICE NAME
                </TableCell>
                <TableCell  >
                    ACTION
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const removeFromList = (element) => {
        alert(element);
        setAdditionaServiceList(additionaServiceList.filter(name => name !== element));

    }

    const addToList = (element) => {
        console.log(element);
        let list = additionaServiceList;
        setAdditionaServiceList(list.concat(element));
        console.log(additionaServiceList);

    }

    const createBoatReservation = () => {
        axios
            .post("http://localhost:8080/boat_reservations/create", {
                boatId: boatId,
                timeOfBeginingReservation: beginDate,
                timeOfEndingReservation: endDate,
	            numberOfPerson: numberOfPerson,
	            namesOfAdditionalsServices: additionaServiceList,
	            clientEmail: localStorage.getItem("userEmail")
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Sucessfuly created reservation!");
                window.location.href = "http://localhost:3000/client/homePage";
});
        
    }

const TableContent = (
    <TableBody>
        {rows.map((element) => (
            <TableRow key={element} >
                <TableCell>{element}</TableCell>
                <TableCell>{additionaServiceList.includes({ element }.element) ? <Button onClick={() => removeFromList({ element }.element)}>Remove</Button> : <Button onClick={() => addToList({ element }.element)}>Add</Button>}</TableCell>
            </TableRow>
        ))}
    </TableBody>
);

return (
    <div>   
        <br />
        <br />
        <Button onClick={() => createBoatReservation()} variant="contained" color="primary">Create Reservation</Button>
        <br />
        <br />
        <TextField label="ID Of Boat" value={boatId} id="outlined-basic" variant="outlined" size="small" />
        <br />
        <br />
        <LocalizationProvider dateAdapter={AdapterDateFns}>
            <DateTimePicker
                label="Begin Date"
                value={beginDate}
                renderInput={(params) => <TextField {...params} />}
            />&nbsp;&nbsp;&nbsp;&nbsp;
            <DateTimePicker
                label="End Date"
                value={endDate}
                renderInput={(params) => <TextField {...params} />}
            />

        </LocalizationProvider>
        <br /><br />
        <TextField label="Number Of Person" value={numberOfPerson} onChange={e => setNumberOfPerson(e.target.value)} id="outlined-basic" variant="outlined" size="small" />
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

export default CreateBoatReservation;
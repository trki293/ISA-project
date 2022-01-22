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
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';

const CreateInstructionsReview = () => {

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


    const [grade, setGrade] = useState(5);
    const [content, setContent] = useState("");
    const [instructionsReservationId, setInstructionsReservationId] = useState(-1);

    useEffect(() => {
        var splitID = []
        splitID = window.location.href.split("/");
        setInstructionsReservationId(splitID[6]);
    }, []);

    const createInstructionsReview = () => {
        axios
            .post("http://localhost:8080/instructions_reviews/create", {
                instructionsReservationId: instructionsReservationId,
                content: content,
                rating: grade,
                clientEmail: localStorage.getItem("userEmail")
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Sucessfuly created review!");
                window.location.href = "http://localhost:3000/client/homePage";
            });
    }

    return (
        <div>
            <br />
            <br />
            <TextField label="ID of Instructions Reservation" value={instructionsReservationId} id="outlined-basic" variant="outlined" size="small" />
            <br />
            <br />
            <TextField
                id="outlined-multiline-flexible"
                label="Multiline"
                multiline
                maxRows={8}
                value={content}
                onChange={(e) => setContent(e.target.value)}
            />
            <br />
            <br />
            <div style={{ marginLeft: '640px' }}>
            <RadioGroup
                aria-labelledby="demo-radio-buttons-group-label"
                row
                value={grade}
                onChange={(e) => setGrade(e.target.value)}
                name="radio-buttons-group"
            >
                <FormControlLabel value="1" control={<Radio />} label="1" />
                <FormControlLabel value="2" control={<Radio />} label="2" />
                <FormControlLabel value="3" control={<Radio />} label="3" />
                <FormControlLabel value="4" control={<Radio />} label="4" />
                <FormControlLabel value="5" control={<Radio />} label="5" />
            </RadioGroup>
            </div>
            <br />
            <br />
            <Button onClick={() => createInstructionsReview()} variant="contained" color="primary">Create Review</Button>
            <br />
            <br />

        </div>
    );
};

export default CreateInstructionsReview;
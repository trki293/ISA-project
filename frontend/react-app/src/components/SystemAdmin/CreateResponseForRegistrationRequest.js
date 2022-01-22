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

const CreateResponseForRegistrationRequest = () => {

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
    const [requestId, setRequestId] = useState(-1);

    useEffect(() => {
        var splitID = []
        splitID = window.location.href.split("/");
        setRequestId(splitID[6]);
    }, []);

    const createCottageResponse = () => {
        axios
            .put("http://localhost:8080/registration_requests/changeStatus/"+localStorage.getItem('userEmail'), {
                requestId: requestId,
                stateOfRequest: 'REJECTED',
                reasoneForRejectionOfRequest: content,
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Sucessfuly created response for request!");
                window.location.href = "http://localhost:3000/client/homePage";
            });
    }

    return (
        <div>
            <br />
            <br />
            <TextField label="ID of Registration Request" value={requestId} id="outlined-basic" variant="outlined" size="small" />
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
            <Button onClick={() => createCottageResponse()} variant="contained" color="primary">Create Response</Button>
            <br />
            <br />

        </div>
    );
};

export default CreateResponseForRegistrationRequest;
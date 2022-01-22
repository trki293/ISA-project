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


const AllBoatsForClient = () => {

    const [rows, setRows] = useState([]);
    const [subscriptionList, setSubscriptionList] = useState([]);

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

    useEffect(() => {
        axios
            .post("http://localhost:8080/boats/getAllBoatsForClient/"+localStorage.getItem("userEmail"), {
                "beginDate": "",
                "endDate": "",
                "city": "",
                "country": "",
                "minAverageGrade": -1,
                "maxAverageGrade": -1,
                "minPrice": -1,
                "maxPrice": -1
            }, {
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
        if (field === 'title') {

            setTitleAsc(!titleAsc);

            let ascTitle = titleAsc === true ? "asc" : "desc";
            alert(titleAsc);
            axios
                .post("http://localhost:8080/boats/sort/title/" + ascTitle, rows, {
                    headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                }
                )
                .then((res) => {
                    setRows(res.data);
                    setCopyRows(res.data);
                });
        }
        if (field === 'city') {

            setCityAsc(!cityAsc);
            let ascCity = cityAsc === true ? "asc" : "desc";

            axios
                .post("http://localhost:8080/boats/sort/city/" + ascCity, rows, {
                    headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                }
                )
                .then((res) => {
                    setRows(res.data);
                    setCopyRows(res.data);
                });
        }
        if (field === 'country') {

            setCountryAsc(!countryAsc);
            let ascCountry = countryAsc === true ? "asc" : "desc";
            axios
                .post("http://localhost:8080/boats/sort/country/" + ascCountry, rows, {
                    headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                }
                )
                .then((res) => {
                    setRows(res.data);
                    setCopyRows(res.data);
                });
        }
        if (field === 'average_grade') {

            setAverageGradeAsc(!averageGradeAsc);
            let ascAverageGrade = averageGradeAsc === true ? "asc" : "desc";
            axios
                .post("http://localhost:8080/boats/sort/average_grade/" + ascAverageGrade, rows, {
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
                .post("http://localhost:8080/boats/sort/price/" + ascPrice, rows, {
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
                    <Button onClick={() => sortList('title')}>Title</Button>
                </TableCell>
                <TableCell>
                    STREET NAME
                </TableCell>
                <TableCell>
                    STREET NUMBER
                </TableCell>
                <TableCell>
                    <Button onClick={() => sortList('city')}>City</Button>
                </TableCell>
                <TableCell>
                    <Button onClick={() => sortList('country')}>Country</Button>
                </TableCell>
                <TableCell>
                    NUMBER OF ROOMS
                </TableCell>
                <TableCell>
                    NUMBER OF BEDS
                </TableCell>
                <TableCell>
                    <Button onClick={() => sortList('average_grade')}>Average Grade</Button>
                </TableCell>
                <TableCell >
                    <Button onClick={() => sortList('price')}>Price per Hour</Button>
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const subscribeOrUnsubscribe = (boatID) => {
        console.log(boatID);
        axios
            .put("http://localhost:8080/boats/subscribe/" + localStorage.getItem("userEmail") + "/boat/" + boatID, {},  {
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
                    <TableCell>{row.title}</TableCell>
                    <TableCell>{row.address.streetName}</TableCell>
                    <TableCell>{row.address.streetNumber}</TableCell>
                    <TableCell>{row.address.city}</TableCell>
                    <TableCell>{row.address.country}</TableCell>
                    <TableCell>{row.numberOfRooms}</TableCell>
                    <TableCell>{row.numberOfBeds}</TableCell>
                    <TableCell>{row.averageGrade}</TableCell>
                    <TableCell>{row.pricePerNight}</TableCell>
                    <TableCell><Link to={`/client/quickReservations/getForBoat/${row.id}`}><Button>Get Quick Booking Reservations</Button></Link></TableCell>
                    {(submited && beginDateSearch !== "" && endDateSearch !== "") ? <TableCell><Link to={`/client/reservation/boat/${row.id}/${beginDateSearch}/${endDateSearch}`}><Button>Make reservation</Button></Link></TableCell> : ""}
                    {(row.userSubscribe) && <TableCell><Button onClick={()=>subscribeOrUnsubscribe(row.id)}>Unsubscribe</Button></TableCell>}
                    {(!row.userSubscribe) && <TableCell><Button onClick={()=>subscribeOrUnsubscribe(row.id)}>Subscribe</Button></TableCell>}  
                </TableRow>
            ))}
        </TableBody>
    );

    

    const handleSubmit = (event) => {
        /*if ((beginDateSearch==="" && endDateSearch!=="") || (beginDateSearch!=="" && endDateSearch==="")) {
            return;
        }
        if (beginDateSearch!=="" && !isMatch(beginDateSearch, 'dd/MM/yyyy HH:mm')){
            return;
        } 
        if (endDateSearch!=="" && !isMatch(endDateSearch, 'dd/MM/yyyy HH:mm')){
            return;
        }*/
        alert("Usao - prije axios");
        event.preventDefault();
        axios
            .post("http://localhost:8080/boats/getAllBoatsForClient/"+localStorage.getItem("userEmail"), {
                "beginDate": beginDateSearch,
                "endDate": endDateSearch,
                "city": citySearch,
                "country": countrySearch,
                "minAverageGrade": minAverageGradeSearch,
                "maxAverageGrade": maxAverageGradeSearch,
                "minPrice": minPricePerHourSearch,
                "maxPrice": maxPricePerHourSearch
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Usao -axios then");
                setRows(res.data);
                setCopyRows(res.data);
                setSubmited(true);
            });
    }

    const showFormHandle = () => {
        let showFormState = showForm;
        console.log(showForm);
        setShowForm(!showForm);
        console.log(showForm);

    }

    const hideAndResetHandler = () => {
        console.log(showForm);
        setShowForm(!showForm);
        console.log(showForm);
        axios
            .post("http://localhost:8080/boats/getAllBoatsForClient/"+localStorage.getItem("userEmail"), {
                "beginDate": "",
                "endDate": "",
                "city": "",
                "country": "",
                "minAverageGrade": -1,
                "maxAverageGrade": -1,
                "minPrice": -1,
                "maxPrice": -1
            }, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                setRows(res.data);
                setCopyRows(res.data);

            });
        setMinPricePerHourSearch(-1);
        setMinAverageGradeSearch(-1);
        setBeginDateSearch("");
        setMaxPricePerHourSearch(-1);
        setMaxAverageGradeSearch(-1);
        setEndDateSearch("");
        setCitySearch("");
        setCountrySearch("");
        setSubmited(false);
    }



    return (
        <div>
            <br />
            <br />
            <form hidden={showForm} onSubmit={handleSubmit}>
                <label>Begin Date:&nbsp;&nbsp;
                    <input
                        type="datetime-local"
                        value={beginDateSearch}
                        onChange={(e) => setBeginDateSearch(e.target.value)}
                    />
                </label>&nbsp;&nbsp;&nbsp;&nbsp;
                <label>Begin Date:&nbsp;&nbsp;
                    <input
                        type="datetime-local"
                        value={endDateSearch}
                        onChange={(e) => setEndDateSearch(e.target.value)}
                    />
                </label><br /><br />
                <label>City:&nbsp;&nbsp;
                    <input
                        type="text"
                        value={citySearch}
                        onChange={(e) => setCitySearch(e.target.value)}
                    />
                </label><br /><br />
                <label>Country:&nbsp;&nbsp;
                    <input
                        type="text"
                        value={countrySearch}
                        onChange={(e) => setCountrySearch(e.target.value)}
                    />
                </label><br /><br />
                <label>Min Average Grade:&nbsp;&nbsp;
                    <input
                        type="text"
                        value={minAverageGradeSearch}
                        onChange={(e) => setMinAverageGradeSearch(e.target.value)}
                    />&nbsp;&nbsp;&nbsp;&nbsp;
                </label>
                <label>Max Average Grade:&nbsp;&nbsp;
                    <input
                        type="text"
                        value={maxAverageGradeSearch}
                        onChange={(e) => setMaxAverageGradeSearch(e.target.value)}
                    />
                </label><br /><br />
                <label>Min Price per Hour:&nbsp;&nbsp;
                    <input
                        type="text"
                        value={minPricePerHourSearch}
                        onChange={(e) => setMinPricePerHourSearch(e.target.value)}
                    />&nbsp;&nbsp;&nbsp;&nbsp;
                </label>
                <label>Max Price per Hour:&nbsp;&nbsp;
                    <input
                        type="text"
                        value={maxPricePerHourSearch}
                        onChange={(e) => setMaxPricePerHourSearch(e.target.value)}
                    />
                </label><br /><br />
                <input type="submit" />
                {!showForm ? <Button onClick={hideAndResetHandler}>Cancel</Button> : ""}
            </form>
            {showForm ? <Button onClick={showFormHandle}>Search</Button> : ""}
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

export default AllBoatsForClient;
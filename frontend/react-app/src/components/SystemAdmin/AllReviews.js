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


const AllReviews = () => {

    const [rowsBoatReview, setRowsBoatReview] = useState([]);
    const [rowsCottageReview, setRowsCottageReview] = useState([]);
    const [rowsInstructionsReview, setRowsInstructionsReview] = useState([]);

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
            .get("http://localhost:8080/boat_reviews/getOnlyBoatReviewsForSystemAdmin", {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((resBoat) => {
                setRowsBoatReview(resBoat.data);
                axios
                    .get("http://localhost:8080/cottage_reviews/getOnlyCottageReviewsForSystemAdmin", {
                        headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                    }
                    )
                    .then((resCottage) => {
                        setRowsCottageReview(resCottage.data);
                        axios
                            .get("http://localhost:8080/instructions_reviews/getInstructionsReviewsForSystemAdmin", {
                                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                            }
                            )
                            .then((resInstructions) => {
                                setRowsInstructionsReview(resInstructions.data);
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
                    GRADE
                </TableCell>
                <TableCell>
                    RESERVATION TITLE
                </TableCell>
                <TableCell>
                    STATUS OF REVIEW
                </TableCell>
                <TableCell>
                    TYPE OF REVIEW
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const publishBoatReview = (id) => {
        axios
            .put("http://localhost:8080/boat_reviews/publish/"+id+"/true" , {}, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly!");
                window.location.href = "http://localhost:3000/systemAdmin/homePage";
            });
    }

    const publishCottageReview = (id) => {
        axios
            .put("http://localhost:8080/cottage_reviews/publish/"+id+"/true" , {}, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly!");
                window.location.href = "http://localhost:3000/systemAdmin/homePage";
            });
    }

    const publishInstructionsReview = (id) => {
        axios
            .put("http://localhost:8080/instructions_reviews/publish/"+id+"/true" , {}, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly!");
                window.location.href = "http://localhost:3000/systemAdmin/homePage";
            });
    }

    const coverBoatReview = (id) => {
        axios
            .put("http://localhost:8080/boat_reviews/publish/"+id+"/false" , {}, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly!");
                window.location.href = "http://localhost:3000/systemAdmin/homePage";
            });
    }

    const coverCottageReview = (id) => {
        axios
            .put("http://localhost:8080/cottage_reviews/publish/"+id+"/false" , {}, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly!");
                window.location.href = "http://localhost:3000/systemAdmin/homePage";
            });
    }

    const coverInstructionsReview = (id) => {
        axios
            .put("http://localhost:8080/instructions_reviews/publish/"+id+"/false" , {}, {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                alert("Succesfuly!");
                window.location.href = "http://localhost:3000/systemAdmin/homePage";
            });
    }


    const TableContentBoat = (
        <TableBody>
            {rowsBoatReview.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.rating}</TableCell>
                    <TableCell>{row.reservationBeingEvaluated.title}</TableCell>
                    <TableCell>{row.statusOfReview}</TableCell>
                    <TableCell>{row.typeOfReview}</TableCell>
                    <TableCell><Button onClick={()=> publishBoatReview(row.id)}>PUBLISH</Button></TableCell>
                    <TableCell><Button onClick={()=> coverBoatReview(row.id)}>COVER</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableContentCottage = (
        <TableBody>
            {rowsCottageReview.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.rating}</TableCell>
                    <TableCell>{row.reservationBeingEvaluated.title}</TableCell>
                    <TableCell>{row.statusOfReview}</TableCell>
                    <TableCell>{row.typeOfReview}</TableCell>
                    <TableCell><Button onClick={()=> publishCottageReview(row.id)}>PUBLISH</Button></TableCell>
                    <TableCell><Button onClick={()=> coverCottageReview(row.id)}>COVER</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableContentInstructor = (
        <TableBody>
            {rowsInstructionsReview.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.rating}</TableCell>
                    <TableCell>{row.reservationBeingEvaluated.title}</TableCell>
                    <TableCell>{row.statusOfReview}</TableCell>
                    <TableCell>{row.typeOfReview}</TableCell>
                    <TableCell><Button onClick={()=> publishInstructionsReview(row.id)}>PUBLISH</Button></TableCell>
                    <TableCell><Button onClick={()=> coverInstructionsReview(row.id)}>COVER</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    return (
        <div>
            <br /><br />
            <h4>COTTAGE REVIEW</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeader}
                            {TableContentCottage}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br /><br />
            <h4>BOAT REVIEW</h4>
            <br /><br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                    <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                        <Table>
                            {TableHeader}
                            {TableContentBoat}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br /><br />
            <h4>INSTRUCTIONS REVIEW</h4>
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

export default AllReviews;
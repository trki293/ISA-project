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


const AllEntities = () => {

    const [rowsCottages, setRowsCottages] = useState([]);
    const [rowsBoats, setRowsBoats] = useState([]);
    const [rowsInstructions, setRowsInstructions] = useState([]);
    const [rowsCottageOwners, setRowsCottageOwners] = useState([]);
    const [rowsBoatOwners, setRowsBoatOwners] = useState([]);
    const [rowsInstructors, setRowsInstructors] = useState([]);
    const [rowsClients, setRowsClients] = useState([]);

    const [copyRows, setCopyRows] = useState({});


    const [showForm, setShowForm] = useState(true);
    const [submited, setSubmited] = useState(false);


    const [beginDateAsc, setBeginDateAsc] = useState("");
    const [durationAsc, setDurationAsc] = useState(false);
    const [priceAsc, setPriceAsc] = useState(false);

    useEffect(() => {
        axios
            .get("http://localhost:8080/boats/getAllBoats", {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((resBoats) => {
                setRowsBoats(resBoats.data);
                axios
                    .get("http://localhost:8080/boats/getAllBoatOwners", {
                        headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                    }
                    )
                    .then((resBoatOwners) => {
                        setRowsBoatOwners(resBoatOwners.data);
                        axios
                            .get("http://localhost:8080/cottages/getAllCottages", {
                                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                            }
                            )
                            .then((resCottages) => {
                                setRowsCottages(resCottages.data);
                                axios
                                    .get("http://localhost:8080/cottages/getAllCottageOwners", {
                                        headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                                    }
                                    )
                                    .then((resCottageOwners) => {
                                        setRowsCottageOwners(resCottageOwners.data);
                                        axios
                                            .get("http://localhost:8080/instructions/getAllInstructors", {
                                                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                                            }
                                            )
                                            .then((resInstructors) => {
                                                setRowsInstructors(resInstructors.data);
                                                axios
                                                    .get("http://localhost:8080/instructions/getAllInstructions", {
                                                        headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                                                    }
                                                    )
                                                    .then((resInstructions) => {
                                                        setRowsInstructions(resInstructions.data);
                                                        axios
                                                            .get("http://localhost:8080/clients/getAllClients", {
                                                                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                                                            }
                                                            )
                                                            .then((resClients) => {
                                                                setRowsClients(resClients.data);

                                                            });
                                                    });
                                            });
                                    });
                            });
                    });
            });
    }, []);

    const TableHeaderBoat = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    TITLE
                </TableCell>
                <TableCell>
                    AVERAGE GRADE
                </TableCell>
                <TableCell>
                    PRICE
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContentBoat = (
        <TableBody>
            {rowsBoats.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.title}</TableCell>
                    <TableCell>{row.averageGrade}</TableCell>
                    <TableCell>{row.pricePerHour}</TableCell>
                    <TableCell><Button onClick={()=>deleteBoat(row.id)}>Delete</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const deleteClient= (id) =>{
    
        axios
            .put("http://localhost:8080/clients/deleteClient/"+id, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/systemAdmin/homePage";
            });
    }

    const deleteBoat= (id) =>{
    
        axios
            .put("http://localhost:8080/boats/deleteBoat/"+id, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/systemAdmin/homePage";
            });
    }

    const deleteBoatOwner= (id) =>{
    
        axios
            .put("http://localhost:8080/boats/deleteBoatOwner/"+id, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/systemAdmin/homePage";
            });
    }

    const deleteCottage= (id) =>{
    
        axios
            .put("http://localhost:8080/cottages/deleteCottage/"+id, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/systemAdmin/homePage";
            });
    }

    const deleteCottageOwner= (id) =>{
    
        axios
            .put("http://localhost:8080/cottages/deleteCottageOwner/"+id, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/systemAdmin/homePage";
            });
    }

    const deleteInstructions= (id) =>{
    
        axios
            .put("http://localhost:8080/instructions/deleteInstructions/"+id, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/systemAdmin/homePage";
            });
    }

    const deleteInstructor= (id) =>{
    
        axios
            .put("http://localhost:8080/instructions/deleteInstructor/"+id, {},  {
                headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
            }
            )
            .then((res) => {
                window.location.href="http://localhost:3000/systemAdmin/homePage";
            });
    }

    const TableHeaderBoatOwner = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    EMAIL
                </TableCell>
                <TableCell>
                    FULL NAME
                </TableCell>
                <TableCell>
                    PHONE NUMBER
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContentBoatOwner = (
        <TableBody>
            {rowsBoatOwners.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.email}</TableCell>
                    <TableCell>{row.firstName} {row.lastName}</TableCell>
                    <TableCell>{row.phoneNumber}</TableCell>
                    <TableCell><Button onClick={()=>deleteBoatOwner(row.id)}>Delete</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableHeaderCottage = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    TITLE
                </TableCell>
                <TableCell>
                    AVERAGE GRADE
                </TableCell>
                <TableCell>
                    PRICE
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContentCottage = (
        <TableBody>
            {rowsCottages.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.title}</TableCell>
                    <TableCell>{row.averageGrade}</TableCell>
                    <TableCell>{row.pricePerNight}</TableCell>
                    <TableCell><Button onClick={()=>deleteCottage(row.id)}>Delete</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableHeaderCottageOwner = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    EMAIL
                </TableCell>
                <TableCell>
                    FULL NAME
                </TableCell>
                <TableCell>
                    PHONE NUMBER
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContentCottageOwner = (
        <TableBody>
            {rowsCottageOwners.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.email}</TableCell>
                    <TableCell>{row.firstName} {row.lastName}</TableCell>
                    <TableCell>{row.phoneNumber}</TableCell>
                    <TableCell><Button onClick={()=>deleteCottageOwner(row.id)}>Delete</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableHeaderInstructions = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    TITLE
                </TableCell>
                <TableCell>
                    AVERAGE GRADE
                </TableCell>
                <TableCell>
                    PRICE
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContentInstructions = (
        <TableBody>
            {rowsInstructions.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.title}</TableCell>
                    <TableCell>{row.averageGrade}</TableCell>
                    <TableCell>{row.pricePerHour}</TableCell>
                    <TableCell><Button onClick={()=>deleteInstructions(row.id)}>Delete</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableHeaderInstructor = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    EMAIL
                </TableCell>
                <TableCell>
                    FULL NAME
                </TableCell>
                <TableCell>
                    PHONE NUMBER
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContentInstructor = (
        <TableBody>
            {rowsInstructors.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.email}</TableCell>
                    <TableCell>{row.firstName} {row.lastName}</TableCell>
                    <TableCell>{row.phoneNumber}</TableCell>
                    <TableCell><Button onClick={()=>deleteInstructor(row.id)}>Delete</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    const TableHeaderClient = (
        <TableHead>
            <TableRow>
                <TableCell>
                    ID
                </TableCell>
                <TableCell  >
                    EMAIL
                </TableCell>
                <TableCell>
                    FULL NAME
                </TableCell>
                <TableCell>
                    PHONE NUMBER
                </TableCell>
            </TableRow>
        </TableHead>
    );

    const TableContentClient = (
        <TableBody>
            {rowsClients.map((row, index) => (
                <TableRow key={index} >
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.email}</TableCell>
                    <TableCell>{row.firstName} {row.lastName}</TableCell>
                    <TableCell>{row.phoneNumber}</TableCell>
                    <TableCell><Button onClick={()=>deleteClient(row.id)}>Delete</Button></TableCell>
                </TableRow>
            ))}
        </TableBody>
    );

    return (
        <div>
            <br />
            <br />
            <h4>BOATS</h4>
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderBoat}
                            {TableContentBoat}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br />
            <br />
            <h4>BOAT OWNERS</h4>
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderBoatOwner}
                            {TableContentBoatOwner}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br />
            <br />
            <h4>COTTAGESS</h4>
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderCottage}
                            {TableContentCottage}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br />
            <br />
            <h4>COTTAGE OWNERS</h4>
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderCottageOwner}
                            {TableContentCottageOwner}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>

            <br />
            <br />
            <h4>INSTRUCTORS</h4>
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderInstructor}
                            {TableContentInstructor}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
            <br />
            <br />
            <h4>INSTRUCTIONS</h4>
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderInstructions}
                            {TableContentInstructions}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>

            <br />
            <br />
            <h4>CLIENTS</h4>
            <br />
            <Grid container spacing={1}>
                <Grid item xs={2} />
                <Grid item xs={15}>
                    <TableContainer style={{ height: "550px", marginTop: "2%" }}>
                        <Table>
                            {TableHeaderClient}
                            {TableContentClient}
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
        </div>
    );
};

export default AllEntities;
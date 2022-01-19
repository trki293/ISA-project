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
  import { useState, useEffect } from "react";
  import axios from "axios";
  import { Link } from "react-router-dom";
  import { set } from "date-fns";
  
  
  const GetAllCottagesUnauthorize = () => {
  
    const [rows, setRows] = useState([]);
  
    const [copyRows, setCopyRows] = useState({});
  
    const [titleSearch, setTitleSearch] = useState("");
    const [citySearch, setCitySearch,] = useState("");
    const [countrySearch, setCountrySearch] = useState("");
  
    const [minPricePerNightSearch, setMinPricePerNightSearch] = useState(-1);
    const [minAverageGradeSearch, setMinAverageGradeSearch] = useState(-1);
    
    const [maxPricePerNightSearch, setMaxPricePerNightSearch] = useState(-1);
    const [maxAverageGradeSearch, setMaxAverageGradeSearch] = useState(-1);
    
    const [minNumberOfRoomsSearch, setMinNumberOfRoomsSearch] = useState(-1);
    const [maxNumberOfRoomsSearch, setMaxNumberOfRoomsSearch] = useState(-1);
    const [minNumberOfBedsSearch, setMinNumberOfBedsSearch] = useState(-1);
    const [maxNumberOfBedsSearch, setMaxNumberOfBedsSearch] = useState(-1);

    const [showForm, setShowForm] = useState(true);
  
    useEffect(() => {
      axios
        .post("http://localhost:8080/cottages/unauthenticated/getAllCottages", {
          "title": "",
          "minAverageGrade": -1,
          "maxAverageGrade": -1,
          "city": "",
          "country": "",
          "minPricePerNight": -1,
          "maxPricePerNight": -1,
          "minNumberOfRooms": -1,
          "maxNumberOfRooms": -1,
          "minNumberOfBeds": -1,
          "maxNumberOfBeds": -1
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
          <TableCell>
            ID
          </TableCell>
          <TableCell  >
            Title
          </TableCell>
          <TableCell>
            Street Name
          </TableCell>
          <TableCell>
            Street Number
          </TableCell>
          <TableCell>
            City
          </TableCell>
          <TableCell>
            Country
          </TableCell>
          <TableCell>
            Promotional Description
          </TableCell>
          <TableCell>
            Average Grade
          </TableCell>
          <TableCell >
            Price per Night
          </TableCell>
        </TableRow>
      </TableHead>
    );
  
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
            <TableCell>{row.promotionalDescription}</TableCell>
            <TableCell>{row.averageGrade}</TableCell>
            <TableCell>{row.pricePerNight}</TableCell>
          </TableRow>
        ))}
      </TableBody>
    );
  
    const handleSubmit = (event) => {
      event.preventDefault();
      axios
        .post("http://localhost:8080/cottages/unauthenticated/getAllCottages", {
          "title": titleSearch,
          "minAverageGrade": minAverageGradeSearch,
          "maxAverageGrade": maxAverageGradeSearch,
          "city": citySearch,
          "country": countrySearch,
          "minPricePerNight": minPricePerNightSearch,
          "maxPricePerNight": maxPricePerNightSearch,
          "minNumberOfRooms": minNumberOfRoomsSearch,
          "maxNumberOfRooms": maxNumberOfRoomsSearch,
          "minNumberOfBeds": minNumberOfBedsSearch,
          "maxNumberOfBeds": maxNumberOfBedsSearch
        }
        )
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        });
    }
  
    const showFormHandle = () => {
      let showFormState = showForm ;
      console.log(showForm);
      setShowForm(!showForm);
      console.log(showForm);
      
    }
  
    const hideAndResetHandler = () => {
      console.log(showForm);
      setShowForm(!showForm);
      console.log(showForm);
      axios
        .post("http://localhost:8080/cottages/unauthenticated/getAllCottages", {
            "title": "",
            "minAverageGrade": -1,
            "maxAverageGrade": -1,
            "city": "",
            "country": "",
            "minPricePerNight": -1,
            "maxPricePerNight": -1,
            "minNumberOfRooms": -1,
            "maxNumberOfRooms": -1,
            "minNumberOfBeds": -1,
            "maxNumberOfBeds": -1
        }
        )
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        });
        setMinPricePerNightSearch(-1);
        setMinAverageGradeSearch(-1);
        
        setMaxPricePerNightSearch(-1);
        setMaxAverageGradeSearch(-1);
        
        setTitleSearch("");
        setCitySearch("");
        setCountrySearch("");

        setMaxNumberOfRoomsSearch(-1);
        setMaxNumberOfBedsSearch(-1);

        setMinNumberOfRoomsSearch(-1);
        setMinNumberOfBedsSearch(-1);
    }
  
    return (
      <div>
        <br />
        <br />
        <form hidden={showForm} onSubmit={handleSubmit}>
          <label>Title:&nbsp;&nbsp;
            <input
              type="text"
              value={titleSearch}
              onChange={(e) => setTitleSearch(e.target.value)}
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
          <label>Min Price per Night:&nbsp;&nbsp;
            <input
              type="text"
              value={minPricePerNightSearch}
              onChange={(e) => setMinPricePerNightSearch(e.target.value)}
            />&nbsp;&nbsp;&nbsp;&nbsp;
          </label>
          <label>Max Price per Night:&nbsp;&nbsp;
            <input
              type="text"
              value={maxPricePerNightSearch}
              onChange={(e) => setMaxPricePerNightSearch(e.target.value)}
            />
          </label><br /><br />
          <label>Min Number Of Beds:&nbsp;&nbsp;
            <input
              type="text"
              value={minNumberOfBedsSearch}
              onChange={(e) => setMinNumberOfBedsSearch(e.target.value)}
            />&nbsp;&nbsp;&nbsp;&nbsp;
          </label>
          <label>Max Number Of Beds:&nbsp;&nbsp;
            <input
              type="text"
              value={maxNumberOfBedsSearch}
              onChange={(e) => setMaxNumberOfBedsSearch(e.target.value)}
            />
          </label><br /><br />
          <label>Min Number Of Rooms:&nbsp;&nbsp;
            <input
              type="text"
              value={minNumberOfRoomsSearch}
              onChange={(e) => setMinNumberOfRoomsSearch(e.target.value)}
            />&nbsp;&nbsp;&nbsp;&nbsp;
          </label>
          <label>Max Number Of Rooms:&nbsp;&nbsp;
            <input
              type="text"
              value={maxNumberOfRoomsSearch}
              onChange={(e) => setMaxNumberOfRoomsSearch(e.target.value)}
            />
          </label><br /><br />

          <input type="submit" />
          { !showForm ? <Button onClick={hideAndResetHandler}>Cancel</Button> : null }
        </form>
        { showForm ? <Button onClick={showFormHandle}>Search</Button> : null }
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
      </div>
    );
  };
  
  export default GetAllCottagesUnauthorize;
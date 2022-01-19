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


const GetAllBoatUnauthorize = () => {

  const [rows, setRows] = useState([]);

  const [copyRows, setCopyRows] = useState({});

  const [titleSearch, setTitleSearch] = useState("");
  const [citySearch, setCitySearch,] = useState("");
  const [countrySearch, setCountrySearch] = useState("");

  const [minPricePerHourSearch, setMinPricePerHourSearch] = useState(-1);
  const [minAverageGradeSearch, setMinAverageGradeSearch] = useState(-1);
  const [minCapacitySearch, setMinCapacitySearch] = useState(-1);
  const [maxPricePerHourSearch, setMaxPricePerHourSearch] = useState(-1);
  const [maxAverageGradeSearch, setMaxAverageGradeSearch] = useState(-1);
  const [maxCapacitySearch, setMaxCapacitySearch] = useState(-1);
  const [showForm, setShowForm] = useState(true);

  useEffect(() => {
    axios
      .post("http://localhost:8080/boats/unauthenticated/getAllBoats", {
        "title": "",
        "minAverageGrade": -1,
        "maxAverageGrade": -1,
        "city": "",
        "country": "",
        "minPricePerHour": -1,
        "maxPricePerHour": -1,
        "minCapacity": -1,
        "maxCapacity": -1
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
          Price per Hour
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
          <TableCell>{row.pricePerHour}</TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:8080/boats/unauthenticated/getAllBoats", {
        "title": titleSearch,
        "minAverageGrade": minAverageGradeSearch,
        "maxAverageGrade": maxAverageGradeSearch,
        "city": citySearch,
        "country": countrySearch,
        "minPricePerHour": minPricePerHourSearch,
        "maxPricePerHour": maxPricePerHourSearch,
        "minCapacity": minCapacitySearch,
        "maxCapacity": maxCapacitySearch
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
      .post("http://localhost:8080/boats/unauthenticated/getAllBoats", {
        "title": "",
        "minAverageGrade": -1,
        "maxAverageGrade": -1,
        "city": "",
        "country": "",
        "minPricePerHour": -1,
        "maxPricePerHour": -1,
        "minCapacity": -1,
        "maxCapacity": -1
      }
      )
      .then((res) => {
        setRows(res.data);
        setCopyRows(res.data);
      });
      setMinPricePerHourSearch(-1);
      setMinAverageGradeSearch(-1);
      setMinCapacitySearch(-1);
      setMaxPricePerHourSearch(-1);
      setMaxAverageGradeSearch(-1);
      setMaxCapacitySearch(-1);
      setTitleSearch("");
      setCitySearch("");
      setCountrySearch("");
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
        <label>Min Capacity:&nbsp;&nbsp;
          <input
            type="text"
            value={minCapacitySearch}
            onChange={(e) => setMinCapacitySearch(e.target.value)}
          />&nbsp;&nbsp;&nbsp;&nbsp;
        </label>
        <label>Max Capacity:&nbsp;&nbsp;
          <input
            type="text"
            value={maxCapacitySearch}
            onChange={(e) => setMaxCapacitySearch(e.target.value)}
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

export default GetAllBoatUnauthorize;
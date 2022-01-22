import { AppBar, Typography, Toolbar, Grid } from "@material-ui/core";
import { Link } from "react-router-dom";

const clearLocalStorage = () => {
    localStorage.clear();
};

const NavBar = ({ user }) => {
    const NavBarForClient = (
        <Toolbar>
            <Grid xs={125} container spacing={1} >
                <Grid item xs={1}>
                    
                    <Grid item xs={3}>
                        <Typography style={{marginTop:"15px"}}>
                            <Link
                                to="/client/homePage"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Home
                            </Link>
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={1}>
                    
                    <Grid item xs={3}>
                        <Typography style={{marginTop:"15px"}}>
                            <Link
                                to="/client/boatsForClient"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Boats
                            </Link>
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={1} style={{ textAlign: "right" }}>
                    <Grid item xs={3} >
                        <Typography style={{marginTop:"15px", marginRight:"20px"}}>
                            <Link
                                to="/client/cottagesForClient"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Cottages  
                            </Link>
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={2} style={{ textAlign: "center" }}>
                    <Grid item xs={50}>
                        <Typography style={{marginTop:"15px", marginRight:"10px"}}>
                            <Link
                                to="/client/instructionsForClient"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                  Instructions
                            </Link>
                        </Typography>
                    </Grid>

                </Grid>
                
                <Grid item xs={2} style={{ textAlign: "left" }}>
                    <Grid item xs={50}>
                        <Typography style={{marginTop:"5px"}}>
                            <Link
                                to="/client/history/cottagesReservation"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                  History Cottage Reservation
                            </Link>
                        </Typography>
                    </Grid>

                </Grid>
                <Grid item xs={2} style={{ textAlign: "left" }}>
                    <Grid item xs={50} >
                        <Typography style={{marginTop:"5px"}}>
                            <Link
                                to="/client/history/boatsReservation"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                  History Boats Reservation
                            </Link>
                        </Typography>
                    </Grid>

                </Grid>
                <Grid item xs={2} style={{ textAlign: "left" }}>
                    <Grid item xs={50}>
                        <Typography style={{marginTop:"5px"}}>
                            <Link
                                to="/client/history/instructionsReservation"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                  History Instructions Reservation
                            </Link>
                        </Typography>
                    </Grid>

                </Grid>
                <Grid item xs={1} style={{ textAlign: "left" }}>
                    <Grid item xs={2}>
                        <Typography style={{marginTop:"5px"}}>
                            <Link
                                to="/client/future/AllReservations"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                  Future Resevations
                            </Link>
                        </Typography>
                    </Grid>

                </Grid>
                
            </Grid>


            <Grid xs={45} item container spacing={1} style={{ textAlign: "right" }}>
                <Grid item xs={2}></Grid>
                <Grid item xs={2}></Grid>
                <Grid item xs={3}></Grid>
                <Grid item xs={2}>
                    <Typography>
                        <Link
                            to="/client/profile"
                            style={{ color: "#fafafa", textDecoration: "none" }}
                        >
                            Profile
                        </Link>
                    </Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography>
                            <a
                                href="/"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                                onClick={clearLocalStorage}
                            >
                                Sing out
                            </a>
                    </Typography>
                </Grid>
            </Grid>

        </Toolbar >
    );

    const NavBarForSystemAdmin = (
        <Toolbar>
            <Grid container>
                <Grid item xs={6}></Grid>
                <Grid item xs={6} container style={{ textAlign: "right" }}>
                    <Grid item xs={3} />
                    <Grid item xs={3}>
                        <Typography>
                            <Link
                                to="/systemAdmin/homePage"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Home page
                            </Link>
                        </Typography>
                    </Grid>
                    <Grid item xs={3}>
                        <Typography>
                            <Link
                                to="/client/profile"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Profile
                            </Link>
                        </Typography>
                    </Grid>
                    <Grid item xs={3}>
                        <Typography>
                            <a
                                href="/"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                                onClick={clearLocalStorage}
                            >
                                Sing out
                            </a>
                        </Typography>
                    </Grid>
                </Grid>
            </Grid>
        </Toolbar>
    );

    const NavBarForUnregistred = (
        <Toolbar>
            <Grid xs={25} container spacing={1}>
                <Grid item xs={1}>
                    <Grid item xs={1}>
                        <Typography>
                            <Link
                                to="/"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Home
                            </Link>
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={1}>
                    <Grid item xs={1}>
                        <Typography>
                            <Link
                                to="/boats/unauthorize"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Boats
                            </Link>
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={1} style={{ textAlign: "right" }}>
                    <Grid item xs={1} >
                        <Typography>
                            <Link
                                to="/cottages/unauthorize"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                Cottages  
                            </Link>
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={1} style={{ textAlign: "left" }}>
                    <Grid item xs={1}>
                        <Typography>
                            <Link
                                to="/instructions/unauthorize"
                                style={{ color: "#fafafa", textDecoration: "none" }}
                            >
                                  Instructions
                            </Link>
                        </Typography>
                    </Grid>

                </Grid>
                <Grid item xs={2} style={{ textAlign: "left" }}>
                
                </Grid>
            </Grid>


            <Grid xs={9} item container spacing={1} style={{ textAlign: "right" }}>
                <Grid item xs={2}></Grid>
                <Grid item xs={2}></Grid>
                <Grid item xs={3}></Grid>
                <Grid item xs={1}>

                </Grid>
                <Grid item xs={2}>
                    <Typography>
                        <Link
                            to="/login"
                            style={{ color: "#fafafa", textDecoration: "none" }}
                        >
                            Sign in
                        </Link>
                    </Typography>
                </Grid>
                <Grid item xs={2}>
                    <Typography>
                        <Link to="/register" style={{ color: "#fafafa", textDecoration: "none" }}>
                            Sign up
                        </Link>
                    </Typography>
                </Grid>
            </Grid>

        </Toolbar >
    );


    return (
        <>
            <AppBar position="static">
                {user === "client" && NavBarForClient}
                {user === "systemAdmin" && NavBarForSystemAdmin}

                {(user === "unregistred" || user === undefined) && NavBarForUnregistred}
            </AppBar>
        </>
    );
};

export default NavBar;

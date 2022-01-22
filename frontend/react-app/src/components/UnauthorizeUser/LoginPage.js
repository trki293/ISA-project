import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import axios from "axios";
import { Link } from "react-router-dom";

class LoginPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            token: "",
            roleUser: "",
            userEmail: "",
            password: "",
            lastTimePenaltyPointsReset: "",
            wrong: false,
            openDialog: true,
        };
    }

    async loginClick() {
        await axios
            .post("http://localhost:8080/auth/login", {
                email: this.state.userEmail,
                password: this.state.password,
            })
            .then((res) => {
                let tokenDTO = res.data;
                this.setState({
                    token: tokenDTO.accessToken,
                    userEmail: tokenDTO.email,
                    roleUser: tokenDTO.typeOfUser,
                    lastTimePenaltyPointsReset: tokenDTO.lastTimePenaltyPointsReset,
                    openDialog: true,
                });
                localStorage.setItem("token", this.state.token);
                localStorage.setItem("userEmail", this.state.userEmail);
                localStorage.setItem("roleUser", this.state.roleUser);
                if (this.state.roleUser === 'CLIENT') {
                    axios
                        .get("http://localhost:8080/clients/checkIfClientNeedResetPenaltyPoints/"+this.state.userEmail, {
                            headers: { Authorization: `Bearer ` + localStorage.getItem('token'), consumes: 'application/json' }
                        }
                        )
                        .then((res) => {
                            console.log("Uradjeni penali!")
                        });
                }
                this.redirect();
            })
            .catch(function (error) {
                if (error.response) {
                    alert("Wrong email or password!");
                }
            });



    }

    setOpenDialog(state) {
        state.setState({
            openDialog: false,
        });
    }

    onTodoChangeUsername(value) {
        this.setState({
            userEmail: value,
        });
    }

    onTodoChangePassword(value) {
        this.setState({
            password: value,
        });
    }

    render() {
        const inputProps = {
            step: 300,
        };
        return (
            <div>
                <br />
                <TextField
                    id="outlined-basic"
                    label="Username"
                    variant="outlined"
                    value={this.state.userEmail}
                    onChange={(e) => this.onTodoChangeUsername(e.target.value)}
                    size="small"
                />
                <br />
                <br />
                <TextField
                    id="outlined-basic"
                    label="Password"
                    variant="outlined"
                    type="password"
                    value={this.state.password}
                    onChange={(e) => this.onTodoChangePassword(e.target.value)}
                    size="small"
                />
                {this.state.wrong ? <p>Wrong email or password</p> : null}
                <br />
                <br />
                <Link to="/register">
                    You don't have account? Please, register with click on link!
                </Link>
                <br />
                <br />
                <div>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={this.loginClick.bind(this)}
                    >
                        Login
                    </Button>
                </div>
            </div>
        );
    }

    redirect() {
        var redirection1 = "/adminSystem/homePage";
        var redirection2 = "/client";

        var userRole = localStorage.getItem("roleUser");
        alert("Usao ", userRole);
        if (userRole === "SYSTEM_ADMIN") {
            alert("Usao ", userRole);
            axios
                .get("http://localhost:8080/system_admins/checkIfFirstLogin/" + this.state.userEmail, {
                    headers: { Authorization: `Bearer ` + this.state.token, consumes: 'application/json' }
                })
                .then((res) => {
                    console.log(res.data);
                    if (res.data === true) {
                        window.location.href = "http://localhost:3000/changePassword/" + this.state.userEmail;
                        localStorage.setItem("isFirstLogin", true);
                    } else {
                        window.location.href = "http://localhost:3000/adminSystem/homePage";
                    }
                })
                .catch(function (error) {
                    if (error.response) {
                        alert("Error");
                    }
                });
        }
        if (userRole === "CLIENT") {
            window.location.href = "http://localhost:3000/client/homePage";
        }
    }
}

export default LoginPage;
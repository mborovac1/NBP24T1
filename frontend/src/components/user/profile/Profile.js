import React, { useState, useEffect } from "react";
import { Grid, Typography, TextField, Paper } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import axios from "axios";
import "./Profile.css";

const Profile = () => {
  const [user, setUser] = useState({});
  const [address, setAddress] = useState({});
  const email = localStorage.getItem("email");

  useEffect(() => {
    const fetchUser = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/cinemaUsers";
        const response = await axios.get(`${BASE_URL}/user/${email}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        console.log("RESPONSE", response.data);
        setUser(response.data);

        //Address name
        const addressId = response.data.addressId;
        console.log("ADDRES ID:", addressId);
        const BASE_URL_ADDRESS = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/addresses";
        const addressResponse = await axios.get(`${BASE_URL_ADDRESS}/address/${addressId}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        console.log("ADDRESS RESPONSE", addressResponse.data);
        setAddress(addressResponse.data);
      } catch (error) {
        console.error("Failed to fetch user or address:", error);
      }
    };
    fetchUser();
  }, []);

  return (
    <Grid style={{ marginTop: "10px" }} container justifyContent="center">
      <Grid item xs={12} sm={6} md={4}>
        <Paper
          elevation={6}
          style={{
            margin: "10px",
            padding: "15px",
            textAlign: "left",
          }}
          key={user.id}
        >
          <Typography
            variant="h4"
            align="center"
            gutterBottom
            sx={{
              fontWeight: "bold",
              fontFamily: "Arial",
              marginBottom: "50px",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <span style={{ marginRight: "10px" }}>Profil</span>
            <PersonIcon fontSize="large" />
          </Typography>

          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
            <Typography
              variant="body1"
              sx={{
                fontWeight: "bold",
                fontSize: "1.2em",
                marginRight: "10px",
                alignSelf: "center",
                whiteSpace: "nowrap",
              }}
            >
              Ime:
            </Typography>
            <TextField
              variant="filled"
              disabled
              fullWidth
              value={user.firstName}
              InputProps={{
                style: {
                  fontWeight: "bold",
                },
              }}
            />
          </div>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
            <Typography
              variant="body1"
              sx={{
                fontWeight: "bold",
                fontSize: "1.2em",
                marginRight: "10px",
                alignSelf: "center",
                whiteSpace: "nowrap",
              }}
            >
              Prezime:
            </Typography>
            <TextField
              variant="filled"
              disabled
              fullWidth
              value={user.lastName}
              InputProps={{
                style: {
                  fontWeight: "bold",
                },
              }}
            />
          </div>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
            <Typography
              variant="body1"
              sx={{
                fontWeight: "bold",
                fontSize: "1.2em",
                marginRight: "10px",
                alignSelf: "center",
                whiteSpace: "nowrap",
              }}
            >
              Datum rodjenja:
            </Typography>
            <TextField
              variant="filled"
              disabled
              fullWidth
              value={user.birthDate}
              InputProps={{
                style: {
                  fontWeight: "bold",
                },
              }}
            />
          </div>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
            <Typography
              variant="body1"
              sx={{
                fontWeight: "bold",
                fontSize: "1.2em",
                marginRight: "10px",
                alignSelf: "center",
                whiteSpace: "nowrap",
              }}
            >
              Email:
            </Typography>
            <TextField
              variant="filled"
              disabled
              fullWidth
              value={user.email}
              InputProps={{
                style: {
                  fontWeight: "bold",
                },
              }}
            />
          </div>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
            <Typography
              variant="body1"
              sx={{
                fontWeight: "bold",
                fontSize: "1.2em",
                marginRight: "10px",
                alignSelf: "center",
                whiteSpace: "nowrap",
              }}
            >
              Broj telefona:
            </Typography>
            <TextField
              variant="filled"
              disabled
              fullWidth
              value={user.phoneNumber}
              InputProps={{
                style: {
                  fontWeight: "bold",
                },
              }}
            />
          </div>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
            <Typography
              variant="body1"
              sx={{
                fontWeight: "bold",
                fontSize: "1.2em",
                marginRight: "10px",
                alignSelf: "center",
                whiteSpace: "nowrap",
              }}
            >
              Adresa:
            </Typography>
            <TextField
              variant="filled"
              disabled
              fullWidth
              value={address.name}
              InputProps={{
                style: {
                  fontWeight: "bold",
                },
              }}
            />
          </div>
        </Paper>
      </Grid>
    </Grid>
  );
};

export default Profile;

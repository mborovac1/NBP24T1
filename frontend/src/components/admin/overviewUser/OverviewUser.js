import React, { useEffect, useState } from "react";
import { Container, Paper, Button } from "@mui/material";
import axios from "axios";
import jwt_decode from "jwt-decode";

export default function OverviewUser() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [korisnici, setKorisnici] = useState([]);
  const [address, setAddress] = useState([]);
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const fetchKorisnici = async () => {
      const token = localStorage.getItem("access_token");
      const decodedToken = jwt_decode(token);

      setIsAdmin(decodedToken.role === "ROLE_ADMIN");

      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response = await axios.get(`${BASE_URL}/api/cinemaUsers/users`, {
          headers: { Authorization: `Bearer ${token}` },
        });

        setKorisnici(response.data);
        console.log("RESPONSE", response.data);
        let addressArray = [];

        for (let i = 0; i < response.data.length; i++) {
          const addressId = response.data[i].addressId;
          const BASE_URL_ADDRESS = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
          const addressResponse = await axios.get(`${BASE_URL_ADDRESS}/api/addresses/address/${addressId}`, {
            headers: { Authorization: `Bearer ${token}` },
          });
          //console.log("ADDRESS RESPONSE", addressResponse.data);
          addressArray.push(addressResponse.data);

          //setAddress([...address, addressResponse.data]);
          //console.log("ADDRESS: ", address);
        }
        setAddress(addressArray);
      } catch (error) {
        console.error("Failed to fetch users:", error);
      }
    };

    fetchKorisnici();
  }, []);

  const handleObrisi = (idKorisnika) => {
    // Optimistically update the local state
    setKorisnici((prevKorisnici) => prevKorisnici.filter((korisnik) => korisnik.id !== idKorisnika));

    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
      axios.delete(`${BASE_URL}/api/nbpUsers/delete`, {
        headers: { Authorization: `Bearer ${token}` },
        params: {
            userId: idKorisnika
        }
      });
      console.log("Uspjesno obrisano");
    } catch (error) {
      console.error("Failed to delete user:", error);
      // If the server request fails, roll back the local state to its previous state
      // or handle the error in a way that makes sense for your application
      setKorisnici((prevKorisnici) => [...prevKorisnici]);
    }
  };

  return (
    <Container>
      {isAdmin ? (
        <>
          <h1 style={{ color: "white" }}>Korisnici</h1>
          <Paper elevation={3} style={paperStyle}>
            {korisnici.map((korisnik, index) => (
              <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left", color: "black" }} key={korisnik.id}>
                Id: {korisnik.id} <br />
                Ime: {korisnik.firstName} <br />
                Prezime: {korisnik.lastName} <br />
                Datum rođenja: {korisnik.birthDate} <br />
                Email: {korisnik.email} <br />
                Broj telefona: {korisnik.phoneNumber} <br />
                Adresa: {address.length > 0 ? address[index].name : ""}
                <Button variant="contained" color="error" onClick={() => handleObrisi(korisnik.id)} style={{ width: "100%", marginTop: "15px" }}>
                  Obriši nalog
                </Button>
              </Paper>
            ))}
          </Paper>
        </>
      ) : (
        <Paper elevation={3} style={paperStyle}>
          <p style={{ textAlign: "center", color: "black" }}>Nemate pristup. Kontaktirajte administratora.</p>
        </Paper>
      )}
    </Container>
  );
}

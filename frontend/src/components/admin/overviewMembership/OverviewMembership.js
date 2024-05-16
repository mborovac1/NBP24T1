import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Select, MenuItem, FormControl, InputLabel } from "@mui/material";
import axios from "axios";
import jwt_decode from "jwt-decode";

const membershipTypes = ["STANDARD", "STUDENT", "GOLD", "PLATINUM", "VIP"];

export default function OverviewMembership() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [korisnici, setKorisnici] = useState([]);
  const [sviKorisnici, setSviKorisnici] = useState([]);
  const [membership, setMembership] = useState([]);
  const [isAdmin, setIsAdmin] = useState(false);

  const [selectedMembership, setSelectedMembership] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchKorisnici = async () => {
      const token = localStorage.getItem("access_token");
      const decodedToken = jwt_decode(token);

      setIsAdmin(decodedToken.role === "ROLE_ADMIN");

      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/cinemaUsers";
        const response = await axios.get(`${BASE_URL}/users`, {
          headers: { Authorization: `Bearer ${token}` },
        });

        setKorisnici(response.data);
        //console.log("RESPONSE", response.data);

        const responseAll = await axios.get(`${BASE_URL}/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        console.log("RESPONSE", responseAll.data);

        setSviKorisnici(responseAll.data);

        let membershipArray = [];

        for (let i = 0; i < responseAll.data.length; i++) {
          const membershipId = responseAll.data[i].membershipId;
          if (membershipId != null) {
            const BASE_URL_ADDRESS = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/memberships";
            const membershipResponse = await axios.get(`${BASE_URL_ADDRESS}/membership/${membershipId}`, {
              headers: { Authorization: `Bearer ${token}` },
            });
            membershipArray.push(membershipResponse.data);
          }
        }
        setMembership(membershipArray);
        setIsLoading(false);
      } catch (error) {
        console.error("Failed to fetch users:", error);
        setIsLoading(false);
      }
    };

    fetchKorisnici();
  }, []);

  //membership changes
  const handleMembershipChange = (userId, newType) => {
    setSelectedMembership((prevState) => ({
      ...prevState,
      [userId]: newType,
    }));
  };

  const handleSave = async (userId, membershipId) => {
    const newType = selectedMembership[userId];
    const token = localStorage.getItem("access_token");
    console.log("NEW", selectedMembership);
    console.log("USER", membershipId);

    try {
      const BASE_URL_ADDRESS = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/memberships";
      const response = await axios.post(`${BASE_URL_ADDRESS}/updateMembership/${membershipId}/${newType}`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      console.log("Update response:", response.data);

      // Refresh the data after the update
      setMembership((prevMemberships) => prevMemberships.map((membership) => (membership.id === response.data.id ? response.data : membership)));
    } catch (error) {
      console.error("Failed to update membership:", error);
    }
  };

  return (
    <Container>
      {isAdmin ? (
        <>
          <h1 style={{ color: "white" }}>Korisnici</h1>
          <Paper elevation={3} style={paperStyle}>
            {korisnici.map((korisnik) => {
              const korisnikMembership = sviKorisnici.find((item) => item.userId === korisnik.id);
              const memb = membership.find((m) => m.id === korisnikMembership?.membershipId);
              const currentType = memb ? memb.type : "";
              return (
                <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left", color: "black" }} key={korisnik.id}>
                  Id: {korisnik.id} <br />
                  Ime: {korisnik.firstName} <br />
                  Prezime: {korisnik.lastName} <br />
                  Email: {korisnik.email} <br />
                  Vrsta članarine: {korisnikMembership ? korisnikMembership.membershipId : ""} <br />
                  <FormControl style={{ minWidth: 200 }}>
                    <Select value={selectedMembership[korisnik.id] !== undefined ? selectedMembership[korisnik.id] : currentType || ""} onChange={(e) => handleMembershipChange(korisnik.id, e.target.value)} style={{ minWidth: 200 }}>
                      {membershipTypes.map((type) => (
                        <MenuItem key={type} value={type} style={{ whiteSpace: "normal" }}>
                          {type}
                        </MenuItem>
                      ))}
                    </Select>
                  </FormControl>
                  <Button variant="contained" color="primary" onClick={() => handleSave(korisnik.id, korisnikMembership.membershipId)} style={{ marginTop: "10px" }}>
                    Save
                  </Button>
                </Paper>
              );
            })}
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

import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Box, TextField, Dialog, DialogTitle, DialogActions } from "@mui/material";
import axios from "axios";

export default function AddMovie() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };

  const [name, setName] = useState("");
  const [duration, setDuration] = useState("");
  const [description, setDescription] = useState("");
  const [posterPath, setPosterPath] = useState("");
  const [zanrovi, setZanrovi] = useState([]);
  const [sale, setSale] = useState([]);
  const [price, setPrice] = useState([]);

  const [filmovi, setFilmovi] = useState([]);

  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [reservationFail, setReservationFail] = useState(false);

  const [selectedZanrovi, setSelectedZanrovi] = useState([]);
  const [selectedSale, setSelectedSale] = useState([]);

  const handleZanrCheckboxChange = (e, zanr) => {
    if (e.target.checked) {
      setSelectedZanrovi((prevSelected) => [...prevSelected, zanr]);
    } else {
      setSelectedZanrovi((prevSelected) => prevSelected.filter((item) => item.id !== zanr.id));
    }
  };

  const handleSaleCheckboxChange = (e, sala) => {
    if (e.target.checked) {
      setSelectedSale((prevSelected) => [...prevSelected, sala]);
    } else {
      setSelectedSale((prevSelected) => prevSelected.filter((item) => item.id !== sala.id));
    }
  };

  const handleClick = async (e) => {
    const film = {
      name,
      duration,
      description,
      posterPath,
      price,
    };

    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      const response = await axios.post(`${BASE_URL}/api/movies/add`, film, {
        headers: { Authorization: `Bearer ${token}` },
      });

      let zahtjevZanrovi = selectedZanrovi.map((zanr) => ({
        id: zanr.id,
        nazivZanra: zanr.nazivZanra,
      }));

      let movieGenreMappings = [];

      zahtjevZanrovi.forEach((zanr) => {
        movieGenreMappings.push({ movieId: response.data.id, genreId: zanr.id });
      });

      const response1 = await axios.post(`${BASE_URL}/api/movieGenres/add`, movieGenreMappings, {
        headers: { Authorization: `Bearer ${token}` },
      });

      /* const response2 = await axios.put(`${BASE_URL}/sale/movie/${response.data.id}`, selectedSale, {
        headers: { Authorization: `Bearer ${token}` },
      });

      const response1 = await axios.put(
        `${BASE_URL}/genres/movie/${response.data.id}`,

        zahtjevZanrovi,

        {
          headers: { Authorization: `Bearer ${token}` },
        }
      ); */

      if (response.ok || response.status === 201) {
        setReservationSuccess(true);
      } else {
        setReservationFail(true);
      }
    } catch (error) {
      console.error("Failed to add film:", error);
    }
  };

  useEffect(() => {
    const fetchFilmovi = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${BASE_URL}/api/movies/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setFilmovi(response.data);
      } catch (error) {
        console.error("Failed to fetch users:", error);
      }
    };

    const fetchZanrovi = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${BASE_URL}/api/genres/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        const updatedZanrovi = response.data.map((zanr) => ({
          id: zanr.id,
          nazivZanra: zanr.name,
        }));
        setZanrovi(updatedZanrovi);
      } catch (error) {
        console.error("Failed to fetch zanrovi:", error);
      }
    };

    const fetchSale = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${BASE_URL}/sale`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        const updatedSale = response.data.map((sala) => ({
          id: sala.id,
          brojSale: sala.brojSale,
        }));
        setSale(updatedSale);
      } catch (error) {
        console.error("Failed to fetch sale:", error);
      }
    };

    fetchFilmovi();
    fetchZanrovi();
    fetchSale();
  }, []);

  const handleClose = () => {
    setReservationSuccess(false);
    setReservationFail(false);
  };

  const handleCloseSuccess = () => {
    setReservationSuccess(false);
    setReservationFail(false);
    window.location.href = "/overviewMovies";
  };

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1 style={{ color: "#2d2d2d" }}>Dodaj film</h1>
        <Box
          component="form"
          sx={{
            "& > :not(style)": { m: 1 },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField
            id="outlined-basic"
            label="Naziv filma"
            variant="outlined"
            value={name}
            onChange={(e) => setName(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />
          <TextField
            id="outlined-basic"
            label="Trajanje"
            variant="outlined"
            value={duration}
            onChange={(e) => setDuration(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />
          <TextField
            id="outlined-basic"
            label="Opis"
            variant="outlined"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />
          <TextField
            id="outlined-basic"
            label="URL Slike"
            variant="outlined"
            value={posterPath}
            onChange={(e) => setPosterPath(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />
          <TextField
            id="outlined-basic"
            label="Cijena"
            variant="outlined"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />

          <div>
            <label htmlFor="zanrovi">Zanrovi:</label>
            <div id="zanrovi">
              {zanrovi.map((zanr) => (
                <div key={zanr.id} style={{ display: "flex", alignItems: "center" }}>
                  <input type="checkbox" id={`checkbox-${zanr.id}`} value={zanr.id} onChange={(e) => handleZanrCheckboxChange(e, zanr)} />

                  <label htmlFor={`checkbox-zanr-${zanr.id}`} style={{ marginLeft: "8px" }}>
                    {zanr.nazivZanra}
                  </label>
                </div>
              ))}
            </div>
          </div>

          <div>
            <label htmlFor="sale">Sale:</label>
            <div id="sale">
              {sale.map((sala) => (
                <div key={sala.id} style={{ display: "flex", alignItems: "center" }}>
                  <input type="checkbox" id={`checkbox-${sala.id}`} value={sala.id} onChange={(e) => handleSaleCheckboxChange(e, sala)} />

                  <label htmlFor={`checkbox-sale-${sala.id}`} style={{ marginLeft: "8px" }}>
                    {sala.brojSale}
                  </label>
                </div>
              ))}
            </div>
          </div>

          <Button variant="contained" onClick={handleClick} style={{ backgroundColor: "#2d2d2d", width: "50vh", marginTop: "30px" }}>
            Dodaj
          </Button>
          <Dialog open={reservationSuccess && !reservationFail} onClose={handleClose}>
            <DialogTitle variant="h5" fontWeight="bold">
              Uspješno ste dodali film !
            </DialogTitle>

            <DialogActions style={{ justifyContent: "center" }}>
              <Button variant="contained" size="large" style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }} onClick={handleCloseSuccess}>
                OK
              </Button>
            </DialogActions>
          </Dialog>
          <Dialog open={reservationFail} onClose={handleClose}>
            <DialogTitle variant="h5" fontWeight="bold">
              Dodavanje filma nije uspjelo! Unijeli ste neispravne parametre!
            </DialogTitle>

            <DialogActions style={{ justifyContent: "center" }}>
              <Button variant="contained" size="large" style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }} onClick={handleClose}>
                OK
              </Button>
            </DialogActions>
          </Dialog>
        </Box>
      </Paper>
    </Container>
  );
}

import { Container, Button, Typography, Box, Dialog, DialogTitle, DialogContent, DialogActions } from "@mui/material";
import WeekendIcon from "@mui/icons-material/Weekend";
import Grid from "@mui/material/Grid";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { FormControl, Select, MenuItem } from "@mui/material";
import axios from "axios";

const Ticket = () => {
  const { kolicinaKarata, idFilma } = useParams();
  const email = localStorage.getItem("email");
  const [sjedista, setSjedista] = useState([]);
  const [odabrana, setOdabrana] = useState([]);
  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [saleFilma, setSaleFilma] = useState([]);
  const [izabranaSala, setIzabranaSala] = useState(1);
  const [korisnik, setKorisnik] = useState({});
  const [izabranaSalaId, setIzabranaSalaId] = useState(1);
  const [saleAll, setSaleAll] = useState({});

  const [movieHalls, setMovieHalls] = useState([]);
  const [appointments, setAppointments] = useState([]);
  const [selectedHall, setSelectedHall] = useState("");
  const [selectedAppointment, setSelectedAppointment] = useState("");

  const fetchSjedistaOdabraneSale = async (trenutnaSala) => {
    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      const response = await axios.get(`${BASE_URL}/sala/${trenutnaSala}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setSjedista(response.data.sjedista.map((e) => e.brojSjedista));
    } catch (error) {
      console.error("Failed to fetch movies:", error);
    }
  };

  useEffect(() => {
    const fetchSale = async () => {
      const token = localStorage.getItem("access_token");
      try {
        //pronalazenje termina i sala za film
        const BASE_URL = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${BASE_URL}/api/appointments/${idFilma}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        console.log("Response", response.data);
        const hallIds = [...new Set(response.data.map((appointment) => appointment.hallId))];
        setMovieHalls(hallIds);
        const sortedAppointments = response.data.sort((a, b) => new Date(a.startTime) - new Date(b.startTime));
        setAppointments(sortedAppointments);

        /*let salaIzOdgovora = response.data.sale.map((e) => e.brojSale);
        let idSaleIzOdgovora = response.data.sale.map((e) => e.id);
        setSaleAll(response.data.sale);
        setSaleFilma(salaIzOdgovora);
        setIzabranaSala(salaIzOdgovora[0]);
        setIzabranaSalaId(idSaleIzOdgovora[0]);
        fetchSjedistaOdabraneSale(salaIzOdgovora[0]);*/
      } catch (error) {
        console.error("Failed to fetch movies:", error);
      }
    };

    fetchSale();
  }, [korisnik]);

  const handleReservation = (event, currentSeat) => {
    if (!sjedista.includes(currentSeat)) {
      if (odabrana.length < kolicinaKarata) {
        setOdabrana((prevOdabrana) => [...prevOdabrana, currentSeat]);
      }
    }
  };

  const handleSubmit = (event) => {
    //const token = localStorage.getItem("access_token");
    if (Number(odabrana.length) !== Number(kolicinaKarata)) {
      alert(`Molimo odaberite tacno ${kolicinaKarata} sjedista.`);
      return;
    }
    for (let i = 0; i < odabrana.length; i++) {
      const post = {
        brojSjedista: odabrana[i],
      };

      let kor = 0;

      const fetchKorisnik = async () => {
        const token = localStorage.getItem("access_token");
        try {
          const BASE_URL = process.env.REACT_APP_BASE_URL;
          const response = await axios.get(`${BASE_URL}/user/email/${email}`, {
            headers: { Authorization: `Bearer ${token}` },
          });
          setKorisnik(response.data);
          kor = response.data.id;
        } catch (error) {
          console.error("Failed to fetch users:", error);
        }
      };
      fetchKorisnik();

      const submitSjedista = async () => {
        const token = localStorage.getItem("access_token");
        try {
          const BASE_URL = process.env.REACT_APP_BASE_URL;
          const response = await axios.post(`${BASE_URL}/dodajSjediste/${izabranaSalaId}`, post, { headers: { Authorization: `Bearer ${token}` } });
        } catch (error) {
          console.error("Failed to fetch sjedista:", error);
        }
      };

      const submitKartu = async () => {
        const token = localStorage.getItem("access_token");
        try {
          const BASE_URL = process.env.REACT_APP_BASE_URL;

          const response = await axios.post(`${BASE_URL}/dodajKartu/${kor}/${idFilma}/${izabranaSalaId}/${odabrana[i]}`, post, { headers: { Authorization: `Bearer ${token}` } });
        } catch (error) {
          console.error("Failed to fetch sjedista:", error);
        }
      };

      submitSjedista().then(() => {
        submitKartu();
      });
      //submitKartu();
    }

    // Show the reservation success message
    setReservationSuccess(true);
  };

  const handleChangeSala = (event) => {
    setIzabranaSala(event.target.value);
    setIzabranaSalaId(saleAll.find((sala) => sala.brojSale === event.target.value).id);
    fetchSjedistaOdabraneSale(event.target.value);
  };

  const handleClose = () => {
    fetchSjedistaOdabraneSale(izabranaSala);
    setOdabrana([]);
    setReservationSuccess(false);
  };

  const handleCloseSuccess = () => {
    fetchSjedistaOdabraneSale(izabranaSala);
    //setOdabrana([]);
    setReservationSuccess(false);

    window.location.href = "/moviesUser";
  };

  const handleChangeAppointment = (event) => {
    setSelectedAppointment(event.target.value);
  };

  const handleChangeHall = (event) => {
    setSelectedHall(event.target.value);
    const filteredAppointments = appointments.filter((appointment) => appointment.hallId === event.target.value);
    if (filteredAppointments.length > 0) {
      setSelectedAppointment(filteredAppointments[0].id);
    } else {
      setSelectedAppointment("");
    }
  };

  const filteredAppointments = appointments.filter((appointment) => appointment.hallId === selectedHall);

  return (
    <>
      <Typography variant="h5" style={{ color: "white", marginTop: "20px" }}>
        Sale:
      </Typography>
      <FormControl style={{ marginTop: "20px" }}>
        <Select style={{ color: "#2d2d2d", backgroundColor: "white" }} labelId="sale-label" id="sale" name="sale" value={selectedHall} onChange={handleChangeHall}>
          {movieHalls.map((e) => (
            <MenuItem key={e} style={{ color: "#2d2d2d", backgroundColor: "white" }} value={e}>
              {e}
            </MenuItem>
          ))}
        </Select>
      </FormControl>

      {selectedHall && (
        <>
          <Typography variant="h5" style={{ color: "white", marginTop: "20px" }}>
            Termini:
          </Typography>
          <FormControl style={{ marginTop: "20px" }}>
            <Select style={{ color: "#2d2d2d", backgroundColor: "white" }} labelId="appointment-label" id="appointment" name="appointment" value={selectedAppointment} onChange={handleChangeAppointment}>
              {filteredAppointments.map((appointment) => {
                const startTime = new Date(appointment.startTime);
                const endTime = new Date(appointment.endTime);
                const date = startTime.toLocaleDateString();
                const startTimeString = startTime.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
                const endTimeString = endTime.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
                return (
                  <MenuItem key={appointment.id} style={{ color: "#2d2d2d", backgroundColor: "white" }} value={appointment.id}>
                    {`${date} ${startTimeString} - ${endTimeString}`}
                  </MenuItem>
                );
              })}
            </Select>
          </FormControl>
        </>
      )}

      <Container sx={{ marginTop: "10px", paddingTop: "30px", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", backgroundColor: "#2d2d2d" }}>
        <Box sx={{ padding: "10px", backgroundColor: "white", border: "10px solid #ccc", width: "auto" }}>
          <Grid container spacing={2}>
            {Array.from(Array(10)).map((_, row) => (
              <Grid key={row} container item xs={12} justifyContent="center">
                {Array.from(Array(10)).map((_, col) => {
                  const seatNumber = row * 10 + col + 1;
                  const isSeatSelected = odabrana.includes(seatNumber) || sjedista.includes(seatNumber);
                  const seatColor = isSeatSelected ? "red" : "blue";
                  const isSeatDisabled = isSeatSelected || odabrana.length >= kolicinaKarata;
                  return (
                    <Grid key={col} item sx={{ marginLeft: 2 }}>
                      <WeekendIcon style={{ color: seatColor, pointerEvents: isSeatDisabled ? "none" : "auto" }} onClick={(e) => handleReservation(e, seatNumber)} />
                    </Grid>
                  );
                })}
              </Grid>
            ))}
          </Grid>
        </Box>
        <Typography variant="body1" sx={{ marginTop: 2, fontWeight: "bold", marginBottom: 2 }}>
          ODABRANA SJEDISTA: {odabrana.join(", ")}
        </Typography>
        <Button variant="contained" color="primary" sx={{ width: "50%", marginBottom: 2 }} onClick={handleSubmit}>
          Potvrdi
        </Button>
        <Dialog open={reservationSuccess} onClose={handleClose}>
          <DialogTitle>Uspjesna rezervacija</DialogTitle>
          <DialogContent>
            <Typography variant="body1">Uspjesno ste rezervisali sjedista: {odabrana.join(", ")}!</Typography>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleCloseSuccess}>OK</Button>
          </DialogActions>
        </Dialog>
      </Container>
    </>
  );
};

export default Ticket;

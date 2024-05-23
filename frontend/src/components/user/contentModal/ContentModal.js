import * as React from "react";
import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { unavailable } from "../movies/config";
import { useEffect, useState } from "react";
import "./ContentModal.css";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "#2d2d2d",
  color: "#fff",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const LoadingScreen = () => {
  return (
    <div className="loading-screen">
      <p>Loading, please wait...</p>
      {/* Add a spinner or animation if desired */}
    </div>
  );
};

export default function ContentModal({ children, id }) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [kolicinaKarata, setKolicinaKarata] = React.useState(0);
  const navigate = useNavigate();

  const navigateToTicketPage = () => {
    navigate(`/ticket/${kolicinaKarata}/${id}`);
  };

  const handleChange = (event) => {
    setKolicinaKarata(event.target.value);
  };

  const [filmovi, setFilmovi] = useState([]);
  const [user, setUser] = useState(null);
  const [membershipName, setMembershipName] = useState("");
  const [discount, setDiscount] = useState("");
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchFilmovi = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response = await axios.get(`${BASE_URL}/api/movies/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setFilmovi(response.data);
      } catch (error) {
        console.error("Failed to fetch movies:", error);
      }
    };

    fetchFilmovi();
  }, []);

  useEffect(() => {
    const fetchUser = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/cinemaUsers";
        const response = await axios.get(`${BASE_URL}/user/${localStorage.getItem("email")}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setUser(response.data);
      } catch (error) {
        console.error("Failed to fetch user:", error);
      }
    };

    fetchUser();
  }, []);

  useEffect(() => {
    const fetchMembership = async () => {
      if (!user || !user.id) return;

      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/cinemaUsers";
        const responseSetKorisnici = await axios.get(`${BASE_URL}/`, {
          headers: { Authorization: `Bearer ${token}` },
        });

        const userWithMembership = responseSetKorisnici.data.find((korisnik) => korisnik.userId === user.id);
        if (userWithMembership && userWithMembership.membershipId) {
          const membershipId = userWithMembership.membershipId;
          const BASE_URL_ADDRESS = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/memberships";
          const membershipResponse = await axios.get(`${BASE_URL_ADDRESS}/membership/${membershipId}`, {
            headers: { Authorization: `Bearer ${token}` },
          });

          setMembershipName(membershipResponse.data.type);
          setDiscount(membershipResponse.data.discount);
        }
        setIsLoading(false);
      } catch (error) {
        console.error("Failed to fetch membership:", error);
        setIsLoading(false);
      }
    };

    fetchMembership();
  }, [user]);

  if (isLoading) {
    return <LoadingScreen />;
  }

  const currentFilm = filmovi.find((film) => film.id === id);

  return (
    <div>
      <div className="media" onClick={handleOpen}>
        {children}
      </div>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        slotProps={{
          backdrop: {
            timeout: 500,
          },
        }}
      >
        <Fade in={open}>
          <Box sx={style}>
            <Button style={{ position: "absolute", top: 0, right: 0, color: "#fff" }} onClick={handleClose}>
              X
            </Button>
            <Typography style={{ marginBottom: "10px" }} variant="h5" component="div" fontWeight="bold">
              {currentFilm?.name || ""}
            </Typography>
            <Typography variant="body1" component="div" mb={2}>
              {currentFilm?.description || ""}
            </Typography>
            <img style={{ width: "200px", height: "300px" }} className="poster" src={currentFilm?.posterPath ? `${currentFilm?.posterPath}` : unavailable} alt={currentFilm?.naziv} />
            <Typography variant="body2" component="div">
              Trajanje filma: {currentFilm?.duration || ""} minuta
            </Typography>

            <div style={{ color: "white", marginTop: "10px" }}>
              <div style={{ display: "flex", alignItems: "center" }}>
                <InputLabel htmlFor="kolicinaKarata" style={{ color: "white", marginRight: "10px" }}>
                  Odaberite broj karata:
                </InputLabel>
                <FormControl sx={{ m: 1, flex: "1" }}>
                  <Select
                    style={{
                      color: "white",
                      width: "100%",
                      border: "1px solid white",
                      borderRadius: "4px",
                      backgroundColor: "transparent",
                    }}
                    value={kolicinaKarata}
                    onChange={handleChange}
                    displayEmpty
                    inputProps={{ "aria-label": "1" }}
                    id="kolicinaKarata"
                  >
                    <MenuItem value="">
                      <em></em>
                    </MenuItem>
                    <MenuItem value={1}>1</MenuItem>
                    <MenuItem value={2}>2</MenuItem>
                    <MenuItem value={3}>3</MenuItem>
                    <MenuItem value={4}>4</MenuItem>
                    <MenuItem value={5}>5</MenuItem>
                  </Select>
                </FormControl>
              </div>
              <div style={{ marginTop: "10px" }}>
                <Typography variant="body2" component="div">
                  Vaša vrsta članstva: {membershipName}
                </Typography>
                <Typography variant="body2" component="div">
                  Ostvareni popust: {discount} %
                </Typography>
              </div>
            </div>

            <Button style={{ marginTop: "10px", backgroundColor: "white", color: "black", fontWeight: "bold" }} onClick={navigateToTicketPage}>
              REZERVIŠI KARTE
            </Button>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}

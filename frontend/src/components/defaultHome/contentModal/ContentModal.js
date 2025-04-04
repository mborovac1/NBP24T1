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
const apiUrl = process.env.REACT_APP_BASE_URL;

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

export default function ContentModal({ children, id }) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [filmovi, setFilmovi] = useState([]);

  useEffect(() => {
    fetch(`${apiUrl}/api/movies/`)
      .then((res) => res.json())
      .then((result) => {
        setFilmovi(result);
      });
  }, []);

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
            <img style={{ width: "200px", height: "300px" }} className="poster" src={currentFilm?.posterPath ? `${currentFilm?.posterPath}` : unavailable} alt={currentFilm?.name} />
            <Typography variant="body2" component="div">
              Trajanje filma: {currentFilm?.duration || ""} minuta
            </Typography>
            <Typography variant="h5" component="div" style={{ marginTop: "10px" }}>
              Želite rezervisati kartu? Prijavite se!
            </Typography>

            <Button
              style={{
                marginTop: "10px",
                backgroundColor: "white",
                color: "black",
                fontWeight: "bold",
              }}
              onClick={() => (window.location.href = "/login")}
            >
              PRIJAVI SE
            </Button>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}

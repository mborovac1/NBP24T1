import React, { useEffect, useState } from "react";
import { TextField, Button, Box, Typography, Grid, IconButton } from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import axios from "axios";

const ReviewUser = () => {
  const [rating, setRating] = useState(0);
  const [hover, setHover] = useState(0);
  const [description, setDescription] = useState("");
  const [cinemaId, setCinemaId] = useState(0);
  const [cinemaUserId, setCinemaUserId] = useState(0);
  const [user, setUser] = useState({});
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const token = localStorage.getItem("access_token");
  const email = localStorage.getItem("email");

  const handleRating = (value) => {
    setRating(value);
  };

  const handleMouseEnter = (value) => {
    setHover(value);
  };

  const handleMouseLeave = () => {
    setHover(0);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const fetchUser = async () => {
    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080/api/cinemaUsers";
      const response = await axios.get(`${BASE_URL}/user/${email}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setUser(response.data);
    } catch (error) {
      console.error("Failed to fetch user", error);
    }
  };

  useEffect(() => {
    fetchUser();
    setCinemaUserId(user.id);
  }, [cinemaUserId]);

  const handleClick = async (e) => {
    console.log("USER", user);
    console.log("CINEMA USER ID", cinemaUserId);
    //if (cinemaUserId != undefined) {
    const cinemaReview = {
      cinemaId,
      cinemaUserId: user.id,
      rating,
      text: description,
    };

    console.log("CINEMA Body", cinemaReview);

    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
      const response = await axios.post(`${BASE_URL}/api/cinemaReviews/addCinemaReview`, cinemaReview, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setSuccessMessage("Hvala Vam na ostavljenoj recenziji.");
      setErrorMessage("");
    } catch (error) {
      console.error("Failed to add cinema review:", error);
      setErrorMessage("Došlo je do greške prilikom ostavljanja recenzije. Možete ostaviti samo jednu recenziju.");
      setSuccessMessage("");
    }
  };

  const isReviewValid = rating > 0 && description.length >= 3;

  return (
    <div style={{ marginTop: "50px", width: "80%", margin: "auto" }}>
      <Grid container spacing={2} justifyContent="center">
        <Grid item xs={12} sm={8} md={6} lg={6} style={{ marginBottom: "20px" }}>
          <Box
            className="square"
            sx={{
              border: "5px solid white",
              borderRadius: "8px",
              background: "#2d2d2d",
              padding: "32px",
              width: "100%",
              height: "500px",
              textAlign: "left",
              color: "white",
              marginTop: "20px",
            }}
          >
            <Typography variant="h4" sx={{ textAlign: "center", fontWeight: "bold" }}>
              Ostavite recenziju
            </Typography>

            <Box display="flex" justifyContent="center" sx={{ marginTop: "20px" }}>
              {[1, 2, 3, 4, 5].map((value) => (
                <IconButton key={value} onClick={() => handleRating(value)} onMouseEnter={() => handleMouseEnter(value)} onMouseLeave={handleMouseLeave} sx={{ color: "#ffb400" }}>
                  {value <= (hover || rating) ? <StarIcon /> : <StarBorderIcon />}
                </IconButton>
              ))}
            </Box>
            <TextField label="Opis" multiline rows={8} variant="outlined" value={description} onChange={handleDescriptionChange} sx={{ marginTop: "1rem", width: "100%", backgroundColor: "white", borderRadius: "4px" }} />
            <Button variant="contained" color="primary" disabled={!isReviewValid} sx={{ marginTop: "1rem", width: "100%", color: "white", "&.Mui-disabled": { color: "white" } }} onClick={handleClick}>
              Ostavite recenziju
            </Button>
            {successMessage && (
              <Typography variant="body1" sx={{ color: "green", marginTop: "1rem", fontWeight: "bold" }}>
                {successMessage}
              </Typography>
            )}
            {errorMessage && (
              <Typography variant="body1" sx={{ color: "red", marginTop: "1rem", fontWeight: "bold" }}>
                {errorMessage}
              </Typography>
            )}
          </Box>
        </Grid>
      </Grid>
    </div>
  );
};

export default ReviewUser;

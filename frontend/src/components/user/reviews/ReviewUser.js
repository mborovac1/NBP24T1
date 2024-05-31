import React, { useEffect, useState } from "react";
import { TextField, Button, Box, Typography, Grid, IconButton, CircularProgress } from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import ContactSupportIcon from "@mui/icons-material/ContactSupport";
import axios from "axios";

const ReviewUser = () => {
  const [rating, setRating] = useState(0);
  const [hover, setHover] = useState(0);
  const [description, setDescription] = useState("");
  const [cinemaId, setCinemaId] = useState(0);
  const [cinemaUserId, setCinemaUserId] = useState(0);
  const [user, setUser] = useState({});
  const [hasLeftReview, setHasLeftReview] = useState(false);
  const [loading, setLoading] = useState(true);
  const [loadingReviewCheck, setLoadingReviewCheck] = useState(true);
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
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      const response = await axios.get(`${BASE_URL}/api/cinemaUsers/user/${email}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setUser(response.data);

      const response1 = await axios.get(`${BASE_URL}/api/cinemaUsers/`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      const cinemaUsers = response1.data;
      const matchingCinemaUser = cinemaUsers.find(cu => cu.userId === response.data.id);
      if (matchingCinemaUser) {
        setCinemaUserId(matchingCinemaUser.id);
      }
    } catch (error) {
      console.error("Failed to fetch user", error);
    }
  };

  const fetchCinemas = async () => {
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      const response = await axios.get(`${BASE_URL}/api/cinemas/`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      const cinemas = response.data;
      if (cinemas.length > 0) {
        setCinemaId(cinemas[0].id); // Set the cinemaId to the first cinema's ID
      }
    } catch (error) {
      console.error("Failed to fetch cinemas", error);
    }
  };

  const checkIfUserHasLeftReview = async (cinemaUserId) => {
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      const response = await axios.get(`${BASE_URL}/api/cinemaReviews/`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      const reviews = response.data;
      const userReview = reviews.find((review) => review.cinemaUserId === cinemaUserId);
      if (userReview) {
        setHasLeftReview(true);
      }
    } catch (error) {
      console.error("Failed to check user reviews", error);
    } finally {
      setLoadingReviewCheck(false);
    }
  };

  useEffect(() => {
    const initialize = async () => {
      setLoading(true);
      await fetchUser();
      await fetchCinemas();
      setLoading(false);
    };
    initialize();
  }, []);

  useEffect(() => {
    if (cinemaUserId) {
      setLoadingReviewCheck(true);
      checkIfUserHasLeftReview(cinemaUserId);
    }
  }, [cinemaUserId]);

  const handleClick = async () => {
    const cinemaReview = {
      cinemaId,
      cinemaUserId,
      rating,
      text: description,
    };

    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      await axios.post(`${BASE_URL}/api/cinemaReviews/addCinemaReview`, cinemaReview, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setSuccessMessage("Hvala Vam na ostavljenoj recenziji.");
      setErrorMessage("");
      setHasLeftReview(true);
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
              height: hasLeftReview ? "300px" : "500px",
              textAlign: "left",
              color: "white",
              marginTop: "20px",
              position: "relative",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            {loading || loadingReviewCheck ? (
              <Box display="flex" justifyContent="center" alignItems="center" height="100%">
                <CircularProgress color="primary" />
              </Box>
            ) : hasLeftReview ? (
              <Box textAlign="center">
                <Typography variant="h4" sx={{ fontWeight: "bold" }}>
                    Hvala Vam na ostavljenoj recenziji.
                </Typography>
                <CheckCircleIcon sx={{ fontSize: 50, color: "green", mt: 2 }} />
                <Typography variant="body1" sx={{ marginTop: "1rem" }}>
                  Vaša povratna informacija nam je izuzetno važna. Nadamo se da ćete ponovo posjetiti naša kina!
                </Typography>
                <Box display="flex" justifyContent="center" alignItems="center" sx={{ marginTop: "1rem" }}>
                  <ContactSupportIcon sx={{ fontSize: 30, marginRight: "0.5rem" }} />
                  <Typography variant="body1">
                    Ako imate bilo kakvih pitanja ili dodatnih komentara, slobodno nas kontaktirajte.
                  </Typography>
                </Box>
              </Box>
            ) : (
              <>
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
              </>
            )}
          </Box>
        </Grid>
      </Grid>
    </div>
  );
};

export default ReviewUser;

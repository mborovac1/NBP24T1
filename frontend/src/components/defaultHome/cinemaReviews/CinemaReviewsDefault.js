import React, { useEffect, useState } from "react";
import axios from "axios";
import { Box, Typography } from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import StarHalfIcon from "@mui/icons-material/StarHalf";

const CinemaReviewsDefault = () => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response = await axios.get(`${BASE_URL}/api/cinemaReviews/`);
        setReviews(response.data);
      } catch (error) {
        console.error("Failed to fetch reviews", error);
      }
    };

    fetchReviews();
  }, []);

  // Function to render star icons based on the rating
  const renderStars = (rating) => {
    const filledStars = Math.floor(rating);
    const halfStar = rating - filledStars >= 0.5 ? 1 : 0;
    const emptyStars = 5 - filledStars - halfStar;

    return (
      <>
        {[...Array(filledStars)].map((_, index) => (
          <StarIcon key={`star-filled-${index}`} />
        ))}
        {halfStar === 1 && <StarHalfIcon />}
        {[...Array(emptyStars)].map((_, index) => (
          <StarBorderIcon key={`star-empty-${index}`} />
        ))}
      </>
    );
  };

  return (
    <div style={{ marginTop: "50px", width: "80%", margin: "auto" }}>
      <Typography variant="h4" sx={{ textAlign: "center", fontWeight: "bold", marginBottom: "30px" }}>
        Recenzije
      </Typography>

      {reviews.map((review) => (
        <Box
          key={review.id}
          className="square"
          sx={{
            border: "5px solid white",
            borderRadius: "8px",
            background: "#2d2d2d",
            padding: "32px",
            width: "100%",
            textAlign: "left",
            color: "white",
            marginTop: "20px",
          }}
        >
          <Typography variant="h5" sx={{ textAlign: "center", marginTop: "20px" }}>
            Ocjena: {renderStars(review.rating)}
          </Typography>

          <Typography variant="body1" sx={{ marginTop: "1rem" }}>
            {review.text}
          </Typography>
        </Box>
      ))}
    </div>
  );
};

export default CinemaReviewsDefault;

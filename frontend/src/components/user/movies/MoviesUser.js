import React, { useEffect, useState } from "react";
import {
  TextField,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Typography,
  Box,
  CircularProgress,
  IconButton,
} from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import SearchIcon from "@mui/icons-material/Search";
import axios from "axios";
import SingleContent from "../SingleContent/SingleContent";
import Genres from "./Genres";
import "./Movies.css";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import { green } from "@mui/material/colors";

const MoviesUser = () => {
  const [filmovi, setFilmovi] = useState([]);
  const [selectedGenres, setSelectedGenres] = useState([]);
  const [genres, setGenres] = useState([]);
  const [allGenres, setAllGenres] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredMovies, setFilteredMovies] = useState([]);
  const [openReviewDialog, setOpenReviewDialog] = useState(false);
  const [selectedMovieId, setSelectedMovieId] = useState(null);
  const [reviewedMovies, setReviewedMovies] = useState(new Set());
  const [hasLeftReview, setHasLeftReview] = useState(false); 
  const [rating, setRating] = useState(0);
  const [hover, setHover] = useState(0);
  const [description, setDescription] = useState("");
  const [loadingReviewCheck, setLoadingReviewCheck] = useState(true);
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [user, setUser] = useState(null);
  const [cinemaUserId, setCinemaUserId] = useState(null);
  const token = localStorage.getItem("access_token");
  const email = localStorage.getItem("email");
  const apiUrl = process.env.REACT_APP_BASE_URL;

  useEffect(() => {
    const fetchFilmovi = async () => {
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${BASE_URL}/api/movies/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setFilmovi(response.data);
      } catch (error) {
        console.error("Failed to fetch movies:", error);
      }
    };

    const fetchGenres = async () => {
      try {
        const response = await axios.get(`${apiUrl}/api/movieGenres/`);
        setAllGenres(response.data);
      } catch (error) {
        console.error("Failed to fetch genres:", error);
      }
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

    const fetchReviews = async () => {
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${BASE_URL}/api/movieReviews/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        const reviews = response.data;
        const userReviewedMovies = new Set(reviews.filter(review => review.cinemaUserId === cinemaUserId).map(review => review.movieId));
        setReviewedMovies(userReviewedMovies);
      } catch (error) {
        console.error("Failed to fetch reviews", error);
      }
    };

    if (cinemaUserId) {
      fetchReviews();
    }

    fetchFilmovi();
    fetchGenres();
    fetchUser();
  }, [cinemaUserId]);

  useEffect(() => {
    filterMovies();
  }, [searchTerm, selectedGenres, filmovi, allGenres]);

  const handleSearchInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleGenreSelect = (genre) => {
    setSelectedGenres([...selectedGenres, genre]);
  };

  const handleGenreDeselect = (genre) => {
    setSelectedGenres(selectedGenres.filter((selected) => selected.id !== genre.id));
  };

  const filterMovies = () => {
    const filteredMovies = filmovi.filter((movie) => {
      const movieGenreIds = allGenres.filter((mg) => mg.movieId === movie.id).map((mg) => mg.genreId);
      const hasAllSelectedGenres = selectedGenres.every((selectedGenre) => movieGenreIds.includes(selectedGenre.id));
      if (searchTerm !== "" && !movie.name.toLowerCase().includes(searchTerm.toLowerCase())) {
        return false;
      }
      return hasAllSelectedGenres;
    });
    setFilteredMovies(filteredMovies);
  };

  const openReviewDialogForMovie = (movieId) => {
    setSelectedMovieId(movieId);
    checkIfUserHasLeftReview(movieId);
    setOpenReviewDialog(true);
  };

  const checkIfUserHasLeftReview = async (movieId) => {
    setLoadingReviewCheck(true);
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      const response = await axios.get(`${BASE_URL}/api/movieReviews/`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      const reviews = response.data;
      const userReview = reviews.find((review) => review.cinemaUserId === cinemaUserId && review.movieId === movieId);
      setHasLeftReview(!!userReview);
    } catch (error) {
      console.error("Failed to check user reviews", error);
    } finally {
      setLoadingReviewCheck(false);
    }
  };

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

  const handleSubmitReview = async () => {
    const movieReview = {
      movieId: selectedMovieId,
      cinemaUserId: cinemaUserId,
      rating,
      text: description,
    };
  
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      await axios.post(`${BASE_URL}/api/movieReviews/addMovieReview`, movieReview, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setErrorMessage("");
      setSuccessMessage("Hvala Vam na ostavljenoj recenziji.");
      setReviewedMovies(new Set([...reviewedMovies, selectedMovieId]));
      setHasLeftReview(true); // Mark the review as left
    } catch (error) {
      console.error("Failed to add movie review:", error);
      if (error.response && error.response.status === 400) {
        setErrorMessage("Došlo je do greške prilikom ostavljanja recenzije. Možete ostaviti samo jednu recenziju.");
      } else {
        setErrorMessage("Došlo je do greške prilikom ostavljanja recenzije.");
      }
      setSuccessMessage("");
    }
  };
  

  const handleCloseReviewDialog = () => {
    setOpenReviewDialog(false);
    setSelectedMovieId(null);
    setRating(0);
    setDescription("");
    setSuccessMessage("");
    setErrorMessage("");
  };

  const filteredContent = searchTerm !== "" || selectedGenres.length > 0 ? filteredMovies : filmovi;

  return (
    <div>
      <div style={{ display: "flex", margin: "15px" }}>
        <TextField
          style={{ flex: 1 }}
          className="searchBox"
          label="Pretraga filmova"
          variant="filled"
          InputProps={{ style: { color: "white" } }}
          InputLabelProps={{ style: { color: "white" } }}
          value={searchTerm}
          onChange={handleSearchInputChange}
        />
        <Button variant="contained" style={{ backgroundColor: "white", marginLeft: 10 }}>
          <SearchIcon style={{ color: "black" }} />
        </Button>
      </div>

      <Genres
        selectedGenres={selectedGenres}
        setSelectedGenres={setSelectedGenres}
        genres={genres}
        setGenres={setGenres}
        onGenreSelect={handleGenreSelect}
        onGenreDeselect={handleGenreDeselect}
      />
      <div className="movies">
        {filteredContent.length > 0 ? (
          filteredContent.map((c) => (
            <div key={c.id}>
              <SingleContent
                id={c.id}
                naziv={c.name}
                trajanje={c.duration}
                opis={c.description}
                poster={c.posterPath}
              />
              <Button
                variant="contained"
                color="primary"
                style={{ marginTop: 10 }}
                onClick={() => openReviewDialogForMovie(c.id)}
                disabled={reviewedMovies.has(c.id)}
              >
                Ostavite recenziju
              </Button>
            </div>
          ))
        ) : (
          <p>
            <b>Ne postoje filmovi sa odabranim žanrovima.</b>
          </p>
        )}
      </div>

      <Dialog open={openReviewDialog} onClose={handleCloseReviewDialog}>
        <DialogTitle>Ostavite recenziju za film</DialogTitle>
        <DialogContent>
  {loadingReviewCheck ? (
    <CircularProgress />
  ) : (
    <Box>
      {successMessage ? (
        <>
          <Box display="flex" alignItems="center" justifyContent="center">
            <CheckCircleOutlineIcon sx={{ color: green[500], fontSize: 48, marginRight: 2 }} />
            <Typography color="primary" variant="h6">{successMessage}</Typography>
          </Box>
        </>
      ) : (
        <>
          <Typography variant="h6">Ocjena</Typography>
          <Box display="flex">
            {[1, 2, 3, 4, 5].map((value) => (
              <IconButton
                key={value}
                onClick={() => handleRating(value)}
                onMouseEnter={() => handleMouseEnter(value)}
                onMouseLeave={handleMouseLeave}
              >
                {value <= (hover || rating) ? <StarIcon /> : <StarBorderIcon />}
              </IconButton>
            ))}
          </Box>
          <TextField
            margin="dense"
            id="name"
            label="Opis"
            type="text"
            fullWidth
            multiline
            rows={4}
            value={description}
            onChange={handleDescriptionChange}
          />
          {errorMessage && (
            <Typography color="error">{errorMessage}</Typography>
          )}
        </>
      )}
    </Box>
  )}
</DialogContent>

        <DialogActions>
          <Button onClick={handleCloseReviewDialog} color="primary">
            Otkaži
          </Button>
          <Button onClick={handleSubmitReview} color="primary" disabled={hasLeftReview || loadingReviewCheck}>
            Pošalji
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default MoviesUser;

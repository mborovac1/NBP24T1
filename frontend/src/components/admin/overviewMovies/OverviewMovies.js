// OverviewMovies.js

import React, { useEffect, useState } from "react";
import { Container, Paper, Button } from "@mui/material";
import axios from "axios";
import jwt_decode from 'jwt-decode';

export default function OverviewMovies() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [filmovi, setFilmovi] = useState([]);
  const [movieGenres, setMovieGenres] = useState([]);
  const [genres, setGenres] = useState([]);
  const [genresForMovie, setGenresForMovie] = useState([]);
  const [isLogged, setIsLogged] = useState(true); // Track whether the user is logged in

  const handleObrisi = async (idFilma) => {
    // Optimistically update the local state
    setFilmovi((prevFilmovi) =>
      prevFilmovi.filter((film) => film.id !== idFilma)
    );

    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL =
        process.env.REACT_APP_BASE_URL || "http://localhost:8080";
      await axios.delete(`${BASE_URL}/api/movies/delete/${idFilma}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log("Uspješno obrisano");
    } catch (error) {
      console.error("Failed to delete movie:", error);
      setFilmovi((prevFilmovi) => [...prevFilmovi]);
    }
  };

  useEffect(() => {
    const fetchFilmovi = async () => {
      const token = localStorage.getItem("access_token");

      // Check if the token is empty
      if (!token) {
        setIsLogged(false);
        return;
      }

      const decodedToken = jwt_decode(token);

      // You can add more conditions here based on the user's role if needed

      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response = await axios.get(`${BASE_URL}/api/movies/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setFilmovi(response.data);

        
        const BASE_URL_MOVIEGENRE = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response_moviegenre = await axios.get(`${BASE_URL_MOVIEGENRE}/api/movieGenres/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setMovieGenres(response_moviegenre.data);

        const BASE_URL_GENRE = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response_genre = await axios.get(`${BASE_URL_GENRE}/api/genres/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setGenres(response_genre.data);

      } catch (error) {
        console.error("Failed to fetch movies:", error);
      }
    };

    fetchFilmovi();
  }, []);


  useEffect(() => {
    const getGenresForMovies = (movies, movieGenres) => {
      const genresForMovies = movies.map((movie) => {
        const genreIdsForMovie = movieGenres
          .filter((movieGenre) => movieGenre.movieId === movie.id)
          .map((movieGenre) => movieGenre.genreId);
  
        const genresForMovie = genres
          .filter((genre) => genreIdsForMovie.includes(genre.id))
          .map((genre) => genre.name);
  
        return { id: movie.id, genres: genresForMovie.join(", ") };
      });
  
      return genresForMovies;
    };
  
    const genresForMovies = getGenresForMovies(filmovi, movieGenres);
    setGenresForMovie(genresForMovies);

  }, [filmovi, movieGenres, genres]);

  return (
    <>
      {isLogged && (
        <Container>
          <h1 style={{ color: "white" }}>Filmovi</h1>
          <Paper elevation={3} style={paperStyle}>
            {filmovi.map((film) => (
              <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left" }} key={film.id}>
                Id: {film.id} <br />
                Naziv: {film.name} <br />
                Trajanje: {film.duration} <br />
                Opis: {film.description} <br />
                URL slike:{" "}
                <a href={film.posterPath} target="_blank" rel="noopener noreferrer" style={{ display: "block", overflow: "hidden", whiteSpace: "nowrap", textOverflow: "ellipsis", maxWidth: "100%" }}>
                  {film.posterPath}
                </a>{" "}
                <br />
                Zanrovi: {genresForMovie.find((item) => item.id === film.id)?.genres || "Nema zanrova"}
                <Button variant="contained" color="error" style={{ width: "100%", marginTop: "15px" }} onClick={() => handleObrisi(film.id)}>
                  OBRISI
                </Button>
              </Paper>
            ))}
          </Paper>
        </Container>
      )}
    </>
  );
}

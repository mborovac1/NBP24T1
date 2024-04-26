import React, { useEffect, useState } from "react";
import TextField from "@mui/material/TextField";
import SingleContent from "../SingleContent/SingleContent";
import "./Movies.css";
import Genres from "./Genres";
import { Button } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

const Movies = () => {
  const [filmovi, setFilmovi] = useState([]);
  const [selectedGenres, setSelectedGenres] = useState([]);
  const [genres, setGenres] = useState([]);
  const [allGenres, setAllGenres] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredMovies, setFilteredMovies] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/movies/")
      .then((res) => res.json())
      .then((result) => {
        setFilmovi(result);
      });

      fetch("http://localhost:8080/api/movieGenres/")
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setAllGenres(result);
      });

    filterMovies();
  }, [searchTerm, selectedGenres]);

  const handleSearchInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleGenreSelect = (genre) => {
    setSelectedGenres([...selectedGenres, genre]);
  };

  const handleGenreDeselect = (genre) => {
    setSelectedGenres(selectedGenres.filter((selected) => selected.id !== genre.id));
  };

 /*  const filterMovies = () => {
    const filteredMovies = filmovi.filter((movie) => {
      let onlyGenres = selectedGenres.map((el) => el.id);
      const movieGenreIds = allGenres.filter((mg) => mg.movieId === movie.id).map((mg) => mg.genreId);

        if (selectedGenres.length > 0) {
            const hasCommonGenre = movieGenreIds.some((movieGenreId) => {
                return selectedGenres.some((selectedGenre) => selectedGenre.id === movieGenreId);
            });
            return hasCommonGenre;
        }
      if (searchTerm !== "" && !movie.name.toLowerCase().includes(searchTerm.toLowerCase())) {
        return false;
      }

      return true;
    });

    setFilteredMovies(filteredMovies);
  };
 */

  const filterMovies = () => {
    const filteredMovies = filmovi.filter((movie) => {
      const movieGenreIds = allGenres.filter((mg) => mg.movieId === movie.id).map((mg) => mg.genreId);
  
      const hasAllSelectedGenres = selectedGenres.every((selectedGenre) => {
        return movieGenreIds.includes(selectedGenre.id);
      });
  
      if (searchTerm !== "" && !movie.name.toLowerCase().includes(searchTerm.toLowerCase())) {
        return false;
      }
  
      return hasAllSelectedGenres;
    });
  
    setFilteredMovies(filteredMovies);
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
          InputProps={{
            style: { color: "white" },
          }}
          InputLabelProps={{
            style: { color: "white" },
          }}
          value={searchTerm}
          onChange={handleSearchInputChange}
        />
        <Button variant="contained" style={{ backgroundColor: "white", marginLeft: 10 }}>
          <SearchIcon style={{ color: "black" }} />
        </Button>
      </div>

      <Genres selectedGenres={selectedGenres} setSelectedGenres={setSelectedGenres} genres={genres} setGenres={setGenres} onGenreSelect={handleGenreSelect} onGenreDeselect={handleGenreDeselect} />
      <div className="movies">
        {filteredContent.length > 0 ? (
          filteredContent.map((c) => (
            <div key={c.id}>
              <SingleContent id={c.id} naziv={c.name} trajanje={c.duration} opis={c.description} poster={c.posterPath} />
            </div>
          ))
        ) : (
          <p>
            <b>Ne postoje filmovi sa odabranim zanrovima.</b>
          </p>
        )}
      </div>
    </div>
  );
};

export default Movies;

import React, { useEffect, useState } from "react";
import SingleContent from "../SingleContent/SingleContent";
import "./Movies.css";
import axios from "axios";

const ForYou = () => {
  const [filmovi, setFilmovi] = useState([]);
  const [selectedGenres, setSelectedGenres] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredMovies, setFilteredMovies] = useState([]);

  const [korisnik, setKorisnik] = useState({});
  const email = localStorage.getItem("email");

  let [films, setFilms] = useState([]);
  const [nazivZanraArray, setNazivZanraArray] = useState([]);

  const [filteredFilms, setFilteredFilms] = useState([]);

  const fetchKorisnik = async () => {
    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
      const response = await axios.get(`${BASE_URL}/user/email/${email}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setKorisnik(response.data);
    } catch (error) {
      console.error("Failed to fetch users:", error);
    }
  };

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

  const fetchFilms = async () => {
    if (filmovi.length > 0) {
      setFilms(korisnik.karte.map((karta) => karta.film));

      // Filter out null values from films array
      const filteredFilms = films.filter((film) => film !== null);
      setFilteredFilms(filteredFilms);

      if (filteredFilms.length > 0) {
        setNazivZanraArray(
          filteredFilms.flatMap((film) => {
            if (film && film.zanrovi && film.zanrovi.length > 0) {
              return film.zanrovi.map((zanr) => zanr.nazivZanra);
            }
            return [];
          })
        );
      }
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      await fetchKorisnik();
      fetchFilmovi();
    };

    fetchData();
  }, [searchTerm, selectedGenres]);

  useEffect(() => {
    fetchFilms();
  }, [filmovi, korisnik]);

  useEffect(() => {
    filterMovies();
  }, [nazivZanraArray, films]);

  const filterMovies = () => {
    const filteredMovies = filmovi.filter((movie) => {
      // Filter based on selected genres
      if (nazivZanraArray.length > 0) {
        const allGenresCurr = movie.zanrovi?.map((el) => el.name) || [];
        const hasCommonGenre = nazivZanraArray.some((genre) => allGenresCurr.includes(genre));

        if (!hasCommonGenre) {
          return false;
        }
      }

      // Filter out movies from the `filteredFilms` array
      const hasMovie = filteredFilms.some((film) => film && film.name === movie.name);
      if (hasMovie) {
        return false;
      }

      return true;
    });
    if(filteredMovies.length !== filmovi.length )
        setFilteredMovies(filteredMovies);
    else
        setFilteredMovies([]);
  };

  return (
    <div>
      <h1 style={{ marginTop: "20px" }}>FILMOVI ZA VAS</h1>
      <div className="movies">
        {filteredMovies.length > 0 ? (
          filteredMovies.map((c) => (
            <div key={c.id}>
              <SingleContent id={c.id} naziv={c.name} trajanje={c.duration} opis={c.description} poster={c.posterPath} />
            </div>
          ))
        ) : (
          <p>
            <b>Ne postoje filmovi sa odabranim žanrovima.</b>
          </p>
        )}
      </div>
    </div>
  );
};

export default ForYou;

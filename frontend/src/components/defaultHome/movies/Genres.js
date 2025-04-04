import React, { useEffect } from "react";
import Chip from "@mui/material/Chip";
const apiUrl = process.env.REACT_APP_BASE_URL;

const Genres = ({ selectedGenres, setSelectedGenres, genres, setGenres, onGenreSelect, onGenreDeselect }) => {
  const handleAdd = (genre) => {
    onGenreSelect(genre);
    setSelectedGenres([...selectedGenres, genre]);
    setGenres(genres.filter((g) => g.id !== genre.id));
  };

  const handleRemove = (genre) => {
    onGenreDeselect(genre);
    setSelectedGenres(selectedGenres.filter((selected) => selected.id !== genre.id));
    setGenres([...genres, genre]);
  };

  useEffect(() => {
    fetch(`${apiUrl}/api/genres/`)
      .then((res) => res.json())
      .then((result) => {
        setGenres(result);
      });
  }, []);

  return (
    <div style={{ padding: "6px 0" }}>
      {selectedGenres.map((genre) => (
        <Chip style={{ margin: 2, backgroundColor: "black", color: "white", fontSize: "16px", padding: "5px" }} label={genre.name} key={genre.id} color="primary" clickable onDelete={() => handleRemove(genre)} />
      ))}
      {genres.map((genre) => (
        <Chip style={{ margin: 2, backgroundColor: "white", color: "black", fontSize: "16px", padding: "5px" }} label={genre.name} key={genre.id} clickable size="small" onClick={() => handleAdd(genre)} />
      ))}
    </div>
  );
};

export default Genres;

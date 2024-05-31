import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Radio, RadioGroup, FormControlLabel } from "@mui/material";
import axios from "axios";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { MultiInputTimeRangeField } from "@mui/x-date-pickers-pro/MultiInputTimeRangeField";
import dayjs from "dayjs";
import utc from "dayjs/plugin/utc";
import timezone from "dayjs/plugin/timezone";

dayjs.extend(utc);
dayjs.extend(timezone);

const Appointments = () => {
    const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
    const [filmovi, setFilmovi] = useState([]);
    const [halls, setHalls] = useState([]);
    const [selectedHall, setSelectedHall] = useState({});
    const [dates, setDates] = useState({});
    const [times, setTimes] = useState({});
    const [isLogged, setIsLogged] = useState(true);

    const handleHallChange = (filmId, hallId) => {
        setSelectedHall((prevState) => ({
            ...prevState,
            [filmId]: hallId,
        }));
    };

    const handleAddAppointment = async (filmId) => {
        const hallId = selectedHall[filmId];
        const [startTime, endTime] = times[filmId] || [dayjs(), dayjs()];

        if (hallId && dates[filmId] && startTime && endTime) {
            const startDateTime = dayjs(dates[filmId]).hour(startTime.hour()).minute(startTime.minute()).second(0).format("YYYY-MM-DDTHH:mm:ss");
            const endDateTime = dayjs(dates[filmId]).hour(endTime.hour()).minute(endTime.minute()).second(0).format("YYYY-MM-DDTHH:mm:ss");

            const appointment = {
                movieId: filmId,
                hallId: hallId,
                startTime: startDateTime,
                endTime: endDateTime,
            };

            try {
                const BASE_URL = process.env.REACT_APP_BASE_URL;
                const token = localStorage.getItem("access_token");

                const response = await axios.post(`${BASE_URL}/api/appointments/add`, appointment, {
                    headers: { Authorization: `Bearer ${token}` },
                });

                console.log("Appointment added:", response.data);
                alert("Appointment successfully added");

            } catch (error) {
                console.error("Failed to add appointment:", error);
                console.error("Error details:", error.response?.data);
            }
        } else {
            console.error("Please ensure all fields are selected.");
        }
    };

    const handleDateChange = (filmId, newValue) => {
        setDates((prevState) => ({
            ...prevState,
            [filmId]: newValue,
        }));
    };

    const handleTimeChange = (filmId, newValue) => {
        setTimes((prevState) => ({
            ...prevState,
            [filmId]: newValue,
        }));
    };

    useEffect(() => {
        const fetchFilmovi = async () => {
            const token = localStorage.getItem("access_token");
            if (!token) {
                setIsLogged(false);
                return;
            }

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

        const fetchHalls = async () => {
            const token = localStorage.getItem("access_token");
            if (!token) {
                setIsLogged(false);
                return;
            }
            try {
                const BASE_URL = process.env.REACT_APP_BASE_URL;
                const response = await axios.get(`${BASE_URL}/api/halls/`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log("HALLS", response.data);
                setHalls(response.data);
            } catch (error) {
                console.error("Failed to fetch halls:", error);
            }
        };

        fetchHalls();
        fetchFilmovi();
    }, []);

    return (
        <>
            {isLogged && (
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <Container>
                        <h1 style={{ color: "white" }}>Termini projekcija</h1>
                        <Paper elevation={3} style={paperStyle}>
                            {filmovi.map((film) => (
                                <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left" }} key={film.id}>
                                    Id: {film.id} <br />
                                    Naziv: {film.name} <br />
                                    Trajanje: {film.duration} <br />
                                    <div>
                                        Sale:
                                        <RadioGroup name={`halls-${film.id}`} value={selectedHall[film.id] || ""} onChange={(e) => handleHallChange(film.id, e.target.value)}>
                                            {halls.map((hall) => (
                                                <div className='hall-choice' style={{ display: 'flex' }}>
                                                    <FormControlLabel key={hall.id} value={hall.id} control={<Radio />} label={hall.number} />
                                                    <p>Sala {hall.hallNumber}</p>
                                                </div>
                                            ))}
                                        </RadioGroup>
                                    </div>
                                    <div>
                                        Odaberite termin:
                                        <DemoContainer components={["DatePicker"]}>
                                            <DatePicker label="Datum" value={dates[film.id] || dayjs()} onChange={(newValue) => handleDateChange(film.id, newValue)} minDate={dayjs()} />
                                        </DemoContainer>
                                    </div>
                                    <div>
                                        <DemoContainer components={["MultiInputTimeRangeField"]}>
                                            <MultiInputTimeRangeField
                                                value={times[film.id] || [dayjs(), dayjs()]}
                                                onChange={(newValue) => handleTimeChange(film.id, newValue)}
                                                slotProps={{
                                                    textField: ({ position }) => ({
                                                        label: position === "start" ? "Početak" : "Kraj",
                                                    }),
                                                }}
                                            />
                                        </DemoContainer>
                                    </div>
                                    <Button variant="contained" color="primary" onClick={() => handleAddAppointment(film.id)}>
                                        Add Appointment
                                    </Button>
                                </Paper>
                            ))}
                        </Paper>
                    </Container>
                </LocalizationProvider>
            )}
        </>
    );
};

export default Appointments;

import React from "react";
import { Container, Paper, Button, Typography } from "@mui/material";
import axios from "axios";

const GenerateReport = () => {
  const handleGenerateReport = async (reportType) => {
    if (reportType === "last7Days") {
      await generateReport("/api/movies/report/last-7-days/pdf", "movies_report_last_7_days.pdf");
    } else if (reportType === "price") {
      alert("Price report generation is not implemented yet.");
    }
  };

  const generateReport = async (endpoint, fileName) => {
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL;
      const response = await axios.get(`${BASE_URL}${endpoint}`, {
        responseType: "blob",
      });

      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", fileName);
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (error) {
      console.error("Error generating the report", error);
    }
  };

  return (
    <Container>
      <Paper elevation={3} style={{ padding: "50px 20px", margin: "20px auto", textAlign: "center" }}>
        <Typography variant="h4" gutterBottom>
          Odaberi izvještaj za generisanje
        </Typography>
        <Button
          variant="contained"
          color="primary"
          onClick={() => handleGenerateReport("last7Days")}
          style={{ margin: "20px", fontSize: "16px", fontWeight: "bold" }}
        >
          Generiši izvještaj o filmovima za posljednjih 7 dana
        </Button>
        <Button
          variant="contained"
          color="secondary"
          onClick={() => handleGenerateReport("price")}
          style={{ margin: "20px", fontSize: "16px", fontWeight: "bold" }}
        >
          Generiši izvještaj o cijenama (USKORO)
        </Button>
      </Paper>
    </Container>
  );
};

export default GenerateReport;

import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import { Link } from "react-router-dom";
import axios from "axios";
import MovieIcon from "@mui/icons-material/Movie";

function AppbarAdmin() {
  const [anchorElNav, setAnchorElNav] = React.useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleLogout = async (e) => {
    const BASE_URL = process.env.REACT_APP_BASE_URL;
    const token = localStorage.getItem("access_token");
    const email = localStorage.getItem("email");

    await axios.post(`${BASE_URL}/api/auth/logout/${email}`, {
      headers: { Authorization: `Bearer ${token}` },
    });

    localStorage.removeItem("access_token");
    localStorage.removeItem("refresh_token");
    window.location.href = "/";
  };

  return (
    <AppBar position="static" style={{ backgroundColor: "#282c34" }}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <a className="ikona" href="/homeAdmin">
            <MovieIcon size="small" sx={{ display: { xs: "none", md: "flex" }, mr: 1 }}></MovieIcon>
          </a>

          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton size="large" aria-label="account of current user" aria-controls="menu-appbar" aria-haspopup="true" onClick={handleOpenNavMenu} color="inherit">
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: "block", md: "none" },
              }}
            >
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/eventsAdmin">
                <Typography textAlign="center">Događaji</Typography>
              </MenuItem>

              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/aboutUsAdmin">
                <Typography textAlign="center">O nama</Typography>
              </MenuItem>

              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/addMovie">
                <Typography textAlign="center">Dodavanje filma</Typography>
              </MenuItem>
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/overviewMovies">
                <Typography textAlign="center">Pregled filmova</Typography>
              </MenuItem>
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/overviewMembership">
                <Typography textAlign="center">Upravljanje članarinama</Typography>
              </MenuItem>
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/overviewUser">
                <Typography textAlign="center">Pregled korisnika</Typography>
              </MenuItem>
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/appointments">
                <Typography textAlign="center">Termini projekcija</Typography>
              </MenuItem>
              <MenuItem onClick={handleCloseNavMenu} component={Link} to="/generateReport">
                <Typography textAlign="center">Generiši izvještaj</Typography>
              </MenuItem>
            </Menu>
          </Box>
          <Typography
            variant="h5"
            noWrap
            component="a"
            href="/homeAdmin"
            sx={{
              mr: 2,
              display: { xs: "flex", md: "none" },
              flexGrow: 1,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            HOME
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            <Button onClick={handleCloseNavMenu} component={Link} to="/eventsAdmin" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Događaji
            </Button>

            <Button onClick={handleCloseNavMenu} component={Link} to="/aboutUsAdmin" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              O nama
            </Button>

            <Button onClick={handleCloseNavMenu} component={Link} to="/addMovie" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Dodavanje filma
            </Button>

            <Button onClick={handleCloseNavMenu} component={Link} to="/overviewMovies" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Pregled filmova
            </Button>

            <Button onClick={handleCloseNavMenu} component={Link} to="/membershipOverview" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Upravljanje članarinama
            </Button>

            <Button onClick={handleCloseNavMenu} component={Link} to="/overviewUser" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Pregled korisnika
            </Button>
            <Button onClick={handleCloseNavMenu} component={Link} to="/appointments" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Termini projekcija
            </Button>

            <Button onClick={handleCloseNavMenu} component={Link} to="/generateReport" sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Generiši izvještaj
            </Button>
          </Box>
          <Box style={{ display: "flex" }}>
            <Button onClick={handleLogout} sx={{ my: 2, color: "white", display: "block", marginLeft: "50px", fontWeight: "bold", fontSize: "16px" }}>
              Logout
            </Button>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export default AppbarAdmin;

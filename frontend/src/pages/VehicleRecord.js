import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Paper, Grid, CircularProgress, Alert,
  Table, TableBody, TableCell, TableContainer, TableHead, TableRow,TextField
} from '@mui/material';

const VehicleRecords = () => {
  const [vehicles, setVehicles] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    const fetchVehicles = async () => {
      try {
        // This URL now correctly points to your new endpoint
        const response = await fetch('http://localhost:8080/api/vehicles');

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        setVehicles(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    fetchVehicles();
  }, []);

  if (isLoading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', mt: 4 }}>
        <CircularProgress />
        <Typography sx={{ ml: 2 }}>Loading Vehicle Data...</Typography>
      </Box>
    );
  }

  if (error) {
    return (
      <Alert severity="error" sx={{ mt: 2 }}>
        <strong>Failed to load data.</strong> Please ensure the backend server is running and that the 
        <code>GET /api/vehicles</code> endpoint exists.
        <br />
        Error details: {error}
      </Alert>
    );
  }


  const filteredVehicles = vehicles.filter((vehicle) =>
  vehicle.vehicleNumber.toLowerCase().includes(searchQuery.toLowerCase())
);

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Vehicle Records
      </Typography>

      {/* ---  SEARCH BAR  --- */}
      <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
      <TextField
        size="small"
        placeholder="Search vehicles..."
        variant="outlined"
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        sx={{ width: 250 }}
      />
    </Box>

      {/* --- Vehicle Count Grid --- */}
      <Grid container spacing={3} sx={{ mb: 4 }}>
        <Grid item xs={12} sm={6} md={4}>
          <Paper
            elevation={3}
            sx={{
              p: 3,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              backgroundColor: 'primary.main',
              color: 'white',
            }}
          >
            <Typography variant="h6" component="h2">
              Total Vehicles
            </Typography>
            <Typography variant="h3" component="p" sx={{ fontWeight: 'bold' }}>
              {vehicles.length}
            </Typography>
          </Paper>
        </Grid>
      </Grid>

      {/* --- Vehicle Details Table --- */}
      <Typography variant="h5" gutterBottom sx={{ mt: 2 }}>
        Vehicle Details
      </Typography>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="vehicle details table">
          <TableHead sx={{ backgroundColor: 'action.hover' }}>
            <TableRow>
              <TableCell sx={{ fontWeight: 'bold' }}>Vehicle Number</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Vehicle Type</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Make</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Fuel Type</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredVehicles.length > 0 ? (
              filteredVehicles.map((vehicle) => (
              <TableRow
                key={vehicle.id} // Assuming your VehicleDetail has an 'id' field
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {vehicle.vehicleNumber}
                </TableCell>
                <TableCell>{vehicle.vehicleType}</TableCell>
                <TableCell>{vehicle.make}</TableCell>
                <TableCell>{vehicle.fuelType}</TableCell>
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={4} align="center">
                    No vehicles found matching your search.
              </TableCell>
            </TableRow>

          )}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default VehicleRecords;
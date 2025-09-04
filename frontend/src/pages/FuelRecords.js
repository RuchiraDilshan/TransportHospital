import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Paper, Grid, CircularProgress, Alert,
  Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField,
  Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle // <-- NEW Imports
} from '@mui/material';

const FuelRecords = () => {
  const [fuelRecords, setFuelRecords] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');

  // <-- NEW State for the dialog form
  const [open, setOpen] = useState(false);
  const [newRecord, setNewRecord] = useState({
    vehicleNumber: '',
    refilleddate: '',
    refilledquantity: '',
    fuelprice: '',
    odometerreading: ''
  });

  // We wrap the fetch logic in a function so we can call it again after adding a new record
  const fetchFuelRecords = async () => {
    try {
      setIsLoading(true); // Show loading spinner during re-fetch
      const response = await fetch('http://localhost:8080/api/fuel-details');
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      setFuelRecords(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchFuelRecords();
  }, []);

  // <-- NEW Functions to handle the dialog
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    // Reset form when closing
    setNewRecord({
      vehicleNumber: '',
      refilleddate: '',
      refilledquantity: '',
      fuelprice: '',
      odometerreading: ''
    });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewRecord(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Your backend needs the 'vehicleNumber' as a query parameter
    // and the rest of the data in the body
    const { vehicleNumber, ...fuelData } = newRecord;

    try {
      const response = await fetch(`http://localhost:8080/api/fuel-details?vehicleNumber=${vehicleNumber}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(fuelData),
      });

      if (!response.ok) {
        // Handle server-side errors
        const errorData = await response.json();
        throw new Error(errorData.message || 'Failed to add fuel record.');
      }

      // If successful:
      handleClose(); // Close the dialog
      fetchFuelRecords(); // Re-fetch all records to update the table
      
    } catch (err) {
      console.error('Submission Error:', err);
      // Here you could set an error state to show a message to the user
    }
  };


  if (isLoading && fuelRecords.length === 0) { // Only show full-page loader on initial load
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', mt: 4 }}>
        <CircularProgress />
        <Typography sx={{ ml: 2 }}>Loading Fuel Data...</Typography>
      </Box>
    );
  }

  if (error) {
    return <Alert severity="error" sx={{ mt: 2 }}>Error: {error}</Alert>;
  }

  const filteredFuelRecords = fuelRecords.filter((record) =>
    record.vehicle.vehicleNumber.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
        <Typography variant="h4" gutterBottom>
          Fuel Records
        </Typography>
        {/* // <-- NEW "Add New Record" Button */}
        <Button variant="contained" onClick={handleClickOpen}>
          Add New Record
        </Button>
      </Box>
      
      {/* Search Bar, Count Grid, and Table remain mostly the same */}
      {/* ... */}

      {/* ---  SEARCH BAR --- */}
      <Paper sx={{ p: 2, mb: 3 }}>
        <TextField fullWidth label="Search by Vehicle Number" variant="outlined" value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)} />
      </Paper>
      
      {/* ... Count Grid and Table ... */}
      <TableContainer component={Paper}>
        <Table>
          {/* ... Table Head ... */}
          <TableBody>
            {/* The table will now automatically update after you add a record */}
            {filteredFuelRecords.map((record) => (
              <TableRow key={record.fuelId}>
                {/* ... your table cells ... */}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* // <-- NEW Dialog Form */}
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Add New Fuel Record</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Please fill out the details for the new fuel record.
          </DialogContentText>
          <TextField autoFocus margin="dense" name="vehicleNumber" label="Vehicle Number" type="text" fullWidth variant="standard" value={newRecord.vehicleNumber} onChange={handleInputChange} />
          <TextField margin="dense" name="refilleddate" label="Refilled Date" type="date" fullWidth variant="standard" value={newRecord.refilleddate} onChange={handleInputChange} InputLabelProps={{ shrink: true }} />
          <TextField margin="dense" name="refilledquantity" label="Refilled Quantity (L)" type="number" fullWidth variant="standard" value={newRecord.refilledquantity} onChange={handleInputChange} />
          <TextField margin="dense" name="fuelprice" label="Fuel Price" type="number" fullWidth variant="standard" value={newRecord.fuelprice} onChange={handleInputChange} />
          <TextField margin="dense" name="odometerreading" label="Odometer Reading" type="number" fullWidth variant="standard" value={newRecord.odometerreading} onChange={handleInputChange} />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSubmit}>Save Record</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default FuelRecords;
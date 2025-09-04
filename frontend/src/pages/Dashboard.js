import React from 'react';
import { Box, Typography, Paper } from '@mui/material';
import  Grid  from '@mui/material/Grid'; // Correct import for v7

const Dashboard = () => {
  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Dashboard
      </Typography>
      <Typography variant="body1" color="textSecondary" gutterBottom>
        Welcome to National Hospital Kandy Vehicle Management System
      </Typography>
      
      <Grid container spacing={3} sx={{ mt: 2 }}>
        <Grid size={{ xs: 12, sm: 6, md: 6 }}>
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography variant="h6">Total Vehicles</Typography>
            <Typography variant="h4" color="primary">0</Typography>
          </Paper>
        </Grid>
        <Grid size={{ xs: 12, sm: 6, md: 6 }}>
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography variant="h6">Fuel Records</Typography>
            <Typography variant="h4" color="secondary">0</Typography>
          </Paper>
        </Grid>
        <Grid size={{ xs: 12, sm: 6, md: 6 }}>
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography variant="h6">Active Today</Typography>
            <Typography variant="h4" color="success.main">0</Typography>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Dashboard;
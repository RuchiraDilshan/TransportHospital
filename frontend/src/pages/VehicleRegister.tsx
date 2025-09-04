import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const VehicleRegister: React.FC = () => {
  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Vehicle Register
      </Typography>
      <Paper sx={{ p: 3 }}>
        <Typography>Vehicle registration form will be here</Typography>
      </Paper>
    </Box>
  );
};

export default VehicleRegister;
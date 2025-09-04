import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const FuelRecords: React.FC = () => {
  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Fuel Records
      </Typography>
      <Paper sx={{ p: 3 }}>
        <Typography>Fuel records management will be here</Typography>
      </Paper>
    </Box>
  );
};

export default FuelRecords;
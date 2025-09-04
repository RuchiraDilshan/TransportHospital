import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Box } from '@mui/material';
import Sidebar from './components/Sidebar';
import VehicleRecord from './pages/VehicleRecord';
import FuelRecords from "./pages/FuelRecords";
import Dashboard from './pages/Dashboard';

function App() {
  return (
    <Router>
      <Box sx={{ display: 'flex' }}>
        <Sidebar />
        <Box component="main" sx={{ flexGrow: 1, p: 3, minHeight: '100vh' }}>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/vehicles" element={<VehicleRecord />} />
            <Route path="/fuel" element={<FuelRecords />} />
          </Routes>
        </Box>
      </Box>
    </Router>
  );
}

export default App;
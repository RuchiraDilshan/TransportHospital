import React from 'react';
import { 
  Drawer, 
  List, 
  ListItem, 
  ListItemIcon, 
  ListItemText, 
  Box, 
  Typography,
  useTheme,
  useMediaQuery 
} from '@mui/material';
import { useNavigate, useLocation } from 'react-router-dom';
import DirectionsCarIcon from '@mui/icons-material/DirectionsCar';
import LocalGasStationIcon from '@mui/icons-material/LocalGasStation';
import HomeIcon from '@mui/icons-material/Home';

// Sidebar items configuration
const sidebarItems = [
  { 
    text: 'Dashboard', 
    icon: <HomeIcon />, 
    path: '/' 
  },
  { 
    text: 'Vehicle Register', 
    icon: <DirectionsCarIcon />, 
    path: '/vehicles' 
  },
  { 
    text: 'Fuel Records', 
    icon: <LocalGasStationIcon />, 
    path: '/fuel' 
  },
];

const Sidebar: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('md'));

  return (
    <Drawer
      variant="permanent"
      sx={{
        width: 240,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
          width: 240,
          boxSizing: 'border-box',
          backgroundColor: '#2c3e50',
          color: 'white',
          border: 'none',
        },
      }}
    >
      {/* Header */}
      <Box 
        sx={{ 
          p: 3, 
          textAlign: 'center', 
          borderBottom: '1px solid rgba(255,255,255,0.1)',
          backgroundColor: '#34495e'
        }}
      >
        <Typography variant="h6" sx={{ color: 'white', fontWeight: 'bold' }}>
          🚑 Transport Hospital
        </Typography>
        <Typography variant="body2" sx={{ color: '#bdc3c7', mt: 1 }}>
          Vehicle Management
        </Typography>
      </Box>
      
      {/* Navigation Items */}
      <List sx={{ mt: 2 }}>
        {sidebarItems.map((item) => (
          <ListItem
            key={item.text}
            onClick={() => navigate(item.path)}
             sx={{
             backgroundColor: location.pathname === item.path ? '#3498db' : 'transparent',
             '&:hover': {
            backgroundColor: location.pathname === item.path ? '#2980b9' : '#34495e',
             },
             margin: '8px 12px',
             borderRadius: '8px',
            transition: 'all 0.3s ease',
             cursor: 'pointer', // Add cursor pointer for clickable appearance
          }}
          >
            <ListItemIcon sx={{ color: 'white', minWidth: '40px' }}>
              {item.icon}
            </ListItemIcon>
            <ListItemText 
              primary={item.text} 
              sx={{
                '& .MuiListItemText-primary': {
                  fontWeight: location.pathname === item.path ? 'bold' : 'normal',
                }
              }}
            />
          </ListItem>
        ))}
      </List>

      {/* Footer */}
      <Box sx={{ mt: 'auto', p: 2, textAlign: 'center', borderTop: '1px solid rgba(255,255,255,0.1)' }}>
        <Typography variant="caption" sx={{ color: '#95a5a6' }}>
          Transport System v1.0
        </Typography>
      </Box>
    </Drawer>
  );
};

export default Sidebar;
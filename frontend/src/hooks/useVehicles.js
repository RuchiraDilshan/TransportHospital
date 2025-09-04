import { useState, useEffect } from 'react';

// A custom hook is just a function that starts with "use"
export const useVehicles = () => {
  // All the state related to vehicles now lives here
  const [vehicles, setVehicles] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // We only want to fetch data once, so we use a flag
    let isMounted = true; 

    const fetchVehicles = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/vehicles');

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        // Only update state if the component is still mounted
        if (isMounted) {
          setVehicles(data);
        }
      } catch (err) {
        if (isMounted) {
          setError(err.message);
        }
      } finally {
        if (isMounted) {
          setIsLoading(false);
        }
      }
    };

    fetchVehicles();

    // Cleanup function: runs when the component unmounts
    return () => {
      isMounted = false;
    };
  }, []); // Empty array ensures this runs only once

  // The hook returns the data and state for any component to use
  return { vehicles, isLoading, error };
};
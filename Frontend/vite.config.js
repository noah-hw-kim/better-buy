import { defineConfig } from 'vite'; // Import defineConfig

export default defineConfig({
    server: {
      proxy: {
        '/api': 'https://better-buy-backend.onrender.com', // Backend API endpoint
      },
    },
  });
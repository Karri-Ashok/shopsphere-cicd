import api from './api';

export const userService = {
  getProfile: async () => {
    const response = await api.get('/api/users/profile');
    return response.data;
  },

  updateProfile: async (userData) => {
    const response = await api.put('/api/users/profile', userData);
    return response.data;
  }
};

import api from './api';

export const productService = {
  getAllProducts: async () => {
    const response = await api.get('/api/products');
    return response.data;
  },

  getProductById: async (id) => {
    const response = await api.get(`/api/products/${id}`);
    return response.data;
  },

  searchProducts: async (query) => {
    const response = await api.get(`/api/products/search?q=${query}`);
    return response.data;
  }
};

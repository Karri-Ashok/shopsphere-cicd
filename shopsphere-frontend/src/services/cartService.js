import api from './api';

export const cartService = {
  getCart: async () => {
    const response = await api.get('/api/cart');
    return response.data;
  },

  addToCart: async (productId, quantity) => {
    const response = await api.post('/api/cart/add', { productId, quantity });
    return response.data;
  },

  removeFromCart: async (productId) => {
    const response = await api.delete(`/api/cart/remove/${productId}`);
    return response.data;
  },

  updateCartItem: async (productId, quantity) => {
    const response = await api.put('/api/cart/update', { productId, quantity });
    return response.data;
  },

  clearCart: async () => {
    const response = await api.delete('/api/cart/clear');
    return response.data;
  }
};

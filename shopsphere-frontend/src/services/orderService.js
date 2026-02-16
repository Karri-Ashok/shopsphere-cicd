import api from './api';

export const orderService = {
  createOrder: async (orderData) => {
    const response = await api.post('/api/orders/create', orderData);
    return response.data;
  },

  getOrders: async () => {
    const response = await api.get('/api/orders');
    return response.data;
  },

  getOrderById: async (id) => {
    const response = await api.get(`/api/orders/${id}`);
    return response.data;
  }
};

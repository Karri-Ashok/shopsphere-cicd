import { createContext, useContext, useState, useEffect } from 'react';
import { cartService } from '../services/cartService';
import { useAuth } from './AuthContext';

const CartContext = createContext(null);

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState(null);
  const [cartCount, setCartCount] = useState(0);
  const { isAuthenticated } = useAuth();

  const fetchCart = async () => {
    if (!isAuthenticated) {
      setCart(null);
      setCartCount(0);
      return;
    }

    try {
      const data = await cartService.getCart();
    setCart({ items: data });
    const count = data?.reduce((sum, item) => sum + item.quantity, 0) || 0;
    setCartCount(count);
    } catch (error) {
      console.error('Error fetching cart:', error);
    }
  };

  useEffect(() => {
    fetchCart();
  }, [isAuthenticated]);

  const addToCart = async (productId, quantity) => {
    await cartService.addToCart(productId, quantity);
    await fetchCart();
  };

  const removeFromCart = async (productId) => {
    await cartService.removeFromCart(productId);
    await fetchCart();
  };

  const updateCartItem = async (productId, quantity) => {
    await cartService.updateCartItem(productId, quantity);
    await fetchCart();
  };

  const clearCart = async () => {
    await cartService.clearCart();
    await fetchCart();
  };

  const value = {
    cart,
    cartCount,
    fetchCart,
    addToCart,
    removeFromCart,
    updateCartItem,
    clearCart
  };

  return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
};

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
};

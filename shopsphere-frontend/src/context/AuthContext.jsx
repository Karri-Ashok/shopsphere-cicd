import { createContext, useContext, useState, useEffect } from 'react';
import { authService } from '../services/authService';
import { userService } from '../services/userService';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // 🔹 Initialize authentication on page load
  useEffect(() => {
    const initializeAuth = async () => {
      const token = authService.getToken();

      if (token) {
        try {
          const profile = await userService.getProfile();
          setUser(profile);
        } catch (error) {
          authService.logout();
          setUser(null);
        }
      }

      setLoading(false);
    };

    initializeAuth();
  }, []);

  // 🔹 Login
  const login = async (credentials) => {
    const response = await authService.login(credentials);
    const profile = await userService.getProfile();
    setUser(profile);
    return response;
  };

  // 🔹 Register
  const register = async (data) => {
    return await authService.register(data);
  };

  // 🔹 Logout
  const logout = () => {
    authService.logout();
    setUser(null);
  };

  const isAuthenticated = !!localStorage.getItem('token');

  return (
    <AuthContext.Provider
      value={{
        user,
        login,
        register,
        logout,
        loading,
        isAuthenticated
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};

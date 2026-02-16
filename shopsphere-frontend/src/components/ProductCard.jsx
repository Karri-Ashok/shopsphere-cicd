import { ShoppingCart } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../context/CartContext';
import toast from 'react-hot-toast';

const ProductCard = ({ product }) => {
  const navigate = useNavigate();
  const { addToCart } = useCart();

  const handleAddToCart = async (e) => {
    e.stopPropagation();

    const token = localStorage.getItem('token');

    if (!token) {
      toast.error('Please login to add items to cart');
      navigate('/login');
      return;
    }

    try {
      await addToCart(product.id, 1);
      toast.success('Product added to cart!');
      navigate('/cart');
    } catch (error) {
      toast.error('Failed to add to cart');
    }
  };

  return (
    <div className="card p-4 flex flex-col h-full cursor-pointer">

      <div className="relative overflow-hidden rounded-lg mb-4 h-48">
        <img
          src={product.imageUrl}
          alt={product.name}
          className="w-full h-full object-cover hover:scale-110 transition-transform duration-300"
        />
      </div>

      <h3 className="text-lg font-semibold text-gray-900 mb-2 min-h-[48px]">
        {product.name}
      </h3>

      <p className="text-sm text-gray-600 mb-3 min-h-[40px]">
        {product.description}
      </p>

      <div className="flex items-center justify-between mb-3">
        <span className="text-2xl font-bold text-primary-600">
          ${product.price}
        </span>
        {product.stock > 0 ? (
          <span className="text-sm text-green-600 font-medium">In Stock</span>
        ) : (
          <span className="text-sm text-red-600 font-medium">Out of Stock</span>
        )}
      </div>

      <div className="mt-auto">
        <button
          onClick={handleAddToCart}
          disabled={product.stock === 0}
          className="w-full btn-primary flex items-center justify-center space-x-2"
        >
          <ShoppingCart className="h-4 w-4" />
          <span>Add to Cart</span>
        </button>
      </div>

    </div>
  );
};

export default ProductCard;

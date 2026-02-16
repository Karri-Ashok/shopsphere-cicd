import { useNavigate } from 'react-router-dom';
import { ShoppingBag, TrendingUp, Shield, Truck } from 'lucide-react';

const Home = () => {
  const navigate = useNavigate();

  const features = [
    {
      icon: <ShoppingBag className="h-8 w-8 text-primary-600" />,
      title: 'Wide Selection',
      description: 'Browse thousands of products across multiple categories'
    },
    {
      icon: <TrendingUp className="h-8 w-8 text-primary-600" />,
      title: 'Best Prices',
      description: 'Get the best deals and competitive prices on all products'
    },
    {
      icon: <Shield className="h-8 w-8 text-primary-600" />,
      title: 'Secure Shopping',
      description: 'Your data is protected with industry-standard security'
    },
    {
      icon: <Truck className="h-8 w-8 text-primary-600" />,
      title: 'Fast Delivery',
      description: 'Quick and reliable shipping to your doorstep'
    }
  ];

  return (
    <div className="min-h-screen">
      <section className="bg-gradient-to-r from-primary-600 to-primary-800 text-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-5xl md:text-6xl font-bold mb-6">
              Welcome to ShopSphere
            </h1>
            <p className="text-xl md:text-2xl mb-8 text-primary-100">
              Your Ultimate Shopping Destination
            </p>
            <p className="text-lg mb-10 max-w-2xl mx-auto text-primary-50">
              Discover amazing products at unbeatable prices. Shop with confidence and enjoy fast, secure delivery.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <button
                onClick={() => navigate('/products')}
                className="bg-white text-primary-600 px-8 py-3 rounded-lg font-semibold hover:bg-gray-100 transition-colors duration-200"
              >
                Shop Now
              </button>
              <button
                onClick={() => navigate('/register')}
                className="bg-primary-700 text-white px-8 py-3 rounded-lg font-semibold hover:bg-primary-800 transition-colors duration-200 border-2 border-white"
              >
                Get Started
              </button>
            </div>
          </div>
        </div>
      </section>

      <section className="py-16 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h2 className="text-3xl font-bold text-center mb-12 text-gray-900">
            Why Choose ShopSphere?
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {features.map((feature, index) => (
              <div key={index} className="card p-6 text-center">
                <div className="flex justify-center mb-4">
                  {feature.icon}
                </div>
                <h3 className="text-xl font-semibold mb-3 text-gray-900">
                  {feature.title}
                </h3>
                <p className="text-gray-600">
                  {feature.description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="py-16 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h2 className="text-3xl font-bold mb-6 text-gray-900">
            Ready to Start Shopping?
          </h2>
          <p className="text-lg text-gray-600 mb-8 max-w-2xl mx-auto">
            Join thousands of satisfied customers and experience the best online shopping platform.
          </p>
          <button
            onClick={() => navigate('/products')}
            className="btn-primary px-8 py-3 text-lg"
          >
            Browse Products
          </button>
        </div>
      </section>
    </div>
  );
};

export default Home;

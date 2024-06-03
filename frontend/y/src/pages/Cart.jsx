import React from 'react';
import { CartItemsList, CartTotals, SectionTitle } from '../components/index.js';
import { Link, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { toast } from 'react-toastify';

const Cart = () => {
  const navigate = useNavigate();
  const loginState = useSelector((state) => state.auth.isLoggedIn);
  const { cartItems } = useSelector((state) => state.cart);

  const handleOrderNow = () => {
    if (cartItems.length === 0) {
      toast.error("Your cart is empty");
    } else if (!loginState) {
      navigate("/checkout"); // or any other route for checkout
    } else {
      navigate("/payment");
    }
  };

  return (
      <>
        <SectionTitle title="Cart" path="Home | Cart" />
        <div className='mt-8 grid gap-8 lg:grid-cols-12 max-w-7xl mx-auto px-10'>
          <div className='lg:col-span-8'>
            <CartItemsList />
          </div>
          <div className='lg:col-span-4 lg:pl-4'>
            <CartTotals />
            <button onClick={handleOrderNow} className='btn bg-blue-600 hover:bg-blue-500 text-white btn-block mt-8'>
              Order Now
            </button>
            {!loginState && (
                <p className="text-sm text-gray-600 mt-2">
                  * Please note: You are currently not logged in. You can proceed with the order, but we recommend logging in for a better experience.
                </p>
            )}
          </div>
        </div>
      </>
  );
};

export default Cart;

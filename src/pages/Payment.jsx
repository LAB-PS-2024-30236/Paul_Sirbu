import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { toast } from 'react-toastify';
import { loadStripe } from '@stripe/stripe-js';
import { Elements, CardElement, useStripe, useElements } from '@stripe/react-stripe-js';
import { SectionTitle } from '../components';
import axios from 'axios';

const stripePromise = loadStripe('pk_test_51PKzXqCMQGEPYZPHb4mSY03mq6f63tQjA3h9y4WGpWaf5ZlJGgMlNBK8QAVUUL7k2POrxpodcGhP8pLfVTqEya9P00ur7sR45a');

const PaymentForm = ({ totalAmount }) => {
    const navigate = useNavigate();
    const stripe = useStripe();
    const elements = useElements();
    const [cardDetails, setCardDetails] = useState({
        name: '',
    });
    const [formErrors, setFormErrors] = useState({
        name: '',
    });

    const handleSubmit = async (event) => {
        event.preventDefault();
        const isValid = validateForm();
        if (!isValid) return;

        try {
            const { data: clientSecret } = await axios.post('http://localhost:8080/payment', {
                ...cardDetails,
                amount: totalAmount
            });

            const cardElement = elements.getElement(CardElement);

            const { error, paymentIntent } = await stripe.confirmCardPayment(clientSecret, {
                payment_method: {
                    card: cardElement,
                    billing_details: {
                        name: cardDetails.name,
                    },
                },
            });

            if (error) {
                toast.error('Payment failed. Please try again.');
            } else if (paymentIntent.status === 'succeeded') {
                toast.success('Payment successful!');
                navigate('/thank-you');
            }
        } catch (error) {
            toast.error('Payment failed. Please try again.');
        }
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setCardDetails({
            ...cardDetails,
            [name]: value,
        });
    };

    const validateForm = () => {
        let isValid = true;
        const errors = {};

        if (!cardDetails.name.trim()) {
            errors.name = 'Name is required';
            isValid = false;
        }

        setFormErrors(errors);
        return isValid;
    };

    return (
        <div className="bg-white rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-semibold mb-6 text-center">Secure Payment</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label htmlFor="name" className="block text-sm font-medium text-gray-700">Name on card</label>
                    <input
                        type="text"
                        name="name"
                        id="name"
                        value={cardDetails.name}
                        placeholder="Name on card"
                        onChange={handleChange}
                        required
                        className="input input-bordered w-full mt-1"
                    />
                    {formErrors.name && <p className="text-red-500 text-xs mt-1">{formErrors.name}</p>}
                </div>
                <div>
                    <label htmlFor="card-element" className="block text-sm font-medium text-gray-700">Card details</label>
                    <CardElement id="card-element" className="input input-bordered w-full mt-1" />
                </div>
                <div>
                    <button type="submit" className="btn btn-primary w-full py-3 mt-4">Process Payment</button>
                </div>
            </form>
        </div>
    );
};

const Payment = () => {
    const { cartItems, total } = useSelector((state) => state.cart);
    const tax = total / 5;
    const shipping = 50;
    const totalAmount = total + shipping + tax;
    return (
        <>
            <SectionTitle title="Secure Payment" path="Home | Cart | Payment" />
            <div className="flex justify-center items-center h-screen bg-gray-100">
                <div className="max-w-md w-full">
                    <Elements stripe={stripePromise}>
                        <PaymentForm totalAmount={totalAmount} />
                    </Elements>
                </div>
            </div>
        </>
    );
};

export default Payment;

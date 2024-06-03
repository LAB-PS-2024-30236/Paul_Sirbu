import React from "react";
import { useDispatch } from "react-redux";
import { FaHeart } from "react-icons/fa";
import { removeFromWishlist } from "../features/wishlist/wishlistSlice.js";
import { toast } from "react-toastify";

const WishItem = ({ wishItem }) => {
    const { id, title, selectedSize } = wishItem;
    const dispatch = useDispatch();

    const removeFromWishlistHandler = () => {
        dispatch(removeFromWishlist(id));
        toast.error("Product removed from the wishlist!");
    };

    return (
        <div className="flex justify-between items-center border-b border-gray-200 py-4">
            <div>
                <h3 className="text-lg font-semibold">{title}</h3>
                <p className="text-sm">Size: {selectedSize}</p>
            </div>
            <button
                onClick={removeFromWishlistHandler}
                className="text-red-500 hover:text-red-600"
            >
                <FaHeart />
            </button>
        </div>
    );
};

export default WishItem;

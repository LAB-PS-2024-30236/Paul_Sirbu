import { createSlice } from "@reduxjs/toolkit";
import {toast} from "react-toastify";

const initialState = {
  wishItems: [],
  amount: 0,
  isLoading: true,
};

const wishlistSlice = createSlice({
  name: "wishlist",
  initialState,
  reducers: {
    clearWishlist: (state) => {
      state.wishItems = [];
      state.amount = 0;
    },
    removeFromWishlist: (state, action) => {
      const { userObj } = action.payload;
      if (userObj && userObj.userWishlist) {
        state.wishItems = userObj.userWishlist;
        state.isLoading = false;
      }
    },
    updateWishlist: (state, action) => {
      const { userObj } = action.payload;
      if (userObj && userObj.userWishlist) {
        state.wishItems = userObj.userWishlist;
        state.isLoading = false;
      }
    },
    calculateWishlistAmount: (state) => {
      let amount = 0;
      state.wishItems.forEach((item) => {
        amount += item.amount;
      });
      state.amount = amount;
    },
    removeFromWishlistHandler: (state, action) => {
      const { id } = action.payload;
      state.wishItems = state.wishItems.filter((item) => item.id !== id);
      wishlistSlice.caseReducers.calculateWishlistAmount(state);
      toast.error("Product removed from the wishlist!");
    },
    addToWishlistHandler: (state, action) => {
      const newItem = action.payload;
      const existingItem = state.wishItems.find((item) => item.id === newItem.id);
      if (existingItem) {
        toast.warning("Product is already in the wishlist!");
      } else {
        state.wishItems.push(newItem);
        toast.success("Product added to the wishlist!");
      }
      wishlistSlice.caseReducers.calculateWishlistAmount(state);
    },
  },

});

export const {
  clearWishlist,
  removeFromWishlist,
  updateWishlist,
  calculateWishlistAmount,
  removeFromWishlistHandler,
  addToWishlistHandler
} = wishlistSlice.actions;

export default wishlistSlice.reducer;

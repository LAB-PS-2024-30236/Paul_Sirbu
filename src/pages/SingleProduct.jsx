import axios from "axios";
import React, { useState, useEffect } from "react";
import { FaHeart, FaCartShopping } from "react-icons/fa6";
import { Link, useLoaderData } from "react-router-dom";
import parse from "html-react-parser";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";
import { addToCart } from "../features/cart/cartSlice";
import { addToWishlistHandler, removeFromWishlistHandler } from "../features/wishlist/wishlistSlice";
import {
  SectionTitle,
} from "../components";

export const singleProductLoader = async ({ params }) => {
  const { id } = params;

  const response = await axios.get(`http://localhost:8080/product/getproduct/${id}`);

  return { productData: response.data };
};

const SingleProduct = () => {
  const [quantity, setQuantity] = useState(1), [size, setSize] = useState(0), [imageSrc, setImageSrc] = useState(''), {wishItems} = useSelector((state) => state.wishlist), {userId} = useSelector((state) => state.auth),
      dispatch = useDispatch(),
      loginState = useSelector((state) => state.auth.isLoggedIn), [rating, setRating] = useState([
        "empty star",
        "empty star",
        "empty star",
        "empty star",
        "empty star",
      ]), {productData} = useLoaderData();

  useEffect(() => {
    // Fetch product image
    axios.get(`http://localhost:8080/product/${productData.id}/image`, { responseType: 'arraybuffer' })
        .then(response => {
          const base64 = btoa(
              new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), '')
          );
          setImageSrc(`data:image/jpeg;base64,${base64}`);
        })
        .catch(error => console.error('Error fetching the image:', error));
  }, [productData.id]);

  const product = {
    id: productData?.id + size,
    title: productData?.name,
    rating: productData?.rating,
    price: productData?.price,
    brandName: productData?.brandName,
    amount: quantity,
    isInWishList: wishItems.find((item) => item.id === productData?.id + size) !== undefined,
  };

  const handleAddToWishlist = () => {
    if (loginState) {
      dispatch(addToWishlistHandler(product))
          .then(() => {
            toast.success("Product added to the wishlist!");
          })
          .catch((error) => {
            toast.error("Failed to add product to the wishlist: " + error.message);
          });
    } else {
      toast.error("You must be logged in to add products to the wishlist");
    }
  };

  const handleRemoveFromWishlist = () => {
    if (loginState) {
      dispatch(removeFromWishlistHandler(product))
          .then(() => {
            toast.success("Product removed from the wishlist!");
          })
          .catch((error) => {
            toast.error("Failed to remove product from the wishlist: " + error.message);
          });
    } else {
      toast.error("You must be logged in to remove products from the wishlist");
    }
  };

  return (
      <>
        <SectionTitle title="Product page" path="Home | Shop | Product page" />
        <div className="grid grid-cols-2 max-w-7xl mx-auto mt-5 max-lg:grid-cols-1 max-lg:mx-5">
          <div className="product-images flex flex-col justify-center max-lg:justify-start">
            {imageSrc ? <img src={imageSrc} alt="Product" /> : <p>Loading image...</p>}
          </div>
          <div className="single-product-content flex flex-col gap-y-5 max-lg:mt-2">
            <h2 className="text-5xl max-sm:text-3xl text-accent-content">
              {productData?.name}
            </h2>
            <p className="text-3xl text-error">${productData?.price}</p>
            <div className="text-xl max-sm:text-lg text-accent-content">
              {parse(productData?.shortDescription)}
            </div>
            <div className="text-xl max-sm:text-lg text-accent-content">
              {parse(productData?.longDescription)}
            </div>
            <div className="flex flex-row gap-x-2 max-sm:flex-col max-sm:gap-x">
              <button
                  className="btn bg-blue-600 hover:bg-blue-500 text-white"
                  onClick={() => {
                    if (loginState) {
                      dispatch(addToCart(product));
                    } else {
                      toast.error("You must be logged in to add products to the cart");
                    }
                  }}
              >
                <FaCartShopping className="text-xl mr-1" />
                Add to cart
              </button>
              {product?.isInWishList ? (
                  <button
                      className="btn bg-blue-600 hover:bg-blue-500 text-white"
                      onClick={() => {
                        if (loginState) {
                          handleRemoveFromWishlist()
                              .then(() => {
                                toast.success("Product removed from the wishlist!");
                              })
                              .catch((error) => {
                                toast.error("Failed to remove product from the wishlist: " + error.message);
                              });
                        } else {
                          toast.error("You must be logged in to remove products from the wishlist");
                        }
                      }}
                  >
                    <FaHeart className="text-xl mr-1" />
                    Remove from wishlist
                  </button>
              ) : (
                  <button
                      className="btn bg-blue-600 hover:bg-blue-500 text-white"
                      onClick={() => {
                        if (loginState) {
                          handleAddToWishlist()
                              .then(() => {
                                console.log("Product added to wishlist successfully");
                              })
                              .catch(error => {
                                console.error("Error adding product to wishlist:", error);
                              });
                        } else {
                          toast.error("You must be logged in to add products to the wishlist");
                        }
                      }}
                  >
                    <FaHeart className="text-xl mr-1" />
                    Add to wishlist
                  </button>
              )}
            </div>
          </div>
        </div>
      </>
  );
};

export default SingleProduct;

import React, { useEffect, useState } from "react";
import { SectionTitle } from "../components/index.js";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axios from "axios";
import { nanoid } from "nanoid";

const OrderHistory = () => {
  const loginState = useSelector((state) => state.auth.isLoggedIn);
  const navigate = useNavigate();
  const [orders, setOrders] = useState([]);

  const getOrderHistory = async () => {
    try {
      const userId = localStorage.getItem("id");
      const response = await axios.get(`http://localhost:8080/weborders/getwebordersbyuser/${userId}`);
      const data = response.data;
      if (Array.isArray(data)) {
        setOrders(data);
      } else {
        setOrders([]);
      }
    } catch (error) {
      toast.error(error.response?.data?.message || "An error occurred while fetching order history");
    }
  };

  useEffect(() => {
    if (!loginState) {
      toast.error("You must be logged in to access this page");
      navigate("/");
    } else {
      getOrderHistory();
    }
  }, [loginState, navigate]);

  return (
      <>
        <SectionTitle title="Order History" path="Home | Order History" />
        <div className="order-history-main max-w-7xl mx-auto mt-10 px-20 max-md:px-10">
          {orders.length === 0 ? (
              <div className="text-center">
                <h1 className="text-4xl text-accent-content">
                  There are no orders in the order history
                </h1>
                <Link
                    to="/shop"
                    className="btn bg-blue-600 hover:bg-blue-500 text-white mt-10"
                >
                  Make your first order
                </Link>
              </div>
          ) : (
              orders.map((order) => (
                  <div
                      key={nanoid()}
                      className="collapse collapse-plus bg-base-200 mb-2"
                  >
                    <input type="radio" name="my-accordion-3" />
                    <div className="collapse-title text-xl font-medium text-accent-content">
                      Order {order.id}
                    </div>
                    <div className="collapse-content">
                      <div className="overflow-x-auto">
                        <table className="table max-sm:table-xs table-pin-rows table-pin-cols">
                          <thead>
                          <tr className="text-accent-content">
                            <th>Name</th>
                            <th>Quantity</th>
                          </tr>
                          </thead>
                          <tbody>
                          {order.quantities && Array.isArray(order.quantities) && order.quantities.map((product, index) => (
                              <tr className="text-accent-content" key={nanoid()}>
                                <td>{product.productName}</td>
                                <td>{product.quantity}</td>
                              </tr>
                          ))}
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
              ))
          )}
        </div>
      </>
  );
};

export default OrderHistory;

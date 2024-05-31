import React, { useState } from "react";
import { SectionTitle } from "../components";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const navigate = useNavigate();

  const [userFormData, setUserFormData] = useState({
    username: "",
    email: "",
    password: "",
    firstName: "",
    lastName: "",
  });

  const registerUser = async (e) => {
    e.preventDefault();
    try {
      const postResponse = await axios.post(
          "http://localhost:8080/auth/register",
          userFormData
      );
      const postData = postResponse.data;
      toast.success("User registered successfully");
      // Optionally, navigate to the login page or user profile page after successful registration
      navigate("/login");
    } catch (error) {
      toast.error("Failed to register user: " + error.response.data.message);
    }
  };

  return (
      <>
        <SectionTitle title="Register" path="Home | Register" />
        <form className="max-w-7xl mx-auto text-center px-10" onSubmit={registerUser}>
          <div className="grid grid-cols-3 max-lg:grid-cols-1">
            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Username</span>
              </label>
              <input
                  type="text"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.username}
                  onChange={(e) => setUserFormData({ ...userFormData, username: e.target.value })}
              />
            </div>
            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Email</span>
              </label>
              <input
                  type="email"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.email}
                  onChange={(e) => setUserFormData({ ...userFormData, email: e.target.value })}
              />
            </div>
            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Password</span>
              </label>
              <input
                  type="password"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.password}
                  onChange={(e) => setUserFormData({ ...userFormData, password: e.target.value })}
              />
            </div>
            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">First Name</span>
              </label>
              <input
                  type="text"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.firstName}
                  onChange={(e) => setUserFormData({ ...userFormData, firstName: e.target.value })}
              />
            </div>
            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Last Name</span>
              </label>
              <input
                  type="text"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.lastName}
                  onChange={(e) => setUserFormData({ ...userFormData, lastName: e.target.value })}
              />
            </div>
          </div>
          <button className="btn btn-lg bg-blue-600 hover:bg-blue-500 text-white mt-10" type="submit">
            Register
          </button>
        </form>
      </>
  );
};

export default Register;

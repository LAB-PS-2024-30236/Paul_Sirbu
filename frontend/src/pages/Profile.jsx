import React, { useEffect, useState } from "react";
import { SectionTitle } from "../components/index.js";
import axios from "axios";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const Profile = () => {
  const [id, setId] = useState(localStorage.getItem("id") || "");
  const [userData, setUserData] = useState({});
  const loginState = useSelector((state) => state.auth.isLoggedIn);
  const wishItems = useSelector((state) => state.wishlist.wishItems);
  const [userFormData, setUserFormData] = useState({
    id: "",
    firstName: "",
    lastName: "",
    email: "",
    username: "",
    password: "",
  });
  const navigate = useNavigate();

  const getUserData = async () => {
    if (!id) {
      toast.error("User ID is not available.");
      return;
    }
    try {
      const response = await axios(`http://localhost:8080/user/getuser/${id}`);
      const data = response.data;
      setUserFormData({
        id: data.id,
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
        username: data.username,
        password: data.password,
      });
    } catch (error) {
      console.error("Error fetching user data:", error);
      toast.error("An error occurred while fetching user data.");
    }
  };

  useEffect(() => {
    if (loginState) {
      getUserData();
    } else {
      toast.error("You must be logged in to access this page");
      navigate("/");
    }
  }, [loginState, id]);

  const updateProfile = async (e) => {
    e.preventDefault();
    if (!id) {
      toast.error("User ID is not available.");
      return;
    }
    try {
      const getResponse = await axios(`http://localhost:8080/user/getuser/${id}`);
      const userObj = getResponse.data;

      const putResponse = await axios.put(`http://localhost:8080/user/updateuser/${id}`, {
        id: id,
        firstName: userFormData.firstName,
        lastName: userFormData.lastName,
        email: userFormData.email,
        username: userFormData.username,
        password: userFormData.password,
        userWishlist: userObj.userWishlist,
      });
      const putData = putResponse.data;
      toast.success("Profile updated successfully");
    } catch (error) {
      console.error("Error updating user profile:", error);
      toast.error("An error occurred while updating user profile.");
    }
  };

  const convertUserDataToXML = () => {
    let xml = '<?xml version="1.0" encoding="UTF-8"?>\n<user>\n';
    xml += `  <id>${userFormData.id}</id>\n`;
    xml += `  <firstName>${userFormData.firstName}</firstName>\n`;
    xml += `  <lastName>${userFormData.lastName}</lastName>\n`;
    xml += `  <email>${userFormData.email}</email>\n`;
    xml += `  <username>${userFormData.username}</username>\n`;
    xml += `  <password>${userFormData.password}</password>\n`;
    xml += '</user>';
    return xml;
  };

  const handleExportXML = () => {
    const xmlData = convertUserDataToXML();
    const blob = new Blob([xmlData], { type: 'application/xml' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'userProfile.xml';
    a.click();
    URL.revokeObjectURL(url);
  };

  return (
      <>
        <SectionTitle title="User Profile" path="Home | User Profile" />
        <form className="max-w-7xl mx-auto text-center px-10" onSubmit={updateProfile}>
          <div className="grid grid-cols-3 max-lg:grid-cols-1">
            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Your Firstname</span>
              </label>
              <input
                  type="text"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.firstName}
                  onChange={(e) => {setUserFormData({...userFormData, firstName: e.target.value})}}
              />
            </div>

            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Your Lastname</span>
              </label>
              <input
                  type="text"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.lastName}
                  onChange={(e) => {setUserFormData({...userFormData, lastName: e.target.value})}}
              />
            </div>

            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Your Email</span>
              </label>
              <input
                  type="email"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.email}
                  onChange={(e) => {setUserFormData({...userFormData, email: e.target.value})}}
              />
            </div>

            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Your Username</span>
              </label>
              <input
                  type="text"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.username}
                  onChange={(e) => {setUserFormData({...userFormData, username: e.target.value})}}
              />
            </div>

            <div className="form-control w-full lg:max-w-xs">
              <label className="label">
                <span className="label-text">Your Password</span>
              </label>
              <input
                  type="password"
                  placeholder="Type here"
                  className="input input-bordered w-full lg:max-w-xs"
                  value={userFormData.password}
                  onChange={(e) => {setUserFormData({...userFormData, password: e.target.value})}}
              />
            </div>
          </div>
          <button
              className="btn btn-lg bg-blue-600 hover:bg-blue-500 text-white mt-10"
              type="submit"
          >
            Update Profile
          </button>
          <button
              type="button"
              className="btn btn-lg bg-blue-600 hover:bg-blue-500 text-white mt-4"
              onClick={handleExportXML}
          >
            Export as XML
          </button>
        </form>
      </>
  );
};

export default Profile;

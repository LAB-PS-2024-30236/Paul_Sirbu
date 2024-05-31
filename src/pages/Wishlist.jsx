import React from "react";
import { SectionTitle, WishItem } from "../components";
import { useSelector } from "react-redux";

const Wishlist = () => {
    const { wishItems } = useSelector((state) => state.wishlist);

    return (
        <>
            <SectionTitle title="Wishlist" path="Home | Wishlist" />
            <div className="max-w-3xl mx-auto">
                <div className="divide-y divide-gray-200">
                    {wishItems.map((item) => (
                        <WishItem key={item.id} wishItem={item} />
                    ))}
                </div>
            </div>
        </>
    );
};

export default Wishlist;

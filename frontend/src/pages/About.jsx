import React from "react";
import { SectionTitle } from "../components/index.js";

const About = () => {
    return (
        <div>
            <SectionTitle title="About Us" path="Home | About" />
            <div className="about-content max-w-3xl mx-auto mt-5 px-4">
                <h2 className="text-6xl text-center mb-10 max-sm:text-3xl text-accent-content">We love our customers!</h2>
                <p className="text-lg text-center max-sm:text-sm text-accent-content leading-relaxed">
                    "Pravalia lui Paul" is not just a clothing store; it's a hub for streetwear enthusiasts and fashion-forward individuals looking to make a statement. Nestled in the heart of the city, it's more than just a place to shop—it's a cultural landmark where style meets expression.
                </p>
                <p className="text-lg text-center max-sm:text-sm text-accent-content leading-relaxed mt-4">
                    From its humble beginnings, "Pravalia lui Paul" has been dedicated to curating the latest trends in streetwear fashion, offering a diverse range of clothing, accessories, and footwear to cater to every taste. Whether you're into bold graphics, minimalist designs, or vintage-inspired looks, you'll find something to suit your style here.
                </p>
                <p className="text-lg text-center max-sm:text-sm text-accent-content leading-relaxed mt-4">
                    But "Pravalia lui Paul" is more than just a store; it's a community. With its welcoming atmosphere and passionate staff, it's a place where fashion enthusiasts can come together to share their love for streetwear, exchange style tips, and discover new brands and designers.
                </p>
                <p className="text-lg text-center max-sm:text-sm text-accent-content leading-relaxed mt-4">
                    At "Pravalia lui Paul," it's not just about what you wear—it's about how you wear it. Whether you're rocking the latest sneaker drop or styling a classic hoodie, it's all about embracing your individuality and expressing yourself through your personal sense of style.
                </p>
                <p className="text-lg text-center max-sm:text-sm text-accent-content leading-relaxed mt-4">
                    So, whether you're a seasoned streetwear aficionado or just dipping your toes into the world of urban fashion, "Pravalia lui Paul" welcomes you to explore its curated selection, immerse yourself in the culture, and join the movement of self-expression through style.
                </p>
            </div>
        </div>
    );
};

export default About;

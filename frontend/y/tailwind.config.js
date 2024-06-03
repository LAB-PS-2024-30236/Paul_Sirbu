/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  daisyui: {
    themes: ["luxury", "wireframe"],
  },
  plugins: [
    require("@tailwindcss/typography"),
    require("daisyui")
  ]
}
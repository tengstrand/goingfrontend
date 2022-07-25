module.exports = {
  content:
    process.env.NODE_ENV == "production"
      ? ["./build/js/main.js"]
      : ["./resources/public/js/cljs-runtime/*.js"],
  plugins: [],
};

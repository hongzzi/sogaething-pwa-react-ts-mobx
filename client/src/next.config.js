const path = require("path");
const TsconfigPathsPlugin = require("tsconfig-paths-webpack-plugin");
const withOptimizedImages = require("next-optimized-images");
const SWPrecacheWebpackPlugin = require("sw-precache-webpack-plugin");

module.exports = withOptimizedImages({
  distDir: "../dist",
  imagesName: "[hash].[ext]",
  target: "serverless",
  webpack(config) {
    config.resolve.plugins = [
      new TsconfigPathsPlugin({
        configFile: path.resolve(__dirname, "./tsconfig.json"),
      }),
    ];
    config.plugins.push(
      new SWPrecacheWebpackPlugin({
        staticFileGlobsIgnorePatterns: [/\.next\//],
        minify: true,
      })
    );
    return config;
  },
});
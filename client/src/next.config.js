const path = require("path");
const TsconfigPathsPlugin = require("tsconfig-paths-webpack-plugin");
const withOptimizedImages = require("next-optimized-images");
const SWPrecacheWebpackPlugin = require("sw-precache-webpack-plugin");
const withBundleAnalyzer = require("@next/bundle-analyzer");
const CompressionPlugin = require('compression-webpack-plugin');
const Dotenv = require("dotenv-webpack");
const webpack = require('webpack');

module.exports = withOptimizedImages({
  distDir: '../dist',
  // imagesName: "[hash].[ext]",
  // target: "serverless",
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
    // config.plugins.push(
    //   new CompressionPlugin()
    // );
    config.plugins.push(
      new webpack.EnvironmentPlugin(process.env)
    );
    config.node = {
      fs: "empty",
      net: "empty"
    };
    return config;
  },
});

// var packageJSON = require('./package.json');
// var path = require('path');
// var webpack = require('webpack');

// const PATHS = {
//   build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
// };

// module.exports = {
//   entry: './app/index.js',

//   output: {
//     path: PATHS.build,
//     filename: 'app-bundle.js'
//   }
// };


const packageJSON = require('./package.json');
const path = require('path');
const webpack = require('webpack');
// const CleanWebpackPlugin = require('clean-webpack-plugin');

const PATHS = {
  build: path.resolve(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
};

module.exports = {
  entry: {
    home: path.join(__dirname, './app/home.js'),
    tt: path.join(__dirname, './app/tt.js'),
    t3: path.join(__dirname, './app/t3.js')
  },
  output: {
    path: PATHS.build,
    filename: '[name].js',
    publicPath: "/assets/"
  },
  devServer: {
    hot: true,
    historyApiFallback: true,
    inline: true,
    port: 3000,
    publicPath: "/assets/",
    // contentBase: './tmp',
    proxy: {
      "**": "http://localhost:8000"
    }
  },
  module: {
    rules: [{
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            cacheDirectory: true,
            presets: ['env', 'react'],
          }
        }
      },
      {
        test: /\.css$/,
        use: [
          'style-loader',
          'css-loader'
        ]
      },
      {
        test: /\.(png|svg|jpg|gif)$/,
        use: [
          'file-loader'
        ]
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: [
          'file-loader'
        ]
      }
    ]
  },
  devtool: 'inline-source-map',
  plugins: [
    // new CleanWebpackPlugin([PATHS.build])
    new webpack.HotModuleReplacementPlugin()
  ]
};
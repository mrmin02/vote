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
    register: path.join(__dirname,'./app/register.js'),
    login: path.join(__dirname,'./app/login.js'),
    voteIndex: path.join(__dirname,'./app/vote/voteIndex.jsx'),
    voteCreate: path.join(__dirname,'./app/vote/voteCreate.jsx'),
    voteShow: path.join(__dirname,'./app/vote/voteShow.jsx'),
    voteResult: path.join(__dirname,'./app/vote/voteResult.jsx'),
    layout: path.join(__dirname,'./app/layout/layout.jsx'),
    userInfo: path.join(__dirname,'./app/userInfo/userIndex.jsx')

  },
  output: {
    path: PATHS.build,
    filename: '[name].js',
    publicPath: "/assets/"
  },
  // resolve:{
  //   extensions: ['','.js','.jsx']
  // },
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
        // exclude: /(node_modules|bower_components)/,
        exclude:  /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            cacheDirectory: true,
            presets: ['env', 'react','babel-polyfill'],
          }
        }
      },
      {
        test: /.jsx$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          query: {
            presets: ['env', 'react','babel-polyfill']
          }
        } 
      //   
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
    ],
  },
  devtool: 'inline-source-map',
  plugins: [
    // new CleanWebpackPlugin([PATHS.build])
    new webpack.HotModuleReplacementPlugin(),
    // ["@babel/plugin-transform-runtime"]
    // ["@babel/plugin-transform-runtime",
    //   {
    //     "regenerator": true
    //   }
    // ]
    
  ]
};
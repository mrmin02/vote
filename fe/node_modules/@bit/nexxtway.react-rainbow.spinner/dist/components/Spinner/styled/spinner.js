"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _styledComponents = _interopRequireDefault(require("styled-components"));

var _colors = require("../../../styles/colors");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _templateObject() {
  var data = _taggedTemplateLiteral(["\n    box-sizing: border-box;\n    position: absolute;\n    top: 50%;\n    left: 50%;\n    z-index: 5000;\n    transform: translate(-50%, -50%) rotate(90deg);\n    display: block;\n    font-size: 0;\n    color: #ddd;\n\n    > div {\n        box-sizing: border-box;\n        position: absolute;\n        top: 50%;\n        left: 50%;\n        z-index: 5000;\n        transform: translate(-50%, -50%) rotate(90deg);\n        display: inline-block;\n        float: none;\n        background-color: currentColor;\n        border: 0 solid currentColor;\n        margin-top: -4px;\n        margin-left: -4px;\n        border-radius: 100px;\n        animation: ball-spin-clockwise 1s infinite ease-in-out;\n    }\n\n    > div:nth-child(1) {\n        top: 5%;\n        left: 50%;\n        animation-delay: -0.875s;\n        color: #fe4849;\n    }\n\n    > div:nth-child(2) {\n        top: 18.1801948466%;\n        left: 81.8198051534%;\n        animation-delay: -0.75s;\n        color: #ff6837;\n    }\n\n    > div:nth-child(3) {\n        top: 50%;\n        left: 95%;\n        animation-delay: -0.625s;\n        color: #f8d832;\n    }\n\n    > div:nth-child(4) {\n        top: 81.8198051534%;\n        left: 81.8198051534%;\n        animation-delay: -0.5s;\n        color: #c7e46a;\n    }\n\n    > div:nth-child(5) {\n        top: 94.9999999966%;\n        left: 50.0000000005%;\n        animation-delay: -0.375s;\n        color: #1de9b6;\n    }\n\n    > div:nth-child(6) {\n        top: 81.8198046966%;\n        left: 18.1801949248%;\n        animation-delay: -0.25s;\n        color: #1ad1a3;\n    }\n\n    > div:nth-child(7) {\n        top: 49.9999750815%;\n        left: 5.0000051215%;\n        animation-delay: -0.125s;\n        color: #01b6f5;\n    }\n\n    > div:nth-child(8) {\n        top: 18.179464974%;\n        left: 18.1803700518%;\n        animation-delay: 0s;\n        color: #5c56b6;\n    }\n\n    @-webkit-keyframes ball-spin-clockwise {\n        0%,\n        100% {\n            opacity: 1;\n            transform: scale(1);\n        }\n\n        20% {\n            opacity: 1;\n        }\n\n        80% {\n            opacity: 0;\n            transform: scale(0);\n        }\n    }\n\n    @-moz-keyframes ball-spin-clockwise {\n        0%,\n        100% {\n            opacity: 1;\n            transform: scale(1);\n        }\n\n        20% {\n            opacity: 1;\n        }\n\n        80% {\n            opacity: 0;\n            transform: scale(0);\n        }\n    }\n\n    @-o-keyframes ball-spin-clockwise {\n        0%,\n        100% {\n            opacity: 1;\n            transform: scale(1);\n        }\n\n        20% {\n            opacity: 1;\n        }\n\n        80% {\n            opacity: 0;\n            transform: scale(0);\n        }\n    }\n\n    @keyframes ball-spin-clockwise {\n        0%,\n        100% {\n            opacity: 1;\n            transform: scale(1);\n        }\n\n        20% {\n            opacity: 1;\n        }\n\n        80% {\n            opacity: 0;\n            transform: scale(0);\n        }\n    }\n\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n"]);

  _templateObject = function _templateObject() {
    return data;
  };

  return data;
}

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

var StyledSpinner = _styledComponents["default"].div(_templateObject(), function (props) {
  return props.size === 'xx-small' && "\n            width: 1rem;\n            height: 1rem;\n\n            > div {\n                width: 0.125rem;\n                height: 0.125rem;\n            }\n        ";
}, function (props) {
  return props.size === 'x-small' && "\n            width: 1.25rem;\n            height: 1.25rem;\n\n            > div {\n                width: 0.25rem;\n                height: 0.25rem;\n            }\n        ";
}, function (props) {
  return props.size === 'small' && "\n            width: 1.625rem;\n            height: 1.625rem;\n\n            > div {\n                width: 0.375rem;\n                height: 0.375rem;\n            }\n        ";
}, function (props) {
  return props.size === 'medium' && "\n            width: 2rem;\n            height: 2rem;\n\n            > div {\n                width: 0.4375rem;\n                height: 0.4375rem;\n            }\n        ";
}, function (props) {
  return props.size === 'large' && "\n            width: 3rem;\n            height: 3rem;\n\n            > div {\n                width: 0.5625rem;\n                height: 0.5625rem;\n            }\n        ";
}, function (props) {
  return props.variant === 'brand' && "\n            > div {\n                color: ".concat(_colors.COLOR_BRAND, " !important;\n            }\n        ");
}, function (props) {
  return props.variant === 'inverse' && "\n            > div {\n                color: ".concat(_colors.COLOR_WHITE, " !important;\n            }\n        ");
}, function (props) {
  return props.variant === 'neutral' && "\n            > div {\n                color: ".concat(_colors.COLOR_GRAY_3, " !important;\n            }\n        ");
});

var _default = StyledSpinner;
exports["default"] = _default;

//# sourceMappingURL=spinner.js.map
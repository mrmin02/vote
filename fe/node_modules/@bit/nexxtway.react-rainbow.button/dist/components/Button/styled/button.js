"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _styledComponents = _interopRequireDefault(require("styled-components"));

var _colors = require("../../../styles/colors");

var _fontSizes = require("../../../styles/fontSizes");

var _shadows = require("../../../styles/shadows");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _templateObject() {
  var data = _taggedTemplateLiteral(["\n    align-items: center;\n    display: inline-flex;\n    font-size: ", ";\n    justify-content: center;\n    position: relative;\n    background: transparent;\n    background-clip: border-box;\n    border: 1px solid transparent;\n    border-radius: 100px;\n    line-height: 2.375rem;\n    text-decoration: none;\n    color: ", ";\n    padding: 0 1rem;\n    cursor: pointer;\n    white-space: normal;\n    user-select: none;\n    text-align: center;\n    vertical-align: middle;\n    transition: border 0.15s linear;\n\n    &:hover,\n    &:focus,\n    &:active,\n    &:visited {\n        text-decoration: none;\n    }\n\n    &:hover,\n    &:focus {\n        color: ", ";\n    }\n\n    &:focus {\n        outline: 0;\n        box-shadow: ", ";\n    }\n\n    &:active {\n        color: ", ";\n        transform: scale(0.95);\n        transition: all 0.2s ease;\n    }\n\n    &[disabled] {\n        color: ", ";\n    }\n\n    &[disabled] * {\n        cursor: default;\n        pointer-events: none;\n    }\n\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n    ", ";\n"]);

  _templateObject = function _templateObject() {
    return data;
  };

  return data;
}

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

var StyledButton = _styledComponents["default"].button(_templateObject(), _fontSizes.FONT_SIZE_HEADING_SMALL, _colors.COLOR_BRAND, _colors.COLOR_BRAND_ACTIVE, _shadows.SHADOW_OUTLINE, _colors.COLOR_BRAND_ACTIVE, _colors.COLOR_GRAY_2, function (props) {
  return props.variant === 'neutral' && "\n            background-color: ".concat(_colors.COLOR_WHITE, ";\n            border: 1px solid ").concat(_colors.COLOR_GRAY_2, ";\n            color: ").concat(_colors.COLOR_BRAND, ";\n\n            &:hover,\n            &:focus,\n            &:active {\n                background-color: ").concat(_colors.COLOR_GRAY_1, ";\n            }\n\n            &[disabled] {\n                background-color: ").concat(props.isLoading ? _colors.COLOR_WHITE : 'transparent', ";\n            }\n        ");
}, function (props) {
  return props.variant === 'brand' && "\n            background-color: ".concat(_colors.COLOR_BRAND, ";\n            border: 1px solid ").concat(_colors.COLOR_BRAND, ";\n            color: ").concat(_colors.COLOR_WHITE, ";\n\n            &:link,\n            &:visited,\n            &:active {\n                color: ").concat(_colors.COLOR_WHITE, "\n            }\n\n            &:hover,\n            &:focus,\n            &:active {\n                background-color: ").concat(_colors.COLOR_BRAND_ACTIVE, ";\n                border-color: ").concat(_colors.COLOR_BRAND_ACTIVE, ";\n                color: ").concat(_colors.COLOR_WHITE, ";\n            }\n        \n            &[disabled] {\n                background-color: ").concat(props.isLoading ? _colors.COLOR_BRAND : _colors.COLOR_GRAY_1, ";\n                border-color: ").concat(props.isLoading ? _colors.COLOR_BRAND : _colors.COLOR_GRAY_1, ";\n                color: ").concat(_colors.COLOR_GRAY_2, ";\n            }\n        ");
}, function (props) {
  return props.variant === 'outline-brand' && "\n            background-color: transparent;\n            border: 1px solid ".concat(_colors.COLOR_BRAND, ";\n            color: ").concat(_colors.COLOR_BRAND, ";\n\n            &:hover,\n            &:focus,\n            &:active {\n                border-color: ").concat(_colors.COLOR_BRAND_ACTIVE, ";\n            }\n        \n            &[disabled] {\n                background-color: transparent;\n                border-color: ").concat(props.isLoading ? _colors.COLOR_BRAND : _colors.COLOR_GRAY_2, ";\n                \n            }\n        ");
}, function (props) {
  return props.variant === 'inverse' && "\n            background-color: transparent;\n            border: 1px solid transparent;\n            color: ".concat(_colors.COLOR_WHITE, ";\n\n            &:hover,\n            &:focus,\n            &:active {\n                color: ").concat(_colors.COLOR_GRAY_3, ";\n            }\n        \n            &:focus {\n                outline: none;\n                box-shadow: ").concat(_shadows.SHADOW_5, ";\n            }\n        \n            &[disabled] {\n                background-color: transparent;\n                color: ").concat(_colors.COLOR_GRAY_4, ";\n            }\n        ");
}, function (props) {
  return props.variant === 'border-inverse' && "   background-color: transparent;\n            border: 1px solid ".concat(_colors.COLOR_WHITE, ";\n            color: ").concat(_colors.COLOR_WHITE, ";\n\n            &:hover,\n            &:focus,\n            &:active {\n                border-color: ").concat(_colors.COLOR_GRAY_3, ";\n                color: ").concat(_colors.COLOR_GRAY_3, ";\n            }\n        \n            &:focus {\n                outline: none;\n                box-shadow: ").concat(_shadows.SHADOW_5, ";\n            }\n        \n            &[disabled] {\n                background-color: transparent;\n                border-color: ").concat(props.isLoading ? _colors.COLOR_WHITE : _colors.COLOR_GRAY_4, ";\n                color: ").concat(_colors.COLOR_GRAY_4, ";\n            }\n        ");
}, function (props) {
  return props.variant === 'destructive' && "\n            background-color: ".concat(_colors.COLOR_ERROR, ";\n            border: 1px solid ").concat(_colors.COLOR_ERROR, ";\n            color: ").concat(_colors.COLOR_WHITE, ";\n\n            &:link,\n            &:visited,\n            &:active {\n                color: ").concat(_colors.COLOR_WHITE, ";\n            }\n        \n            &:hover,\n            &:focus {\n                background-color: ").concat(_colors.COLOR_ERROR_ACTIVE, ";\n                color: ").concat(_colors.COLOR_WHITE, ";\n            }\n        \n            &:active {\n                background-color: ").concat(_colors.COLOR_ERROR_ACTIVE, ";\n                border-color: ").concat(_colors.COLOR_ERROR_ACTIVE, ";\n            }\n        \n            &[disabled] {\n                background-color: ").concat(props.isLoading ? _colors.COLOR_ERROR : _colors.COLOR_GRAY_1, ";\n                border-color: ").concat(props.isLoading ? _colors.COLOR_ERROR : _colors.COLOR_GRAY_1, ";\n                color: ").concat(_colors.COLOR_GRAY_2, ";\n            }\n        ");
}, function (props) {
  return props.variant === 'success' && "\n            background-color: ".concat(_colors.COLOR_SUCCESS, ";\n            border: 1px solid ").concat(_colors.COLOR_SUCCESS, ";\n            color: ").concat(_colors.COLOR_WHITE, ";\n\n            &:link,\n            &:visited,\n            &:active,\n            &:hover,\n            &:focus {\n                color: ").concat(_colors.COLOR_WHITE, ";\n            }\n        \n            &:hover,\n            &:focus,\n            &:active {\n                background-color: ").concat(_colors.COLOR_SUCCESS_ACTIVE, ";\n                border-color: ").concat(_colors.COLOR_SUCCESS_ACTIVE, ";\n            }\n        \n            &[disabled] {\n                background-color: ").concat(props.isLoading ? _colors.COLOR_SUCCESS : _colors.COLOR_GRAY_1, ";\n                border-color: ").concat(props.isLoading ? _colors.COLOR_SUCCESS : _colors.COLOR_GRAY_1, ";\n                color: ").concat(_colors.COLOR_GRAY_2, ";\n            }\n        ");
}, function (props) {
  return props.shaded && "box-shadow: ".concat(_shadows.SHADOW_1, ";");
});

var _default = StyledButton;
exports["default"] = _default;

//# sourceMappingURL=button.js.map
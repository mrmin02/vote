"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = Spinner;

var _react = _interopRequireDefault(require("react"));

var _propTypes = _interopRequireDefault(require("prop-types"));

var _AssistiveText = _interopRequireDefault(require("./../AssistiveText"));

var _spinner = _interopRequireDefault(require("./styled/spinner"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

/**
 * Spinners should be shown when retrieving data or performing slow,
 * help to reassure the user that the system is actively retrieving data.
 */
function Spinner(props) {
  var className = props.className,
      style = props.style,
      assistiveText = props.assistiveText,
      isVisible = props.isVisible,
      size = props.size,
      variant = props.variant;

  if (isVisible) {
    return _react["default"].createElement(_spinner["default"], {
      className: className,
      size: size,
      variant: variant,
      style: style
    }, _react["default"].createElement("div", null), _react["default"].createElement("div", null), _react["default"].createElement("div", null), _react["default"].createElement("div", null), _react["default"].createElement("div", null), _react["default"].createElement("div", null), _react["default"].createElement("div", null), _react["default"].createElement("div", null), _react["default"].createElement(_AssistiveText["default"], {
      text: assistiveText
    }));
  }

  return null;
}

Spinner.propTypes = {
  /** The variant changes the appearance of the spinner.
   * Accepted variants are base, brand, and inverse. This value defaults to base. */
  variant: _propTypes["default"].oneOf(['base', 'brand', 'inverse', 'neutral']),

  /** The size of the spinner. Accepted sizes are small, medium, and large.
   * This value defaults to medium. */
  size: _propTypes["default"].oneOf(['xx-small', 'x-small', 'small', 'medium', 'large']),

  /** Show/Hide the spinner. */
  isVisible: _propTypes["default"].bool,

  /** A description for assistive sreen readers. */
  assistiveText: _propTypes["default"].string,

  /** A CSS class for the outer element, in addition to the component's base classes. */
  className: _propTypes["default"].string,

  /** An object with custom style applied to the outer element. */
  style: _propTypes["default"].object
};
Spinner.defaultProps = {
  variant: 'base',
  size: 'medium',
  isVisible: true,
  assistiveText: null,
  className: undefined,
  style: undefined
};

//# sourceMappingURL=index.js.map
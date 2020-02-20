"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = Content;

var _react = _interopRequireDefault(require("react"));

var _propTypes = _interopRequireDefault(require("prop-types"));

var _buttonContent = _interopRequireDefault(require("./buttonContent"));

var _spinner = _interopRequireDefault(require("./styled/spinner"));

var _content = _interopRequireDefault(require("./styled/content"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var variantMap = {
  base: 'base',
  neutral: 'base',
  brand: 'inverse',
  destructive: 'inverse',
  success: 'inverse',
  inverse: 'inverse',
  'outline-brand': 'brand',
  'border-inverse': 'inverse'
};

function Content(_ref) {
  var label = _ref.label,
      children = _ref.children,
      variant = _ref.variant,
      isLoading = _ref.isLoading;

  if (isLoading) {
    return _react["default"].createElement(_content["default"], null, _react["default"].createElement(_buttonContent["default"], {
      label: label
    }, children), _react["default"].createElement(_spinner["default"], {
      isVisible: isLoading,
      variant: variantMap[variant],
      size: "small"
    }));
  }

  return _react["default"].createElement(_buttonContent["default"], {
    label: label
  }, children);
}

Content.propTypes = {
  label: _propTypes["default"].oneOfType([_propTypes["default"].string, _propTypes["default"].node]),
  children: _propTypes["default"].oneOfType([_propTypes["default"].arrayOf(_propTypes["default"].node), _propTypes["default"].object]),
  variant: _propTypes["default"].oneOf(['base', 'neutral', 'brand', 'outline-brand', 'destructive', 'success', 'inverse', 'border-inverse']),
  isLoading: _propTypes["default"].bool
};
Content.defaultProps = {
  label: undefined,
  children: null,
  variant: 'neutral',
  isLoading: false
};

//# sourceMappingURL=content.js.map
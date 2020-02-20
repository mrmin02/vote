"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _react = _interopRequireWildcard(require("react"));

var _propTypes = _interopRequireDefault(require("prop-types"));

var _content = _interopRequireDefault(require("./content"));

var _button = _interopRequireDefault(require("./styled/button"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _getRequireWildcardCache() { if (typeof WeakMap !== "function") return null; var cache = new WeakMap(); _getRequireWildcardCache = function _getRequireWildcardCache() { return cache; }; return cache; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function _typeof(obj) { if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _possibleConstructorReturn(self, call) { if (call && (_typeof(call) === "object" || typeof call === "function")) { return call; } return _assertThisInitialized(self); }

function _assertThisInitialized(self) { if (self === void 0) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return self; }

function _getPrototypeOf(o) { _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf : function _getPrototypeOf(o) { return o.__proto__ || Object.getPrototypeOf(o); }; return _getPrototypeOf(o); }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function"); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, writable: true, configurable: true } }); if (superClass) _setPrototypeOf(subClass, superClass); }

function _setPrototypeOf(o, p) { _setPrototypeOf = Object.setPrototypeOf || function _setPrototypeOf(o, p) { o.__proto__ = p; return o; }; return _setPrototypeOf(o, p); }

/**
 * Buttons are clickable items used to perform an action.
 */
var Button =
/*#__PURE__*/
function (_Component) {
  _inherits(Button, _Component);

  function Button(props) {
    var _this;

    _classCallCheck(this, Button);

    _this = _possibleConstructorReturn(this, _getPrototypeOf(Button).call(this, props));
    _this.buttonRef = _react["default"].createRef();
    return _this;
  }

  _createClass(Button, [{
    key: "isDisabled",
    value: function isDisabled() {
      var _this$props = this.props,
          disabled = _this$props.disabled,
          isLoading = _this$props.isLoading;
      return disabled || isLoading;
    }
    /**
     * Sets focus on the element.
     * @public
     */

  }, {
    key: "focus",
    value: function focus() {
      this.buttonRef.current.focus();
    }
    /**
     * Sets click on the element.
     * @public
     */

  }, {
    key: "click",
    value: function click() {
      this.buttonRef.current.click();
    }
    /**
     * Sets blur on the element.
     * @public
     */

  }, {
    key: "blur",
    value: function blur() {
      this.buttonRef.current.blur();
    }
  }, {
    key: "render",
    value: function render() {
      var _this$props2 = this.props,
          style = _this$props2.style,
          label = _this$props2.label,
          children = _this$props2.children,
          tabIndex = _this$props2.tabIndex,
          onFocus = _this$props2.onFocus,
          onBlur = _this$props2.onBlur,
          onClick = _this$props2.onClick,
          title = _this$props2.title,
          type = _this$props2.type,
          ariaHaspopup = _this$props2.ariaHaspopup,
          id = _this$props2.id,
          isLoading = _this$props2.isLoading,
          variant = _this$props2.variant,
          shaded = _this$props2.shaded,
          ariaPressed = _this$props2.ariaPressed,
          ariaControls = _this$props2.ariaControls,
          ariaExpanded = _this$props2.ariaExpanded,
          onKeyDown = _this$props2.onKeyDown,
          form = _this$props2.form,
          className = _this$props2.className;
      return _react["default"].createElement(_button["default"], {
        "data-id": "button-element",
        id: id,
        className: className,
        style: style,
        variant: variant,
        isLoading: isLoading,
        shaded: shaded,
        disabled: this.isDisabled(),
        tabIndex: tabIndex,
        onFocus: onFocus,
        onBlur: onBlur,
        onClick: onClick,
        title: title,
        type: type,
        "aria-haspopup": ariaHaspopup,
        "aria-controls": ariaControls,
        "aria-expanded": ariaExpanded,
        "aria-pressed": ariaPressed,
        onKeyDown: onKeyDown,
        form: form,
        ref: this.buttonRef
      }, _react["default"].createElement(_content["default"], {
        variant: variant,
        label: label,
        isLoading: isLoading
      }, children));
    }
  }]);

  return Button;
}(_react.Component);

exports["default"] = Button;
Button.propTypes = {
  /** The text to be displayed inside the button. */
  label: _propTypes["default"].oneOfType([_propTypes["default"].string, _propTypes["default"].node]),

  /** The content of the button, used to render icon or text elements inside the button.
   * Children take precedence over label. */
  children: _propTypes["default"].oneOfType([_propTypes["default"].arrayOf(_propTypes["default"].node), _propTypes["default"].object]),

  /** The variant changes the appearance of the button.
   * Accepted variants include base, neutral, brand, outline-brand, destructive,
   * success, inverse and border-inverse. */
  variant: _propTypes["default"].oneOf(['base', 'neutral', 'brand', 'outline-brand', 'destructive', 'success', 'inverse', 'border-inverse']),

  /** Specifies true when the button has a shadow around it.
   * Only neutral, brand, destructive and success variant can be shaded. */
  shaded: _propTypes["default"].bool,

  /** Displays tooltip text when the mouse moves over the element. */
  title: _propTypes["default"].string,

  /** Specifies the type of button. Valid values are button, reset, and submit. */
  type: _propTypes["default"].oneOf(['button', 'submit', 'reset']),

  /** Specifies whether this button should be displayed in a disabled state.
   * Disabled buttons can't be clicked. */
  disabled: _propTypes["default"].bool,

  /** Specifies the tab order of an element (when the tab button is used for navigating). */
  tabIndex: _propTypes["default"].oneOfType([_propTypes["default"].number, _propTypes["default"].string]),

  /** The action triggered when the element is clicked. */
  onClick: _propTypes["default"].func,

  /** The action triggered when a keyboard key is pressed. */
  onKeyDown: _propTypes["default"].func,

  /** The action triggered when the element receives focus. */
  onFocus: _propTypes["default"].func,

  /** The action triggered when the element releases focus. */
  onBlur: _propTypes["default"].func,

  /** Indicates that the element has a popup context menu or sub-level menu. */
  ariaHaspopup: _propTypes["default"].bool,

  /** A space-separated list of element IDs that
   * this button controls the contents or presence of. */
  ariaControls: _propTypes["default"].string,

  /** Indicates whether an element the button controls is expanded or collapsed.
   * Valid values are 'true' or 'false'. */
  ariaExpanded: _propTypes["default"].bool,

  /** Indicates that the element has been pressed. */
  ariaPressed: _propTypes["default"].bool,

  /** It must be the id attribute of a form element that the button is associated with. */
  form: _propTypes["default"].string,

  /** A CSS class for the outer element, in addition to the component's base classes. */
  className: _propTypes["default"].string,

  /** An object with custom style applied to the outer element. */
  style: _propTypes["default"].object,

  /** The id of the outer element. */
  id: _propTypes["default"].string,

  /** If it is set to true, then a loading symbol is displayed. */
  isLoading: _propTypes["default"].bool
};
Button.defaultProps = {
  label: undefined,
  children: null,
  variant: 'neutral',
  shaded: false,
  title: undefined,
  type: 'button',
  disabled: false,
  tabIndex: undefined,
  onClick: function onClick() {},
  onKeyDown: function onKeyDown() {},
  onFocus: function onFocus() {},
  onBlur: function onBlur() {},
  ariaHaspopup: undefined,
  className: undefined,
  style: undefined,
  id: undefined,
  isLoading: false,
  ariaPressed: undefined,
  ariaControls: undefined,
  ariaExpanded: undefined,
  form: undefined
};

//# sourceMappingURL=index.js.map
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _styledComponents = _interopRequireDefault(require("styled-components"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _templateObject() {
  var data = _taggedTemplateLiteral(["\n    position: absolute !important;\n    margin: -1px !important;\n    border: 0 !important;\n    padding: 0 !important;\n    width: 1px !important;\n    height: 1px !important;\n    overflow: hidden !important;\n    clip: rect(0 0 0 0) !important;\n    text-transform: none !important;\n    white-space: nowrap !important;\n"]);

  _templateObject = function _templateObject() {
    return data;
  };

  return data;
}

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

var HiddenElement = _styledComponents["default"].span(_templateObject());

var _default = HiddenElement;
exports["default"] = _default;

//# sourceMappingURL=hiddenElement.js.map
(function () {

    namespace("FlexSolution.component");

    /**
     * YUI Library aliases
     */
    var Dom = YAHOO.util.Dom,
        Color = YAHOO.util.Color;

    FlexSolution.component.ColorPicker = function SimplexColorPicker_constructor(htmlId) {
        FlexSolution.component.ColorPicker.superclass.constructor.call(this, "FlexSolution.component.ColorPicker", htmlId, null);

        this.id = htmlId;

        return this;
    };

    YAHOO.extend(FlexSolution.component.ColorPicker, Alfresco.component.Base,
        {

            options: {

                pageContext: "/share",
                showhsvcontrols: false,
                showhexcontrols: true,
                showhexsummary: false,
                showrgbcontrols: false,
                showwebsafe: false,
                value: "000000"
            },

            /**
             * Fired by YUI when parent element is available for scripting
             * @method onReady
             */
            onReady: function onReady() {

                this.widgets.editButton = Alfresco.util.createYUIButton(this, "editButton", this._showPicker);

                this.widgets.hideButton = Alfresco.util.createYUIButton(this, "hideButton", this._hidePicker);

                this.widgets.picker = new YAHOO.widget.ColorPicker(this.id + "-container", {
                    showhsvcontrols: this.options.showhsvcontrols,
                    showhexcontrols: this.options.showhexcontrols,
                    showhexsummary: this.options.showhexsummary,
                    showrgbcontrols: this.options.showrgbcontrols,
                    showwebsafe: this.options.showwebsafe,

                    images: {
                        PICKER_THUMB: this.options.pageContext + "/res/yui/colorpicker/assets/picker_thumb.png",
                        HUE_THUMB: this.options.pageContext + "/res/yui/colorpicker/assets/hue_thumb.png"
                    }
                });

                this.widgets.picker.on("rgbChange", this._updateColorInput, this, this);

                this.widgets.picker.setValue(this.getRGBColor(this.options.value), false);
            },

            getRGBColor: function (hex) {

                return Color.hex2rgb(hex)
            },

            _updateColorInput: function (p_obj) {

                var hexCode = Color.rgb2hex(p_obj.newValue);

                Dom.get(this.id).value = hexCode;

                var cssCode = "#" + hexCode;

                Dom.get(this.id + "-value").style.backgroundColor = cssCode;

                this.widgets.editButton._button.innerHTML = cssCode;
            },

            _showPicker: function () {
                Dom.get(this.id + "-picker").style.visibility = "visible";
            },

            _hidePicker: function () {
                Dom.get(this.id + "-picker").style.visibility = "hidden";
            }

        });
})();
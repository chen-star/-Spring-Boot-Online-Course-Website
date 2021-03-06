Validator = {
    require: function (value, text) {
        if (Tool.isEmpty(value)) {
            Toast.warning(text + " can not be empty!");
            return false;
        } else {
            return true;
        }
    },

    length: function (value, text, min, max) {
        if (Tool.isEmpty(text)) {
            return true;
        }
        if (!Tool.isLength(value, min, max)) {
            Toast.warning(text + " length " + min + "~" + max);
            return false;
        } else {
            return true;
        }
    }
}
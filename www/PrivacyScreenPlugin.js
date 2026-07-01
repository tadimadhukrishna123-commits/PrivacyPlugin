module.exports = {

    enable: function(success, error) {

        if (typeof success === "function") {
            success();
        }

    },

    disable: function(success, error) {

        if (typeof success === "function") {
            success();
        }

    }

};

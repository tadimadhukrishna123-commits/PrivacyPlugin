var exec = require('cordova/exec');

module.exports = {

    enable: function(success, error) {

        exec(success,
             error,
             "PrivacyScreenPlugin",
             "enable",
             []);

    },

    disable: function(success, error) {

        exec(success,
             error,
             "PrivacyScreenPlugin",
             "disable",
             []);

    }

};
(function (ng) {
    var mod = ng.module('longPlayModule', ['ngCrud']);

    mod.constant('longPlayContext', 'longPlays');

    mod.constant('longPlayModel', {
        fields: [{
                name: 'price',
                displayName: 'Price',
                type: 'Integer',
                required: true
            }, {
                name: 'album',
                displayName: 'Album',
                type: 'Reference',
                service: 'albumService',
                options: [],
                required: true
            }]});
})(window.angular);

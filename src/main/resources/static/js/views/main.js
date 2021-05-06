define(function() {
    return {
        rows: [
            {
                view: 'button',
                label: 'Cars',
                click: function() {
                    routie('')
                }
            },
            { template: 'Row 1' },
            { template: 'Row 2' },
            {
                cols: [
                    { template: 'col 1' },
                    { template: 'col 2' }
                ]
            }
        ]
    }
})

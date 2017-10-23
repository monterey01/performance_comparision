var fs = require("fs");
var contents = fs.readFileSync("array.json");
var jsonContent = JSON.parse(contents);
var a = jsonContent.numbers;

module.exports = function (app) {

    app.get('/long-operation', function (req, res) {
        //console.log(factor(largeNumber));
        console.log('hello');

        bubbleSort(a);
        res.header('Content-Type', 'application/json')
        res.send('{"message":"done"}');
    });

    function bubbleSort(a) {

        console.log(a.length);
        for (i = 0; i < a.length; i++) {
            for (j = 1; j < a.length - i; j++) {

                if (a[j - 1] < a[j]) {
                    var temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }
}
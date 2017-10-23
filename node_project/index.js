const restify = require('restify');
const restifyPlugins = require('restify-plugins');
var fs = require('fs');
//const config = require('./config');

var cluster = require('cluster');
if (cluster.isMaster) {
    console.log('Server is active. Forking workers now.');
    var cpuCount = require('os').cpus().length;
    for (var i = 0; i < cpuCount; i++) {
        cluster.fork();
    }
    cluster.on('exit', function (worker) {
        console.error('Worker %s has died! Creating a new one.', worker.id);
        cluster.fork();
    });
}

else {


    var api = restify.createServer({
        name: 'app'
    });


    api.pre(restify.pre.sanitizePath());
//Adding additional Accept Headers type to support API Gateway. Need to check if this is the best solution.
    api.acceptable.push('application/json;v=1');
    api.use(restifyPlugins.jsonBodyParser({mapParams: true}));
    api.use(restifyPlugins.acceptParser(api.acceptable));
    api.use(restifyPlugins.queryParser({mapParams: true}));
    api.use(restifyPlugins.fullResponse());

    require('./routes/longOperation')(api);

    api.listen('8080', function () {
        console.log("running");
    });
}
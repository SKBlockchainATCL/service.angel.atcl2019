#!/usr/bin/env node

// Refer original source at https://github.com/ethereum/remix-ide/blob/v0.7.6/bin/remix-ide
var path = require('path')
var httpServer = require('http-server')
var remixd = require('remixd')

var server = httpServer.createServer({
  root: path.join(__dirname, '/../')
})

var folder = process.argv.length > 2 ? process.argv[2] : process.cwd()

server.listen(8088, '127.0.0.1', function () {})
var router = new remixd.Router(65520, remixd.services.sharedFolder, { remixIdeUrl: 'http://localhost:8088' }, (webSocket) => {
  remixd.services.sharedFolder.setWebSocket(webSocket)
  remixd.services.sharedFolder.setupNotifications(folder)
  remixd.services.sharedFolder.sharedFolder(folder, false)
})

router.start()

console.log('\x1b[33m%s\x1b[0m', 'Starting Remix IDE at http://localhost:8080 and sharing ' + folder)
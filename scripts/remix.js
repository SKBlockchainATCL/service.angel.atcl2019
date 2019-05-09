#!/usr/bin/env node

// Refer original source at https://github.com/ethereum/remix-ide/blob/v0.7.6/bin/remix-ide
const path = require('path')
const program = require('commander');
const httpServer = require('http-server')
const remixd = require('remixd')

// Reference for Commander.js
//   - https://github.com/tj/commander.js/
//   - http://tj.github.io/commander.js/
program.version('0.1');
program.option('-p, --port <port>', 'HTTP port used by Remix IDE', 8088)
  .option('-b  --remix-dir <folder>', 'Directory where \'remix-ide\' package is installed')
  .option('-s, --shared-folder <folder>', 'Folder to share with Remix IDE via Remixd', '.')
  .option('-q, --remixd-port <port>', 'HTTP port used by the Remixd', 8099);
program.parse(process.argv);

//console.log(program.port);
//console.log(program.remixDir);
//console.log(program.sharedFolder);
//console.log(program.remixdPort);
//console.log(process.cwd());

var server = httpServer.createServer({
  root: program.remixDir
})

var folder = path.join(__dirname, program.sharedFolder);

server.listen(program.port, '127.0.0.1', function () {})
// @TODO Try to find method of sharedFolder to set HTTP port.
var router = new remixd.Router(65520, remixd.services.sharedFolder, { remixIdeUrl: `http://127.0.0.1:${program.port}`  }, (webSocket) => {
  remixd.services.sharedFolder.setWebSocket(webSocket)
  remixd.services.sharedFolder.setupNotifications(folder)
  remixd.services.sharedFolder.sharedFolder(folder, false)
})

router.start()

console.log('\x1b[33m%s\x1b[0m', `Starting Remix IDE at http://localhost:${program.port} and sharing ${folder}`);

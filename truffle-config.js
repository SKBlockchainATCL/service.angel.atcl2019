require("babel-polyfill");
require("babel-register")({
   "presets" : [ "env" ],
   "plugins" : [ "syntax-async-functions", "transform-regenerator" ]
});

const HDWalletProvider = require("truffle-hdwallet-provider");
const mnemonic = "hell bent for leather";

//const fs = require('fs');

//const accounts = fs.readFileSync('run/accounts').toString().split('\n');
//const config = fs.readFileSync('scripts/quorum/solo/config.sh').toString();
//const rpcPort = config.match(/\[rpcport\]=[0-9]*/g)[0].substring(10);
//const networkId = config.match(/\[networkid\]=[0-9]*/g)[0].substring(12);

/*
 * References
 *
 *   - Truffle Configuration: https://truffleframework.com/docs/truffle/reference/configuration
 *   - Truffle Commands: https://truffleframework.com/docs/truffle/reference/truffle-commands
 *   - web3.js 1.0 API : https://web3js.readthedocs.io/en/1.0/
 */
module.exports = {
   contracts_directory: "./src/main/contracts",
   contracts_build_directory: "./target/contracts",

   networks: {
      development: {
         host: "127.0.0.1",
         port: 8555,
         network_id: 37,
         from: "0xc5776c5d4ba76dd38424a160927c6b7054b55edd",
         gas: 0x40000
      },
   },

   ropsten : {
      provider : function() {
         return new HDWalletProvider(mnemonic,
               "https://ropsten.infura.io/Sqj6qg9ix47UK1EBQQb0");
      },
      network_id : 3,
   },

   rinkeby : {
      provider : function() {
         return new HDWalletProvider(mnemonic,
               "https://rinkeby.infura.io/Sqj6qg9ix47UK1EBQQb0");
      },
      network_id : 4,
   },

   kovan : {
      provider : function() {
         return new HDWalletProvider(mnemonic,
               "https://kovan.infura.io/Sqj6qg9ix47UK1EBQQb0");
      },
      network_id : 6,
   },

   mocha: {
      useColors: true
    },

   solc : {
      version: "^0.5.4",
      optimizer : {
         enabled : true,
         runs : 200
      }
   }
};

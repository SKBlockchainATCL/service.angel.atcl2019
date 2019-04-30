### Prerequisites

| Software | Min Version | Recommanded Version | Remarks |
| -------- | ----------- | ------------------- | ------- |
| JDK      | 9           |                     |         |
| Maven    |             |                     |         |
| Node.js  | 8.9.4       | 10.x ([Download Node.js LTS](https://nodejs.org/en/download/)) |   |


### Version Compatibilities

| Software | Version | Release Notes | Remarks |
| -------- | ------- | ------------- | ------- |
| web3.js  | 1.0.0-beta | https://github.com/ethereum/web3.js/releases/tag/v1.0.0-beta.52 |   |
| solc-js  | 0.5.4 | https://github.com/ethereum/solc-js/releases/tag/v0.5.4
| Truffle  | 5.0.14  | https://github.com/trufflesuite/truffle/releases/tag/v5.0.14 | Ganache Core 2.5.5  |
| Ganache CLI | 6.4.3 | https://github.com/trufflesuite/ganache-cli/releases/tag/v6.4.3 | Ganache Core 2.5.5 |
| Ganache Core | 2.5.5 | https://github.com/trufflesuite/ganache-core/releases/tag/v2.5.5 | 

### Using Ganache CLI for local lightweight standalone Ethereum client

#### Starting local Ganache CLI instance

The script expects Bash shell environment. 
If you are using Mac, there would be no problem.
If you are using Windows, try to use Git Bash.

~~~~bash
$ # If you don't initialize the project using npm, run 'npm install' at the base directory of the project.
$ npm install

$ # At the base directory of the project
$ ./scripts/ganache-cli-start.sh
~~~~

The above script will show the following message and create `run/ganachecli/data` 
directory under the base directory of the project.

The loaded Ganache CLI instance is local and standalone Ethereum client using local 8555 TCP port.

The three generated private keys and accounts are always same with the below, 'cause
the script makes use of static mnemonic to start the Ganache CLI.
For more, refert the contents of the `ganache-cli-start.sh`.

To stop the Ganache CLI instance, press 'CTRL+C' at the prompt.

~~~~
$ ./scripts/ganache-cli-start.sh
Ganache CLI v6.4.3 (ganache-core: 2.5.5)

Available Accounts
==================
(0) 0xc5776c5d4ba76dd38424a160927c6b7054b55edd (~1000000 ETH)
(1) 0x99322780c19b664e9902ff1031549da575de8f3b (~1000000 ETH)
(2) 0xf0f0717db9387ea3b095de1ff43786c63dc93e45 (~1000000 ETH)

Private Keys
==================
(0) 0xbbd0e1d8507416b8c64e88f63b4534969b9d88e4a79ebc67f4abff122f28cfb7
(1) 0xf8c91da1e73f5601a25cbffdac303138ffac30eeeda2680f1853b6ce325ac01b
(2) 0x572775a6686f4b5d3b26c46133e7419e97b88b5ba1db9e0f5d3ff9a109916a47

HD Wallet
==================
Mnemonic:      in rock machine head the dark side of the moon third stage
Base HD Path:  m/44'/60'/0'/0/{account_index}

Gas Price
==================
20000000000

Gas Limit
==================
90000

Listening on 127.0.0.1:8555
~~~~

#### Exploring local Ganache CLI instance
e
For Windows, you should use `truffle.cmd` instead of `truffle` even though you are using Git Bash.

~~~bash

service.angel.atcl2019>truffle.cmd console --network local
truffle(local)> web3.eth.getNodeInfo()
'EthereumJS TestRPC/v2.5.5/ethereum-js'
truffle(local)> web3.eth.getAccounts()
[ '0xC5776C5d4ba76dD38424A160927c6B7054b55edD',
  '0x99322780C19B664e9902Ff1031549da575De8F3B',
  '0xf0f0717dB9387ea3B095dE1FF43786C63DC93e45' ]
truffle(local)> web3.eth.getBlockNumber()
0

~~~


### References

* [`web3.js` 1.0 API](https://web3js.readthedocs.io/en/1.0/)

#! /bin/bash

readonly script_dir=$(cd `dirname $0` && pwd)
readonly data_dir=${script_dir}/../run/ganachecli/data

rm -Rf "${data_dir}"
mkdir -p "${data_dir}"

cd "${script_dir}"

# Ganache CLI : https://github.com/trufflesuite/ganache-cli#using-ganache-cli
# BIP 32 : https://github.com/bitcoin/bips/blob/master/bip-0032.mediawiki
# BIP 39 : https://github.com/bitcoin/bips/blob/master/bip-0039.mediawiki
# private key candidate
#   - b92c249c190dd162da9d28bf3e78a730f39444a9c8ead96d6b5616ec0f25b370
ganache-cli --networkId 37 \
            --port 8555 \
            --gasPrice 20000000000 \
            --gasLimit 900000 \
            --mnemonic "in rock machine head the dark side of the moon third stage" \
            --accounts 3 \
            --secure --unlock 0 --unlock 1 --unlock 2 \
            --defaultBalanceEther 1000000 \
            --blockTime 0 \
            --db "${data_dir}"
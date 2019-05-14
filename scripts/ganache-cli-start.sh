#! /bin/bash

readonly script_dir=$(cd `dirname $0` && pwd)
readonly data_dir=${script_dir}/../run/ganachecli/data

options=$(getopt -o r --long "refresh" --name 'ganache-cli-start-options' -- "$@");

if [ $? -ne 0 ]; then
  command=${0##*/}
  echo "Unable to parse command line, which expect '$command [-r|--refresh]'."
  echo ""
  exit 300
fi

eval set -- "$options"

declare refreshes=0  # false
while true; do
  case "$1" in
    -r | --refresh )
      refreshes=1
      shift ;;
    -- ) shift; break ;;
   esac
done

if [ $refreshes -eq 1 ]; then
  echo "Removing all the current data under '${data_dir}'"
  rm -Rf "${data_dir}"
fi 

if [ ! -d "${data_dir}" ]; then
  echo "Created data direcotry onto '${data_dir}'"
  mkdir -p "${data_dir}"
fi  

cd "${script_dir}"

# Ganache CLI : https://github.com/trufflesuite/ganache-cli#using-ganache-cli
# BIP 32 : https://github.com/bitcoin/bips/blob/master/bip-0032.mediawiki
# BIP 39 : https://github.com/bitcoin/bips/blob/master/bip-0039.mediawiki
#
# Accounts
#   - 0xc5776c5d4ba76dd38424a160927c6b7054b55edd
#   - 0x99322780c19b664e9902ff1031549da575de8f3b
#   - 0xf0f0717db9387ea3b095de1ff43786c63dc93e45
# Private keys
#   - 0xbbd0e1d8507416b8c64e88f63b4534969b9d88e4a79ebc67f4abff122f28cfb7
#   - 0xf8c91da1e73f5601a25cbffdac303138ffac30eeeda2680f1853b6ce325ac01b
#   - 0x572775a6686f4b5d3b26c46133e7419e97b88b5ba1db9e0f5d3ff9a109916a47
# Options
#   - gasLimit : The block gas limit (defaults to 0x6691b7)
#   - gasPrice: The price of gas in wei (defaults to 20000000000)
ganache-cli --networkId 37 \
            --port 8070 \
            --gasPrice 20000000000 \
            --gasLimit 7000000 \
            --mnemonic "in rock machine head the dark side of the moon third stage" \
            --accounts 3 \
            --secure --unlock 0 --unlock 1 --unlock 2 \
            --defaultBalanceEther 1000000 \
            --blockTime 0 \
            --db "${data_dir}"
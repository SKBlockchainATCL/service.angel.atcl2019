#! /bin/bash

readonly script_dir=$(cd `dirname $0` && pwd)
readonly remix_dir="$(npm prefix -g)\node_modules\remix-ide"

cd "${script_dir}"

node ./remix.js -p 8088 -b "$remix_dir" -s ../src/main/contracts -q 8099

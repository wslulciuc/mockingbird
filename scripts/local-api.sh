#!/bin/bash

set -e

repo_root_path=$(git rev-parse --show-toplevel)
cd $repo_root_path

echo "Bundling source..." && ./pants bundle src/jvm/mockingbird:api >/dev/null
echo "Starting local server..." && \
    cd dist/src.jvm.mockingbird.api-bundle && \
    java -jar server.jar

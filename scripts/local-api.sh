#!/bin/bash

error() {
  echo
  echo -e "ERROR: $@"

  exit 1
}

repo_root_path=$(git rev-parse --show-toplevel)
cd $repo_root_path

echo "Bundling source..." && ./pants bundle src/jvm/mockingbird:api >/dev/null || error "failed to bundle source" 
echo "Running local server..." && \
    cd dist/src.jvm.mockingbird.api-bundle && \
    java -jar server.jar || error "failed to run local server"

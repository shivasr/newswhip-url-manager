#!/usr/bin/env bash
docker run -it \
-v $PWD:$PWD \
-w $PWD \
shivasr/news-whip-shell:1.0.0
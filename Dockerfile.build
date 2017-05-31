# https://pkgs.alpinelinux.org/packages
# ビルドサーバ用のコンテナイメージを作成する
# 各種資材のビルドにはMakefileを利用する

FROM openjdk:8u131-jdk-alpine

RUN apk update && apk upgrade && \
    apk add curl nodejs-npm=6.10.3-r0 maven=3.3.9-r1 make

# デフォルトだとalpineでphantomjs使えないので対処(https://github.com/dustinblackman/phantomized)
RUN curl -Ls "https://github.com/dustinblackman/phantomized/releases/download/2.1.1/dockerized-phantomjs.tar.gz" | tar xz -C /
WORKDIR /build
COPY . /build

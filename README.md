# docker-multibranch-sample

## 方針
* Thin Server Architecture（クライアント側はVuejs、サーバ側はSpringBoot）
* ビルド・静的解析・単体テストはDockerコンテナ上で実施する。
* Dockerコンテナの管理はJenkinsfileに記述した docker pipeline plugin で行う。

# Jenkinsのセットアップ
以下プラグインを追加で入れてください。
* Checkstyle Plug-in
* FindBugs Plug-in
* JaCoCo plugin

## 注意事項
* declarative pipeline で記述しています。
* ビルド用のDockerイメージはdindに対応してないので、本コマンドを実行する環境はDockerで起動しないこと。
* /tmp/docker/cache/.m2 と /tmp/docker/cache/node_modules をjenkins実行ユーザで作成してください
* deleteDir()でファイル消せなくてビルドこける・・・


## ファイル一覧

| ファイル              | 説明                                                   |  
|:----------------------|:-------------------------------------------------------|
| Dockerfile.build      |ビルドサーバを構築するためのDockerfile                  | 
| Dockerfile.integration|結合試験を実施するためのDockerfile                      | 
| Makefile              |開発時にローカル環境で利用するコマンドをまとめたMakefile| 

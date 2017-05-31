# docker-multibranch-sample

## 方針
* ビルドはDockerコンテナ上で実施する。
* Dockerコンテナの管理はJenkinsfileに記述した docker pipeline plugin で行う。

## 注意事項
scripted syntax で記述しています。

## ファイル一覧

| ファイル              | 説明                                                   |  
|:----------------------|:-------------------------------------------------------|
| Dockerfile.build      |ビルドサーバを構築するためのDockerfile                  | 
| Dockerfile.integration|結合試験を実施するためののDockerfile                    | 
| Makefile              |開発時にローカル環境で利用するコマンドをまとめたMakefile| 

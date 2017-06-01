# docker-multibranch-sample

## 方針
* Thin Server Architecture（クライアント側はVuejs、サーバ側はSpringBoot）
* ビルド・静的解析・単体テストはDockerコンテナ上で実施する
* カレントディレクトリをDockerコンテナにマウントする
* 起動ユーザでDockerコンテナ内でコマンドを実行する
* npm、mavenをキャッシュするためにホストディレクトリをマウントする

```
make -f Makefile.docker help
```

## 注意事項
ビルド用のDockerイメージはdindに対応してないので、本コマンドを実行する環境はDockerで起動しないこと

## ファイル一覧

| ファイル              | 説明                                                   |  
|:----------------------|:-------------------------------------------------------|
| Dockerfile.build      |ビルドサーバを構築するためのDockerfile                  | 
| Dockerfile.integration|結合試験を実施するためのDockerfile                      | 
| Makefile              |開発時にローカル環境で利用するコマンドをまとめたMakefile| 
| Makefile.docker       |dockerコンテナを操作するためのコマンドをまとめたMakefile| 

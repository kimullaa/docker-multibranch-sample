# docker-multibranch-sample

## 方針
ビルドはDockerコンテナ上で実施する

```
make -f Makefile.docker help
```

## 注意事項
ビルド用のDockerイメージはdindに対応してないので、JenkinsはDockerで起動しないこと

## ファイル一覧

| ファイル              | 説明                                                   |  
|:----------------------|:-------------------------------------------------------|
| Dockerfile.build      |ビルドサーバを構築するためのDockerfile                  | 
| Dockerfile.integration|結合試験を実施するためののDockerfile                    | 
| Makefile              |開発時にローカル環境で利用するコマンドをまとめたMakefile| 
| Makefile.docker       |dockerコンテナを操作するためのコマンドをまとめたMakefile| 

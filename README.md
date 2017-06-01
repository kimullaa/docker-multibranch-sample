# docker-multibranch-sample

## 各ブランチ
|ブランチ名             | 説明                                                   |
|:----------------------|:-------------------------------------------------------|
|master                 | 自作Docker管理&コンテナのワークスペースとJenkinsのworkspaceに乖離があるのでlintレポートで困る|
|plugin                 | Docker Pipeline Pluginを使った|
|make-makefile-great-again| Docker Pipeline Pluginを参考にmasterを改良|

## 方針
* Thin Server Architecture（クライアント側はVuejs、サーバ側はSpringBoot）
* ビルド・静的解析・単体テストはDockerコンテナ上で実施する
* コンテナで生成された成果物はカレントディレクトリにコピーする
* 各ステップごとにshでDockerを起動し、成果物を取り出して破棄する
* npm、mavenをキャッシュするためにホストディレクトリをマウントする

```
make -f Makefile.docker help
```

# Jenkinsのセットアップ
以下プラグインを追加で入れてください。
* Checkstyle Plug-in
* FindBugs Plug-in
* JaCoCo plugin

## 注意事項
ビルド用のDockerイメージはdindに対応してないので、本コマンドを実行する環境はDockerで起動しないこと

## ファイル一覧

| ファイル              | 説明                                                   |  
|:----------------------|:-------------------------------------------------------|
| Dockerfile.build      |ビルドサーバを構築するためのDockerfile                  | 
| Dockerfile.integration|結合試験を実施するためのDockerfile                      | 
| Makefile              |開発時にローカル環境で利用するコマンドをまとめたMakefile| 
| Makefile.docker       |dockerコンテナを操作するためのコマンドをまとめたMakefile| 




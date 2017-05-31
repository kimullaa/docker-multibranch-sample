.PHONY: help build test lint clean

help:
	@echo "--------------------------------------------------------"
	@echo "build   クライアント～サーバをビルドする"
	@echo "unit    クライアント～サーバの単体テストを実行する"
	@echo "lint    クライアント～サーバの静的解析を実行する"
	@echo "clean   クライアント～サーバのクリーンを実行する"
	@echo "--------------------------------------------------------"

setup:
	npm --prefix client install

build: setup
	#npm --prefix client run build
	#cp -R client/dist/* server/src/main/resources/static
	mvn -f server/pom.xml package -DskipTests=true

clean:
	npm --prefix client run clean
	mvn -f server/pom.xml clean

unit: setup
	npm --prefix client run unit
	mvn -f server/pom.xml test jacoco:report

lint: setup
	npm --prefix client run lint
	mvn -f server/pom.xml compile findbugs:findbugs checkstyle:checkstyle

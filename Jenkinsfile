#!/usr/bin/env groovy
node {

    stage('setup') {
        // https://github.com/moby/moby/issues/2259
        // マウントするディレクトリがないとrootで作られるため、ディレクトリを作っておく
        sh 'mkdir -p /tmp/docker/cache/.node_modules || true'
        sh 'mkdir -p /tmp/docker/cache/.m2 || true'
    }

    CURRENT_PATH = pwd()
    // mavenはsettings.xmlでローカルリポジトリを/var/maven/.m2に設定する
    docker.build("${BUILD_ID}", "-f Dockerfile.build .").inside("-v /tmp/docker/cache/.m2:/var/maven/.m2 -v /tmp/docker/cache/.node_modules:${CURRENT_PATH}/client/node_modules") {

        withEnv(['npm_config_cache=npm-cache']) {
            stage('build') {
                sh 'make build'
                archiveArtifacts 'server/target/*jar'
            }

            stage('unit') {
                sh 'make unit'
                junit 'server/target/surefire-reports/*.xml'
                jacoco()
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'client/test/report/coverage/lcov-report/', reportFiles: 'index.html', reportName: 'カバレッジ（クライアント）', reportTitles: ''])
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'client/test/report/karma/', reportFiles: 'index.html', reportName: 'Karma', reportTitles: ''])
            }

            stage('lint') {
                sh 'make lint'
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'client/test/report/eslint/', reportFiles: 'index.html', reportName: 'ESLint', reportTitles: ''])
                findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'server/target/findbugsXml.xml', unHealthy: ''
                checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: 'server/target/checkstyle-result.xml', unHealthy: ''
            }
        }
    }

    stage('clean') {
        deleteDir()
    }

}

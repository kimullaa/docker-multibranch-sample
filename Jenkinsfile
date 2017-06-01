#!/usr/bin/env groovy
node {

    stage('clean') {
        deleteDir()
    }
    stage('checkout') {
        checkout scm
    }
    CURRENT_PATH = pwd()
    HOME_PATH = sh returnStdout: true, script: 'echo ~'
    HOME_PATH =HOME_PATH.trim()
    docker.build("${BUILD_ID}", "-f Dockerfile.build .").inside("-v /var/tmp/docker/cache/.m2:${HOME_PATH}/.m2 -v /var/tmp/docker/cache/.node_modules:${CURRENT_PATH}/client/node_modules") {

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

}

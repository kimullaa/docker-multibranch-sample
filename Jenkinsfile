#!/usr/bin/env groovy
node {
    stage('checkout') {
        checkout scm
    }
    CURRENT_PATH = pwd()
    sh 'pwd'
    sh 'ip addr show'
    sh 'ls -al'
    sh 'whoami'
    docker.build("${BUILD_ID}", "-f Dockerfile.build .").inside("-u root -v /var/tmp/docker/cache/.m2:/root/.m2 -v /var/tmp/docker/cache/.node_modules:${CURRENT_PATH}/client/node_modules") {
        stage('build') {
            sh 'pwd'
            sh 'ip addr show'
            sh 'ls -al'
            sh 'whoami'
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

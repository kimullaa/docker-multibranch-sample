#!groovy
pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/kimullamen/docker-multibranch-sample.git'
            }
        }
        stage('build') {
            steps {
                sh 'make ID=${BUILD_ID} -f Makefile.docker clean build'
                archiveArtifacts 'server/target/*jar'
            }
        }
        stage('unit') {
            steps {
                sh 'make ID=${BUILD_ID} -f Makefile.docker clean unit'
                junit 'server/target/surefire-reports/*.xml'
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'client/test/report/coverage/lcov-report/', reportFiles: 'index.html', reportName: 'カバレッジ（クライアント）', reportTitles: ''])
            }
        }
        stage('lint') {
            steps {
                sh 'make ID=${BUILD_ID} -f Makefile.docker clean lint'
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'client/test/report/eslint/', reportFiles: 'index.html', reportName: 'ESLint', reportTitles: ''])
                findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'server/target/findbugsXml.xml', unHealthy: ''
                checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: 'server/target/checkstyle-result.xml', unHealthy: ''
            }
        }
    }
}

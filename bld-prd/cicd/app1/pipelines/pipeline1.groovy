#!/usr/bin/env groovy

// library identifier: "msp-jenkins-lib@${env.BRANCH_NAME}", retriever: modernSCM(github(traits: [gitHubPullRequestDiscovery(2)], credentialsId: 'gh-svc-nable-logicnow', repository: 'msp-jenkins-lib', repoOwner: 'logicnow'))

pipeline {
    agent none
    options {
        ansiColor('xterm')
    }
    stages {
        stage('Unit Tests') {
            agent {
                kubernetes {
                    cloud "jenkins-cloud"
                    namespace 'jenkins'
                    defaultContainer 'gradle'
                    yamlFile 'build/build.yaml'
                }
            }
            environment {
                GRADLE_OPTS = "-Xmx2048m -Dorg.gradle.daemon=false"
            }
            steps{
                sh './gradlew test'
            }
        }
    }
}

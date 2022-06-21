#!/usr/bin/env groovy

def label = "jenkins-example-${UUID.randomUUID().toString()}"

podTemplate(label: label,
        containers: [
                containerTemplate(name: 'jnlp', image: 'jenkins/inbound-agent:alpine'),
        ],
        ) {
    node(label) {
        stage('Init') {
            timeout(time: 3, unit: 'MINUTES') {
                checkout scm
            }
        }
        stage('Dep') {
            echo 'Hello from Dep stage'
        }
        stage('Test') {
            echo 'Hello from Test stage'
        }
        stage('Build') {
            echo 'Hello from Build stage'
        }
    }
}

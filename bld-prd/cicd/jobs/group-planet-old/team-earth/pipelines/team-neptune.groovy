#!/usr/bin/env groovy

pipelineJob('team-neptune') {
    displayName('team neptune')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/opeomotayo/playground.git')
                    }
                    branches('*/master')
                }
            }
            scriptPath('cicd/guestbook/Jenkinsfile')
        }
    }
}

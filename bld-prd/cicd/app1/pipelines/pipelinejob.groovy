pipelineJob('pipeline-project') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            github('opeomotayo/building-a-multibranch-pipeline-project', 'https')
            credentials('github-key')
          }
          branch('master')
        }
      }
      scriptPath('Jenkinsfile')
    }
  }

  logRotator {
    daysToKeep(7)
    numToKeep(7)
  }

  configure {

  }
}


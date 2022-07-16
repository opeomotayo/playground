pipelineJob('spring-boot-api-example') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            github('opeomotayo/spring-boot-api-example', 'https')
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


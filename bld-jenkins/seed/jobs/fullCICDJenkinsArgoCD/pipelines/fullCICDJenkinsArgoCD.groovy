pipelineJob("fullCICDJenkinsArgoCD") {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url("https://github.com/opeomotayo/playground.git")
          }
          branch('master')
        }
      }
      scriptPath("dsl-pipelines/fullCICDJenkinsArgoCD/Jenkinsfile")
    }
  }

  logRotator {
    daysToKeep(7)
    numToKeep(7)
  }

}


pipelineJob("fullCICDJenkinsArgoCDFolder/updatePublishManifest") {
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
      scriptPath("dsl-pipelines/full-cicd-jenkins-argocd/update-publish-manifest/Jenkinsfile")
    }
  }

  logRotator {
    daysToKeep(7)
    numToKeep(7)
  }

}


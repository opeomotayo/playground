pipelineJob("buildPublishManifest") {
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
      scriptPath("dsl-pipelines/full-cicd-jenkins-argocd/build-publish-manifest/Jenkinsfile")
    }
  }

  logRotator {
    daysToKeep(7)
    numToKeep(7)
  }

}


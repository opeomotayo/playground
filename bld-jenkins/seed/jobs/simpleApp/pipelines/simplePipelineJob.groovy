pipelineJob("simple-app-folder/folder1/simple-app") {
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
      scriptPath("dsl-pipelines/simplePipeline.groovy")
    }
  }
}

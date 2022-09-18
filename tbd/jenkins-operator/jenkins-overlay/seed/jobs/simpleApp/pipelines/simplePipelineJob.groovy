pipelineJob("simpleAppFolder/folder1/simpleApp") {
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

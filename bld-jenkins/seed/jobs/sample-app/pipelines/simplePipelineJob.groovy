pipelineJob('pipelineJob') {
    definition {
        cps {
            script(readFileFromWorkspace('../../simplePipeline.groovy'))
            sandbox()
        }
    }
}

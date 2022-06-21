multibranchPipelineJob('multibranch-pipeline-job') {
    branchSources {
        git {
            id('github-key') // IMPORTANT: use a constant and unique identifier
            remote('git@github.com:opeomotayo/building-a-multibranch-pipeline-project.git')
            credentialsId('github-key')
            includes('JENKINS-*')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(10)
        }
    }
}

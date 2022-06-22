multibranchPipelineJob('building-a-multibranch-pipeline-project') {
  branchSources {
    github {
      id('building-a-multibranch-pipeline-project')
      repoOwner('opeomotayo')
      repository('building-a-multibranch-pipeline-project')
      scanCredentialsId('github-key')
    }
  }

  orphanedItemStrategy {
    discardOldItems {
      daysToKeep(7) //numToKeep(10)
    }
  }

  factory {
    workflowBranchProjectFactory {
      scriptPath('Jenkinsfile')
    }
  }

  configure {
    def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
    traits << 'org.jenkinsci.plugins.github__branch__source.BranchDiscoveryTrait' {
      strategyId(1)
    }
    traits << 'org.jenkinsci.plugins.github__branch__source.OriginPullRequestDiscoveryTrait' {
      strategyId(2)
    }
    traits << 'jenkins.scm.impl.trait.RegexSCMHeadFilterTrait' {
      regex(".*(master|main|dev|PR-).*")
    }
    traits << 'jenkins.plugins.git.traits.CleanBeforeCheckoutTrait' {
      extension(class: 'hudson.plugins.git.extensions.impl.CleanBeforeCheckout') {
        deleteUntrackedNestedRepositories(true)
      }
    }
  }
} 


multibranchPipelineJob('group-planet/team-earth/guestbook-pipeline') {
  branchSources {
    github {
      id('group-planet/team-earth/guestbook-pipeline')
      repoOwner('opeomotayo')
      repository('playground')
      scanCredentialsId('github-pat')
    }
  }

  orphanedItemStrategy {
    discardOldItems {
      daysToKeep(7)
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
      regex(".*(master|main|feature/|PR-).*")
    }
    traits << 'jenkins.plugins.git.traits.CleanBeforeCheckoutTrait' {
      extension(class: 'hudson.plugins.git.extensions.impl.CleanBeforeCheckout') {
        deleteUntrackedNestedRepositories(true)
      }
    }
  }
}


folder('group-planet') {
  description('Folder for group planet')

  configure {
    it / 'properties' /'org.csanchez.jenkins.plugins.kubernetes.KubernetesFolderProperty'(plugin:"kubernetes@1.19.0") {
      'permittedClouds' {
        'string' 'group-planet'
      }
    }
  }
}


pipelineJob('create-folder-creds') {
  definition {
    cps {
      script('''
        pipeline {
          agent any
          stages {
            stage('Create Username Password') {
              steps {
                // your logic here
                script {
                    gv = load "../../../../script.groovy"
                }
              }
            }
            stage('Create Private Key') {
              steps {
                // your logic here
                script {
                    gv = load "../../../../script.groovy"
                }
              }
            }
          }
        }
      }
    ''')   
    }
  }
}

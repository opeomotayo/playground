pipelineJob('credentialsFolder/credentialsPipeline') {
  definition {
    cps {
      script('''
        pipeline {
          agent any
          stages {
            stage('Create Private Key') {
              steps {
                // your logic here
                script {
                    "ls -la"
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

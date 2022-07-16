pipelineJob('credentialsFolder/credentialsPipeline') {
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
                    gv = load https://github.com/opeomotayo/playground/tree/master/dsl-pipelines/script.groovy"
                }
              }
            }
            stage('Create Private Key') {
              steps {
                // your logic here
                script {
                    ls -la
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

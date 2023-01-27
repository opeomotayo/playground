library identifier: 'jenkins-sharedlib-release@master', retriever: modernSCM([$class: 'GitSCMSource',
	remote: 'https://github.com/lbg-gcp-foundation/jenkins-sharedlib-release.git',
	credentialsId: 'vbpFrcGitHubApp'])

def config = [
    repoName: 'actions-runner-controller',
    version: '0.22.0',
    namespace: 'actions-runner-controller',
    releaseName: 'actions-runner-controller'
]

pipeline {
    options {
        timestamps()
        ansiColor('xterm')
        timeout(time: 30, unit: 'MINUTES')
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '100'))
    }

    agent {
        kubernetes {
            label "-${UUID.randomUUID()}"
            yamlFile './pipelines/config/podTemplate.yaml'
            defaultContainer 'infra-tools'
        }
    }

    <!-- environment {
        http_proxy = "ep.threatpulse.net:80"
        https_proxy ="ep.threatpulse.net:80"
        no_proxy = "metadata.google.internal,.google.com,.googleapis.com,.cloudendpointsapis.com,.oncp.dev,10.84.0.160/28,172.25.248.0/22,172.25.246.0/23"
    } -->

    stages {
        stage('Add ARC Repo') {
            steps {
                sh "helm3 repo add ${config.repoName} https://actions-runner-controller.github.io/actions-runner-controller"
                sh "helm3 pull --version ${config.version} --untar ${config.repoName}/actions-runner-controller"
            }
        }

        <!-- stage('gcloud login') {
            steps {
                script {
                    env.CLOUDSDK_COMPUTE_ZONE = sh script: 'curl --silent http://metadata.google.internal/computeMetadata/v1/instance/attributes/cluster-location -H "Metadata-Flavor: Google"', returnStdout: true
                    env.CLOUDSDK_CORE_PROJECT = sh script: 'curl --silent "http://metadata.google.internal/computeMetadata/v1/project/project-id" -H "Metadata-Flavor: Google"', returnStdout: true
                    env.CLOUDSDK_CONTAINER_CLUSTER = sh script: 'curl --silent http://metadata.google.internal/computeMetadata/v1/instance/attributes/cluster-name -H "Metadata-Flavor: Google"', returnStdout: true
                    sh 'gcloud container clusters get-credentials $CLOUDSDK_CONTAINER_CLUSTER'
                }
            }
        } -->

        <!-- stage('Update Secret') {
            when { branch 'main' }
            steps {
                sh 'gcloud secrets versions access latest --secret=vbp-frc-bld-sa-frc-secret-consumer-key --project=vbp-kms-bld-3451 >> ./resources/sa-frc-secret-consumer-key.json'
                sh "kubectl -n ${config.namespace} create secret generic sa-frc-secret-consumer-key --dry-run=client -o yaml --from-file=./resources/sa-frc-secret-consumer-key.json | kubectl apply -f -"
                sh "rm ./resources/sa-frc-secret-consumer-key.json"
                withCredentials([file(credentialsId: 'actions-controller-secret', variable: 'FILE')]) {
                    dir('subdir') {
                        sh "kubectl -n ${config.namespace} apply -f \$FILE"
                    }
                }
            }
        } -->

        <!-- stage('Update Resources') {
            when { branch 'main' }
            steps {
                sh "kubectl -n ${config.namespace} create configmap wssca --dry-run=client -o yaml --from-file=./resources/wss.crt | kubectl apply -f -"
                sh "kubectl -n ${config.namespace} create configmap get-github-app-token --dry-run=client -o yaml --from-file=./resources/scripts/get-app-token.sh | kubectl apply -f -"
                sh "kubectl -n ${config.namespace} create configmap move-sa-key --dry-run=client -o yaml --from-file=./resources/scripts/move-sa-key.sh | kubectl apply -f -"
                sh 'kubectl apply -f pipelines/config/permissive-crb.yaml'
            }
        } -->

        <!-- stage('Upgrade ARC Release') {
            when { branch 'main' }
            steps {
                script {
                    def flags = '--install --create-namespace --wait --cleanup-on-fail'
                    def options = "-n ${config.namespace} -f 'helm-overrides/${env.CLOUDSDK_CONTAINER_CLUSTER}/values.yaml' ${config.releaseName} ./actions-runner-controller" 
                    sh "helm3 upgrade $flags $options"
                }
            }
        } -->

        <!-- stage('Deploy Runners') {
            when { branch 'main' }
            steps {
                sh "./generateRunnerSetYaml.sh ${config.namespace}"
            }
        } -->
    }
}

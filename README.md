Applications are deployed using github actions or Jenkins.
Workflows:
playground/.github/workflows/ contains workflows to build and deploy applications

Pipelines:
playground/jenkins-ci-pipelines/ contains pipelines to build applications
shared-services/jenkins-cd-pipelines/ contains pipelines to build applications


How Jenkins was installed:

ArgoCD uses helm-jenkins in https://github.com/opeomotayo/playground/blob/master/devops-tools/argocd/apps/jenkins.yaml to install Jenkins

```yaml
apiVersion: argoproj.io/v1alpha1
kind: Application
metadata: 
  name: helm-jenkins
  namespace: argocd
  ...
```

How to deploy Action Runner Controller via Jenkins and Helm
Reference: https://www.youtube.com/watch?v=lD0t-UgKfEo&t=784s
Create a github app, add github app permissions,  
https://github.com/settings/apps/self-hosted-github-app




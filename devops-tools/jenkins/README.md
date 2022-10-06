https://appfleet.com/blog/how-to-set-up-jenkins-on-kubernetes/
https://www.bogotobogo.com/DevOps/Docker/Docker-Kubernetes-Jenkins-Helm.php ***
https://octopus.com/blog/jenkins-helm-install-guide
https://www.youtube.com/watch?v=ObGR0EfVPlg&t=116s
https://www.youtube.com/watch?v=mzm7prM4f64
https://www.youtube.com/watch?v=KB7thPsG9VA&t=2351s
https://devopscube.com/jenkins-build-agents-kubernetes/
https://www.youtube.com/watch?v=o4QG_kqYvHk


 2916  helm template charts/jenkins
 2917  helm template charts/jenkins -f charts/jenkins/values.yaml
 2918  helm lint charts/jenkins
 2919  helm upgrade --install jenkins jenkins/jenkins
 2920  helm template charts/jenkins
 2921  helm template charts/jenkins -f charts/jenkins/values.yaml
 2922  vi charts/jenkins/Chart.yaml
 2923  vi charts/jenkins/values.yaml
 2924  helm package charts/jenkins -d charts/
 2925  helm repo index charts
 2926  git add -A
 2927  git commit -m "Updating index.yaml"
 2928  git push
 2929  git pull
 2930  git commit -m "Updating index.yaml"
 2931  git push
 2932  helm repo update
 2933  helm upgrade --install jenkins jenkins/jenkins

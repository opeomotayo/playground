https://appfleet.com/blog/how-to-set-up-jenkins-on-kubernetes/
https://www.bogotobogo.com/DevOps/Docker/Docker-Kubernetes-Jenkins-Helm.php ***
https://octopus.com/blog/jenkins-helm-install-guide
https://www.youtube.com/watch?v=ObGR0EfVPlg&t=116s
https://www.youtube.com/watch?v=mzm7prM4f64
https://www.youtube.com/watch?v=KB7thPsG9VA&t=2351s
https://devopscube.com/jenkins-build-agents-kubernetes/
https://www.youtube.com/watch?v=o4QG_kqYvHk

  

References:
```yaml
How to update and push Jenkins helm chart to a remote repo (github pages)
video explaination: https://www.youtube.com/watch?v=Xp8gTpSYyRo
chart repos are create in playground-depency repo
create a new branch (helm-repo-host), follow the below instructions and push changes
go to settings, pages, change branch name to new branch
update values in charts/jenkins/values.yaml
update vi charts/jenkins/Chart.yaml
lint chart: helm lint charts/jenkins
view chart template: helm template charts/jenkins
package chart: helm package charts/jenkins -d charts/
update index.yaml file: helm repo index charts
git add -A
git commit -am "Updating index.yaml"
git push
helm fetch jenkins/jenkins
helm search repo jenkins/jenkins
helm repo update
helm upgrade --install jenkins jenkins/jenkins -n jenkins
kubectl exec --namespace jenkins -it svc/jenkins -c jenkins -- /bin/cat /run/secrets/additional/chart-admin-password && echo
```
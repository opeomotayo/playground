Installation Instruction:
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml


https://www.stackovercloud.com/2022/01/22/how-to-deploy-to-kubernetes-using-argo-cd-and-gitops/

https://argo-cd.readthedocs.io/en/stable/operator-manual/ingress/#option-2-multiple-ingress-objects-and-hosts

kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
argocd login argo-cd.opeomotayo.net:443
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
argocd app create helm-guestbook --repo https://github.com/argoproj/argocd-example-apps.git --path helm-guestbook --dest-server https://kubernetes.default.svc --dest-namespace apps
argocd app get helm-guestbook
argocd app sync helm-guestbook
https://cloudyuga.guru/blog/jenkins-argo
kubectl port-forward svc/argocd-server -n argocd 8080:443 --address 167.235.236.197
sudo curl -sSL -o /usr/local/bin/argocd https://github.com/argoproj/argo-cd/releases/latest/download/argocd-linux-amd64\n
sudo chmod +x /usr/local/bin/argocd
argocd version --grpc-web
argocd login argocd.opeomotayo.net:443 --grpc-web
argocd cluster list
k get app -n argocd
argocd app create app-2 --repo https://github.com/opeomotayo/playground.git --revision master --path applications/guestbook --dest-server https://kubernetes.default.svc --dest-namespace app-2 --sync-option CreateNamespace=true --grpc-web
argocd app sync app-2 --grpc-web
argocd app list --grpc-web
argocd app delete kustomize-guestbook
k get appproject -n argocd -o yaml
k apply -f role-project.yaml
k get appproject -n argocd
argocd proj role create-token role-project ci-role --grpc-web
argocd app delete git-guestbook --grpc-web --auth-token eyJhbGciOiJ...
argocd app create nginx-ingress --repo https://charts.helm.sh/stable --helm-chart nginx-ingress --revision 1.24.3 --dest-namespace default --dest-server https://kubernetes.default.svc --self-heal

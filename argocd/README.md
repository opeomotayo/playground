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
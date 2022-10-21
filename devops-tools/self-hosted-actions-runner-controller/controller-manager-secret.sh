kubectl create secret generic controller-manager \
    -n actions \
    --from-literal=github_app_id=188792 \
    --from-literal=github_app_installation_id=24796500 \
    --from-file=github_app_private_key=/home/opeomotayo/self-hosted-github-app.2022-10-21.private-key.pem

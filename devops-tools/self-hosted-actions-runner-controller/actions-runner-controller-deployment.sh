helm install actions \
    actions-runner-controller/actions-runner-controller \
    --namespace actions \
    --version 0.21.0 \
    --set syncPeriod=1m

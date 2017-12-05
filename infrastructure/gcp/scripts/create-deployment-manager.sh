#!/bin/bash
#create a stack instance

#projectID=$(gcloud projects list --query "list[0].PROJECT_ID" --output text)

gcloud deployment-manager deployments create my-first-deployment --config ../configuration/vm.yaml

gcloud deployment-manager deployments list
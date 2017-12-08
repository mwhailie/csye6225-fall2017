#!/bin/bash
#create a stack instance

projectID=$(gcloud config list --format 'value(core.project)')

gcloud deployment-manager deployments update my-first-deployment --config ../configuration/vm.yaml 

gcloud deployment-manager deployments list 

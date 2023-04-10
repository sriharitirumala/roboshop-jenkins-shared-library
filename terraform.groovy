def call() {
    pipeline {
        agent any
        stages {
            stage('init') {
                steps {
                    sh 'terraform int -backend-config=env-prod/state.tfvars'
                }
            }

            stage('apply') {
                steps {
                    sh 'terraform apply -auto-approve -var-file=env-prod/main.tfvars'
                }
            }
        }
    }
}
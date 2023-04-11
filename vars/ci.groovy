def call () {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts=""
    }
    pipeline {
        agent any

        stages {

            stage('Compile/Build') {
                steps {
                    mail bcc: '', body: 'test message', cc: '', from: 'sritirumala30@gmail.com', replyTo: '', subject: 'test', to: 'sritirumala30@gmail.com'
                    script {
                        common.compile()
                    }
                }
            }

            stage('Test Cases') {
                steps {
                    script {
                        common.testcases()
                    }
                }
            }
            stage('Code Quality') {
                steps {
                    script {
                        common.codequality()
                    }
                }
            }
        }
    }
}
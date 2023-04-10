def call (){
    pipeline {
        agent any

        s tages {
            stage("Compile/Build") {
                steps {
                    scrpit {
                        if (app_lang == "nodejs") {
                            sh 'npm install'
                        }
                        if (app_lang == "maven") {
                            sh 'mvn package'
                        }
                    }
                }
            }
        }

            stage ("Test Cases") {
                steps{
                    echo "Test Cases"
                }
            }
        }
    }
}
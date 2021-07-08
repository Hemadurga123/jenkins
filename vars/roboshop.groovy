def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP : '172.31.10.228',
          SLAVE_LABEL : 'JAVA',

  ]
  args << params

  // End Default + Required Arguments
  pipeline {
    agent any

    environment {
      COMPONENT = "${args.COMPONENT}"
      NEXUS_IP = "${args.NEXUS_IP}"
      PROJECT_NAME = "${args.PROJECT_NAME}"
      SLAVE_LABEL = "${args.SLAVE_LABEL}"

    }


    stages {
      stage('prepare  the  Artifact') {
        steps {
          sh '''
         zip -r ../login.zip *
       '''
        }
      }

    }
  }
}
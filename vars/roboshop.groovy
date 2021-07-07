def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP         : '172.31.10.228',

  ]
  args << params

  // End Default + Required Arguments
  pipeline{
    agent  any

    environment {
      COMPONENT = "${args.COMPONENT}"
      NEXUS_IP = "${args.NEXUS_IP}"
      PROJECT_NAME = "${args.PROJECT_NAME}"
      SLAVE_LABEL :  "${args.SLAVE_LABEL}"

    }


    stages {
      stage ('prepare  the  Artifact ---nginx') {
        when {
          environment name: 'COMPONENT', value: 'frontend'
        }

        steps{
          sh '''
         zip -r ../${COMPONENT.zip} *
       '''
        }
      }
      stage ('preparing the Artifact -----login') {
        when {
          environment name: 'COMPONENT', value: 'login'
        }

        steps{
          sh '''
         zip -r ../${COMPONENT.zip} *
       '''
        }
      }
      stage ('preparing the Artifact ------todo') {
        when {
          environment name: 'COMPONENT', value: 'todo'
        }

        steps{
          sh '''
         zip -r ../${COMPONENT.zip} *
       '''
        }
      }
      stage ('preparing the Artifact  ------users') {
        when {
          environment name: 'COMPONENT', value: 'users'
        }

        steps{
          sh '''
         zip -r ../${COMPONENT.zip} *
       '''
        }
      }

      stage('upload artifact'){
        steps{
          sh '''
          curl -f -v -u admin:admin123 --upload-file ../frontend.zip http://${NEXUS_IP}:8081/repository/frontend/frontend.zip

        '''


        }

      }

    }
  }

}

















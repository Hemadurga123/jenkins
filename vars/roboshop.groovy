def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP   : '172.31.10.228',
          SLAVE_LABEL: 'JAVA',

  ]
  args << params

  // End Default + Required Arguments
  pipeline {
    agent {
      label "${args.SLAVE_LABEL}"
    }

    environment {
      COMPONENT = "${args.COMPONENT}"
      NEXUS_IP = "${args.NEXUS_IP}"
      PROJECT_NAME = "${args.PROJECT_NAME}"
      SLAVE_LABEL = "${args.SLAVE_LABEL}"
      APP_TYPE = "${args.APP_TYPE}"

    }


    stages {
      stage('prepare  the  Artifact ------frontend') {
        when{
          environment name: 'COMPONENT', value: 'frontend'

        }
        steps {
          script{
            prepare = new nexus()
            prepare.make_artifacts 'frontend'

          }
          sh '''
          ls 
       '''
        }
      }
      stage('prepare  the  Artifact ------login') {
        when{
          environment name: 'COMPONENT', value: 'login'

        }
        steps {
          sh '''
         zip -r ../login.zip *
       '''
        }
      }
      stage('prepare  the  Artifact ------users') {
        when{
          environment name: 'COMPONENT', value: 'users'

        }
        steps {
          sh '''
         zip -r ../users.zip *
       '''
        }
      }
      stage('prepare  the  Artifact ------todo') {
        when{
          environment name: 'COMPONENT', value: 'todo'

        }
        steps {
          sh '''
         zip -r ../todo.zip *
       '''
        }
      }
    }
  }
}
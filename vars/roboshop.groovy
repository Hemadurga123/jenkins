def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP   : '172.31.10.228',
          SLAVE_LABEL: 'NODEJS',

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
      stage('prepare  the  Artifact ------NGINX') {
        when{
          environment name: 'APP_TYPE', value: 'JAVA'

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
          script{
            prepare = new nexus()
            prepare.make_artifacts 'login'
          }
          sh '''
         ls
       '''
        }
      }
      stage('prepare  the  Artifact ------users') {
        when{
          environment name: 'COMPONENT', value: 'users'

        }
        steps {
          script{
            prepare = new nexus()
            prepare.make_artifacts 'users'
          }
          sh '''
          ls
       '''
        }
      }
      stage('prepare  the  Artifact ------todo') {
        when{
          environment name: 'COMPONENT', value: 'todo'

        }
        steps {
          script{
            prepare = new nexus ()
            prepare.make_artifacts 'todo'
          }
          sh '''
          ls
       '''
        }
      }
    }
  }
}
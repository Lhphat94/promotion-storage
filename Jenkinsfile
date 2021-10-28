pipeline {
    agent any
    
    tools
    {
       maven "maven"
    }
     
    stages {
      stage('checkout') {
           steps {
             
                git branch: 'master', url: 'https://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@github.com/huynguyen-99/promotion-storage.git'
             
          }
        }

      stage('Execute Maven') {
           steps {
                sh 'pwd'
                sh 'mvn install -Dmaven.test.skip=true -Djacoco.skip=true'             
          }
        }
    }
}

node {
  stage('SCM') {
    git 'https://github.com/vboar/RiskSystem.git/'
  }
  stage('build') {
    def mvnHome = tool 'Maven3'
    sh "${mvnHome}/bin/mvn -B clean package"
  }
  stage('deploy') {
    sh "docker stop my-tomcat || true"
    sh "docker rm my-tomcat || true"
    sh "docker run --name my-tomcat -p 11111:8080 -d tomcat"
    sh "docker cp target/risk-system.war my-tomcat:/usr/local/tomcat/webapps"
  }
  stage('results') {
    archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
  }
}
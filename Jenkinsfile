node {
  stage('SCM') {
    git 'https://github.com/vboar/RiskSystem.git/'
  }
  stage('build') {
    def mvnHome = tool 'Maven3'
    sh "${mvnHome}/bin/mvn -B clean package"
  }
  stage('results') {
    archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
  }
}
@Library('TEaaS')
import org.coep.*

def stages = new Stages()

node {
	stage('Checkout SCM') {
		echo 'Checking out SCM'
	}
	stage('Build') {
		echo 'Compiling'
		echo 'Building'
	}
	stage('Run regression test') {
		echo 'Running all tests'
	}
	stage('Publish test results') {
		echo 'Publishing test results'
		stages.publishTestResults()
	}
	stage('Clean up') {
		echo 'Clearing workspace'
	}
	stage('Send Email') {
		echo 'Sending test report via email'
	}
}

@Library('TEaaS')
import org.coep.*

node {
	stages {
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
		}
		stage('Clean up') {
			echo 'Clearing workspace'
		}
		stage('Send Email') {
			echo 'Sending test report via email'
		}
	}
}

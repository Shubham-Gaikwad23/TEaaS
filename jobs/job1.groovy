@Library('TEaaS')
import org.coep.*

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
		String url = 'https://gist.github.com/cbergau/65e6eac87d69161f41ba'
		httpRequest consoleLogResponseBody: true, ignoreSslErrors: true, outputFile: 'test_results.xml', responseHandle: 'NONE', url: url, wrapAsMultipart: false
		junit '**test_results.xml'
	}
	stage('Clean up') {
		echo 'Clearing workspace'
	}
	stage('Send Email') {
		echo 'Sending test report via email'
	}

	/**
	 writeFile file: 'test_results.xml', text: '''<testsuite tests="3">
	 <testcase classname="foo1" name="ASuccessfulTest"/>
	 <testcase classname="foo2" name="AnotherSuccessfulTest"/>
	 <testcase classname="foo3" name="AFailingTest">
	 <failure type="NotEnoughFoo"> details about failure </failure>
	 </testcase>
	 </testsuite>'''
	 */
}

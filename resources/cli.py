import jenkins

server = jenkins.Jenkins('http://localhost:8080', username='jenkins', password='jenkins')
user = server.get_whoami()
version = server.get_version()
print('Hello %s from Jenkins %s' % (user['fullName'], version))
print("Triggering job build...")
server.build_job('Main/main_job')
print("Build is running. Email will be sent after completion")
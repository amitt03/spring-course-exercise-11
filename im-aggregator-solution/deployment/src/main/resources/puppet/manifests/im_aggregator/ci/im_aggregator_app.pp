## This manifest file contains the instructions required to setup an 'im_aggregator_app' application
## server. It installs the tomcat application server, creates a customized 'im_aggregator.properties'
## file and deploys the 'im_aggregator' RPM.

node /ctvr-git01/ inherits default {

	class{'im_aggregator::app':
	    snmp_port                       => '389',
		csds_domain                     => '192.168.21.129:8080',
        im_aggregator_buildversion  => 'latest',
	}

}
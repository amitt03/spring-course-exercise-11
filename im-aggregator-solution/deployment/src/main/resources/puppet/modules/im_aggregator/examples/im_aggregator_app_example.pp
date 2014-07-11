## This manifest file contains the instructions required to setup an 'im_aggregator_app' application
## server. It installs the tomcat application server, creates a customized 'im_aggregator.properties'
## file and deploys the 'im_aggregator' RPM.

node /REPLACE WITH YOUR NODE'S HOSTNAME OR REGEX/ inherits default {

	class{'im_aggregator::app':
	    snmp_port                       => 'REPLACE WITH YOUR SNMP PORT (e.g. 389 or 1389)',
		csds_domain                     => 'REPLACE WITH YOUR CSDS DOMAIN (e.g. 192.168.21.129:8080 or adminlogin.liveperson.net)',
        add_params                      => (optional) REPLACE WITH ADDITIONAL PARAMETERS YOU NEED FOR THIS ENV ONLY AS HASH {'private.timeout'=>'1500'},
        im_aggregator_buildversion  => 'REPLACE WITH THE im_aggregator RPM VERSION OR latest',
	}

}
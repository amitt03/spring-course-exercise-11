## This manifest file contains the instructions required to setup an 'im_aggregator' web
## server. It installs the apache (httpd) service and creates two apache 'conf' files one for read requests one for write requests.

node /ctvr-git01/ inherits default {

	class{'im_aggregator::web':
        http_port             				=>  '80',
        https_port            				=>  '8443',
        server_name	                		=>  $hostname,
        ssl_certificate_file        		=>  'dev_wildCard.crt',
        ssl_certificate_key_file    		=>  'dev_wildCard.key',
        ssl_ca_certificate_file     		=>  'DigiCertCA.crt',
				im_aggregator_balancer_member   =>  'ctvr-git01',
		        ajp_port                    		=>  '8009',
	}

    file{'/liveperson/code/server_apache/certs/dev_wildCard.crt':
            ensure => present,
            mode => 0644,
            source => "puppet:///modules/certs/wildcard.dev.lprnd.net.crt",
    }
    file{'/liveperson/code/server_apache/certs/dev_wildCard.key':
            ensure => present,
            mode => 0644,
            source => "puppet:///modules/certs/wildcard.dev.lprnd.net.key",
    }

    file{'/liveperson/code/server_apache/certs/DigiCertCA.crt':,
            ensure => present,
            mode => 0644,
            source => "puppet:///modules/certs/DigiCertCA.crt",
    }


}

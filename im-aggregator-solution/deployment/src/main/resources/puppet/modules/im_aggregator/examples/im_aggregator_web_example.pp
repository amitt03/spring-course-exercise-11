## This manifest file contains the instructions required to setup an 'im_aggregator' web
## server. It installs the apache (httpd) service and creates two apache 'conf' files one for read requests one for write requests.

node /REPLACE WITH YOUR NODE'S HOSTNAME OR REGEX/ inherits default {

	class{'im_aggregator::web':
        http_port             				=>  'REPLACE WITH YOUR READ HTTP PORT (e.g. 18080)',
        https_port            				=>  'REPLACE WITH YOUR READ HTTPS PORT (e.g. 18443)',
        server_name	                		=>  'REPLACE WITH YOUR WRITE SERVER NAME (e.g. tlv-web)',
        ssl_certificate_file        		=>  SSL CERTIFICATE FILE PLACED UNDER THE 'CERTS' FOLDER (for example - 'domain.cer'),
        ssl_certificate_key_file    		=>  SSL CERTIFICATE KEY FILE PLACED UNDER THE 'CERTS' FOLDER (for example - 'domain.key'),
        ssl_ca_certificate_file     		=>  SSL CERTIFICATE CHAIN FILE PLACED UNDER THE 'CERTS' FOLDER (for example - 'intermediate.crt'),
        im_aggregator_balancer_member   =>  'REPLACE WITH YOUR im_aggregator TOMCAT IP OR VIP (e.g. 'tlv-ac-cdn-int1' or ['tlv-ac-cdn-int1', 'tlv-ac-cdn-int2'])',
        ajp_port                    		=>  'REPLACE WITH YOUR AJP PORT (e.g. 8009)',
	}

}

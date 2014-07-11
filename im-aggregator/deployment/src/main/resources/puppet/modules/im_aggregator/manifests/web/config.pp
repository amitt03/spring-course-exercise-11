/*

== Class: im_aggregator::web::config

Installs live engage web server

Example usage:

  include im_aggregator::web::config

*/

class  im_aggregator::web::config {

	file { "$apache_webserver::params::vdir/im_aggregator_web.conf":
		content => template("im_aggregator/web/im_aggregator_web.conf.erb"),
		owner   => 'web',
		group   => 'web',
		mode    => '0644',
		require => [Class['im_aggregator::web::install'],Class['apache_webserver::config']],
		notify => Service['httpd'],
    }

    file { "$apache_webserver::config::logdir/../cache":
        ensure => directory,
        owner =>  'web',
        group => 'web',
    	mode => 0755,
    	require => [Class['im_aggregator::web::install'],Class['apache_webserver::config']],
    }

}

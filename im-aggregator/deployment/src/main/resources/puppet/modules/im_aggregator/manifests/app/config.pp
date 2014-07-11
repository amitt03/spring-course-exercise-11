/*

== Class: im_aggregator::app::config

configs the im_aggregator app server properties

Example usage:

  include im_aggregator::app::config

*/

class im_aggregator::app::config {

    file { "${apache_tomcat::params::tomcat_home}/conf/liveperson/im_aggregator/im_aggregator.properties":
        content => template("im_aggregator/app/im_aggregator.properties.erb"),
        owner   => 'appuser',
        group   => 'appgrp',
        mode    => '0644',
        require => [Class['im_aggregator::app::install'],Class['apache_tomcat::params']],
        notify => Service['LPtomcat'],
    }

}

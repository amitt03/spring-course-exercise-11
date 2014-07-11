/*

== Class: im_aggregator::app::install

Installs the live-engage im_aggregator app server

Example usage:

  include im_aggregator::app::install

*/

class im_aggregator::app::install(
    $buildversion = installed,
    $max_jvm_heap_size,
    $max_perm_size,
    $extra_catalina_opts,
    ){

     include apache_tomcat

      class {'apache_tomcat::config':
        max_perm_size => $max_perm_size,
        enable_utf8_file_encoding => true,
        max_jvm_heap_size=>$max_jvm_heap_size,
        extra_catalina_opts=>$extra_catalina_opts

      }

      package{'im_aggregator':
        ensure => $buildversion,
        notify => Service['LPtomcat'],
      }

}
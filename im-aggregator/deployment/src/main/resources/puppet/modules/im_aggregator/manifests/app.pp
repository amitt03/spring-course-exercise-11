class im_aggregator::app(
	$snmp_port,
	$csds_domain,
    $add_params = nil,
    $im_aggregator_buildversion,
    $max_jvm_heap_size = "2G",
    $max_perm_size = "256m",
    $extra_catalina_opts = false,
) {
     if ( $buildversion ) {
             $war_rpm_buildversion = $buildversion
         } else {
             $war_rpm_buildversion = $im_aggregator_buildversion
         }

    class{'im_aggregator::app::install':
        buildversion    => $war_rpm_buildversion,
        max_jvm_heap_size    => $max_jvm_heap_size,
        max_perm_size => $max_perm_size,
        extra_catalina_opts => $extra_catalina_opts,
    }
    include im_aggregator::app::config

}

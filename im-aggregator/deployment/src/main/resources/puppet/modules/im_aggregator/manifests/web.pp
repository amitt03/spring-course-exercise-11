class im_aggregator::web(
    $http_port,
    $https_port,
    $server_name,
    $ssl_certificate_file,
    $ssl_certificate_key_file,
	$ssl_ca_certificate_file,
    $ssl_certificate_chain_file = nil,
    $im_aggregator_balancer_member,
    $ajp_port,
) {

    include im_aggregator::web::install
    include im_aggregator::web::config

}
/*

== Class: im_aggregator::web::install

Installs live engage web server

Example usage:

  include im_aggregator::web::install

*/

class im_aggregator::web::install {

  class{'apache_webserver': }
  class{'apache_webserver::ssl': }
  class{'apache_webserver::config': }

}

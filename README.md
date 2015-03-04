Office Mate Tracking
==================

Small Wicket based Web Application with [Android Companion](https://github.com/atomfrede/mustached-nemesis) to make your office Club Mate supply tracking easier

[![Build Status](https://travis-ci.org/atomfrede/freezing-octo-bear.svg?branch=master)](https://travis-ci.org/atomfrede/freezing-octo-bear)
[![Build Status](https://drone.io/github.com/atomfrede/freezing-octo-bear/status.png)](https://drone.io/github.com/atomfrede/freezing-octo-bear/latest)
[![Coverage Status](https://coveralls.io/repos/atomfrede/freezing-octo-bear/badge.png)](https://coveralls.io/r/atomfrede/freezing-octo-bear)
<a href="https://scan.coverity.com/projects/2584">
  <img alt="Coverity Scan Build Status"
       src="https://scan.coverity.com/projects/2584/badge.svg"/>
</a>
[![Stories in Ready](https://badge.waffle.io/atomfrede/freezing-octo-bear.png?label=ready&title=Ready)](https://waffle.io/atomfrede/freezing-octo-bear)
[![Stories in Progress](https://badge.waffle.io/atomfrede/freezing-octo-bear.png?label=in%20progress&title=In%20Progress)](https://waffle.io/atomfrede/freezing-octo-bear)
[![endorse](http://api.coderwall.com/atomfrede/endorsecount.png)](http://coderwall.com/atomfrede)

Example MySQL Properties
========================
    
     connection pool config (c3p0 ComboPooledDataSource)
     # all time values are in seconds
     c3p0.minPoolSize=1
     c3p0.maxPoolSize=200
     c3p0.maxConnectionAge=3800
     c3p0.maxIdleTime=3600
     c3p0.checkoutTimeout=20000
     c3p0.idleConnectionTestPeriod=300
     c3p0.automaticTestTable=jdbc_pool_check
     
     # connect data
     jdbc.driver=com.mysql.jdbc.Driver
     jdbc.url=jdbc:mysql://YOUR-DATABASE?characterEncoding=utf8
     jdbc.username=USERNAME
     jdbc.password=PASSWORD
     hibernate.dialect=de.atomfrede.mate.domain.MySQL5InnoDBUtf8Dialect
     hibernate.show_sql=true
     hibernate.hbm2ddl.auto=create
    
Setup
=====

Jetty: Adapt your Database Configuration (e.g. MySQL), build it, put the war into Jetty's webapps folder: you're done.

Contributors
============

License
========

    Copyright 2013 Frederik Hahne
    
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

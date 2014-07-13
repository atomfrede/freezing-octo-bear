Office Mate Tracking
==================

Small Wicket based Web Application with [Android Companion](https://github.com/atomfrede/mustached-nemesis) to make your office Club Mate supply tracking easier

[![Build Status](https://travis-ci.org/atomfrede/freezing-octo-bear.svg?branch=master)](https://travis-ci.org/atomfrede/freezing-octo-bear)
[![Build Status](https://drone.io/github.com/atomfrede/freezing-octo-bear/status.png)](https://drone.io/github.com/atomfrede/freezing-octo-bear/latest)
[![Coverage Status](https://coveralls.io/repos/atomfrede/freezing-octo-bear/badge.png)](https://coveralls.io/r/atomfrede/freezing-octo-bear)
[![Stories in Ready](https://badge.waffle.io/atomfrede/freezing-octo-bear.png?label=ready&title=Ready)](https://waffle.io/atomfrede/freezing-octo-bear)
[![Stories in Progress](https://badge.waffle.io/atomfrede/freezing-octo-bear.png?label=in%20progress&title=In%20Progress)
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
	
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
